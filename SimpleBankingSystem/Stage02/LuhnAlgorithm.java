package SimpleBankingSystem.Stage02;

import java.util.Random;
import java.util.Scanner;

/**
 * Luhn algorithm
 * Description
 * In this stage, we will find out what the purpose of the checksum is and what
 * the Luhn algorithm is used for.
 * 
 * The main purpose of the check digit is to verify that the card number is
 * valid. Say you're buying something online, and you type in your credit card
 * number incorrectly by accidentally swapping two digits, which is one of the
 * most common errors. When the website looks at the number you've entered and
 * applies the Luhn algorithm to the first 15 digits, the result won't match the
 * 16th digit on the number you entered. The computer knows the number is
 * invalid, and it knows the number will be rejected if it tries to submit the
 * purchase for approval, so you're asked to re-enter the number. Another
 * purpose of the check digit is to catch clumsy attempts to create fake credit
 * card numbers. Those who are familiar with the Luhn algorithm, however, could
 * get past this particular security measure.
 * 
 * Luhn Algorithm in action
 * 
 * The Luhn algorithm is used to validate a credit card number or other
 * identifying numbers, such as Social Security. Luhn algorithm, also called the
 * Luhn formula or modulus 10, checks the sum of the digits in the card number
 * and checks whether the sum matches the expected result or if there is an
 * error in the number sequence. After working through the algorithm, if the
 * total modulus 10 equals zero, then the number is valid according to the Luhn
 * method.
 * 
 * While the algorithm can be used to verify other identification numbers, it is
 * usually associated with credit card verification. The algorithm works for all
 * major credit cards.
 * 
 * Here is how it works for a credit card with the number 4000008449433403:
 * 
 * 
 * 
 * If the received number is divisible by 10 with the remainder equal to zero,
 * then this number is valid; otherwise, the card number is not valid. When
 * registering in your banking system, you should generate cards with numbers
 * that are checked by the Luhn algorithm. You know how to check the card for
 * validity. But how do you generate a card number so that it passes the
 * validation test? It's very simple!
 * 
 * First, we need to generate an Account Identifier, which is unique to each
 * card. Then we need to assign the Account Identifier to our BIN (Bank
 * Identification Number). As a result, we get a 15-digit number
 * 400000844943340, so we only have to generate the last digit, which is a
 * checksum.
 * 
 * To find the checksum, it is necessary to find the control number for
 * 400000844943340 by the Luhn algorithm. It equals 57 (from the example above).
 * The final check digit of the generated map is 57+X, where X is checksum. In
 * order for the final card number to pass the validity check, the check number
 * must be a multiple of 10, so 57+X must be a multiple of 10. The only number
 * that satisfies this condition is 3.
 * 
 * Therefore, the checksum is 3. So the total number of the generated card is
 * 4000008449433403. The received card is checked by the Luhn algorithm.
 * 
 * You need to change the credit card generation algorithm so that they pass the
 * Luhn algorithm.
 * 
 * Objectives
 * You should allow customers to create a new account in our banking system.
 * 
 * Once the program starts you should print the menu:
 * 
 * 1. Create an account
 * 2. Log into the account
 * 0. Exit
 * 
 * If the customer chooses ‘Create an account’, you should generate a new card
 * number that satisfies all the conditions described above. Then you should
 * generate a PIN code that belongs to the generated card number. A PIN is a
 * sequence of 4 digits; it should be generated in the range from 0000 to 9999.
 * 
 * If the customer chooses ‘Log into account’, you should ask to enter card
 * information.
 * 
 * After the information has been entered correctly, you should allow the user
 * to check the account balance; after creating the account, the balance should
 * be 0. It should also be possible to log out of the account and exit the
 * program.
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

public class LuhnAlgorithm {

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
		c.createCardNumberLuhnAlgorithm();
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

	public void createCardNumberLuhnAlgorithm() {
		String[] str = cardNumber.substring(0, cardNumber.length() - 1).split("");
		int sum = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length; i++) {
			int temp = Integer.parseInt(str[i]);
			if ((i + 1) % 2 != 0) {
				temp *= 2;
			}
			if (temp > 9) {
				temp -= 9;
			}
			sum += temp;
			sb.append(str[i]);
		}
		int checkNumber = sum % 10 == 0 ? 0 : 10 - sum % 10;
		sb.append(String.valueOf(checkNumber));
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
