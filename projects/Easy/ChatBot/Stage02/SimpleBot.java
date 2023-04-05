package projects.Easy.ChatBot.Stage02;

import java.util.Calendar;
import java.util.Scanner;

/**
 * Description
 * 
 * The greeting part is great, but chatbots are also supposed to interact with a
 * user. It's time to implement this functionality. Objective
 * 
 * In this stage, you will introduce yourself to the bot so that it can greet
 * you by your name.
 * 
 * Your program should print the following lines:
 * 
 * Hello! 
 * My name is Aid. 
 * I was created in 2020. 
 * Please, remind me your name.
 * What a great name you have, {yourName}!
 * 
 * You may change the name and the creation year of your bot if you want.
 * 
 * Instead of {yourName}, the bot must print your name entered from the standard
 * input. 
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
 * > Max 
 * What a great name you have, Max!
 * 
 * Use the provided template to simplify your work. You can change the text but
 * not the number of printed lines.
 * 
 * 
 * @author SMD_ASY
 *
 */

public class SimpleBot {

	private String nameChatBot;
	private Calendar date;
	private String userName;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		SimpleBot simpleBot = new SimpleBot("andrii", Calendar.getInstance());
		simpleBot.start("andrii");
		simpleBot.getInfo(sc);
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
		askNameOfUser();
		getUserName(sc);
		printUserName();
	}

	private void printUserName() {
		System.out.println("What a great name you have, " + this.userName + ".");
	}

	private void getUserName(Scanner sc) {
		this.userName = sc.nextLine();
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
