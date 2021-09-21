package FloatingDataTypes;

import java.util.Locale;
import java.util.Scanner;

/**
 * Write a program which reads a double value x and evaluates the result of
 * x^3 + x^2 + x + 1 
 * 
 * Output data format
 * 
 * The double result of the expression. Report a typo
 * 
 * Sample Input 1:
 * 
 * 22.5
 * 
 * Sample Output 1:
 * 
 * 11920.375
 * 
 * @author SMD_ASY
 *
 */

public class EvaluateExpression {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in).useLocale(Locale.US);
		System.out.println("Enter your x:");
		double x = sc.nextDouble();
		sc.close();
		final int n = 3;
		double sum = 0;
		for (int i = n; i >= 0; i--) {
			sum += Math.pow(x, i);
		}
		System.out.println(sum);
	}

}
