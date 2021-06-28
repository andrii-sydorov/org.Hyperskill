package TicTacToe_04;

public class BattleField {

	private int rowNumber;
	private int columnNumber;
	private char[][] field;
	private int xNumber;
	private int oNumber;

	public BattleField(int number) {
		this.columnNumber = number;
		this.rowNumber = number;
		field = new char[this.rowNumber + 2][(this.columnNumber) + (this.columnNumber - 1) + 4];
	}

	/**
	 * 
	 * @return the field of the game
	 */
	public char[][] getField() {
		return this.field;
	}

	/**
	 * initial setup of the marks
	 * 
	 * @param startCondition
	 */
	public void makeField() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (i == 0 || i == field.length - 1) {
					field[i][j] = '-';
				} else if (j == 0 || j == field[i].length - 1) {
					field[i][j] = '|';
				} else {
					field[i][j] = ' ';
				}
			}
		}
	}

	/**
	 * printing field in console
	 */
	public void printField() {
		for (char[] c : field) {
			for (char d : c) {
				System.out.print(d);
			}
			System.out.println();
		}
	}

	public int getxNumber() {
		return xNumber;
	}

	public void setxNumber(int xNumber) {
		this.xNumber = xNumber;
	}

	public int getoNumber() {
		return oNumber;
	}

	public void setoNumber(int oNumber) {
		this.oNumber = oNumber;
	}

	/**
	 * the data input at the field
	 * 
	 * @param coordinates one dimensional array, from user
	 * @param mark        the mark, that should be leaved on the battlefield
	 */
	public void setField(int[] coordinates, char mark) {
		this.field[coordinates[0]][coordinates[1] * 2] = mark;
		if (mark == 'X') {
			xNumber++;
		} else {
			oNumber++;
		}
	}
}
