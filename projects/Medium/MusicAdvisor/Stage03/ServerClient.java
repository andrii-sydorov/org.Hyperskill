package projects.Medium.MusicAdvisor.Stage03;

import static java.lang.Thread.sleep;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class ServerClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        // TODO Auto-generated method stub
        ServerRedirect sd = ServerRedirect.create();
        sd.startServer();
        waitingForCode(sd);
        sd.stopServer(1);
        System.out.println(sd.getCode());
    }

    private static void waitingForCode(ServerRedirect server) {
        int numberOfAttempts = 10;
        while (server.getCode() == null && numberOfAttempts > 0) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                assert false : "This will never happen";
            }
            numberOfAttempts--;
        }
        if (server.getCode() == null) {
            System.out.println("Error!");
        }
    }
}

class Client {

    HttpClient client;
    URI uri;
    private String clientId = "ebaccb57b2754da89ac2a9aef07c78fa";
    private String clientSecret = "9e4c25b6dd9b4d06b3f6a993e779300b";
    private String redirectedURI = "http://localhost:8080";
    private String responseType = "code";

    public Client() {
        this.client = HttpClient.newHttpClient();
    }

    public void getRequest() {
        uri = URI.create(
                String.format("https://accounts.spotify.com/authorize?client_id=%s&redirect_uri=%s&response_type=%s",
                        clientId, responseType, redirectedURI));
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (InterruptedException | IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void postRequest() {
        uri = URI.create("https://accounts.spotify.com/api/token");
        String data = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s", clientId,
                clientSecret);
        HttpRequest request = HttpRequest.newBuilder().uri(uri)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(data)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.uri());
            System.out.println(response.body());
        } catch (InterruptedException | IOException ie) {
            ie.printStackTrace();
        }
    }

}

class ServerRedirect {

    private final static String SUCCESS = "Got the code. Return back to your program.";
    private final static String FAILED = "Authorization code not found. Try again.";

    private final HttpServer server;
    private String code;

    private ServerRedirect() throws IOException {
        server = HttpServer.create();
        server.bind(new InetSocketAddress(8080), 0);
        defineContext(server);
    }

    public static ServerRedirect create() throws IOException {
        return new ServerRedirect();
    }

    private void defineContext(HttpServer server) {
        server.createContext("/", rootHandler -> {
            String query = rootHandler.getRequestURI().getQuery();

            String response;
            if (query != null && query.contains("code")) {
                code = query.replace("code=", "");
                response = SUCCESS;
            } else {
                response = FAILED;
            }

            rootHandler.sendResponseHeaders(200, response.length());
            rootHandler.getResponseBody().write(response.getBytes());
            rootHandler.getResponseBody().close();
            System.out.println(response);
        });
        server.setExecutor(null);
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
