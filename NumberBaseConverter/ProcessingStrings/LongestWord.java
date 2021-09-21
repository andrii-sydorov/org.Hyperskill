package NumberBaseConverter.ProcessingStrings;

import java.util.Scanner;

/**
 * The longest word 
 * In the given string find the first longest word and output it.
 * 
 * Input data
 * 
 * Given a string in a single line. Words in the string are separated by a
 * single space.
 * 
 * Output data
 * 
 * Output the longest word. If there are several such words, you should output
 * the one, which occurs earlier.
 * 
 * 
 * Sample Input: 
 * one two three four five six
 * 
 * Sample Output: 
 * three
 * 
 * @author SMD_ASY
 *
 */

public class LongestWord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String[] data = sc.nextLine().split(" ");
		sc.close();
		System.out.println(longestWord(data));
	}

	private static String longestWord(String[] data) {
		String temp = data[0];
		for (int i = 1; i < data.length; i++) {
			if (temp.length() < data[i].length()) {
				temp = data[i];
			}
		}
		return temp;
	}

}
