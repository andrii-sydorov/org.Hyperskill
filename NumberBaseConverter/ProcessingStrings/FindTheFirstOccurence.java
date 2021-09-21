package NumberBaseConverter.ProcessingStrings;

import java.util.Scanner;

/**
 * Find the first occurrence of the word "the" 
 * Write a program that takes a sentence as input and returns the index of the first occurrence of the word
 * "the" (can be part of a word), regardless of the capitalization. If there is no occurrence of it must output -1.
 * 
 * For instance, if the sentence is “The apple is red.” the output should be 0,
 * if the sentence is “I ate the red apple.” the output should be 6, and if the
 * sentence is “I love apples.” the output should be -1.
 * 
 * Note, the and The are equal.
 * 
 * 
 * Sample Input: 
 * The apple is red.
 * 
 * Sample Output: 
 * 0
 * 
 * 
 * Sample Input: 
 * I ate the red apple.
 * 
 * Sample Output: 
 * 6
 * 
 * @author SMD_ASY
 *
 */

public class FindTheFirstOccurence {

	public static final String[] ar = { "The", "the" };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String data = sc.nextLine();
		sc.close();
		System.out.println(firstOccurrenceOfTheWorld(data));
	}

	private static int firstOccurrenceOfTheWorld(String data) {
		for (int i = 0; i < ar.length; i++) {
			if (data.contains(ar[i])) {
				return data.indexOf(ar[i]);
			}
		}
		return -1;
	}

}
