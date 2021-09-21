package NumberBaseConverter.ProcessingStrings;

import java.util.Scanner;

/**
 * The lucky ticket 
 * Paul loves to ride public transport and after receiving a
 * ticket, he immediately checks whether he got a lucky one. A ticket is
 * considered a lucky one if the sum of the first three numbers of this ticket
 * matches the sum of the last three numbers.
 * 
 * However, Paul does not count well in his head. That is why he asks you to
 * write a program which will check the equality of the sums and display "Lucky"
 * if the sums match, and "Regular" if the sums differ.
 * 
 * A string of six digits is provided as input to the program.
 * 
 * You need to print out only the word "Lucky" or "Regular" with a capital
 * letter (without quotes).
 * 
 * Hint
 * 
 * 
 * Sample Input: 
 * 090234
 * 
 * Sample Output: 
 * Lucky
 * 
 * 
 * Sample Input: 
 * 123456
 * 
 * Sample Output: 
 * Regular
 * 
 * @author SMD_ASY
 *
 */

public class LuckyTicket {

	public static void main(String[] args) {
		// TODO
		Scanner sc = new Scanner(System.in);
		String data = sc.nextLine();
		sc.close();
		System.out.println(isLucky(data));
	}

	private static String isLucky(String data) {
		int sum1 = 0;
		int sum2 = 0;
		for (int i = 0; i < data.length() / 2; i++) {
			sum1 += Character.getNumericValue(data.charAt(i));
			sum2 += Character.getNumericValue(data.charAt(data.length() - 1 - i));
		}
		return sum1 == sum2 ? "Lucky" : "Regular";
	}

}
