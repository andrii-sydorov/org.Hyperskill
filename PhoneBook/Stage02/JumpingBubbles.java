package PhoneBook.Stage02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Stage 2/4: Jumping bubbles Description You have to iterate over each element
 * of the number list every time you want to find someone's number. This is the
 * only way to search if your list contains unordered data. Any number can be
 * anywhere on the list, so you have to check every element.
 * 
 * At this stage, you should sort the list of numbers alphabetically by the
 * owner’s name. Sort the list using the bubble sort algorithm and search in the
 * list using the jump search algorithm.
 * 
 * After sorting, search for 500 phone numbers using the list from the previous
 * stage. Note how long it takes to sort the list, and also measure the time the
 * program spends searching. Don't include the sorting time in the searching
 * time, because the list of numbers stays sorted after every search request. If
 * you want to save the sorted list into the file so you don’t have to sort it
 * again, do not override the file that contains the unsorted list of phone
 * numbers. This file will also be required in the next stage.
 * 
 * If the sorting process takes too long (more than 10 times longer than all 500
 * iterations of the linear search), you should stop sorting and use the linear
 * search. Look at the second example to see what you need to output.
 * 
 * Example Output both approaches one after another and see which one is faster.
 * The output example is shown below. Note that you can get totally different
 * sorting and searching times!
 * 
 * Example 1:
 * 
 * Start searching (linear search)... Found 500 / 500 entries. Time taken: 1
 * min. 56 sec. 328 ms.
 * 
 * Start searching (bubble sort + jump search)... Found 500 / 500 entries. Time
 * taken: 9 min. 15 sec. 291 ms. Sorting time: 8 min. 45 sec. 251 ms. Searching
 * time: 0 min. 30 sec. 40 ms.
 * 
 * Example 2:
 * 
 * Start searching (linear search)... Found 500 / 500 entries. Time taken: 2
 * min. 01 sec. 134 ms.
 */

public class JumpingBubbles {

	private static String pathDirectory = "./src/PhoneBook/Stage02/small_directory.txt";
	private static String pathFind = "./src/PhoneBook/Stage02/small_find.txt";
	private static int toFindLinearSearch;
	private static int isFindLinearSearch;
	private static int toFindJumpingSearch;
	private static int isFindJumpingSearch;
	private static Person[] personToFind; // source
	private static Person[] personSeacrhFor; // to search
	private static List<Person> lsToFind = new ArrayList<>();
	private static List<Person> lsToSearch = new ArrayList<>();
	private static long totalTimeLinearSearch;
	private static long startJumpSearch;
	private static long stopJumpSearch;
	private static long startBubbleSorting;
	private static long stopBubbleSorting;
	private static boolean exceededTime;

	public static void main(String[] args) {

		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current absolute path is: " + s);

		readDataFromDirectory(pathDirectory);
		readDataFromFind(pathFind);

		// linearSearch();
		// workingWithCollections();
		System.out.println();
		Person[] per = Arrays.copyOfRange(personToFind, 0, personToFind.length);
		Arrays.sort(per);
		bubbleSortingAlgorithm(personToFind);

		for (int i = 0; i < personToFind.length; i++) {

			System.out.println(personToFind[i]);

		}

		System.out.println(Arrays.equals(per, personToFind));

		System.exit(0);

		startBubbleSorting = System.currentTimeMillis();
		bubbleSortingAlgorithm(personToFind);
		long minuteBubbleSort = (stopBubbleSorting - startBubbleSorting) / 60000;
		long secondsBubbleSort = ((stopBubbleSorting - startBubbleSorting) % 60000) / 1000;
		long milisecondsBubbleSort = (stopBubbleSorting - startBubbleSorting) % 1000;
		String add = "";
		if (exceededTime) {
			add += "- STOPPED, moved to linear search";
			long startLinearSearch = System.currentTimeMillis();
			searchLogicLinearAlgorithm(personToFind, personSeacrhFor);
			long stopLinearSearch = System.currentTimeMillis();

			long minute = (stopLinearSearch - startBubbleSorting) / 60000;
			long seconds = ((stopLinearSearch - startBubbleSorting) % 60000) / 1000;
			long miliseconds = (stopLinearSearch - startBubbleSorting) % 1000;
			String timeLinearSearch = minute + " min. " + seconds + " sec. " + miliseconds + " ms.";

			long minuteLinearSearch = (stopLinearSearch - startLinearSearch) / 60000;
			long secondsLinearSearch = ((stopLinearSearch - startLinearSearch) % 60000) / 1000;
			long milisecondsLinearSearch = (stopLinearSearch - startLinearSearch) % 1000;

			System.out.println();
			System.out.println("Start searching (bubble sort + jump search)...");
			System.out.printf("Found %d / %d entries. Time taken: %s\n", isFindLinearSearch, toFindLinearSearch,
					timeLinearSearch);
			System.out.printf("Sorting time: %d min. %d sec. %d ms. %s\n", minuteBubbleSort, secondsBubbleSort,
					milisecondsBubbleSort, add);
			System.out.printf("Searching time: %d min. %d sec. %d ms.", minuteLinearSearch, secondsLinearSearch,
					milisecondsLinearSearch);

		} else {

			startJumpSearch = System.currentTimeMillis();
			jumpingSearch(personToFind, personSeacrhFor);
			stopJumpSearch = System.currentTimeMillis();
			long minuteJumpSort = (stopJumpSearch - startJumpSearch) / 60000;
			long secondsJumpSort = ((stopJumpSearch - startJumpSearch) % 60000) / 1000;
			long milisecondsJumpSort = (stopJumpSearch - startJumpSearch) % 1000;

			long minuteGeneral = (stopJumpSearch - startBubbleSorting) / 60000;
			long secondsGeneral = ((stopJumpSearch - startBubbleSorting) % 60000) / 1000;
			long milisecondsGeneral = (stopJumpSearch - startBubbleSorting) % 1000;

			System.out.println();

			System.out.println("Start searching (bubble sort + jump search)...");
			System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n", isFindJumpingSearch,
					toFindJumpingSearch, minuteGeneral, secondsGeneral, milisecondsGeneral);
			System.out.printf("Sorting time: %d min. %d sec. %d ms. %s\n", minuteBubbleSort, secondsBubbleSort,
					milisecondsBubbleSort, add);
			System.out.printf("Searching time: %d min. %d sec. %d ms.", minuteJumpSort, secondsJumpSort,
					milisecondsJumpSort);
		}

		// Collections.sort(lsToFind);
		//
		// checkingSortingAlgorithm(personToFind, lsToFind);

	}

	private static void linearSearch() {
		long startLinearSearch = System.currentTimeMillis();
		searchLogicLinearAlgorithm(personToFind, personSeacrhFor);
		long stopLinearSearch = System.currentTimeMillis();
		System.out.println("Start searching (linear search)...");
		long minute = (stopLinearSearch - startLinearSearch) / 60000;
		long seconds = ((stopLinearSearch - startLinearSearch) % 60000) / 1000;
		long miliseconds = (stopLinearSearch - startLinearSearch) % 1000;
		String timeLinearSearch = minute + " min. " + seconds + " sec. " + miliseconds + " ms.";
		System.out.printf("Found %d / %d entries. Time taken: %s\n", isFindLinearSearch, toFindLinearSearch,
				timeLinearSearch);
		totalTimeLinearSearch = stopLinearSearch - startLinearSearch;
	}

	private static void jumpingSearch(Person[] personToFind, Person[] personSearchFor) {
		for (int i = 0; i < personSearchFor.length; i++) {
			if (jumpSearch(personToFind, personSearchFor[i])) {
				isFindJumpingSearch++;
			}
		}
		toFindJumpingSearch = personSearchFor.length;
	}

	private static boolean jumpSearch(Person[] personToFind, Person value) {

		int curr = 0;
		int prev = 0;
		int step = (int) Math.sqrt(personToFind.length);
		int last = personToFind.length - 1;

		while (personToFind[curr].compareTo(value) < 0) {
			if (curr == last) {
				return false;
			}
			prev = curr;
			curr = Math.min(step + curr, last - 1);
		}

		while (personToFind[curr].compareTo(value) > 0) {
			if (curr == prev) {
				return false;
			}
			curr--;
		}

		return true;
	}

	private static void checkingSortingAlgorithm(Person[] personToFind, List<Person> lsToFind) {
		System.out.println("\nChecking sorting Algorithm: ");
		for (int i = 0; i < personToFind.length; i++) {
			if (personToFind[i].getName().equals(lsToFind.get(i).getName())) {
				continue;
			}
			System.out.printf("Index is %d\n", i);
			System.out.println(personToFind[i].getName());
			System.out.println(lsToFind.get(i).getName());
			break;
		}
	}

	private static void bubbleSortingAlgorithm(Person[] personToFind) {
		boolean isSorted = false;
		int turns = 0;
		while (!isSorted) {
			isSorted = true;
			for (int i = 0; i < personToFind.length - 1 - turns; i++) {
				if (personToFind[i].compareTo(personToFind[i + 1]) > 0) {
					Person p = personToFind[i];
					personToFind[i] = personToFind[i + 1];
					personToFind[i + 1] = p;
					isSorted = false;
				}
			}
			turns++;
//			stopBubbleSorting = System.currentTimeMillis();
//			if (stopBubbleSorting - startBubbleSorting > 10 * totalTimeLinearSearch) {
//				exceededTime = true;
//				break;
//			}
		}
	}

	private static void searchLogicLinearAlgorithm(Person[] personToFind, Person[] personSeacrhFor) {
		isFindLinearSearch = 0;
		for (Person p1 : personSeacrhFor) {
			for (Person p2 : personToFind) {
				if (p1.getName().equals(p2.getName())) {
					isFindLinearSearch++;
				}
			}
		}
		toFindLinearSearch = personSeacrhFor.length;
	}

	private static void readDataFromDirectory(String pathDirectory) {

		try (Scanner sc = new Scanner(new File(pathDirectory))) {
			while (sc.hasNext()) {
				String s = sc.nextLine();
				String name = s.substring(s.indexOf(" ") + 1);
				String phone = s.substring(0, s.indexOf(" "));
				lsToFind.add(new Person(name, phone));
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("The directory was not loaded");
		}

		personToFind = lsToFind.toArray(new Person[0]);

		// try {
		// directory = new String(Files.readAllBytes(Paths.get(pathDirectory)));
		// } catch (IOException fnfe) {
		// System.out.println("The directory was not loaded");
		// }
	}

	private static void readDataFromFind(String pathFind) {
		try (Scanner sc = new Scanner(new File(pathFind))) {
			while (sc.hasNext()) {
				String s = sc.nextLine();
				lsToSearch.add(new Person(s, null));
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("The file was not loaded");
		}

		personSeacrhFor = lsToSearch.toArray(new Person[0]);

		// try {
		// find = new String(Files.readAllBytes(Paths.get(pathFind)));
		// } catch (IOException fnfe) {
		// System.out.println("The find was not loaded");
		// }
	}

}

class Person implements Comparable<Person> {

	private String name;
	private String number;

	public Person(String name, String number) {
		this.name = name;
		this.number = number;
	}

	public String getName() {
		return this.name;
	}

	public String getNumber() {
		return this.number;
	}

	public int compareTo(Person p) {
		if (this.name.compareTo(p.getName()) > 0) {
			return 1;
		}
		if (this.name.compareTo(p.getName()) < 0) {
			return -1;
		}
		return 0;
	}

	public String toString() {
		return this.name + " " + this.number;
	}
}
