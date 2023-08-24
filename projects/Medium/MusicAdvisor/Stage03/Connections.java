package projects.Medium.MusicAdvisor.Stage03;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Connections {

    private static String clientID = "ebaccb57b2754da89ac2a9aef07c78fa";
    private static String redirectedURI = "http://localhost:8080";
    private static String responseType = "code";
    private static String uriAuthorize = "https://accounts.spotify.com/authorize?";
    private static Scanner sc;
    private static String access;

    static {
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            map.put(args[i], args[i + 1]);
        }
        access = map.get("-access") == null ? "https://accounts.spotify.com" : map.get("-access");
        boolean isRunning = true;
        boolean isSigned = false;
        while (isRunning) {
            String userOption = sc.nextLine();
            if (!userOption.equals("auth") & isSigned == false) {
                System.out.println("Please, provide access for application.");
                continue;
            }
            switch (userOption) {
                case "featured":
                    printFeature();
                    break;
                case "new":
                    printNew();
                    break;
                case "categories":
                    printCategories();
                    break;
                case "playlists Mood":
                    printPlaylists();
                    break;
                case "auth":
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
                    ls.stopServer(1);
                    if (ls.getCode() != null) {
                        System.out.println("code received");
                    } else {
                        System.out.println("Timeout error\n");
                        break;
                    }
                    LocalClient lc = LocalClient.getLocalClient();
                    System.out.println("making http request for access_token...");
                    lc.setCode(ls.getCode());
                    lc.setAccess(access);
                    lc.postRequest();
                    System.out.println("response:\n" + lc.getAccessToken());
                    System.out.println("---SUCCESS---");
                    isSigned = true;
                    break;
                case "exit":
                    printExit();
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid input command. Try again!");

            }
        }
        sc.close();
    }

    private static void printFeature() {
        String s = """
                ---FEATURED---
                Mellow Morning
                Wake Up and Smell the Coffee
                Monday Motivation
                Songs to Sing in the Shower""";
        System.out.println(s);
    }

    private static void printNew() {
        String s = """
                ---NEW RELEASES---
                Mountains [Sia, Diplo, Labrinth]
                Runaway [Lil Peep]
                The Greatest Show [Panic! At The Disco]
                All Out Life [Slipknot]""";
        System.out.println(s);
    }

    private static void printCategories() {
        String s = """
                ---CATEGORIES---
                Top Lists
                Pop
                Mood
                Latin""";
        System.out.println(s);
    }

    private static void printPlaylists() {
        String s = """
                ---MOOD PLAYLISTS---
                Walk Like A Badass
                Rage Beats
                Arab Mood Booster
                Sunday Stroll""";
        System.out.println(s);
    }

    public static void printExit() {
        String s = """
                ---GOODBYE!---
                """;
        System.out.println(s);
    }

    private static String createCodeRequest() {
        String data = String.format("client_id=%s&response_type=%s&redirect_uri=%s", clientID,
                responseType, redirectedURI);
        String address = uriAuthorize + data;
        return address;
    }

}
