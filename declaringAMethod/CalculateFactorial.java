package declaringAMethod;

import java.util.Scanner;

/**
 * Write a method that calculates the factorial of a given number.
 * 
 * The factorial of n is calculated by the product of integer number from 1 to n
 * (inclusive). The factorial of 0 is equal to 1.
 * 
 * Report a typo 
 * Sample Input 1:
 * 
 * 0 
 * 
 * Sample Output 1:
 * 
 * 1 
 * 
 * Sample Input 2:
 * 
 * 5 
 * 
 * Sample Output 2:
 * 
 * 120
 * 
 * @author SMD_ASY
 *
 */

public class CalculateFactorial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter your's value:");
		Scanner sc = new Scanner(System.in);
		long number = Long.valueOf(sc.nextLine().trim());
		sc.close();
		System.out.println(factorial(number));
	}

	public static long factorial(long number) {
		if (number == 0 || number == 1) {
			return 1;
		}
		long pow = 1;
		for (long i = 2; i <= number; i++) {
			pow *= i;
		}
		return pow;
	}

}
