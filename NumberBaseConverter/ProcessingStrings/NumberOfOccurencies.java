package NumberBaseConverter.ProcessingStrings;

import java.util.Scanner;

/**
 * Number of occurrences 
 * Write a program that finds the frequency of occurrences
 * of a substring in a given string. Substrings cannot overlap: for example, the
 * string ababa contains only one substring aba.
 * 
 * 
 * Input data format
 * 
 * The first input line contains a string, the second one contains a substring.
 * 
 * 
 * Sample Input: 
 * ababa 
 * aba
 * 
 * Sample Output: 
 * 1
 * 
 * 
 * Sample Input: 
 * hello there 
 * the
 * 
 * Sample Output: 
 * 1
 * 
 * 
 * Sample Input: 
 * hello yellow jello 
 * ll
 * 
 * Sample Output: 
 * 3
 * 
 * @author SMD_ASY
 *
 */

public class NumberOfOccurencies {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// put your code here
		Scanner sc = new Scanner(System.in);
		String source = sc.nextLine();
		String sequence = sc.nextLine();
		sc.close();
		System.out.println(numberOfOccurencies(source, sequence));
	}

	private static int numberOfOccurencies(String source, String sequence) {
		int ans = 0;
		String[] arr = source.split(" ");
		for (int j = 0; j < arr.length; j++) {
			for (int i = 0; i <= arr[j].length() - sequence.length(); i++) {
				if (arr[j].substring(i, i + sequence.length()).equals(sequence)) {
					ans++;
					i += sequence.length();
				}
			}
		}
		return ans;
	}

}
