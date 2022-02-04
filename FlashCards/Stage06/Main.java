package FlashCards.Stage06;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Game g = new Game();
		g.sc = sc;
		g.mainMenu();
		sc.close();
	}

}

class Game {

	private static String[] options = { "add", "remove", "import", "export", "ask", "exit", "log", "hardest card",
			"reset stats" };

	private Map<String, String> m;
	private Set<Card> c;
	private User us;
	int numberOfCards;
	Scanner sc;
	private List<String> log;

	public Game() {
		c = new LinkedHashSet<>();
		m = new LinkedHashMap<>();
		log = new ArrayList<>();
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
				removeCard();
				break;
			case "import":
				importCard();
				break;
			case "export":
				exportCard();
				break;
			case "ask":
				askCard();
				break;
			case "log":
				showLog();
				break;
			case "hardest card":
				hardestCard();
				break;
			case "reset stats":
				resetStats();
				break;
			case "exit":
				exit = true;
				sayBye();
				break;
			}
			System.out.println();
		}
	}

	public void resetStats() {
		for (Card cd : c) {
			cd.setErrors(0);
		}
		System.out.println("Card statistics have been reset.");
	}

	public void showLog() {
		System.out.println("File name:");
		String line = sc.nextLine();
		try (PrintWriter pr = new PrintWriter(line)) {
			for (String s : log) {
				pr.println(s);
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			return;
		}
		System.out.println("The log has been saved.");
	}

	public void hardestCard() {
		int numberOfMaxErrors = Integer.MIN_VALUE;
		List<String> lsToPrint = new ArrayList<>();
		for (Card cd : c) {
			if (cd.getErrors() > numberOfMaxErrors) {
				numberOfMaxErrors = cd.getErrors();
				lsToPrint.clear();
				lsToPrint.add(cd.getTerm());
			} else if (cd.getErrors() == numberOfMaxErrors) {
				lsToPrint.add(cd.getTerm());
			}
		}
		if (numberOfMaxErrors == 0) {
			System.out.println("There are no cards with errors.");
		} else {
			String res = lsToPrint.size() == 1
					? "The hardest card is " + terms(lsToPrint) + " . You have " + numberOfMaxErrors
							+ " errors answering it."
					: "The hardest card are " + terms(lsToPrint) + " . You have " + numberOfMaxErrors
							+ "  errors answering them.";
			System.out.println(res);
		}
	}

	public String terms(List<String> ls) {
		String[] ar = new String[ls.size()];
		for (int i = 0; i < ls.size(); i++) {
			ar[i] = "\"" + ls.get(i) + "\"";
		}
		return String.join(", ", ar);
	}

	public void askCard() {
		System.out.println("How many times to ask?");
		int numberOfAsks = Integer.valueOf(sc.nextLine());
		log.add(String.valueOf(numberOfAsks));
		Collection<String> col = m.values();
		List<String> ls = new ArrayList<>(m.keySet());
		if (ls.size() == 0) {
			return;
		}
		for (int i = 0; i < numberOfAsks; i++) {
			String term = ls.get(i % ls.size());
			System.out.println("Print the definition of \"" + term + "\":");
			us.setAnswer(sc);
			String definition = us.getAnswer();
			log.add(definition);
			if (definition.equals(m.get(term))) {
				System.out.println("Correct!");
				continue;
			} else if (col.contains(definition)) {
				System.out.println("Wrong. The right answer is \"" + m.get(term)
						+ "\", but your definition is correct for \"" + findKey(m, definition) + "\".");
			} else {
				System.out.println("Wrong. The right answer is \"" + m.get(term) + "\"");
			}
			incrementError(c, term);
		}
	}

	public void exportCard() {
		System.out.println("File name:");
		String fileName = sc.nextLine();
		log.add(fileName);
		try (PrintWriter writer = new PrintWriter(fileName);) {
			for (Card cd : c) {
				writer.println(cd.getTerm());
				writer.println(cd.getDefinition());
				writer.println(cd.getErrors());
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		}
		System.out.println(c.size() + " cards have been saved.");
	}

	public void addCard() {
		String term = null;
		String definition = null;

		System.out.println("Card:");
		term = sc.nextLine();
		log.add(term);
		if (m.containsKey(term)) {
			System.out.println("The card " + "\"" + term + "\"" + " already exists.");
			return;
		}

		System.out.println("The definition of the card:");
		definition = sc.nextLine();
		log.add(definition);
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
		log.add(term);
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
		log.add(file);
		List<String> ls = readFileAsString(file);
		if (ls.size() == 0) {
			return;
		}
		int count = 0;
		if (ls.size() % 3 == 1) {
			ls.remove(ls.size() - 1);
		}
		if (ls.size() % 3 == 2) {
			for (int i = 0; i < 2; i++) {
				ls.remove(ls.size() - 1);
			}
		}
		for (int i = 0; i < ls.size(); i += 3) {
			String term = ls.get(i);
			String definition = ls.get(i + 1);
			int error = Integer.valueOf(i + 2);
			c.removeIf(s -> s.getTerm().equals(term));
			m.put(term, definition);
			Card cd = new Card(term, definition);
			cd.setErrors(error);
			c.add(cd);
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

	public void createUser() {
		us = new User();
	}

	private String findKey(Map<String, String> m, String definition) {
		for (Map.Entry<String, String> entr : m.entrySet()) {
			if (entr.getValue().equals(definition)) {
				return entr.getKey();
			}
		}
		return null;
	}

	private void incrementError(Set<Card> c, String term) {
		for (Card cd : c) {
			if (cd.getTerm().equals(term)) {
				cd.incErrors();
			}
		}
	}

	public String compareInfo(Card cd, User us) {
		return cd.getDefinition().equals(us.getAnswer()) ? "Correct!"
				: "Wrong. The right answer is " + "\"" + cd.getDefinition() + "\".";
	}
}

class Card {

	private String term;
	private String definition;
	private int errors;

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

	public void incErrors() {
		this.errors++;
	}

	public int getErrors() {
		return errors;
	}

	public void setErrors(int errors) {
		this.errors = errors;
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
