package ReadabilityScore.Stage04;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	private static String[] command = new String[] { "ARI", "FK", "SMOG", "CL", "all" };

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
		System.out.println("Enter the score you want to calculate "
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
		System.out.println();
		calculateIndexes(sc);

	}

	private static void calculateIndexes(Scanner sc, String[] words, String[] sentences,
			int[] syllablesAndPolysyllables, String characters) {
		String line = sc.nextLine();
		switch (line) {
		case "ARI":
			double ari = calculateAri(syllablesAndPolysyllables[0], words.length, sentences.length,
					characters.length());
			printARI(ari);
			break;
		case "FK":
			double fk = calculateFK(words.length, sentences.length, syllablesAndPolysyllables[0]);
			printFK(fk);
			break;
		}
	}

	private static double calculateFK(int words, int sentences, int syllables) {
		return 0.38 * words / sentences + 11.8 * syllables / words - 15.59;
	}

	private static void printFK(double fk) {
		System.out.println("Flesch–Kincaid readability tests: " + fk);
	}

	private static double calculateAri(int Syllables, int words, int sentences, int characters) {
		return 4.71 * (characters * 1.0 / words) + 0.5 * (words * 1.0 / sentences) - 21.43;
	}

	private static void printARI(double ari) {
		System.out.println("Automated Readability Index: " + ari);
	}

	private static String getAgesFromTable(int n) {
		String[] gradeLavel = { "5-6", "6-7", "7-8", "8-9", "9-10", "10-11", "11-12", "12-13", "13-14", "14-15",
				"15-16", "16-17", "17-18", "18-24" };
		return gradeLavel[n];
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
