package BullsAndCows.Stage05;

import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * Description
 * 
 * The algorithm suggested for generating the secret code in the previous stage
 * was really a “reinvention of the wheel”. Java already has the tools for
 * generating random numbers! Research some common pseudo-random generation
 * methods such as Math.random() and other methods from the Random class. Choose
 * the method you like and use it to rewrite the secret code generation.
 * 
 * Nothing else is supposed to change at this stage: the program asks for the
 * length, generates a secret code, and then receives and grades the attempts
 * until the code is guessed. Your task here is to rewrite the code generator
 * without breaking the existing code.
 * 
 * Objective
 * 
 * In this stage, rewrite the secret code generator using a suitable Java
 * method. Example
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

		Scanner sc = new Scanner(System.in);

		HumanPlayer humanPlayer;
		IIPlayer PC_Player;
		private int countBulls;
		private int countCows;
		private String secretNumber;
		private int lengthOfSecretNumber;
		private List<Integer> ls = new ArrayList<>();

		public Game() {
		}

		private void createPlayers() {
			humanPlayer = new HumanPlayer(this);
			PC_Player = new IIPlayer(this);
		}

		private void getSecretNumber(String number) {
			secretNumber = number;
			secretNumber = "9374";
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
					ls.add(Character.getNumericValue(userNumber.charAt(i)));
					continue;
				}
			}
			for (int i = 0; i < secretNumber.length(); i++) {
				if (secretNumber.contains(Character.toString(userNumber.charAt(i)))
						&& !ls.contains(Character.getNumericValue(userNumber.charAt(i)))) {
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
		private String secretNumber;
		private String firstAttempt;
		private int index = 0;

		public IIPlayer(Game g) {
			this.g = g;
		}

		private String createSecretNumber() {
			do {
				// secretNumber = String.valueOf(System.currentTimeMillis() % (int) Math.pow(10,
				// g.lengthOfSecretNumber));
				int lowerLimit = (int) Math.pow(10, g.lengthOfSecretNumber) - 1;
				secretNumber = String.valueOf(new Random().nextInt(lowerLimit));
			} while (!isCorrect() || secretNumber.length() != g.lengthOfSecretNumber);
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
