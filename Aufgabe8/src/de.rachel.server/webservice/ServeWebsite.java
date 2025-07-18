package webservice;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.*;
import parserlib.ParseCsv;

public class ServeWebsite {
    ServeWebsite() {

    }

    public static void main(String[] args) {

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            HttpContext context = server.createContext("/");
            context.setHandler(new MyHttpHandler());
            server.start();
            System.out.println(" Server started on port 8001");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
