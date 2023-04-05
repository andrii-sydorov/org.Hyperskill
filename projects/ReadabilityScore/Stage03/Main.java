package projects.ReadabilityScore.Stage03;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.text.NumberFormat;

/**
 * Description In this stage, you will program the Automated readability index.
 * It was introduced in 1968 and a lot of research works rely on this. The index
 * is calculated by the following formula:
 * 
 * score = 4.71 \times \dfrac{characters}{words} + 0.5 \times
 * \dfrac{words}{sentences} - 21.43score=4.71× words characters ​ +0.5×
 * sentences words ​ −21.43
 * 
 * You can look at different ages corresponding to the different scores by the
 * table in this article.
 * 
 * Also, your program should read a file instead of typing a text manually. You
 * should pass the filename through the command line arguments.
 * 
 * The program should output the score itself and an approximate age needed to
 * comprehend the text.
 * 
 * Use the appropriate rounding function to calculate the score as integer.
 * 
 * You should also print how many characters, words, and sentences the text has.
 * 
 * The number of characters is any visible symbol (so, in the real text it's
 * everything except space, newline "\n" and tab "\t").
 * 
 * Notice, that the text can contain multiple lines, not just a single line like
 * in the previous stages. You should analyze all the lines.
 * 
 * Examples The greater-than symbol followed by a space (> ) represents the user
 * input. Note that it's not part of the input.
 * 
 * Example 1
 * 
 * > java Main in.txt 
 * The text is: 
 * Readability is the ease with which a reader can understand a written text. 
 * In natural language, the readability of text depends on its content and its 
 * presentation. Researchers have used various factors to measure readability. 
 * Readability is more than simply legibility, which is a measure of how easily 
 * a reader can distinguish individual letters or characters from each other. 
 * Higher readability eases reading effort and speed for any reader, but it is 
 * especially important for those who do not have high reading comprehension. 
 * In readers with poor reading comprehension, raising the readability level of 
 * a text from mediocre to good can make the difference between success and failure.
 * 
 * Words: 108 
 * Sentences: 6 
 * Characters: 580 
 * The score is: 12.86 
 * This text should be understood by 18-24-year-olds. 
 * 
 * Example 2
 * 
 * > java Main in.txt 
 * The text is: This is the page of the Simple English Wikipedia. A place where 
 * people work together to write encyclopedias in different languages. That includes 
 * children and adults who are learning English. There are 142,262 articles on the 
 * Simple English Wikipedia. All of the pages are free to use. They have all been 
 * published under both the Creative Commons License 3 and the GNU Free Documentation 
 * License. You can help here! You may change these pages and make new pages. Read the 
 * help pages and other good pages to learn how to write pages here. You may ask questions
 * at Simple talk.
 * 
 * Words: 100 
 * Sentences: 10 
 * Characters: 476 
 * The score is: 5.98 
 * This text should be understood by 11-12-year-olds.
 * 
 * @author SMD_ASY
 *
 */

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

		String[] sentences = data.split("[\\.?!]");
		String[] words = data.replaceAll(",", "").replaceAll("[\\W]", " ").split("\\s+");
		String characters = data.replaceAll("\\n", "").replaceAll("\\t", "").replaceAll(" ", "");
		System.out.println("Words: " + words.length);
		System.out.println("Sentences: " + sentences.length);
		System.out.println("Characters: " + characters.length());
		double score = 4.71 * (characters.length() * 1.0 / words.length) + 0.5 * (words.length * 1.0 / sentences.length)
				- 21.43;
		NumberFormat nfe = NumberFormat.getInstance();
		nfe.setMaximumFractionDigits(2);
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

}
