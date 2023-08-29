package projects.Medium.MusicAdvisor.Stage04.auth;

import java.io.IOException;

import projects.Medium.MusicAdvisor.Stage04.app.LocalClient;
import projects.Medium.MusicAdvisor.Stage04.app.LocalServer;

public class Authorization {

    LocalClient lc;
    private String code;
    private static String clientID = "ebaccb57b2754da89ac2a9aef07c78fa";
    private static String clientSecret = "9e4c25b6dd9b4d06b3f6a993e779300b";
    private static String redirectedURI = "http://localhost:8080";
    private static String responseType = "code";
    private static String uriAuthorize = "https://accounts.spotify.com/authorize?";

    private Authorization(LocalClient lc) {
        this.lc = lc;
    }

    public static Authorization getAuthorization(LocalClient lc) {
        return new Authorization(lc);
    }

    public String getCode() {
        return code;
    }

    public void start() throws IOException {
        LocalServer ls = LocalServer.getLocalServer();
        ls.startServer();
        System.out.println("use this link to request the access code:");
        System.out.println(createCodeRequest());
        System.out.println("waiting for code...");
        while (ls.getCode() == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                System.out.println("Error by thread sleeping");
            }
        }
        code = ls.getCode();
        ls.stopServer(1);
    }

    private static String createCodeRequest() {
        String data = String.format("client_id=%s&response_type=%s&redirect_uri=%s", clientID,
                responseType, redirectedURI);
        String address = uriAuthorize + data;
        return address;
    }
}
