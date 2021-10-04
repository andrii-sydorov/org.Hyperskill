package ChatBot.Stage05;

import java.util.Scanner;

/**
 * Description
 * 
 * At the final stage, you will improve your simple bot so that it can give you
 * a test and check your answers. The test should be a multiple-choice quiz
 * about programming. Your bot has to repeat the test until you answer correctly
 * and congratulate you upon completion. 
 * 
 * Objective
 * 
 * Your bot can ask anything you want, but there are two rules for your output:
 * 
 * - the line with the test should end with the question mark character; 
 * - an option starts with a digit followed by the dot (1., 2., 3., 4.)
 * 
 * If a user enters an incorrect answer, the bot may print a message:
 * 
 * Please, try again.
 * 
 * The program should stop on the correct answer and print Congratulations, have
 * a nice day! at the end. 
 * 
 * Example
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1: a dialogue with the final version of your bot
 * 
 * Hello! My name is Aid. 
 * I was created in 2020. 
 * Please, remind me your name. > Max 
 * What a great name you have, Max! 
 * Let me guess your age. 
 * Enter the remainders of dividing your age by 3, 5 and 7. 
 * > 1 
 * > 2 
 * > 1 
 * Your age is 22: that's a good time to start programming! 
 * Now I will prove to you that I can count to any number you want. 
 * > 3 
 * 0! 
 * 1! 
 * 2! 
 * 3! 
 * Let's test your programming knowledge. 
 * Why do we use methods? 
 * 1. To repeat a statement multiple times. 
 * 2. To decompose a program into several small subroutines. 
 * 3. To determine the execution time of a program. 
 * 4. To interrupt the execution of a program. 
 * > 4
 * Please, try again. 
 * > 2 
 * Congratulations, have a nice day!
 * 
 * The program must end with the Congratulations, have a nice day! message.
 * 
 * Use the provided template to simplify your work. You can change the text if
 * you want. Please note that we use functions to make it easy to understand the
 * program and add new code to it or edit later.
 * 
 * @author SMD_ASY
 *
 */

public class SimpleBot {
	final static Scanner scanner = new Scanner(System.in); // Do not change this line

	public static void main(String[] args) {
		greet("Aid", "2018"); // change it as you need
		remindName();
		guessAge();
		count();
		test();
		end();
	}

	static void greet(String assistantName, String birthYear) {
		System.out.println("Hello! My name is " + assistantName + ".");
		System.out.println("I was created in " + birthYear + ".");
		System.out.println("Please, remind me your name.");
	}

	static void remindName() {
		String name = scanner.nextLine();
		System.out.println("What a great name you have, " + name + "!");
	}

	static void guessAge() {
		System.out.println("Let me guess your age.");
		System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");
		int rem3 = scanner.nextInt();
		int rem5 = scanner.nextInt();
		int rem7 = scanner.nextInt();
		int age = (rem3 * 70 + rem5 * 21 + rem7 * 15) % 105;
		System.out.println("Your age is " + age + "; that's a good time to start programming!");
	}

	static void count() {
		System.out.println("Now I will prove to you that I can count to any number you want.");
		int num = scanner.nextInt();
		for (int i = 0; i <= num; i++) {
			System.out.printf("%d!\n", i);
		}
	}

	static void test() {
		System.out.println("Let's test your programming knowledge.");
		// write your code here
		System.out.println("What are some benefits of using Java modules?");
		String[] arr = { "It increases program performance", "Strong enforcement of data hiding",
				"It cuts development time in half", "It can make standalone applications",
				"It can decrease program size" };
		printVariants(arr);
		checkVariants(scanner);
	}

	static void printVariants(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println((i + 1) + " ." + arr[i]);
		}
	}

	static void checkVariants(Scanner sc) {
		String corAnswer = "245";
		while (!corAnswer.contains(String.valueOf(sc.nextInt()))) {
			System.out.println("Please, try again.");
		}
	}

	static void end() {
		System.out.println("Congratulations, have a nice day!"); // Do not change this text
	}
}
