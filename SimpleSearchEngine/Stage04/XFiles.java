package SimpleSearchEngine.Stage04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class XFiles {

	public static final String NAMES = "Kristofer Galley\n" + "Fernando Marbury fernando_marbury@gmail.com\n"
			+ "Kristyn Nix nix-kris@gmail.com\n" + "Regenia Enderle\n" + "Malena Gray\n" + "Colette Mattei\n"
			+ "Wendolyn Mcphillips\n" + "Jim Gray\n" + "Coreen Beckham\n" + "Bob Yeh bobyeah@gmail.com\n"
			+ "Shannan Strope stropeshah@gmail.com\n" + "Yer Fillion\n" + "Margene Resendez marres@gmail.com\n"
			+ "Blossom Ambler\n" + "Teri Ledet teri_ledet@gmail.com\n" + "Dana Baron baron@gmail.com\n"
			+ "Abram Goldsberry\n" + "Yer Leopold\n" + "Stefania Trunzo\n" + "Alexis Leopold\n" + "Carlene Pompa\n"
			+ "Oliver Dacruz\n" + "Jonie Richter\n" + "Pasquale Gallien gallien@evilcorp.com\n" + "Verdie Gentle\n"
			+ "Gerardo Strouth gallien@evilcorp.com\n" + "Agripina Dones\n" + "Latricia Niebuhr\n" + "Malena Schommer\n"
			+ "Drema Leopold\n" + "Heide Payeur\n" + "Ranae Digiovanni\n" + "Simona Pereira\n" + "Nick Digiovanni\n"
			+ "Angelita Wigington gallien@evilcorp.com\n" + "Elin Gray\n" + "Dwain Trunzo\n" + "Boris Beiler\n"
			+ "Remi Malek fsociefy@gmail.com\n" + "Demetria Hostetler gallien@evilcorp.com\n" + "Nydia Mcduffie\n"
			+ "Florencio Defibaugh\n" + "Warner Giblin\n" + "May Mans\n" + "Shu Gray\n" + "Kaycee Gray\n"
			+ "Victorina Froehlich victory@gmail.com\n" + "Roseanne Gray\n" + "Erica Radford hisam@gmail.com\n"
			+ "Elyse Pauling";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < args.length; i += 2) {
			map.put(args[i], args[i + 1]);
		}
		String name = map.get("--data");
		String[] data = buildData(name).split("\r\n");
		buildMenu(sc, data);
		sc.close();
	}

	private static void printMenu() {
		System.out.println("=== Menu ===");
		for (Options op : Options.values()) {
			System.out.println(op);
		}
	}

	private static void buildMenu(Scanner sc, String[] data) {
		boolean isRunning = true;
		while (isRunning) {
			printMenu();
			int userChoice = Integer.valueOf(sc.nextLine());
			System.out.println();
			switch (userChoice) {
			case 0:
				System.out.println("Bye!");
				isRunning = false;
				break;
			case 1:
				findPerson(sc, data);
				break;
			case 2:
				printPeople(data);
				break;
			default:
				System.out.println("Incorrect option! Try again.");
				break;
			}
			System.out.println();
		}
	}

	private static void printPeople(String[] data) {
		System.out.println("=== List of people ===");
		for (String s : data) {
			System.out.println(s);
		}
	}

	private static void findPerson(Scanner sc, String[] data) {
		System.out.println("Enter a name or email to search all suitable people.");
		String name = sc.nextLine();
		searchEngine(data, name);
	}

	private static void searchEngine(String[] data, String name) {
		List<String> found = new ArrayList<>();
		for (int j = 0; j < data.length; j++) {
			if (data[j].toLowerCase().contains(name.toLowerCase())) {
				found.add(data[j]);
			}
		}
		if (found.isEmpty()) {
			System.out.println("No matching people found.");
		} else {
			printResult(found);
			//found.clear();
		}
		String[] reference = NAMES.split("\n");
		String[] idealSearchResult = Arrays.stream(reference)
				.filter(line -> line.toLowerCase().contains(name.toLowerCase().trim())).toArray(String[]::new);
		
		List<String> ans = Arrays.asList(idealSearchResult);
		
		//ans.forEach(x -> System.out.println(x));
		
		System.out.println(ans.size() == found.size());
		
//		for (int i = 0; i< ans.size(); i++) {
//			System.out.println(ans.get(i).equals(found.get(i)));
//		}

		System.out.println(Objects.equals(found, ans));
	}

	private static void printResult(List<String> found) {
		// System.out.println("Found people:");
		for (String s : found) {
			System.out.println(s);
		}
	}

	private static String buildData(String name) {
		String ans = null;
		try {
			ans = new String(Files.readAllBytes(Paths.get(name)));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return ans;
	}
}

enum Options {

	SEARCH("Find a person", 1), PRINT("Print all data.", 2), EXIT("Exit.", 0);

	private String message;
	private int index;

	Options(String message, int index) {
		this.message = message;
		this.index = index;
	}

	public String toString() {
		return this.index + ". " + this.message;
	}

	public int getIndex() {
		return this.index;
	}

}
