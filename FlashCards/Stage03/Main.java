package FlashCards.Stage03;

import java.util.Scanner;

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
