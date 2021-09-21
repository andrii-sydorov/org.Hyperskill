package NumberBaseConverter.ProcessingStrings;

import java.util.Scanner;

/**
 * Piece of alphabet 
 * Write a program that reads a string and outputs "true" only
 * when the letters of this string form a substring of the ordered English
 * alphabet, for example, "abc", "xy", "pqrst".
 * 
 * Otherwise, it should print out "false".
 * 
 * Note: string can consist of a single character. Assume an empty string as an
 * alphabet substring.
 * 
 * 
 * Sample Input: 
 * abc
 * 
 * Sample Output: 
 * true
 * 
 * 
 * Sample Input: 
 * bce
 * 
 * Sample Output: 
 * false
 * 
 * @author SMD_ASY
 *
 */

public class PieceOfAlphabet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String data = sc.nextLine();
		sc.close();
		System.out.println(pieceOfAlphabet(data));
	}

	public static String pieceOfAlphabet(String data) {
		char ch = data.charAt(0);
		for (int i = 0; i < data.length(); i++) {
			if (ch != data.charAt(i)) {
				return "false";
			}
			ch++;
		}
		return "true";
	}

}
