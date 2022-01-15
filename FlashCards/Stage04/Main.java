package FlashCards.Stage04;

import java.util.Scanner;
import java.util.Map;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.HashSet;

/**
 * Description
 * 
 * While learning new things, we may mix things up and use the right definition
 * for the wrong term. Let's inform our players if they enter the definition
 * that is wrong for the requested flashcard but correct for another flashcard
 * in our set.
 * 
 * Also, it might be very confusing if our flashcard set contains cards with the
 * same term or definition, since seeing only one side of the card we can't tell
 * them apart. Let's add a constraint: the user must add only unique terms and
 * definitions. To do this, you need to find a way to check whether the set
 * contains a particular term or definition and get the term by the definition,
 * and vice versa.
 * 
 * These two features will definitely improve our game! Objectives
 * 
 * Modify your program to behave the following way:
 * 
 * When the user tries to add a duplicate term, forbid it and output the message
 * The term "term" already exists. Try again: using the term instead of "term".
 * Ask for the term until the user inputs a unique term. When the user tries to
 * add a duplicate definition, forbid it and Output the message The definition
 * "definition" already exists. Try again: with the definition instead of
 * "definition". Ask the player to input the definition until the user inputs a
 * unique one. When the user enters the wrong definition for the requested term,
 * but this definition is correct for another term, print the appropriate
 * message Wrong. The right answer is "correct answer", but your definition is
 * correct for "term for user's answer". , where "correct answer" is the actual
 * definition for the requested term, and "term for user's answer" is the
 * appropriate term for the user-entered definition.
 * 
 * Examples
 * 
 * The symbol > represents the user input. Note that it's not part of the input.
 * 
 * Example 1: the user tries to add duplicated term and definition
 * 
 * Input the number of cards: 
 * > 2 
 * Card #1: 
 * > print() 
 * The definition for card #1:
 * > outputs text 
 * Card #2: 
 * > print() 
 * The term "print()" already exists. Try again: 
 * > str() 
 * The definition for card #2: 
 * > outputs text 
 * The definition "outputs text" already exists. Try again: 
 * > converts to a string 
 * Print the definition of "print()": 
 * > outputs text Correct! 
 * Print the definition of "str()": 
 * > converts to a string Correct!
 * 
 * Example 2: the user gives a correct definition for a term that exists, but
 * which is not the term that the program is asking about
 * 
 * Input the number of cards: 
 * > 2 
 * Card #1: 
 * > uncle 
 * The definition for card #1: 
 * > a brother of one's parent 
 * Card #2: > ankle 
 * The definition for card #2: 
 * > a part of the body where the foot and the leg meet 
 * Print the definition of "uncle": 
 * > a part of the body where the foot and the leg meet 
 * Wrong. The right answer is "a brother of one's parent", but your definition is correct
 * for "ankle". 
 * Print the definition of "ankle": 
 * > ??? 
 * Wrong. The right answer is "a part of the body where the foot and the leg meet"
 * 
 * @author SMD_ASY
 *
 */

public class Main {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game g = new Game();
		g.sc = sc;
		g.setSizeofCards();
		g.createCards();
		g.createUser();
		g.askningUser();
		sc.close();
	}

}

class Game {

	private Map<String, String> m;
	private Set<Card> c;
	private User us;
	int numberOfCards;
	Scanner sc;

	public Game() {
		c = new HashSet<>();
		m = new LinkedHashMap<>();
	}

	public void createCards() {
		String term = null;
		String definition = null;
		for (int i = 0; i < numberOfCards;) {
			System.out.println("Card #" + (i + 1) + ":");
			while (true) {
				term = sc.nextLine();
				if (m.containsKey(term)) {
					System.out.println("The term \"" + term + "\" already exists. Try again:");
					continue;
				}
				break;
			}
			System.out.println("The definition for " + "card #" + (i + 1) + ":");
			while (true) {
				definition = sc.nextLine();
				if (m.values().contains(definition)) {
					System.out.println("The definition \"" + definition + "\" already exists. Try again:");
					continue;
				}
				break;
			}
			m.put(term, definition);
			c.add(new Card(term, definition));
			i++;
		}
	}

	public void createUser() {
		us = new User();
	}

	public void setSizeofCards() {
		System.out.println("Input the number of cards:");
		numberOfCards = 0;
		try {
			numberOfCards = Integer.valueOf(sc.nextLine());
		} catch (NumberFormatException nfe) {
			System.out.println("Incorrect number of cards!");
		}
	}

	public void askningUser() {
		Collection<String> col = m.values();
		for (String term : m.keySet()) {
			System.out.println("Print the definition of \"" + term + "\":");
			us.setAnswer(sc);
			String definition = us.getAnswer();
			if (definition.equals(m.get(term))) {
				System.out.println("Correct!");
			} else if (col.contains(definition)) {
				System.out.println("Wrong. The right answer is \"" + m.get(term)
						+ "\", but your definition is correct for \"" + findKey(m, definition) + "\".");
			} else {
				System.out.println("Wrong. The right answer is \"" + m.get(term) + "\"");
			}
		}

	}

	private String findKey(Map<String, String> m, String definition) {
		for (Map.Entry<String, String> entr : m.entrySet()) {
			if (entr.getValue().equals(definition)) {
				return entr.getKey();
			}
		}
		return null;
	}

	public String compareInfo(Card cd, User us) {
		return cd.getDefinition().equals(us.getAnswer()) ? "Correct!"
				: "Wrong. The right answer is " + "\"" + cd.getDefinition() + "\".";
	}
}

class Card {

	private String term;
	private String definition;

	public Card(String term, String definition) {
		this.term = term;
		this.definition = definition;
	}

	public String getDefinition() {
		return this.definition;
	}

	public String getTerm() {
		return this.term;
	}

}

class User {

	private String answer;

	public void setAnswer(Scanner sc) {
		answer = sc.nextLine();
	}

	public String getAnswer() {
		return this.answer;
	}
}
