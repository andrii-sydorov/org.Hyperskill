package Encryption_Decryption.Stage05;

import java.util.Map;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description
 * 
 * At this stage, you need to add the ability to read and write original and
 * cipher data to files. The program must parse two additional arguments -in and
 * -out to specify the full name of a file to read data and to write the result.
 * Arguments -mode, -key, and -data should still work as before.
 * 
 * Your program should read data from -data or from a file written in the -in
 * argument. That's why you can't have both -data and -in arguments
 * simultaneously, only one of them.
 * 
 *  1. If there is no -mode, the program should work in enc mode. 
 *  2. If there is no -key, the program should consider that key = 0. 
 *  3. If there is no -data, and there is no -in the program should assume that the data is an empty string.
 *  4. If there is no -out argument, the program must print data to the standard output. 
 *  5. If there are both -data and -in arguments, your program should prefer -data over -in.
 * 
 * If there is a non-standard situation (an input file does not exist or an
 * argument doesn’t have a value), the program should not fail. Instead, it must
 * display a clear message about the problem and stop successfully. The message
 * should contain the word "Error" in any case. Examples
 * 
 * Example 1
 * 
 * java Main -mode enc -in road_to_treasure.txt -out protected.txt -key 5
 * 
 * This command must get data from the file road_to_treasure.txt, encrypt the
 * data with the key 5, create a file called protected.txt and write ciphertext
 * to it.
 * 
 * Example 2
 * 
 * Input:
 * 
 * java Main -mode enc -key 5 -data "Welcome to hyperskill!"
 * 
 * Output:
 * 
 * \jqhtrj%yt%m~ujwxpnqq&
 * 
 * Example 3
 * 
 * Input:
 * 
 * java Main -key 5 -data "\jqhtrj%yt%m~ujwxpnqq&" -mode dec
 * 
 * Output:
 * 
 * Welcome to hyperskill!
 * 
 * @author SMD_ASY
 *
 */

public class Main {

	private static String encodedMessage;
	private static String decodedMessage;

	public static void main(String[] args) throws myException {
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < args.length; i += 2) {
			map.put(args[i], args[i + 1]);
		}
		String operation = map.containsKey("-mode") ? map.get("-mode") : "-enc";
		String message = map.containsKey("-data") ? map.get("-data")
				: map.containsKey("-in") ? buildDataFromFile(map.get("-in")) : new String();
		int key = map.containsKey("-key") ? Integer.valueOf(map.get("-key")) : 0;

		switch (operation) {
		case "enc":
			encodedMessage = encodeMessage(message, key);
			break;
		case "dec":
			decodedMessage = decodeMessage(message, key);
			break;
		default:
			break;
		}

		String dataToPrint = encodedMessage == null ? decodedMessage : encodedMessage;
		if (map.containsKey("-out")) {
			saveDataToFile(map.get("-out"), dataToPrint);
		} else {
			printData(dataToPrint);
		}

	}

	private static void saveDataToFile(String outputFile, String dataToPrint) {
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
		String ans = null;
		file = ".src/Encryption_Decryption/Stage05/" + file;
		try {
			ans = new String(Files.readAllBytes(Paths.get(file)));
		} catch (IOException ioe) {
			throw new myException("Error");
		}
		return ans == null ? null : ans.trim();
	}

	private static String decodeMessage(String message, int key) {
		if (message == null) {
			return message;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < message.length(); i++) {
			char chTemp = message.charAt(i);
			sb.append(Character.toString(chTemp - key));
		}
		return sb.toString();
	}

	private static String encodeMessage(String message, int key) {
		if (message == null) {
			return message;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < message.length(); i++) {
			char chTemp = message.charAt(i);
			sb.append(Character.toString(chTemp + key));
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
