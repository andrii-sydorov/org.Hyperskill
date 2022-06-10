package BudgetManager.Stage03;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
		System.out.println("Enter purchase name:");
		String key = sc.nextLine();
		System.out.println("Enter its price:");
		double value = Double.valueOf(sc.nextLine());
		p.map.put(key, value);
		System.out.println("Purchase was added!");
		balance -= value;
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
	}

	private static boolean isEmpty() {
		return food.map.size() + cloth.map.size() + ent.map.size() + oth.map.size() == 0;
	}

	private static void showPurchase(Purchases p) {
		System.out.println(p.getName() + ":");
		if (p.map.size() == 0) {
			System.out.println("The purchase list is empty");
		} else {
			p.map.forEach((k, v) -> System.out.println(k + " $" + v));
			System.out.println("Total sum: $" + p.getBalance());
		}
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
