package BullsAndCows.Stage01;

import java.util.Scanner;

public class Main {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game g = new Game();
		g.createSecretNumber();
		g.printGreetings();
		g.fillWithStarts();
		g.startGame();
		g.sayBye();
	}

	public static class Game {

		private String secretNumber;
		private int lengthOfSecretNumber = 4;

		private void createSecretNumber() {
			secretNumber = "9305";
//			do {
//				secretNumber = String.valueOf(System.currentTimeMillis() % (int) Math.pow(10, lengthOfSecretNumber));
//			} while (!isCorrect() && secretNumber.startsWith("0"));
		}

		private void printGreetings() {
			System.out.println("The secret code is prepared: " + fillWithStarts() + "\n");
		}

		private String fillWithStarts() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < secretNumber.length(); i++) {
				sb.append("*");
			}
			return sb.toString();

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

		private void startGame() {
			int index = 1;
			String userNumber = null;
			do {
				System.out.println("Turn " + index + ". Answer:");
				userNumber = sc.nextLine();
				if (!isDataValid(userNumber)) {
					continue;
				}
				System.out.println("Grade: " + countAnimals(userNumber) + "\n");
			} while (!userNumber.equals(secretNumber));
		}

		private String countAnimals(String userNumber) {
			int countBulls = 0;
			int countCows = 0;
			for (int i = 0; i < userNumber.length(); i++) {
				if (secretNumber.charAt(i) == userNumber.charAt(i)) {
					countBulls++;
				} else if (secretNumber.contains(Character.toString(userNumber.charAt(i)))) {
					countCows++;
				}
			}
			String bulls = countBulls > 1 ? "bulls" : "bull";
			String cows = countCows > 1 ? "cows" : "cow";
			return countBulls + " " + bulls + " and " + countCows + " " + cows + ".";
		}

		private boolean isDataValid(String s) {
			if (s.length() != lengthOfSecretNumber) {
				System.out.println("Invalid user input. The data should length 4 has.");
				return false;
			}
			try {
				int n = Integer.valueOf(s);
			} catch (NumberFormatException nfe) {
				System.out.println("The data should be number!");
				return false;
			}
			return true;
		}
		
		private void sayBye() {
			System.out.println("Congrats! The secret code " + secretNumber + " .");
		}

	}
}
