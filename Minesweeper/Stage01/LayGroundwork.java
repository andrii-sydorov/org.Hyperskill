package Minesweeper.Stage01;

import java.util.Random;

/**
 * Stage 1/5: Lay the groundwork 
 * 
 * Description Minesweeper is a game of logic
 * where the player is presented with a field full of hidden mines. The goal is
 * to mark the positions of all mines without setting any of them off. It's not
 * a game of wild guessing: it offers hints showing the number of mines around
 * each cell. One wrong move, and game over!
 * 
 * Objective 
 * 
 * Your first step is easy: you need to output some state of the
 * minefield.
 * 
 * Set the minefield size and place any number of mines you want on it. At this
 * point, all the mines are there in plain sight – we are not going to hide them
 * from the player just yet.
 * 
 * You can use any character you want to represent mines and safe cells at this
 * step. Later on, we will use X for mines and . for safe cells.
 * 
 * Example 
 * 
 * In this example, there are 10 mines on a 9x9 field. Feel free to use
 * your own marking symbols and field configuration!
 * 
 * .X....... 
 * .....X..X 
 * ....X.... 
 * ......X.. 
 * ..X...... 
 * ....X.... 
 * ..X......
 * ..X...... 
 * ......X..
 * 
 * @author SMD_ASY
 *
 */

public class LayGroundwork {

	final static int size = 9;
	private static char[][] field = new char[size][size];
	final static int numberOfMine = 10;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		makeField();
		makeMine();
		printField();
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
		for (int i = 0, j = 0; i < numberOfMine; i++, j++) {
			int n = r.nextInt(size);
			field[j % size][n] = 'X';
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
