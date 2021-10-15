package AmazingNumbers.Stage05;

import java.util.Scanner;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Arrays;

/**
 * Description
 * 
 * In this stage, we will add another property that is called a Spy number. A
 * number is said to be Spy if the sum of all digits is equal to the product of
 * all digits.
 * 
 * Our program calculates the properties of numbers, and also knows how to
 * process a list of numbers. But what if we want to find numbers that have a
 * certain property? For example, in case we want to find ten Buzz numbers
 * starting from 1000. We need to add a new request feature for this. In
 * addition, add a new property — Spy numbers. These numbers hide well, so
 * beware. Your task is to modify the program so that it can search for these
 * numbers. 
 * 
 * Objectives
 * 
 * Your program should process the user requests. In this stage, your program
 * should:
 * 
 * 1) Welcome users; 
 * 2) Display the instructions; 
 * 3) Ask for a request; 
 * 4) If a user enters zero, terminate the program; 
 * 5) If numbers are not natural, print an error message; 
 * 6) If a user inputs an incorrect property, print the error message and
 * the list of available properties; 
 * 7) For one number, print the properties of the number; For two numbers, 
 * print the list of numbers with their properties; 
 * 8) For two numbers and a property, print the list of numbers and their properties
 * filtered by the indicated property; 
 * 9) Once a request is processed, continue execution from step 3.
 * 
 * In the current stage, the property names include even, odd, buzz, duck,
 * palindromic , gapful, and spy. The test won't check the order of properties,
 * their indentation, and spaces. You may format numbers as you like. Please,
 * add the information below: 
 * 
 * Instructions
 * 
 * Supported requests: 
 * - enter a natural number to know its properties; 
 * - enter two natural numbers to obtain the properties of the list: 
 *  * the first parameter represents a starting number; 
 *  * the second parameter shows how many consecutive numbers are to be printed; 
 * - two natural numbers and a property to search for; 
 * - separate the parameters with one space; 
 * - enter 0 to exit.
 * 
 * Error messages
 * 
 * The first parameter should be a natural number or zero.
 * 
 * The second parameter should be a natural number.
 * 
 * The property [SUN] is wrong. Available properties: [EVEN, ODD, BUZZ, DUCK,
 * PALINDROMIC, GAPFUL, SPY]
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
 * - enter two natural numbers to obtain the properties of the list: 
 *   * the first parameter represents a starting number; 
 *   * the second parameters show how many consecutive numbers are to be processed; 
 * - two natural numbers and a property to search for; 
 * - separate the parameters with one space; 
 * - enter 0 to exit.
 * 
 * Enter a request: 9
 * 
 * Properties of 9 
 * buzz: false 
 * duck: false 
 * palindromic: true 
 * gapful: false 
 * spy: true 
 * even: false 
 * odd: true
 * 
 * Enter a request: 9 7
 * 
 * 9 is palindromic, spy, odd 
 * 10 is duck, even 
 * 11 is palindromic, odd 
 * 12 is even
 * 13 is odd 
 * 14 is buzz, even 
 * 15 is odd
 * 
 * Enter a request: 99 9 spy
 * 
 * 123 is spy, odd 
 * 132 is gapful, spy, even 
 * 213 is spy, odd 
 * 231 is buzz, gapful, spy, odd 
 * 312 is spy, even 
 * 321 is spy, odd 
 * 1,124 is spy, even 
 * 1,142 is spy, even 
 * 1,214 is spy, even
 * 
 * Enter a request: 9 3 drake
 * 
 * The property [DRAKE] is wrong. Available properties: [BUZZ, DUCK,
 * PALINDROMIC, GAPFUL, SPY, EVEN, ODD]
 * 
 * Enter a request: 9 3 duck
 * 
 * 10 is duck, even 
 * 20 is duck, even 3
 * 0 is duck, even
 * 
 * Enter a request: bye
 * 
 * The first parameter should be a natural number or zero.
 * 
 * Enter a request: 0
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
	private boolean Buzz;
	private boolean Even;
	private boolean Duck;
	private boolean Palindrome;
	private boolean Gapful;
	private boolean Spy;
	private static final String[] operation = { "EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY" };

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
			for (int i = 0; i < userStringArray.length; i++) {
				if (userStringArray[0].equals("0")) {
					exit = true;
					break;
				}
				isOddOrEven(userStringArray[i]);
				isBuzz(userStringArray[i]);
				isDuck(userStringArray[i]);
				isPalindromic(userStringArray[i]);
				isGapful(userStringArray[i]);
				isSpy(userStringArray[i]);
				printProperties(userStringArray[i]);
				if (i == userStringArray.length - 1 && i > 0) {
					System.out.println();
				}
			}
		} while (!exit);
	}

	private boolean isInputValid(String[] userData) {
		switch (userData.length) {
		case 1:
			return isValidOneArgument(userData, 0, "The first parameter should be a natural number or zero.\n");
		case 2:
			return isValidTwoArgument(userData);
		case 3:
			return isValidThreeArgument(userData);
		}
		return true;
	}

	private boolean isValidOneArgument(String[] s, int index, String message) {
		try {
			if (Long.valueOf(s[index]) < index) {
				throw new NumberFormatException(message);
			}
		} catch (NumberFormatException nfe) {
			nfe = new NumberFormatException(message);
			System.out.println();
			System.out.println(nfe.getMessage());
			return false;
		}
		return true;
	}

	private boolean isValidTwoArgument(String[] s) {
		if (!isValidOneArgument(s, 0, "The first parameter should be a natural number or zero.\n")) {
			return false;
		}
		if (!isValidOneArgument(s, 1, "The second parameter should be a natural number.\n")) {
			return false;
		}
		return true;
	}

	private boolean isValidThreeArgument(String[] s) {
		if (!isValidTwoArgument(s)) {
			return false;
		}
		for (String str : operation) {
			if (str.equalsIgnoreCase(s[2])) {
				return true;
			}
		}
		System.out.println("The property " + "[" + s[2] + "]" + " is wrong.");
		System.out.println("Available properties: " + Arrays.toString(operation));
		return false;
	}

	private void greetings() {
		System.out.println("Welcome to Amazing Numbers!\n");
	}

	private void showMenu() {
		System.out.println("Supported requests:\r\n" + "- enter a natural number to know its properties;\r\n"
				+ "- enter two natural numbers to obtain the properties of the list:\r\n"
				+ "  * the first parameter represents a starting number;\r\n"
				+ "  * the second parameters show how many consecutive numbers are to be processed;\r\n"
				+ "- two natural numbers and a property to search for;\n"
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
		makeStringArray(data);
		fillStringArray(data);
	}

	private void fillStringArray(String[] s) {
		long startValue = 0;
		int capacity = 0;
		switch (s.length) {
		case 1:
			userStringArray = s;
			break;
		case 2:
			startValue = Long.valueOf(s[0]);
			capacity = Integer.valueOf(s[1]);
			for (int i = 0; i < capacity; i++) {
				userStringArray[i] = String.valueOf(startValue++);
			}
			break;
		case 3:
			startValue = Long.valueOf(s[0]);
			capacity = Integer.valueOf(s[1]);
			for (int i = 0; i < capacity; startValue++) {
				String choice = s[2].toUpperCase();
				switch (choice) {
				case "EVEN":
					isOddOrEven(String.valueOf(startValue));
					if (Even) {
						userStringArray[i] = String.valueOf(Long.valueOf(startValue));
						i++;
					}
					continue;
				case "ODD":
					isOddOrEven(String.valueOf(startValue));
					if (!Even) {
						userStringArray[i] = String.valueOf(Long.valueOf(startValue));
						i++;
					}
					continue;
				case "BUZZ":
					isBuzz(String.valueOf(startValue));
					if (Buzz) {
						userStringArray[i] = String.valueOf(Long.valueOf(startValue));
						i++;
					}
					continue;
				case "DUCK":
					isDuck(String.valueOf(startValue));
					if (Duck) {
						userStringArray[i] = String.valueOf(Long.valueOf(startValue));
						i++;
					}
					continue;
				case "PALINDROMIC":
					isPalindromic(String.valueOf(startValue));
					if (Palindrome) {
						userStringArray[i] = String.valueOf(Long.valueOf(startValue));
						i++;
					}
					continue;
				case "GAPFUL":
					isGapful(String.valueOf(startValue));
					if (Gapful) {
						userStringArray[i] = String.valueOf(Long.valueOf(startValue));
						i++;
					}
					continue;
				case "SPY":
					isSpy(String.valueOf(startValue));
					if (Spy) {
						userStringArray[i] = String.valueOf(Long.valueOf(startValue));
						i++;
					}
					continue;
				}
			}
		}
		System.out.println();
	}

	private void makeStringArray(String[] data) {
		int dataLength = data.length > 1 ? Integer.parseInt(data[1]) : 1;
		userStringArray = new String[dataLength];
	}

	private void isOddOrEven(String s) {
		Even = false;
		if (Long.parseLong(s) % 2 == 0) {
			Even = true;
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
		Buzz = endsWith7 || isDivisibleBy7;
	}

	private void isDuck(String s) {
		Duck = false;
		if (s.contains("0")) {
			Duck = true;
		}
	}

	private void isPalindromic(String s) {
		for (int i = 0; i <= s.length() / 2; i++) {
			if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				Palindrome = false;
				return;
			}
		}
		Palindrome = true;
	}

	private void isGapful(String s) {
		int divider = Integer.valueOf(Character.toString(s.charAt(0)) + Character.toString(s.charAt(s.length() - 1)));
		Gapful = Long.parseLong(s) % divider == 0 && s.length() > 2 ? true : false;
	}

	private void isSpy(String s) {
		int sum = 0;
		int power = 1;
		for (int i = 0; i < s.length(); i++) {
			sum += Character.getNumericValue(s.charAt(i));
			power *= Character.getNumericValue(s.charAt(i));
		}
		Spy = power == sum ? true : false;
	}

	private void printProperties(String s) {
		String[] properties = { "buzz", "duck", "palindromic", "gapful", "spy", "even", "odd" };
		Boolean[] propertyValues = { Buzz, Duck, Palindrome, Gapful, Spy, Even, !Even, };
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
