package projects.Medium.MusicAdvisor.Stage04;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Map;
import java.util.Scanner;

import projects.Medium.MusicAdvisor.Stage04.auth.Authorization;
import projects.Medium.MusicAdvisor.Stage04.category.Category;
import projects.Medium.MusicAdvisor.Stage04.feature.Feature;
import projects.Medium.MusicAdvisor.Stage04.newSongs.NewReleases;
import projects.Medium.MusicAdvisor.Stage04.playlist.Playlist;

public class Application {

    private static String access;
    private static String resource;
    private static String accesToken;
    private static Map<String, String> categories;

    private Application(String access, String resource) {
        this.access = access;
        this.resource = resource;
    }

    public static Application getApplication(String access, String resource) {
        return new Application(access, resource);
    }

    public void start() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        boolean isSigned = false;
        while (isRunning) {
            String userOption = sc.nextLine();
            if (!userOption.equals("auth") & isSigned == false) {
                System.out.println("Please, provide access for application.");
                continue;
            }
            HttpClient lc = HttpClient.newHttpClient();
            String[] arr = userOption.trim().split("\\s+");
            switch (arr[0]) {
                case "featured":
                    Feature f = Feature.getFeature(lc, resource, accesToken);
                    f.start();
                    f.printFeature();
                    break;
                case "new":
                    NewReleases ns = NewReleases.getNewReleases(lc, resource, accesToken);
                    ns.start();
                    break;
                case "categories":
                    Category c = Category.getCategory(lc, resource, accesToken);
                    c.start();
                    c.printCategories();
                    categories = c.getMap();
                    break;
                case "playlists":
                    Playlist pl = Playlist.getPlaylist(lc, resource, accesToken, categories, arr[1]);
                    pl.start();
                    break;
                case "auth":
                    if (isSigned) {
                        continue;
                    }
                    Authorization a = Authorization.getAuthorization(lc, access);
                    a.start();
                    a.postRequest();
                    accesToken = a.getAccessToken();
                    isSigned = true;
                    break;
                case "exit":
                    System.out.println("---GOODBYE!---");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid input command. Try again!");
            }
        }
        sc.close();
    }
}
