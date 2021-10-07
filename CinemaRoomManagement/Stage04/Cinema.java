package CinemaRoomManagement.Stage04;

import java.util.Scanner;

/**
 * 
 * Description
 * 
 * When choosing a ticket you are guided not only by your space preference but
 * also by your finances. Let's implement the opportunity to check the ticket
 * price and see the reserved seat.
 * 
 * Objectives
 * 
 * Read two positive integer numbers that represent the number of rows and seats
 * in each row and print the seating arrangement like in the first stage. Then,
 * read two integer numbers from the input: a row number and a seat number in
 * that row. These numbers represent the coordinates of the seat according to
 * which the program should print the ticket price. The ticket price is
 * determined by the same rules as the previous stage:
 * 
 * - If the total number of seats in the screen room is not more than 60, then
 * the price of each ticket is 10 dollars. - In a larger room, the tickets are
 * 10 dollars for the front half of the rows and 8 dollars for the back half.
 * Please note that the number of rows can be odd, for example, 9 rows. In this
 * case, the first half is the first 4 rows, and the second half is the last 5
 * rows.
 * 
 * After that, the program should print out all the seats in the screen room as
 * shown in the example and mark the chosen seat by the B symbol. Finally, it
 * should print the ticket price and stop. Note that in this project, the number
 * of rows and seats won't be greater than 9.
 * 
 * Examples
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1
 * 
 * Enter the number of rows: > 7 Enter the number of seats in each row: > 8
 * 
 * Cinema: 1 2 3 4 5 6 7 8 1 S S S S S S S S 2 S S S S S S S S 3 S S S S S S S S
 * 4 S S S S S S S S 5 S S S S S S S S 6 S S S S S S S S 7 S S S S S S S S
 * 
 * Enter a row number: > 3 Enter a seat number in that row: > 6
 * 
 * Ticket price: $10
 * 
 * Cinema: 1 2 3 4 5 6 7 8 1 S S S S S S S S 2 S S S S S S S S 3 S S S S S B S S
 * 4 S S S S S S S S 5 S S S S S S S S 6 S S S S S S S S 7 S S S S S S S S
 * 
 * Example 2
 * 
 * Enter the number of rows: > 8 Enter the number of seats in each row: > 9
 * 
 * Cinema: 1 2 3 4 5 6 7 8 9 1 S S S S S S S S S 2 S S S S S S S S S 3 S S S S S
 * S S S S 4 S S S S S S S S S 5 S S S S S S S S S 6 S S S S S S S S S 7 S S S S
 * S S S S S 8 S S S S S S S S S
 * 
 * Enter a row number: > 6 Enter a seat number in that row: > 5
 * 
 * Ticket price: $8
 * 
 * Cinema: 1 2 3 4 5 6 7 8 9 1 S S S S S S S S S 2 S S S S S S S S S 3 S S S S S
 * S S S S 4 S S S S S S S S S 5 S S S S S S S S S 6 S S S S B S S S S 7 S S S S
 * S S S S S 8 S S S S S S S S S
 * 
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
	public static final String[] menu = { "Exit", "Show the seats", "Buy a ticket" };

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
	}

	private void printName() {
		System.out.println("Cinema:");
	}

	private void dataInput() {
		System.out.println("Enter the number of rows:");
		rowNumbers = sc.nextInt();
		System.out.println("Enter the number of seats in each row:");
		columnNumbers = sc.nextInt();
		System.out.println();
	}

	private void placeInput() {
		System.out.println("Enter a row number:");
		rowPlace = sc.nextInt();
		System.out.println("Enter a seat number in that row:");
		columnPlace = sc.nextInt();
		System.out.println();
	}

	private void calculatePrice() {
		int price = 0;
		if (rowNumbers * columnNumbers < 60 || rowPlace <= rowNumbers / 2) {
			price = 10;
		} else {
			price = 8;
		}
		System.out.println("Ticket price: $" + price);
	}

	private void makeBusy() {
		field[rowPlace][columnPlace * 2] = "B";
	}

	private void printMenu() {
		for (int i = 0; i < menu.length; i++) {
			int index = (i + 1) % 3;
			System.out.println(index + ". " + menu[index]);
		}
	}

	private void cessionMenu() {
		int choice = 0;
		do {
			printMenu();
			String userInput = sc.nextLine();
			if (!isNumerical(userInput) && !inRange(choice)) {
				continue;
			}
			userDataProcessing(choice);
		} while (choice != 0);
		printMenu();
	}

	private boolean isNumerical(String userInput) {
		try {
			int choice = Integer.valueOf(userInput);
		} catch (NumberFormatException nfe) {
			System.out.println("Invalid data input, shoild be numerical, without fraction part");
			return false;
		}
		return true;
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
		case 0:
			break;
		}
	}
}
