package webservice;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException{
    //   System.out.println(httpExchange.getRequestMethod());
    //   System.out.println(httpExchange.getRequestHeaders());
      System.out.println(httpExchange.getRequestBody());
    //   System.out.println(httpExchange.getRequestURI());
        //Code for what happens at the server, here we simply print
      //the request message
    //   InputStream inStream = httpExchange.getRequestBody();
    //   Scanner scanner = new Scanner(inStream);
    //   String data = scanner.nextLine();
    //   System.out.println(data);
    //   scanner.close();
   }
}
