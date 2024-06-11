/** Project: LAb3
 * Purpose Details: To receive JSON
 * Course: IST 242
 * Author: Kadin
 * Date Developed: 6/8
 * Last Date Changed: 6/9
 * Rev:

 */

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class GameJSONB {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/game", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started on port 8080");
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            InputStream is = exchange.getRequestBody();
            String json = new String(is.readAllBytes());

            ObjectMapper objectMapper = new ObjectMapper();
            Game game = objectMapper.readValue(json, Game.class);

            String response = "Received game object: " + game;
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
            System.out.println(response);
        }
    }
}



