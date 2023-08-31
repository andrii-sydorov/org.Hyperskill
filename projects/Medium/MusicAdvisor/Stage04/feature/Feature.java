package projects.Medium.MusicAdvisor.Stage04.feature;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Feature {

    HttpClient cl;
    private String resource;
    private String accessToken;
    Map<String, String> map = new LinkedHashMap<>();

    private Feature(HttpClient cl, String resource, String accessToken) {
        this.cl = cl;
        this.resource = resource;
        this.accessToken = accessToken;
    }

    public static Feature getFeature(HttpClient cl, String resource, String accessToken) {
        return new Feature(cl, resource, accessToken);
    }

    public void start() {
        // https://api.spotify.com/v1/browse/featured-playlists
        URI uri = URI.create(resource + "/v1/browse/featured-playlists");
        HttpRequest request = HttpRequest.newBuilder().uri(uri).header("Authorization", "Bearer " + accessToken)
                .GET().build();
        try {
            HttpResponse<String> response = cl.send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println(response.statusCode());
            // System.out.println(response.body());
            parseJSon(response.body());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }

    public void parseJSon(String response) {
        // Music for a Workday - description
        // https://open.spotify.com/playlist/37i9dQZF1DXcsT4WKI8W8r - extrenal-urls

        // getting full respond as json
        JsonObject jo = JsonParser.parseString(response).getAsJsonObject();
        // getting playlist object from json
        JsonObject addressObj = jo.getAsJsonObject("playlists");
        // getting array objects field "item"
        List<JsonObject> items = new ArrayList<>();
        for (JsonElement hobby : addressObj.getAsJsonArray("items")) {
            items.add(hobby.getAsJsonObject());
        }
        // getting "description" and "spotify" from array of objects
        for (JsonObject t : items) {
            String key = t.get("description").getAsString();
            JsonObject extrenalURLs = t.getAsJsonObject("external_urls");
            String value = extrenalURLs.get("spotify").getAsString();
            map.put(key, value);
        }
    }

    public void printFeature() {
        map.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
            System.out.println();
        });
    }
}
