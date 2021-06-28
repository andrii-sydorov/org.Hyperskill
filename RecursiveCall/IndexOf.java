package RecursiveCall;

import java.util.Scanner;

/**
 * Write a recursive method called indexOf that accepts two strings as
 * parameters and returns the starting index of the first occurrence of the
 * second string inside the first string (or -1 if not found). Report a typo
 * 
 * Sample Input 1:
 * 
 * Java 
 * J
 * 
 * Sample Output 1:
 * 
 * 0
 * 
 * Sample Input 2:
 * 
 * Hyper 
 * Er
 * 
 * Sample Output 2:
 * 
 * -1
 * 
 * @author SMD_ASY
 *
 */

public class IndexOf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the source String:");
		String src = sc.nextLine();
		System.out.println("Enter the target String:");
		String tg = sc.nextLine();
		System.out.println(indexOf(src, tg));
	}

	public static int indexOf(String src, String tg) {
		if (!src.contains(tg)) {
			return -1;
		}
		int startIndex = 0;
		return indexWithRecursion(src, tg, startIndex);
	}

	public static int indexWithRecursion(String src, String tg, int startIndex) {
		if (src.substring(startIndex, startIndex + tg.length()).equals(tg)) {
			return startIndex;
		}
		startIndex++;
		return indexWithRecursion(src, tg, startIndex);
	}

}
