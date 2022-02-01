package FlashCards.Stage05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.PrintWriter;

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
		createUser();
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
				exportCard();
				break;
			case "ask":
				// TODO
				askCard();
				break;
			case "exit":
				// TODO
				exit = true;
				sayBye();
				break;
			}
			System.out.println();
		}
	}

	private void askCard() {
		// TODO Auto-generated method stub
		System.out.println("How many times to ask?");
		int numberOfAsks = Integer.valueOf(sc.nextLine());
		Collection<String> col = m.values();
		for (String term : m.keySet()) {
			if (numberOfAsks == 0) {
				break;
			}
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
			numberOfAsks--;
		}
	}

	public void exportCard() {
		System.out.println("File name:");
		String fileName = sc.nextLine();
		try (PrintWriter writer = new PrintWriter(fileName);) {
			for (Card cd : c) {
				writer.println(cd.getTerm());
				writer.println(cd.getDefinition());
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		System.out.println(c.size() + " cards have been saved.");
	}

	public void addCard() {
		String term = null;
		String definition = null;

		System.out.println("Card:");
		term = sc.nextLine();
		if (m.containsKey(term)) {
			System.out.println("The card " + "\"" + term + "\"" + " already exists.");
			return;
		}

		System.out.println("The definition of the card:");
		definition = sc.nextLine();
		if (m.values().contains(definition)) {
			System.out.println("The definition " + "\"" + definition + "\"" + " already exists.");
			return;
		}
		m.put(term, definition);
		c.add(new Card(term, definition));
		System.out.println("The pair (\"" + term + "\"" + ":" + "\"" + definition + "\")" + " has been added.");
	}

	public void removeCard() {
		System.out.println("Which card?");
		String term = sc.nextLine();
		if (m.containsKey(term)) {
			m.remove(term);
			c.removeIf(s -> s.getTerm().equals(term));
			System.out.println("The card has been removed.");
		} else {
			System.out.println("Can't remove \"" + term + "\"" + ": there is no such card.");
		}
		printCard();
	}

	public void printCard() {
		for (Card cd : c) {
			System.out.println("Card: " + cd.getTerm() + " " + cd.getDefinition());
		}
	}

	public void importCard() {
		System.out.println("File name: ");
		String file = sc.nextLine();
		List<String> ls = readFileAsString(file);
		if (ls.size() == 0) {
			return;
		}
		int count = 0;
		if (ls.size() % 2 != 0) {
			ls.remove(ls.size() - 1);
		}
		for (int i = 0; i < ls.size(); i += 2) {
			String term = ls.get(i);
			String definition = ls.get(i + 1);
			c.removeIf(s -> s.getTerm().equals(term));
			m.put(term, definition);
			c.add(new Card(term, definition));
			count++;
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

//	public void createCards() {
//		String term = null;
//		String definition = null;
//		for (int i = 0; i < numberOfCards;) {
//			System.out.println("Card #" + (i + 1) + ":");
//			while (true) {
//				term = sc.nextLine();
//				if (m.containsKey(term)) {
//					System.out.println("The term \"" + term + "\" already exists. Try again:");
//					continue;
//				}
//				break;
//			}
//			System.out.println("The definition for " + "card #" + (i + 1) + ":");
//			while (true) {
//				definition = sc.nextLine();
//				if (m.values().contains(definition)) {
//					System.out.println("The definition \"" + definition + "\" already exists. Try again:");
//					continue;
//				}
//				break;
//			}
//			m.put(term, definition);
//			c.add(new Card(term, definition));
//			i++;
//		}
//	}

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

	public void askingUser() {
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
