package CinemaRoomManagement.Stage02;

import java.util.Scanner;

/**
 * Description
 * 
 * Good job: our friends really liked your program! Now they want to expand
 * their theater and add screen rooms with more seats. However, this is
 * expensive, so they want to make sure this will pay off. Help them calculate
 * the profit from all the sold tickets depending on the number of available
 * seats. 
 * 
 * Objectives
 * 
 * In this stage, you need to read two positive integer numbers from the input:
 * the number of rows and the number of seats in each row. The ticket price is
 * determined by the following rules:
 * 
 * - If the total number of seats in the screen room is not more than 60, then the
 * price of each ticket is 10 dollars. 
 * - In a larger room, the tickets are 10 dollars for the front half of the rows 
 * and 8 dollars for the back half.
 * Please note that the number of rows can be odd, for example, 9 rows. In this
 * case, the first half is the first 4 rows, and the second half is the other 5
 * rows.
 * 
 * Calculate the profit from the sold tickets depending on the number of seats
 * and print the result as shown in the examples below. After that, your program
 * should stop. Note that in this project, the number of rows and seats won't be
 * greater than 9. 
 * 
 * Examples
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1
 * 
 * Enter the number of rows: > 4 
 * Enter the number of seats in each row: > 5
 * Total income: $200
 * 
 * Example 2
 * 
 * Enter the number of rows: > 8 
 * Enter the number of seats in each row: > 9
 * Total income: $648
 * 
 * Example 3
 * 
 * Enter the number of rows: > 
 * 9 Enter the number of seats in each row: > 7
 * Total income: $560
 * 
 * @author ASY
 *
 */

public class Cinema {

	private Scanner sc = new Scanner(System.in);

	private int rowNumbers;
	private int columnNumbers;
	private final String[][] field = new String[rowNumbers + 1][columnNumbers * 2 + 1];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cinema c = new Cinema();
		c.dataInput();
		c.calculateProfit();
	}

	private void makeField() {
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
	}

	private void calculateProfit() {
		if (rowNumbers * columnNumbers < 60) {
			System.out.println("Total income:\n" + "$" + rowNumbers * columnNumbers * 10);
		} else {
			System.out.println("Total income:");
			int count = columnNumbers * (rowNumbers / 2 * 10 + (rowNumbers - rowNumbers / 2) * 8);
			System.out.println("$" + count);
		}
	}
}
