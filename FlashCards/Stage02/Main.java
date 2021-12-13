package FlashCards.Stage02;

import java.util.Scanner;

/**
 * Description Of course, we cannot use flashcards with only one hardcoded card.
 * So let's make our program more dynamic! Let’s create flashcards depending on
 * the user's input and add a primitive guessing mechanism so that the user can
 * check how well they remember the definitions.
 * 
 * In this stage, you need to implement a custom flashcard-creation mechanism
 * which will be extensively used in further steps, and add a mechanism to check
 * the user's answer.
 * 
 * Objectives Your program should read two lines from the console, a term, and a
 * definition, that represent a card.
 * 
 * After that, the user inputs a line as an answer (a definition of the term on
 * the card). Compare the user's answer with the correct definition and print
 * the result.
 * 
 * The output of the program must contain one of two words:
 * 
 *  - wrong if the answer doesn't match the definition; 
 *  - right if the answer matches the definition. 
 *  
 * Of course, at this point, the user is unlikely to get the
 * answer wrong, since they’re the ones who just typed in the answer... But
 * don’t worry: right now we're just warming up so that in later stages we could
 * make this a bit more challenging for our users.
 * 
 * Examples 
 * The greater-than symbol followed by a space (> ) represents the user
 * input. Note that it's not part of the input.
 * 
 * Example 1: the user's answer is correct
 * 
 * Input (a term, then a definition, and, finally, an answer):
 * 
 * > print() 
 * > outputs text 
 * > outputs text Output:
 * 
 * Your answer is right! 
 * 
 * Example 2: the user's answer is incorrect
 * 
 * Input (a term, a definition, an answer):
 * 
 * > Jetbrains 
 * > A place for people who love to code 
 * > A place for people who hate to code 
 * 
 * Output:
 * 
 * Your answer is wrong..
 * 
 * @author SMD_ASY
 *
 */

public class Main {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game g = new Game();
		g.createElements(sc);
		System.out.println(g.compareInfo());
	}

}

class Game {
	private Card c;
	private User us;

	public void createElements(Scanner sc) {
		this.c = new Card("Card", "Definition", sc);
		this.us = new User(sc);
	}

	public String compareInfo() {
		return c.getDefinition().equals(us.getAnswer()) ? "Your answer is right!" : "Your answer is wrong...";
	}
}

class Card {
	private final String frontOfTheCard;
	private String term;
	private final String backOfTheCard;
	private String definition;

	public Card(String frontOfTheCard, String backOfTheCard, Scanner sc) {
		this.frontOfTheCard = frontOfTheCard;
		this.term = sc.nextLine();
		this.backOfTheCard = backOfTheCard;
		this.definition = sc.nextLine();
	}

	public String toString() {
		return new String(
				this.frontOfTheCard + ":\n" + this.term + "\n" + this.backOfTheCard + ":\n" + this.definition);
	}

	public String getDefinition() {
		return this.definition;
	}
}

class User {

	private String answer;

	public User(Scanner sc) {
		this.answer = sc.nextLine();
	}

	public void setAnswer(Scanner sc) {
		answer = sc.nextLine();
	}

	public String getAnswer() {
		return this.answer;
	}
}
