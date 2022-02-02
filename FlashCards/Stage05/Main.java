package FlashCards.Stage05;

import java.io.BufferedReader;
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

/**
 * Description
 * 
 * Our users cannot create new flashcards all the time. It seems like a good
 * idea to keep old but useful cards in storage so we can use them later. Let's
 * try to do that!
 * 
 * In this stage, you will improve the application's interactivity by having it
 * ask the user for an action and perform it. Also, it will provide additional
 * functionality allowing the user to store flashcards in files for future use.
 * 
 * The program should support the following actions:
 * 
 * add a card: add
 * remove a card: remove
 * load cards from file: import
 * save cards to file: export
 * ask for definitions of some random cards: ask
 * exit the program: exit
 * 
 * You can use any format of separating to store cards in a file; tests do not
 * check the content of the file, but they do check that all the saved
 * flashcards are loaded correctly.
 * 
 * When you load flashcards from a file, you shouldn't erase the cards that
 * aren't in the file. If the imported flashcard already exists, update its
 * definition in the program memory. There won't be any conflict with
 * definitions in the tests.
 * 
 * Objectives
 * 
 * Print the message Input the action (add, remove, import, export, ask, exit):
 * each time the user is prompted for their next action. The action is read from
 * the next line, processed, and the message is output again until the user
 * decides to exit the program.
 * 
 * The program's behavior depends on the action the user inputs:
 * 
 * add — create a new flashcard with a unique term and definition. After adding
 * the card, output the message The pair ("term":"definition") has been added,
 * where "term" is the term entered by the user and "definition" is the
 * definition entered by the user.
 * remove — ask the user for the term of the card they want to remove with the
 * message Which card?, and read the user's input from the next line. If a
 * matching flashcard exists, remove it from the set and output the message The
 * card has been removed.. If there is no such flashcard in the set, output the
 * message Can't remove "card": there is no such card., where "card" is the
 * user's input.
 * import — print the line File name:, read the user's input from the next line,
 * which is the file name, and import all the flashcards written to this file.
 * If there is no file with such name, print the message File not found.. After
 * importing the cards, print the message n cards have been loaded., where n is
 * the number of cards in the file. The imported cards should be added to the
 * ones that already exist in the memory of the program. However, the imported
 * cards have priority: if you import a card with the name that already exists
 * in the memory, the card from the file should overwrite the one in memory.
 * export — request the file name with the line File name: and write all
 * currently available flashcards into this file. Print the message n cards have
 * been saved., where n is the number of cards exported to the file.
 * ask — ask the user about the number of cards they want to be asked about and
 * then prompt them for definitions, like in the previous stage.
 * exit — print Bye bye! and finish the program.
 * 
 * Examples
 * 
 * The symbol > represents the user input. Note that it's not part of the input.
 * 
 * Example 1: the user removes an existing card and tries to remove a
 * non-existent one
 * 
 * Input the action (add, remove, import, export, ask, exit):
 * > add
 * The card:
 * > France
 * The definition of the card:
 * > Paris
 * The pair ("France":"Paris") has been added.
 * 
 * Input the action (add, remove, import, export, ask, exit):
 * > add
 * The card:
 * > France
 * The card "France" already exists.
 * 
 * Input the action (add, remove, import, export, ask, exit):
 * > add
 * The card:
 * > Great Britain
 * The definition of the card:
 * > Paris
 * The definition "Paris" already exists.
 * 
 * Input the action (add, remove, import, export, ask, exit):
 * > remove
 * Which card?
 * > France
 * The card has been removed.
 * 
 * Input the action (add, remove, import, export, ask, exit):
 * > remove
 * Which card?
 * > Wakanda
 * Can't remove "Wakanda": there is no such card.
 * 
 * Input the action (add, remove, import, export, ask, exit):
 * > exit
 * Bye bye!
 * 
 * Example 2: the user uses files to import and export their flashcards;
 * definitions of existing cards are updated after import
 * 
 * Input the action (add, remove, import, export, ask, exit):
 * > import
 * File name:
 * > ghost_file.txt
 * File not found.
 * 
 * Input the action (add, remove, import, export, ask, exit):
 * > add
 * The card:
 * > Japan
 * The definition of the card:
 * > Tokyo
 * The pair ("Japan":"Tokyo") has been added.
 * 
 * Input the action (add, remove, import, export, ask, exit):
 * > add
 * The card:
 * > Russia
 * The definition of the card:
 * > UpdateMeFromFile
 * The pair ("Russia":"UpdateMeFromFile") has been added.
 * 
 * Input the action (add, remove, import, export, ask, exit):
 * > import
 * File name:
 * > capitals.txt
 * 28 cards have been loaded.
 * 
 * Input the action (add, remove, import, export, ask, exit):
 * > ask
 * How many times to ask?
 * > 1
 * Print the definition of "Russia":
 * > Moscow
 * Correct!
 * 
 * Input the action (add, remove, import, export, ask, exit):
 * > export
 * File name:
 * > capitalsNew.txt
 * 29 cards have been saved.
 * 
 * Input the action (add, remove, import, export, ask, exit):
 * > exit
 * Bye bye!
 * 
 * Example 3: the program asks for definitions several times
 * 
 * Input the action (add, remove, import, export, ask, exit):
 * > add
 * The card:
 * > a brother of one's parent
 * The definition of the card:
 * > uncle
 * The pair ("a brother of one's parent":"uncle") has been added.
 * 
 * Input the action (add, remove, import, export, ask, exit):
 * > add
 * The card:
 * > a part of the body where the foot and the leg meet
 * The definition of the card:
 * > ankle
 * The pair ("a part of the body where the foot and the leg meet":"ankle") has
 * been added.
 * 
 * Input the action (add, remove, import, export, ask, exit):
 * > ask
 * How many times to ask?
 * > 6
 * Print the definition of "a brother of one's parent":
 * > ankle
 * Wrong. The right answer is "uncle", but your definition is correct for "a
 * part of the body where the foot and the leg meet".
 * Print the definition of "a part of the body where the foot and the leg meet":
 * > ??
 * Wrong. The right answer is "ankle".
 * Print the definition of "a brother of one's parent":
 * > uncle
 * Correct!
 * Print the definition of "a part of the body where the foot and the leg meet":
 * > ankle
 * Correct!
 * Print the definition of "a brother of one's parent":
 * > ??
 * Wrong. The right answer is "uncle".
 * Print the definition of "a part of the body where the foot and the leg meet":
 * > uncle
 * Wrong. The right answer is "ankle", but your definition is correct for "a
 * brother of one's parent".
 * 
 * Input the action (add, remove, import, export, ask, exit):
 * > exit
 * Bye bye!
 */
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

	private static String[] options = { "add", "remove", "import", "export", "ask", "exit" };

	private Map<String, String> m;
	private Set<Card> c;
	private User us;
	int numberOfCards;
	Scanner sc;

	public Game() {
		c = new LinkedHashSet<>();
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
				case "exit":
					exit = true;
					sayBye();
					break;
			}
			System.out.println();
		}
	}

	public void askCard() {
		// TODO Auto-generated method stub
		System.out.println("How many times to ask?");
		int numberOfAsks = Integer.valueOf(sc.nextLine());
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
			return;
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
