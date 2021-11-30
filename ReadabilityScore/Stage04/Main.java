package ReadabilityScore.Stage04;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String data = null;
		for (int i = 0; i < args.length; i++) {
			data = readAllLines(args[i]);
			printText(data);
			printSettings(data);
			System.out.println();
		}
	}

	private static void printText(String data) {
		System.out.println("The text is:");
		System.out.println(data + "\n");
	}

	private static void printSettings(String data) {

		String[] sentences = data.split("[.?!]");
		String[] words = data.replaceAll(",", "").replaceAll("[\\W]", " ").split("\\s+");
		String characters = data.replaceAll("\\n", "").replaceAll("\\t", "").replaceAll(" ", "");
		int[] syllables = calculateSyllablesAndPollysyllables(words);
		System.out.println("Words: " + words.length);
		System.out.println("Sentences: " + sentences.length);
		System.out.println("Characters: " + characters.length());
		System.out.println("Syllables: " + syllables[0]);
		System.out.println("Polysyllables: " + syllables[1]);
		double score = 4.71 * (characters.length() * 1.0 / words.length) + 0.5 * (words.length * 1.0 / sentences.length)
				- 21.43;
		int n = (int) Math.ceil(score);
		// System.out.printf("The score is: %.2f \n", score);
		String temp = String.valueOf(score);
		System.out.println("The score is: " + temp.substring(0, temp.indexOf(".") + 3));
		System.out.println("This text should be understood by " + getAgesFromTable(n) + "-year-olds.");

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
		String vowels = "ayueoiAYUEOI";
		int countSyllables = 0;
		int countPolysyllables = 0;
		for (int i = 0; i < words.length; i++) {
			if (words[i].length() == 1) {
				continue;
			}
			int countOfSyllables = 0;
			for(int j = 0; j < words[i].length(); j++) {
				if (vowels.indexOf(Character.toString(words[i].charAt(j))) == -1) {
					countSyllables++;
				}
				if (vowels.indexOf(Character.toString(words[i].charAt(j))) != -1) {
					countSyllables++;
					countOfSyllables++;
				}
			}
			if (countOfSyllables >= 2) {
				countPolysyllables++;
			}
		}
		return new int[] {countSyllables, countPolysyllables};
	}

}
