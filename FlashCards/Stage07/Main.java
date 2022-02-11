package FlashCards.Stage07;

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
import java.util.HashMap;

/**
 * Description
 * 
 * Files are used to save progress and restore it the next time the user runs
 * the program. It's tedious to print the actions manually. Sometimes you can
 * just forget to do it! Let's add run arguments that define which file to read
 * at the start and which file to save at the exit. 
 * 
 * Objectives
 * 
 * When provided with command-line arguments, your program should do the
 * following:
 * 
 * If -import IMPORT is passed, where IMPORT is the file name, read the initial
 * card set from the external file and print the message n cards have been
 * loaded. as the first line of the output, where n is the number of cards
 * loaded from the external file. If such an argument is not provided, the set
 * of cards should initially be empty and no message about card loading should
 * be output. 
 * If -export EXPORT is passed, where EXPORT is the file name, write
 * all cards that are in the program memory into this file after the user has
 * entered exit, and the last line of the output should be n cards have been
 * saved., where n is the number of flashcards in the set.
 * 
 * Run arguments examples
 * 
 * java Flashcards -import derivatives.txt
 * 
 * java Flashcards -export animals.txt
 * 
 * java Flashcards -import words13june.txt -export words14june.txt
 * 
 * java Flashcards -export vocab.txt -import vocab.txt
 * 
 * @author SMD_ASY
 *
 */

public class Main {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Map<String, String> commandLineArguments = buildMap(args);
		Game g = new Game(commandLineArguments);
		g.sc = sc;
		g.mainMenu();
		sc.close();
	}

	public static Map<String, String> buildMap(String[] args) {
		if (args.length % 2 != 0) {
			System.out.println("Invalid argumetns length");
			return null;
		}
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < args.length; i += 2) {
			map.put(args[i], args[i + 1]);
		}
		return map;
	}

}

class Game {

	private static String[] options = { "add", "remove", "import", "export", "ask", "exit", "log", "hardest card",
			"reset stats" };

	private String pathToImport;
	private String pathToExport;
	private String[] settingsOptions = { "-import", "-export" };
	private String[] pathToFile = new String[settingsOptions.length];
	private Map<String, String> map;
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

	public Game(Map<String, String> commands) {
		this();
		map = commands;
	}

	public void printAvaliableOptions() {
		String line = "Input the action (" + Arrays.toString(options).replace("[", "").replace("]", "") + "):";
		log.add(line);
		System.out.println(line);
	}

	public void mainMenu() {
		additionalOptions();
		pathToImport = pathToFile[0];
		pathToExport = pathToFile[1];
		if (pathToImport != null) {
			importCard(pathToImport);
		}
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
				importCard(null);
				break;
			case "export":
				exportCard(null);
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
				if (pathToExport != null) {
					exportCard(pathToExport);
				}
				break;
			}
			String emptyLine = "\n";
			log.add(emptyLine);
			System.out.println();
		}
	}

	private void additionalOptions() {
		if (map.size() == 0) {
			return;
		}
		for (Map.Entry<String, String> entr : map.entrySet()) {
			for (int i = 0; i < settingsOptions.length; i++) {
				if (entr.getKey().equals(settingsOptions[i])) {
					pathToFile[i] = map.get(settingsOptions[i]);
					break;
				}
			}
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
	
	/**
	 * Difference from previous version, now it's possible upload data during and after programm execution.
	 * In case after, if command line arguments contain key-word and path, to file, where data should be stored.
	 * @param fileName It goes from command line arguments and from console input. If command line doesn't contain 
	 * key-word, fileName equals null.
	 */

	public void exportCard(String fileName) {
		if (fileName == null || fileName.length() == 0) {
			String pathToExport = "File name:";
			log.add(pathToExport);
			System.out.println(pathToExport);
			fileName = sc.nextLine();
			log.add(fileName);
		}
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
	
	/**
	 * Difference before previous variant, import the datas now is possible before asking the next
	 * steps, if programm contains additional arguments. In function is two workflow, that depends on
	 * input String data.
	 * @param file before execution, the path to file, from which we want to download data.
	 */
	public void importCard(String file) {
		if (file == null || file.length() == 0) {
			String fileToImport = "File name: ";
			log.add(fileToImport);
			System.out.println(fileToImport);
			file = sc.nextLine();
			log.add(file);
		}
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
