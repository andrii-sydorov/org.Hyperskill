package SimpleSearchEngine.Stage03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stage 3/6: 
 * 
 * User menu Description Modify the previous program. At this stage,
 * you need to add a user menu.
 * 
 * The menu should display the following actions:
 * 
 * 1. Search information. 
 * 2. Print all data. 
 * 0. Exit. 
 * The user must select a menu item and then enter data if necessary. Your program must not stop until
 * the corresponding option (the exit option) is chosen.
 * 
 * Decompose the program into separate methods to make it easy to understand and
 * add to or edit later.
 * 
 * Example In the example below, we use people as a dataset example. The lines
 * that start with > represent the user input. Note that these symbols are not
 * part of the input.
 * 
 * Enter the number of people: 
 * > 6 
 * Enter all people: 
 * > Dwight Josephdjo@gmail.com 
 * > Rene Webb webb@gmail.com 
 * > Katie Jacobs 
 * > Erick Harringtonharrington@gmail.com 
 * > Myrtle Medina 
 * > Erick Burgess
 * 
 * === Menu === 
 * 1. Find a person 
 * 2. Print all people 
 * 0. Exit 
 * > 3
 * 
 * Incorrect option! Try again.
 * 
 * === Menu === 
 * 1. Find a person 
 * 2. Print all people 
 * 0. Exit 
 * > 1
 * 
 * Enter a name or email to search all suitable people. 
 * > KATIE 
 * Katie Jacobs
 * 
 * === Menu === 
 * 1. Find a person 
 * 2. Print all people 
 * 0. Exit 
 * > 2
 * 
 * === List of people === 
 * Dwight Joseph djo@gmail.com 
 * Rene Webb webb@gmail.com
 * Katie Jacobs 
 * Erick Harrington harrington@gmail.com 
 * Myrtle Medina 
 * Erick Burgess
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

public class ExpandSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of people:");
		int numberPeople = Integer.valueOf(sc.nextLine());
		String[] data = buildData(numberPeople, sc);
		buildMenu(sc, data);
		sc.close();
	}

	private static void printMenu() {
		System.out.println("\n=== Menu ===");
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
			System.out.println("No matching people found.\n");
		} else {
			printResult(found);
			found.clear();
		}
	}

	private static void searchEngine(int numberQueries, String[] data, Scanner sc) {
		List<String> found = new ArrayList<>();
		for (int i = 0; i < numberQueries; i++) {
			System.out.println();
			System.out.println("Enter data to search people:");
			String dataToSearch = sc.nextLine();
			for (int j = 0; j < data.length; j++) {
				if (data[j].toLowerCase().contains(dataToSearch.toLowerCase())) {
					found.add(data[j]);
				}
			}
			if (found.isEmpty()) {
				System.out.println("No matching people found.");
			} else {
				System.out.println();
				printResult(found);
				found.clear();
			}
		}
	}

	private static void printResult(List<String> found) {
		// System.out.println("Found people:");
		for (String s : found) {
			System.out.println(s);
		}
	}

	private static String[] buildData(int numberPeople, Scanner sc) {
		System.out.println("Enter all people:");
		String[] ans = new String[numberPeople];
		for (int i = 0; i < numberPeople; i++) {
			ans[i] = sc.nextLine();
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
