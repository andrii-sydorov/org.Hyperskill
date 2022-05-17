package Minesweeper.Stage02;

import java.util.Random;
import java.util.Scanner;

/**
 * Stage 2/5: Flexible mines 
 * 
 * Description 
 * It's no fun when the field has the same
 * setup every time and you know where all the mines are located. Let's generate
 * a random configuration every time the player wants to play the game.
 * 
 * To improve the program, we need to let the player choose how many mines they
 * want on the field. The player needs to input the number of mines they want
 * with their keyboard.
 * 
 * Objectives 
 * 
 * Your program should ask the player to define the number of mines
 * to add to a 9x9 field with the message "How many mines do you want on the
 * field?". It should then use the input to initialize the field and display it
 * with the mines. At this point, the mines are still visible to the player; you
 * will hide them later.
 * 
 * Make sure to use the following marking symbols:
 * 
 * X for mines 
 * . for safe cells 
 * Examples The greater-than symbol followed by a space (> ) represents the user input. 
 * Note that it's not part of the input.
 * 
 * Example 1:
 * 
 * How many mines do you want on the field? 
 * > 
 * 10 
 * ........X 
 * ........X 
 * ......X.X
 * X........ 
 * ......... 
 * ......X.. 
 * XX......X 
 * ......... 
 * .....X... 
 * 
 * Example 2:
 * 
 * How many mines do you want on the field? 
 * > 10 
 * ......... 
 * .X....... 
 * ...X...XX
 * X........ 
 * .X....... 
 * ......... 
 * ......... 
 * X......X. 
 * ...X....X 
 * 
 * Example 3:
 * 
 * How many mines do you want on the field? 
 * > 20 
 * .X..XX... 
 * .....XX.X 
 * ....XX...
 * ....XX.XX 
 * .X......X 
 * .....X... 
 * ..X..XX.. 
 * ......... 
 * .X.....X.
 * 
 * @author SMD_ASY
 *
 */

public class FlexibleMines {

	final static int size = 9;
	private static char[][] field = new char[size][size];
	static int numberOfMine;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		askingUser(sc);
		makeField();
		makeMine();
		printField();
		sc.close();
	}

	private static void askingUser(Scanner sc) {
		System.out.println("How many mines do you want on the field?");
		numberOfMine = Integer.valueOf(sc.nextLine());
	}

	private static void printField() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				System.out.print(field[i][j]);
			}
			System.out.println();
		}
	}

	private static void makeMine() {
		Random r = new Random();
		for (int i = 0, j = 0; i < numberOfMine; j++) {
			int n = r.nextInt(size);
			if (field[j % size][n] == '.') {
				field[j % size][n] = 'X';
				i++;
			}
		}
	}

	private static void makeField() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				field[i][j] = '.';
			}
		}
	}

}
