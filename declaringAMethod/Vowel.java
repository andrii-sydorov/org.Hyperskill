package declaringAMethod;

import java.util.Scanner;

/**
 * Implement a method that checks whether a given English letter is a vowel or
 * not. The input may be in any case.
 * 
 * In our case, the letter 'y' is not considered a vowel.
 * 
 * Examples:
 * 
 * isVowel('a') should be true 
 * isVowel('A') should be true 
 * isVowel('b') should be false
 * 
 * @author SMD_ASY
 *
 */

public class Vowel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter your character:");
		Scanner sc = new Scanner(System.in);
		char ch = sc.nextLine().charAt(0);
		System.out.println(isVowel(ch));
		sc.close();
	}

	public static boolean isVowel(char ch) {
		char[] c = { 'a', 'e', 'i', 'o', 'u' };
		String s = new String(c);
		if (s.contains(Character.toString(Character.toLowerCase(ch)))) {
			return true;
		}
		return false;
	}

}
