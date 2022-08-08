package PhoneBook.Stage04;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.Scanner;
import java.util.Hashtable;

/**
 * Stage 4/4: Instant search
 * Description
 * The search is pretty fast, but is it possible to come up with something even
 * faster?
 * 
 * In the previous stage, you prepared the data using an algorithm with a
 * complexity of O(n log n) and found the data using an algorithm with a
 * complexity of O(log n). At this stage, you will implement faster data
 * preparation and a faster search. The preparation will have a complexity of
 * O(n), and the search will have a complexity of O(1). A hash table will help
 * you with this.
 * 
 * You need to add all the elements to the hash table and then find the
 * necessary phone numbers, as in the previous stages. Since the hash table is
 * filled once, you need to measure the hash table creation time separately
 * (just like you did with sorting in the previous stage).
 * 
 * Example
 * Output all four approaches one after another and see which one is faster. The
 * output example is shown below. Note that you can get totally different
 * sorting and searching times!
 * 
 * Start searching (linear search)...
 * Found 500 / 500 entries. Time taken: 1 min. 56 sec. 328 ms.
 * 
 * Start searching (bubble sort + jump search)...
 * Found 500 / 500 entries. Time taken: 9 min. 15 sec. 291 ms.
 * Sorting time: 8 min. 45 sec. 251 ms.
 * Searching time: 0 min. 30 sec. 40 ms.
 * 
 * Start searching (quick sort + binary search)...
 * Found 500 / 500 entries. Time taken: 1 min. 21 sec. 996 ms.
 * Sorting time: 1 min. 17 sec. 381 ms.
 * Searching time: 0 min. 4 sec. 615 ms.
 * 
 * Start searching (hash table)...
 * Found 500 / 500 entries. Time taken: 0 min. 4 sec. 256 ms.
 * Creating time: 0 min. 4 sec. 121 ms.
 * Searching time: 0 min. 0 sec. 135 ms.
 */

public class InstantSearch {

	private static String pathDirectory = "./src/PhoneBook/Stage04/directory.txt";
	private static String pathFind = "./src/PhoneBook/Stage04/find.txt";
	private static int toFindLinearSearch;
	private static int isFindLinearSearch;
	private static int toFindJumpingSearch;
	private static int isFindJumpingSearch;
	private static int toFindBinarySearch;
	private static int isFindBinarySearch;
	private static int toFindInstantSearch;
	private static int isFindInstantSearch;
	private static Person[] personToFindBubbleSort; // source
	private static Person[] personSeacrhFor; // to search
	private static Person[] personToFindQuickSort; // copy of source for quick sort
	private static List<Person> lsToFind = new ArrayList<>();
	private static List<Person> lsSearchFor = new ArrayList<>();
	private static long totalTimeLinearSearch;
	private static long startBubbleSorting;
	private static long stopBubbleSorting;
	private static boolean exceededTime;
	private static Map<String, String> tableToFind = new Hashtable<>();

	public static void main(String[] args) {

		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current absolute path is: " + s);

		readDataFromDirectory(pathDirectory);
		readDataFromFind(pathFind);

		linearSearch();

		jumpSearchAlgorithm();

		binarySearchAlgorithm();

		instantSearchAlgorithm();

		// Collections.sort(lsToFind);

		// checkingSortingAlgorithm(toCheck3, lsToFind);

	}

	// ****************************Instant Search*******************************
	// ********************************Start************************************

	private static void instantSearchAlgorithm() {
		long startCreateHashTable = System.currentTimeMillis();
		createHashTable();
		long stopCreateHashTable = System.currentTimeMillis();
		long minuteCreateTable = (stopCreateHashTable - startCreateHashTable) / 60000;
		long secondsCreateTable = ((stopCreateHashTable - startCreateHashTable) % 60000) / 1000;
		long milisecondsCreateTable = (stopCreateHashTable - startCreateHashTable) % 1000;

		long startInstantSearch = System.currentTimeMillis();
		instantSearch();
		long stopInstantSearch = System.currentTimeMillis();
		long minuteInstantSearch = (stopInstantSearch - startInstantSearch) / 60000;
		long secondsInstantSearch = ((stopInstantSearch - startInstantSearch) % 60000) / 1000;
		long milisecondsInstantSearch = (stopInstantSearch - startInstantSearch) % 1000;

		long minute = (stopInstantSearch - startCreateHashTable) / 60000;
		long seconds = ((stopInstantSearch - startCreateHashTable) % 60000) / 1000;
		long miliseconds = (stopInstantSearch - startCreateHashTable) % 1000;
		String timeInstantSearch = minute + " min. " + seconds + " sec. " + miliseconds + " ms.";

		System.out.println();
		System.out.println("Start searching (hash table)...");
		System.out.printf("Found %d / %d entries. Time taken: %s\n", isFindInstantSearch, toFindInstantSearch,
				timeInstantSearch);
		System.out.printf("Creating time: %d min. %d sec. %d ms.\n", minuteCreateTable, secondsCreateTable,
				milisecondsCreateTable);
		System.out.printf("Searching time: %d min. %d sec. %d ms.", minuteInstantSearch, secondsInstantSearch,
				milisecondsInstantSearch);
	}

	private static void createHashTable() {
		for (Person p : lsToFind) {
			tableToFind.put(p.getName(), p.getNumber());
		}
	}

	private static void instantSearch() {
		isFindInstantSearch = 0;
		for (Person p : lsSearchFor) {
			if (tableToFind.get(p.getName()) != null) {
				isFindInstantSearch++;
			}
		}
		toFindInstantSearch = lsSearchFor.size();
	}
	// ***************Instant Search********************************************
	// *******************Stop**************************************************

	// ************** quickSort + binarySearhc********************************
	// ****************************Start****************************************
	private static boolean searchLogicBinarySearch(Person[] personToFind, Person value) {

		int left = 0;
		int right = personToFind.length - 1;

		while (left <= right) {
			int middle = (left + right) / 2;
			if (personToFind[middle].compareTo(value) == 0) {
				return true;
			}
			if (personToFind[middle].compareTo(value) < 0) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
		}

		return false;
	}

	private static void binarySearch(Person[] personToFind, Person[] personSearchFor) {
		isFindBinarySearch = 0;
		for (int i = 0; i < personSearchFor.length; i++) {
			if (searchLogicBinarySearch(personToFind, personSearchFor[i])) {
				isFindBinarySearch++;
			}
		}
		toFindBinarySearch = personSearchFor.length;
	}

	private static void quickSortingAlgorithm(Person[] personToFindQuickSort, int lowIndex, int highIndex) {

		if (lowIndex >= highIndex) {
			return;
		}

		Person pivot = personToFindQuickSort[highIndex];

		int leftPointer = lowIndex;
		int rightPointer = highIndex - 1;

		while (leftPointer < rightPointer) {

			while (personToFindQuickSort[leftPointer].compareTo(pivot) <= 0 && leftPointer < rightPointer) {
				leftPointer++;
			}

			while (personToFindQuickSort[rightPointer].compareTo(pivot) > 0 && leftPointer < rightPointer) {
				rightPointer--;
			}

			swap(personToFindQuickSort, leftPointer, rightPointer);
		}

		if (personToFindQuickSort[leftPointer].compareTo(personToFindQuickSort[highIndex]) > 0) {
			swap(personToFindQuickSort, leftPointer, highIndex);
		} else {
			leftPointer = highIndex;
		}

		quickSortingAlgorithm(personToFindQuickSort, lowIndex, leftPointer - 1); // search left from pivot
		quickSortingAlgorithm(personToFindQuickSort, leftPointer + 1, highIndex); // search rigth from pivot

	}

	private static void quickSortMy(Person[] arr, int start, int stop) {

		if (start >= stop) {
			return;
		}

		Person pivot = arr[stop];
		int lp = start;
		int rp = stop;

		while (true) {
			if (pivot.compareTo(arr[lp]) >= 0) {
				lp++;
			} else {
				if (pivot.compareTo(arr[rp]) > 0) {
					Person t = arr[rp];
					arr[rp] = arr[lp];
					arr[lp] = t;
				} else {
					rp--;
				}
			}
			if (lp == rp && pivot.compareTo(arr[lp]) < 0) {
				Person t = arr[stop];
				arr[stop] = arr[lp];
				arr[lp] = t;
				break;
			} else if (lp == rp && pivot.compareTo(arr[lp]) >= 0) {
				break;
			}
		}

		quickSortMy(arr, start, lp - 1);
		quickSortMy(arr, lp + 1, stop);
	}

	private static void swap(Person[] personToFindQuickSort, int leftPointer, int rightPointer) {
		Person t = personToFindQuickSort[leftPointer];
		personToFindQuickSort[leftPointer] = personToFindQuickSort[rightPointer];
		personToFindQuickSort[rightPointer] = t;
	}

	private static void binarySearchAlgorithm() {

		long startQuickSorting = System.currentTimeMillis();
		quickSortingAlgorithm(personToFindQuickSort, 0, personToFindQuickSort.length - 1);
		long stopQuickSorting = System.currentTimeMillis();
		long minuteQuickSort = (stopQuickSorting - startQuickSorting) / 60000;
		long secondsQuickSort = ((stopQuickSorting - startQuickSorting) % 60000) / 1000;
		long milisecondsQuickSort = (stopQuickSorting - startQuickSorting) % 1000;

		long startBianrySearch = System.currentTimeMillis();
		binarySearch(personToFindQuickSort, personSeacrhFor);
		long stopBinarySearch = System.currentTimeMillis();
		long minuteBinarySearch = (stopBinarySearch - startBianrySearch) / 60000;
		long secondsBinarySearch = ((stopBinarySearch - startBianrySearch) % 60000) / 1000;
		long milisecondsBinarySearch = (stopBinarySearch - startBianrySearch) % 1000;

		long minute = (stopBinarySearch - startQuickSorting) / 60000;
		long seconds = ((stopBinarySearch - startQuickSorting) % 60000) / 1000;
		long miliseconds = (stopBinarySearch - startQuickSorting) % 1000;
		String timeBinarySearch = minute + " min. " + seconds + " sec. " + miliseconds + " ms.";

		System.out.println();
		System.out.println("Start searching (quick sort + binary search)...");
		System.out.printf("Found %d / %d entries. Time taken: %s\n", isFindBinarySearch, toFindBinarySearch,
				timeBinarySearch);
		System.out.printf("Sorting time: %d min. %d sec. %d ms.\n", minuteQuickSort, secondsQuickSort,
				milisecondsQuickSort);
		System.out.printf("Searching time: %d min. %d sec. %d ms.\n", minuteBinarySearch, secondsBinarySearch,
				milisecondsBinarySearch);
	}

	// ************** quickSort + binarySearhc*******************************
	// ****************************Stop****************************************

	// ************** bubbleSort + jumpingSearch********************************
	// ****************************Start****************************************

	private static void jumpSearchAlgorithm() {
		startBubbleSorting = System.currentTimeMillis();
		bubbleSortingAlgorithm(personToFindBubbleSort);
		long minuteBubbleSort = (stopBubbleSorting - startBubbleSorting) / 60000;
		long secondsBubbleSort = ((stopBubbleSorting - startBubbleSorting) % 60000) / 1000;
		long milisecondsBubbleSort = (stopBubbleSorting - startBubbleSorting) % 1000;
		String add = "";
		if (exceededTime) {
			add += "- STOPPED, moved to linear search";
			long startLinearSearch = System.currentTimeMillis();
			searchLogicLinearAlgorithm(personToFindBubbleSort, personSeacrhFor);
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
			System.out.printf("Searching time: %d min. %d sec. %d ms.\n", minuteLinearSearch, secondsLinearSearch,
					milisecondsLinearSearch);

		} else {

			long startJumpSearch = System.currentTimeMillis();
			jumpingSearch(personToFindBubbleSort, personSeacrhFor);
			long stopJumpSearch = System.currentTimeMillis();
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
			System.out.printf("Searching time: %d min. %d sec. %d ms.\n", minuteJumpSort, secondsJumpSort,
					milisecondsJumpSort);
		}
	}

	private static void linearSearch() {
		long startLinearSearch = System.currentTimeMillis();
		searchLogicLinearAlgorithm(personToFindBubbleSort, personSeacrhFor);
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
		isFindJumpingSearch = 0;
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

		if (personToFind[curr].compareTo(value) == 0) {
			return true;
		}

		return false;
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
			stopBubbleSorting = System.currentTimeMillis();
			if (stopBubbleSorting - startBubbleSorting > 10 * totalTimeLinearSearch) {
				exceededTime = true;
				break;
			}
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

		personToFindBubbleSort = lsToFind.toArray(new Person[0]);
		personToFindQuickSort = lsToFind.toArray(new Person[0]);
	}

	private static void readDataFromFind(String pathFind) {
		try (Scanner sc = new Scanner(new File(pathFind))) {
			while (sc.hasNext()) {
				String s = sc.nextLine();
				lsSearchFor.add(new Person(s, null));
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("The file was not loaded");
		}

		personSeacrhFor = lsSearchFor.toArray(new Person[0]);
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
