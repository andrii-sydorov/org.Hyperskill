package BullsAndCows.Stage03;

import java.util.Scanner;

/**
 * Description
 * 
 * Using a predefined secret code doesn't make a fun game. Let's implement the
 * ability to generate a pseudo-random secret number of a given length. If the
 * length is greater than 10, print a warning message and do not generate a
 * number.
 * 
 * We suggest you use the following algorithm to generate the numbers:
 * 
 * long pseudoRandomNumber = System.nanoTime();
 * 
 * This code saves the nanoseconds elapsed since a certain time to the
 * pseudoRandomNumber variable. We can assume that this is a random number. You
 * can generate a secret code by iterating over the pseudoRandomNumber in the
 * reverse order and adding unique digits. If the pseudoRandomNumber lacks the
 * required number of unique digits, call System.nanoTime() again and try to
 * generate the secret code again until you get a satisfactory result.
 * 
 * You can use the Character.getNumericValue(char a) method to get an integer
 * representation of a number!
 * 
 * Objective
 * 
 * In this stage, your program should generate a pseudo-random number of a given
 * length with unique digits and print it. If the length is greater than 10, the
 * program should print a message containing the word Error. The secret code may
 * contain any digits from 0 to 9 but only once. The secret code shouldn't start
 * with a digit 0: for the first digit of the secret code, use digits from 1 to
 * 9.
 * 
 * Don't delete your previous work, just move your code to a separate method.
 * You will need it in the future stages.
 * 
 * Examples
 * 
 * The greater-than symbol followed by a space > represents the user input. Note
 * that it's not part of the input.
 * 
 * Example 1
 * 
 * > 5 
 * The random secret number is 48379.
 * 
 * Example 2
 * 
 * > 4 
 * The random secret number is 5213.
 * 
 * Example 3
 * 
 * > 11 
 * Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.
 * 
 * @author ASY
 *
 */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game g = new Game();
		g.setLengthOfSecretNumber();
		g.startGame();
		g.printSecretNumber();
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

		public Game() {

		}

		private void printSecretNumber() {
			System.out.println("The random secret number is " + secretNumber);
		}

		private void setLengthOfSecretNumber() {
			do {
				try {
					this.lengthOfSecretNumber = sc.nextInt();
				} catch (NumberFormatException nfe) {
					System.out.println(nfe.getMessage());
					continue;
				}
				if (this.lengthOfSecretNumber > 10) {
					System.out.println("Error: can't generate a secret number with a length of " + lengthOfSecretNumber
							+ " because there aren't enough unique digits.");
					break;
				}
				break;
			} while (true);
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
//			do {
//				userNumber = secondPlayer.userAnswer();
//				countAnimals(userNumber);
//				System.out.println("Grade: " + printResult() + " The secret code is " + secretNumber + "\n");
//			} while (false);
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
			firstAttempt = String.valueOf(System.nanoTime() % (int) Math.pow(10, g.lengthOfSecretNumber));
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
