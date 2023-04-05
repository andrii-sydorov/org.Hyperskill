package projects.Easy.ChatBot.Stage04;

import java.util.Calendar;
import java.util.Scanner;

/**
 * Description
 * 
 * Now you will teach your bot to count. It's going to become an expert in
 * numbers!
 * 
 * Objective
 * 
 * In this stage, you will program the bot to count from 0 to any positive
 * number users enter.
 * 
 * Example
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1: a dialogue with the new version of the bot
 * 
 * Hello! My name is Aid. 
 * I was created in 2020. 
 * Please, remind me your name. 
 * >Max 
 * What a great name you have, Max! 
 * Let me guess your age. Enter remainders of dividing your age by 3, 5 and 7. 
 * > 1 
 * > 2 
 * > 1 
 * Your age is 22; that's a good time to start programming! 
 * Now I will prove to you that I can count to any number you want. 
 * > 5 
 * 0! 
 * 1! 
 * 2! 
 * 3! 
 * 4! 
 * 5! 
 * Completed, have a nice day!
 * 
 * Note: each number starts with a new line, and after a number, the bot should
 * print the exclamation mark.
 * 
 * Use the provided template to simplify your work. You can change the text if
 * you want, but be especially careful when counting numbers.
 * 
 * @author ASY
 *
 */

public class SimpleBot {

	private String nameChatBot;
	private Calendar date;
	private User user;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		SimpleBot simpleBot = new SimpleBot("andrii", Calendar.getInstance());
		simpleBot.start("andrii");
		simpleBot.getInfo(sc);
		simpleBot.ageCalculation(sc);
		simpleBot.toCount(sc);
	}

	public SimpleBot(String nameChatBot, Calendar dateOfCreation) {
		this.nameChatBot = nameChatBot;
		this.date = dateOfCreation;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////

	private void start(String name) {
		printGreetings(name);
		printYearOfCreation(this.date);
	}

	private void printGreetings(String name) {
		System.out.println("Hello! My name is " + name + ".");
	}

	private void printYearOfCreation(Calendar date) {
		System.out.println("I was created in " + date.get(Calendar.YEAR) + ".");
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////

	private void getInfo(Scanner sc) {
		user = new User();
		askNameOfUser();
		getUserName(sc);
		printUserName();
	}

	private void askNameOfUser() {
		System.out.println("Please, remind me your name.");
	}

	private void getUserName(Scanner sc) {
		user.userName = sc.nextLine();
	}

	private void printUserName() {
		System.out.println("What a great name you have, " + this.user.userName + ".");
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////

	private void ageCalculation(Scanner sc) {
		askRemainder();
		user.setRemainder(sc);
		user.setAge(calcAge());
		printUserAge();
	}

	private void askRemainder() {
		System.out.println("Let me guess your age.\r\n" + "Enter remainders of dividing your age by 3, 5 and 7.");
	}

	private int calcAge() {
		int ans = 0;
		for (int i = 0; i < user.remainder.length; i++) {
			ans += user.remainder[i] * user.magicNumber[i];
		}
		return ans % 105;
	}

	private void printUserAge() {
		System.out.println("Your age is " + user.age + "; that's a good time to start programming!");
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////

	private void toCount(Scanner sc) {
		suggest();
		askNumberFromUser(sc);
		countToNumber();
		complete();
	}

	private void suggest() {
		System.out.println("Now I will prove to you that I can count to any number you want.");
	}

	private void askNumberFromUser(Scanner sc) {
		user.numberToCount = sc.nextInt();
	}

	private void countToNumber() {
		int finish = user.numberToCount;
		for (int i = 0; i <= finish; i++) {
			System.out.println(i + "!");
		}
	}

	private void complete() {
		System.out.println("Completed, have a nice day!");
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////

}

class User {

	public String userName;
	public int age;
	public int ageToStartProgramm;
	public int[] remainder = new int[3];
	public int[] magicNumber = { 70, 21, 15 };
	public int numberToCount;

	public void setRemainder(Scanner sc) {
		int n = 3;
		for (int i = 0; i < n; i++) {
			remainder[i] = sc.nextInt();
		}
	}

	public void setAge(int i) {
		this.age = i;
	}
}
