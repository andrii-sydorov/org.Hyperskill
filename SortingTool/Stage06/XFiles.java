package SortingTool.Stage06;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class XFiles {
	private static String dataType;
	private static String kindOfComparator;
	private static String inputFile;
	private static String outputFile;
	private static Map<String, Integer> dataToAnalyze = new HashMap<>();
	private static Map<String, String> associateMap = new HashMap<>();
	private static List<String> dataInput = new ArrayList<>();
	private static List<String> dataFromFile = new ArrayList<>();
	private static List<String> dataToFile = new ArrayList<>();
	private static int count = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		defineDataType(args);
		readDataFromFile();
		buildAssociateMap();
		buildDataToAnalyze(dataFromFile);
		writeDataToFile();
		sc.close();
	}

	private static void readDataFromFile() {
		File f = new File(inputFile);
		try (Scanner sc = new Scanner(f)) {
			while (sc.hasNextLine()) {
				dataFromFile.add(sc.nextLine());
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}

	private static void writeDataToFile() {
		File f = new File(outputFile);
		try (PrintWriter pr = new PrintWriter(f)) {
			for (String s : dataToFile) {
				pr.println(s);
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}

	private static void buildAssociateMap() {
		associateMap.put("long", "numbers");
		associateMap.put("word", "words");
		associateMap.put("line", "lines");
	}

	private static void defineDataType(String[] args) {
		String data = "-dataType";
		String comparator = "-sortingType";
		String input = "-inputFile";
		String output = "-outputFile";
		List<String> settings = new ArrayList<>();
		settings.add(data);
		settings.add(comparator);
		settings.add(input);
		settings.add(output);
		// by default when key -sortingType is absent
		kindOfComparator = "natural";
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(data) && (i + 1) < args.length) {
				dataType = args[i + 1];
				i++;
				settings.add(dataType);
			}
			if (args[i].equals(comparator)) {
				if (i + 1 < args.length) {
					kindOfComparator = args[i + 1];
					i++;
				} else {
					kindOfComparator = null;
				}
			}
			if (args[i].equals(input) && (i + 1) < args.length) {
				inputFile = args[i + 1];
				i++;
				settings.add(inputFile);
			}
			if (args[i].equals(output) && (i + 1) < args.length) {
				outputFile = args[i + 1];
				i++;
				settings.add(outputFile);
			}
		}
		settings.add(kindOfComparator);

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
			boolean validArguments = settings.contains(args[i]);
			if (!validArguments) {
				System.out.printf("\"%s\" is not a valid parameter. It will be skipped.\n", args[i]);
			}
		}

	}

	private static void buildDataToAnalyze(List<String> dataFromFile) {
		switch (dataType) {
		case "long":
			workWithLong(dataFromFile);
			buildStatisticWithLong(dataType);
			break;
		case "word":
			workWithWord(dataFromFile);
			buildStatisticWithWord(dataType);
			break;
		case "line":
			workWithLine(dataFromFile);
			buildStatisticWithLine(dataType);
			break;
		default:
			System.out.println("Incorrect command line argumetns");
			break;
		}
	}

	private static void workWithLong(List<String> list) {
		for (String s : list) {
			String[] line = s.split("\\s+");
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
		// System.out.printf("Total %s: %d.\n", associateMap.get(dataType), count);
		dataToFile.add(String.format("Total %s: %d.\n", associateMap.get(dataType), count));
		switch (kindOfComparator) {
		case "natural":
			// List<String> lsString = new ArrayList<String>(dataToAnalyze.keySet());
			dataInput.sort((x, y) -> Integer.valueOf(x).compareTo(Integer.valueOf(y)));
			// System.out.print("Sorted data: ");
			dataToFile.add("Sorted data: ");
			dataToFile.addAll(dataInput);
			// dataInput.forEach(x -> System.out.print(x + " "));
			break;
		case "byCount":
			List<Entry<String, Integer>> list = new ArrayList<>(dataToAnalyze.entrySet());
			Comparator<Entry<String, Integer>> cmp1 = (x, y) -> x.getValue().compareTo(y.getValue());
			Comparator<Entry<String, Integer>> cmp2 = (x, y) -> Integer.valueOf(x.getKey())
					.compareTo(Integer.valueOf(y.getKey()));
			list.sort(cmp1.thenComparing(cmp2));
			// print result
			for (Entry<String, Integer> entr : list) {
//				System.out.printf("%s: %d time(s), %d%%\n", entr.getKey(), entr.getValue(),
//						entr.getValue() * 100 / count);
				dataToFile.add(String.format("%s: %d time(s), %d%%\n", entr.getKey(), entr.getValue(),
						entr.getValue() * 100 / count));
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

	private static void workWithWord(List<String> list) {
		for (String s : list) {
			String[] data = s.split("\\s+");
			for (int i = 0; i < data.length; i++) {
				dataToAnalyze.put(data[i], dataToAnalyze.getOrDefault(data[i], 0) + 1);
				dataInput.add(data[i]);
				count++;
			}
		}
	}

	private static void workWithLine(List<String> list) {
		for (String s : list) {
			dataToAnalyze.put(s, dataToAnalyze.getOrDefault(s, 0) + 1);
			dataInput.add(s);
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
