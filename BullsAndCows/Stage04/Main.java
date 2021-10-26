package BullsAndCows.Stage04;

import java.util.Scanner;

/**
 * Description
 * 
 * In this stage, you should combine all the previous parts into a simple
 * playable version of the "Bulls and Cows" game. First, prompt the player to
 * input the length of the secret code. The length will determine the difficulty
 * level for the current game session. The program should generate a secret code
 * of the given length. Remember that it should consist of unique numbers.
 * 
 * Then, the game starts and the program prompts the player to guess the code.
 * When the player inputs a number, the program should grade it in bulls and
 * cows. The game goes on until the code is guessed, that is, until the number
 * of bulls is equal to the number of digits in the code. When the game ends,
 * the program should finish its execution. 
 * 
 * Objectives
 * 
 * In this stage, your program should:
 * 
 * 1. Ask for the length of the secret code and then generate the code. 
 * 2. Wait for the user input. 
 * 3. Grade the guessing attempt in bulls and cows. 
 * 4. If the secret code has been guessed, the program stops; otherwise, return to the second
 * step.
 * 
 * Example
 * 
 * The greater-than symbol followed by a space > represents the user input. Note
 * that it's not part of the input.
 * 
 * Please, enter the secret code's length: 
 * > 4 
 * Okay, let's start a game! 
 * Turn 1:
 * > 1234 
 * Grade: 1 bull and 1 cow 
 * Turn 2: 
 * > 7354 
 * Grade: 2 bulls and 1 cow 
 * Turn 3: 
 * > 9374 
 * Grade: 4 bulls Congratulations! 
 * You guessed the secret code.
 * 
 * @author SMD_ASY
 *
 */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game g = new Game();
		g.startGame();
	}

	public static class Game {

		Scanner sc = new Scanner(System.in);

		HumanPlayer humanPlayer;
		IIPlayer PC_Player;
		private int countBulls;
		private int countCows;
		private String secretNumber;
		private int lengthOfSecretNumber;

		public Game() {
		}

		private void createPlayers() {
			humanPlayer = new HumanPlayer(this);
			PC_Player = new IIPlayer(this);
		}

		private void getSecretNumber(String number) {
			secretNumber = number;
		}

		private void startGame() {
			printGreetingsEnterLength();
			createPlayers();
			humanPlayer.getLengthOfSecretNumber();
			try {
				lengthOfSecretNumber = Integer.valueOf(humanPlayer.lengthOfSecretNumber);
			} catch (NumberFormatException nfe) {
				System.out.println(nfe.getMessage());
				return;
			}
			if (lengthOfSecretNumber > 10 | lengthOfSecretNumber < 0) {
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
				countAnimals(userNumber);
				System.out.println("Grade: " + printResult() + "\n");
				index++;
			} while (!secretNumber.equals(userNumber));
			sayBye();
		}

		private void printStartGame() {
			System.out.println("Okay, let's start a game!");
		}

		private void printGreetingsEnterLength() {
			System.out.println("Please, enter the secret code's length:");
		}

		private void countAnimals(String userNumber) {
			countBulls = 0;
			countCows = 0;
			for (int i = 0; i < secretNumber.length(); i++) {
				if (secretNumber.charAt(i) == userNumber.charAt(i)) {
					countBulls++;
					continue;
				} else if (secretNumber.contains(Character.toString(userNumber.charAt(i)))) {
					countCows++;
				}
			}
		}

		private String printResult() {
			String bulls = "bull(s)";
			String cows = "cow(s)";
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
		private String secretNumber;
		private String firstAttempt;
		private int index = 0;

		public IIPlayer(Game g) {
			this.g = g;
		}

		private String createSecretNumber() {
			do {
				secretNumber = String.valueOf(System.currentTimeMillis() % (int) Math.pow(10, g.lengthOfSecretNumber));
			} while (!isCorrect() || secretNumber.length() < g.lengthOfSecretNumber);
			return secretNumber;
		}

		private boolean isCorrect() {
			String s = secretNumber;
			int i = 0;
			return contain(i, s);
		}

		private boolean contain(int i, String s) {
			if (s.substring(i).length() == 1) {
				return true;
			}
			if (s.substring(i + 1).contains(Character.toString(s.charAt(i)))) {
				return false;
			}
			i++;
			return contain(i, s);
		}

		private String possibleVariants() {
			if (index > firstAttempt.length()) {
				return firstAttempt;
			}
			StringBuilder sb = new StringBuilder(firstAttempt);
			int prevCountBulls = g.countBulls;
			for (int i = 0; i <= 9; i++) {
				sb.setCharAt(index, Character.forDigit(i, 10));
				g.countAnimals(sb.toString());
				if (prevCountBulls < g.countBulls) {
					break;
				} else if (prevCountBulls > g.countBulls) {
					return firstAttempt;
				} else {
					continue;
				}
			}
			firstAttempt = sb.toString();
			index++;
			return firstAttempt;
		}

	}

	public static class HumanPlayer {

		private String lengthOfSecretNumber;
		private String userVariants;
		Game g;

		public HumanPlayer(Game g) {
			this.g = g;
		}

		private void getLengthOfSecretNumber() {
			lengthOfSecretNumber = g.sc.nextLine();
		}

		private String getUserVariants() {
			userVariants = g.sc.nextLine();
			return userVariants;
		}
	}

}
