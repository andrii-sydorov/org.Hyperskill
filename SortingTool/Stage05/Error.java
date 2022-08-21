package SortingTool.Stage05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * Stage 5/6: Error
 * Description
 * There is always a possibility that someone will run your program the wrong
 * way. It shouldn't just silently crash, but instead, it should print a message
 * that informs the user of the mistake they made.
 * 
 * In this stage, let's implement error handling for various exceptional
 * situations the user might encounter.
 * 
 * Objectives
 * Add exception handling for possible errors and output error messages to the
 * console:
 * 
 * if the -sortingType argument is provided but the type is not, print a message
 * No sorting type defined!
 * if the -dataType argument is provided but the type is not, print No data type
 * defined!
 * if unknown command-line arguments are provided, print "-arg" is not a valid
 * parameter. It will be skipped. for each unknown argument -arg;
 * if there are strings in the input, but the data type is defined as long,
 * print "abc" is not a long. It will be skipped. for each string abc from the
 * input.
 * Examples
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1: sorting numbers naturally without errors
 * 
 * $> java SortingTool -sortingType natural -dataType long
 * > 1 -2 33 4
 * > 42
 * > 1 1
 * Total numbers: 7.
 * Sorted data: -2 1 1 1 4 42 333
 * Example 2: sorting numbers by count without errors
 * 
 * $> java SortingTool -sortingType byCount -dataType long
 * > 1 -2 33 4
 * > 42
 * > 1 1
 * Total numbers: 7.
 * -2: 1 time(s), 14%
 * 4: 1 time(s), 14%
 * 33: 1 time(s), 14%
 * 42: 1 time(s), 14%
 * 1: 3 time(s), 43%
 * Example 3: missing sorting type
 * 
 * $> java SortingTool -sortingType
 * No sorting type defined!
 * Example 4: missing data type
 * 
 * $> java SortingTool -dataType
 * No data type defined!
 * Example 5: invalid arguments and input value
 * 
 * $> java SortingTool -dataType long -sortingType natural -abc -def
 * "-abc" is not a valid parameter. It will be skipped.
 * "-def" is not a valid parameter. It will be skipped.
 * > a 2 -42
 * "a" is not a long. It will be skipped.
 * Total numbers: 2.
 * Sorted data: -42 2
 */

public class Error {
	private static String dataType;
	private static String kindOfComparator;
	private static Map<String, Integer> dataToAnalyze = new HashMap<>();
	private static Map<String, String> associateMap = new HashMap<>();
	private static List<String> dataInput = new ArrayList<>();
	private static int count = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		defineDataType(args);
		buildAssociateMap();
		buildDataToAnalyze(sc);
		sc.close();
	}

	private static void buildAssociateMap() {
		associateMap.put("long", "numbers");
		associateMap.put("word", "words");
		associateMap.put("line", "lines");
	}

	private static void defineDataType(String[] args) {
		String data = "-dataType";
		String comparator = "-sortingType";
		// by default when key -sortingType is absent
		kindOfComparator = "natural";
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(data) && (i + 1) < args.length) {
				dataType = args[i + 1];
				i++;
			}
			if (args[i].equals(comparator)) {
				if (i + 1 < args.length) {
					kindOfComparator = args[i + 1];
					i++;
				} else {
					kindOfComparator = null;
				}
			}
		}

		try {
			if (dataType == null || dataType.contains("-")) {
				throw new MyException("No data type defined!");
			}
			if (kindOfComparator == null || kindOfComparator.contains("-")) {
				throw new MyException("No sorting type defined!");
			}
		} catch (MyException my) {
			System.out.println(my.getMessage());
			System.exit(0);
		}

		for (int i = 0; i < args.length; i++) {
			boolean validArguments = args[i].equals(data) || args[i].equals(comparator) || args[i].equals(dataType)
					|| args[i].equals(kindOfComparator);
			if (!validArguments) {
				System.out.printf("\"%s\" is not a valid parameter. It will be skipped.\n", args[i]);
			}
		}

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
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
			if (data.isEmpty()) {
				break;
			}
			String[] line = data.split("\\s+");
			for (int i = 0; i < line.length; i++) {
				try {
					long l = Long.valueOf(line[i]);
					dataToAnalyze.put(String.valueOf(l), dataToAnalyze.getOrDefault(String.valueOf(l), 0) + 1);
					dataInput.add(String.valueOf(l));
					count++;
				} catch (NumberFormatException nfe) {
					System.out.printf("\"%s\" is not a long. It will be skipped.", line[i]);
				}

			}

		}
	}

	private static void buildStatisticWithLong(String dataType) {
		System.out.printf("Total %s: %d.\n", associateMap.get(dataType), count);
		switch (kindOfComparator) {
			case "natural":
				// List<String> lsString = new ArrayList<String>(dataToAnalyze.keySet());
				dataInput.sort((x, y) -> Integer.valueOf(x).compareTo(Integer.valueOf(y)));
				System.out.print("Sorted data: ");
				dataInput.forEach(x -> System.out.print(x + " "));
				break;
			case "byCount":
				List<Entry<String, Integer>> list = new ArrayList<>(dataToAnalyze.entrySet());
				Comparator<Entry<String, Integer>> cmp1 = (x, y) -> x.getValue().compareTo(y.getValue());
				Comparator<Entry<String, Integer>> cmp2 = (x, y) -> Integer.valueOf(x.getKey())
						.compareTo(Integer.valueOf(y.getKey()));
				list.sort(cmp1.thenComparing(cmp2));
				// print result
				for (Entry<String, Integer> entr : list) {
					System.out.printf("%s: %d time(s), %d%%\n", entr.getKey(), entr.getValue(),
							entr.getValue() * 100 / count);
				}
				break;
			default:
				break;
		}
	}

	private static void buildStatisticWithWord(String dataType) {
		System.out.printf("Total %s: %d.\n", associateMap.get(dataType), count);
		switch (kindOfComparator) {
			case "natural":
				// List<String> lsString = new ArrayList<String>(dataToAnalyze.keySet());
				dataInput.sort((x, y) -> Integer.valueOf(x).compareTo(Integer.valueOf(y)));
				System.out.print("Sorted data: ");
				dataInput.forEach(x -> System.out.print(x + " "));
				break;
			case "byCount":
				List<Entry<String, Integer>> list = new ArrayList<>(dataToAnalyze.entrySet());
				Comparator<Entry<String, Integer>> cmp1 = (x, y) -> x.getValue().compareTo(y.getValue());
				Comparator<Entry<String, Integer>> cmp2 = (x, y) -> x.getKey().compareTo(y.getKey());
				list.sort(cmp1.thenComparing(cmp2));
				// print result
				for (Entry<String, Integer> entr : list) {
					System.out.printf("%s: %d time(s), %d%%\n", entr.getKey(), entr.getValue(),
							entr.getValue() * 100 / count);
				}
				break;
			default:
				break;
		}
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
				dataInput.add(data[i]);
				count++;
			}
		}
	}

	private static void workWithLine(Scanner sc) {
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
			if (data.isEmpty()) {
				break;
			}
			dataToAnalyze.put(data, dataToAnalyze.getOrDefault(data, 0) + 1);
			dataInput.add(data);
			count++;

		}
	}

	private static void buildStatisticWithLine(String dataType) {
		System.out.printf("Total %s: %d.\n", associateMap.get(dataType), count);
		switch (kindOfComparator) {
			case "natural":
				// List<String> lsString = new ArrayList<String>(dataToAnalyze.keySet());
				dataInput.sort((x, y) -> x.compareTo(y));
				System.out.println("Sorted data:");
				dataInput.forEach(x -> System.out.println(x));
				break;
			case "byCount":
				List<Entry<String, Integer>> list = new ArrayList<>(dataToAnalyze.entrySet());
				Comparator<Entry<String, Integer>> cmp1 = (x, y) -> x.getValue().compareTo(y.getValue());
				Comparator<Entry<String, Integer>> cmp2 = (x, y) -> x.getKey().compareTo(y.getKey());
				list.sort(cmp1.thenComparing(cmp2));
				// print result
				for (Entry<String, Integer> entr : list) {
					System.out.printf("%s: %d time(s), %d%%\n", entr.getKey(), entr.getValue(),
							entr.getValue() * 100 / count);
				}
				break;
			default:
				break;
		}
	}
}

class MyException extends Exception {

	private String message;

	public MyException(String message) {
		super(message);
	}
}
