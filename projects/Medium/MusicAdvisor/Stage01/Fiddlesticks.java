package projects.Medium.MusicAdvisor.Stage01;

import java.util.Scanner;

/**
 * <h5>Stage 1/5: Strings and fiddlesticks</h5>
 * 
 * <h6>$1. Description</h6>
 * 
 * <p>When developing your web applications, it can be useful to implement the
 * ability to register users using third-party services (such as Twitter,
 * Facebook, Google, etc.), as well as the ability to interact with these
 * services (for example, use Twitter to publish a post, or getting a list of
 * friends on Facebook). To implement this functionality, you need to learn how
 * to work with the service's API. Most services use REST (Representational
 * State Transfer) to provide the ability to interact with the service. To learn
 * how to work with it from Java, we will develop a simple application that will
 * offer us music using Spotify.</p>
 * 
 * <p>First, in this stage, you have to implement simple functionality to read user
 * input and provide information at the user's request.</p>
 * <ul>
 * <li><b>featured</b> — a list of Spotify-featured playlists with their links fetched from
 * API;</li>
 * <li><b>new</b> — a list of new albums with artists and links on Spotify;</li>
 * <li><b>categories</b> — a list of all available categories on Spotify (just their
 * names);</li>
 * <li><b>playlists C_NAME</b>, where C_NAME is the name of category. The list contains
 * playlists of this category and their links on Spotify;</li>
 * <li><b>exit</b> shuts down the application.</li>
 * </ul>
 * 
 * <p>Notice that here, you don’t need to implement interaction with the API (you
 * will do that later), just replace the API responses with simple strings.</p>
 * 
 * <h6>$2. Example</h6>
 * 
 * <p>Below is an output example of the described program. Try to output all cases
 * like in the example.</p>
 * 
 * <p>The greater-than symbol followed by a space (<b>> </b>) represents the user input.
 * Note that it's not part of the input.</p>
 * 
 * <p>> new</p>
 * <p>---NEW RELEASES---</p>
 * <p>Mountains [Sia, Diplo, Labrinth]</p>
 * <p>Runaway [Lil Peep]</p>
 * <p>The Greatest Show [Panic! At The Disco]</p>
 * <p>All Out Life [Slipknot]</p>
 * <p>> featured</p>
 * <p>---FEATURED---</p>
 * <p>Mellow Morning</p>
 * <p>Wake Up and Smell the Coffee</p>
 * <p>Monday Motivation</p>
 * <p>Songs to Sing in the Shower</p>
 * <p>> categories</p>
 * <p>---CATEGORIES---</p>
 * <p>Top Lists</p>
 * <p>Pop</p>
 * <p>Mood</p>
 * <p>Latin</p>
 * <p>> playlists Mood</p>
 * <p>---MOOD PLAYLISTS---</p>
 * <p>Walk Like A Badass</p>
 * <p>Rage Beats</p>
 * <p>Arab Mood Booster</p>
 * <p>Sunday Stroll</p>
 * <p>> exit</p>
 * <p>---GOODBYE!---</p>
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
