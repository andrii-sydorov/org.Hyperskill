package HttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

public class SpotifyAPI {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Client cl = new Client();
        cl.postRequest();
    }

}

class Client {

    private HttpClient client;
    private URI uri;

    public Client() {
        this.client = HttpClient.newHttpClient();
    }

    public void postRequest() {
        uri = URI.create("https://accounts.spotify.com/api/token");
        String clientId = "ebaccb57b2754da89ac2a9aef07c78fa";
        String clientSecret = "9e4c25b6dd9b4d06b3f6a993e779300b";
        String data = String.format("grant_type=client_credentials&client_id=%s&client_secret=%s", clientId,
                clientSecret);
        HttpRequest requset = HttpRequest.newBuilder().uri(uri)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(data)).build();

        try {
            HttpResponse<String> response = client.send(requset, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.uri());
            System.out.println(response.body());
            ObjectMapper obj = new ObjectMapper();
            Pojo p = obj.readValue(response.body(), Pojo.class);
            System.out.println(p);
        } catch (InterruptedException | IOException ioe) {
            ioe.printStackTrace();
        }

    }

}

class Pojo {

    private String acess_token;
    private String token_type;
    private int expires_in;

    public Pojo() {
    }

    public void setAcess_token(String acess_token) {
        this.acess_token = acess_token;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getAcess_token() {
        return acess_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

}
