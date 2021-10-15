package AmazingNumbers.Stage06;

import java.util.Scanner;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Arrays;

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
	private boolean complexInput;
	private static final String[] operation = { "EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE",
			"SUNNY" };

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
		case 4:
			return isValidFourArgument(userData) && isNotExclusiveProperties(userData);
		}
		complexInput = userData.length > 1 ? true : false;
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

	private boolean isValidFourArgument(String[] s) {
		if (!isValidTwoArgument(s)) {
			return false;
		}
		boolean isFoundFirst = false;
		boolean isFoundSecond = false;
		for (String str : operation) {
			if (str.equalsIgnoreCase(s[2])) {
				isFoundFirst = true;
				break;
			}
		}
		for (String str : operation) {
			if (str.equalsIgnoreCase(s[3])) {
				isFoundSecond = true;
				break;
			}
		}
		if (!isFoundFirst && !isFoundSecond) {
			System.out.println("\nThe properties " + "[" + s[2] + ", " + s[3] + "]" + " are wrong.");
			System.out.println("Available properties: " + Arrays.toString(operation) + "\n");
			return false;
		} else if (!isFoundFirst) {
			System.out.println("\nThe property " + "[" + s[2]  + "]" + " is wrong.");
			System.out.println("Available properties: " + Arrays.toString(operation) + "\n");
			return false;
		} else if (!isFoundSecond) {
			System.out.println("\nThe property " + "[" + s[3]  + "]" + " is wrong.");
			System.out.println("Available properties: " + Arrays.toString(operation) + "\n");
			return false;
		}
		return true;
	}

	private boolean isNotExclusiveProperties(String[] s) {
		String[] toBeChecked = { s[2].toUpperCase(), s[3].toUpperCase() };
		Arrays.sort(toBeChecked);
		String[][] exclusiveProperties = { { "EVEN", "ODD" }, { "DUCK", "SPY" }, { "SUNNY", "SQUARE" } };
		for (String[] ar : exclusiveProperties) {
			Arrays.sort(ar);
			if (Arrays.deepEquals(toBeChecked, ar)) {
				System.out
						.println("The request contains mutually exclusive properties: " + Arrays.toString(toBeChecked));
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
			fillAccordingOneProperty(s, startValue, capacity);
			break;
		case 4:
			startValue = Long.valueOf(s[0]);
			capacity = Integer.valueOf(s[1]);
			fillAccordingTwoProperties(s, startValue, capacity);
			break;
		}
		System.out.println();
	}

	private void fillAccordingTwoProperties(String[] s, long startValue, int capacity) {
		// TODO Auto-generated method stub
		for (int i = 0; i < capacity; startValue++) {
			String firstChoice = s[2].toUpperCase();
			boolean firstProperty = checkProperty(startValue, firstChoice);
			String secondChoice = s[3].toUpperCase();
			boolean secondProperty = checkProperty(startValue, secondChoice);
			if (firstProperty && secondProperty) {
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
		}
		return firstPropery;
	}

	private void fillAccordingOneProperty(String[] s, long startValue, int capacity) {
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
			case "SUNNY":
				isSunny(String.valueOf(startValue));
				if (Sunny) {
					userStringArray[i] = String.valueOf(Long.valueOf(startValue));
					i++;
				}
				continue;
			case "SQUARE":
				isSquare(String.valueOf(startValue));
				if (Square) {
					userStringArray[i] = String.valueOf(Long.valueOf(startValue));
					i++;
				}
				continue;
			}
		}
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

	private void printProperties(String s) {
		String[] properties = { "buzz", "duck", "palindromic", "gapful", "spy", "square", "sunny", "even", "odd" };
		Boolean[] propertyValues = { Buzz, Duck, Palindrome, Gapful, Spy, Square, Sunny, Even, !Even, };
		// System.out.println("Properties of " + s);
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