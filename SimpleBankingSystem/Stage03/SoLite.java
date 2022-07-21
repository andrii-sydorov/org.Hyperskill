package SimpleBankingSystem.Stage03;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.sqlite.SQLiteDataSource;

/**
 * I'm so lite
 * Description
 * It's very upsetting when the data about registered users disappears after the
 * program is completed. To avoid this problem, you need to create a database
 * where you will store all the necessary information about the created credit
 * cards. We will use SQLite to create the database.
 * 
 * SQLite is a database engine. It is software that allows users to interact
 * with a relational database. In SQLite, a database is stored in a single file
 * — a trait that distinguishes it from other database engines. This allows for
 * greater accessibility: copying a database is no more complicated than copying
 * the file that stores the data, and sharing a database implies just sending an
 * email attachment.
 * 
 * You may consult the SQL tutorial website to navigate the work with SQLite
 * from Java.
 * 
 * You don't have to install any libraries to work with a database — you can use
 * it in the project without any problems.
 * 
 * Objectives
 * In this stage, create a database with a table titled card. It should have the
 * following columns:
 * 
 * id INTEGER
 * number TEXT
 * pin TEXT
 * balance INTEGER DEFAULT 0
 * Also, in this stage, you should read the database file name from the command
 * line argument. Filename should be passed to the program using -fileName
 * argument, for example, -fileName db.s3db.
 * 
 * Pay attention: your database file should be created when the program starts,
 * if it hasn't yet been created. And all created cards should be stored in the
 * database from now.
 * 
 * Example
 * The symbol > represents the user input. Notice that it's not a part of the
 * input.
 * 
 * 1. Create account
 * 2. Log into account
 * 0. Exit
 * >1
 * 
 * Your card has been created
 * Your card number:
 * 4000003429795087
 * Your card PIN:
 * 6826
 * 
 * 1. Create account
 * 2. Log into account
 * 0. Exit
 * >2
 * 
 * Enter your card number:
 * >4000003429795087
 * Enter your PIN:
 * >4444
 * 
 * Wrong card number or PIN!
 * 
 * 1. Create account
 * 2. Log into account
 * 0. Exit
 * >2
 * 
 * Enter your card number:
 * >4000003429795087
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
 * 1. Create account
 * 2. Log into account
 * 0. Exit
 * >0
 * 
 * Bye!
 */

public class SoLite {

	private static int userChoice;
	private static String baseName;
	private static Connection con;

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < args.length; i += 2) {
			map.put(args[i], args[i + 1]);
		}
		baseName = map.get("-fileName");
		Scanner sc = new Scanner(System.in);
		UserAccount ua = new UserAccount(sc);
		getConnection(baseName);
		deleteAndCreateTable(con);
		boolean isRunning = true;
		while (isRunning) {
			printMenu();
			getUserChoice(sc);
			switch (userChoice) {
				case 1:
					ua.createAccount();
					ua.saveToDataBase(baseName, con);
					break;
				case 2:
					ua.createUser();
					ua.checkingInfo();
					if (ua.acces) {
						authorizedWork(sc, ua);
						isRunning = false;
					}
					break;
				case 0:
					isRunning = false;
					closeConnection();
					break;
			}
			System.out.println();
		}
		System.out.println("Bye!");
		sc.close();
	}

	private static void getConnection(String baseName) {
		String url = "jdbc:sqlite:./src/SimpleBankingSystem/Stage03/" + baseName;
		SQLiteDataSource dataSource = new SQLiteDataSource();
		dataSource.setUrl(url);
		con = null;
		try {
			con = dataSource.getConnection();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	private static void deleteAndCreateTable(Connection con) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate("DROP TABLE IF EXISTS card;");
			st.executeUpdate(
					"CREATE TABLE IF NOT EXISTS card" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "number TEXT,"
							+ "pin VARCHAR(20)," + "balance INTEGER DEFAULT 0);");
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	private static void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException sql) {
				sql.printStackTrace();
			}
		}
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
					closeConnection();
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

	public void saveToDataBase(String baseName, Connection con) {
		try (Statement st = con.createStatement()) {
			// int id = 0;
			String text = c.getCardNumber();
			String pin = c.getPin();
			int balance = getBalance();
			String update = String.format("INSERT INTO card(number, pin, balance)" + "VALUES(%s, %s, %d)", text,
					pin, balance);
			st.executeUpdate(update);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
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
