package BudgetManager.Stage04;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 * Memorable purchases 
 * 
 * What's the point of counting the money if the results are lost and forgotten
 * once you close the program? To allow for some long-term budget planning, we
 * need to save purchases to file. Add items Save and Load to the menu.
 * 
 * Save should save all purchases to the file.
 * Load should load all purchases from the file.
 * 
 * Use the purchases.txt file to store purchases.
 * 
 * Examples
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Notice that it's not part of the input.
 * 
 * Example 1:
 * 
 * Choose your action:
 * 1) Add income
 * 2) Add purchase
 * 3) Show list of purchases
 * 4) Balance
 * 5) Save
 * 6) Load
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
 * 5) Save
 * 6) Load
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
 * > Almond 250g
 * Enter its price:
 * > 35.43
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
 * 5) Save
 * 6) Load
 * 0) Exit
 * > 5
 * 
 * Purchases were saved!
 * 
 * Choose your action:
 * 1) Add income
 * 2) Add purchase
 * 3) Show list of purchases
 * 4) Balance
 * 5) Save
 * 6) Load
 * 0) Exit
 * > 0
 * 
 * Bye!
 * Example 2:
 * 
 * Choose your action:
 * 1) Add income
 * 2) Add purchase
 * 3) Show list of purchases
 * 4) Balance
 * 5) Save
 * 6) Load
 * 0) Exit
 * > 6
 * 
 * Purchases were loaded!
 * 
 * Choose your action:
 * 1) Add income
 * 2) Add purchase
 * 3) Show list of purchases
 * 4) Balance
 * 5) Save
 * 6) Load
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
 * > 1
 * 
 * Food:
 * Almond 250g $35.43
 * Total sum: $35.43
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
 * Almond 250g $35.43
 * Total sum: $35.43
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
 * 5) Save
 * 6) Load
 * 0) Exit
 * > 4
 * 
 * Balance: $964.57
 * 
 * Choose your action:
 * 1) Add income
 * 2) Add purchase
 * 3) Show list of purchases
 * 4) Balance
 * 5) Save
 * 6) Load
 * 0) Exit
 * > 0
 * 
 * Bye!
 */

public class MemoryPurchases {

	private static int userChoice;
	private static double balance;
	private static Purchases food = Purchases.FOOD;
	private static Purchases cloth = Purchases.CLOTHES;
	private static Purchases ent = Purchases.ENTERTAINMENT;
	private static Purchases oth = Purchases.OTHER;
	private static String pathToFile = "./purchases.txt";
	private static File f;
	private static String purchases;
	private static NumberFormat nfe;

	static {
		f = new File(pathToFile);
		try {
			f.createNewFile();
		} catch (IOException ioe) {
			System.out.println("File couldn't be created!");
		}
		purchases = null;
		nfe = NumberFormat.getInstance(Locale.US);
		nfe.setMinimumFractionDigits(2);
		nfe.setMinimumFractionDigits(2);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in).useLocale(Locale.US);
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
				case 5:
					m = Menu.SAVE;
					saveListOfPurchases();
					break;
				case 6:
					m = Menu.LOAD;
					loadListOfPurchases();
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

	private static void loadListOfPurchases() throws FileNotFoundException {
		Scanner sc = new Scanner(f);
		Purchases p = null;
		while (sc.hasNext()) {
			String s = sc.nextLine();
			switch (s) {
				case "Food:":
					p = food;
					continue;
				case "Clothes:":
					p = cloth;
					continue;
				case "Entertainment:":
					p = ent;
					continue;
				case "Other:":
					p = oth;
					continue;
				case "Balance:":
					balance = Double.valueOf(sc.nextLine());
				default:
					break;
			}
			if (s.length() != 0 && s.contains("$")) {
				p.map.put(s.substring(0, s.lastIndexOf("$") - 1), Double.valueOf(s.substring(s.lastIndexOf("$") + 1)));
			}
		}
		sc.close();
		System.out.println();
		System.out.println("Purchases were loaded!");
	}

	private static void saveListOfPurchases() {
		try (PrintWriter p = new PrintWriter(f)) {
			dataToSave();
			p.println(purchases);
		} catch (IOException ioe) {
			System.out.println("The data couldn't be stored!");
		}
		System.out.println("Purchases were saved!");
	}

	private static void dataToSave() {
		StringBuilder sb = new StringBuilder();
		Purchases[] pur = new Purchases[] { food, cloth, ent, oth };
		String[] name = { "Food:", "Clothes:", "Entertainment:", "Other:" };
		int index = 0;
		for (Purchases p : pur) {
			if (p.map.size() != 0) {
				sb.append(name[index] + "\n").append(dataFromMap(p));
			}
			index++;
		}
		sb.append("Balance:\n").append(balance);
		purchases = sb.toString();
	}

	private static String dataFromMap(Purchases p) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Double> entr : p.map.entrySet()) {
			sb.append(entr.getKey() + " $" + entr.getValue()).append("\n");
		}
		return sb.toString();
	}

	private static void addPurchase(Scanner sc) {
		System.out.println();
		Purchases p = null;
		boolean isPurchaseOn = true;
		while (isPurchaseOn) {
			printPurchases();
			getUserChoice(sc);
			switch (userChoice) {
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
		System.out.println();
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
			getUserChoice(sc);
			switch (userChoice) {
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
			food.map.forEach((k, v) -> System.out.println(k + " $" + nfe.format(v)));
		}
		if (cloth.map.size() != 0) {
			cloth.map.forEach((k, v) -> System.out.println(k + " $" + nfe.format(v)));
		}
		if (ent.map.size() != 0) {
			ent.map.forEach((k, v) -> System.out.println(k + " $" + nfe.format(v)));
		}
		if (oth.map.size() != 0) {
			oth.map.forEach((k, v) -> System.out.println(k + " $" + nfe.format(v)));
		}
		System.out.println("Total sum: $"
				+ nfe.format(food.getBalance() + cloth.getBalance() + ent.getBalance() + oth.getBalance()));
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
			p.map.forEach((k, v) -> System.out.println(k + " $" + nfe.format(v)));
			System.out.println("Total sum: $" + nfe.format(p.getBalance()));
		}
		System.out.println();
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
		for (Purchases p : Purchases.values()) {
			if (p.map.size() != 0) {
				for (Map.Entry<String, Double> entr : p.map.entrySet()) {
					balance -= entr.getValue();
				}
			}
		}
		if (balance < 0) {
			balance = 0;
		}
		System.out.println("Balance: $" + nfe.format(balance));
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
	BALANCE(4, "Balance"), SAVE(5, "Save"), LOAD(6, "Load"), EXIT(0, "Exit");

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
	public Map<String, Double> map = new LinkedHashMap<>();
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
