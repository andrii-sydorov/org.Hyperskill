package projects.Medium.MusicAdvisor.Stage02;

import java.util.Scanner;

/**
 * Stage 2/5: Rocking OAuth
 * Description
 * You have written a simulation of the final application, and now we will make
 * it fully functional, step by step.
 * 
 * Working with OAuth
 * OAuth means a protocol for authorization. When you are visiting a website
 * that allows you to register, you often have the option of signing in with
 * Google, Twitter, Facebook, or another service. All these services use OAuth
 * to provide user information to third-party applications.
 * 
 * The main entity of OAuth is access_token, the secret code that should be sent
 * with an HTTP request to API, so that the service is sure that you have enough
 * rights to get information from API. So, before an application can receive
 * access_token, the user should confirm access to that application.
 * In our case, using the Spotify API, it will look like this:
 * 
 * https://accounts.spotify.com/authorize?client_id=YOURCLIENT&redirect_uri=https://www.example.com&response_type=code
 * The user follows this link and confirms access.
 * Then the user is redirected to redirect_uri with the response in the URL
 * (http://www.example.com?code=7angkqw2DAsdfkQ). (Your own server waiting for
 * code.)
 * You use this code to get access_token and make requests to an API.
 * You can find more information in the authorization guide (look at
 * Authorization Code Flow section).
 * 
 * Objectives
 * So, in this stage, you must go to the Spotify Web site for developers and
 * create your application.
 * To create an application, you should select Dashboard tab on the site, log in
 * to Spotify, and click the button Create an App.
 * 
 * Add one more command to your program: an auth command that will print the
 * auth link and allow us to use another command (no real authorization
 * required, just print a workable link and make some Boolean field true). Don't
 * forget to put your client id in this link.
 * 
 * Make commands unavailable if user access for your program is not confirmed
 * (if they did not call the auth command).
 * 
 * Remember that you should add your redirect_uri in the settings of your
 * application.
 * 
 * Example
 * Below is an output example of the described program. Try to output all cases
 * like in the example.
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * > new
 * Please, provide access for application.
 * > featured
 * Please, provide access for application.
 * > auth
 * https://accounts.spotify.com/authorize?client_id=a19ee7dbfda443b2a8150c9101bfd645&redirect_uri=http://localhost:8080&response_type=code
 * ---SUCCESS---
 * > new
 * ---NEW RELEASES---
 * Mountains [Sia, Diplo, Labrinth]
 * Runaway [Lil Peep]
 * The Greatest Show [Panic! At The Disco]
 * All Out Life [Slipknot]
 * > exit
 * ---GOODBYE!---
 */

public class RockOAuth {

    private static String clientID = "ebaccb57b2754da89ac2a9aef07c78fa";
    private static String clientSecret = "9e4c25b6dd9b4d06b3f6a993e779300b";
    private static String redirectURL = "https://hyperskill.org/study-plan";
    private static boolean access;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            String input = sc.nextLine();
            switch (input) {
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
                    printAuth();
                    break;
                case "exit":
                    printExit();
                    isRunning = false;
                    break;

            }
        }
        sc.close();
    }

    public static void printAccess() {
        System.out.println("Please, provide access for application.");
    }

    public static void printAuth() {
        String s = String.format(
                "https://accounts.spotify.com/authorize?client_id=%s&redirect_uri=%s&response_type=code",
                clientID, redirectURL);
        System.out.println(s);
        access = true;
        System.out.println("---SUCCESS---");
    }

    public static void printFeature() {
        if (!access) {
            printAccess();
            return;
        }
        String s = """
                ---FEATURED---
                Mellow Morning
                Wake Up and Smell the Coffee
                Monday Motivation
                Songs to Sing in the Shower""";
        System.out.println(s);
    }

    public static void printNew() {
        if (!access) {
            printAccess();
            return;
        }
        String s = """
                ---NEW RELEASES---
                Mountains [Sia, Diplo, Labrinth]
                Runaway [Lil Peep]
                The Greatest Show [Panic! At The Disco]
                All Out Life [Slipknot]""";
        System.out.println(s);
    }

    public static void printCategories() {
        if (!access) {
            printAccess();
            return;
        }
        String s = """
                ---CATEGORIES---
                Top Lists
                Pop
                Mood
                Latin""";
        System.out.println(s);
    }

    public static void printPlaylists() {
        if (!access) {
            printAccess();
            return;
        }
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

}
