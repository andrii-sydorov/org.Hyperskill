package NumberBaseConverter.MathLibrary;

import java.util.Scanner;

/**
 * Heron's formula Many years ago when Paul went to school, he did not like the
 * Heron's formula to calculate the area of a triangle, because he considered it
 * very complex. Once he decided to help all school students and write and
 * distribute a program calculating the area of a triangle by its three sides.
 * 
 * However, there was a problem: as Paul did not like the formula, he did not
 * memorize it. Help him finish this act of kindness and write the program
 * calculating the area of a triangle with the known length of its sides, in
 * accordance with Heron's formula:
 * 
 * S=p(p−a)(p−b)(p−c)−−−−−−−−−−−−−−−−−√ where p=a+b+c2 is the half-perimeter of
 * the triangle. In the input, the program has integers, and the output should
 * be a real number corresponding to the area of the triangle.
 * 
 * 
 * Sample Input: 
 * 3 
 * 4 
 * 5
 * 
 * Sample Output:
 * 6.0
 * 
 * @author SMD_ASY
 *
 */

public class HeronsFormula {
	// TODO Auto-generated method stub
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double a = sc.nextDouble();
		double b = sc.nextDouble();
		double c = sc.nextDouble();
		double p = sum(a, b, c) / 2;
		double S = heronFormula(p, a, b, c);
		System.out.println(S);
	}

	private static double sum(double... values) {
		double sum = 0;
		for (int i = 0; i < values.length; i++) {
			sum += values[i];
		}
		return sum;
	}

	private static double heronFormula(double p, double... values) {
		double[] dif = new double[values.length];
		for (int i = 0; i < values.length; i++) {
			dif[i] = p - values[i];
		}
		double pow = 1;
		for (int i = 0; i < values.length; i++) {
			pow *= dif[i];
		}
		return Math.sqrt(p * pow);
	}
}
