package AmazingNumbers.Stage02;

import java.util.Scanner;

public class Main {

	private final Scanner sc = new Scanner(System.in);
	private String s;
	private boolean isBuzz;
	private boolean isOdd;
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
			isOdd = false;
		} else {
			isEven = false;
			isOdd = true;
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
		String[] properties = {"even", "odd", "buzz", "duck"};
		Boolean[] propertyValues = {isEven, isOdd, isBuzz, isDuck};
		System.out.println("Properties of " + s);
		for (int i = 0; i < properties.length; i++) {
			System.out.println(properties[i] + ": " + propertyValues[i]);
		}
	}

}
