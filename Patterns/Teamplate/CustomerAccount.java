package Patterns.Teamplate;

import java.util.Scanner;

/**
 * New customer
 * 
 * 
 * There are three classes: an abstract class Customer with the template method,
 * and two concrete classes, Premium and Standard. You must implement the
 * template method in the Customer class to create an account using the
 * following algorithm:
 * 
 * Verify customer's identity Generate customer's identification number Send a
 * thank you message Send a welcome gift
 * 
 * Your task is to make the Premium and Standard classes inherit from the class
 * Customer, and write the methods generateCustomerID() and sendGift() according
 * to the console output. Report a typo
 * 
 * Sample Input 1:
 * 
 * premium
 * 
 * Sample Output 1:
 * 
 * Verify your identity 
 * Your premium account identification number: PA-01 
 * Thank you for creating a new customer account! 
 * You received 100 Gems
 * 
 * Sample Input 2:
 * 
 * standard
 * 
 * Sample Output 2:
 * 
 * Verify your identity 
 * Your standard account identification number: ST-01 
 * Thank you for creating a new customer account! 
 * You received 50 Gems
 * 
 * @author SMD_ASY
 *
 */

public class CustomerAccount {
	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final String type = scanner.nextLine();
		scanner.close();
		Customer customer = null;
		if ("premium".equalsIgnoreCase(type)) {
			customer = new Premium();
		} else if ("standard".equalsIgnoreCase(type)) {
			customer = new Standard();
		} else {
			System.out.println("Error!");
			System.exit(0);
		}
		customer.createAccount();
	}
}

class Premium extends Customer {
	public void generateCustomerID() {
		String id = "PA-01";
		String account = "premium";
		System.out.printf("Your %s account identification number: %s\n", account, id);
	}

	public void sendGift() {
		int gift = 100;
		System.out.printf("You received %d Gems\n", gift);
	}
}

class Standard extends Customer {
	public void generateCustomerID() {
		String id = "ST-01";
		String account = "standard";
		System.out.printf("Your %s account identification number: %s\n", account, id);
	}

	public void sendGift() {
		int gift = 50;
		System.out.printf("You received %d Gems\n", gift);
	}
}

abstract class Customer {

	public void createAccount() {
		verifyIdentity();
		generateCustomerID();
		sayThankYou();
		sendGift();
	}

	public abstract void generateCustomerID();

	public abstract void sendGift();

	public void verifyIdentity() {
		System.out.println("Verify your identity");
	}

	public void sayThankYou() {
		System.out.println("Thank you for creating a new customer account!");
	}
}
