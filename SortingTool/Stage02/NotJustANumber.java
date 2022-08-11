package SortingTool.Stage02;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Stage 2/6: Not just numbers
 * Description
 * Remember how we wanted the program to work not only with numbers but also
 * with lines and words? In this stage, you will add behavior for text data
 * types to your program. You will also implement parsing for command-line
 * arguments that will allow the user to define the input data type
 * 
 * After parsing the arguments and reading the input, the program should treat
 * the input according to its data type and output an information message
 * similar to the one from the previous stage.
 * 
 * Objectives
 * 1. Parse arguments that define the input data type:
 * 	- if the optional -dataType argument is provided, it should be followed by
 * long, line, or word, which means that the input consists of numbers, lines,
 * or words, respectively.
 * 	- if the argument is not provided, you should assume that the -dataType
 * argument is word.
 * 2. Read the input depending on the type:
 * 	- long — numbers with an arbitrary number of spaces between them, just like in
 * the previous stage.
 *  - line — each line treated as a whole string.
 * 	- word — continuous sequences of characters separated by an arbitrary number of
 * spaces.
 * 3. Compute the following information about the data:
 * 	1. The number of lines, numbers, or words in the input.
 * 	2. The greatest number or the longest line or word in the input.
 * 	3. How many times this greatest or longest element occurs in the input (compare
 * words and lines by length; if two elements are the same length, arrange them
 * alphabetically).
 *  4. The greatest/longest element's occurrence percentage.
 * 4. Print this information as shown in the examples. Note that you should print
 * the longest line on a separate line, so you will end up printing 4 lines
 * instead of 2.
 * 
 * Examples
 * Run configuration examples:
 * 
 * java SortingTool -dataType long
 * java SortingTool -dataType line
 * java SortingTool -dataType word
 * Run examples
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1, for integers:
 * 
 * > 1 -2 333 4
 * > 42
 * > 1 1
 * Total numbers: 7.
 * The greatest number: 333 (1 time(s), 14%).
 * 
 * Example 2, for lines:
 * 
 * > 1 -2 333 4
 * > 42
 * > 1 1
 * Total lines: 3.
 * The longest line:
 * 1 1
 * (1 time(s), 33%).
 * 
 * Example 3, for words:
 * 
 * > 1 -2 333 4
 * > 42
 * > 1 1
 * Total words: 7.
 * The longest word: 333 (1 time(s), 14%).
 */

public class NotJustANumber {

	private static String dataType;
	private static Map<String, Integer> dataToAnalyze = new HashMap<>();
	private static Map<String, String> associateMap = new HashMap<>();
	private static int count = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		defineDataType(args);
		buildAssociateMap();
		Scanner sc = new Scanner(System.in);
		buildDataToAnalyze(sc);
		sc.close();
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
