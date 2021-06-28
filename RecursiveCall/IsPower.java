package RecursiveCall;

import java.util.Scanner;

/**
 * What does the following method do?
 * 
 * public static boolean method(int n) { 
 * if (n == 0) { 
 * return false; 
 * } else if (n == 1) { 
 * return true; 
 * } else if (n % 3 != 0) { 
 * return false; 
 * } else {
 * return method(n / 3); 
 * } 
 * }
 * 
 * Report a typo Select one option from the list It returns true if n is a power
 * of 3, otherwise - false.
 * 
 * @author SMD_ASY
 *
 */

public class IsPower {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your's number");
		int n = 0;
		try {
			n = Integer.valueOf(sc.nextLine());
		} catch (NumberFormatException nfe) {
			System.out.println("The data must be a number");
		}
		System.out.println(isPowerOf(n));
	}

	public static boolean isPowerOf(int n) {
		if (n == 0) {
			return false;
		} else if (n == 1) {
			return true;
		} else if (n % 3 != 0) {
			return false;
		} else {
			return isPowerOf(n / 3);
		}
	}

}
