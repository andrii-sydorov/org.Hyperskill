package projects.Easy.ChatBot.Stage03;

import java.util.Calendar;
import java.util.Scanner;

/**
 * Description
 * 
 * Keep improving your bot by developing new skills for it. We suggest a simple
 * guessing game that will predict the age of a user.
 * 
 * It's based on a simple math trick. First, take a look at this formula:
 * 
 * age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105
 * 
 * The numbers remainder3, remainder5, and remainder7 are the remainders of the
 * division of age by 3, 5, and 7 respectively.
 * 
 * It turns out that for each number ranging from 0 to 104, the calculation will
 * result in the number itself. This perfectly fits the ordinary age range,
 * doesn't it? Ask the user for the remainders and use them to guess the age!
 * Objective
 * 
 * In this stage, you will introduce yourself to the bot. It will greet you by
 * your name and then try to guess your age using arithmetic operations.
 * 
 * Your program should print the following lines:
 * 
 * Hello! My name is Aid. 
 * I was created in 2020. 
 * Please, remind me your name.
 * What a great name you have, Max! 
 * Let me guess your age. 
 * Enter remainders of dividing your age by 3, 5 and 7. 
 * Your age is {yourAge}; that's a good time to start programming!
 * 
 * Read three numbers from the standard input. Assume that all the numbers will
 * be given on separate lines.
 * 
 * Instead of {yourAge}, the bot will print the age determined according to the
 * special formula discussed above. 
 * 
 * Example
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1: a dialogue with the bot
 * 
 * Hello! My name is Aid. 
 * I was created in 2020. 
 * Please, remind me your name. 
 * >Max 
 * What a great name you have, Max! 
 * Let me guess your age. 
 * Enter remainders of dividing your age by 3, 5 and 7. 
 * > 1 
 * > 2 
 * > 1 
 * Your age is 22; that's a good time to start programming!
 * 
 * Use the provided template to simplify your work. You can change the text but
 * not the number of printed lines.
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
	}

	public SimpleBot(String nameChatBot, Calendar dateOfCreation) {
		this.nameChatBot = nameChatBot;
		this.date = dateOfCreation;
	}

	private void start(String name) {
		printGreetings(name);
		printYearOfCreation(this.date);
	}

	private void getInfo(Scanner sc) {
		user = new User();
		askNameOfUser();
		getUserName(sc);
		printUserName();
	}

	private void ageCalculation(Scanner sc) {
		askRemainder();
		user.setRemainder(sc);
		user.setAge(calcAge());
		printUserAge();
	}

	private void printUserAge() {
		System.out.println("Your age is " + user.age + "; that's a good time to start programming!");
	}

	private int calcAge() {
		int ans = 0;
		for (int i = 0; i < user.remainder.length; i++) {
			ans += user.remainder[i] * user.magicNumber[i];
		}
		return ans % 105;
	}

	private void askRemainder() {
		System.out.println("Let me guess your age.\r\n" + "Enter remainders of dividing your age by 3, 5 and 7.");
	}

	private void printUserName() {
		System.out.println("What a great name you have, " + this.user.userName + ".");
	}

	private void getUserName(Scanner sc) {
		user.userName = sc.nextLine();
	}

	private void askNameOfUser() {
		System.out.println("Please, remind me your name.");
	}

	private void printGreetings(String name) {
		System.out.println("Hello! My name is " + name + ".");
	}

	private void printYearOfCreation(Calendar date) {
		System.out.println("I was created in " + date.get(Calendar.YEAR) + ".");
	}
}

class User {

	public String userName;
	public int age;
	public int ageToStartProgramm;
	public int[] remainder = new int[3];
	public int[] magicNumber = { 70, 21, 15 };

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
