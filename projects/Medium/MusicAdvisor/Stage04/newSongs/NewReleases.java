package projects.Medium.MusicAdvisor.Stage04.newSongs;

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

public class NewReleases {

    HttpClient cl;
    String resource;
    String accessToken;

    private NewReleases(HttpClient cl, String resource, String accessToken) {
        this.cl = cl;
        this.resource = resource;
        this.accessToken = accessToken;
    }

    public static NewReleases getNewReleases(HttpClient cl, String resource, String accessToken) {
        return new NewReleases(cl, resource, accessToken);
    }

    public void start() {
        // https://api.spotify.com/v1/browse/new-releases
        URI uri = URI.create(resource + "/v1/browse/new-releases");
        HttpRequest request = HttpRequest.newBuilder().uri(uri).header("Authorization", "Bearer " + accessToken)
                .GET().build();
        try {
            HttpResponse<String> response = cl.send(request, HttpResponse.BodyHandlers.ofString());
            //System.out.println("response status code: " + response.statusCode());
            parseJSon(response.body());
        } catch (InterruptedException | IOException i) {
            i.printStackTrace();
        }
    }
    
    public void parseJSon(String response) {
//        I Used To Know Her - Part 2 - EP                        - albums name
//        [H.E.R.]                                                - singer name
//        https://open.spotify.com/album/46imFLgb9fR1Io6EoPYeQh   - links
        
        // create parser
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        // getting object "albums"
        JsonObject albums = jsonObject.getAsJsonObject("albums");
        //getting array of items
        List<JsonObject> ls = new ArrayList<>();
        for (JsonElement j : albums.getAsJsonArray("items")) {
            ls.add(j.getAsJsonObject());
        }
        
        for (JsonObject obj : ls) {
            // getting the album name
            String albumName = obj.get("name").getAsString();
            //getting the utl from external_urls
            JsonObject externalURL = obj.get("external_urls").getAsJsonObject();
            String url = externalURL.get("spotify").getAsString();
            // getting the array of artists
            List<JsonObject> artist = new ArrayList<>();
            for (JsonElement j : obj.getAsJsonArray("artists")) {
                artist.add(j.getAsJsonObject());
            }
            List<String> artistsName = new ArrayList<>();
            for (JsonObject jsobj : artist) {
                artistsName.add(jsobj.get("name").getAsString());
            }
            System.out.println(albumName);
            System.out.println(artistsName);
            System.out.println(url);
            System.out.println();
        }
    }

}
