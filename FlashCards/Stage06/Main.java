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

/**
 * Description

While studying, it may be very helpful to pay more attention to challenging parts where you make the most mistakes. In this stage, you will add some statistics features to your program so that the users can track their progress.

Implement the following additional actions:

- save the application log to the given file: log
- print the term or terms that the user makes most mistakes with: hardest card
- erase the mistake count for all cards: reset stats

Remember that now you need to store three items (term, definition, mistakes) instead of two (term, definition).
Objectives

Print the message Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats): each time the user is prompted for their next action. The action is read from the next line, processed, and the message is output again until the user decides to exit the program.

The program's behavior depends on the user's input action:

- log — ask the user where to save the log with the message File name:, save all the lines that have been input/output to the console to the file, and print the message The log has been saved. Don't clear the log after saving it to the file.
- hardest card — print a string that contains the term of the card with the highest number of wrong answers, for example, The hardest card is "term". You have N errors answering it. If there are several cards with the highest number of wrong answers, print all of the terms: The hardest cards are "term_1", "term_2". If there are no cards with errors in the user's answers, print the message There are no cards with errors.
- reset stats — set the count of mistakes to 0 for all the cards and output the message Card statistics have been reset.

Update your import and export actions from the previous stage, so that the error count for each flashcard is also imported and exported.
Example

The symbol > represents the user input. Note that it's not part of the input.

Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> hardest card
There are no cards with errors.

Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> import
File name:
> capitals.txt
28 cards have been loaded.

Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> hardest card
The hardest card is "France". You have 10 errors answering it.

Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> ask
How many times to ask?
> 1
Print the definition of "Russia":
> Paris
Wrong. The right answer is "Moscow", but your definition is correct for "France" card.

Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> hardest card
The hardest cards are "Russia", "France". You have 10 errors answering them.

Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> reset stats
Card statistics have been reset.

Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> hardest card
There are no cards with errors.

Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> log
File name:
> todayLog.txt
The log has been saved.

Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> exit
Bye bye!
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
		String line = "Input the action (" + Arrays.toString(options).replace("[", "").replace("]", "") + "):";
		log.add(line);
		System.out.println(line);
	}

	public void mainMenu() {
		boolean exit = false;
		while (!exit) {
			printAvaliableOptions();
			String option = sc.nextLine();
			log.add(option);
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
			String emptyLine = "\n";
			log.add(emptyLine);
			System.out.println();
		}
	}

	public void resetStats() {
		for (Card cd : c) {
			cd.setErrors(0);
		}
		String warReseted = "Card statistics have been reset.";
		log.add(warReseted);
		System.out.println(warReseted);
	}

	public void showLog() {
		String logMessage = "File name:";
		log.add(logMessage);
		System.out.println(logMessage);
		String fileToLog = sc.nextLine();
		log.add(fileToLog);
		try (PrintWriter pr = new PrintWriter(fileToLog)) {
			for (String s : log) {
				pr.println(s);
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			return;
		}
		String logResult = "The log has been saved.";
		log.add(logResult);
		System.out.println(logResult);
	}

	public void hardestCard() {
		int numberOfMaxErrors = 0;
		List<String> lsToPrint = new ArrayList<>();
		String hardestCardResult = null;
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
			hardestCardResult = "There are no cards with errors.";
		} else {
			hardestCardResult = lsToPrint.size() == 1
					? "The hardest card is " + terms(lsToPrint) + ". You have " + numberOfMaxErrors
							+ " errors answering it."
					: "The hardest cards are " + terms(lsToPrint) + ". You have " + numberOfMaxErrors
							+ "  errors answering them.";
		}
		System.out.println(hardestCardResult);
		log.add(hardestCardResult);
	}

	public String terms(List<String> ls) {
		String[] ar = new String[ls.size()];
		for (int i = 0; i < ls.size(); i++) {
			ar[i] = "\"" + ls.get(i) + "\"";
		}
		return String.join(", ", ar);
	}

	public void askCard() {
		String timeToAsk = "How many times to ask?";
		log.add(timeToAsk);
		System.out.println(timeToAsk);
		int numberOfAsks = Integer.valueOf(sc.nextLine());
		log.add(String.valueOf(numberOfAsks));
		Collection<String> col = m.values();
		List<String> ls = new ArrayList<>(m.keySet());
		if (ls.size() == 0) {
			return;
		}
		for (int i = 0; i < numberOfAsks; i++) {
			String term = ls.get(i % ls.size());
			String asking = "Print the definition of \"" + term + "\":";
			log.add(asking);
			System.out.println(asking);
			us.setAnswer(sc);
			String definition = us.getAnswer();
			log.add(definition);
			String resulToAsk = null;
			if (definition.equals(m.get(term))) {
				resulToAsk = "Correct!";
				log.add(resulToAsk);
				System.out.println(resulToAsk);
				continue;
			} else if (col.contains(definition)) {
				resulToAsk = "Wrong. The right answer is \"" + m.get(term) + "\", but your definition is correct for \""
						+ findKey(m, definition) + "\".";
			} else {
				resulToAsk = "Wrong. The right answer is \"" + m.get(term) + "\"";

			}
			log.add(resulToAsk);
			System.out.println(resulToAsk);
			incrementError(c, term);
		}
	}

	public void exportCard() {
		String pathToExport = "File name:";
		log.add(pathToExport);
		System.out.println(pathToExport);
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
		String resultExportCard = c.size() + " cards have been saved.";
		log.add(resultExportCard);
		System.out.println(resultExportCard);
	}

	public void addCard() {
		String term = null;
		String definition = null;
		String printAddcard = "Card:";
		log.add(printAddcard);
		System.out.println(printAddcard);
		term = sc.nextLine();
		log.add(term);
		if (m.containsKey(term)) {
			String hasKey = "The card " + "\"" + term + "\"" + " already exists.";
			log.add(hasKey);
			System.out.println(hasKey);
			return;
		}
		String askDefinition = "The definition of the card:";
		log.add(askDefinition);
		System.out.println(askDefinition);
		definition = sc.nextLine();
		log.add(definition);
		if (m.values().contains(definition)) {
			String hasValue = "The definition " + "\"" + definition + "\"" + " already exists.";
			log.add(hasValue);
			System.out.println(hasValue);
			return;
		}
		m.put(term, definition);
		c.add(new Card(term, definition));
		String resultAddCard = "The pair (\"" + term + "\"" + ":" + "\"" + definition + "\")" + " has been added.";
		log.add(resultAddCard);
		System.out.println(resultAddCard);
	}

	public void removeCard() {
		String cardToRemove = "Which card?";
		log.add(cardToRemove);
		System.out.println(cardToRemove);
		String term = sc.nextLine();
		log.add(term);
		String resultRemoveCard = null;
		if (m.containsKey(term)) {
			m.remove(term);
			c.removeIf(s -> s.getTerm().equals(term));
			resultRemoveCard = "The card has been removed.";

		} else {
			resultRemoveCard = "Can't remove \"" + term + "\"" + ": there is no such card.";
		}
		log.add(resultRemoveCard);
		System.out.println(resultRemoveCard);
		printCard();
	}

	public void printCard() {
		for (Card cd : c) {
			String toPrint = "Card: " + cd.getTerm() + " " + cd.getDefinition();
			log.add(toPrint);
			System.out.println(toPrint);
		}
	}

	public void importCard() {
		String fileToImport = "File name: ";
		log.add(fileToImport);
		System.out.println(fileToImport);
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
			int error = Integer.valueOf(ls.get(i + 2));
			c.removeIf(s -> s.getTerm().equals(term));
			m.put(term, definition);
			Card cd = new Card(term, definition);
			cd.setErrors(error);
			c.add(cd);
			count++;
		}
		String resultImportCard = count + " cards have been loaded.";
		log.add(resultImportCard);
		System.out.println(resultImportCard);
	}

	public List<String> readFileAsString(String fileName) {
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
			String error = "File not found.";
			log.add(error);
			System.out.println(error);
		}
		return ls;
	}

	public void sayBye() {
		String bye = "Bye bye!";
		log.add(bye);
		System.out.println(bye);
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
