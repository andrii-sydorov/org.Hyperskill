package Minesweeper.Stage03;

import java.util.Random;
import java.util.Scanner;

/**
 * Stage 3/5: Look around you 
 * 
 * Description 
 * 
 * The player needs hints to be able to win, and we want them to have a chance to win! 
 * Let's show the number of mines around the empty cells so that our players 
 * have something to work with.
 * 
 * Objectives 
 * 
 * As in the previous step, you need to initialize the field with
 * mines. Then, calculate how many mines there are around each empty cell. Check
 * 8 cells if the current cell is in the middle of the field, 5 cells if it's on
 * the side, and 3 cells if it's in the corner.
 * 
 * If there are mines around the cell, display the number of mines (from 1 to 8)
 * instead of the symbol representing an empty cell. The symbols for empty cells
 * and mines stay the same.
 * 
 * Check all the possibilities carefully.
 * 
 * Examples The greater-than symbol followed by a space (> ) represents the user
 * input. Note that it's not part of the input.
 * 
 * Example 1:
 * 
 * How many mines do you want on the field? 
 * > 10 
 * ......... 
 * .111111.. 
 * .1X22X211
 * .112X33X1 
 * ...12X211 
 * ....1221. 
 * ..1111X1. 
 * 123X1222. 
 * 1XX211X1. 
 * 
 * Example 2:
 * 
 * How many mines do you want on the field? 
 * > 15 
 * 1221..... 
 * 2XX21.... 
 * X34X2..11
 * 112X2..2X 
 * 11211..3X 
 * 1X1....2X 
 * 12321..11 
 * 12XX11232 
 * X22211XXX 
 * 
 * Example 3:
 * 
 * How many mines do you want on the field? > 20 
 * .2X3X23XX 
 * 13X43X3X3 
 * 1X3X32211
 * 2232X1... 
 * 2X2221... 
 * X32X1..11 
 * X32331.1X 
 * X21XX2.22 
 * 1113X2.1X
 * 
 * @author SMD_ASY
 *
 */

public class LookAroundYou {

	final static int size = 9;
	private static char[][] field = new char[size][size];
	static int numberOfMine;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		askingUser(sc);
		makeField();
		makeMine();
		// printField();
		calculateMinesAround();
		// System.out.println();
		printField();
		sc.close();
	}

	private static void calculateMinesAround() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[i][j] == 'X') {
					continue;
				}
				field[i][j] = calcMines(i, j);
			}
		}
	}

	private static char calcMines(int i, int j) {
		char calc = '0';
		int startI = i - 1;
		int startJ = j - 1;
		int stopI = i + 1;
		int stopJ = j + 1;
		for (int x = startI; x <= stopI; x++) {
			if (x < 0 || x > field.length - 1) {
				continue;
			}
			for (int y = startJ; y <= stopJ; y++) {
				if (y < 0 || y > field.length - 1) {
					continue;
				}
				if (x == i && y == j) {
					continue;
				}
				if (field[x][y] == 'X') {
					calc++;
				}
			}
		}
		return calc == '0' ? '.' : calc;
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
