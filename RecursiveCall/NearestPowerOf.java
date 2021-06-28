package RecursiveCall;

import java.util.Scanner;

/**
 * Given the following recursive method.
 * 
 * public static long method(long n) { 
 * if (n == 1) { 
 * return 0; 
 * } 
 * return method(n / 2) + 1; 
 * }
 * 
 * 
 * It takes a long number n >= 1 and returns a long result.
 * 
 * Select all correct results. Report a typo Select one or more options from the
 * list 
 * method(1) returns 0 
 * method(129) returns 7 
 * method(128) returns 7 
 * Correct. Great job, keep at it!
 * 
 * @author SMD_ASY
 *
 */

public class NearestPowerOf {

	private static final int power = 2;

	public static void main(String[] args) {
		System.out.println("Enter your number:");
		Scanner sc = new Scanner(System.in);
		int n = 0;
		try {
			n = Integer.valueOf(sc.nextLine());
		} catch (myException my) {
			my.setMessage("Should be a number");
			System.out.println(my.getMessage());
			System.exit(0);
		}
		sc.close();
		System.out.println(nearestPowerOfWithRecursion(n));
		System.out.println(nearestPowerOfWithLoops(n));
	}

	public static long nearestPowerOfWithRecursion(int n) {
		if (n == 1) {
			return 0;
		}
		return nearestPowerOfWithRecursion(n / power) + 1;
	}

	public static long nearestPowerOfWithLoops(int n) {
		long ans = 0;
		while (n != 1) {
			n /= power;
			ans++;
		}
		return ans;
	}

	static class myException extends NumberFormatException {

		private String message;

		public myException() {
			super();
		}

		public myException(String message) {
			super(message);
		}

		public void setMessage(String message) {
			this.message = message;
		}

		@Override
		public String getMessage() {
			return this.message;
		}

	}

}
