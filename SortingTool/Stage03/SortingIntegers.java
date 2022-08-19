package SortingTool.Stage03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Stage 3/6: Sorting it out
 * Description
 * This project is called Sorting Tool, but, so far, you still haven’t really
 * sorted the elements of the user input. Let's add a number-sorting mechanism
 * to the program and provide an appropriate command-line argument to use this
 * function.
 * 
 * The new optional -sortIntegers argument indicates that the input numbers
 * should be sorted.
 * 
 * Objectives
 * Update the parsing of command-line arguments to support the number sorting
 * option.
 * 
 * If the -sortIntegers argument is provided, ignore the other arguments and
 * output two lines: the first containing the total number of numbers in the
 * input, and the second containing all of the input numbers in ascending order.
 * 
 * If the -sortIntegers argument is not provided, the behavior of the program
 * should be the same as in the previous stage.
 * 
 * Example
 * Run configuration example:
 * 
 * java SortingTool -sortIntegers
 * Run example
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * > 1 -2 33 4
 * > 42
 * > 1 1
 * Total numbers: 7.
 * Sorted data: -2 1 1 1 4 33 42
 */

public class SortingIntegers {
	private static String dataType;
	private static Map<String, Integer> dataToAnalyze = new HashMap<>();
	private static Map<String, String> associateMap = new HashMap<>();
	private static int count = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		final String value = "-sortIntegers";
		if (arrayContains(args, value) != -1) {
			sortIntegersAndBuildAnalytycs(sc);
			System.exit(0);
		}
		defineDataType(args);
		buildAssociateMap();
		buildDataToAnalyze(sc);
		sc.close();
	}

	private static void sortIntegersAndBuildAnalytycs(Scanner sc) {
		List<Integer> ls = new ArrayList<>();
		while (sc.hasNextInt()) {
			ls.add(sc.nextInt());
		}
		System.out.printf("Total numbers: %d\n", ls.size());
		System.out.print("Sorted data: ");
		ls.sort((x, y) -> x.compareTo(y));
		ls.forEach(x -> System.out.print(x + " "));
	}

	private static int arrayContains(String[] args, String value) {
		// implementing binary Search for Array
		if (args.length == 0) {
			return -1;
		}
		Arrays.sort(args);
		int leftPointer = 0;
		int rightPointer = args.length - 1;
		int middle = 0;

		while (leftPointer <= rightPointer) {

			middle = (leftPointer + rightPointer) / 2;
			if (args[middle].compareTo(value) > 0) {
				rightPointer = middle - 1;
			} else if (args[middle].compareTo(value) < 0) {
				leftPointer = middle + 1;
			}

			if (args[middle].compareTo(value) == 0) {
				return middle;
			}

		}
		return -1;

	}

	private static void buildAssociateMap() {
		associateMap.put("long", "numbers");
		associateMap.put("word", "words");
		associateMap.put("line", "lines");
	}

	private static void defineDataType(String[] args) {
		String key = "-dataType";
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < args.length; i += 2) {
			map.put(args[i], args[i + 1]);
		}
		dataType = map.get(key);
	}

	private static void buildDataToAnalyze(Scanner sc) {
		switch (dataType) {
			case "long":
				workWithLong(sc);
				buildStatisticWithLong(dataType);
				break;
			case "word":
				workWithWord(sc);
				buildStatisticWithWord(dataType);
				break;
			case "line":
				workWithLine(sc);
				buildStatisticWithLine(dataType);
				break;
			default:
				System.out.println("Incorrect command line argumetns");
				break;
		}
	}

	private static void workWithLong(Scanner sc) {
		while (sc.hasNextLong()) {
			long l = sc.nextLong();
			dataToAnalyze.put(String.valueOf(l), dataToAnalyze.getOrDefault(String.valueOf(l), 0) + 1);
			count++;
		}
	}

	private static void buildStatisticWithLong(String dataType) {
		System.out.printf("Total %s: %d.\n", associateMap.get(dataType), count);
		int max = Integer.MIN_VALUE;
		String key = null;
		for (Map.Entry<String, Integer> entr : dataToAnalyze.entrySet()) {
			if (Integer.valueOf(entr.getKey()) > max) {
				max = Integer.valueOf(entr.getKey());
				key = entr.getKey();
			}
		}
		System.out.printf("The greatest number: %s (%d time(s), %d%%).\n", key, dataToAnalyze.get(key),
				dataToAnalyze.get(key) * 100 / count);

	}

	private static void workWithWord(Scanner sc) {
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			if (s.isEmpty()) {
				break;
			}
			String[] data = s.split("\\s+");
			for (int i = 0; i < data.length; i++) {
				dataToAnalyze.put(data[i], dataToAnalyze.getOrDefault(data[i], 0) + 1);
				count++;
			}
		}
	}

	private static void buildStatisticWithWord(String dataType) {
		System.out.printf("Total %s: %d.\n", associateMap.get(dataType), count);
		Integer max = Integer.MIN_VALUE;
		String key = null;
		for (Map.Entry<String, Integer> entr : dataToAnalyze.entrySet()) {
			if (entr.getKey().length() > max) {
				max = entr.getKey().length();
				key = entr.getKey();
			}
		}
		System.out.printf("The longest %s: %s (%d time(s), %d%%).\n", dataType, key, dataToAnalyze.get(key),
				dataToAnalyze.get(key) * 100 / count);

	}

	private static void workWithLine(Scanner sc) {
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
			if (data.isEmpty()) {
				break;
			}
			dataToAnalyze.put(data, dataToAnalyze.getOrDefault(data, 0) + 1);
			count++;

		}
	}

	private static void buildStatisticWithLine(String dataType) {
		System.out.printf("Total %s: %d.\n", associateMap.get(dataType), count);
		Integer max = Integer.MIN_VALUE;
		String key = null;
		for (Map.Entry<String, Integer> entr : dataToAnalyze.entrySet()) {
			if (entr.getKey().length() > max) {
				max = entr.getKey().length();
				key = entr.getKey();
			}
		}
		System.out.printf("The longest %s:\n%s\n(%d time(s), %d%%).\n", dataType, key, dataToAnalyze.get(key),
				dataToAnalyze.get(key) * 100 / count);

	}
}
