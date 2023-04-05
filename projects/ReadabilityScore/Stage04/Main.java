package projects.ReadabilityScore.Stage04;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;

/**
 * Description
 * 
 * In this stage, you should implement various other scientific approaches to
 * calculate a readability score.
 * 
 * Take a look at different ages and corresponding scores in the table in this
 * article. This table is suitable for all the algorithms described in this
 * stage. To calculate the age use the upper bound of the range. For example, if
 * the range is 12-13-year-olds then it's upper bound is 13-year-olds.
 * 
 * The first algorithm is Flesch–Kincaid readability tests. First, you need to
 * create a method that calculates the number of syllables in a word. The
 * formula is given below. You can find more information here. You can use the
 * second formula to calculate the index; it allows you to easily calculate the
 * age of a person using the same table from the Automated Readability Index.
 * 
 * score = 0.39 * words/sentences + 11.8 * syllables/words - 15.59
 * 
 * The second one is SMOG index. It stands for Simple Measure of Gobbledygook.
 * To calculate it, you need to count the number of polysyllables which is the
 * number of words with more than 2 syllables. The formula is shown below. You
 * can find out more here. The Wikipedia page says that at least 30 sentences
 * are required for this index to work properly. Don't pay attention to this,
 * just keep it in mind when you use this index in real life. As in the previous
 * example, the grade level is calculated here, so to get the age of a person
 * you need to use the table from the first link.
 * 
 * score = 1.043 * sqrt(polysyllables * 30/sentences) + 3.1291​
 * 
 * The next one is Coleman–Liau index. The formula is given below. For more
 * information read this. L is the average number of characters per 100 words
 * and S is the average number of sentences per 100 words. Like all other
 * indices, the output is a person's grade level. Like all other indices, the
 * result is a minimum grade level required to understand this text.
 * 
 * score = 0.0588 * L - 0.296 * S - 15.8
 * 
 * So, in this stage, you should program all three approaches. Don't forget
 * about the Automated readability index! Also, there should be an option to
 * choose all methods at the same time.
 * 
 * To count the number of syllables you should use letters a, e, i, o, u, y as
 * vowels. You can see here examples and intricacies with determining vowels in
 * a word with 100% accuracy. So, let's use the following 4 rules:
 * 
 * 1. Count the number of vowels in the word. 
 * 2. Do not count double-vowels (for example, "rain" has 2 vowels but only 1 syllable). 
 * 3. If the last letter in the word is 'e' do not count it as a vowel (for example, "side" 
 * has 1 syllable). 
 * 4. If at the end it turns out that the word contains 0 vowels,
 * then consider this word as a 1-syllable one. 
 * 
 * Example
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * > java Main in.txt 
 * The text is: 
 * This is the front page of the Simple English
 * Wikipedia. Wikipedias are places where people work together to write
 * encyclopedias in different languages. We use Simple English words and grammar
 * here. The Simple English Wikipedia is for everyone! That includes children
 * and adults who are learning English. There are 142,262 articles on the Simple
 * English Wikipedia. All of the pages are free to use. They have all been
 * published under both the Creative Commons License and the GNU Free
 * Documentation License. You can help here! You may change these pages and make
 * new pages. Read the help pages and other good pages to learn how to write
 * pages here. If you need help, you may ask questions at Simple talk. Use Basic
 * English vocabulary and shorter sentences. This allows people to understand
 * normally complex terms or phrases.
 * 
 * Words: 137 
 * Sentences: 14 
 * Characters: 687 
 * Syllables: 210 
 * Polysyllables: 17
 * Enter the score you want to calculate (ARI, FK, SMOG, CL, all): all
 * 
 * Automated Readability Index: 7.08 (about 13-year-olds). 
 * Flesch–Kincaid readability tests: 6.31 (about 12-year-olds). 
 * Simple Measure of Gobbledygook: 9.42 (about 15-year-olds). 
 * Coleman–Liau index: 10.66 (about 17-year-olds).
 * 
 * This text should be understood in average by 14.25-year-olds.
 * 
 * @author ASY
 *
 */

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
