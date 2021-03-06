package BudgetManager.Stage03;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *  Oh the things you can buy 
 * 
 * Description
 * 
 * To better control the expenses, we need to categorize our purchases. It helps
 * to see how exactly your budget is distributed: you may be actually quite
 * surprised!
 * 
 * Implement a function that assigns a purchase to a specific category.
 * 
 * The program should have the following categories:
 * Food
 * Clothes
 * Entertainment
 * Other
 * 
 * The function allows you to output the shopping list by type. After selecting
 * the action of showing the list of expenses, offer to show either a certain
 * category or a general list. At the end print the total amount of purchases
 * that are on the list.
 * Example
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Notice that it's not part of the input.
 * 
 * Choose your action:
 * 1) Add income
 * 2) Add purchase
 * 3) Show list of purchases
 * 4) Balance
 * 0) Exit
 * > 3
 * 
 * The purchase list is empty!
 * 
 * Choose your action:
 * 1) Add income
 * 2) Add purchase
 * 3) Show list of purchases
 * 4) Balance
 * 0) Exit
 * > 2
 * 
 * Choose the type of purchase
 * 1) Food
 * 2) Clothes
 * 3) Entertainment
 * 4) Other
 * 5) Back
 * > 1
 * 
 * Enter purchase name:
 * > Milk
 * Enter its price:
 * > 3.5
 * Purchase was added!
 * 
 * Choose the type of purchase
 * 1) Food
 * 2) Clothes
 * 3) Entertainment
 * 4) Other
 * 5) Back
 * > 5
 * 
 * Choose your action:
 * 1) Add income
 * 2) Add purchase
 * 3) Show list of purchases
 * 4) Balance
 * 0) Exit
 * > 3
 * 
 * Choose the type of purchases
 * 1) Food
 * 2) Clothes
 * 3) Entertainment
 * 4) Other
 * 5) All
 * 6) Back
 * > 4
 * 
 * Other:
 * The purchase list is empty!
 * 
 * Choose the type of purchases
 * 1) Food
 * 2) Clothes
 * 3) Entertainment
 * 4) Other
 * 5) All
 * 6) Back
 * > 1
 * 
 * Food:
 * Milk $3.50
 * Total sum: $3.50
 * 
 * Choose the type of purchases
 * 1) Food
 * 2) Clothes
 * 3) Entertainment
 * 4) Other
 * 5) All
 * 6) Back
 * > 5
 * 
 * All:
 * Milk $3.50
 * Total sum: $3.50
 * 
 * Choose the type of purchases
 * 1) Food
 * 2) Clothes
 * 3) Entertainment
 * 4) Other
 * 5) All
 * 6) Back
 * > 6
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
public class ThingsToBuy {

	private static int userChoice;
	private static double balance;
	private static int kindOfPurchase;
	private static int purchaseToShow;
	private static Purchases food = Purchases.FOOD;
	private static Purchases cloth = Purchases.CLOTHES;
	private static Purchases ent = Purchases.ENTERTAINMENT;
	private static Purchases oth = Purchases.OTHER;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean isRunning = true;
		Menu m = null;
		while (isRunning) {
			print();
			getUserChoice(sc);
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
					showListOfPurchase(sc);
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
		System.out.println();
		Purchases p = null;
		boolean isPurchaseOn = true;
		while (isPurchaseOn) {
			printPurchases();
			getPurchase(sc);
			switch (kindOfPurchase) {
				case 1:
					p = food;
					break;
				case 2:
					p = cloth;
					break;
				case 3:
					p = ent;
					break;
				case 4:
					p = oth;
					break;
				case 5:
					p = Purchases.BACK;
					isPurchaseOn = false;
					break;
			}
			addKindOfPurchase(p, sc);
		}
	}

	private static void addKindOfPurchase(Purchases p, Scanner sc) {
		if (p == null || p == Purchases.BACK) {
			return;
		}
		System.out.println();
		System.out.println("Enter purchase name:");
		String key = sc.nextLine();
		System.out.println("Enter its price:");
		double value = Double.valueOf(sc.nextLine());
		p.map.put(key, value);
		System.out.println("Purchase was added!");
		balance -= value;
		System.out.println();
	}

	private static void getPurchase(Scanner sc) {
		kindOfPurchase = Integer.valueOf(sc.nextLine());
	}

	private static void printPurchases() {
		System.out.println("Choose the type of purchase");
		for (Purchases p : Purchases.values()) {
			System.out.println(p.toString());
		}
	}

	private static void showListOfPurchase(Scanner sc) {
		System.out.println();
		if (isEmpty()) {
			System.out.println("The purchase list is empty!");
			return;
		}
		boolean isPurchaseListToShow = true;
		while (isPurchaseListToShow) {
			printListOfPurchases();
			getUserChoiceListToShow(sc);
			switch (purchaseToShow) {
				case 1:
					showPurchase(food);
					break;
				case 2:
					showPurchase(cloth);
					break;
				case 3:
					showPurchase(ent);
					break;
				case 4:
					showPurchase(oth);
					break;
				case 5:
					showPurchasesAll();
					break;
				case 6:
					isPurchaseListToShow = false;
					break;
			}

		}
	}

	private static void showPurchasesAll() {
		System.out.println();
		System.out.println("All: ");
		if (food.map.size() != 0) {
			food.map.forEach((k, v) -> System.out.println(k + " $" + v));
		}
		if (cloth.map.size() != 0) {
			cloth.map.forEach((k, v) -> System.out.println(k + " $" + v));
		}
		if (ent.map.size() != 0) {
			ent.map.forEach((k, v) -> System.out.println(k + " $" + v));
		}
		if (oth.map.size() != 0) {
			oth.map.forEach((k, v) -> System.out.println(k + " $" + v));
		}
		System.out.println(
				"Total sum: $" + (food.getBalance() + cloth.getBalance() + ent.getBalance() + oth.getBalance()));
		System.out.println();
	}

	private static boolean isEmpty() {
		return food.map.size() + cloth.map.size() + ent.map.size() + oth.map.size() == 0;
	}

	private static void showPurchase(Purchases p) {
		System.out.println();
		System.out.println(p.getName() + ":");
		if (p.map.size() == 0) {
			System.out.println("The purchase list is empty");
		} else {
			p.map.forEach((k, v) -> System.out.println(k + " $" + v));
			System.out.println("Total sum: $" + p.getBalance());
		}
		System.out.println();
	}

	private static void getUserChoiceListToShow(Scanner sc) {
		purchaseToShow = Integer.valueOf(sc.nextLine());
	}

	private static void printListOfPurchases() {
		System.out.println("Choose the type of purchase");
		for (ListPurchases lp : ListPurchases.values()) {
			System.out.println(lp.toString());
		}
	}

	private static void addIncome(Scanner sc) {
		System.out.println();
		System.out.println("Enter income: ");
		balance += Integer.valueOf(sc.nextLine());
		System.out.println("Income was added!");
	}

	private static void showBalance() {
		System.out.println();
		if (balance < 0) {
			balance = 0;
		}
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

enum Purchases {

	FOOD(1, "Food"), CLOTHES(2, "Clothes"), ENTERTAINMENT(3, "Entertainment"), OTHER(4, "Other"), BACK(5, "Back");

	private int index;
	private String name;
	public Map<String, Double> map = new HashMap<>();
	private double balance;

	Purchases(int index, String name) {
		this.name = name;
		this.index = index;
	}

	public int getIndex() {
		return this.index;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return this.getIndex() + ") " + this.getName();
	}

	public double getBalance() {
		balance = 0;
		for (Map.Entry<String, Double> entr : map.entrySet()) {
			balance += entr.getValue();
		}
		return this.balance;
	}
}

enum ListPurchases {
	FOOD(1, "Food"), CLOTHES(2, "Clothes"), ENTERTAINMENT(3, "Entertainment"), OTHER(4, "Other"), ALL(5, "All"),
	BACK(6, "Back");

	private int index;
	private String name;
	public Map<String, Double> map = new HashMap<>();

	ListPurchases(int index, String name) {
		this.name = name;
		this.index = index;
	}

	public int getIndex() {
		return this.index;
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return this.getIndex() + ") " + this.getName();
	}
}
