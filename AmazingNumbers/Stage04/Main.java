package AmazingNumbers.Stage04;

import java.util.Scanner;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Description
 * 
 * In this stage, we are going to add one more property — Gapful numbers. It is
 * a number that contains at least 3 digits and is divisible by the
 * concatenation of its first and last digit without a remainder. 12 is not a
 * Gapful number, as it has only two digits. 132 is a Gapful number, as 132 % 12
 * == 0. 7881 is another example of a Gapful number, as 7881 % 71 == 0.
 * 
 * Until this stage, the program could process only one number at a time. Now, a
 * user should be able to enter two numbers to obtain the properties of a list
 * of numbers. Separate the numbers with one space. Space separates the first
 * number in the list and the length of the list. For, example. 100 2 tells the
 * program to process two numbers: 100 and 101. 1 100 means that the program is
 * about to process 100 numbers, starting from 1. If a user enters one number,
 * the program should work the same as in the previous stages. The program
 * should analyze a number and print its properties. As before, if a user enters
 * a single 0 (zero), terminate the program. Information about each number
 * should be printed in one line in the following format:
 * 
 * 140 is even, buzz, duck, gapful 
 * 141 is odd, palindromic
 * 
 * So, the format is {number} is {property}, {property}, ... {property}
 * 
 * Objectives
 * 
 * Your program should process various user requests. In this stage, your
 * program should:
 * 
 * 1) Welcome users; 
 * 2) Display the instructions; 
 * 3) Ask for a request; 
 * 4) If a user enters zero, terminate the program; 
 * 5) If a user enters an empty request, print the instructions; 
 * 6) If numbers are not natural, print an error message; 
 * 7) If one number is entered, calculate and print the properties of this number; 
 * 8) For two numbers, print the list of numbers with properties; 
 * 9) Once the request is processed, continue execution from step 3.
 * 
 * In the current stage, the property names include even, odd, buzz , duck,
 * palindromic and gapful. The test won't check the order of properties, their
 * indentation, and spaces. You may format numbers as you like. Please, add the
 * information below:
 * 
 * Instructions
 * 
 * Supported requests: - enter a natural number to know its properties; - enter
 * two natural numbers to obtain the properties of the list: the first parameter
 * represents a starting number; the second parameter shows how many consecutive
 * numbers are to be printed; - separate the parameters with one space; - enter
 * 0 to exit.
 * 
 * Error messages
 * 
 * The first parameter should be a natural number or zero.
 * 
 * The second parameter should be a natural number.
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
 * Supported requests: - enter a natural number to know its properties; - enter
 * two natural numbers to obtain the properties of the list: the first parameter
 * represents a starting number; the second parameters show how many consecutive
 * numbers are to be processed; - separate the parameters with one space; -
 * enter 0 to exit.
 * 
 * Enter a request: > 7881
 * 
 * Properties of 7,881 buzz: false duck: false palindromic: false gapful: true
 * even: false odd: true
 * 
 * Enter a request: > 7880
 * 
 * Properties of 7,880 buzz: false duck: true palindromic: false gapful: false
 * even: true odd: false
 * 
 * Enter a request: > 105 5
 * 
 * 105 is buzz, duck, gapful, odd 106 is duck, even 107 is buzz, duck, odd 108
 * is duck, gapful, even 109 is duck, odd
 * 
 * Enter a request: > exit
 * 
 * The first parameter should be a natural number or zero.
 * 
 * Enter a request: >
 * 
 * Supported requests: - enter a natural number to know its properties; - enter
 * two natural numbers to obtain the properties of the list: the first parameter
 * represents a starting number; the second parameters show how many consecutive
 * numbers are to be processed; - separate the parameters with one space; -
 * enter 0 to exit.
 * 
 * Enter a request: > 0
 * 
 * Goodbye!
 * 
 * Process finished with exit code 0
 * 
 * @author SMD_ASY
 *
 */

public class Main {

	private final Scanner sc = new Scanner(System.in);
	private String[] userStringArray;
	private boolean isBuzz;
	private boolean isEven;
	private boolean isDuck;
	private boolean isPalindrome;
	private boolean isGapful;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();
		m.greetings();
		m.showMenu();
		m.dataProcessing();
		m.sayBye();
	}

	private void sayBye() {
		System.out.println("Goodbye!\n");
		System.out.println("Process finished with exit code 0");
	}

	private void dataProcessing() {
		boolean exit = false;
		do {
			userInput(sc);
			for (String s : userStringArray) {
				if (s.equals("0")) {
					exit = true;
					break;
				}
				isOddOrEven(s);
				isBuzz(s);
				isDuckNumbers(s);
				isPalindromic(s);
				isGapful(s);
				printProperties(s);
			}
		} while (!exit);
	}

	private boolean isInputValid(String[] userData) {
		int index = 0;
		for (String s : userData) {
			if (!isNatural(s)) {
				System.out.println();
				if (index == 0) {
					System.out.println("The first parameter should be a natural number or zero.\n");
				} else if (index == 1) {
					System.out.println("The second parameter should be a natural number or zero.\n");
				}
				return false;
			}
			index++;
		}
		return true;
	}

	private void greetings() {
		System.out.println("Welcome to Amazing Numbers!\n");
	}

	private void showMenu() {
		System.out.println("Supported requests:\r\n" + "- enter a natural number to know its properties;\r\n"
				+ "- enter two natural numbers to obtain the properties of the list:\r\n"
				+ "  * the first parameter represents a starting number;\r\n"
				+ "  * the second parameters show how many consecutive numbers are to be processed;\r\n"
				+ "- separate the parameters with one space;\r\n" + "- enter 0 to exit.\n");
	}

	private void userInput(Scanner sc) {
		String[] data = null;
		while (true) {
			System.out.print("Enter a request: ");
			data = sc.nextLine().split(" ");
			if (!isInputValid(data)) {
				continue;
			}
			break;
		}
		long startValue = Long.parseLong(data[0]);
		int dataLength = data.length > 1 ? Integer.parseInt(data[1]) : 1;
		userStringArray = new String[dataLength];
		for (int i = 0; i < dataLength; i++) {
			userStringArray[i] = String.valueOf(startValue++);
		}
		System.out.println();
	}

	private boolean isNatural(String s) {
		boolean isValid = true;
		long n = 0;
		try {
			n = Long.parseLong(s);
		} catch (NumberFormatException nfe) {
			isValid = false;
		}
		if (n < 0) {
			isValid = false;
		}
		return isValid;
	}

	private void isOddOrEven(String s) {
		isEven = false;
		if (Long.parseLong(s) % 2 == 0) {
			isEven = true;
		}
	}

	private void isBuzz(String s) {
		boolean endsWith7 = false;
		boolean isDivisibleBy7 = false;
		if (s.endsWith("7")) {
			endsWith7 = true;
		}
		if (Long.parseLong(s) % 7 == 0) {
			isDivisibleBy7 = true;
		}
		isBuzz = endsWith7 || isDivisibleBy7;
	}

	private void isDuckNumbers(String s) {
		isDuck = false;
		if (s.contains("0")) {
			isDuck = true;
		}
	}

	private void isPalindromic(String s) {
		for (int i = 0; i <= s.length() / 2; i++) {
			if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				isPalindrome = false;
				return;
			}
		}
		isPalindrome = true;
	}

	private void isGapful(String s) {
		int divider = Integer.valueOf(Character.toString(s.charAt(0)) + Character.toString(s.charAt(s.length() - 1)));
		isGapful = Long.parseLong(s) % divider == 0 && s.length() > 2 ? true : false;
	}

	private void printProperties(String s) {
		String[] properties = { "buzz", "duck", "palindromic", "gapful", "even", "odd" };
		Boolean[] propertyValues = { isBuzz, isDuck, isPalindrome, isGapful, isEven, !isEven, };
		// System.out.println("Properties of " + s);
		if (userStringArray.length == 1) {
			System.out.println("Properties of " + NumberFormat.getInstance(Locale.UK).format(Long.parseLong(s)));
			for (int i = 0; i < properties.length; i++) {
				System.out.println(properties[i] + ": " + propertyValues[i]);
			}
		} else {
			System.out.print(s + " is ");
			List<String> ls = new ArrayList<>();
			for (int i = 0; i < propertyValues.length; i++) {
				if (propertyValues[i]) {
					ls.add(properties[i]);
				}
			}
			System.out.print(String.join(",", ls.toArray(new String[ls.size()])));
		}
		System.out.println();
	}

}
