package RecursiveCall;

import java.util.Scanner;

/**
 * Given a recursive method which should print an input string in the reverse
 * order.
 * 
 * Now the method prints the string in the same order. Fix the method.
 * 
 * After your fix, the method must be recursive.
 * 
 * Sample Input 1:
 * 
 * ousyn
 * 
 * Sample Output 1:
 * 
 * nysuo
 * 
 * @author SMD_ASY
 *
 */

public class ReverseOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter your's phrase: ");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		printInReverseOrder(s);
		System.out.println();
		printInReverseOrderWithLoops(s);
	}

	public static void printInReverseOrder(String s) {
		if (s.length() > 0) {
			int lastIndex = s.length() - 1;
			System.out.print(s.charAt(lastIndex));
			printInReverseOrder(s.substring(0, lastIndex));
		}
	}

	public static void printInReverseOrderWithLoops(String s) {
		for (int i = s.length() - 1; i >= 0; i--) {
			System.out.print(s.charAt(i));
		}
	}

}
