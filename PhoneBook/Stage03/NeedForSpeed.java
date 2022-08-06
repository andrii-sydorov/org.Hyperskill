package PhoneBook.Stage03;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NeedForSpeed {

	private static String pathDirectory = "./src/PhoneBook/Stage03/small_directory.txt";
	private static String pathFind = "./src/PhoneBook/Stage03/small_find.txt";
	private static int toFindLinearSearch;
	private static int isFindLinearSearch;
	private static int toFindJumpingSearch;
	private static int isFindJumpingSearch;
	private static int toFindBinarySearch;
	private static int isFindBinarySearch;
	private static Person[] personToFindBubbleSort; // source
	private static Person[] personSeacrhFor; // to search
	private static Person[] personToFindQuickSort; // copy of source for quick sort
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
		
		Person[] toCheck = Arrays.copyOfRange(personToFindQuickSort, 0, personToFindQuickSort.length);
		Person[] toCheck1 = Arrays.copyOfRange(personToFindQuickSort, 0, personToFindQuickSort.length);
		Person[] toCheck2 = Arrays.copyOfRange(personToFindQuickSort, 0, personToFindQuickSort.length);
		Person[] toCheck3 = Arrays.copyOfRange(personToFindQuickSort, 0, personToFindQuickSort.length);
		
		Arrays.sort(toCheck);
		quickSortingAlgorithm(toCheck1, 0, toCheck1.length - 1);
		quickSortMy(toCheck2, 0, toCheck2.length - 1);
		bubbleSortingAlgorithm(toCheck3);
		System.out.println(Arrays.equals(toCheck, toCheck1));
		System.out.println();
		
		System.out.println(Arrays.equals(toCheck, toCheck2));
		System.out.println(Arrays.equals(toCheck, toCheck3));
		System.out.println(Arrays.equals(toCheck1, toCheck3));

//		linearSearch();
//
//		jumpSearchAlgorithm();
//
//		quickSort();

		 Collections.sort(lsToFind);
		
		 checkingSortingAlgorithm(toCheck3, lsToFind);

	}

	// ************** quickSearch + binarySearhc*******************************
	private static boolean searchLogicBinarySearch(Person[] toFind, Person value) {

		int left = 0;
		int right = toFind.length - 1;
		while (left < right) {
			int middle = (left + right) / 2;
			if (toFind[middle].equals(value)) {
				return true;
			}
			if (toFind[middle].compareTo(value) < 0) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
		}

		return false;
	}

	private static void binarySearch(Person[] personToFind, Person[] personSearchFor) {
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

			while (personToFindQuickSort[leftPointer].compareTo(pivot) > 0 && leftPointer < rightPointer) {
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

//		if (lp == start) {
//			quickSort(arr, start + 1, stop);
//		} else if (lp == stop) {
//			quickSort(arr, start, stop - 1);
//		} else {
//			quickSort(arr, start, lp - 1);
//			quickSort(arr, lp + 1, stop);
//		}

		quickSortMy(arr, start, lp - 1);
		quickSortMy(arr, lp + 1, stop);
	}

	private static void swap(Person[] personToFindQuickSort, int leftPointer, int rightPointer) {
		Person t = personToFindQuickSort[leftPointer];
		personToFindQuickSort[leftPointer] = personToFindQuickSort[rightPointer];
		personToFindQuickSort[rightPointer] = t;
	}

	private static void quickSort() {
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
		System.out.printf("Searching time: %d min. %d sec. %d ms.", minuteBinarySearch, secondsBinarySearch,
				milisecondsBinarySearch);
	}

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
			System.out.printf("Searching time: %d min. %d sec. %d ms.", minuteLinearSearch, secondsLinearSearch,
					milisecondsLinearSearch);

		} else {

			startJumpSearch = System.currentTimeMillis();
			jumpingSearch(personToFindBubbleSort, personSeacrhFor);
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
				lsToSearch.add(new Person(s, null));
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("The file was not loaded");
		}

		personSeacrhFor = lsToSearch.toArray(new Person[0]);
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
