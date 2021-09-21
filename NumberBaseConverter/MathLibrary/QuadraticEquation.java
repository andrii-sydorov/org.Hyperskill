package NumberBaseConverter.MathLibrary;

import java.util.Scanner;
import java.util.Arrays;

/**
 * Quadratic equation 
 * You are given real numbers a, b and c, where a ? 0.
 * 
 * Solve the quadratic equation ax2+bx+c=0 and output all of its roots.
 * 
 * It is guaranteed that the equation always has two roots.
 * 
 * Output the results roots in ascending order. Do not round or format them, the
 * testing system does it automatically.
 * 
 * 
 * Sample Input: 
 * 1 
 * -1 
 * -2
 * 
 * Sample Output: 
 * -1 2
 * 
 * 
 * Sample Input: 
 * 1 
 * -7.5 
 * 3
 * 
 * Sample Output: 
 * 0.423966 7.07603
 * 
 * 
 * Sample Input: 
 * 0.1 
 * -2 
 * 0.999
 * 
 * Sample Output: 
 * 0.51264 19.4874
 * 
 * 
 * Sample Input: 
 * -11 
 * -32 
 * 41
 * 
 * Sample Output: 
 * -3.87177 0.962679
 * 
 * @author SMD_ASY
 *
 */

public class QuadraticEquation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		double a = sc.nextDouble();
		double b = sc.nextDouble();
		double c = sc.nextDouble();
		printRoots(roots(a, b, c));
		sc.close();
	}

	private static double[] roots(double a, double b, double c) {
		double det = calculateDeterminator(a, b, c);
		return new double[] { (-b + det) / (2 * a), (-b - det) / (2 * a) };
	}

	private static void printRoots(double[] roots) {
		Arrays.sort(roots);
		for (int i = 0; i < roots.length; i++) {
			System.out.print(roots[i] + " ");
		}
	}

	private static double calculateDeterminator(double a, double b, double c) {
		return Math.sqrt(Math.pow(b, 2) - 4 * a * c);
	}

}
