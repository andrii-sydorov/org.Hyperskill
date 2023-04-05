package projects.ReadabilityScore.Stage02;

import java.util.Scanner;

/**
 * Description
 * 
 * However, a real text may be pretty long and still easy to read, right? There
 * needs to be another approach. How about calculating the number of words in
 * each sentence? Clearly, if each sentence of a text contains a lot of words,
 * this text is hard to read.
 * 
 * Suppose that if the text contains sentences that on average have more than 10
 * words per sentence, this text is hard to read. Otherwise, this text is easy
 * to read. Don't worry, we will consider more scientific ways in the next
 * stages.
 * 
 * The input contains a single line of text. Output "HARD" if the text is hard
 * to read and "EASY" if the text is easy to read.
 * 
 * For example, the first example contains two sentences with 6 and 10 words
 * (numbers also count as words) so the average is 8, and this is less than 10.
 * In the second example, there are also 2 sentences but with 6 and 16 words, so
 * the average is 11 and this is more than 10. If the average is equal to 10,
 * the text is still considered easy to read.
 * 
 * Don't forget that sentences can end with a full stop as well as with an
 * exclamation mark and a question mark. But the last sentence can be with or
 * without a punctuation mark at the end. 
 * 
 * Examples
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1
 * 
 * > This text is simple to read! It has on average less than 10 words per
 * sentence. 
 * 
 * EASY
 * 
 * Example 2
 * 
 * > This text is hard to read. It contains a lot of sentences as well as a lot
 * of words in each sentence 
 * 
 * HARD
 * 
 * @author ASY
 *
 */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the tesx: ");
		String text = sc.nextLine();
		sc.close();
		System.out.println(difficultyOfText(text));
	}

	private static String difficultyOfText(String text) {
		String[] str = text.split("[\\.!?] ");
		int sum = 0;
		for (int i = 0; i < str.length; i++) {
			sum += str[i].split("\\s+").length;
		}
		int awg = sum / str.length;
		return awg > 10 ? "HARD" : "EASY";
	}

}
