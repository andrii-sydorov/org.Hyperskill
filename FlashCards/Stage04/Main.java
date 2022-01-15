package FlashCards.Stage04;

import java.util.Scanner;
import java.util.Map;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/**
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
		m = new HashMap<>();
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
			String definition = sc.nextLine();
			if (definition.equals(m.get(term))) {
				System.out.println("Correct!");
			} else if (col.contains(definition)) {
				System.out.println("Wrong. The right answer is \"" + m.get(term)
						+ "\", but your definition is correct for \"" + findKey(m, definition) + "\"");
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

class Term {

	private String term;

	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	@Override
	public int hashCode() {
		int sum = 0;
		for (int i = 0; i < this.term.length(); i++) {
			sum += Character.getNumericValue(this.term.charAt(i));
		}
		return sum;
	}

	@Override
	public boolean equals(Object a) {
		if (this == a) {
			return true;
		}
		if (a instanceof Term) {
			String s = ((Term) a).getTerm();
			if (s.equals(this.term)) {
				return true;
			}
		}
		return false;
	}

}

class Definition {

	private String definition;

	public String getDefinition() {
		return this.definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	@Override
	public int hashCode() {
		int sum = 0;
		for (int i = 0; i < this.definition.length(); i++) {
			sum += Character.getNumericValue(this.definition.charAt(i));
		}
		return sum;
	}

	@Override
	public boolean equals(Object a) {
		if (this == a) {
			return true;
		}
		if (a instanceof Term) {
			String s = ((Term) a).getTerm();
			if (s.equals(this.definition)) {
				return true;
			}
		}
		return false;
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
