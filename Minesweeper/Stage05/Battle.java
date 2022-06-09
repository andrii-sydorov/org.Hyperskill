package Minesweeper.Stage05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 * Stage 5/5: Battle
 * Description
 * In this stage, you will upgrade your program to act just like the original
 * Minesweeper game! We won't show all the hints from the beginning anymore, but
 * we will allow the player to explore the minefield by themselves, which is
 * much more challenging and fun.
 * 
 * The game starts with an unexplored minefield that has a user-defined number
 * of mines.
 * 
 * The player can:
 * 
 * Mark unexplored cells as cells that potentially have a mine, and also remove
 * those marks. Any empty cell can be marked, not just the cells that contain a
 * mine. The mark is removed by marking the previously marked cell.
 * 
 * Explore a cell if they think it does not contain a mine.
 * 
 * There are three possibilities after exploring a cell:
 * 
 * If the cell is empty and has no mines around, all the cells around it,
 * including the marked ones, can be explored, and it should be done
 * automatically. Also, if next to the explored cell there is another empty one
 * with no mines around, all the cells around it should be explored as well, and
 * so on, until no more can be explored automatically.
 * 
 * If a cell is empty and has mines around it, only that cell is explored,
 * revealing a number of mines around it.
 * 
 * If the explored cell contains a mine, the game ends and the player loses.
 * 
 * There are two possible ways to win:
 * 
 * Marking all the cells that have mines correctly.
 * 
 * Opening all the safe cells so that only those with unexplored mines are left.
 * 
 * Objectives
 * In this final stage, your program should contain the following additional
 * functionality:
 * 
 * Print the current state of the minefield starting with all unexplored cells
 * at the beginning, ask the player for their next move with the message
 * Set/unset mine marks or claim a cell as free:, treat the player's move
 * according to the rules, and print the new minefield state. Ask for the
 * player's next move until the player wins or steps on a mine. The player's
 * input contains a pair of cell coordinates and a command: mine to mark or
 * unmark a cell, free to explore a cell.
 * 
 * If the player explores a mine, print the field in its current state, with
 * mines shown as X symbols. After that, output the message You stepped on a
 * mine and failed!.
 * 
 * Generate mines like in the original game: the first cell explored with the
 * free command cannot be a mine; it should always be empty. You can achieve
 * this in many ways – it's up to you.
 * 
 * Use the following symbols to represent each cell's state:
 * 
 * . as unexplored cells
 * 
 * / as explored free cells without mines around it
 * 
 * Numbers from 1 to 8 as explored free cells with 1 to 8 mines around them,
 * respectively
 * 
 * X as mines
 * 
 * as unexplored marked cells
 * 
 * Examples
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1: the user loses after exploring a cell that contains a mine
 * 
 * How many mines do you want on the field? > 10
 * 
 *  |123456789|
 * -|---------|
 * 1|.........|
 * 2|.........|
 * 3|.........|
 * 4|.........|
 * 5|.........|
 * 6|.........|
 * 7|.........|
 * 8|.........|
 * 9|.........|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 3 2 free
 * 
 *  |123456789|
 * -|---------|
 * 1|.1///1...|
 * 2|.1//12...|
 * 3|11//1....|
 * 4|////1....|
 * 5|11111....|
 * 6|.........|
 * 7|.........|
 * 8|.........|
 * 9|.........|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 1 1 free
 * 
 *  |123456789|
 * -|---------|
 * 1|11///1...|
 * 2|.1//12...|
 * 3|11//1....|
 * 4|////1....|
 * 5|11111....|
 * 6|.........|
 * 7|.........|
 * 8|.........|
 * 9|.........|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 1 2 mine
 * 
 *  |123456789|
 * -|---------|
 * 1|11///1...|
 * 2|*1//12...|
 * 3|11//1....|
 * 4|////1....|
 * 5|11111....|
 * 6|.........|
 * 7|.........|
 * 8|.........|
 * 9|.........|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 8 8 free
 * 
 *  |123456789|
 * -|---------|
 * 1|11///1...|
 * 2|*1//12...|
 * 3|11//1....|
 * 4|////1....|
 * 5|11111....|
 * 6|.........|
 * 7|.........|
 * 8|.......1.|
 * 9|.........|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 7 8 free
 * 
 *  |123456789|
 * -|---------|
 * 1|11///1...|
 * 2|*1//12...|
 * 3|11//1....|
 * 4|////1....|
 * 5|11111....|
 * 6|.........|
 * 7|.........|
 * 8|......11.|
 * 9|.........|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 6 8 free
 * 
 *  |123456789|
 * -|---------|
 * 1|11///1...|
 * 2|*1//12...|
 * 3|11//1....|
 * 4|////1....|
 * 5|11111....|
 * 6|.........|
 * 7|.........|
 * 8|.....211.|
 * 9|.........|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 2 7 free
 * 
 *  |123456789|
 * -|---------|
 * 1|11///1...|
 * 2|*1//12...|
 * 3|11//1....|
 * 4|////1....|
 * 5|11111....|
 * 6|.........|
 * 7|.3.......|
 * 8|.....211.|
 * 9|.........|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 5 6 free
 * 
 *  |123456789|
 * -|---------|
 * 1|11///1X..|
 * 2|X1//12...|
 * 3|11//1X...|
 * 4|////1....|
 * 5|11111....|
 * 6|.X..X....|
 * 7|.3X...X..|
 * 8|.X..X211.|
 * 9|...X.....|
 * -|---------|
 * You stepped on a mine and failed!
 * Example 2: the user wins by marking all mines correctly
 * 
 * How many mines do you want on the field? > 8
 * 
 *  |123456789|
 * -|---------|
 * 1|.........|
 * 2|.........|
 * 3|.........|
 * 4|.........|
 * 5|.........|
 * 6|.........|
 * 7|.........|
 * 8|.........|
 * 9|.........|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 5 5 free
 * 
 *  |123456789|
 * -|---------|
 * 1|..1//1...|
 * 2|111//2...|
 * 3|/////1...|
 * 4|/////11..|
 * 5|//////1..|
 * 6|/111//1..|
 * 7|23.1//111|
 * 8|..21/////|
 * 9|..1//////|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 2 1 mine
 * 
 *  |123456789|
 * -|---------|
 * 1|.*1//1...|
 * 2|111//2...|
 * 3|/////1...|
 * 4|/////11..|
 * 5|//////1..|
 * 6|/111//1..|
 * 7|23.1//111|
 * 8|..21/////|
 * 9|..1//////|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 3 7 mine
 * 
 *  |123456789|
 * -|---------|
 * 1|.*1//1...|
 * 2|111//2...|
 * 3|/////1...|
 * 4|/////11..|
 * 5|//////1..|
 * 6|/111//1..|
 * 7|23*1//111|
 * 8|..21/////|
 * 9|..1//////|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 2 8 mine
 * 
 *  |123456789|
 * -|---------|
 * 1|.*1//1...|
 * 2|111//2...|
 * 3|/////1...|
 * 4|/////11..|
 * 5|//////1..|
 * 6|/111//1..|
 * 7|23*1//111|
 * 8|.*21/////|
 * 9|..1//////|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 1 8 mine
 * 
 *  |123456789|
 * -|---------|
 * 1|.*1//1...|
 * 2|111//2...|
 * 3|/////1...|
 * 4|/////11..|
 * 5|//////1..|
 * 6|/111//1..|
 * 7|23*1//111|
 * 8|**21/////|
 * 9|..1//////|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 7 3 mine
 * 
 *  |123456789|
 * -|---------|
 * 1|.*1//1...|
 * 2|111//2...|
 * 3|/////1*..|
 * 4|/////11..|
 * 5|//////1..|
 * 6|/111//1..|
 * 7|23*1//111|
 * 8|**21/////|
 * 9|..1//////|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 8 3 free
 * 
 *  |123456789|
 * -|---------|
 * 1|.*1//1...|
 * 2|111//2...|
 * 3|/////1*1.|
 * 4|/////11..|
 * 5|//////1..|
 * 6|/111//1..|
 * 7|23*1//111|
 * 8|**21/////|
 * 9|..1//////|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 9 3 free
 * 
 *  |123456789|
 * -|---------|
 * 1|.*1//1...|
 * 2|111//2.31|
 * 3|/////1*1/|
 * 4|/////111/|
 * 5|//////111|
 * 6|/111//1..|
 * 7|23*1//111|
 * 8|**21/////|
 * 9|..1//////|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 8 6 mine
 * 
 *  |123456789|
 * -|---------|
 * 1|.*1//1...|
 * 2|111//2.31|
 * 3|/////1*1/|
 * 4|/////111/|
 * 5|//////111|
 * 6|/111//1*.|
 * 7|23*1//111|
 * 8|**21/////|
 * 9|..1//////|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 7 2 free
 * 
 *  |123456789|
 * -|---------|
 * 1|.*1//1...|
 * 2|111//2231|
 * 3|/////1*1/|
 * 4|/////111/|
 * 5|//////111|
 * 6|/111//1*.|
 * 7|23*1//111|
 * 8|**21/////|
 * 9|..1//////|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 7 1 mine
 * 
 *  |123456789|
 * -|---------|
 * 1|.*1//1*..|
 * 2|111//2231|
 * 3|/////1*1/|
 * 4|/////111/|
 * 5|//////111|
 * 6|/111//1*.|
 * 7|23*1//111|
 * 8|**21/////|
 * 9|..1//////|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 9 1 mine
 * 
 *  |123456789|
 * -|---------|
 * 1|.*1//1*.*|
 * 2|111//2231|
 * 3|/////1*1/|
 * 4|/////111/|
 * 5|//////111|
 * 6|/111//1*.|
 * 7|23*1//111|
 * 8|**21/////|
 * 9|..1//////|
 * -|---------|
 * Congratulations! You found all the mines!
 * Example 3: the user wins by exploring all safe cells
 * 
 * How many mines do you want on the field? > 5
 * 
 *  |123456789|
 * -|---------|
 * 1|.........|
 * 2|.........|
 * 3|.........|
 * 4|.........|
 * 5|.........|
 * 6|.........|
 * 7|.........|
 * 8|.........|
 * 9|.........|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 5 5 free
 * 
 *  |123456789|
 * -|---------|
 * 1|/////////|
 * 2|/////111/|
 * 3|111//1.1/|
 * 4|..1//1.21|
 * 5|111//1...|
 * 6|/////1.21|
 * 7|/////111/|
 * 8|111//////|
 * 9|..1//////|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 1 9 free
 * 
 *  |123456789|
 * -|---------|
 * 1|/////////|
 * 2|/////111/|
 * 3|111//1.1/|
 * 4|..1//1.21|
 * 5|111//1...|
 * 6|/////1.21|
 * 7|/////111/|
 * 8|111//////|
 * 9|1.1//////|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 1 4 free
 * 
 *  |123456789|
 * -|---------|
 * 1|/////////|
 * 2|/////111/|
 * 3|111//1.1/|
 * 4|1.1//1.21|
 * 5|111//1...|
 * 6|/////1.21|
 * 7|/////111/|
 * 8|111//////|
 * 9|1.1//////|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 7 4 free
 * 
 *  |123456789|
 * -|---------|
 * 1|/////////|
 * 2|/////111/|
 * 3|111//1.1/|
 * 4|1.1//1121|
 * 5|111//1...|
 * 6|/////1.21|
 * 7|/////111/|
 * 8|111//////|
 * 9|1.1//////|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 7 5 free
 * 
 *  |123456789|
 * -|---------|
 * 1|/////////|
 * 2|/////111/|
 * 3|111//1.1/|
 * 4|1.1//1121|
 * 5|111//11..|
 * 6|/////1.21|
 * 7|/////111/|
 * 8|111//////|
 * 9|1.1//////|
 * -|---------|
 * Set/unset mines marks or claim a cell as free: > 8 5 free
 * 
 *  |123456789|
 * -|---------|
 * 1|/////////|
 * 2|/////111/|
 * 3|111//1.1/|
 * 4|1.1//1121|
 * 5|111//112.|
 * 6|/////1.21|
 * 7|/////111/|
 * 8|111//////|
 * 9|1.1//////|
 * -|---------|
 * Congratulations! You found all the mines!
 */

 public class Battle {

	final static int size = 12;
	private static char[][] field = new char[size][size];
	static int numberOfMines;
	static int numberOfRefusedMines;
	private static List<int[]> mines = new ArrayList<>();
	private static List<int[]> refusedMines = new ArrayList<>();
	private static int[] userCoordinate = new int[2];
	private static String userChoice;
	private static Stack<int[]> toSearchCell = new Stack<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		askingUser(sc);
		buildField();
		makeMine();
		System.out.println();
		printField();
		boolean explosion = false;
		while (!explosion && numberOfRefusedMines != numberOfMines) {
			printMessage();
			userDataInput(sc);
			if (isOccupied(userCoordinate)) {
				System.out.println("There is a number here!");
				continue;
			}
			switch (userChoice) {
				case "free":
					if (contain(mines, new int[] { userCoordinate[0], userCoordinate[1] })) {
						exploreMines();
						explosion = true;
					} else {
						freeCell();
					}
					break;
				case "mine":
					neutralizeMine();
					break;
				default:
					continue;
			}
			System.out.println();
			printField();
		}
		String result = explosion ? "You stepped on a mine and failed!" : "Congratulations! You found all the mines!";
		printCongratulations(result);
		sc.close();
	}

	private static void freeCell() {
		field[userCoordinate[0]][userCoordinate[1]] = calcMines(userCoordinate);
		if (field[userCoordinate[0]][userCoordinate[1]] == '/') {
			makeStackToSearch(userCoordinate);
			while (!toSearchCell.isEmpty()) {
				int[] arrToSearch = toSearchCell.pop();
				field[arrToSearch[0]][arrToSearch[1]] = calcMines(arrToSearch);
				if (field[arrToSearch[0]][arrToSearch[1]] == '/') {
					makeStackToSearch(arrToSearch);
				}
			}
		}
	}

	private static void makeStackToSearch(int[] coordinate) {
		int startI = coordinate[0] - 1;
		int startJ = coordinate[1] - 1;
		int stopI = coordinate[0] + 1;
		int stopJ = coordinate[1] + 1;
		for (int x = startI; x <= stopI; x++) {
			if (x < 2 || x > field.length - 1) {
				continue;
			}
			for (int y = startJ; y <= stopJ; y++) {
				if (y < 2 || y > field.length - 1) {
					continue;
				}
				if (x == coordinate[0] && y == coordinate[1]) {
					continue;
				}
				if (contain(toSearchCell, new int[] { x, y })) {
					continue;
				} else if (field[x][y] == '.' || (field[x][y] == '*' && !contain(mines, new int[] { x, y }))) {
					toSearchCell.push(new int[] { x, y });
				}
			}
		}
	}

	private static boolean contain(Stack<int[]> stack, int[] arr) {
		for (int[] iar : stack) {
			if (Arrays.equals(iar, arr)) {
				return true;
			}
		}
		return false;
	}

	private static boolean contain(List<int[]> ls, int[] arr) {
		for (int[] iar : ls) {
			if (Arrays.equals(iar, arr)) {
				return true;
			}
		}
		return false;
	}

	private static void exploreMines() {
		for (int[] arr : mines) {
			field[arr[0]][arr[1]] = 'X';
		}
	}

	private static void neutralizeMine() {
		if (field[userCoordinate[0]][userCoordinate[1]] == '*') {
			numberOfRefusedMines--;
			field[userCoordinate[0]][userCoordinate[1]] = '.';
			deleteRefusedMines(new int[] { userCoordinate[0], userCoordinate[1] });
			return;
		}
		if (contain(mines, new int[] { userCoordinate[0], userCoordinate[1] })) {
			numberOfRefusedMines++;
			field[userCoordinate[0]][userCoordinate[1]] = '*';
		} else {
			field[userCoordinate[0]][userCoordinate[1]] = '*';
		}
		refusedMines.add(new int[] { userCoordinate[0], userCoordinate[1] });
	}

	private static void deleteRefusedMines(int[] arr) {
		Iterator<int[]> it = refusedMines.iterator();
		while (it.hasNext()) {
			int[] ar = it.next();
			if (Arrays.equals(arr, ar)) {
				it.remove();
				break;
			}
		}
	}

	private static void userDataInput(Scanner sc) {
		String[] data = sc.nextLine().split("\\s+");
		int j = Integer.valueOf(data[0]) + 1;
		int i = Integer.valueOf(data[1]) + 1;
		userCoordinate = new int[] { i, j };
		userChoice = data[2];
	}

	private static void printCongratulations(String s) {
		System.out.println(s);
	}

	private static boolean isOccupied(int[] coordinates) {
		if (Character.isDigit(field[coordinates[0]][coordinates[1]])) {
			return true;
		}
		return false;
	}

	private static void printMessage() {
		System.out.print("Set/delete mines marks (x and y coordinates):");
	}

	private static char calcMines(int[] coordinate) {
		char calc = '0';
		int startI = coordinate[0] - 1;
		int startJ = coordinate[1] - 1;
		int stopI = coordinate[0] + 1;
		int stopJ = coordinate[1] + 1;
		for (int x = startI; x <= stopI; x++) {
			if (x < 0 || x > field.length - 1) {
				continue;
			}
			for (int y = startJ; y <= stopJ; y++) {
				if (y < 0 || y > field.length - 1) {
					continue;
				}
				if (x == coordinate[0] && y == coordinate[1]) {
					continue;
				}
				if (contain(mines, new int[] { x, y })) {
					calc++;
				}
			}
		}
		return calc == '0' ? '/' : calc;
	}

	private static void askingUser(Scanner sc) {
		System.out.print("How many mines do you want on the field?");
		numberOfMines = Integer.valueOf(sc.nextLine());
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
		for (int i = 0, j = 2; i < numberOfMines; j++) {
			if (j >= size - 1) {
				j = 2;
			}
			int n = r.nextInt(9) + 2;
			int[] ar = new int[] { j % size, n };
			if (!mines.contains(ar)) {
				mines.add(ar);
				// field[j % size][n] = 'X';
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
				else if (x == 0 && (y > 1 && y < size - 1)) {
					field[x][y] = (char) (y - 1 + '0');
				}
				// build last line
				else if ((x == size - 1 || x == 1) && (y != 1 && y != size - 1)) {
					field[x][y] = '-';
				}
				// build boundary
				else if (y == 1 || y == size - 1) {
					field[x][y] = '|';
				} else if (y == 0 && (x > 1 && x < size - 1)) {
					field[x][y] = (char) (x - 1 + '0');
				} else {
					field[x][y] = '.';
				}
			}
		}
	}
}