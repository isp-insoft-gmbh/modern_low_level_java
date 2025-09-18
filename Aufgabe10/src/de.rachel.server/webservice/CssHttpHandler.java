package webservice;

import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CssHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException{
      String response = "";

      if (httpExchange.getRequestMethod().equals("GET")) {
        response = getCssContent();
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
   }

   private String getCssContent() {
    return """
        th {
          background-color: lightgray;
        }
        td {
          background-color: aquamarine;
        }
        """;
   }
}
