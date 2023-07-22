package AnonymousClasses;

import java.util.Scanner;

/**
 * String reverser 
 * 
 * There is an interface StringReverser:
 * 
 * interface StringReverser {
 * 
 * 		String reverse(String str); 
 * } 
 * 
 * You should create an anonymous class that
 * implements the interface and assign the instance to the variable reverser.
 * The anonymous class must override the method reverse of the interface. It
 * should return the reversed input string.
 * 
 * Report a typo Sample Input 1:
 * 
 * line 
 * 
 * Sample Output 1:
 * 
 * enil 
 * 
 * Sample Input 2:
 * 
 * aba 
 * 
 * Sample Output 2:
 * 
 * aba
 * 
 * @author SMD_ASY
 *
 */

public class ReverseString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		StringReverser str = new StringReverser() {
			@Override
			public String reverse(String s) {
				String ans = "";
				for (int i = s.length() - 1; i >= 0; i--) {
					ans += Character.toString(s.charAt(i));
				}
				return ans;
			}
		};
		System.out.println(str.reverse(s));
		sc.close();
	}

}

interface StringReverser {
	String reverse(String s);
}
