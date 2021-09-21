package NumberBaseConverter.ProcessingStrings;

import java.util.Scanner;

/**
 * Double characters 
 * Write a program that reads a string and then output another
 * string with doubled characters.
 * 
 * 
 * Sample Input: 
 * The
 * 
 * Sample Output: 
 * TThhee
 * 
 * @author SMD_ASY
 *
 */

public class DoubleCharacters {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String data = sc.nextLine();
		sc.close();
		System.out.println(printDoubleString(data));
	}

	private static String printDoubleString(String data) {
		int l = data.length() * 2;
		String ans = "";
		for (int i = 0; i < l; i++) {
			ans += Character.toString(data.charAt(i / 2));
		}
		return ans;
	}

}
