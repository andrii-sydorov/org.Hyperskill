package ReadabilityScore.Stage01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(defineDifficulty(sc.nextLine()));
        sc.close();
    }

    private static String defineDifficulty(String s) {
        return s.length() > 100 ? "HARD" : "EASY";
    }
}
