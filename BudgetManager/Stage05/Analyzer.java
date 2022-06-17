package BudgetManager.Stage05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 * Stage 5/5: Analyzer
 * Description
 * Do you know how much money you spend on food? On entertainment? It's quite
 * interesting to know since the main purpose of this application is to analyze
 * your expenses. Let's implement this feature!
 * 
 * First, add the Analyze item to the menu. This way you will request an
 * analysis of your purchases.
 * 
 * Once this item is called you need to offer a way to sort the purchases.
 * 
 * There are three of them:
 * 
 * 1. Sort All – sort the entire shopping list and display it so that the most
 * expensive purchases are at the top of the list.
 * 2. Sort By Type – show which category eats the most money. If a category has no
 * purchases in it the total sum should be $0.
 * 3. Sort Certain Type – same as Sort All, but for a specific category.
 * Example
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Notice that it's not part of the input.
 * 
 * Choose your action:
 * 1) Add income
 * 2) Add purchase
 * 3) Show list of purchases
 * 4) Balance
 * 5) Save
 * 6) Load
 * 7) Analyze (Sort)
 * 0) Exit
 * > 7
 * 
 * How do you want to sort?
 * 1) Sort all purchases
 * 2) Sort by type
 * 3) Sort certain type
 * 4) Back
 * >1
 * 
 * The purchase list is empty!
 * 
 * How do you want to sort?
 * 1) Sort all purchases
 * 2) Sort by type
 * 3) Sort certain type
 * 4) Back
 * > 2
 * 
 * Types:
 * Food - $0
 * Entertainment - $0
 * Clothes - $0
 * Other - $0
 * Total sum: $0
 * 
 * How do you want to sort?
 * 1) Sort all purchases
 * 2) Sort by type
 * 3) Sort certain type
 * 4) Back
 * > 3
 * 
 * Choose the type of purchase
 * 1) Food
 * 2) Clothes
 * 3) Entertainment
 * 4) Other
 * > 2
 * 
 * The purchase list is empty!
 * 
 * How do you want to sort?
 * 1) Sort all purchases
 * 2) Sort by type
 * 3) Sort certain type
 * 4) Back
 * > 4
 * 
 * Choose your action:
 * 1) Add income
 * 2) Add purchase
 * 3) Show list of purchases
 * 4) Balance
 * 5) Save
 * 6) Load
 * 7) Analyze (Sort)
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
 * 7) Analyze (Sort)
 * 0) Exit
 * > 7
 * 
 * How do you want to sort?
 * 1) Sort all purchases
 * 2) Sort by type
 * 3) Sort certain type
 * 4) Back
 * > 1
 * 
 * All:
 * Almond 250g $35.43
 * Skate rental $30.00
 * FIJI Natural Artesian Water $25.98
 * Wrangler Men's Stretch Cargo Pant $19.97
 * Sensodyne Pronamel Toothpaste $19.74
 * Men's Dual Defense Crew Socks 12 Pairs $13.00
 * LEGO DUPLO Town Farm Animals $10.10
 * Chick-fil-A $10 Gift Card $10.00
 * Cinema $8.73
 * Gildan LT $8.61
 * Hershey's milk chocolate bars $8.54
 * Keystone Ground Beef $6.28
 * Red Fuji Apple $5.99
 * Eggs $3.99
 * Milk $3.50
 * Debt $3.50
 * Great Value Broccoli Florets $1.00
 * Total: $214.36
 * 
 * How do you want to sort?
 * 1) Sort all purchases
 * 2) Sort by type
 * 3) Sort certain type
 * 4) Back
 * > 2
 * 
 * Types:
 * Food - $90.71
 * Entertainment - $48.83
 * Clothes - $41.58
 * Other - $33.24
 * Total sum: $214.36
 * 
 * How do you want to sort?
 * 1) Sort all purchases
 * 2) Sort by type
 * 3) Sort certain type
 * 4) Back
 * > 3
 * 
 * Choose the type of purchase
 * 1) Food
 * 2) Clothes
 * 3) Entertainment
 * 4) Other
 * > 1
 * 
 * Food:
 * Almond 250g $35.43
 * FIJI Natural Artesian Water $25.98
 * Hershey's milk chocolate bars $8.54
 * Keystone Ground Bee $6.28
 * Red Fuji Apple $5.99
 * Eggs $3.99
 * Milk $3.50
 * Great Value Broccoli Florets $1.00
 * Total sum: $90.71
 * 
 * How do you want to sort?
 * 1) Sort all purchases
 * 2) Sort by type
 * 3) Sort certain type
 * 4) Back
 * > 4
 * 
 * Choose your action:
 * 1) Add income
 * 2) Add purchase
 * 3) Show list of purchases
 * 4) Balance
 * 5) Save
 * 6) Load
 * 7) Analyze (Sort)
 * 0) Exit
 * > 0
 * 
 * Bye!
 */

public class Analyzer {

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
		while (isRunning) {
			print();
			getUserChoice(sc);
			switch (userChoice) {
				case 1:
					addIncome(sc);
					break;
				case 2:
					addPurchase(sc);
					break;
				case 3:
					showListOfPurchase(sc);
					break;
				case 4:
					showBalance();
					break;
				case 5:
					saveListOfPurchases();
					break;
				case 6:
					loadListOfPurchases();
					break;
				case 7:
					analyzeData(sc);
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

	public static void analyzeData(Scanner sc) {
		boolean isSortingActive = true;
		SortingMenu sm = null;
		MainSortData sortData = null;
		List<Purchases> ps = createListOfPurchases();
		while (isSortingActive) {
			System.out.println();
			printSortMenu();
			getUserChoice(sc);
			switch (userChoice) {
				case 1:
					sortData = new MainSortData(new SortAll());
					sortData.sort(ps);
					break;
				case 2:
					sortData = new MainSortData(new SortByItems());
					sortData.sort(ps);
					break;
				case 3:
					printSortMenuCertainItems(ps);
					getUserChoice(sc);
					sortData = new MainSortData(new SortCertainData());
					List<Purchases> p = new ArrayList<>();
					switch (userChoice) {
						case 1:
							p.add(food);
							sortData.sort(p);
							break;
						case 2:
							p.add(cloth);
							sortData.sort(p);
							break;
						case 3:
							p.add(ent);
							sortData.sort(p);
							break;
						case 4:
							p.add(oth);
							sortData.sort(p);
							break;
					}
					break;
				case 4:
					sm = SortingMenu.BACK;
					isSortingActive = false;
					break;
			}
		}
	}

	private static void printSortMenuCertainItems(List<Purchases> ps) {
		System.out.println();
		System.out.println("Choose the type of purchase");
		for (Purchases p : ps) {
			System.out.println(p);
		}
	}

	private static List<Purchases> createListOfPurchases() {
		List<Purchases> ls = new ArrayList<>();
		ls.add(food);
		ls.add(cloth);
		ls.add(ent);
		ls.add(oth);
		return ls;
	}

	private static void printSortMenu() {
		System.out.println("How do you want to sort?");
		for (SortingMenu sm : SortingMenu.values()) {
			System.out.println(sm.toString());
		}
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
		for (Purchases p : pur) {
			if (p.map.size() != 0) {
				sb.append(p.getDescription() + ":\n").append(dataFromMap(p));
			}
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
		double value = Double.valueOf((sc.nextLine()));
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
		System.out.println(p.getDescription() + ":");
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
	BALANCE(4, "Balance"), SAVE(5, "Save"), LOAD(6, "Load"), ANALYZE(7, "Analyze (Sort)"), EXIT(0, "Exit");

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
	private String description;
	public Map<String, Double> map = new LinkedHashMap<>();
	private double balance;

	Purchases(int index, String name) {
		this.description = name;
		this.index = index;
	}

	public int getIndex() {
		return this.index;
	}

	public String getDescription() {
		return this.description;
	}

	public String toString() {
		return this.getIndex() + ") " + this.getDescription();
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

enum SortingMenu {
	ALL(1, "Sort all purchases"), BYTYPE(2, "Sort by type"), CERTAINTYPE(3, "Sort certain type"), BACK(4, "Back");

	int index;
	String description;

	SortingMenu(int index, String description) {
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
		return getIndex() + ") " + getDescription();
	}
}
