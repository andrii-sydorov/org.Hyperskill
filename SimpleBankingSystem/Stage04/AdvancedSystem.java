package SimpleBankingSystem.Stage04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.sqlite.SQLiteDataSource;

/**
 * Stage 4/4: Advanced system
 * Description
 * You have created the foundation of our banking system. Now let's take the
 * opportunity to deposit money into an account, make transfers and close an
 * account if necessary.
 * 
 * Now your menu should look like this:
 * 
 * 1. Balance
 * 2. Add income
 * 3. Do transfer
 * 4. Close account
 * 5. Log out
 * 0. Exit
 * 
 * If the user asks for Balance, you should read the balance of the account from
 * the database and output it into the console.
 * 
 * Add income item should allow us to deposit money to the account.
 * 
 * Do transfer item should allow transferring money to another account. You
 * should handle the following errors:
 * 
 * If the user tries to transfer more money than he/she has, output: Not enough
 * money!
 * If the user tries to transfer money to the same account, output the following
 * message: You can't transfer money to the same account!
 * If the receiver's card number doesn’t pass the Luhn algorithm, you should
 * output: Probably you made a mistake in the card number. Please try again!
 * If the receiver's card number doesn’t exist, you should output: Such a card
 * does not exist.
 * If there is no error, ask the user how much money they want to transfer and
 * make the transaction.
 * If the user chooses the Close an account item, you should delete that account
 * from the database.
 * 
 * Examples
 * The symbol > represents the user input. Notice that it's not a part of the
 * input.
 * 
 * Example 1:
 * 
 * 1. Create an account
 * 2. Log into account
 * 0. Exit
 * >1
 * 
 * Your card has been created
 * Your card number:
 * 4000009455296122
 * Your card PIN:
 * 1961
 * 
 * 1. Create an account
 * 2. Log into account
 * 0. Exit
 * >1
 * 
 * Your card has been created
 * Your card number:
 * 4000003305160034
 * Your card PIN:
 * 5639
 * 
 * 1. Create an account
 * 2. Log into account
 * 0. Exit
 * >2
 * 
 * Enter your card number:
 * >4000009455296122
 * Enter your PIN:
 * >1961
 * 
 * You have successfully logged in!
 * 
 * 1. Balance
 * 2. Add income
 * 3. Do transfer
 * 4. Close account
 * 5. Log out
 * 0. Exit
 * >2
 * 
 * Enter income:
 * >10000
 * Income was added!
 * 
 * 1. Balance
 * 2. Add income
 * 3. Do transfer
 * 4. Close account
 * 5. Log out
 * 0. Exit
 * >1
 * 
 * Balance: 10000
 * 
 * 1. Balance
 * 2. Add income
 * 3. Do transfer
 * 4. Close account
 * 5. Log out
 * 0. Exit
 * >3
 * 
 * Transfer
 * Enter card number:
 * >4000003305160035
 * Probably you made a mistake in the card number. Please try again!
 * 
 * 1. Balance
 * 2. Add income
 * 3. Do transfer
 * 4. Close account
 * 5. Log out
 * 0. Exit
 * >3
 * 
 * Transfer
 * Enter card number:
 * >4000003305061034
 * Such a card does not exist.
 * 
 * 1. Balance
 * 2. Add income
 * 3. Do transfer
 * 4. Close account
 * 5. Log out
 * 0. Exit
 * >3
 * 
 * Transfer
 * Enter card number:
 * >4000003305160034
 * Enter how much money you want to transfer:
 * >15000
 * Not enough money!
 * 
 * 1. Balance
 * 2. Add income
 * 3. Do transfer
 * 4. Close account
 * 5. Log out
 * 0. Exit
 * >3
 * 
 * Transfer
 * Enter card number:
 * >4000003305160034
 * Enter how much money you want to transfer:
 * >5000
 * Success!
 * 
 * 1. Balance
 * 2. Add income
 * 3. Do transfer
 * 4. Close account
 * 5. Log out
 * 0. Exit
 * >1
 * 
 * Balance: 5000
 * 
 * 1. Balance
 * 2. Add income
 * 3. Do transfer
 * 4. Close account
 * 5. Log out
 * 0. Exit
 * 
 * >0
 * Bye!
 * Example 2:
 * 
 * 1. Create an account
 * 2. Log into account
 * 0. Exit
 * >1
 * 
 * Your card has been created
 * Your card number:
 * 4000007916053702
 * Your card PIN:
 * 6263
 * 
 * 1. Create an account
 * 2. Log into account
 * 0. Exit
 * >2
 * 
 * Enter your card number:
 * >4000007916053702
 * Enter your PIN:
 * >6263
 * 
 * You have successfully logged in!
 * 
 * 1. Balance
 * 2. Add income
 * 3. Do transfer
 * 4. Close account
 * 5. Log out
 * 0. Exit
 * >4
 * 
 * The account has been closed!
 * 
 * 1. Create an account
 * 2. Log into account
 * 0. Exit
 * >2
 * 
 * Enter your card number:
 * >4000007916053702
 * Enter your PIN:
 * >6263
 * 
 * Wrong card number or PIN!
 * 
 * 1. Create an account
 * 2. Log into account
 * 0. Exit
 * >0
 * 
 * Bye!
 */

public class AdvancedSystem {

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
		getConnection(baseName);
		deleteAndCreateTable(con);
		boolean isRunning = true;
		while (isRunning) {
			printMenu();
			getUserChoice(sc);
			switch (userChoice) {
				case 1:
					Card c = new Card();
					c.printInfo();
					saveToDataBase(c);
					break;
				case 2:
					User u = new User();
					u.setUserData(sc);
					if (isLogged(u)) {
						authorizedWork(sc, u);
						if (userChoice == 4) {
							continue;
						}
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

	public static void saveToDataBase(Card c) {
		try (Statement st = con.createStatement()) {
			String text = c.getCardNumber();
			String pin = c.getPin();
			int balance = c.getBalance();
			String update = String.format("INSERT INTO card(number, pin, balance)" + "VALUES(%s, %s%s%s, %d)", text,
					"'", pin, "'", balance);
			st.executeUpdate(update);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	private static void getConnection(String baseName) {
		String url = "jdbc:sqlite:./src/SimpleBankingSystem/Stage04/" + baseName;
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
			st.executeUpdate("CREATE TABLE IF NOT EXISTS card" + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "number TEXT," + "pin VARCHAR(20)," + "balance INTEGER DEFAULT 0);");
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

	private static void authorizedWork(Scanner sc, User u) {
		System.out.println();
		boolean logOn = true;
		while (logOn) {
			printLogOnData();
			getUserChoice(sc);
			switch (userChoice) {
				case 1:
					printBalance(u);
					break;
				case 2:
					addIncome(u, sc);
					break;
				case 3:
					doTransfer(u, sc);
					break;
				case 4:
					closeAccount(u);
					logOn = false;
					break;
				case 5:
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

	private static void closeAccount(User u) {
		String queryToDelete = "DELETE FROM card WHERE number = ? AND pin = ?;";
		try (PreparedStatement ps = con.prepareStatement(queryToDelete)) {

			con.setAutoCommit(true);
			ps.setString(1, u.c.getCardNumber());
			ps.setString(2, u.c.getPin());

			ps.executeUpdate();

		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		System.out.println("The account has been closed!");
	}

	private static void addIncome(User u, Scanner sc) {
		System.out.println();
		System.out.println("Enter income:");
		int toAdd = Integer.valueOf(sc.nextLine());
		String add = "UPDATE card " + "SET balance = balance + ?" + "WHERE number = ?;";
		String getBalance = "SELECT * FROM card WHERE number = ?;";
		try (PreparedStatement ps = con.prepareStatement(add);
				PreparedStatement setBalance = con.prepareStatement(getBalance)) {

			con.setAutoCommit(true);
			ps.setInt(1, toAdd);
			ps.setString(2, u.c.getCardNumber());
			ps.executeUpdate();

			setBalance.setString(1, u.c.getCardNumber());
			ResultSet rs = setBalance.executeQuery();
			u.c.setBalance(rs.getInt(4));

		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		System.out.println("Income was added!");
		System.out.println();
	}

	private static void doTransfer(User u, Scanner sc) {
		System.out.println();
		System.out.println("Transfer");
		System.out.println("Enter card number:");
		String cardNumber = sc.nextLine();
		String from = "UPDATE card " + "SET balance = balance - ? " + "WHERE number = ?;";
		String to = "UPDATE card " + "SET balance = balance + ? " + "WHERE number = ?;";
		String checkIfCardExist = "SELECT * FROM card " + "WHERE number = ?;";
		String exceptionsMessage = null;
		try (PreparedStatement fromCard = con.prepareStatement(from);
				PreparedStatement toCard = con.prepareStatement(to);
				PreparedStatement checkCard = con.prepareStatement(checkIfCardExist);) {
			con.setAutoCommit(false);
			if (!checkAccordingLuhnAlgorithm(cardNumber)) {
				exceptionsMessage = "Probably you made a mistake in the card number. Please try again!";
				throw new SQLException(exceptionsMessage);
			}
			if (cardNumber.equals(u.c.getCardNumber())) {
				exceptionsMessage = "You can't transfer money to the same account!";
				throw new SQLException(exceptionsMessage);
			}
			checkCard.setString(1, cardNumber);
			ResultSet rs = checkCard.executeQuery();
			if (!rs.next()) {
				exceptionsMessage = "Such a card does not exist.";
				throw new SQLException(exceptionsMessage);
			}
			System.out.println("Enter how much money you want to transfer:");
			int toTransfer = Integer.parseInt(sc.nextLine());
			if (u.c.getBalance() < toTransfer) {
				exceptionsMessage = "Not enough money!";
				throw new SQLException(exceptionsMessage);
			}

			fromCard.setInt(1, toTransfer);
			fromCard.setString(2, u.c.getCardNumber());
			fromCard.executeUpdate();

			toCard.setInt(1, toTransfer);
			toCard.setString(2, cardNumber);
			toCard.executeUpdate();

			con.commit();
			System.out.println("Success!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
	}

	private static boolean checkAccordingLuhnAlgorithm(String cardNumber) {
		String[] subCardNumber = cardNumber.substring(0, cardNumber.length() - 1).split("");
		int lastDigit = Character.getNumericValue(cardNumber.charAt(cardNumber.length() - 1));
		int count = 0;
		for (int i = 0; i < subCardNumber.length; i++) {
			int temp = Integer.parseInt(subCardNumber[i]);
			temp = (i + 1) % 2 != 0 ? temp * 2 : temp;
			if (temp > 9) {
				temp -= 9;
			}
			count += temp;
		}
		return (count + lastDigit) % 10 == 0 ? true : false;
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

	private static void printBalance(User u) {
		String query = "SELECT * FROM card WHERE number = ? AND pin = ?;";
		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setString(1, u.c.getCardNumber());
			ps.setString(2, u.c.getPin());
			ResultSet rs = ps.executeQuery();
			u.c.setBalance(rs.getInt(4));

		} catch (SQLException sql) {
			sql.printStackTrace();
			return;
		}
		System.out.println();
		System.out.println("Balance: " + u.c.getBalance());
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

	private static boolean isLogged(User u) {
		String query = "SELECT * FROM card " + "WHERE number = ? AND pin = ?;";
		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setString(1, u.getCardNumber());
			ps.setString(2, u.getPin());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("You have successfully logged in!");
				String cardNumber = rs.getString(2);
				String pin = rs.getString(3);
				int balance = rs.getInt(4);
				u.setCard(cardNumber, pin, balance);
				return true;
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		System.out.println("Wrong card number or PIN!");
		return false;
	}

}

class User {

	private String pin;
	private String cardNumber;
	public Card c;

	public void setUserData(Scanner sc) {
		System.out.println();
		System.out.println("Enter your card number:");
		this.cardNumber = sc.nextLine();
		System.out.println("Enter your PIN:");
		this.pin = sc.nextLine();
		System.out.println();
	}

	public void setCard(String cardNumber, String pin, int balance) {
		c = Card.getCard(cardNumber, pin, balance);
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
	private int balance;
	Random r = new Random();

	public void printInfo() {
		System.out.println();
		System.out.println("Your card has been created");
		System.out.println("Your card number:");
		System.out.println(getCardNumber());
		System.out.println("Your card PIN:");
		System.out.println(getPin());
	}

	Card() {
		createPin();
		createCardNumber();
		createCardNumberLuhnAlgorithm();
	}

	private Card(String cardNumber, String pin, int balance) {
		this.cardNumber = cardNumber;
		this.pin = pin;
		this.balance = balance;
	}

	public static Card getCard(String cardNumber, String pin, int balance) {
		return new Card(cardNumber, pin, balance);
	}

	public Card getCard() {
		return new Card();
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

	public int getBalance() {
		return this.balance;
	}

	public void setBalance(int newBalance) {
		this.balance = newBalance;
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
	BALANCE(1, "Balance"), ADD(2, "Add income"), TRANSFER(3, "Do transfer"), CLOSE(4, "Close account"),
	LOGOUT(5, "Log out"), EXIT(0, "Exit");

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
