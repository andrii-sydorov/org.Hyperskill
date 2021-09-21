package NumberBaseConverter.MathLibrary;

import java.util.Scanner;

/**
 * Pow You are given two floating-point numbers: a and b.
 * 
 * Calculate and output the value of the expression ab.
 * 
 * Note: use double variables for a and b.
 * 
 * Input data format:
 * 
 * Two floating-point numbers in one line.
 * 
 * Output data format:
 * 
 * The result of the expression.
 * 
 * 
 * Sample Input: 
 * 2 3
 * 
 * Sample Output: 
 * 8.0
 * 
 * 
 * Sample Input: 
 * 2 
 * 1.02
 * 
 * Sample Output: 
 * 2.027918959580058
 * 
 * @author SMD_ASY
 *
 */

public class Power {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// put your code here
		Scanner sc = new Scanner(System.in);
		double a = sc.nextDouble();
		double b = sc.nextDouble();
		sc.close();
		System.out.println(Math.pow(a, b));
	}

}
