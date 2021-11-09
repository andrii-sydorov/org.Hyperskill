package BullsAndCows.Stage07;

import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * Description
 * 
 * There are a lot of error possibilities. What if someone enters an answer of
 * the wrong length? Or the number of possible symbols is less than the length
 * of the code? What if the answer contains invalid symbols? The game may crash
 * before the secret number was guessed!
 * 
 * Let's handle errors like this. At this point, you can implement this without
 * the try catch construction. Use the following rule of thumb: if you can avoid
 * the exception-based logic, avoid it! If you use exceptions in normal
 * situations, how would you deal with unusual (exceptional) situations? Now it
 * may not seem that important, but when you need to find errors in more complex
 * programs, this makes a difference. 
 * 
 * Objectives
 * 
 * In this stage, your program should:
 * 
 * Handle incorrect input. Print an error message that contains the word error.
 * After that, don't ask for the numbers again, just finish the program.
 * 
 * Examples
 * 
 * The greater-than symbol followed by a space > represents the user input. Note
 * that it's not part of the input.
 * 
 * Example 1
 * 
 * Input the length of the secret code: 
 * > 6 
 * Input the number of possible symbols in the code: 
 * > 5 
 * Error: it's not possible to generate a code with a length of 6 with 5 unique symbols.
 * 
 * Example 2
 * 
 * Input the length of the secret code: 
 * > abc 0 -7 
 * Error: "abc 0 -7" isn't a valid number.
 * 
 * Example 3
 * 
 * Input the length of the secret code: 
 * > 6 
 * Input the number of possible symbols in the code: 
 * > 37 
 * Error: maximum number of possible symbols in the code is 36 (0-9, a-z).
 * 
 * Example 4
 * 
 * Input the length of the secret code: 
 * > 4 
 * Input the number of possible symbols in the code: 
 * > 12 
 * The secret is prepared: **** (0-9, a-b). 
 * Okay, let's start a game! 
 * Turn 1: 
 * > a234 
 * Grade: 1 bull and 1 cow 
 * Turn 2: 
 * > 73b4 
 * Grade: 2 bulls and 1 cow 
 * Turn 3: 
 * > 9374 
 * Grade: 4 bulls 
 * 
 * Congratulations! You guessed the secret code.
 * 
 * @author ASY
 *
 */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game g = new Game();
		g.startGame();
	}

	public static class Game {

		private static String possibleSymbols;
		Scanner sc = new Scanner(System.in);
		HumanPlayer humanPlayer;
		IIPlayer PC_Player;
		private int countBulls;
		private int countCows;
		private String secretNumber;
		private int lengthOfSecretNumber;
		private int numberOfPossibleSymbols;

		static {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i <= 9; i++) {
				sb.append(Character.forDigit(i, 10));
			}
			for (int i = 'a'; i <= 'z'; i++) {
				sb.append((char) i);
			}
			possibleSymbols = sb.toString();
		}

		private void createPlayers() {
			humanPlayer = new HumanPlayer(this);
			PC_Player = new IIPlayer(this);
		}

		private void getSecretNumber(String number) {
			secretNumber = number;
		}

		private void startGame() {
			createPlayers();
			printEnterLength();
			humanPlayer.getLengthOfSecretNumber();
			try {
				isUserInputOk();
			} catch (myException mex) {
				System.out.println(mex.getMessage());
				return;
			}
			printNumberOfSymbols();
			humanPlayer.getNumberOfSymbols();
			try {
				isConditionOk();
			} catch (myException mex) {
				System.out.println(mex.getMessage());
				return;
			}
			getSecretNumber(PC_Player.createSecretNumber());
			printStartGame();
			String userNumber = null;
			int index = 1;
			do {
				System.out.println("Turn " + index + " :");
				userNumber = humanPlayer.getUserVariants();
				if (userNumber.length() != secretNumber.length()) {
					System.out.println("Invalid data length!");
					return;
				}
				countAnimals(userNumber);
				System.out.println("Grade: " + printResult() + "\n");
				index++;
			} while (!secretNumber.equals(userNumber));
			sayBye();
		}

		private void isUserInputOk() throws myException {
			try {
				lengthOfSecretNumber = Integer.parseInt(humanPlayer.lengthOfSecretNumber, 10);
			} catch (Exception ex) {
				throw new myException(
						String.format("Error: \"%s\" isn't a valid number.", humanPlayer.lengthOfSecretNumber));
			}
		}

		private void isConditionOk() throws myException {
			try {
				numberOfPossibleSymbols = Integer.parseInt(humanPlayer.numberOfSymbols, 10);
			} catch (Exception ex) {
				throw new myException(
						String.format("Error: \"%s\" isn't a valid number.", humanPlayer.numberOfSymbols));
			}
			if (numberOfPossibleSymbols > possibleSymbols.length()) {
				throw new myException("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");

			}
			if (lengthOfSecretNumber <= 0) {
				throw new myException("Error: the length of secretnumber caanot be equal to 0.");
			}
			if (lengthOfSecretNumber > numberOfPossibleSymbols) {
				throw new myException(String.format(
						"Error: it's not possible to generate a code with a length of %d with %d unique symbols.",
						lengthOfSecretNumber, numberOfPossibleSymbols));
			}
		}

		private void printStartGame() {
			String stars = "";
			for (int i = 0; i < lengthOfSecretNumber; i++) {
				stars += "*";
			}

			String digitPart = numberOfPossibleSymbols == 0 ? ""
					: numberOfPossibleSymbols == 1 ? "" + possibleSymbols.charAt(0)
							: numberOfPossibleSymbols <= 9
									? possibleSymbols.charAt(0) + "-" + possibleSymbols.charAt(numberOfPossibleSymbols)
									: possibleSymbols.charAt(0) + "-" + possibleSymbols.charAt(9);
			String characterPart = numberOfPossibleSymbols < 10 ? ""
					: possibleSymbols.charAt(10) + "-" + possibleSymbols.charAt(numberOfPossibleSymbols - 1);
			String area = characterPart.isEmpty() ? "(" + digitPart + ")" : "(" + digitPart + "," + characterPart + ")";
			System.out.println("The secret is prepared: " + stars + " " + area);
			System.out.println("Okay, let's start a game!");
		}

		private void printEnterLength() {
			System.out.println("Please, enter the secret code's length:");
		}

		private void printNumberOfSymbols() {
			System.out.println("Input the number of possible symbols in the code:");
		}

		private void countAnimals(String userNumber) {
			countBulls = 0;
			countCows = 0;
			List<String> ls = new ArrayList<>();
			for (int i = 0; i < secretNumber.length(); i++) {
				if (secretNumber.charAt(i) == userNumber.charAt(i)) {
					countBulls++;
					ls.add(Character.toString(userNumber.charAt(i)));
				}
			}
			for (int i = 0; i < secretNumber.length(); i++) {
				if (secretNumber.contains(Character.toString(userNumber.charAt(i)))
						&& !ls.contains(Character.toString(userNumber.charAt(i)))) {
					ls.add(Character.toString(userNumber.charAt(i)));
					countCows++;
				}
			}
		}

		private String printResult() {
			String bulls = countBulls == 1 ? "bull" : "bulls";
			String cows = countCows == 1 ? "cow" : "cows";
			if (countBulls == 0 && countCows == 0) {
				return "None. ";
			}
			if (countBulls == 0) {
				return countCows + " " + cows + ".";
			}
			if (countCows == 0) {
				return countBulls + " " + bulls + ".";
			}
			return countBulls + " " + bulls + " and " + countCows + " " + cows + ".";
		}

		private void sayBye() {
			System.out.println("Congratulations! You guessed the secret code.");
		}

	}

	public static class IIPlayer {

		private Game g;

		public IIPlayer(Game g) {
			this.g = g;
		}

		private String createSecretNumber() {
			List<String> ls = new ArrayList<>();
			do {
				int index = new Random().nextInt(g.numberOfPossibleSymbols);
				String temp = Character.toString(Game.possibleSymbols.charAt(index));
				if (!ls.contains(temp)) {
					ls.add(temp);
				}
			} while (ls.size() != g.lengthOfSecretNumber);
			return ls.toString().replaceAll("[^0-9a-z]", "");
		}

	}

	public static class HumanPlayer {

		private String lengthOfSecretNumber;
		private String numberOfSymbols;
		Game g;

		public HumanPlayer(Game g) {
			this.g = g;
		}

		private void getLengthOfSecretNumber() {
			lengthOfSecretNumber = g.sc.nextLine();
		}

		private void getNumberOfSymbols() {
			numberOfSymbols = g.sc.nextLine();
		}

		private String getUserVariants() {
			return g.sc.nextLine();
		}
	}

}

class myException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public myException(String userMessage) {
		super(userMessage);
	}

	public myException() {
		super();
	}
}
