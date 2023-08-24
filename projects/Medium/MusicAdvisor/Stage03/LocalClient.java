package projects.Medium.MusicAdvisor.Stage03;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class LocalClient {

    private HttpClient client;
    private static String clientID = "ebaccb57b2754da89ac2a9aef07c78fa";
    private static String clientSecret = "9e4c25b6dd9b4d06b3f6a993e779300b";
    private String redirectedURI = "http://localhost:8080";
    private String grantType = "authorization_code";
    private String accessToken;
    private URI uri;
    private String code;
    private String access;

    private LocalClient() {
        client = HttpClient.newHttpClient();
    }

    public static LocalClient getLocalClient() {
        return new LocalClient();
    }

    public void postRequest() {
        uri = URI.create(access + "/api/token");
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
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.statusCode());
//            System.out.println(response.uri());
//            System.out.println(response.body());
            accessToken = response.body();
        } catch (InterruptedException | IOException ie) {
            ie.printStackTrace();

        }
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
