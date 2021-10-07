package CinemaRoomManagement.Stage04;

import java.util.Scanner;

/**
 * 
 * Description
 * 
 * The theatre is getting popular, and the customers started complaining that
 * it's not practical that the program stops after buying one ticket. Let's add
 * a menu that will allow them to buy tickets and display the current state of
 * the seating arrangement when needed. Objectives
 * 
 * At the start, your program should read two positive integer numbers that
 * represent the number of rows and seats in each row. Then, it should print a
 * menu with the following three items:
 * 
 * Show the seats should print the current seating arrangement. The empty seats
 * should be marked with an S symbol, and taken seats are marked with a B
 * symbol. Buy a ticket should read the seat coordinates from the input and
 * print the ticket price like in the previous stage. After that, the chosen
 * seat should be marked with a B when the item Show the seats is called. Exit
 * should stop the program.
 * 
 * Example
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Enter the number of rows: > 7 
 * Enter the number of seats in each row: > 7
 * 
 * 1. Show the seats 
 * 2. Buy a ticket 
 * 0. Exit 
 * > 1
 * 
 * Cinema: 
 *   1 2 3 4 5 6 7 
 * 1 S S S S S S S 
 * 2 S S S S S S S 
 * 3 S S S S S S S 
 * 4 S S S S S S S 
 * 5 S S S S S S S 
 * 6 S S S S S S S 
 * 7 S S S S S S S
 * 
 * 1. Show the seats 
 * 2. Buy a ticket 
 * 0. Exit 
 * > 2
 * 
 * Enter a row number: 
 * > 4 
 * Enter a seat number in that row: 
 * > 5 
 * Ticket price: $10
 * 
 * 1. Show the seats 
 * 2. Buy a ticket 
 * 0. Exit 
 * > 1
 * 
 * Cinema: 
 *   1 2 3 4 5 6 7 
 * 1 S S S S S S S 
 * 2 S S S S S S S 
 * 3 S S S S S S S
 * 4 S S S S B S S 
 * 5 S S S S S S S 
 * 6 S S S S S S S 
 * 7 S S S S S S S
 * 
 * 1. Show the seats 
 * 2. Buy a ticket 
 * 0. Exit 
 * > 2
 * 
 * Enter a row number: 
 * > 6 
 * Enter a seat number in that row: 
 * > 6 
 * Ticket price: $10
 * 
 * 1. Show the seats 
 * 2. Buy a ticket 
 * 0. Exit 
 * > 1
 * 
 * Cinema:
 *  1 2 3 4 5 6 7 
 *1 S S S S S S S 
 *2 S S S S S S S 
 *3 S S S S S S S 
 *4 S S S S B S S 
 *5 S S S S S S S 
 *6 S S S S S B S 
 *7 S S S S S S S
 * 
 * 1. Show the seats 
 * 2. Buy a ticket 
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
		System.out.println();
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
		System.out.println("Ticket price: $" + price + "\n");
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
		case 0:
			break;
		}
	}
}
