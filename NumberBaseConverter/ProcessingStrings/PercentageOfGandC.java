package NumberBaseConverter.ProcessingStrings;

import java.util.Scanner;

/**
 * The percentage of G and C characters 
 * GC-content is an important feature of the genome sequences and is defined as the percentage ratio of the sum of all
 * guanines and cytosines to the overall number of nucleic bases in the genome sequence.
 * 
 * Write a program, which calculates the percentage of G characters (guanine)
 * and C characters (cytosine) in the entered string. Your program should be
 * case independent.
 * 
 * For example, in the string "acggtgttat" the percentage of characters G and C
 * equals to 410⋅100=40.0, where 4 is the number of symbols G and C, and 10 is
 * the length of the string.
 * 
 * Note, answer should be double.
 * 
 * int i = …, j = …; 
 * double result = i / j; // not correct double result =
 * (double) i / j // correct 
 * Remember: characters are case-insensitive(g equals G and c equals C)
 * 
 * Input data format
 * 
 * The single input line contains a sequence.
 * 
 * Output data format
 * 
 * The percentage of G and C characters as a double. Do not round/format the
 * value, output it as is.
 * 
 * 
 * Sample Input: 
 * acggtgttat
 * 
 * Sample Output: 
 * 40.0
 * 
 * @author SMD_ASY
 *
 */

public class PercentageOfGandC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String data = sc.nextLine();
		System.out.println(percentageOfCG(data));
	}

	private static double percentageOfCG(String data) {
		String gc = "cgCG";
		int sum = 0;
		for (int i = 0; i < data.length(); i++) {
			if (gc.contains(Character.toString(data.charAt(i)))) {
				sum++;
			}
		}
		return (double) sum / data.length() * 100;
	}

}
