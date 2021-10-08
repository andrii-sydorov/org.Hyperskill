package AmazingNumbers.Stage02;

import java.util.Scanner;

/**
 * Description
 * 
 * Your next task is to determine whether a number is a Duck number. A Duck
 * number is a positive number that contains zeroes. For example, 3210, 8050896,
 * 70709 are Duck numbers. Note that a number with a leading 0 is not a Duck
 * number. So, numbers like 035 or 0212 are not Duck numbers. Although, 01203 is
 * a Duck, since it has a trailing 0.
 * 
 * In this stage, we need to simplify the way we display the information. We
 * already have several properties that we need to show; we are going to add new
 * features to our program in each stage. From now on, the card should follow
 * this notation:
 * 
 * Properties of {number} 
 * {property}: true/false 
 * {property}: true/false ...
 * {property}: true/false
 * 
 * In this stage, the properties are even, odd, buzz and duck. For 14, the
 * program output should look like this:
 * 
 * Properties of 14 
 * even: true 
 * odd: false 
 * buzz: true 
 * duck: false
 * 
 * The property order, indentation, and spaces are not checked by the tests. The
 * tests are also case-insensitive. 
 * 
 * Objectives
 * 
 * Your program should print the properties of a natural number. In this stage,
 * your program should:
 * 
 * 1) Ask a user to enter a natural number; 
 * 2) If the number is not natural, the program should print an error message; 
 * 3) If the number is natural, the program should indicate the properties of the number; 
 * 4) Finish the program after printing the message.
 * 
 * Examples
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1:
 * 
 * Enter a natural number: 
 * > -7 
 * This number is not natural!
 * 
 * Example 2:
 * 
 * Enter a natural number: 
 * > 15 
 * Properties of 15 
 * even: false
 * odd: true 
 * buzz: false 
 * duck: false
 * 
 * Example 3:
 * 
 * Enter a natural number: 
 * > 14 
 * Properties of 14 
 * even: true 
 * odd: false 
 * buzz: true 
 * duck: false
 * 
 * Example 4:
 * 
 * Enter a natural number: 
 * > 102 
 * Properties of 102 
 * even: true 
 * odd: false 
 * buzz: false 
 * duck: true
 * 
 * @author SMD_ASY
 *
 */

public class Main {

	private final Scanner sc = new Scanner(System.in);
	private String s;
	private boolean isBuzz;
	private boolean isEven;
	private boolean isDuck;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();
		m.enterNumber(m.sc);
		if (!m.isNatural(m.s)) {
			System.out.println("This number is not natural!");
			return;
		}
		m.isOddOrEven();
		m.isBuzz();
		m.isDuckNumbers();
		m.printProperties();

	}

	private void enterNumber(Scanner sc) {
		System.out.println("Enter a natural number:");
		s = sc.nextLine();
	}

	private boolean isNatural(String s) {
		int number = 0;
		boolean isValid = true;
		try {
			number = Integer.valueOf(s);
		} catch (NumberFormatException nfe) {
			isValid = false;
		}
		if (number < 1) {
			isValid = false;
		}
		return isValid;
	}

	private void isOddOrEven() {
		if (Integer.valueOf(s) % 2 == 0) {
			isEven = true;
		} 
	}

	private void isBuzz() {
		boolean endsWith7 = false;
		boolean isDivisibleBy7 = false;
		if (s.endsWith("7")) {
			endsWith7 = true;
		}
		if (Integer.valueOf(s) % 7 == 0) {
			isDivisibleBy7 = true;
		}
		isBuzz = endsWith7 || isDivisibleBy7;
	}

	private void isDuckNumbers() {
		if (s.contains("0")) {
			isDuck = true;
		}
	}

	private void printProperties() {
		String[] properties = { "even", "odd", "buzz", "duck" };
		Boolean[] propertyValues = { isEven, !isEven, isBuzz, isDuck };
		System.out.println("Properties of " + s);
		for (int i = 0; i < properties.length; i++) {
			System.out.println(properties[i] + ": " + propertyValues[i]);
		}
	}

}
