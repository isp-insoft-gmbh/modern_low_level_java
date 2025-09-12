package webservice;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.*;

public class ServeWebsite {
    ServeWebsite() {}

    public static void main(String[] args) {

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            HttpContext context = server.createContext("/");
            HttpContext cssContext = server.createContext("/style.css");
            context.setHandler(new MyHttpHandler());
            cssContext.setHandler(new CssHttpHandler());
            // start an deamon process
            server.start();
            System.out.println("Server started on port 8001");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
