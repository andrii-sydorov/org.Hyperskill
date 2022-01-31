package FlashCards.Stage05;

import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game g = new Game();
		g.sc = sc;
		g.mainMenu();
		sc.close();
	}

}

class Game {

	private static String[] options = { "add", "remove", "import", "export", "ask", "exit" };

	private Map<String, String> m;
	private Set<Card> c;
	private User us;
	int numberOfCards;
	Scanner sc;

	public Game() {
		c = new HashSet<>();
		m = new LinkedHashMap<>();
	}

	public void printAvaliableOptions() {
		System.out.println("Input the action (" + Arrays.toString(options).replace("[", "").replace("]", "") + "):");
	}

	public void mainMenu() {
		boolean exit = false;
		while (!exit) {
			printAvaliableOptions();
			String option = sc.nextLine();
			switch (option) {
			case "add":
				addCard();
				break;
			case "remove":
				// TODO
				removeCard();
				break;
			case "import":
				// TODO
				importCard();
				break;
			case "export":
				// TODO
				// exportCard();
				break;
			case "ask":
				// TODO
				// askUser();
				break;
			case "exit":
				// TODO
				exit = true;
				sayBye();
				break;
			}
		}
	}

	public void addCard() {
		String term = null;
		String definition = null;
		System.out.println("Card:");
		while (true) {
			term = sc.nextLine();
			if (m.containsKey(term)) {
				return;
			}
			break;
		}
		System.out.println("The definition of the card:");
		while (true) {
			definition = sc.nextLine();
			if (m.values().contains(definition)) {
				return;
			}
			break;
		}
		m.put(term, definition);
		c.add(new Card(term, definition));
		System.out.println("The pair (\"" + term + "\"" + ":" + "\"" + definition + "\")" + "has been added.");
	}

	public void removeCard() {
		System.out.println("Which card?");
		String term = sc.nextLine();
		if (m.containsKey(term)) {
			m.remove(term);
			System.out.println("The card has been removed.");
		} else {
			System.out.println("Can't remove \"" + term + "\"" + ": there is no such card.");
		}
	}

	public void importCard() {
		System.out.println("File name: ");
		String file = sc.nextLine();
		List<String> ls = readFileAsString(file);
		int count = ls.size() / 2;
		for (int i = 0, j = 0; i < count; i++) {
			String term = ls.get(j);
			String definition = ls.get(j + 1);
			c.add(new Card(term, definition));
			j++;
		}
		System.out.println(count + " cards have been loaded.");
	}

	public static List<String> readFileAsString(String fileName) {
		List<String> ls = new ArrayList<>();
		
		try (BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
			while (true) {
				String line = bf.readLine();
				if (line == null || line.isBlank()) {
					break;
				}
				ls.add(line);
			}
		} catch (IOException ioe) {
			System.out.println("File not found.");
		} 
		return ls;
	}

	public void sayBye() {
		System.out.println("Bye bye!");
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
