package AmazingNumbers.Stage01;

import java.util.Scanner;

/**
 * Description
 * 
 * Integers can be even or odd. All numbers that end with 0, 2, 4, 6, or 8 are
 * even; the rest are odd. However, numbers have many other properties, you will
 * learn about some of them in this project.
 * 
 * First, let's talk about Buzz numbers. They are numbers that are either
 * divisible by 7 or end with 7. For example, the number 14 is a buzz number,
 * since it is divisible by 7 without a remainder; the number 17 ends with 7, so
 * it is also a buzz number. However, the number 75 is not a Buzz number, since
 * it is neither divisible by 7 nor does it end with 7. The number 7 is a Buzz
 * number too.
 * 
 * Your task at this stage is to write a program that prints the natural number
 * parity and determines whether this number is a Buzz. The program should print
 * why this number is a Buzz number.
 * 
 * Do you know how to determine whether a number is divisible by 7 or not? We
 * have a great tip:
 * 
 * - Remove the last digit; 
 * - Multiply the removed digit by 2 and subtract it from the remaining number. 
 * - If the result of the subtraction can be divided by 7, then the initial number 
 * is divisible by 7. You can apply the whole sequence multiple times until you get 
 * a relatively small/easy-to-interpret subtraction result.
 * 
 * For example, take 196. We remove the last digit and get 19. We subtract 12
 * (the removed digit multiplied by 2) to get 7. Since the last left digit is 7,
 * the number is multiple of 7. So 196 is a Buzz number!
 * 
 * You can use any solution you want, the tests won't check it. 
 * 
 * Objectives
 * 
 * Your program should check whether the given natural number is a buzz number.
 * 
 * 1) Ask a user to enter a natural number. 
 * 2) If the number is not natural, print an error message. 
 * 3) Calculate and print the parity of the number. 
 * 4) Check whether is the number is a Buzz number and print the explanation. 
 * 5) Finish the program after printing the message.
 * 
 * Natural numbers are whole numbers starting from 1.
 * 
 * Explanations:
 * 
 * ... is neither divisible by 7 nor does it end with 7
 * 
 * ... is divisible by 7
 * 
 * ... ends with 7
 * 
 * ... is divisible by 7 and ends with 7
 * 
 * Error message:
 * 
 * This number is not natural!
 * 
 * Examples
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1:
 * 
 * Enter a natural number: 
 * > 13 
 * This number is Odd. 
 * It is not a Buzz number.
 * Explanation: 
 * 13 is neither divisible by 7 nor does it end with 7.
 * 
 * Example 2:
 * 
 * Enter a natural number: 
 * > 14 
 * This number is Even. 
 * It is a Buzz number.
 * Explanation: 
 * 14 is divisible by 7.
 * 
 * Example 3:
 * 
 * Enter a natural number: 
 * > 17 
 * This number is Odd. 
 * It is a Buzz number.
 * Explanation: 
 * 17 ends with 7.
 * 
 * Example 4:
 * 
 * Enter a natural number: 
 * > 7 
 * This number is Odd. 
 * It is a Buzz number.
 * Explanation: 
 * 7 is divisible by 7 and ends with 7.
 * 
 * Example 5:
 * 
 * Enter a natural number: 
 * -76 
 * This number is not natural!
 * 
 * @author SMD_ASY
 *
 */

public class Main {

	private final Scanner sc = new Scanner(System.in);
	private String s;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();
		m.enterNumber(m.sc);
		if (!m.isNatural(m.s)) {
			System.out.println("This number is not natural!");
			return;
		}
		m.isOdd();
		m.isBuzz();

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

	private void isOdd() {
		String ans = null;
		if (Integer.valueOf(s) % 2 != 0) {
			ans = "This number is Odd.";
		} else {
			ans = "The number is Even";
		}
		System.out.println(ans);
	}

	private void isBuzz() {
		String ans = null;
		boolean endsWith7 = false;
		boolean isDivisibleBy7 = false;
		if (s.endsWith("7")) {
			endsWith7 = true;
		}
		if (Integer.valueOf(s) % 7 == 0) {
			isDivisibleBy7 = true;
		}
		boolean isBuzz = endsWith7 || isDivisibleBy7;
		if (isDivisibleBy7 == true && endsWith7 == true) {
			ans = s + " is divisible by 7 and ends with 7.";
		} else if (isDivisibleBy7 == true && endsWith7 == false) {
			ans = s + " is divisible by 7.";
		} else if (isDivisibleBy7 == false && endsWith7 == true) {
			ans = s + " ends with 7.";
		} else {
			ans = s + " is neither divisible by 7 nor does it end with 7.";
		}
		if (isBuzz) {
			System.out.println("It is a Buzz number.");
		} else {
			System.out.println("It is not a Buzz number.");
		}
		System.out.println("Explanation:");
		System.out.println(ans);
	}

}
