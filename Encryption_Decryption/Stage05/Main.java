package Encryption_Decryption.Stage05;

import java.util.Map;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

	private static String encodedMessage;
	private static String decodedMessage;
	private String[] avaliableOptions = { "-mode", "-key", "-in", "-out", "-data" };

	public static void main(String[] args) {
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
				System.out.println(encodedMessage);
				break;
			case "dec":
				decodedMessage = decodeMessage(message, key);
				System.out.println(decodedMessage);
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

	private static String buildDataFromFile(String file) {
		String ans = null;
		try {
			ans = new String(Files.readAllBytes(Paths.get(file)));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return ans;
	}

	private static String decodeMessage(String message, int key) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < message.length(); i++) {
			char chTemp = message.charAt(i);
			sb.append(Character.toString(chTemp - key));
		}
		return sb.toString();
	}

	private static String encodeMessage(String message, int key) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < message.length(); i++) {
			char chTemp = message.charAt(i);
			sb.append(Character.toString(chTemp + key));
		}
		return sb.toString();
	}

}
