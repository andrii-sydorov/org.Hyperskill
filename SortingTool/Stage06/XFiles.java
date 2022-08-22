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

/**
 * Stage 6/6: X-files
 * Description
 * Sometimes it's useful to read data that is from a file, rather than from the
 * standard input, and write the result to another file instead of printing it
 * to the console. Add this functionality to your program along with the
 * appropriate command-line argument support.
 * 
 * Objectives
 * Update command-line arguments parsing to support the -inputFile and
 * -outputFile arguments.
 * 
 * If -inputFile is provided followed by the file name, read the input data from
 * the file.
 * 
 * If -outputFile is provided followed by the file name, output only the error
 * messages to the console and print the results to the file.
 * 
 * Examples
 * Example 1: input file is defined
 * 
 * java SortingTool -sortingType byCount -inputFile input.txt
 * Example 2: input and output files are defined
 * 
 * java SortingTool -sortingType byCount -inputFile data.dat -outputFile out.txt
 */

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
	private static boolean readFromFile;
	private static boolean writeToFile;
	private static int count = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		defineDataType(args);
		buildAssociateMap();
		if (readFromFile) {
			readDataFromFile();
		}
		buildDataToAnalyze(dataFromFile, sc);
		if (writeToFile) {
			writeDataToFile();
		}
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
				pr.print(s);
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
		// by default when key -sortingType is absent
		kindOfComparator = "natural";
		dataType = "line";
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(data)) {
				if ((i + 1) < args.length) {
					dataType = args[i + 1];
					i++;
					settings.add(dataType);
					settings.add(data);
				} else {
					dataType = null;
				}

			}
			if (args[i].equals(comparator)) {
				if (i + 1 < args.length) {
					kindOfComparator = args[i + 1];
					i++;
					settings.add(kindOfComparator);
					settings.add(comparator);
				} else {
					kindOfComparator = null;
				}
			}
			if (args[i].equals(input) && (i + 1) < args.length) {
				inputFile = args[i + 1];
				i++;
				settings.add(inputFile);
				settings.add(input);
				readFromFile = true;
			}
			if (args[i].equals(output) && (i + 1) < args.length) {
				outputFile = args[i + 1];
				i++;
				settings.add(outputFile);
				settings.add(output);
				writeToFile = true;
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
				dataToFile.add(String.format("\"%s\" is not a valid parameter. It will be skipped.\n", args[i]));
				if (!writeToFile) {
					System.out.printf("\"%s\" is not a valid parameter. It will be skipped.\n", args[i]);
				}
			}
		}

	}

	private static void buildDataToAnalyze(List<String> dataFromFile, Scanner sc) {
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
		if (readFromFile) {
			for (String s : dataFromFile) {
				String[] line = s.split("\\s+");
				for (int i = 0; i < line.length; i++) {
					try {
						long l = Long.valueOf(line[i]);
						dataToAnalyze.put(String.valueOf(l), dataToAnalyze.getOrDefault(String.valueOf(l), 0) + 1);
						dataInput.add(String.valueOf(l));
						count++;
					} catch (NumberFormatException nfe) {
						dataToFile.add(String.format("\"%s\" is not a long. It will be skipped.\n", line[i]));
					}

				}

			}
		} else {
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
	}

	private static void buildStatisticWithLong(String dataType) {
		dataToFile.add(String.format("Total %s: %d.\n", associateMap.get(dataType), count));
		if (!writeToFile) {
			System.out.printf("Total %s: %d.\n", associateMap.get(dataType), count);
		}
		switch (kindOfComparator) {
			case "natural":
				// List<String> lsString = new ArrayList<String>(dataToAnalyze.keySet());
				dataInput.sort((x, y) -> Integer.valueOf(x).compareTo(Integer.valueOf(y)));
				StringBuilder sb = new StringBuilder();
				sb.append("Sorted data: ");
				dataInput.forEach(x -> sb.append(x + " "));
				dataToFile.add(sb.toString());
				if (!writeToFile) {
					System.out.print("Sorted data: ");
					dataInput.forEach(x -> System.out.print(x + " "));
				}
				break;
			case "byCount":
				List<Entry<String, Integer>> list = new ArrayList<>(dataToAnalyze.entrySet());
				Comparator<Entry<String, Integer>> cmp1 = (x, y) -> x.getValue().compareTo(y.getValue());
				Comparator<Entry<String, Integer>> cmp2 = (x, y) -> Integer.valueOf(x.getKey())
						.compareTo(Integer.valueOf(y.getKey()));
				list.sort(cmp1.thenComparing(cmp2));
				// print result
				for (Entry<String, Integer> entr : list) {
					dataToFile.add(String.format("%s: %d time(s), %d%%\n", entr.getKey(), entr.getValue(),
							entr.getValue() * 100 / count));
					if (!writeToFile) {
						System.out.printf("%s: %d time(s), %d%%\n", entr.getKey(), entr.getValue(),
								entr.getValue() * 100 / count);
					}
				}
				break;
			default:
				break;
		}
	}

	private static void buildStatisticWithWord(String dataType) {

		dataToFile.add(String.format("Total %s: %d.\n", associateMap.get(dataType), count));
		if (!writeToFile) {
			System.out.printf("Total %s: %d.\n", associateMap.get(dataType), count);
		}

		switch (kindOfComparator) {
			case "natural":
				// List<String> lsString = new ArrayList<String>(dataToAnalyze.keySet());
				dataInput.sort((x, y) -> Integer.valueOf(x).compareTo(Integer.valueOf(y)));
				StringBuilder sb = new StringBuilder();
				sb.append("Sorted data: ");
				dataInput.forEach(x -> sb.append(x + " "));
				dataToFile.add(sb.toString());
				if (!writeToFile) {
					System.out.print("Sorted data: ");
					dataInput.forEach(x -> System.out.print(x + " "));
				}
				break;
			case "byCount":
				List<Entry<String, Integer>> list = new ArrayList<>(dataToAnalyze.entrySet());
				Comparator<Entry<String, Integer>> cmp1 = (x, y) -> x.getValue().compareTo(y.getValue());
				Comparator<Entry<String, Integer>> cmp2 = (x, y) -> x.getKey().compareTo(y.getKey());
				list.sort(cmp1.thenComparing(cmp2));
				// print result
				for (Entry<String, Integer> entr : list) {
					dataToFile.add(String.format("%s: %d time(s), %d%%\n", entr.getKey(), entr.getValue(),
							entr.getValue() * 100 / count));
					if (!writeToFile) {
						System.out.printf("%s: %d time(s), %d%%\n", entr.getKey(), entr.getValue(),
								entr.getValue() * 100 / count);
					}
				}
				break;
			default:
				break;
		}
	}

	private static void workWithWord(Scanner sc) {
		if (readFromFile) {
			for (String s : dataFromFile) {
				String[] data = s.split("\\s+");
				for (int i = 0; i < data.length; i++) {
					dataToAnalyze.put(data[i], dataToAnalyze.getOrDefault(data[i], 0) + 1);
					dataInput.add(data[i]);
					count++;
				}
			}
		} else {
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
	}

	private static void workWithLine(Scanner sc) {
		if (readFromFile) {
			for (String s : dataFromFile) {
				dataToAnalyze.put(s, dataToAnalyze.getOrDefault(s, 0) + 1);
				dataInput.add(s);
				count++;
			}
		} else {
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
	}

	private static void buildStatisticWithLine(String dataType) {

		dataToFile.add(String.format("Total %s: %d.\n", associateMap.get(dataType), count));
		if (!writeToFile) {
			System.out.printf("Total %s: %d.\n", associateMap.get(dataType), count);
		}

		switch (kindOfComparator) {
			case "natural":

				dataInput.sort((x, y) -> x.compareTo(y));
				StringBuilder sb = new StringBuilder();
				sb.append("Sorted data: ");
				dataInput.forEach(x -> sb.append(x + " "));
				dataToFile.add(sb.toString());
				if (!writeToFile) {
					System.out.println("Sorted data:");
					dataInput.forEach(x -> System.out.println(x));
				}

				break;
			case "byCount":
				List<Entry<String, Integer>> list = new ArrayList<>(dataToAnalyze.entrySet());
				Comparator<Entry<String, Integer>> cmp1 = (x, y) -> x.getValue().compareTo(y.getValue());
				Comparator<Entry<String, Integer>> cmp2 = (x, y) -> x.getKey().compareTo(y.getKey());
				list.sort(cmp1.thenComparing(cmp2));

				for (Entry<String, Integer> entr : list) {
					dataToFile.add(String.format("%s: %d time(s), %d%%\n", entr.getKey(), entr.getValue(),
							entr.getValue() * 100 / count));
					if (!writeToFile) {
						System.out.printf("%s: %d time(s), %d%%\n", entr.getKey(), entr.getValue(),
								entr.getValue() * 100 / count);
					}
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
