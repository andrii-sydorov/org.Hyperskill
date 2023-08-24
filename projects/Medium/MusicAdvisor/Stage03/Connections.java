package projects.Medium.MusicAdvisor.Stage03;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Connections {

    private static String clientID = "ebaccb57b2754da89ac2a9aef07c78fa";
    private static String redirectedURI = "http://localhost:8080";
    private static String responseType = "code";
    private static String uriAuthorize = "https://accounts.spotify.com/authorize?";

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        LocalServer ls = LocalServer.getLocalServer();
        ls.startServer();
        String request = createCodeRequest();
        System.out.println(request);
        while (ls.getCode() == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                //ie.printStackTrace();
            }
        }
        ls.stopServer(1);
        LocalClient lc = LocalClient.getLocalClient();
        lc.setCode(ls.getCode());
        lc.postRequest();
    }

    private static String createCodeRequest() {
        String data = String.format("client_id=%s&response_type=%s&redirect_uri=%s", clientID,
                responseType, redirectedURI);
        String address = uriAuthorize + data;
        return address;
    }
    
   
}
