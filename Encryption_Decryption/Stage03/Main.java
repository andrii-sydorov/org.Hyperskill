package Encryption_Decryption.Stage03;

import java.util.Scanner;

/**
 * Description
 * 
 * In this stage, you need to support decryption in your program. The decryption
 * is simply the inverse of encryption, following the same steps but reversing
 * the order in which the keys are applied.
 * 
 * Write a program that reads three lines from the standard input: a target
 * operation (enc for encryption, dec for decryption), a message or a
 * cyphertext, and a key to encrypt/decrypt messages. All non-letter characters
 * should be encrypted as well as regular letters. We recommend you to get an
 * integer representation of each character (according to the Unicode table) to
 * shift it.
 * 
 * Decompose your program using methods to make it easy to understand and edit
 * later. One method should encrypt a message and another one should decrypt it
 * according to a key. 
 * 
 * Encryption input example
 * enc 
 * Welcome to hyperskill! 
 * 5
 * 
 * Encryption output example
 * \jqhtrj%yt%m~ujwxpnqq&
 * 
 * Decryption input example
 * dec 
 * \jqhtrj%yt%m~ujwxpnqq& 
 * 5
 * 
 * Decryption output example
 * Welcome to hyperskill!
 * 
 * @author SMD_ASY
 *
 */

public class Main {

	private static StringBuilder sbCharacters = new StringBuilder();
	private static String encodedMessage;
	private static String decodedMessage;
	private static String[] steps = new String[3];

	static {
		for (char c = 'a'; c <= 'z'; c++) {
			sbCharacters.append(Character.toString(c));
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int n = 3;
		for (int i = 0; i < n; i++) {
			steps[i] = sc.nextLine();
		}
		String operation = steps[0];
		String message = steps[1];
		int key = Integer.valueOf(steps[2]);
		sc.close();
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
