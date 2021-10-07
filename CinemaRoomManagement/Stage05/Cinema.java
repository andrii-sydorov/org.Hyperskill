package CinemaRoomManagement.Stage05;

import java.util.Scanner;

/**
 * Description
 * 
 * Running a cinema theatre is no easy business. To help our friends, let's add
 * statistics to your program. The stats will show the current income, total
 * income, the number of available seats, and the percentage of occupancy.
 * 
 * In addition, our friends asked you to take care of a small inconvenience:
 * it's not good when a user can buy a ticket that has already been purchased by
 * another user. Let's fix this! 
 * 
 * Objectives
 * 
 * Now your menu should look like this:
 * 
 * 1. Show the seats 
 * 2. Buy a ticket 
 * 3. Statistics 
 * 0. Exit
 * 
 * When the item Statistics is chosen, your program should print the following
 * information:
 * 
 * - The number of purchased tickets; 
 * - The number of purchased tickets represented as a percentage. Percentages 
 * should be rounded to 2 decimal places; 
 * - Current income; 
 * - The total income that shows how much money the theatre will get if
 * all the tickets are sold.
 * 
 * The rest of the menu items should work the same way as before, except the
 * item Buy a ticket shouldn't allow a user to buy a ticket that has already
 * been purchased.
 * 
 * If a user chooses an already taken seat, print "That ticket has already been
 * purchased!" and ask them to enter different seat coordinates until they pick
 * an available seat. Of course, you shouldn't allow coordinates that are out of
 * bounds. If this happens, print "Wrong input!" and ask to enter different seat
 * coordinates until the user picks an available seat. 
 * 
 * Examples
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Enter the number of rows: > 6 
 * Enter the number of seats in each row: > 6
 * 
 * 1. Show the seats 
 * 2. Buy a ticket 
 * 3. Statistics 
 * 0. Exit 
 * > 3
 * 
 * Number of purchased tickets: 0 
 * Percentage: 0.00% 
 * Current income: $0 
 * Total income: $360
 * 
 * 1. Show the seats 
 * 2. Buy a ticket 
 * 3. Statistics 
 * 0. Exit 
 * > 2
 * 
 * Enter a row number: 
 * > 1 
 * Enter a seat number in that row: 
 * > 1
 * 
 * Ticket price: $10
 * 
 * 1. Show the seats 
 * 2. Buy a ticket 
 * 3. Statistics 
 * 0. Exit 
 * > 3
 * 
 * Number of purchased tickets: 1 
 * Percentage: 2.78% 
 * Current income: $10 
 * Total income: $360
 * 
 * 1. Show the seats 
 * 2. Buy a ticket 
 * 3. Statistics 
 * 0. Exit
 *  > 2
 * 
 * Enter a row number: 
 * > 1 
 * Enter a seat number in that row: 
 * > 1
 * 
 * That ticket has already been purchased!
 * 
 * Enter a row number: 
 * > 10 
 * Enter a seat number in that row: 
 * > 20
 * 
 * Wrong input!
 * 
 * Enter a row number: 
 * > 4 
 * Enter a seat number in that row: 
 * > 4
 * 
 * Ticket price: $10
 * 
 * 1. Show the seats 
 * 2. Buy a ticket 
 * 3. Statistics 
 * 0. Exit 
 * > 1
 * 
 * Cinema:
 *  1 2 3 4 5 6 
 * 1 B S S S S S 
 * 2 S S S S S S 
 * 3 S S S S S S 
 * 4 S S S B S S 
 * 5 S S S S S S 
 * 6 S S S S S S
 * 
 * 1. Show the seats 
 * 2. Buy a ticket 
 * 3. Statistics 
 * 0. Exit 
 * > 3
 * 
 * Number of purchased tickets: 2 
 * Percentage: 5.56% 
 * Current income: $20 
 * Total income: $360
 * 
 * 1. Show the seats 
 * 2. Buy a ticket 
 * 3. Statistics 
 * 0. Exit 
 * > 0
 * 
 * @author ASY
 *
 */

public class Cinema {

	private Scanner sc = new Scanner(System.in);

	private int rowNumbers;
	private int columnNumbers;
	private String[][] field;
	private int rowPlace;
	private int columnPlace;
	public static final String[] menu = { "Exit", "Show the seats", "Buy a ticket", "Statistics" };
	private int income = 0;
	private int purchasedPlace = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cinema c = new Cinema();
		c.dataInput();
		c.makeField();
		c.cessionMenu();
	}

	private void makeField() {
		field = new String[rowNumbers + 1][columnNumbers * 2 + 1];
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (i == 0 && j == 0) {
					field[i][j] = " ";
					continue;
				} else if (i == 0 && j % 2 == 0) {
					field[i][j] = String.valueOf(j / 2);
				} else if (j == 0) {
					field[i][j] = String.valueOf(i);
				} else if (j % 2 == 0) {
					field[i][j] = "S";
				} else {
					field[i][j] = " ";
				}
			}
		}
	}

	private void printField() {
		for (String[] s : field) {
			for (String s1 : s) {
				System.out.print(s1);
			}
			System.out.println();
		}
		System.out.println();
	}

	private void printName() {
		System.out.println("Cinema:");
	}

	private void dataInput() {
		while (true) {
			System.out.println("Enter the number of rows:");
			rowNumbers = sc.nextInt();
			System.out.println("Enter the number of seats in each row:");
			columnNumbers = sc.nextInt();
			break;
		}
		System.out.println();
	}

	private void placeInput() {
		while (true) {
			System.out.println("Enter a row number:");
			rowPlace = sc.nextInt();
			System.out.println("Enter a seat number in that row:");
			columnPlace = sc.nextInt();
			System.out.println();
			if (notInRange()) {
				System.out.println("Wrong input!\n");
				continue;
			}
			if (isOccupied()) {
				System.out.println("That ticket has already been purchased!\n");
				continue;
			}
			break;
		}
	}

	private void calculatePrice() {
		int price = 0;
		if (rowNumbers * columnNumbers < 60 || rowPlace <= rowNumbers / 2) {
			price = 10;
		} else {
			price = 8;
		}
		income += price;
		purchasedPlace++;
		System.out.println("Ticket price: $" + price + "\n");
	}

	private void makeBusy() {
		field[rowPlace][columnPlace * 2] = "B";
	}

	private void printMenu() {
		for (int i = 0; i < menu.length; i++) {
			int index = (i + 1) % menu.length;
			System.out.println(index + ". " + menu[index]);
		}
	}

	private void cessionMenu() {
		int choice = 0;
		do {
			printMenu();
			choice = sc.nextInt();
			System.out.println();
			if (!inRange(choice)) {
				continue;
			}
			userDataProcessing(choice);
		} while (choice != 0);
	}

	private boolean inRange(int choice) {
		boolean result = choice <= menu.length - 1 && choice >= 0 ? true : false;
		if (!result) {
			System.out.printf("User input should be in range beetwen %d and %d\n", 0, menu.length - 1);
		}
		return result;
	}

	private void userDataProcessing(int choice) {
		switch (choice) {
		case 1:
			printName();
			printField();
			break;
		case 2:
			placeInput();
			calculatePrice();
			makeBusy();
			break;
		case 3:
			numberPurchaseTicket();
			procentPurchasedTicket();
			currentIncome();
			totalIncome();
			break;
		case 0:
			break;
		}
	}

	private void totalIncome() {
		if (rowNumbers * columnNumbers < 60) {
			System.out.println("Total income:" + "$" + rowNumbers * columnNumbers * 10);
		} else {
			int count = columnNumbers * (rowNumbers / 2 * 10 + (rowNumbers - rowNumbers / 2) * 8);
			System.out.println("Total income: $" + count);
		}
		System.out.println();
	}

	private void currentIncome() {
		System.out.println("Current income: $" + income);
	}

	private void numberPurchaseTicket() {
		System.out.println("Number of purchased tickets: " + purchasedPlace);
	}

	private void procentPurchasedTicket() {
		double procent = purchasedPlace * 1.0 / (rowNumbers * columnNumbers) * 100.00;
		System.out.printf("Percentage: %.2f%% \n", procent);
	}

	private boolean isOccupied() {
		if (field[rowPlace][columnPlace * 2].equals("B")) {
			return true;
		}
		return false;
	}

	private boolean notInRange() {
		return rowPlace < 0 || columnPlace < 0 || rowPlace > rowNumbers || columnPlace > columnNumbers ? true : false;
	}
}
