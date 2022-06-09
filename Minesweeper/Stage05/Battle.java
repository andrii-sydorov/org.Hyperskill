package Minesweeper.Stage05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

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
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		askingUser(sc);
		buildField();
		makeMine();
		System.out.println();
		printField();
		boolean explosion = false;
		while (!explosion && mines.size() != 0 && numberOfRefusedMines != numberOfMines) {
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
				if (contain(toSearchCell, new int[] { x, y })) {
					continue;
				} else if (field[x][y] == '.') {
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
			deleteMines(new int[] { userCoordinate[0], userCoordinate[1] });
			field[userCoordinate[0]][userCoordinate[1]] = '*';
		} else {
			field[userCoordinate[0]][userCoordinate[1]] = '*';
		}
		numberOfRefusedMines++;
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

	private static void deleteMines(int[] arr) {
		Iterator<int[]> it = mines.iterator();
		while (it.hasNext()) {
			int[] ar = it.next();
			if (Arrays.equals(ar, arr)) {
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