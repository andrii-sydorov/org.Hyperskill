package SimpleSearchEngine.Stage02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Expand the search
 * 
 * Description
 * 
 * Write a program that reads text lines from the standard input and processes
 * queries consisting of a single word to search for. The program must output
 * all lines that contain the string from the query.
 * 
 * You may choose what the text represents in your project. For example, each
 * line may describe:
 * 
 * a person, represented by a first name, last name, and an optional email;
 * 
 * an address of a building, represented by country, city, state, street, and
 * zip code;
 * 
 * information about a book, represented by ISBN, title, author/authors,
 * publisher, and so on.
 * 
 * You can take any of these ideas or use your own, but it is important to work
 * with the same type of dataset throughout all stages of the project. As item
 * delimiters, you can use spaces, commas (see CSV), or any other characters.
 * 
 * Here is an example of a line. It contains three items: first name, last name,
 * and this person's email.
 * 
 * Elsa Sanders elsa@gmail.com
 * 
 * In this example, all items are separated by spaces.
 * 
 * The search should ignore letter cases and all the extra spaces.
 * 
 * Firstly, the user should input a number N, which is a number of lines with
 * data they are going to enter next. Then the user enters N lines with data.
 * After that, the user enters a number M, which is a number of search queries.
 * And after each query, the program should print the information it managed to
 * find among the data. You can see this searching process in the example below.
 * Example
 * 
 * In the following example, we use different names and e-mails as an example of
 * the dataset. The lines that start with > represent the user input. Note that
 * these symbols are not part of the input.
 * 
 * Enter the number of people: 
 * > 6 
 * Enter all people: 
 * > Dwight Josephdjo@gmail.com 
 * > Rene Webb webb@gmail.com 
 * > Katie Jacobs 
 * > Erick Harrington harrington@gmail.com 
 * > Myrtle Medina 
 * > Erick Burgess
 * 
 * Enter the number of search queries: 
 * > 3
 * 
 * Enter data to search people: 
 * > ERICK
 * 
 * Found people: 
 * Erick Harrington harrington@gmail.com 
 * Erick Burgess
 * 
 * Enter data to search people: 
 * > unknown
 *  No matching people found.
 * 
 * Enter data to search people: 
 * > WEBB@gmail.com
 * 
 * Found people: 
 * Rene Webb webb@gmail.com
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
		System.out.println();
		System.out.println("Enter the number of search queries:");
		int numberQueries = Integer.valueOf(sc.nextLine());
		searchEngine(numberQueries, data, sc);
		sc.close();
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
		System.out.println("Found people:");
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
