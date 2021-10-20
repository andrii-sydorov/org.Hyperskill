package AmazingNumbers.Stage08;

import java.util.Scanner;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Arrays;
import java.util.Collections;

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
		String temp = s.length() == 1 ? String.valueOf((int)Math.pow(Integer.valueOf(s), 2)) : s;
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
