package projects.Medium.MusicAdvisor.Stage02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Test {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        LocalServer ls = LocalServer.getLocalServer();
        ls.startServer();
        LocalClient lc = LocalClient.getLocalClient();
        lc.getRequest();
        while(ls.getCode() == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        ls.stopServer(1);
    }
}

class LocalClient {

    private HttpClient client;
    private static String clientID = "ebaccb57b2754da89ac2a9aef07c78fa";
    private static String clientSecret = "9e4c25b6dd9b4d06b3f6a993e779300b";
    private String redirectedURI = "http://localhost:8080";
    private String responseType = "code";
    private String accessToken;
    private URI uri;

    private LocalClient() {
        client = HttpClient.newHttpClient();
    }

    public static LocalClient getLocalClient() {
        return new LocalClient();
    }

    public void postRequest() {
        uri = URI.create("https://accounts.spotify.com/api/token");
        String data = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s", clientID,
                clientSecret);
        HttpRequest request = HttpRequest.newBuilder().uri(uri)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(data)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.uri());
            System.out.println(response.body());
            accessToken = response.body();
        } catch (InterruptedException | IOException ie) {
            ie.printStackTrace();

        }
    }

    public void getRequest() {
        uri = URI.create("https://accounts.spotify.com/authorize?");
        String data = String.format("client_id=%s&redirect_uri=%s&response_type=%s", clientID, redirectedURI,
                responseType);
        String address = uri + data;
        System.out.println(address);
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (InterruptedException | IOException ioe) {
            System.out.println(ioe);
        }
    }

    public String getAccessToken() {
        return this.accessToken;
    }
}

class LocalServer {

    private HttpServer server;
    private String outputMessage;
    private String code;

    public static LocalServer getLocalServer() throws IOException {
        return new LocalServer();
    }

    private LocalServer() throws IOException {
        server = HttpServer.create();
        server.bind(new InetSocketAddress(8080), 0);
        defineContext(server);
    }

    private void defineContext(HttpServer server) {
        server.createContext("/", new HttpHandler() {

            @Override
            public void handle(HttpExchange exchange) throws IOException {
                // TODO Auto-generated method stub
                String query = exchange.getRequestURI().getQuery();
                System.out.println(query);
                if (query == null & !query.contains("code")) {
                    outputMessage = "Authorization code not found. Try again.";
                } else {
                    outputMessage = "Got the code. Return back to your program.";
                    code = query.replace("code=", "");
                }
                System.out.println(outputMessage);
                exchange.sendResponseHeaders(200, outputMessage.length());
                exchange.getResponseBody().write(outputMessage.getBytes());
                exchange.getResponseBody().close();
            }
        });
    }

    public String getCode() {
        return this.code;
    }

    public void startServer() {
        server.start();
    }

    public void stopServer(int delay) {
        server.stop(delay);
    }
}