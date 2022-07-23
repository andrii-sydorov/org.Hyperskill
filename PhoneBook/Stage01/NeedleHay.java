package PhoneBook.Stage01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sqlite.SQLiteDataSource;

public class NeedleHay {

	private static Connection con;
	private static String find;
	private static String directory;
	private static String pathDirectory = "./src/PhoneBook/Stage01/directory.txt";
	private static String pathFind = "./src/PhoneBook/Stage01/find.txt";
	private static int toFind;
	private static int isFind;

	/**
	 * Stage 1/4: A needle in the hay
	 * Description
	 * Have you ever had to use one of those 2000+ page phone books full of various
	 * organizations and people's names, written in a small font and with multiple
	 * columns on each page? Finding the information you need in such books can be
	 * an ordeal. In fact, even computers struggle to search through the millions of
	 * entries in a directory.
	 * 
	 * In this project, you will create a phone book, implementing several
	 * algorithms, and comparing their efficiency when using a big dataset.
	 * 
	 * For this, you will need to download the file directory.txt that contains the
	 * phone numbers of over a million people in multiple cities.
	 * 
	 * At this stage, you should implement the simplest possible search to find the
	 * numbers of a few people whose names are listed in the file find.txt.
	 * 
	 * It takes a time to find all the records from the big file. We recommend you
	 * manually test your program with the simplified data: small_directory.txt and
	 * small_find.txt. But to pass all the tests you have to work with the big files
	 * above.
	 * 
	 * Note how long it takes you to do this when using a linear search so that you
	 * can compare results with other search methods.
	 * 
	 * To measure the time difference, you can use System.currentTimeMillis().
	 * 
	 * Also notice that you don't need to read the file "directory.txt" again and
	 * again after each query. You should load all lines into memory and measure
	 * only the search process.
	 * 
	 * Please, do not keep the downloaded files inside your project directory
	 * because the server can reject large files and you will see the message
	 * "Failed to post submission to the Hyperskill".
	 * Example
	 * Below is an example of how your output should look:
	 * 
	 * Start searching...
	 * Found 500 / 500 entries. Time taken: 1 min. 56 sec. 328 ms.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current absolute path is: " + s);

		readDataFromDirectory(pathDirectory);
		readDataFromFind(pathFind);

		long start = System.currentTimeMillis();
		searchLogic(directory, find);
		long stop = System.currentTimeMillis();
		System.out.println("Start searching...");
		long minute = (stop - start) / 60000;
		long seconds = ((stop - start) % 60000) / 1000;
		long miliseconds = (stop - start) % 1000;
		String time = minute + " min. " + seconds + " sec. " + miliseconds + " ms.";
		System.out.printf("Found %d / %d entries. Time taken: %s", isFind, toFind, time);

		// workWithDatabase();
		// workingWithCollections();

	}

	private static void searchLogic(String directory, String find) {
		String[] searchIn = directory.replaceAll("\r", "").split("\n");
		String[] searchFor = find.replaceAll("\r", "").split("\n");
		toFind = searchFor.length;
		for (int i = 0; i < searchFor.length; i++) {
			for (int j = 0; j < searchIn.length; j++) {
				if (searchIn[j].substring(searchIn[j].indexOf(" ") + 1).equals(searchFor[i])) {
					isFind++;
				}
			}
		}
	}

	private static void workingWithCollections() {
		Map<String, String> mapDirectory = makeDirectory();
		List<String> listFind = makeFind();
		searchLogic(mapDirectory, listFind);
	}

	private static void searchLogic(Map<String, String> map, List<String> list) {
		toFind = list.size();
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			if (map.get(list.get(i)) != null) {
				count++;
				break;
			}
		}
		isFind = count;
	}

	private static List<String> makeFind() {
		List<String> ls = new ArrayList<>();
		find = find.replaceAll("\r", "");
		String[] dir = find.split("\n");
		for (int i = 0; i < dir.length; i++) {
			ls.add(dir[i]);
		}
		return ls;
	}

	private static Map<String, String> makeDirectory() {
		Map<String, String> map = new HashMap<>();
		directory = directory.replaceAll("\r", "");
		String[] dir = directory.split("\n");
		for (int i = 0; i < dir.length; i++) {
			map.put(dir[i].substring(dir[i].indexOf(" ") + 1), dir[i].substring(0, dir[i].indexOf(" ")));
		}
		return map;
	}

	private static void workWithDatabase() {
		createConnection();
		createTable();
		fillTable();
	}

	private static void readDataFromDirectory(String pathDirectory) {
		try {
			directory = new String(Files.readAllBytes(Paths.get(pathDirectory)));
		} catch (IOException fnfe) {
			System.out.println("The directory was not loaded");
		}
	}

	private static void readDataFromFind(String pathFind) {
		try {
			find = new String(Files.readAllBytes(Paths.get(pathFind)));
		} catch (IOException fnfe) {
			System.out.println("The find was not loaded");
		}
	}

	private static void fillTable() {
		String toExecute = "INSERT INTO phonebook(number, name)" + "VALUES (?, ?);";
		String delete = "DELETE FROM phonebook;";
		try (PreparedStatement ps = con.prepareStatement(toExecute);
				PreparedStatement del = con.prepareStatement(delete)) {
			del.executeUpdate();

			directory = directory.replaceAll("\r", "");
			String[] dir = directory.split("\n");

			for (int i = 0; i < dir.length; i++) {
				String n = dir[i].substring(0, dir[i].indexOf(" "));
				String p = dir[i].substring(dir[i].indexOf(" ") + 1);

				ps.setString(1, n);
				ps.setString(2, p);
				ps.executeUpdate();

			}
		} catch (SQLException sql) {
			sql.printStackTrace();
			System.out.println("The table was not filled!");
		}
	}

	private static void createConnection() {
		String url = "jdbc:sqlite:./src/PhoneBook/Stage01/phoneData.db";
		SQLiteDataSource ds = new SQLiteDataSource();
		ds.setUrl(url);
		try {
			con = ds.getConnection();
		} catch (SQLException sql) {
			System.out.println("The connection was not established");
		}
	}

	private static void createTable() {
		String createTable = "CREATE TABLE IF NOT EXISTS phonebook(" + "number VARCHAR(40)," + "name VARCHAR(75));";
		try (PreparedStatement ps = con.prepareStatement(createTable)) {
			ps.executeUpdate();
		} catch (SQLException sql) {
			System.out.println("The table was not created");
		}
	}

}
