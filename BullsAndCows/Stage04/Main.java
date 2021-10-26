package BullsAndCows.Stage04;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game g = new Game();
		g.startGame();
	}

	public static class Game {

		Scanner sc = new Scanner(System.in);

		Player humanPlayer;
		Player PC_Player;
		private int countBulls;
		private int countCows;
		private String secretNumber;
		private int lengthOfSecretNumber;

		public Game() {
		}

		private void createPlayers() {
			humanPlayer = new Player(this);
			PC_Player = new Player(this);
		}

		private void getSecretNumber(String number) {
			secretNumber = number;
		}

		private void startGame() {
			printGreetingsEnterLength();
			createPlayers();
			humanPlayer.setLengthOfSecretNumber();
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
				userNumber = humanPlayer.getUserVarinats();
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

	public static class Player {

		private Game g;
		private String secretNumber;
		private String firstAttempt;
		private int index = 0;
		private String lengthOfSecretNumber;

		public Player(Game g) {
			this.g = g;
		}

		private String createSecretNumber() {
			do {
				secretNumber = String.valueOf(System.nanoTime() % (int) Math.pow(10, g.lengthOfSecretNumber));
			} while (!isCorrect() || secretNumber.startsWith("0"));
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

		private void setLengthOfSecretNumber() {
			lengthOfSecretNumber = g.sc.nextLine();
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

		private String getUserVarinats() {
			return g.sc.nextLine();
		}
	}
}
