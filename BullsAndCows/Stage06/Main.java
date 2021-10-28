package BullsAndCows.Stage06;

import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * Description
 * 
 * Some players need a challenge, so let's make the secret code in the game
 * harder to guess. Add support for more than 10 symbols by adding letters. Now,
 * the secret code can contain the numbers 0-9 and the lowercase Latin
 * characters a-z. The new maximum length for the code is 36. Note that the
 * length of the secret word may not match the number of possible characters in
 * the secret code, so you should request t input twice: once for the secret
 * code length and once for the number of possible characters.
 * 
 * Also, since a secret code is not a number anymore, the symbol 0 should be
 * allowed as the first character in a secret code. 
 * 
 * Objectives
 * 
 * In this step, your program should:
 * 
 * 1. Ask for the length of the secret code. 
 * 2. Ask for the range of possible characters in the secret code.
 * 3. Generate a secret code using numbers and characters. This time, you should also print the secret code using *
 * characters and print which characters were used to generate the secret code.
 * 4. Function as a fully playable game.
 * 
 * Example
 * 
 * The greater-than symbol followed by a space > represents the user input. Note
 * that it's not part of the input.
 * 
 * Input the length of the secret code: 
 * > 4 
 * Input the number of possible symbols in the code: 
 * > 16 
 * The secret is prepared: **** (0-9, a-f). 
 * Okay, let's start a game! 
 * Turn 1: 
 * > 1a34 
 * Grade: 1 bull and 1 cow 
 * Turn 2:
 * > b354 
 * Grade: 2 bulls and 1 cow 
 * Turn 3: 
 * > 93b4 
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
			printNumberOfSymbols();
			humanPlayer.getNumberOfSymbols();
			try {
				lengthOfSecretNumber = Integer.valueOf(humanPlayer.lengthOfSecretNumber);
				numberOfPossibleSymbols = Integer.valueOf(humanPlayer.numberOfSymbols);
			} catch (NumberFormatException nfe) {
				System.out.println(nfe.getMessage());
				return;
			}
			if (lengthOfSecretNumber > possibleSymbols.length() | lengthOfSecretNumber < 0) {
				System.out.println("Error: can't generate a secret number with a length of " + lengthOfSecretNumber
						+ " because there aren't enough unique digits.");
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
