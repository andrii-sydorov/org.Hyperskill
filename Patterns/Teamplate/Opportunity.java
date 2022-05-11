package Patterns.Teamplate;

import java.util.Scanner;

/**
 * There are three classes: an abstract class Career, and two concrete classes,
 * Engineer and DataScientist. Your task is to implement the abstract class
 * Career with a template method called execute() and an abstract method called
 * work() using the following algorithm:
 * 
 * Dream 
 * Plan 
 * Study 
 * Work
 * 
 * Make the classes Engineer and DataScientist inherit from the Career class and
 * implement the methods according to the console output. Report a typo
 * 
 * Sample Input 1:
 * 
 * engineer
 * 
 * Sample Output 1:
 * 
 * Dream big! 
 * Draw a plan! 
 * Study! 
 * Work as a Full Stack Engineer
 * 
 * Sample Input 2:
 * 
 * scientist
 * 
 * Sample Output 2:
 * 
 * Dream big! 
 * Draw a plan! 
 * Study! 
 * Work as a Data Scientist
 * 
 * @author SMD_ASY
 *
 */

public class Opportunity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		Career c = null;

		if (s.equalsIgnoreCase("engineer")) {
			c = new Engineer();
		} else if (s.equalsIgnoreCase("scientist")) {
			c = new DataScientist();
		} else {
			System.out.println("Error!");
			System.exit(0);
		}
		sc.close();
		c.excute();
	}

}

abstract class Career {

	public void excute() {
		dream();
		plan();
		study();
		work();
	}

	abstract void work();

	public void dream() {
		System.out.println("Dream big!");
	}

	public void plan() {
		System.out.println("Draw a plan!");
	}

	public void study() {
		System.out.println("Study!");
	}

}

class Engineer extends Career {

	public void work() {
		System.out.println("Work as a Full Stack Engineer");
	}
}

class DataScientist extends Career {

	public void work() {
		System.out.println("Work as a Data Scientist");
	}
}
