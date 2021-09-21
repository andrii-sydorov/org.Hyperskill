package NumberBaseConverter.ProcessingStrings;

import java.util.Scanner;

/**
 * Move the first N characters 
 * Write a program that reads a string s and an int n, and then moves the first n characters of s to the end of the string. The
 * program must output the changed string. If n is greater than the length of the string, it must output the string unchanged.
 * 
 * Note, the substring method can help you, but you may choose another way to
 * solve the problem.
 * 
 * Input data format
 * 
 * The single input line contains s and n separated by a space.
 * 
 * 
 * Sample Input: 
 * Hello 3
 * 
 * Sample Output: 
 * loHel
 * 
 * @author SMD_ASY
 *
 */

public class MoveTheFirstNCharacters {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String[] line = sc.nextLine().split(" ");
		String data = line[0];
		int n = Integer.valueOf(line[1]);
		sc.close();
		System.out.println(moveCharacters(data, n));
	}

	private static String moveCharacters(String data, int n) {
		if (data.length() < n) {
			return data;
		}
		return data.substring(n) + data.substring(0, n);
	}

}
