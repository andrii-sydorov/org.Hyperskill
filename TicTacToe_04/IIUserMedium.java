package TicTacToe_04;

import java.util.Random;

public class IIUserMedium implements User {

	Game g;
	char[][] field;
	char IIMark;
	Random r = new Random();
	private String[] dataInput = new String[2];

	public IIUserMedium(Game g) {
		this.g = g;
		field = this.g.b.getField();
		IIMark = g.b.getxNumber() >= g.b.getoNumber() ? 'O' : 'X';
	}

	@Override
	public String[] getDataInput() {
		return this.dataInput;
	}

	@Override
	public void askUser() {
		System.out.println("Making move level \"medium\"");
		dataInput = iiSolution(field, IIMark);
		if (dataInput == null) {
			dataInput = iiRandom();
		}
		System.out.println(dataInput[0] + " " + dataInput[1]);
	}
/**
 * this method realized the logik of the II, to win and not to lose
 * @param field the battlefield
 * @param mark the mark of II
 * @return
 */
	private String[] iiSolution(char[][] field, char mark) {
		String[] ans = null;
		ans = iiSolutionRaws(field, mark);
		if (ans == null) {
			ans = iiSolutionColumns(field, mark);
		}
		if (ans == null) {
			ans = iiSolutionMainDiagonal(field, mark);
		}
		if (ans == null) {
			ans = iiSolutionSecondaryDiagonal(field, mark);
		}
		return ans;
	}

	private String[] iiSolutionRaws(char[][] field, char mark) {
		String[] ansToWin = null;
		String[] ansToExceptLose = null;
		for (int i = 1; i <= g.numbersField; i++) {
			int numbersEnemiesMark = 0;
			int numbersMyMark = 0;
			int numberComplete = 0;
			int tempColumnToExceptLose = 0;
			int tempColumnToWin = 0;
			for (int j = 2; j <= g.numbersField * 2; j += 2) {
				if (field[i][j] != ' ' && field[i][j] != mark) {
					numbersEnemiesMark++;
					numberComplete++;
				} else {
					tempColumnToExceptLose = j;
				}
				if (field[i][j] == mark) {
					numbersMyMark++;
					numberComplete++;
				} else {
					tempColumnToWin = j;
				}
			}
			if (numbersMyMark == 2 && numberComplete != 3) {
				ansToWin = new String[] { String.valueOf(i), String.valueOf(tempColumnToWin / 2) };
			}
			if (numbersEnemiesMark == 2 && numberComplete != 3) {
				ansToExceptLose = new String[] { String.valueOf(i), String.valueOf(tempColumnToExceptLose / 2) };
			}
		}
		System.out.println("From raws method");
		return ansToWin != null ? ansToWin : ansToExceptLose != null ? ansToExceptLose : null;
	}

	private String[] iiSolutionColumns(char[][] field, char mark) {
		String[] ansToWin = null;
		String[] ansToExceptLose = null;
		for (int j = 2; j <= g.numbersField * 2; j += 2) {
			int numbersEnemiesMark = 0;
			int numbersMyMark = 0;
			int numberComplete = 0;
			int tempRawToExceptLose = 0;
			int tempRawToWin = 0;
			for (int i = 1; i <= g.numbersField; i++) {
				if (field[i][j] != ' ' && field[i][j] != mark) {
					numbersEnemiesMark++;
					numberComplete++;
				} else {
					tempRawToExceptLose = i;
				}
				if (field[i][j] == mark) {
					numbersMyMark++;
					numberComplete++;
				} else {
					tempRawToWin = i;
				}
			}
			if (numbersMyMark == 2 && numberComplete != 3) {
				ansToWin = new String[] { String.valueOf(tempRawToWin), String.valueOf(j / 2) };
			}
			if (numbersEnemiesMark == 2 && numberComplete != 3) {
				ansToExceptLose = new String[] { String.valueOf(tempRawToExceptLose), String.valueOf(j / 2) };
			}
		}
		System.out.println("From columns method");
		return ansToWin != null ? ansToWin : ansToExceptLose != null ? ansToExceptLose : null;
	}

	private String[] iiSolutionMainDiagonal(char[][] field, char mark) {
		String[] ansToWin = null;
		String[] ansToExceptLose = null;
		int numbersEnemiesMark = 0;
		int numbersMyMark = 0;
		int numberComplete = 0;
		int tempMainDiagonalToExceptLoseRaws = 0;
		int tempMainDiagonalToExceptLoseColumns = 0;
		int tempMainDiagonalToWinRaws = 0;
		int tempMainDiagonalToWinColumns = 0;
		for (int i = 1; i <= g.numbersField; i++) {
			for (int j = 2; j <= g.numbersField * 2; j += 2) {
				if (j == 2 * i) {
					if (field[i][j] != ' ' && field[i][j] != mark) {
						numbersEnemiesMark++;
						numberComplete++;
					} else {
						tempMainDiagonalToExceptLoseColumns = j;
						tempMainDiagonalToExceptLoseRaws = i;
					}
					if (field[i][j] == mark) {
						numbersMyMark++;
						numberComplete++;
					} else {
						tempMainDiagonalToWinColumns = j;
						tempMainDiagonalToWinRaws = i;
					}
				}
			}
		}
			if (numbersMyMark == 2 && numberComplete != 3) {
				ansToWin = new String[] { String.valueOf(tempMainDiagonalToWinRaws),
						String.valueOf(tempMainDiagonalToWinColumns / 2) };
			}
			if (numbersEnemiesMark == 2 && numberComplete != 3) {
				ansToExceptLose = new String[] { String.valueOf(tempMainDiagonalToExceptLoseRaws),
						String.valueOf(tempMainDiagonalToExceptLoseColumns / 2) };
			}
		System.out.println("From main diagonal");
		return ansToWin != null ? ansToWin : ansToExceptLose != null ? ansToExceptLose : null;
	}

	private String[] iiSolutionSecondaryDiagonal(char[][] field, char mark) {
		String[] ansToWin = null;
		String[] ansToExceptLose = null;
		int numbersEnemiesMark = 0;
		int numbersMyMark = 0;
		int numberComplete = 0;
		int tempSecondaryDiagonalToExceptLoseRaws = 0;
		int tempSecondaryDiagonalToExceptLoseColumns = 0;
		int tempSecondaryDiagonalToWinRaws = 0;
		int tempSecondaryDiagonalToWinColumns = 0;
		for (int i = 1; i <= g.numbersField; i++) {
			for (int j = 2; j <= g.numbersField * 2; j += 2) {
				if (i + j / 2 == g.numbersField + 1) {
					if (field[i][j] != ' ' && field[i][j] != mark) {
						numbersEnemiesMark++;
						numberComplete++;
					} else {
						tempSecondaryDiagonalToExceptLoseColumns = j;
						tempSecondaryDiagonalToExceptLoseRaws = i;
					}
					if (field[i][j] == mark) {
						numbersMyMark++;
						numberComplete++;
					} else {
						tempSecondaryDiagonalToWinColumns = j;
						tempSecondaryDiagonalToWinRaws = i;
					}
				}

			}
		}
			if (numbersMyMark == 2 && numberComplete != 3) {
				ansToWin = new String[] { String.valueOf(tempSecondaryDiagonalToWinRaws),
						String.valueOf(tempSecondaryDiagonalToWinColumns / 2) };
			}
			if (numbersEnemiesMark == 2 && numberComplete != 3) {
				ansToExceptLose = new String[] { String.valueOf(tempSecondaryDiagonalToExceptLoseRaws),
						String.valueOf(tempSecondaryDiagonalToExceptLoseColumns / 2) };
			}
		System.out.println("From secondary diagonal");
		return ansToWin != null ? ansToWin : ansToExceptLose != null ? ansToExceptLose : null;
	}

	private String[] iiRandom() {
		String[] ans = new String[2];
		final int n = 2;
		for (int i = 0; i < n; i++) {
			ans[i] = String.valueOf(r.nextInt(g.numbersField) + 1);
		}
		System.out.println("From random method");
		return ans;
	}

}
