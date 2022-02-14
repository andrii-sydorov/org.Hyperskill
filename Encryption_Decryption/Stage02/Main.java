package Encryption_Decryption.Stage02;

import java.util.Scanner;

/**
 * The English alphabet is below:
 * 
 * abcdefghijklmnopqrstuvwxyz
 * 
 * The program should not modify non-letter characters.
 * 
 * The key is assumed to mean that if a person knows the value of the key, they
 * will be able to decrypt the text, and if they do not know, they will not be
 * able to decrypt the text. It's like a real key that can open up access to the
 * message text.
 * 
 * Note, key is just a special parameter that controls the behavior of our
 * encryption algorithm. See the picture below for more information.
 * 
 *   						  key
 * 						       |
 * 	Original text -> Encryption Algorithm -> Cyphertext
 * 
 * Input example
 * 
 * welcome to hyperskill 
 * 5
 * 
 * Output example
 * 
 * bjqhtrj yt mdujwxpnqq
 * 
 * The encryption process
 * 
 * @author SMD_ASY
 *
 */

public class Main {

	private static StringBuilder sbCharacters = new StringBuilder();
	private static String encodedMessage;

	static {
		for (char c = 'a'; c <= 'z'; c++) {
			sbCharacters.append(Character.toString(c));
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String message = sc.nextLine();
		int key = Integer.valueOf(sc.nextLine());
		sc.close();
		encodedMessage = decodeMessage(message, key);
		System.out.println(encodedMessage);
	}

	private static String decodeMessage(String message, int key) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < message.length(); i++) {
			char chTemp = message.charAt(i);
			if (Character.isAlphabetic(chTemp)) {
				int index = (int) Math
						.abs((sbCharacters.indexOf(Character.toString(chTemp)) + key) % sbCharacters.length());
				sb.append(Character.toString(sbCharacters.charAt(index)));
			} else {
				sb.append(Character.toString(message.charAt(i)));
			}
		}
		return sb.toString();
	}

}
