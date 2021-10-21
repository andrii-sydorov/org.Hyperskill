package AmazingNumbers.Stage08;

import java.util.Scanner;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Arrays;
import java.util.Collections;

/**
 * Description
 * 
 * In number theory, a happy number is a number that reaches 1 after a sequence
 * during which the number is replaced by the sum of each digit squares. For
 * example, 13 is a happy number, as 12 + 32 = 10 which leads to 12 + 02 = 1. On
 * the other hand, 4 is not a happy number because the sequence starts with 42 =
 * 16, 12 + 62 = 37, and finally reaches 22 + 02 = 4. This is the number that
 * started the sequence, so the process goes on in an infinite cycle. A number
 * that is not happy is called Sad (or Unhappy).
 * 
 * Our program is finished. It can indicate many interesting properties of
 * numbers, it knows how to calculate them. Now, when prompted, a user can have
 * a list of number properties. To complete the program, let's add an ability to
 * exclude a property from the search query. If a user puts a minus (-) before
 * the property, exclude this property from the search query. For example, if a
 * user specifies palindromic -duck, it means that they are looking for
 * Palindromic numbers that are not Ducks. 
 * 
 * Objectives
 * 
 * Your program should process the user requests. In this stage, your program
 * should:
 * 
 * 1. Welcome users; 
 * 2. Display the instructions;
 * 3. Ask for a request;
 * 4. If a user enters an empty request, print the instructions;
 * 5. If the user enters zero, terminate the program;
 * 6. If numbers are not natural, print the error message;
 * 7. If an incorrect property is specified, print the error message and the list of
 * available properties;
 * 8. For one number, print the properties of the number;
 * 9. For two numbers, print the properties of all numbers in the list;
 * 10. For two numbers and two properties, print the list of numbers that contain the specified
 * properties;
 * 11. If a property is preceded by a minus, this property should not be present in a number;
 * 12. If the user specifies mutually exclusive properties,abort the request and warn the user; 
 * 13. Once the request is processed, continue execution from step 3.
 * 
 * In this stage, property names include even, odd, buzz, duck, palindromic,
 * gapful, spy, sunny, square, jumping, sad, and happy. 
 * Mutually exclusive properties are even/odd, duck/spy, sunny/square, sad/happy pairs, as well as
 * direct opposites (property and -property). The test won't check the order of
 * properties, their indentation, and spaces. You may format numbers as you
 * like. 
 * 
 * Instructions
 * 
 * Supported requests: 
 * - enter a natural number to know its properties; 
 * - enter two natural numbers to obtain the properties of the list: 
 *  * the first parameter represents a starting number; 
 *  * the second parameter shows how many consecutive numbers are to be printed;
 * - two natural numbers and properties to search for;
 * - a property preceded by minus must not be present in numbers; 
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
 * Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]
 * 
 * The properties [HOT, SUN] are wrong. 
 * Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]
 * 
 * The request contains mutually exclusive properties: [HAPPY, SAD] 
 * There are no numbers with these properties.
 * 
 * The request contains mutually exclusive properties: [-HAPPY, -SAD] 
 * There are no numbers with these properties.
 * 
 * The request contains mutually exclusive properties: [GAPFUL, -GAPFUL] 
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
 * - a property preceded by minus must not be present in numbers; 
 * - separate the parameters with one space; 
 * - enter 0 to exit.
 * 
 * Enter a request: 1 10
 * 
 * 1 is odd, palindromic, spy, square, jumping, happy 
 * 2 is even, palindromic, spy, jumping, sad 
 * 3 is odd, palindromic, spy, sunny, jumping, sad 
 * 4 is even, palindromic, spy, square, jumping, sad 
 * 5 is odd, palindromic, spy, jumping, sad 
 * 6 is even, palindromic, spy, jumping, sad 
 * 7 is odd, buzz, palindromic, spy, jumping, happy 
 * 8 is even, palindromic, spy, sunny, jumping, sad 
 * 9 is odd, palindromic, spy, square, jumping, sad 
 * 10 is even, duck, jumping, happy
 * 
 * Enter a request: 1 5 -odd
 * 
 * 2 is even, palindromic, spy, jumping, sad 
 * 4 is even, palindromic, spy, square, jumping, sad 
 * 6 is even, palindromic, spy, jumping, sad 
 * 8 is even, palindromic, spy, sunny, jumping, sad 
 * 10 is even, duck, jumping, happy
 * 
 * Enter a request: 1 5 -even
 * 
 * 1 is odd, palindromic, spy, square, jumping, happy 
 * 3 is odd, palindromic, spy, sunny, jumping, sad 
 * 5 is odd, palindromic, spy, jumping, sad 
 * 7 is odd, buzz, palindromic, spy, jumping, happy 
 * 9 is odd, palindromic, spy, square, jumping, sad
 * 
 * Enter a request: 1 5 -odd -even gapful
 * 
 * The request contains mutually exclusive properties: [-ODD, -EVEN] 
 * There are no numbers with these properties.
 * 
 * Enter a request: 1 5 odd square -odd
 * 
 * The request contains mutually exclusive properties: [-ODD, ODD] 
 * There are no numbers with these properties.
 * 
 * Enter a request: 1 5 sunny square
 * 
 * The request contains mutually exclusive properties: [SQUARE, SUNNY] 
 * There are no numbers with these properties.
 * 
 * Enter a request: 1 5 -sunny -square
 * 
 * 2 is even, palindromic, spy, jumping, sad 
 * 5 is odd, palindromic, spy, jumping, sad 
 * 6 is even, palindromic, spy, jumping, sad 
 * 7 is odd, buzz, palindromic, spy, jumping, happy 
 * 10 is even, duck, jumping, happy
 * 
 * Example 2: Numbers that have one specified property
 * 
 * Enter a request: > 2000 5 happy
 * 
 * 2,003 is odd, duck, happy 
 * 2,008 is even, duck, happy 
 * 2,019 is odd, duck, happy 
 * 2,026 is even, duck, happy 
 * 2,030 is even, buzz, duck, happy
 * 
 * Example 3: Numbers with all specified properties
 * 
 * Enter a request: 1 5 even sunny happy -duck -gapful
 * 
 * 3,968 is even, sunny, happy 
 * 34,224 is even, sunny, happy 
 * 75,624 is even, sunny, happy 
 * 134,688 is even, sunny, happy 
 * 178,928 is even, sunny, happy
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
	private boolean Happy;
	private boolean complexInput;
	private static final String[] operation = { "EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE",
			"SUNNY", "JUMPING", "HAPPY", "SAD" };

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
				isHappy(userStringArray[i]);
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
		String[] oppositeOperation = Arrays.stream(operation).map(x -> "-" + x).toArray(String[]::new);
		List<String> allOperation = new ArrayList<>();
		Collections.addAll(allOperation, operation);
		Collections.addAll(allOperation, oppositeOperation);
		for (int i = 2; i < s.length; i++) {
			boolean isFound = false;
			for (String str : allOperation) {
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
		String[][] exclusiveProperties = { { "EVEN", "ODD" }, { "DUCK", "SPY" }, { "SUNNY", "SQUARE" },
				{ "SAD", "HAPPY" } };
		String[][] oppositeExclusiveProperties = { { "-EVEN", "-ODD" }, { "-DUCK", "-SPY" }, { "-SAD", "-HAPPY" } };
		String[][] directOppositedProperties = makeDirectOppositedProperties();
		List<String[]> ls = new ArrayList<>();
		ls = makeListOfExclusiveProperties(exclusiveProperties, directOppositedProperties, oppositeExclusiveProperties);
		for (String[] ar : ls) {
			int found = 0;
			for (int i = 0; i < ar.length; i++) {
				for (int j = 0; j < toBeChecked.length; j++) {
					if (ar[i].equalsIgnoreCase(toBeChecked[j])) {
						found++;
						break;
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

	private List<String[]> makeListOfExclusiveProperties(String[][]... arg) {
		List<String[]> ls = new ArrayList<>();
		for (String[][] ar : arg) {
			for (String[] ar1 : ar) {
				ls.add(ar1);
			}
		}
		return ls;
	}

	private String[][] makeDirectOppositedProperties() {
		String[][] ans = new String[operation.length][];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = new String[] { operation[i], "-" + operation[i] };
		}
		return ans;
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
				+ "- a property preceded by minus must not be present in numbers;\n"
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
				if (s[j].startsWith("-")) {
					if (!checkProperty(startValue, s[j].toUpperCase())) {
						count++;
						continue;
					} else {
						break;
					}
				} else {
					if (checkProperty(startValue, s[j].toUpperCase())) {
						count++;
						continue;
					} else {
						break;
					}
				}
			}
			if (count == s.length - 2) {
				userStringArray[i] = String.valueOf(Long.valueOf(startValue));
				i++;
			}
		}
	}

	private boolean checkProperty(long startValue, String choice) {
		boolean passed = false;
		switch (choice) {
		case "EVEN":
		case "-EVEN":
			isOddOrEven(String.valueOf(startValue));
			if (Even) {
				passed = true;
			}
			break;
		case "ODD":
		case "-ODD":
			isOddOrEven(String.valueOf(startValue));
			if (!Even) {
				passed = true;
			}
			break;
		case "BUZZ":
		case "-BUZZ":
			isBuzz(String.valueOf(startValue));
			if (Buzz) {
				passed = true;
			}
			break;
		case "DUCK":
		case "-DUCK":
			isDuck(String.valueOf(startValue));
			if (Duck) {
				passed = true;
			}
			break;
		case "PALINDROMIC":
		case "-PALINDROMIC":
			isPalindromic(String.valueOf(startValue));
			if (Palindrome) {
				passed = true;
			}
			break;
		case "GAPFUL":
		case "-GAPFUL":
			isGapful(String.valueOf(startValue));
			if (Gapful) {
				passed = true;
			}
			break;
		case "SPY":
		case "-SPY":
			isSpy(String.valueOf(startValue));
			if (Spy) {
				passed = true;
			}
			break;
		case "SUNNY":
		case "-SUNNY":
			isSunny(String.valueOf(startValue));
			if (Sunny) {
				passed = true;
			}
			break;
		case "SQUARE":
		case "-SQUARE":
			isSquare(String.valueOf(startValue));
			if (Square) {
				passed = true;
			}
			break;
		case "JUMPING":
		case "-JUMPING":
			isJumping(String.valueOf(startValue));
			if (Jumping) {
				passed = true;
//				if (String.valueOf(startValue).endsWith("0")) {
//					startValue = startValue * 10 + 1 - 1;
//				} else if (String.valueOf(startValue).endsWith("9")) {
//					startValue = startValue * 10 + 8 - 1;
//				} else {
//					startValue = startValue * 10 + startValue % 10 + 1 - 1;
//				}
			}
			break;
		case "HAPPY":
		case "-HAPPY":
			isHappy(String.valueOf(startValue));
			if (Happy) {
				passed = true;
			}
			break;
		case "SAD":
		case "-SAD":
			isHappy(String.valueOf(startValue));
			if (!Happy) {
				passed = true;
			}
			break;
		}
		return passed;
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

	private void isHappy(String s) {
		if (Long.valueOf(s) == 1) {
			Happy = true;
			return;
		}
		String temp = s.length() == 1 ? String.valueOf((int) Math.pow(Integer.valueOf(s), 2)) : s;
		char[] number = temp.toCharArray();
		while (number.length != 1) {
			long sum = 0;
			for (int i = 0; i < number.length; i++) {
				sum += Math.pow(Character.getNumericValue(number[i]), 2);
			}
			number = String.valueOf(sum).toCharArray();
		}
		if (Integer.valueOf(new String(number)) == 1) {
			Happy = true;
		} else {
			Happy = false;
		}
		return;
	}

	private void printProperties(String s) {
		String[] properties = Arrays.stream(operation).map(String::toLowerCase).toArray(String[]::new);
		Boolean[] propertyValues = { Even, !Even, Buzz, Duck, Palindrome, Gapful, Spy, Square, Sunny, Jumping, Happy,
				!Happy };
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
