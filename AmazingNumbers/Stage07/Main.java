package AmazingNumbers.Stage07;

import java.util.Scanner;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Arrays;

/**
 * Description
 * 
 * A number is a Jumping number if the adjacent digits inside the number differ
 * by 1. The difference between 9 and 0 is not considered as 1. Single-digit
 * numbers are considered Jumping numbers. For example, 78987, and 4343456 are
 * Jumping numbers, but 796 and 89098 are not.
 * 
 * In this stage, we will also remove the limitation on pending properties in a
 * request. The program knows how to calculate ten properties of numbers, and it
 * would be strange to limit the query to just two properties. Let's remove this
 * limitation. Let the program indicate all properties for all numbers in the
 * request. 
 * 
 * Objectives
 * 
 * Your program should process the user requests. In this stage, your program
 * should:
 * 
 * 1. Welcome users; 
 * 2. Display the instructions; 
 * 3. Ask for a request; 
 * 4. If a user enters zero, terminate the program; 
 * 5. If numbers are not natural, print the error message; 
 * 6. If an incorrect property is specified, print the error message and
 * the list of available properties; 
 * 7. For one number, print the properties of the number; 
 * 8. For two numbers, print the list of numbers with their properties. 
 * 9. For two numbers and properties, print the numbers with the specified properties;
 * 10. If a user specifies mutually exclusive properties, abort the request and warn
 * the user; 
 * 11. Once a request has been processed, continue execution from step 3.
 * 
 * In the current stage, the property names include even, odd, buzz, duck,
 * palindromic, gapful, spy, square, sunny, and jumping. The test won't check
 * the order of properties, their indentation, and spaces. You may format
 * numbers as you like. 
 * 
 * Instructions
 * 
 * Supported requests: 
 * - enter a natural number to know its properties; 
 * - enter two natural numbers to obtain the properties of the list: 
 *  * the first parameter represents a starting number; 
 *  * the second parameter shows how many consecutive numbers are to be printed; 
 * - two natural numbers and properties to search for; 
 * - separate the parameters with one space; 
 * - enter 0 to exit.
 * 
 * Error messages
 * 
 * The first parameter should be a natural number or zero.
 * 
 * The second parameter should be a natural number.
 * 
 * The property [SUN] is wrong. 
 * Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]
 * 
 * The properties [HOT, SUN] are wrong. 
 * Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]
 * 
 * The request contains mutually exclusive properties: [SQUARE, SUNNY] 
 * There are no numbers with these properties.
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
 *  * the first parameter represents a starting number; 
 *  * the second parameter shows how many consecutive numbers are to be processed; 
 * - two natural numbers and properties to search for; 
 * - separate the parameters with one space; 
 * - enter 0 to exit.
 * 
 * Enter a request: > 1234567890
 * 
 * Properties of 1,234,567,890 
 * buzz: false 
 * duck: true 
 * palindromic: false 
 * gapful: true 
 * spy: false 
 * square: false 
 * sunny: false 
 * jumping: false 
 * even: true 
 * odd: false
 * 
 * Enter a request: > 1234567890 4
 * 
 * 1,234,567,890 is duck, gapful, even 
 * 1,234,567,891 is odd 
 * 1,234,567,892 is even 
 * 1,234,567,893 is gapful, odd
 * 
 * Enter a request: > 1234567890 4 jumping
 * 
 * 1,234,567,898 is jumping, even 
 * 2,101,010,101 is duck, jumping, odd
 * 2,101,010,121 is buzz, duck, gapful, jumping, odd 
 * 2,101,010,123 is duck, jumping, odd
 * 
 * Enter a request: > 5000 5 duck spy
 * 
 * The request contains mutually exclusive properties: [DUCK, SPY] 
 * There are no numbers with these properties.
 * 
 * Enter a request: > 5000 5 duck jumping
 * 
 * 10,101 is buzz, duck, palindromic, jumping, odd 
 * 10,121 is duck, jumping, odd
 * 10,123 is duck, jumping, odd 
 * 12,101 is duck, jumping, odd 
 * 21,010 is duck, jumping, even
 * 
 * Enter a request: > 999 6 jumping odd palindromic
 * 
 * 10,101 is buzz, duck, palindromic, jumping, odd 
 * 12,121 is palindromic, jumping, odd 
 * 12,321 is palindromic, square, jumping, odd 
 * 32,123 is buzz, palindromic, jumping, odd 
 * 32,323 is palindromic, jumping, odd 
 * 34,343 is palindromic, jumping, odd
 * 
 * Enter a request: 1 5 hot sunny
 * 
 * The property [HOT] is wrong. 
 * Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, EVEN, ODD]
 * 
 * Enter a request: exit
 * 
 * The first parameter should be a natural number or zero.
 * 
 * Enter a request: 0
 * 
 * Goodbye!
 * 
 * Process finished with exit code 0
 * 
 * @author ASY
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
	private boolean Sunny;
	private boolean Square;
	private boolean Jumping;
	private boolean complexInput;
	private static final String[] operation = { "EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE",
			"SUNNY", "JUMPING" };

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
				isSquare(userStringArray[i]);
				isSunny(userStringArray[i]);
				isJumping(userStringArray[i]);
				printProperties(userStringArray[i]);
				if (i == userStringArray.length - 1 && i > 0) {
					System.out.println();
				}
			}
		} while (!exit);
	}

	private boolean isInputValid(String[] userData) {
		complexInput = userData.length > 1 ? true : false;
		switch (userData.length) {
		case 1:
			return isValidOneArgument(userData, 0, "The first parameter should be a natural number or zero.\n");
		case 2:
			return isValidTwoArgument(userData);
		default:
			return isValidForAnyArgument(userData) && isNotExclusiveProperties(userData);
		}
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

	private boolean isValidForAnyArgument(String[] s) {
		if (!isValidTwoArgument(s)) {
			return false;
		}
		List<String> errors = new ArrayList<>();
		for (int i = 2; i < s.length; i++) {
			boolean isFound = false;
			for (String str : operation) {
				if (str.equalsIgnoreCase(s[i])) {
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				errors.add(s[i]);
			}
		}
		if (errors.size() == 0) {
			return true;
		} else if (errors.size() == 1) {
			System.out.println(
					"The property " + Arrays.toString(errors.toArray(new String[errors.size()])) + " is wrong.");
			System.out.println("Available properties: " + Arrays.toString(operation) + "\n");
		} else {
			System.out.println(
					"The properties " + Arrays.toString(errors.toArray(new String[errors.size()])) + " are wrong.");
			System.out.println("Available properties: " + Arrays.toString(operation) + "\n");
		}
		return false;
	}

	private boolean isNotExclusiveProperties(String[] s) {
		String[] toBeChecked = Arrays.copyOfRange(s, 2, s.length);
		String[][] exclusiveProperties = { { "EVEN", "ODD" }, { "DUCK", "SPY" }, { "SUNNY", "SQUARE" } };
		for (String[] ar : exclusiveProperties) {
			int found = 0;
			for (int i = 0; i < ar.length; i++) {
				for (int j = 0; j < toBeChecked.length; j++) {
					if (ar[i].equalsIgnoreCase(toBeChecked[j])) {
						found++;
					}
				}
			}
			if (found == ar.length) {
				System.out.println("\nThe request contains mutually exclusive properties: " + Arrays.toString(ar));
				System.out.println("There are no numbers with these properties.\n");
				return false;
			}
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
				+ "- two natural numbers and a property to search for;\n"
				+ "- two natural numbers and two properties to search for;\n"
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
		long[] size = makeLimitsArray(s);
		switch (s.length) {
		case 1:
			userStringArray = s;
			break;
		case 2:
			for (int i = 0; i < size[1]; i++) {
				userStringArray[i] = String.valueOf(size[0]++);
			}
			break;
		default:
			fillAccordingAnyProperties(s, size);
			break;
		}
		System.out.println();
	}

	private long[] makeLimitsArray(String[] s) {
		if (s.length == 1) {
			return new long[] { Long.valueOf(s[0]) };
		} else {
			return new long[] { Long.valueOf(s[0]), Integer.valueOf(s[1]) };
		}
	}

	private void fillAccordingAnyProperties(String[] s, long[] size) {
		long startValue = size[0];
		long capacity = size[1];
		for (int i = 0; i < capacity; startValue++) {
			int count = 0;
			for (int j = 2; j < s.length; j++) {
				if (checkProperty(startValue, s[j].toUpperCase())) {
					count++;
					continue;
				} else {
					break;
				}
			}
			if (count == s.length - 2) {
				userStringArray[i] = String.valueOf(Long.valueOf(startValue));
				i++;
			}
		}
	}

	private boolean checkProperty(long startValue, String choice) {
		boolean firstPropery = false;
		switch (choice) {
		case "EVEN":
			isOddOrEven(String.valueOf(startValue));
			if (Even) {
				firstPropery = true;
			}
			break;
		case "ODD":
			isOddOrEven(String.valueOf(startValue));
			if (!Even) {
				firstPropery = true;
			}
			break;
		case "BUZZ":
			isBuzz(String.valueOf(startValue));
			if (Buzz) {
				firstPropery = true;
			}
			break;
		case "DUCK":
			isDuck(String.valueOf(startValue));
			if (Duck) {
				firstPropery = true;
			}
			break;
		case "PALINDROMIC":
			isPalindromic(String.valueOf(startValue));
			if (Palindrome) {
				firstPropery = true;
			}
			break;
		case "GAPFUL":
			isGapful(String.valueOf(startValue));
			if (Gapful) {
				firstPropery = true;
			}
			break;
		case "SPY":
			isSpy(String.valueOf(startValue));
			if (Spy) {
				firstPropery = true;
			}
			break;
		case "SUNNY":
			isSunny(String.valueOf(startValue));
			if (Sunny) {
				firstPropery = true;
			}
			break;
		case "SQUARE":
			isSquare(String.valueOf(startValue));
			if (Square) {
				firstPropery = true;
			}
			break;
		case "JUMPING":
			isJumping(String.valueOf(startValue));
			if (Jumping) {
				firstPropery = true;
//				if (String.valueOf(startValue).endsWith("0")) {
//					startValue = startValue * 10 + 1 - 1;
//				} else if (String.valueOf(startValue).endsWith("9")) {
//					startValue = startValue * 10 + 8 - 1;
//				} else {
//					startValue = startValue * 10 + startValue % 10 + 1 - 1;
//				}
			}
			break;
		}
		return firstPropery;
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

	private void isSquare(String s) {
		Square = Math.sqrt(Long.valueOf(s)) == (int) Math.sqrt(Long.valueOf(s)) * 1.0 ? true : false;
	}

	private void isSunny(String s) {
		long nextNumbers = Long.valueOf(s) + 1;
		Sunny = Math.sqrt(nextNumbers) == (int) Math.sqrt(nextNumbers) * 1.0 ? true : false;
	}

	private void isJumping(String s) {
		if (s.length() == 1) {
			Jumping = true;
			return;
		}
		for (int i = 0; i < s.length() - 1; i++) {
			if (Math.abs(s.charAt(i) - s.charAt(i + 1)) != 1) {
				Jumping = false;
				return;
			}
		}
		Jumping = true;
	}

	private void printProperties(String s) {
		String[] properties = { "buzz", "duck", "palindromic", "gapful", "spy", "square", "sunny", "jumping", "even",
				"odd" };
		Boolean[] propertyValues = { Buzz, Duck, Palindrome, Gapful, Spy, Square, Sunny, Jumping, Even, !Even, };
		if (userStringArray.length == 1 && !complexInput) {
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
