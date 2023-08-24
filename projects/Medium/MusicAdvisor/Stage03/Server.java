package projects.Medium.MusicAdvisor.Stage03;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO Auto-generated method stub
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(8080), 0);
        server.createContext("/", new HttpHandler() {

            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String hello = "hello world";
                exchange.sendResponseHeaders(200, hello.length());
                exchange.getResponseBody().write(hello.getBytes());
                exchange.getResponseBody().close();
            }
        });
        server.createContext("/test", new HttpHandler() {

            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String hello = "hello world from test page";
                exchange.sendResponseHeaders(200, hello.length());
                exchange.getResponseBody().write(hello.getBytes());
                exchange.getResponseBody().close();
            }
        });
        server.start();

        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create("http://localhost:8080");
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        System.out.println(response.statusCode());
        
        uri = URI.create("http://localhost:8080/test");
        request = HttpRequest.newBuilder().uri(uri).GET().build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        System.out.println(response.statusCode());
        server.stop(1);
    }

}
