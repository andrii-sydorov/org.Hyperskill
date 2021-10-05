package StringBuilder;

import java.util.Scanner;

/**
 * Implement a method to concatenate all strings from the given array to a
 * single long string. You must skip all digits inside the input strings.
 * 
 * Use StringBuilder to solve the problem, because the input array can contain a
 * huge number of strings. Report a typo
 * 
 * Sample Input 1:
 * 
 * T7est i1nput
 * 
 * Sample Output 1:
 * 
 * Testinput
 * 
 * @author ASY
 *
 */

public class ConcatenateStringsProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String[] strings = sc.nextLine().split("\\s+");
		String result = concatenateStringsWithoutDigits(strings);
		sc.close();
		System.out.println(result);
	}

	public static String concatenateStringsWithoutDigits(String[] strings) {
		StringBuilder sb = new StringBuilder();
		for (String s : strings) {
			sb.append(s.replaceAll("[0-9]", ""));
		}
		return sb.toString();
	}

}
