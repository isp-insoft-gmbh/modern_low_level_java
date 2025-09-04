package webservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import parserlib.ParseCsv;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException{
    //   System.out.println(httpExchange.getRequestMethod());
      // System.out.println(httpExchange.getRequestHeaders());
      // System.out.println(httpExchange.getRequestBody());
    //   System.out.println(httpExchange.getRequestURI());
      //Code for what happens at the server, here we simply print
      //the request message

      String response = "";

      if (httpExchange.getRequestMethod().equals("GET")) {
        response = getWelcomeSite();
        try {
          byte[] bs = response.getBytes("UTF-8");
          httpExchange.sendResponseHeaders(200, bs.length);
          OutputStream os = httpExchange.getResponseBody();
          os.write(response.getBytes());
          os.close();
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
      }


      if (httpExchange.getRequestMethod().equals("POST")) {
        try {
          StringBuilder csvContent = new StringBuilder();
          int readedBlankLines = 0;
          InputStream inStream = httpExchange.getRequestBody();
          Scanner scanner = new Scanner(inStream);
          while (scanner.hasNextLine()){
            String data = scanner.nextLine();

            // all stuff between the two empty lines are the csv content
            if (data.equals("")) {
              readedBlankLines++;
            }

            if (readedBlankLines == 1) {
              csvContent.append(data + System.lineSeparator());
            }

          }
          scanner.close();

          ParseCsv csvParser = new ParseCsv();
          csvParser.parseCsv(csvContent.toString().trim());

          response = getSiteWithCsvParsedTable(csvParser);
          byte[] bs = response.getBytes("UTF-8");
          httpExchange.sendResponseHeaders(200, bs.length);
          OutputStream os = httpExchange.getResponseBody();
          os.write(response.getBytes());
          os.close();

        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
      }
   }

   private String getWelcomeSite() {
    String welcomeSite = """
        <!DOCTYPE html><html lang="de">
        <head>
          <title>CSV Parser Site</title>
          <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        </head>
          <body>
            <div>
              <h1>Welcome to the awesome CSV Parser Site!</h1>
            </div>
            <div>
              <p>Bitte csv Datei für den Upload angeben</p>
              <form name="csvupload" action="/" method="post" enctype="multipart/form-data">
              <label>Dateiauswahl (*.csv):</label>
              <input name="datei" type="file" size="50" accept="text/csv">
              <button type="submit">Datei hochladen</button>
              </form>
            </div>
          </body>
        </html>
        """;
    return welcomeSite;
   }

   private String getSiteWithCsvParsedTable(ParseCsv csvParser) {
    String responseSite = """
        <!DOCTYPE html><html lang="de">
        <head>
          <title>CSV Parser Site</title>
          <meta http-equiv="content-type" content="text/html; charset=UTF-8">
          <link rel="stylesheet" href="/style.css">
        </head>
          <body>
            <div>
              <h1>And here it is... the analyzed CSV as a table</h1>
            </div>
            <div>
            %s
            </div>
          </body>
        </html>
        """.formatted(csvParser.getHtmlTable());

    return responseSite;
   }
}
