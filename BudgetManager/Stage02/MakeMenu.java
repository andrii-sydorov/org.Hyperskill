package BudgetManager.Stage02;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Stage 2/5: Make a menu
 * Description
 * Let's make your application more convenient. Only counting the expenses is a
 * little bit sad, right?
 * 
 * To make your application flexible and functional, add a menu that consists of
 * 4 items.
 * 
 * 1. Add Income. We must track both our expenses and our income. When this item is
 * selected, the program should ask to enter the amount of income.
 * 2. Add Purchase. This item should add a purchase to the list. You have
 * implemented this feature in the previous stage.
 * 3. Show the list of purchases. This menu item should display a list of all
 * expenses and incomes in the order they were made.
 * 4. Balance. Show the balance.
 * 5. Exit. Exit the program. Make this item under number 0, not number 5.
 * 
 * Notice, that the amount of remaining money cannot be negative. In this case,
 * make the balance equal to $0.
 * 
 * When displaying the price or the total amount, print 2 numbers after the
 * point.
 * Example: $14.20
 * Follow this rule in the next stages.
 * 
 * Example
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Notice that it's not part of the input.
 * 
 * Choose your action:
 * 1) Add income
 * 2) Add purchase
 * 3) Show list of purchases
 * 4) Balance
 * 0) Exit
 * > 1
 * 
 * Enter income:
 * > 1000
 * Income was added!
 * 
 * Choose your action:
 * 1) Add income
 * 2) Add purchase
 * 3) Show list of purchases
 * 4) Balance
 * 0) Exit
 * > 4
 * 
 * Balance: $1000.00
 * 
 * Choose your action:
 * 1) Add income
 * 2) Add purchase
 * 3) Show list of purchases
 * 4) Balance
 * 0) Exit
 * > 3
 * 
 * The purchase list is empty
 * 
 * Choose your action:
 * 1) Add income
 * 2) Add purchase
 * 3) Show list of purchases
 * 4) Balance
 * 0) Exit
 * > 2
 * 
 * Enter purchase name:
 * >Red Fuji Apple
 * Enter its price:
 * > 5.99
 * Purchase was added!
 * 
 * Choose your action:
 * 1) Add income
 * 2) Add purchase
 * 3) Show list of purchases
 * 4) Balance
 * 0) Exit
 * > 3
 * 
 * Red Fuji Apple $5.99
 * Total sum: $5.99
 * 
 * Choose your action:
 * 1) Add income
 * 2) Add purchase
 * 3) Show list of purchases
 * 4) Balance
 * 0) Exit
 * > 0
 * 
 * Bye!
 */

public class MakeMenu {

	private static int userChoice;
	private static double balance;
	private static double purchase;
	private static Map<String, Double> m = new HashMap<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean isRunning = true;
		Menu m = null;
		while (isRunning) {
			print();
			getUserChoice(sc);
			System.out.println();
			switch (userChoice) {
				case 1:
					m = Menu.INCOME;
					addIncome(sc);
					break;
				case 2:
					m = Menu.PURCHASE;
					addPurchase(sc);
					break;
				case 3:
					m = Menu.LISTOFPURCHASE;
					showListOfPurchase();
					break;
				case 4:
					m = Menu.BALANCE;
					showBalance();
					break;
				case 0:
					m = Menu.EXIT;
					isRunning = false;
					break;
			}
			System.out.println();
		}
		System.out.println("Bye!");
		sc.close();
	}

	private static void addPurchase(Scanner sc) {
		System.out.println("Enter purchase name:");
		String key = sc.nextLine();
		System.out.println("Enter its price:");
		double value = Double.valueOf(sc.nextLine());
		m.put(key, value);
		System.out.println("Purchase was added!");
		purchase += value;
		balance -= value;
	}

	private static void showListOfPurchase() {
		if (m.size() == 0) {
			System.out.println("The purchase list is empty");
		} else {
			m.forEach((k, v) -> System.out.println(k + " $" + v));
			System.out.println("Total sum: $" + purchase);
		}
	}

	private static void addIncome(Scanner sc) {
		System.out.println("Enter income: ");
		balance += Integer.valueOf(sc.nextLine());
		System.out.println("Income was added!");
	}

	private static void showBalance() {
		System.out.println("Balance: $" + balance);
	}

	private static void getUserChoice(Scanner sc) {
		userChoice = Integer.valueOf(sc.nextLine());
	}

	private static void print() {
		System.out.println("Choose your action:");
		for (Menu m : Menu.values()) {
			System.out.println(m.toString());
		}
	}
}

enum Menu {
	INCOME(1, "Add income"), PURCHASE(2, "Add purchase"), LISTOFPURCHASE(3, "Show list of purchases"),
	BALANCE(4, "Balance"), EXIT(0, "Exit");

	private int menuPunkt;
	private String description;

	Menu(int n, String description) {
		this.menuPunkt = n;
		this.description = description;
	}

	public int getMenuPunkt() {
		return this.menuPunkt;
	}

	public String getDescription() {
		return this.description;
	}

	public String toString() {
		return this.getMenuPunkt() + ") " + this.getDescription();
	}

}
