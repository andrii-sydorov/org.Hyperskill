package SimpleBankingSystem.Stage01;

import java.util.Random;
import java.util.Scanner;

/**
 * Card anatomy
 * Description
 * We live busy lives these days. Between work, chores, and other things in our
 * to-do lists, it can be tough to catch your breath and stay calm. Credit cards
 * are one of the things that save us time, energy, and nerves. From not having
 * to carry a wallet full of cash to consumer protection, cards make our lives
 * easier in many ways. In this project, you will develop a simple banking
 * system with a database.
 * 
 * If you’re curious about business, technology, or how things around you work,
 * you'll probably enjoy learning how credit card numbers work. These numbers
 * ensure easy payments, and they also help prevent payment errors and fraud.
 * Card numbers are evolving, and they might look different in the near future.
 * 
 * Let's take a look at the anatomy of a credit card:
 * 
 * 
 * 
 * The very first number is the Major Industry Identifier (MII), which tells you
 * what sort of institution issued the card.
 * 
 * 1 and 2 are issued by airlines
 * 3 is issued by travel and entertainment
 * 4 and 5 are issued by banking and financial institutions
 * 6 is issued by merchandising and banking
 * 7 is issued by petroleum companies
 * 8 is issued by telecommunications companies
 * 9 is issued by national assignment
 * In our banking system, credit cards should begin with 4.
 * 
 * The first six digits are the Bank Identification Number (BIN). These can be
 * used to look up where the card originated from. If you have access to a list
 * that provides detail on who owns each BIN, you can see who issued the card
 * just by reading the card number.
 * 
 * Here are a few you might recognize:
 * 
 * Visa: 4*****
 * American Express (AMEX): 34**** or 37****
 * Mastercard: 51**** to 55****
 * In our banking system, the BIN must be 400000.
 * 
 * The seventh digit to the second-to-last digit is the customer account number.
 * Most companies use just 9 digits for the account numbers, but it’s possible
 * to use up to 12. This means that using the current algorithm for credit
 * cards, the world can issue about a trillion cards before it has to change the
 * system.
 * 
 * We often see 16-digit credit card numbers today, but it’s possible to issue a
 * card with up to 19 digits using the current system. In the future, we may see
 * longer numbers becoming more common.
 * 
 * In our banking system, the customer account number can be any, but it should
 * be unique. And the whole card number should be 16-digit length.
 * 
 * The very last digit of a credit card is the check digit or checksum. It is
 * used to validate the credit card number using the Luhn algorithm, which we
 * will explain in the next stage of this project. For now, the checksum can be
 * any digit you like.
 * 
 * Objectives
 * You should allow customers to create a new account in our banking system.
 * 
 * Once the program starts, you should print the menu:
 * 
 * 1. Create an account
 * 2. Log into account
 * 0. Exit
 * If the customer chooses ‘Create an account’, you should generate a new card
 * number which satisfies all the conditions described above. Then you should
 * generate a PIN code that belongs to the generated card number. A PIN code is
 * a sequence of any 4 digits. PIN should be generated in a range from 0000 to
 * 9999.
 * 
 * If the customer chooses ‘Log into account’, you should ask them to enter
 * their card information. Your program should store all generated data until it
 * is terminated so that a user is able to log into any of the created accounts
 * by a card number and its pin. You can use an array to store the information.
 * 
 * After all information is entered correctly, you should allow the user to
 * check the account balance; right after creating the account, the balance
 * should be 0. It should also be possible to log out of the account and exit
 * the program.
 * 
 * Example
 * The symbol > represents the user input. Notice that it's not a part of the
 * input.
 * 
 * 1. Create an account
 * 2. Log into account
 * 0. Exit
 * >1
 * 
 * Your card has been created
 * Your card number:
 * 4000004938320895
 * Your card PIN:
 * 6826
 * 
 * 1. Create an account
 * 2. Log into account
 * 0. Exit
 * >2
 * 
 * Enter your card number:
 * >4000004938320895
 * Enter your PIN:
 * >4444
 * 
 * Wrong card number or PIN!
 * 
 * 1. Create an account
 * 2. Log into account
 * 0. Exit
 * >2
 * 
 * Enter your card number:
 * >4000004938320895
 * Enter your PIN:
 * >6826
 * 
 * You have successfully logged in!
 * 
 * 1. Balance
 * 2. Log out
 * 0. Exit
 * >1
 * 
 * Balance: 0
 * 
 * 1. Balance
 * 2. Log out
 * 0. Exit
 * >2
 * 
 * You have successfully logged out!
 * 
 * 1. Create an account
 * 2. Log into account
 * 0. Exit
 * >0
 * 
 * Bye!
 */

public class CardAnatomy {

	private static int userChoice;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserAccount ua = new UserAccount(sc);
		boolean isRunning = true;
		while (isRunning) {
			printMenu();
			getUserChoice(sc);
			switch (userChoice) {
				case 1:
					ua.createAccount();
					break;
				case 2:
					ua.createUser();
					ua.checkingInfo();
					if (ua.acces) {
						authorizedWork(sc, ua);
					}
					break;
				case 0:
					isRunning = false;
					break;
			}
			System.out.println();
		}
		System.out.println("Bye!");
		sc.close();
	}

	private static void authorizedWork(Scanner sc, UserAccount ua) {
		System.out.println();
		boolean logOn = true;
		while (logOn) {
			printLogOnData();
			getUserChoice(sc);
			switch (userChoice) {
				case 1:
					printBalance(ua);
					break;
				case 2:
					printLogOut();
					logOn = false;
					break;
				case 0:
					logOn = false;
					break;
			}
		}
	}

	private static void printLogOut() {
		System.out.println();
		System.out.println("You have successfully logged out!");
	}

	private static void printLogOnData() {
		for (UserAccountMenu ua : UserAccountMenu.values()) {
			System.out.println(ua);
		}
	}

	private static void printBalance(UserAccount ua) {
		System.out.println();
		System.out.println("Balance: " + ua.getBalance());
		System.out.println();
	}

	private static void getUserChoice(Scanner sc) {
		userChoice = Integer.parseInt(sc.nextLine());
	}

	private static void printMenu() {
		for (Menu m : Menu.values()) {
			System.out.println(m.toString());
		}
	}

}

class UserAccount {
	private Card c;
	private User u;
	Scanner sc;
	public boolean acces;
	private int balance;

	public UserAccount(Scanner sc) {
		this.sc = sc;
		c = new Card();
		u = new User();
	}

	public void createAccount() {
		c.createPin();
		c.createCardNumber();
		c.printInfo();
	}

	public void createUser() {
		u.getUserData(sc);
	}

	public void checkingInfo() {
		if (c.getCardNumber().equals(u.getCardNumber()) && c.getPin().equals(u.getPin())) {
			System.out.println("You have successfully logged in!");
			acces = true;
		} else {
			System.out.println("Wrong card number or PIN!");
			acces = false;
		}
	}

	public int getBalance() {
		return this.balance;
	}
}

class User {

	private String pin;
	private String cardNumber;

	public void getUserData(Scanner sc) {
		System.out.println();
		System.out.println("Enter your card number:");
		cardNumber = sc.nextLine();
		System.out.println("Enter your PIN:");
		pin = sc.nextLine();
		System.out.println();
	}

	public String getPin() {
		return this.pin;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}
}

class Card {
	private String BIN = "400000";
	private String pin;
	private int pinLength = 4;
	private String cardNumber;
	private int cardNumberLength = 16;
	Random r = new Random();

	public void printInfo() {
		System.out.println();
		System.out.println("Your card has been created");
		System.out.println("Your card number:");
		System.out.println(getCardNumber());
		System.out.println("Your card PIN:");
		System.out.println(getPin());
	}

	public void createPin() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < pinLength; i++) {
			sb.append(r.nextInt(9));
		}
		pin = sb.toString();
	}

	public void createCardNumber() {
		StringBuilder sb = new StringBuilder();
		sb.append(BIN);
		for (; cardNumberLength != sb.length();) {
			sb.append(r.nextInt(9));
		}
		cardNumber = sb.toString();
	}

	public String getPin() {
		return this.pin;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}
}

enum Menu {
	CREATE(1, "Create an account"), LOG(2, "Log into account"), EXIT(0, "Exit");

	private int index;
	private String description;

	Menu(int index, String description) {
		this.index = index;
		this.description = description;
	}

	public int getIndex() {
		return this.index;
	}

	public String getDescription() {
		return this.description;
	}

	public String toString() {
		return getIndex() + ". " + getDescription();
	}
}

enum UserAccountMenu {
	BALANCE(1, "Balance"), LOGOUT(2, "Log out"), EXIT(0, "Exit");

	private int index;
	private String description;

	UserAccountMenu(int index, String description) {
		this.index = index;
		this.description = description;
	}

	public int getIndex() {
		return this.index;
	}

	public String getDescription() {
		return this.description;
	}

	public String toString() {
		return getIndex() + ". " + getDescription();
	}
}
