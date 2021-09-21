package NumberBaseConverter.MathLibrary;

import java.util.Scanner;
import java.util.Arrays;

/**
 * The angle between vectors 
 * You are given two 2D vectors. Find the angle (in degrees) between them.
 * 
 * If you don't know how to find the angle, see here:
 * http://www.wikihow.com/Find-the-Angle-Between-Two-Vectors.
 * 
 * Input data format
 * 
 * The first line contains two components of the first vector; the second line
 * contains two components of the second vector. Components in one line are
 * separated by space.
 * 
 * Output data format
 * 
 * One double value: an angle between two vectors. The result can have an error
 * of less than 1e-8.
 * 
 * 
 * Sample Input: 
 * 1 3 
 * 4 2
 * 
 * Sample Output: 
 * 45
 * 
 * 
 * Sample Input: 
 * 0 4 
 * 0 4
 * 
 * Sample Output: 
 * 0
 * 
 * @author SMD_ASY
 *
 */

public class AnglesBeetwenVectors {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// put your code here
		Scanner sc = new Scanner(System.in);
		String[] firstVector = sc.nextLine().split(" ");
		String[] secondVector = sc.nextLine().split(" ");
		sc.close();
		double[] first = Arrays.stream(firstVector).mapToDouble(Double::valueOf).toArray();
		double[] second = Arrays.stream(secondVector).mapToDouble(Double::valueOf).toArray();
		double cos = calculateCos(first, second);
		System.out.println(Math.toDegrees(Math.acos(cos)));
	}

	private static double calculateCos(double[] first, double[] second) {
		double numerator = calculateNumerator(first, second);
		double denominator = calculateDenominator(first, second);
		return numerator / denominator;
	}

	private static double calculateNumerator(double[] first, double[] second) {
		double numerator = 0;
		for (int i = 0; i < first.length; i++) {
			numerator += first[i] * second[i];
		}
		return numerator;
	}

	private static double calculateDenominator(double[] first, double[] second) {
		double firstModule = calculateModule(first);
		double secondModule = calculateModule(second);
		return firstModule * secondModule;
	}

	private static double calculateModule(double[] array) {
		double sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += Math.pow(array[i], 2);
		}
		return Math.sqrt(sum);
	}
}
