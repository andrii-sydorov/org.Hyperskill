package RecursiveCall;

import java.util.Scanner;

/**
 * Given a recursive method:
 * 
 * public static String method(int n) { 
 * if (n == 0 || n == 1) { 
 * return String.valueOf(n); 
 * } 
 * return method(n / 2) + String.valueOf(n % 2); 
 * }
 * 
 * 
 * Assume, n >= 0.
 * 
 * What does the resulting string contain after calling the method? Report a
 * typo Select one option from the list 
 * It contains the binary representation of the given number.
 * 
 * @author SMD_ASY
 *
 */

public class BinaryRepresentation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter your's number:");
		Scanner sc = new Scanner(System.in);
		int n = Integer.valueOf(sc.nextLine());
		sc.close();
		System.out.println(binaryRepresentWithRecursive(n));
		System.out.println(binaryRepresentWithLoops(n));
	}

	public static String binaryRepresentWithLoops(int n) {
		// TODO Auto-generated method stub
		String ans = "";
		while (n != 0) {
			if (n % 2 == 0) {
				ans += "0";
			} else {
				ans += "1";
			}
			n /= 2;
		}
		return ans;
	}

	public static String binaryRepresentWithRecursive(int n) {
		if (n == 0 || n == 1) {
			return String.valueOf(n);
		}
		return binaryRepresentWithRecursive(n / 2) + String.valueOf(n % 2);
	}

}
