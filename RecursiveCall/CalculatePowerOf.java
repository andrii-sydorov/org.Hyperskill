package RecursiveCall;

import java.util.Scanner;

/**
 * Given a recursive method:
 * 
 * public static int method(int n) { 
 * if (n == 0) { 
 * return 1; 
 * } else { 
 * return 2 * method(n - 1); 
 * } 
 * }
 * 
 * 
 * Enter the result of the following invocation:
 * 
 * method(8);
 * 
 * @author SMD_ASY
 *
 */

public class CalculatePowerOf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the power of two:");
		int n = Integer.valueOf(sc.nextLine());
		System.out.println(TwoInPowerOf(n));
		sc.close();
	}

	public static int TwoInPowerOf(int n) {
		if (n == 0) {
			return 1;
		} else {
			return 2 * TwoInPowerOf(n - 1);
		}
	}

}
