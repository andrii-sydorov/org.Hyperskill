package AmazingNumbers.Stage04;

import java.util.Scanner;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
			if (!checkInput(userStringArray)) {
				continue;
			}
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
	
	private boolean checkInput(String[] userStringArray) {
		for (String s : userStringArray) {
			if (!isNatural(s)) {
				if ()
				System.out.println("The first parameter should be a natural number or zero.");
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
				+ "- separate the parameters with one space;\r\n" + "- enter 0 to exit.\n");
	}

	private void userInput(Scanner sc) {
		System.out.print("Enter a request: ");
		String[] data = sc.nextLine().split(" ");
		int startValue = Integer.parseInt(data[0]);
		if (data.length == 1) {
			userStringArray = new String[] { data[0] };
			System.out.println();
			return;
		}
		int dataLength = Integer.parseInt(data[1]);
		userStringArray = dataLength > 0 ? new String[dataLength] : new String[1];
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
		if (n < 1) {
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
		isGapful = Long.parseLong(s) % divider == 0 ? true : false;
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
