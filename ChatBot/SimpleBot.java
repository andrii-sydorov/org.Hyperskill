package ChatBot;

import java.util.Calendar;

/**
 * Description
 * 
 * Digital personal assistants help people to drive cars, plan their day, buy
 * something online. In a sense, they are simplified versions of artificial
 * intelligence with whom you can talk.
 * 
 * In this project, you will develop step by step a simple bot that will help
 * you study programming. Objective
 * 
 * For the first stage, you will write a bot that displays a greeting, its name,
 * and the date of its creation. First impressions count!
 * 
 * Your program should print the following lines:
 * 
 * Hello! My name is {botName}. 
 * I was created in {birthYear}.
 * 
 * Instead of {botName}, print any name you choose and replace {birthYear} with
 * the current year (four digits). 
 * 
 * Example
 * 
 * Output:
 * 
 * Hello! My name is Aid. 
 * I was created in 2020.
 * 
 * You can change the text if you want but print exactly two lines.
 * 
 * Next, we will use Aid and 2020 as your bot's name and its birth year, but you
 * can change it if you need to.
 * 
 * @author SMD_ASY
 *
 */

public class SimpleBot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String name = "Andrii";
		final Calendar date = Calendar.getInstance();
		printGreetings(name);
		printYearOfCreation(date.get(Calendar.YEAR));
	}
	
	private static void printGreetings(String name) {
		System.out.println("Hello! My name is " + name + ".");
	}
	
	private static void printYearOfCreation(int d) {
		System.out.println("I was created in " + d + ".");
	}
 
}
