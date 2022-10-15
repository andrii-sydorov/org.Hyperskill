package SimpleSearchEngine.Stage04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Stage 4/6: X-files
 * Description
 * Modify the program. The program should read data from a text file instead of
 * the standard input. The file structure depends on your text’s meaning
 * (personal information, building information, book information, and so on).
 * See an example below.
 * 
 * Dwight Joseph djo@gmail.com
 * Rene Webb webb@gmail.com
 * Katie Jacobs
 * Erick Harrington harrington@gmail.com
 * Myrtle Medina
 * Erick Burgess
 * The program must process the command line argument --data, and after that,
 * the name of the file with the data, for example, --data text.txt.
 * 
 * Note that the file should not include the total number of lines. All lines
 * must be read only once, at the start of your program.
 * 
 * Here is an example of a line. It contains three items: first name, last name,
 * and email of a person.
 * 
 * Output example
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
 * > WEBB@gmail.com
 * Rene Webb webb@gmail.com
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

public class XFiles {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            map.put(args[i], args[i + 1]);
        }
        String name = map.get("--data");
        String[] data = buildData(name).replaceAll("\r", "").split("\n");
        buildMenu(sc, data);
        sc.close();
    }

    private static void printMenu() {
        System.out.println("=== Menu ===");
        for (Options op : Options.values()) {
            System.out.println(op);
        }
    }

    private static void buildMenu(Scanner sc, String[] data) {
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
                    findPerson(sc, data);
                    break;
                case 2:
                    printPeople(data);
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
                    break;
            }
            System.out.println();
        }
    }

    private static void printPeople(String[] data) {
        System.out.println("=== List of people ===");
        for (String s : data) {
            System.out.println(s);
        }
    }

    private static void findPerson(Scanner sc, String[] data) {
        System.out.println("Enter a name or email to search all suitable people.");
        String name = sc.nextLine();
        searchEngine(data, name);
    }

    private static void searchEngine(String[] data, String name) {
        List<String> found = new ArrayList<>();
        for (int j = 0; j < data.length; j++) {
            if (data[j].toLowerCase().contains(name.toLowerCase())) {
                found.add(data[j]);
            }
        }
        if (found.isEmpty()) {
            System.out.println("No matching people found.");
        } else {
            printResult(found);
            found.clear();
        }
    }

    private static void printResult(List<String> found) {
        System.out.println("Found people:");
        for (String s : found) {
            System.out.println(s);
        }
    }

    private static String buildData(String name) {
        String ans = null;
        try {
            ans = new String(Files.readAllBytes(Paths.get(name)));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return ans;
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
