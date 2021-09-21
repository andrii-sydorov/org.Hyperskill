package NumberBaseConverter.ProcessingStrings;

import java.util.Scanner;

/**
 * Palindrome 
 * Write a program that reads a string and checks whether it is a
 * palindrome, i.e. it reads the same both left-to-right and right-to-left.
 * 
 * The program must output “yes” if the string is a palindrome and “no”
 * otherwise.
 * 
 * 
 * Sample Input: 
 * kayak
 * 
 * Sample Output: 
 * yes
 * 
 * @author SMD_ASY
 *
 */

public class Palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String in = sc.nextLine();
		sc.close();
		System.out.println(isPalindrome(in));
	}

	private static String isPalindrome(String in) {
		boolean isPalindrome = true;
		for (int i = 0; i < in.length() / 2; i++) {
			if (in.charAt(i) != in.charAt(in.length() - 1 - i)) {
				isPalindrome = false;
				break;
			}
		}
		return isPalindrome ? "yes" : "no";
	}

}
