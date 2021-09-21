package NumberBaseConverter.ProcessingStrings;

import java.util.Scanner;

/**
 * Cutting out the middle of a string 
 * Write a program that reads a string, and then outputs the string without its middle character when the length is odd,
 * and without the middle 2 characters when the length is even.
 * 
 * Hint
 * 
 * 
 * Sample Input: 
 * Hello
 * 
 * Sample Output: 
 * Helo
 * 
 * 
 * Sample Input: 
 * abcd
 * 
 * Sample Output: 
 * ad
 * 
 * @author SMD_ASY
 *
 */

public class CuttingTheMiddleOfTheStrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String data = sc.nextLine();
		sc.close();
		System.out.println(cutMiddleOfTheString(data));
	}

	private static String cutMiddleOfTheString(String data) {
		return data.length() % 2 != 0 ? data.substring(0, data.length() / 2) + data.substring(data.length() / 2 + 1)
				: data.substring(0, data.length() / 2 - 1) + data.substring(data.length() / 2 + 1);
	}

}
