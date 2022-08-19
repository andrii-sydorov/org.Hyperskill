package SortingTool.Stage04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class EverythingCount {
	private static String dataType;
	private static String kindOfComparator;
	private static Map<String, Integer> dataToAnalyze = new HashMap<>();
	private static Map<String, String> associateMap = new HashMap<>();
	private static Comparator cmp;
	private static int count = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
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
		String data = "-dataType";
		String comparator = "-sortingType";
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < args.length; i += 2) {
			map.put(args[i], args[i + 1]);
		}
		dataType = map.get(data);
		kindOfComparator = map.getOrDefault(comparator, "natural");
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
		switch (kindOfComparator) {
		case "natural":
			List<String> lsString = new ArrayList<String>(dataToAnalyze.keySet());
			lsString.sort((x, y) -> Integer.valueOf(x).compareTo(Integer.valueOf(y)));
			System.out.print("Sorted data: ");
			lsString.forEach(x -> System.out.print(x + " "));
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
