package BullsAndCows.Stage02;

import java.util.Scanner;

/**
 * Description
 * 
 * Let's add some interactivity to our program. The program should create a
 * 4-digit secret code, and the player should try to guess it on the first try.
 * The program should give a grade to evaluate how accurate the player was.
 * 
 * As you remember, a correctly guessed digit is a cow, and if its position is
 * also correct, then it is a bull. After the player tries to guess the secret
 * code, the program should grade the attempt and finish the execution.
 * 
 * For example, if the secret code is 9305, then:
 * 
 * The number 9305 contains 4 bulls and 0 cows since all 4 digits are correct
 * and their positions are correct as well. It's the only number that can
 * contain 4 bulls, so it's also the secret code itself. 
 * The numbers 3059, 3590, 5930, 5039 contain 0 bulls and 4 cows since all 4 digits are correct but
 * their positions don't match the positions in the secret code. 
 * The numbers 9306, 9385, 9505, 1305 contain 3 bulls. 
 * The numbers 9350, 9035, 5309, 3905 contain 2 bulls and 2 cows. 
 * The numbers 1293, 5012, 3512, 5129 contain 0 bulls and 2 cows. 
 * The numbers 1246, 7184, 4862, 2718 contain 0 bulls and 0 cows.
 * 
 * Note that guesses can contain repetitive digits, so:
 * 
 * The number 1111 contains 0 bulls and 0 cows. 
 * The number 9999 contains 1 bull and 3 cows. 
 * The number 9955 contains 2 bulls and 2 cows.
 * 
 * Objectives
 * 
 * In this stage, your goal is to write the core part of the game: the grader.
 * 
 * 1. Your program should take a 4-digit number as an input. 
 * 2. Use a predefined 4-digit code and grade the answer that was input. You can do it digit by
 * digit.
 * 
 * The grade is considered correct if it contains number-and-word pairs (like X
 * bulls and Y cows) that give the correct information. If the answer doesn't
 * contain any bulls and cows, you should output None. 
 * 
 * Examples
 * 
 * The greater-than symbol followed by a space > represents the user input. Note
 * that it's not part of the input.
 * 
 * Example 1
 * 
 * > 1234 
 * Grade: 1 cow(s). The secret code is 9305.
 * 
 * Example 2
 * 
 * > 9087 
 * Grade: 1 bull(s) and 1 cow(s). The secret code is 9305.
 * 
 * Example 3
 * 
 * > 1267 
 * Grade: None. The secret code is 9305.
 * 
 * @author ASY
 *
 */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game g = new Game(4);
		g.startGame();
	}

	public static class Game {

		Scanner sc = new Scanner(System.in);

		Player firstPlayer;
		Player secondPlayer;
		private int countBulls;
		private int countCows;

		private String secretNumber;
		private int lengthOfSecretNumber;

		public Game(int lengthOfSecretNumber) {
			this.lengthOfSecretNumber = lengthOfSecretNumber;
		}

		private void createPlayers() {
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
			String userNumber = null;
			createPlayers();
			secondPlayer.g = this;
			firstPlayer.g = this;
			getSecretNumber(firstPlayer.createSecretNumber());
			do {
				userNumber = secondPlayer.userAnswer();
				countAnimals(userNumber);
				System.out.println("Grade: " + printResult() + " The secret code is " + secretNumber + "\n");
			} while (false);
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
			System.out.println("Congrats! The secret code " + secretNumber + " .");
		}

	}

	public static class Player {

		private Game g;
		private String secretNumber;
		private String firstAttempt;
		private int index = 0;

		public Player(Game g) {
			this.g = g;
			firstAttempt = String.valueOf(System.currentTimeMillis() % (int) Math.pow(10, g.lengthOfSecretNumber));
		}

		private String createSecretNumber() {
			do {
				secretNumber = String.valueOf(System.currentTimeMillis() % (int) Math.pow(10, g.lengthOfSecretNumber));
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

		private String userAnswer() {
			return g.sc.nextLine();
		}
	}
}
