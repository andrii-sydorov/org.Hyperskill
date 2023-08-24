package projects.Medium.MusicAdvisor.Stage03;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class LocalServer {

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
                if (query != null & query.contains("code")) {
                    outputMessage = "Got the code. Return back to your program.";
                    code = query.replace("code=", "");
                } else {
                    outputMessage = "Authorization code not found. Try again.";
                }
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
