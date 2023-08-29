package projects.Medium.MusicAdvisor.Stage04;

import java.io.IOException;
import java.util.Scanner;

import projects.Medium.MusicAdvisor.Stage04.app.LocalClient;
import projects.Medium.MusicAdvisor.Stage04.auth.Authorization;

public class Application {

    private static String access;
    private static String resource;
    private static String accesToken;

    private Application(String access, String resource) {
        this.access = access;
        this.resource = resource;
    }

    public static Application getApplication(String access, String resource) {
        return new Application(access, resource);
    }

    public void start() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = false;
        boolean isSigned = false;
        while (isRunning) {
            String userOption = sc.nextLine();
            if (!userOption.equals("auth") & isSigned == false) {
                System.out.println("Please, provide access for application.");
                continue;
            }
            LocalClient lc = LocalClient.getLocalClient();
            String[] arr = userOption.trim().split("\\s+");
            switch (arr[0]) {
                case "featured":
                    break;
                case "new":
                    break;
                case "categories":
                    break;
                case "playlists":
                    break;
                case "auth":
                    Authorization a = Authorization.getAuthorization(lc);
                    a.start();
                    lc.setCode(a.getCode());
                    lc.setAccess(access);
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
