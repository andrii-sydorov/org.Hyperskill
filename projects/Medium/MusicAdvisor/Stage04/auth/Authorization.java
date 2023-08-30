package projects.Medium.MusicAdvisor.Stage04.auth;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import projects.Medium.MusicAdvisor.Stage04.app.LocalServer;

public class Authorization {

    HttpClient lc;
    private String code;
    private static String clientID = "ebaccb57b2754da89ac2a9aef07c78fa";
    private static String clientSecret = "9e4c25b6dd9b4d06b3f6a993e779300b";
    private static String redirectedURI = "http://localhost:8080";
    private static String responseType = "code";
    private static String uriAuthorize = "https://accounts.spotify.com/authorize?";
    private String grantType = "authorization_code";
    private String access;
    private String accessToken;

    private Authorization(HttpClient lc, String access) {
        this.lc = lc;
        this.access = access;
    }

    public static Authorization getAuthorization(HttpClient lc, String access) {
        return new Authorization(lc, access);
    }

    public String getCode() {
        return code;
    }

    public String getAccessToken() {
        return this.accessToken;
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

    public void postRequest() {
        URI uri = URI.create(access + "/api/token");
        String data = String.format("grant_type=%s&code=%s&redirect_uri=%s", grantType,
                code, redirectedURI);
        // System.out.println(data);
        String originalInput = String.format("%s:%s", clientID, clientSecret);
        // System.out.println("original data " + originalInput);
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        // System.out.println("encoded data " + encodedString);
        HttpRequest request = HttpRequest.newBuilder().uri(uri)
                .header("Authorization", String.format("Basic %s", encodedString))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(data)).build();

        try {
            HttpResponse<String> response = lc.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.statusCode());
//            System.out.println(response.uri());
//            System.out.println(response.body());
            String spotifyResponse = response.body();
            System.out.println(spotifyResponse);
            parseAccessToken(spotifyResponse);
        } catch (InterruptedException | IOException ie) {
            ie.printStackTrace();

        }
    }

    public void parseAccessToken(String spotifyResponse) {
        // TODO parse data to get access token
//        JsonObject jo = JsonParser.parseString(spotifyResponse).getAsJsonObject();
//        accessToken = jo.get("access_token").getAsString();
        Map<String, String> map = new HashMap<>();
        String[] arr = spotifyResponse.replaceAll("\"","").replace("{", "").replace("}", "").split(",");
        for (String s : arr) {
            String[] t = s.split(":");
            map.put(t[0], t[1]);
        }
        accessToken = map.get("access_token");
    }
}
