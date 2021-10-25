package BullsAndCows.Stage01;

/**
 * Description
 * 
 * Let's start by reminding ourselves how the game goes. There are two players:
 * the first writes a secret 4-digit code using different digits, and the second
 * has to guess it. At each turn, the second player writes a 4-digit number that
 * they think might be the correct answer. Then, the first player grades that
 * answer using bulls and cows as a notation. If a digit in the given answer
 * matches a digit and its position in the code, it's called a "bull." If a
 * given digit appears in the code but its position doesn't match, then it's
 * called a "cow." The first player reveals how many bulls and cows there are.
 * The information is general; in other words, it isn't bound to any particular
 * digit. For example:
 * 
 * The code is 4931. 
 * The answer is 1234. 
 * The grade is 1 bull and 2 cows.
 * 
 * Here, 3 is a bull, 1 and 4 are cows. If all the digits are bulls, the secret
 * code has been guessed and the game ends. If not, the game continues and the
 * second player tries again.
 * 
 * This may sound rather complicated, but don't worry, we will take it very
 * gradually. In this stage, you will not even have to implement the gameplay:
 * all you need to do now is output the text that could be in the game. In other
 * words, you don't have to worry about handling any requests or processing
 * data: your goal is to display an example text of the game. 
 * 
 * Objectives
 * 
 * In this stage, your program should:
 * 
 * Print a predefined game log that contains at least two turns. The output to
 * be graded is a 4-digit code consisting of unique digits. The last line of
 * your output contains a secret number.
 * 
 * Examples
 * 
 * Example 1
 * 
 * The secret code is prepared: ****.
 * 
 * Turn 1. Answer: 
 * 1234 
 * Grade: 1 cow.
 * 
 * Turn 2. Answer: 
 * 5678 
 * Grade: 1 cow.
 * 
 * Turn 3. Answer: 
 * 9012 
 * Grade: 1 bull and 1 cow.
 * 
 * Turn 4. Answer: 
 * 9087 
 * Grade: 1 bull and 1 cow.
 * 
 * Turn 5. Answer: 
 * 1087 
 * Grade: 1 cow.
 * 
 * Turn 6. Answer: 
 * 9205 
 * Grade: 3 bulls.
 * 
 * Turn 7. Answer: 
 * 9305 
 * Grade: 4 bulls. 
 * 
 * Congrats! The secret code is 9305.
 * 
 * Example 2
 * 
 * The secret code is prepared: ****.
 * 
 * Turn 1. Answer: 
 * 1234 
 * Grade: None.
 * 
 * Turn 2. Answer: 
 * 9876 
 * Grade: 4 bulls. 
 * 
 * Congrats! The secret code is 9876.
 * 
 * @author SMD_ASY
 *
 */

public class Main {

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
			int index = 1;
			String userNumber = null;
			createPlayers();
			secondPlayer.g = this;
			firstPlayer.g = this;
			getSecretNumber(firstPlayer.createSecretNumber());
			do {
				System.out.println("Turn " + index + ". Answer:");
				userNumber = index == 1 ? secondPlayer.firstAttempt : secondPlayer.possibleVariants();
				System.out.println(userNumber);
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
	}
}
