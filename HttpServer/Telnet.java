package HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Telnet {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Server server = new Server();
        server.root();
        server.sort();
        Client client = new Client();
        client.getRequest();
        client.postRequest();

        server.stop(10);
    }

}

class Server {

    private HttpServer server;

    public Server() {
        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);
            server.start();
        } catch (IOException ioe) {
            System.out.println("Server was not created!");
        }
    }

    public void root() {
        server.createContext("/", new HttpHandler() {

            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String hello = "hello world from test page";
                exchange.sendResponseHeaders(200, hello.length());
                exchange.getResponseBody().write(hello.getBytes());
                exchange.getResponseBody().close();
            }
        });
    }

    public void sort() {
        server.createContext("/sort", new HttpHandler() {

            @Override
            public void handle(HttpExchange exchange) throws IOException {
                StringBuilder sb = new StringBuilder();
                InputStream is = exchange.getRequestBody();
                Scanner sc = new Scanner(is);
                while (sc.hasNext()) {
                    sb.append(sc.nextLine());
                }
                sc.close();
                System.out.println("Data received by server " + sb); // getting the input data;
                int[] ar = Arrays.stream(sb.toString().split("\\s+")).mapToInt(Integer::valueOf).toArray();
                bubbleSort(ar);
                String data = Arrays.toString(ar);
                //working with method
                String methodName = exchange.getRequestMethod();
                System.out.println("HttpMethod " + methodName);
                // working with headers
                Headers headers = exchange.getRequestHeaders();
                for(Map.Entry<String, List<String>> entr : headers.entrySet()) {
                    System.out.println(entr.getKey() + entr.getValue());
                }
               
                exchange.sendResponseHeaders(200, data.length());
                exchange.getResponseBody().write(data.getBytes());
                exchange.getResponseBody().close();
            }
        });
    }

    public void bubbleSort(int[] ar) {
        int turn = 0;
        boolean isSorted = false;
        while (!isSorted) {
            for (int i = 0; i < ar.length - 1 - turn; i++) {
                isSorted = true;
                if (ar[i] > ar[i + 1]) {
                    int temp = ar[i];
                    ar[i] = ar[i + 1];
                    ar[i + 1] = temp;
                    isSorted = false;
                }
            }
            turn++;
        }
    }

    public void stop(int n) {
        server.stop(n);
    }
}

class Client {

    private HttpClient client;
    private URI uri;

    public Client() {
        this.client = HttpClient.newHttpClient();
    }

    public void getRequest() {
        uri = URI.create("http://localhost:8080/");
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
        } catch (InterruptedException | IOException ioe) {
            System.out.println("Client was not created!");
        }
    }

    public void postRequest() {
        uri = URI.create("http://localhost:8080/sort");
        String data = "1 2 3 6 7 21 32";
        HttpRequest request = HttpRequest.newBuilder().uri(uri)
                .header("Content-Type", "text/plain")
                .POST(HttpRequest.BodyPublishers.ofString(data)).build();
        

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("The main data from client: \n");
            System.out.println("URI request from cleint " + request.uri());
            System.out.println("Headars from client " + request.headers());
            System.out.println("Data received client!");
            System.out.println(response.statusCode());
            System.out.println(response.body());
        } catch (InterruptedException | IOException ioe) {
            System.out.println("Problem with sorting");
            ioe.printStackTrace();
        }
    }
}
