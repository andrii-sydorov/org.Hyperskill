package projects.Medium.MusicAdvisor.Stage04.category;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Category {

    HttpClient cl;
    private String resource;
    private String accessToken;
    private List<String> ls = new ArrayList<>();

    public List<String> getLs() {
        return this.ls;
    }

    private Category(HttpClient cl, String resource, String accessToken) {
        this.cl = cl;
        this.resource = resource;
        this.accessToken = accessToken;
    }

    public static Category getCategory(HttpClient cl, String resource, String accessToken) {
        return new Category(cl, resource, accessToken);
    }

    public void start() {
        // https://api.spotify.com/v1/browse/categories
        URI uri = URI.create(resource + "/v1/browse/categories");
        HttpRequest request = HttpRequest.newBuilder().uri(uri).header("Authorization", "Bearer " + accessToken).GET()
                .build();

        try {
            HttpResponse<String> response = cl.send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println(response.statusCode());
            parseJSon(response.body());
        } catch (InterruptedException | IOException i) {
            i.printStackTrace();
        }
    }

    public void parseJSon(String response) {
        // create parser
        JsonObject jObject = JsonParser.parseString(response).getAsJsonObject();
        // getting object categories
        JsonObject categories = jObject.get("categories").getAsJsonObject();
        // getting array
        List<JsonObject> items = new ArrayList<>();
        for (JsonElement je : categories.getAsJsonArray("items")) {
            items.add(je.getAsJsonObject());
        }
        for (JsonObject jo : items) {
            ls.add(jo.get("name").getAsString());
        }
    }
    
    public void printCategories() {
        ls.forEach(x -> System.out.println(x));
    }
}
