package HttpClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {

        // Get request
        HttpClient client = HttpClient.newHttpClient();
        URI firstWebSiteAddress = URI.create("http://info.cern.ch/hypertext/WWW/TheProject.html");
        HttpRequest getRequest = HttpRequest.newBuilder().uri(firstWebSiteAddress).GET().build();

        try {
            HttpResponse<String> response = client.send(
                    getRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
        } catch (Exception ex) {
            System.out.println("We can't access the site. Please, try later");
        }

        // Post request

        URI fakePostService = URI.create("https://jsonplaceholder.typicode.com/posts");
        String bookData = "{\"title\":\"The Invisible Man\", \"author\":\"H. G. Wells\"}";
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(fakePostService)
                .header("Content-Type", "application/json") // we specify that we send a JSON
                .POST(HttpRequest.BodyPublishers.ofString(bookData))
                .build();
        
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode()); // 201 if everything is OK
            System.out.println(response.body());       // a JSON response with ID
        } catch (Exception e) {
            System.out.println("We cannot send data. Please, try later.");
        }
    }
}
