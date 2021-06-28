package TicTacToe_02;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

	BattleField b;
	Scanner sc;
	User[] u;
	int[] coordinates;
	int numbersField;
	boolean isEnd;

	public Game(int usersNumber, int numbersField) {
		sc = new Scanner(System.in);
		this.numbersField = numbersField;
		b = new BattleField(numbersField);
		u = new User[usersNumber];
	}

	/**
	 * start method of the game
	 */
	public void start() {
		System.out.println("Enter the cells:");
		gameInitiliazation();
		b.makeField();
		b.printField();
		while (true) {
			for (int i = 0; i < u.length; i++) {
				User currentUser = u[i];
				while (true) {
					currentUser.askUser();
					if (checkNotInstanceOfInteger(currentUser) || checkNotInRange() || checkIsOccupied()
							|| checkLength(currentUser)) {
						continue;
					}
					break;
				}
				setField();
				b.printField();
				if (isFinished()) {
					return;
				} else {
					if (b.getoNumber() + b.getxNumber() == numbersField * numbersField) {
						System.out.println("Draw");
						return;
					}
				}
			}
		}
	}

	/**
	 * game initialization, defining the user's property
	 */
	private void gameInitiliazation() {
		if (u.length == 1) {
			u[0] = new HumanUser(sc);
		} else {
			u[0] = new HumanUser(sc);
			u[1] = new IIUser(this);
		}
	}

	/**
	 * checking the length of input data
	 * 
	 * @param u current user, user has string value input
	 * @return boolean value: true if user has inputed two datas, and false when not
	 */
	private boolean checkLength(User u) {
		if (u.getDataInput().length != 2) {
			System.out.println("The data must contain two coordinates");
			return true;
		}
		return false;
	}

	/**
	 * checking the data format of input data
	 * 
	 * @param u getting String input of current user
	 * @return true if user entered integer value, false when not
	 */
	private boolean checkNotInstanceOfInteger(User u) {
		try {
			this.coordinates = Arrays.stream(u.getDataInput()).mapToInt(Integer::valueOf).toArray();
		} catch (NumberFormatException nfe) {
			System.out.println("You should enter numbers!");
			return true;
		}
		return false;
	}

	/**
	 * check that value should be in range
	 * 
	 * @return true: when data is in range, and false when not
	 */
	private boolean checkNotInRange() {
		for (int i = 0; i < coordinates.length; i++) {
			if (this.coordinates[i] < 1 || this.coordinates[i] > this.numbersField) {
				System.out.printf("Coordinates should be from 1 to %d!\n", this.numbersField);
				return true;
			}
		}
		return false;
	}

	/**
	 * check that cell is occupied or not
	 * 
	 * @return true: when not, and false: when yes
	 */
	private boolean checkIsOccupied() {
		char[][] battleField = b.getField();
		if (battleField[this.coordinates[0]][this.coordinates[1] * 2] != ' ') {
			System.out.println("This cell is occupied! Choose another one!");
			return true;
		}
		return false;
	}

	/**
	 * mark in field set
	 */
	private void setField() {
		b.setField(this.coordinates, defineMark());
	}

	/**
	 * define the next mark
	 * 
	 * @return
	 */
	private char defineMark() {
		if (b.getxNumber() > b.getoNumber()) {
			int oNumber = b.getoNumber();
			b.setoNumber(oNumber++);
			return 'O';
		} else {
			int xNumber = b.getxNumber();
			b.setxNumber(xNumber++);
			return 'X';
		}
	}

	/**
	 * checking if the game is finished
	 */
	private boolean isFinished() {
		char[][] field = b.getField();
		if (checkRow(field) || checkColumn(field) || checkDiagonal(field)) {
			return true;
		}
		return false;
	}

	/**
	 * checking the row of the battlefield
	 * 
	 * @param field current battlefield
	 * @return true, when the game is finished according the row rule
	 */
	private boolean checkRow(char[][] field) {
		for (int i = 1; i <= numbersField; i++) {
			int sumRow = 0;
			for (int j = 2; j <= numbersField * 2; j += 2) {
				sumRow += field[i][j];
			}
			if (sumRow / 3 == 'X') {
				System.out.println("X wins");
				return true;
			}
			if (sumRow / 3 == 'O') {
				System.out.println("O wins");
				return true;
			}
		}
		return false;
	}

	/**
	 * checking the column of the battlefield
	 * 
	 * @param field current battlefield
	 * @return true, when the game is finished according the column rule
	 */
	private boolean checkColumn(char[][] field) {
		for (int j = 2; j <= numbersField * 2; j += 2) {
			int sumColumn = 0;
			for (int i = 1; i <= numbersField; i++) {
				sumColumn += field[i][j];
			}
			if (sumColumn / numbersField == 'X') {
				System.out.println("X wins");
				return true;
			}
			if (sumColumn / numbersField == 'O') {
				System.out.println("O wins");
				return true;
			}
		}
		return false;
	}

	/**
	 * checking the diagonal of the battlefield
	 * 
	 * @param field current battlefield
	 * @return true, when the game is finished according the diagonal rule
	 */
	private boolean checkDiagonal(char[][] field) {
		int sumMainDiagonal = 0;
		int sumSecondaryDiagonal = 0;
		for (int i = 1; i <= numbersField; i++) {
			for (int j = 2; j <= numbersField * 2; j += 2) {
				if (2 * i == j) {
					sumMainDiagonal += field[i][j];
				}
				/*
				 * offset while numeration is started not from 0
				 */
				if (i + j / 2 == numbersField + 1) {
					sumSecondaryDiagonal += field[i][j];
				}
			}
		}
		if (sumMainDiagonal / 3 == 'X' || sumSecondaryDiagonal / 3 == 'X') {
			System.out.println("X wins");
			return true;
		}
		if (sumMainDiagonal / 3 == 'O' || sumSecondaryDiagonal / 3 == 'O') {
			System.out.println("O wins");
			return true;
		}
		return false;
	}

}
