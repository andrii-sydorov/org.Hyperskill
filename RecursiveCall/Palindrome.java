package RecursiveCall;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.IOException;

/**
 * Here is a method that checks if the input string is a palindrome:
 * 
 * public static boolean isPalindrome(String s) { 
 * if (s.length() == 2 || s.length() == 1) { // (1) 
 * return true; // (2) 
 * }
 * 
 * int lastIndex = s.length() - 1; // (3) 
 * boolean r = s.charAt(0) == s.charAt(lastIndex); // (4)
 * 
 * return r || isPalindrome(s.substring(1, lastIndex)); // (5) 
 * }
 * 
 * Unfortunately, the method is wrong. To fix it, you should change several
 * lines of the code.
 * 
 * Choose the necessary whole line replacements.
 * 
 * Here are some tests with their expected results. The method should pass all
 * tests.
 * 
 * isPalindrome(""); // true 
 * isPalindrome("a"); // true 
 * isPalindrome("aa"); // true 
 * isPalindrome("ab"); // false 
 * isPalindrome("aba"); // true
 * isPalindrome("abab"); // false ...
 * 
 * @author SMD_ASY
 *
 */

public class Palindrome {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Enter your phrase:");
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String line = bf.readLine();
		System.out.println(isPalindrome(line));
		String[] test = { "", "a", "aa", "ab", "aba", "abab" };
		System.out.println(isPalindromeWithLoop("aa"));
		Arrays.stream(test).forEach(s -> System.out.println(s + " with using of Recursivecalls " + "\""
				+ isPalindrome(s) + "\"" + " with using Loops " + "\"" + isPalindromeWithLoop(s) + "\""));
	}

	public static boolean isPalindrome(String line) {
		if (line.length() == 0 || line.length() == 1) {
			return true;
		}
		int lastIndex = line.length() - 1;
		boolean r = line.charAt(0) == line.charAt(lastIndex);
		return r && isPalindrome(line.substring(1, lastIndex));
	}

	public static boolean isPalindromeWithLoop(String line) {
		if (line.length() == 0 || line.length() == 1) {
			return true;
		}
		for (int i = 0; i < line.length() / 2; i++) {
			if (line.charAt(i) != line.charAt(line.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}

}
