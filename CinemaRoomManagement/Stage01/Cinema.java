package CinemaRoomManagement.Stage01;

import java.util.Arrays;

/**
 * Description
 * 
 * There are many enjoyable activities on this funny little planet Earth, and
 * still, the happiest, simplest, and most fulfilling one is probably going to
 * the movies. Going with friends, going with loved ones, experiencing a whole
 * new adventure from the safety of a cinema seat, surrounded by like-minded
 * fellow travelers.
 * 
 * As a beginner developer, you can contribute to creating this wonderful cinema
 * experience. Your good friends asked you to help them create an application
 * for a cinema theatre where people can get tickets, reserve seats, and enjoy
 * their movie night. 
 * 
 * Objectives
 * 
 * There is not a lot of space in our new cinema theatre, so you need to take
 * into account the restrictions on the number of seats. Your friends said that
 * the room would fit 7 rows of 8 seats. Your task is to help them visualize the
 * seating arrangement by printing the scheme to the console.
 * 
 * Your output should be like in the example below and should contain 9 lines.
 * 
 * Example
 * 
 * Cinema: 
 *   1 2 3 4 5 6 7 8 
 * 1 S S S S S S S S 
 * 2 S S S S S S S S 
 * 3 S S S S S S S S
 * 4 S S S S S S S S 
 * 5 S S S S S S S S 
 * 6 S S S S S S S S 
 * 7 S S S S S S S S
 * 
 * @author ASY
 *
 */

public class Cinema {

	private final int rowNumbers = 15;
	private final int columnNumbers = 23;
	private final String[][] field = new String[rowNumbers + 1][columnNumbers * 2 + 1];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cinema c = new Cinema();
		c.makeField();
		c.printName();
		c.printField();
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
}
