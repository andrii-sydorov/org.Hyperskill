package BullsAndCows.Stage01;

import java.util.Scanner;

public class Main {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game g = new Game(4);
		g.printGreetings();
		g.startGame();
		g.sayBye();
	}

	public static class Game {

		Player firstPlayer;
		Player secondPlayer;
		private int countBulls;
		private int countCows;

		private String secretNumber;
		private int lengthOfSecretNumber;

		public Game(int lengthOfSecretNumber) {
			this.lengthOfSecretNumber = lengthOfSecretNumber;
			firstPlayer = new Player(this);
			secondPlayer = new Player(this);
		}

		private void printGreetings() {
			System.out.println("The secret code is prepared: " + fillWithStars() + "\n");
		}

		private void getSecretNumber(String number) {
			secretNumber = number;
		}

		private String fillWithStars() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < lengthOfSecretNumber; i++) {
				sb.append("*");
			}
			return sb.toString();

		}

		private void startGame() {
			int index = 1;
			String userNumber = null;
			secondPlayer.g = this;
			firstPlayer.g = this;
			getSecretNumber(firstPlayer.createSecretNumber());
			do {
				System.out.println("Turn " + index + ". Answer:");
				userNumber = index == 1 ? secondPlayer.firstAttempt : secondPlayer.possibleVariants();
				countAnimals(userNumber);
				System.out.println("Grade: " + printResult() + "\n");
				index++;
			} while (!userNumber.equals(secretNumber));
		}

		private void countAnimals(String userNumber) {
			countBulls = 0;
			countCows = 0;
			for (int i = 0; i < userNumber.length(); i++) {
				if (secretNumber.charAt(i) == userNumber.charAt(i)) {
					countBulls++;
				} else if (secretNumber.contains(Character.toString(userNumber.charAt(i)))) {
					countCows++;
				}
			}
		}

		private String printResult() {
			String bulls = countBulls > 1 ? "bulls" : "bull";
			String cows = countCows > 1 ? "cows" : "cow";
			if (countBulls == 0 && countCows == 0) {
				return "None.";
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
			System.out.println("Congrats! The secret code " + secretNumber + " .");
		}

	}

	public static class Player {

		private Game g;
		private String secretNumber;
		private String firstAttempt = String
				.valueOf(System.currentTimeMillis() % (int) Math.pow(10, g.lengthOfSecretNumber));
		private int index = 0;

		public Player(Game g) {
			this.g = g;
		}

		private String createSecretNumber() {
			do {
				secretNumber = String.valueOf(System.currentTimeMillis() % (int) Math.pow(10, g.lengthOfSecretNumber));
			} while (!isCorrect() && secretNumber.startsWith("0"));
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
				sb.setCharAt(index, (char) i);
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

	abstract class Unusedmethod {

//		private boolean isDataValid(String s) {
//			if (s.length() != lengthOfSecretNumber) {
//				System.out.println("Invalid user input. The data should length 4 has.");
//				return false;
//			}
//			try {
//				int n = Integer.valueOf(s);
//			} catch (NumberFormatException nfe) {
//				System.out.println("The data should be number!");
//				return false;
//			}
//			return true;
//		}

	}
}
