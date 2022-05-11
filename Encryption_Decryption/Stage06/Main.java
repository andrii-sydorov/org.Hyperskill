package Encryption_Decryption.Stage06;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Choices, choices
 * 
 * Description
 * 
 * Extend your program by adding different algorithms to encode/decode data. The
 * first one would be shifting algorithm (it shifts each letter by the specified
 * number according to its order in the alphabet in circle). The second one
 * would be based on Unicode table, like in the previous stage.
 * 
 * When starting the program, the necessary algorithm should be specified by an
 * argument (-alg). The first algorithm should be named shift, the second one
 * should be named unicode. If there is no -alg you should default it to shift.
 * 
 * Remember that in case of shift algorithm you need to encode only English
 * letters (from 'a' to 'z' as the first circle and from 'A' to 'Z' as the
 * second circle i.e. after 'z' comes 'a' and after 'Z' comes 'A').
 * 
 * To complete this stage, we recommend that you create a set of classes and
 * interfaces for encryption and decryption algorithms. Examples
 * 
 * Example 1
 * 
 * java Main -mode enc -in road_to_treasure.txt -out protected.txt -key 5 -alg
 * unicode
 * 
 * This command must get data from the file road_to_treasure.txt, encrypt the
 * data with the key 5, create a file called protected.txt and write ciphertext
 * to it.
 * 
 * Example 2
 * 
 * Input:
 * 
 * java Main -mode enc -key 5 -data "Welcome to hyperskill!" -alg unicode
 * 
 * Output:
 * 
 * \jqhtrj%yt%m~ujwxpnqq&
 * 
 * Example 3
 * 
 * Input:
 * 
 * java Main -key 5 -alg unicode -data "\jqhtrj%yt%m~ujwxpnqq&" -mode dec
 * 
 * Output:
 * 
 * Welcome to hyperskill!
 * 
 * Example 4:
 * 
 * Input:
 * 
 * java Main -key 5 -alg shift -data "Welcome to hyperskill!" -mode enc
 * 
 * Output:
 * 
 * Bjqhtrj yt mdujwxpnqq!
 * 
 * Example 5:
 * 
 * Input:
 * 
 * java Main -key 5 -alg shift -data "Bjqhtrj yt mdujwxpnqq!" -mode dec
 * 
 * Output:
 * 
 * Welcome to hyperskill!
 * 
 * @author SMD_ASY
 *
 */

public class Main {

	private static String dataToPrint;
	private final static StringBuilder sbLetters = new StringBuilder();

	static {
		for (char c = 'a'; c <= 'z'; c++) {
			sbLetters.append(c);
		}
		for (char c = 'A'; c <= 'Z'; c++) {
			sbLetters.append(c);
		}
	}

	public static void main(String[] args) throws myException {

		// java Main -key 5 -alg shift -data "Welcome to hyperskill!" -mode enc
		// java Main -key 5 -alg shift -data "Bjqhtrj yt mdujwxpnqq!" -mode dec
		// -mode enc -key 5 -in input.txt -alg shift -out output.txt
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < args.length; i += 2) {
			map.put(args[i], args[i + 1]);
		}
		String operation = map.getOrDefault("-mode", "-enc");
		String message = map.containsKey("-data") ? map.get("-data")
				: map.containsKey("-in") ? buildDataFromFile(map.get("-in")) : new String();
		int key = map.containsKey("-key") ? Integer.valueOf(map.get("-key")) : 0;
		String alg = map.getOrDefault("-alg", "shift");

		buildDataToPrint(message, alg, operation, key);

		if (map.containsKey("-out")) {
			saveDataToFile(map.get("-out"), dataToPrint);
		} else {
			printData(dataToPrint);
		}

	}

	private static void buildDataToPrint(String message, String alg, String operation, int key) {
		if (message == null || message.length() == 0) {
			dataToPrint = message;
		}
		switch (operation) {
		case "enc":
			dataToPrint = encodeMessage(message, alg, key);
			break;
		case "dec":
			dataToPrint = decodeMessage(message, alg, key);
			break;
		default:
			break;
		}
	}

	private static void saveDataToFile(String outputFile, String dataToPrint) {
		System.out.println(outputFile);
		System.out.println(dataToPrint);
		outputFile = "./src/Encryption_Decryption/Stage06/" + outputFile;
		try (PrintWriter pr = new PrintWriter(outputFile)) {
			pr.println(dataToPrint);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static void printData(String dataToPrint) {
		System.out.println(dataToPrint);
	}

	private static String buildDataFromFile(String file) throws myException {
		String ans;
		file = "./src/Encryption_Decryption/Stage06/" + file;
		/*
		 * only for test with read/write data
		 */
		try {
			ans = new String(Files.readAllBytes(Paths.get(file)));
		} catch (IOException ioe) {
			System.out.println(ioe.getCause());
			throw new myException("Error");
		}
		System.out.println(ans);
		return ans;
	}

	private static String decodeMessage(String message, String alg, int key) {
		String ans = null;
		switch (alg) {
		case "unicode":
			ans = decodeMessageUnicode(message, key);
			break;
		case "shift":
			ans = decodeMessageShift(message, key);
			break;
		}
		return ans;
	}

	private static String decodeMessageUnicode(String message, int key) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < message.length(); i++) {
			char chTemp = message.charAt(i);
			sb.append(Character.toString(chTemp - key));
		}
		return sb.toString();
	}

	private static String decodeMessageShift(String message, int key) {
		StringBuilder sb = new StringBuilder();
		int middle = sbLetters.length() / 2;
		for (int i = 0; i < message.length(); i++) {
			if (!Character.isAlphabetic(message.charAt(i))) {
				sb.append(message.charAt(i));
				continue;
			}
			int index = sbLetters.indexOf(Character.toString(message.charAt(i)));
			if (index <= middle) {
				index = index - key < 0 ? index - key + middle : index - key;
			} else {
				index = Math.abs(index - key);
				index = index < middle ? index + middle : index;
			}
			sb.append(sbLetters.charAt(index));
		}
		return sb.toString();
	}

	private static String encodeMessage(String message, String alg, int key) {
		String ans = null;
		switch (alg) {
		case "unicode":
			ans = encodeMessageUnicode(message, key);
			break;
		case "shift":
			ans = encodeMessageShift(message, key);
			break;
		default:
			break;
		}
		return ans;
	}

	private static String encodeMessageUnicode(String message, int key) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < message.length(); i++) {
			char chTemp = message.charAt(i);
			sb.append(Character.toString(chTemp + key));
		}
		return sb.toString();
	}

	private static String encodeMessageShift(String message, int key) {
		StringBuilder sb = new StringBuilder();
		int middle = sbLetters.length() / 2;
		for (int i = 0; i < message.length(); i++) {
			if (!Character.isAlphabetic(message.charAt(i))) {
				sb.append(message.charAt(i));
				continue;
			}
			int index = sbLetters.indexOf(Character.toString(message.charAt(i)));
			if (index < middle) {
				index = (index + key) % middle;
			} else {
				index += key;
				index = index % middle + middle;
			}
			sb.append(sbLetters.charAt(index));
		}
		return sb.toString();
	}

}

class myException extends IOException {

	private static final long serialVersionUID = 1L;
	String message;

	myException(String message) {
		super(message);
		this.message = message;
	}
}
