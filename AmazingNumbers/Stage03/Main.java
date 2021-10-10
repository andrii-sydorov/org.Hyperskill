package AmazingNumbers.Stage03;

import java.util.Scanner;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Description
 * 
 * In this stage, the program should check whether a number is a Palindromic
 * one. A Palindromic number is symmetrical; in other words, it stays the same
 * regardless of whether we read it from left or right. For example, 17371 is a
 * palindromic number. 5 is also a palindromic number. 1234 is not. If read it
 * from right, it becomes 4321. Add this new property to our program.
 * 
 * In previous stages, the program could process only one number. From now on,
 * the program should accept a request from a user, analyze and execute it. The
 * program should print Enter a request instead of asking for a natural number.
 * The program should also continue execution unless a user enters a terminate
 * command. Let's make it 0 (zero).
 * 
 * Your program should welcome users and print the instructions. At this point,
 * make your program execute two commands. If a user enters a natural number,
 * the program should indicate the properties of that number. If a user enters
 * zero, then the program should exit. If a user enters a negative number by
 * mistake, print an error message. 
 * 
 * Objectives
 * 
 * In this stage, your program should:
 * 
 * Welcome users; Display the instructions; Ask for a request; Terminate the
 * program if a user enters zero; If a number is not natural, print an error
 * message; Print the properties of the natural number; Continue execution from
 * step 3, after the request has been processed.
 * 
 * The properties are even, odd, buzz, duck and palindromic. The tests won't
 * check the order of properties, indentation, and spaces. You may format
 * numbers as you like. Please, add the information below:
 * 
 * Instructions:
 * 
 * Supported requests: 
 * - enter a natural number to know its properties; 
 * - enter 0 to exit.
 * 
 * Error message:
 * 
 * The first parameter should be a natural number or zero.
 * 
 * Examples
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1:
 * 
 * Welcome to Amazing Numbers!
 * 
 * Supported requests: 
 * - enter a natural number to know its properties; 
 * - enter 0 to exit.
 * 
 * Enter a request: > 9223372036854775807
 * 
 * Properties of 9,223,372,036,854,775,807 
 * even: false 
 * odd: true 
 * buzz: true
 * duck: true 
 * palindromic: false
 * 
 * Enter a request: > 101
 * 
 * Properties of 101 
 * even: false 
 * odd: true 
 * buzz: false 
 * duck: true 
 * palindromic: true
 * 
 * Enter a request: > -56
 * 
 * The first parameter should be a natural number or zero.
 * 
 * Enter a request: > 0
 * 
 * Goodbye!
 * 
 * @author ASY
 *
 */

public class Main {

	private final Scanner sc = new Scanner(System.in);
	private String s;
	private boolean isBuzz;
	private boolean isEven;
	private boolean isDuck;
	private boolean isPalindrome;
	private BigInteger n;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();
		m.greetings();
		m.showMenu();
		m.dataProcessing();
		m.sayBye();
	}

	private void sayBye() {
		System.out.println("Goodbye!");
	}

	private void dataProcessing() {
		do {
			userInput(sc);
			if (s.equals("0")) {
				break;
			}
			if (!isNatural(s)) {
				System.out.println("The first parameter should be a natural number or zero.\n");
				continue;
			}
			isOddOrEven();
			isBuzz();
			isDuckNumbers();
			isPalindromic();
			printProperties();
		} while (true);
	}

	private void greetings() {
		System.out.println("Welcome to Amazing Numbers!\n");
	}

	private void showMenu() {
		System.out.println("Supported requests:\r\n" + "- enter a natural number to know its properties;\r\n"
				+ "- enter 0 to exit.\n");
	}

	private void userInput(Scanner sc) {
		System.out.print("Enter a request: ");
		s = sc.nextLine();
		System.out.println();
	}

	private boolean isNatural(String s) {
		boolean isValid = true;
		try {
			n = new BigInteger(s);
		} catch (NumberFormatException nfe) {
			isValid = false;
		}
		if (n.compareTo(BigInteger.valueOf(1)) == -1) {
			isValid = false;
		}
		return isValid;
	}

	private void isOddOrEven() {
		isEven = false;
		if (n.divideAndRemainder(BigInteger.valueOf(2))[1].compareTo(BigInteger.valueOf(0)) == 0) {
			isEven = true;
		}
	}

	private void isBuzz() {
		boolean endsWith7 = false;
		boolean isDivisibleBy7 = false;
		if (s.endsWith("7")) {
			endsWith7 = true;
		}
		if (n.divideAndRemainder(BigInteger.valueOf(7))[1].compareTo(BigInteger.valueOf(0)) == 0) {
			isDivisibleBy7 = true;
		}
		isBuzz = endsWith7 || isDivisibleBy7;
	}

	private void isDuckNumbers() {
		isDuck = false;
		if (s.contains("0")) {
			isDuck = true;
		}
	}

	private void isPalindromic() {
		for (int i = 0; i <= s.length() / 2; i++) {
			if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				isPalindrome = false;
				return;
			}
		}
		isPalindrome = true;
	}

	private void printProperties() {
		String[] properties = { "even", "odd", "buzz", "duck", "palindromic" };
		Boolean[] propertyValues = { isEven, !isEven, isBuzz, isDuck, isPalindrome };
		System.out.println("Properties of " + s);
		System.out.println("Properties of " + NumberFormat.getInstance(Locale.UK).format(n));
		for (int i = 0; i < properties.length; i++) {
			System.out.println(properties[i] + ": " + propertyValues[i]);
		}
		System.out.println();
	}

}
