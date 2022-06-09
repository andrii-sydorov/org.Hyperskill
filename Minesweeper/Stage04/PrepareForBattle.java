package Minesweeper.Stage04;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Stage 4/5: Prepare for battle 
 * 
 * Description 
 * 
 * We managed to create the minefield
 * and fill it with clues: now it's time to play! Let's give our player the
 * opportunity to guess where the mines are with the help of our hints.
 * 
 * All the numbers are still shown to the player, but now the mines are not. To
 * win, the player must find all the mines on the field by marking them.
 * 
 * Update the field input and add the coordinate grid like in the examples so
 * that the player can mark cells by entering their coordinates.
 * 
 * Objectives 
 * 
 * Your upgraded program should meet the following requirements:
 * 
 * 1. After initializing the field, all the numbers are shown to the player, but
 * not the positions of the mines.
 * 
 * 2. The player sees the message “Set/delete mine marks (x and y coordinates):”
 * and enters two numbers as coordinates on the field.
 * 
 * 3. The user input is treated according to the rules:
 * 
 *    1. If the player enters the coordinates of a non-marked cell, the program marks
 * the cell, which means that the player thinks a mine is located there.
 * 
 *    2. If the player enters the coordinates of a cell with a number, the program
 * should print the message “There is a number here!” and ask the player again
 * without printing the minefield, since cells with numbers are guaranteed to be
 * free of mines.
 * 
 *    3. If the player enters the coordinates of a marked cell, the cell becomes
 * unmarked. This is necessary because the game ends only if all the marks are
 * correct, but the player can mark more cells than there are mines.
 * 
 * 4. After successfully marking or unmarking a cell, the new minefield state is
 * printed. The symbol . is used to represent non-marked cells, and * is for
 * marked ones. The prompt for the player's next move is printed until the game
 * is finished.
 * 
 * 5. When the player marks all the mines correctly without marking any empty
 * cells, they win and the game ends. If the player has marked extra cells that
 * do not contain mines, they continue playing. After clearing all the excess
 * mine marks, the player wins. Print the message “Congratulations! You found
 * all the mines!” after the final field state.
 * 
 * Examples 
 * The greater-than symbol followed by a space (> ) represents the user
 * input. Note that it's not part of the input.
 * 
 * Example 1: the user enters the coordinates of a cell that contains a number
 * 
 * How many mines do you want on the field? 
 * > 5
 * 
 *  |123456789| 
 * -|---------| 
 * 1|.111.....| 
 * 2|.1.1.....| 
 * 3|.1221....| 
 * 4|..1.1....|
 * 5|.1221....|
 * 6|.1.21....|
 * 7|.12.1....|
 * 8|..1221...| 
 * 9|...1.1...| 
 * -|---------|
 * Set/delete mines marks (x and y coordinates): > 2 1 
 * There is a number here!
 * Set/delete mines marks (x and y coordinates): > 3 2
 * 
 *  |123456789| 
 * -|---------|
 * 1|.111.....|
 * 2|.1*1.....|
 * 3|.1221....| 
 * 4|..1.1....|
 * 5|.1221....| 
 * 6|.1.21....| 
 * 7|.12.1....| 
 * 8|..1221...| 
 * 9|...1.1...| 
 * -|---------|
 * Set/delete mines marks (x and y coordinates): > 4 4
 * 
 *  |123456789| 
 * -|---------| 
 * 1|.111.....| 
 * 2|.1*1.....| 
 * 3|.1221....| 
 * 4|..1*1....|
 * 5|.1221....| 
 * 6|.1.21....| 
 * 7|.12.1....| 
 * 8|..1221...| 
 * 9|...1.1...| 
 * -|---------|
 * Set/delete mines marks (x and y coordinates): > 3 6
 * 
 *  |123456789| 
 * -|---------| 
 * 1|.111.....| 
 * 2|.1*1.....| 
 * 3|.1221....| 
 * 4|..1*1....|
 * 5|.1221....| 
 * 6|.1*21....| 
 * 7|.12.1....| 
 * 8|..1221...| 
 * 9|...1.1...| 
 * -|---------|
 * Set/delete mines marks (x and y coordinates): > 4 7
 * 
 *  |123456789| 
 * -|---------| 
 * 1|.111.....| 
 * 2|.1*1.....| 
 * 3|.1221....| 
 * 4|..1*1....|
 * 5|.1221....| 
 * 6|.1*21....| 
 * 7|.12*1....| 
 * 8|..1221...| 
 * 9|...1.1...| 
 * -|---------|
 * Set/delete mines marks (x and y coordinates): > 5 9
 * 
 *  |123456789| 
 * -|---------| 
 * 1|.111.....| 
 * 2|.1*1.....| 
 * 3|.1221....| 
 * 4|..1*1....|
 * 5|.1221....| 
 * 6|.1*21....| 
 * 7|.12*1....| 
 * 8|..1221...| 
 * 9|...1*1...| 
 * -|---------|
 * Congratulations! You found all the mines! 
 * 
 * Example 2: the user wins after removing excessive mine marks
 * 
 * How many mines do you want on the field? > 1
 * 
 *  |123456789| 
 * -|---------| 
 * 1|.........| 
 * 2|.........| 
 * 3|.........| 
 * 4|....111..|
 * 5|....1.1..| 
 * 6|....111..| 
 * 7|.........| 
 * 8|.........| 
 * 9|.........| 
 * -|---------|
 * Set/delete mines marks (x and y coordinates): > 1 1
 * 
 *  |123456789|
 * -|---------| 
 * 1|*........| 
 * 2|.........| 
 * 3|.........| 
 * 4|....111..|
 * 5|....1.1..| 
 * 6|....111..| 
 * 7|.........| 
 * 8|.........| 
 * 9|.........| 
 * -|---------|
 * Set/delete mines marks (x and y coordinates): > 6 5
 * 
 *  |123456789| 
 * -|---------| 
 * 1|*........| 
 * 2|.........| 
 * 3|.........| 
 * 4|....111..|
 * 5|....1*1..| 
 * 6|....111..| 
 * 7|.........| 
 * 8|.........| 
 * 9|.........| 
 * -|---------|
 * Set/delete mines marks (x and y coordinates): > 1 1
 * 
 *  |123456789| 
 * -|---------| 
 * 1|.........| 
 * 2|.........| 
 * 3|.........| 
 * 4|....111..|
 * 5|....1*1..| 
 * 6|....111..| 
 * 7|.........| 
 * 8|.........| 
 * 9|.........| 
 * -|---------|
 * Congratulations! You found all mines!
 * 
 * @author SMD_ASY
 *
 */

public class PrepareForBattle {

	final static int size = 12;
	private static char[][] field = new char[size][size];
	static int numberOfMines;
	static int numberOfRefusedMines;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		askingUser(sc);
		makeField();
		makeMine();
		System.out.println();
		calculateMinesAround();
		printField();
		int numberOfMinesSaved = numberOfMines;
		while (numberOfMines != 0 && numberOfRefusedMines != numberOfMinesSaved) {
			printMessage();
			int[] coordinates = getCoordinate(sc);
			if (isOccupied(coordinates)) {
				System.out.println("There is a number here!");
				continue;
			}
			workWithCoordinates(coordinates);
			printField();
		}
		System.out.println();
		printCongratulations();
		sc.close();
	}

	private static void workWithCoordinates(int[] coordinates) {
		switch (field[coordinates[0]][coordinates[1]]) {
		case 'X':
			numberOfMines--;
			numberOfRefusedMines++;
			field[coordinates[0]][coordinates[1]] = '*';
			break;
		case '*':
			field[coordinates[0]][coordinates[1]] = '.';
			numberOfRefusedMines--;
			break;
		case '.':
			field[coordinates[0]][coordinates[1]] = '*';
			numberOfRefusedMines++;
			break;
		}
	}

	private static void printCongratulations() {
		System.out.println("Congratulations! You found all the mines!");
	}

	private static boolean isOccupied(int[] coordinates) {
		if (Character.isDigit(field[coordinates[0]][coordinates[1]])) {
			return true;
		}
		return false;
	}

	private static int[] getCoordinate(Scanner sc) {
		int[] coor = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::valueOf).toArray();
		int j = coor[0] + 1;
		int i = coor[1] + 1;
		return new int[] { i, j };
	}

	private static void printMessage() {
		System.out.print("Set/delete mines marks (x and y coordinates):");
	}

	private static void calculateMinesAround() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[i][j] == '.') {
					field[i][j] = calcMines(i, j);
				}

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
		return calc == '0' ? field[i][j] : calc;
	}

	private static void askingUser(Scanner sc) {
		System.out.println("How many mines do you want on the field?");
		numberOfMines = Integer.valueOf(sc.nextLine());
	}

	private static void printField() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				System.out.print(field[i][j] == 'X' ? '.' : field[i][j]);
			}
			System.out.println();
		}
	}

	private static void makeMine() {
		Random r = new Random();
		for (int i = 0, j = 2; i < numberOfMines; j++) {
			if (j > size - 1) {
				j = 2;
			}
			int n = r.nextInt(9) + 2;
			if (field[j % size][n] == '.') {
				field[j % size][n] = 'X';
				i++;
			}
		}
	}

	private static void buildField() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				// build space
				if (x == 0 && y == 0) {
					field[x][y] = ' ';
				}
				// build first line
				if (x == 0 && (y > 1 && y < size - 1)) {
					field[x][y] = (char) (y - 1 + '0');
				}
				// build last line
				if ((x == size - 1 || x == 1) && (y != 1 && y != size - 1)) {
					field[x][y] = '-';
				}
				// build boundary
				if (y == 1 || y == size - 1) {
					field[x][y] = '|';
				}
				if (y == 0 && (x > 1 && x < size - 1)) {
					field[x][y] = (char) (x - 1 + '0');
				}
			}
		}
	}

	private static void makeField() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				field[i][j] = '.';
			}
		}
		buildField();
	}

}
