package projects.Medium.MusicAdvisor.Stage04.playlist;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import projects.Medium.MusicAdvisor.Stage04.category.Category;

public class Playlist {

    HttpClient cl;
    private String accessToken;
    private String resource;
    private String id;
    private String categoryName;
    private static Map<String, String> categories;

    private Playlist(HttpClient cl, String resource, String accessToken, Map<String, String> categories,
            String categoryName) {
        this.cl = cl;
        this.resource = resource;
        this.accessToken = accessToken;
        if (Playlist.categories == null) {
            if (categories != null) {
                Playlist.categories = categories;
            } else {
                Category c = Category.getCategory(cl, resource, accessToken);
                c.start();
                Playlist.categories = c.getMap();
            }
        }
        this.categoryName = categoryName;
    }

    public static Playlist getPlaylist(HttpClient cl, String resource, String accessToken,
            Map<String, String> categories, String categoryName) {
        return new Playlist(cl, resource, accessToken, categories, categoryName);
    }

    public void start() {
        // https://api.spotify.com/v1/browse/categories/{category_id}/playlists
        id = categories.get(categoryName);
        URI uri = URI.create(String.format(resource + "/v1/browse/categories/%s/playlists", id));
        HttpRequest request = HttpRequest.newBuilder().uri(uri).header("Authorization", "Bearer " + accessToken).GET()
                .build();
        try {
            HttpResponse<String> response = cl.send(request, HttpResponse.BodyHandlers.ofString());
            // System.out.println(response.body());
            printPlaylist(response);
        } catch (InterruptedException | IOException ie) {
            ie.printStackTrace();
        }

    }

    public void printPlaylist(HttpResponse<String> response) {
        String data = response.body();
        JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
        if (jsonObject.toString().contains("error")) {
            JsonObject error = jsonObject.getAsJsonObject("error");
            String message = error.get("message").getAsString();
            System.out.println(message);
        } else {
            JsonObject playlists = jsonObject.getAsJsonObject("playlists");
            for (JsonElement e : playlists.getAsJsonArray("items")) {
                JsonObject t = e.getAsJsonObject();
                System.out.println(t.get("name").getAsString());
                JsonObject externalURLs = t.getAsJsonObject("external_urls");
                System.out.println(externalURLs.get("spotify").getAsString());
                System.out.println();
            }
        }
    }
}
