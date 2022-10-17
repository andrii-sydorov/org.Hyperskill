package SimpleSearchEngine.Stage05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Stage 5/6: stage5
 * Description
 * Now your program can successfully search for all matching lines, and the
 * search is case- and space-insensitive. There is one problem though: you need
 * to check each line to find out whether it contains the query string.
 * 
 * To optimize your program, you can use a data structure called an Inverted
 * Index. It maps each word to all positions/lines/documents in which the word
 * occurs. As a result, when we receive a query, we can immediately find the
 * answer without any comparisons.
 * 
 * Objectives
 * In this stage, build an inverted index at the start of the program and then
 * use the index for searching operations. You can implement it using the Map
 * class. It connects an item with a list (or set) of indexes belonging to the
 * lines that contain the item.
 * 
 * Suppose you have the following list of lines:
 * 
 * 0: Katie Jacobs
 * 1: Erick Harrington harrington@gmail.com
 * 2: Myrtle Medina
 * 3: Erick Burgess
 * For these lines, the inverted index will look like this:
 * 
 * Katie -> [0]
 * Jacobs -> [0]
 * Erick -> [1, 3]
 * Harrington -> [1]
 * harrington@gmail.com -> [1]
 * Myrtle -> [2]
 * Medina -> [2]
 * Burgess -> [3]
 * The order of pairs is not important. If you are searching for Erick, you can
 * immediately get the target fields using this mapping.
 * 
 * Note that the Inverted Index is not intended to search for parts of a word,
 * it is only used to search for full words.
 * 
 * Example
 * The lines that start with > represent the user input. Note that these symbols
 * are not part of the input.
 * 
 * === Menu ===
 * 1. Find a person
 * 2. Print all people
 * 0. Exit
 * > 1
 * 
 * Enter a name or email to search all suitable people.
 * > ERICK
 * 2 persons found:
 * Erick Harrington harrington@gmail.com
 * Erick Burgess
 * 
 * === Menu ===
 * 1. Find a person
 * 2. Print all people
 * 0. Exit
 * > 1
 * 
 * Enter a name or email to search all suitable people.
 * > ROY@gmail.com
 * 1 persons found:
 * Richard Roy roy@gmail.com
 * 
 * === Menu ===
 * 1. Find a person
 * 2. Print all people
 * 0. Exit
 * > 1
 * 
 * Enter a name or email to search all suitable people.
 * > john
 * No matching people found.
 * 
 * === Menu ===
 * 1. Find a person
 * 2. Print all people
 * 0. Exit
 * > 0
 * 
 * Bye!
 * 
 * @author SMD_ASY
 *
 */

public class InvertedIndexSearch {

    public static List<String> data = new ArrayList<>();
    public static Map<String, List<Integer>> mapWithData = new HashMap<>();

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            map.put(args[i], args[i + 1]);
        }
        String name = map.get("--data");
        buildData(name);
        buildMenu(sc);
        sc.close();
    }

    private static void printMenu() {
        System.out.println("=== Menu ===");
        for (Options op : Options.values()) {
            System.out.println(op);
        }
    }

    private static void buildMenu(Scanner sc) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            int userChoice = Integer.valueOf(sc.nextLine());
            System.out.println();
            switch (userChoice) {
                case 0:
                    System.out.println("Bye!");
                    isRunning = false;
                    break;
                case 1:
                    findPerson(sc);
                    break;
                case 2:
                    printPeople();
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
                    break;
            }
            System.out.println();
        }
    }

    private static void printPeople() {
        System.out.println("=== List of people ===");
        for (String s : data) {
            System.out.println(s);
        }
    }

    private static void findPerson(Scanner sc) {
        System.out.println("Enter a name or email to search all suitable people.");
        String name = sc.nextLine();
        List<Integer> ls = mapWithData.get(name);
        if (ls != null) {
            System.out.printf("%d person%s found:\n", ls.size(), ls.size() == 1 ? "" : "s");
            for (Integer i : ls) {
                System.out.println(data.get(i - 1));
            }
        } else {
            System.out.println("No matching people found.");
        }
    }

    private static void buildData(String name) {
        File f = new File(name);
        try (Scanner innerScanner = new Scanner(f)) {
            int index = 0;
            while (innerScanner.hasNext()) {
                String s = innerScanner.nextLine();
                data.add(s);
                String[] arrayOfData = s.split("\\s+");
                index++;
                for (int i = 0; i < arrayOfData.length; i++) {
                    if (mapWithData.keySet().contains(arrayOfData[i])) {
                        mapWithData.get(arrayOfData[i]).add(index);
                    } else {
                        List<Integer> ls = new ArrayList<>();
                        ls.add(index);
                        mapWithData.put(arrayOfData[i], ls);
                    }
                }

            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("The data were not downloaded");
        }
    }
}

enum Options {

    SEARCH("Find a person", 1), PRINT("Print all data.", 2), EXIT("Exit.", 0);

    private String message;
    private int index;

    Options(String message, int index) {
        this.message = message;
        this.index = index;
    }

    public String toString() {
        return this.index + ". " + this.message;
    }

    public int getIndex() {
        return this.index;
    }

}
