package ReadabilityScore.Stage04;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	private static String[] command = new String[] { "ARI", "FK", "SMOG", "CL", "all" };
	private static List<Integer> ls = new ArrayList<>();
	private static final String prefix = "-year-olds";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String data = null;
		for (int i = 0; i < args.length; i++) {
			data = readAllLines(args[i]);
			printText(data);
			printSettings(data);
		}
	}

	private static void printText(String data) {
		System.out.println("The text is:");
		System.out.println(data + "\n");
	}

	private static void printRequest() {
		System.out.print("Enter the score you want to calculate "
				+ Arrays.toString(command).replace("[", "(").replace("]", ")") + ":");
	}

	private static void printSettings(String data) {

		String[] sentences = data.split("[\\.?!]");
		String[] words = data.replaceAll(",", "").replaceAll("[\\W]", " ").split("\\s+");
		String characters = data.replaceAll("\\n", "").replaceAll("\\t", "").replaceAll(" ", "");
		int[] syllablesAndPolysyllables = calculateSyllablesAndPollysyllables(words);
		System.out.println("Words: " + words.length);
		System.out.println("Sentences: " + sentences.length);
		System.out.println("Characters: " + characters.length());
		System.out.println("Syllables: " + syllablesAndPolysyllables[0]);
		System.out.println("Polysyllables: " + syllablesAndPolysyllables[1]);
		printRequest();
		calculateIndexes(sc, words, sentences, syllablesAndPolysyllables, characters);
		System.out.println();
		calculateAverageYears(ls);
	}

	private static void calculateAverageYears(List<Integer> ls) {
		double sum = 0;
		for (int i = 0; i < ls.size(); i++) {
			sum += ls.get(i);
		}
		System.out.println("This text should be understood in average by " + 1.0 * sum / ls.size() + prefix);
	}

	private static void calculateIndexes(Scanner sc, String[] words, String[] sentences,
			int[] syllablesAndPolysyllables, String characters) {
		String line = sc.nextLine();
		System.out.println();
		switch (line) {
		case "ARI":
			makeARI(words, sentences, syllablesAndPolysyllables, characters);
			break;
		case "FK":
			makeFK(words, sentences, syllablesAndPolysyllables);
			break;
		case "SMOG":
			makeSMOG(sentences, syllablesAndPolysyllables);
			break;
		case "CL":
			makeCL(words, sentences, characters);
			break;
		case "all":
			makeARI(words, sentences, syllablesAndPolysyllables, characters);
			makeFK(words, sentences, syllablesAndPolysyllables);
			makeSMOG(sentences, syllablesAndPolysyllables);
			makeCL(words, sentences, characters);
			break;
		}
	}

	private static void makeCL(String[] words, String[] sentences, String characters) {
		double cl = calculateCL(words.length, sentences.length, characters.length());
		printCL(cl);
	}

	private static void makeSMOG(String[] sentences, int[] syllablesAndPolysyllables) {
		double smog = calculateSMOG(syllablesAndPolysyllables[1], sentences.length);
		printSMOG(smog);
	}

	private static void makeFK(String[] words, String[] sentences, int[] syllablesAndPolysyllables) {
		double fk = calculateFK(words.length, sentences.length, syllablesAndPolysyllables[0]);
		printFK(fk);
	}

	private static void makeARI(String[] words, String[] sentences, int[] syllablesAndPolysyllables,
			String characters) {
		double ari = calculateAri(syllablesAndPolysyllables[0], words.length, sentences.length, characters.length());
		printARI(ari);
	}

	private static double calculateCL(int words, int sentences, int characters) {
		double L = 1.0 * characters / words * 100;
		double S = 1.0 * sentences / words * 100;
		return 0.0588 * L - 0.296 * S - 15.8;
	}

	private static void printCL(double cl) {
		String limit = getAgesFromTable((int) Math.ceil(cl));
		int year = getMaximumIntegerFromStrings(limit);
		System.out.println(String.format(Locale.US, "Coleman–Liau index: %.2f", cl) + " (about " + year + prefix + ")");
	}

	private static double calculateSMOG(int polySyllables, int sentences) {
		return 1.043 * Math.sqrt(1.0 * polySyllables * 30 / sentences) + 3.1291;
	}

	private static void printSMOG(double smog) {
		String limit = getAgesFromTable((int) Math.ceil(smog));
		int year = getMaximumIntegerFromStrings(limit);
		System.out.println(String.format(Locale.US, "Simple Measure of Gobbledygook: %.2f", smog) + " (about " + year
				+ prefix + ")");
	}

	private static int getMaximumIntegerFromStrings(String st) {
		int[] ar = Arrays.stream(st.split("-")).mapToInt(Integer::valueOf).toArray();
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < ar.length; i++) {
			if (max < ar[i]) {
				max = ar[i];
			}
		}
		ls.add(max);
		return max;
	}

	private static double calculateFK(int words, int sentences, int syllables) {
		return 0.39 * words / sentences + 11.8 * syllables / words - 15.59;
	}

	private static void printFK(double fk) {
		String limit = getAgesFromTable((int) Math.ceil(fk));
		int year = getMaximumIntegerFromStrings(limit);
		System.out.println(String.format(Locale.US, "Flesch–Kincaid readability tests: %.2f", fk) + " (about " + year
				+ prefix + ")");
	}

	private static double calculateAri(int Syllables, int words, int sentences, int characters) {
		return 4.71 * (characters * 1.0 / words) + 0.5 * (words * 1.0 / sentences) - 21.43;
	}

	private static void printARI(double ari) {
		String limit = getAgesFromTable((int) Math.ceil(ari));
		int year = getMaximumIntegerFromStrings(limit);
		System.out.println(
				String.format(Locale.US, "Automated Readability Index: %.2f", ari) + " (about " + year + prefix + ")");
	}

	private static String getAgesFromTable(int n) {
		String[] gradeLevel = { "5-6", "6-7", "7-8", "8-9", "9-10", "10-11", "11-12", "12-13", "13-14", "14-15",
				"15-16", "16-17", "17-18", "18-24" };
		if (n >= gradeLevel.length) {
			return gradeLevel[gradeLevel.length - 1];
		}
		if (n > 10) {
			return gradeLevel[n];
		}
		return gradeLevel[n - 1];
	}

	private static String readAllLines(String fileName) throws IOException {
		return new String(Files.readAllBytes(Paths.get(fileName)));
	}

	private static int[] calculateSyllablesAndPollysyllables(String[] words) {
		int countSyllables = 0;
		int countPollySyllables = 0;

		for (int i = 0; i < words.length; i++) {
			if (words[i].length() == 1) {
				continue;
			}
			int inc = countSyllables(words[i]);
			countSyllables += inc;
			if (inc > 2) {
				countPollySyllables++;
			}
		}
		return new int[] { countSyllables, countPollySyllables };
	}

	private static int countSyllables(String words) {
		String vowels = "aeoiuyAEOIUY";
		int ans = 0;
		for (int j = 0; j < words.length(); j++) {
			if (vowels.indexOf(Character.toString(words.charAt(j))) != -1) {
				if (j == 0) {
					ans++;
					continue;
				}
				if (vowels.indexOf(Character.toString(words.charAt(j - 1))) == -1) {
					ans++;
					continue;
				}
			}
		}
		if (words.endsWith("e")) {
			ans--;
		}
		return ans <= 0 ? 1 : ans;
	}

}
