package projects.Medium.MusicAdvisor.Stage01;

import java.util.Scanner;

/**
 * Stage 1/5: Strings and fiddlesticks
 * Description
 * When developing your web applications, it can be useful to implement the
 * ability to register users using third-party services (such as Twitter,
 * Facebook, Google, etc.), as well as the ability to interact with these
 * services (for example, use Twitter to publish a post, or getting a list of
 * friends on Facebook). To implement this functionality, you need to learn how
 * to work with the service's API. Most services use REST (Representational
 * State Transfer) to provide the ability to interact with the service. To learn
 * how to work with it from Java, we will develop a simple application that will
 * offer us music using Spotify.
 * 
 * First, in this stage, you have to implement simple functionality to read user
 * input and provide information at the user's request.
 * 
 * featured — a list of Spotify-featured playlists with their links fetched from
 * API;
 * new — a list of new albums with artists and links on Spotify;
 * categories — a list of all available categories on Spotify (just their
 * names);
 * playlists C_NAME, where C_NAME is the name of category. The list contains
 * playlists of this category and their links on Spotify;
 * exit shuts down the application.
 * Notice that here, you don’t need to implement interaction with the API (you
 * will do that later), just replace the API responses with simple strings.
 * 
 * Example
 * Below is an output example of the described program. Try to output all cases
 * like in the example.
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * > new
 * ---NEW RELEASES---
 * Mountains [Sia, Diplo, Labrinth]
 * Runaway [Lil Peep]
 * The Greatest Show [Panic! At The Disco]
 * All Out Life [Slipknot]
 * > featured
 * ---FEATURED---
 * Mellow Morning
 * Wake Up and Smell the Coffee
 * Monday Motivation
 * Songs to Sing in the Shower
 * > categories
 * ---CATEGORIES---
 * Top Lists
 * Pop
 * Mood
 * Latin
 * > playlists Mood
 * ---MOOD PLAYLISTS---
 * Walk Like A Badass
 * Rage Beats
 * Arab Mood Booster
 * Sunday Stroll
 * > exit
 * ---GOODBYE!---
 */
public class Fiddlesticks {
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
                case "exit":
                    printExit();
                    isRunning = false;
                    break;

            }
        }
        sc.close();
    }

    public static void printFeature() {
        String s = """
                ---FEATURED---
                Mellow Morning
                Wake Up and Smell the Coffee
                Monday Motivation
                Songs to Sing in the Shower""";
        System.out.println(s);
    }

    public static void printNew() {
        String s = """
                ---NEW RELEASES---
                Mountains [Sia, Diplo, Labrinth]
                Runaway [Lil Peep]
                The Greatest Show [Panic! At The Disco]
                All Out Life [Slipknot]""";
        System.out.println(s);
    }

    public static void printCategories() {
        String s = """
                ---CATEGORIES---
                Top Lists
                Pop
                Mood
                Latin""";
        System.out.println(s);
    }

    public static void printPlaylists() {
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
