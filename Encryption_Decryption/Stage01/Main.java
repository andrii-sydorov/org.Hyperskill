package Encryption_Decryption.Stage01;

/**
 * Motivation
 * 
 * Today, encryption and decryption algorithms are used everywhere on the
 * Internet to protect our data. This is especially important for sites that
 * handle sensitive data, such as e-commerce sites that accept online card
 * payments and login areas that require users to enter their credentials. To
 * ensure data security, there are complex encryption algorithms behind the
 * scenes.
 * 
 * In this project, you will learn how to encrypt and decrypt messages and texts
 * using simple algorithms. We should note that such algorithms are not suitable
 * for industrial use because they can easily be cracked, but these algorithms
 * demonstrate some general ideas about encryption. Description
 * 
 * For the first stage, you need to manually encrypt the message "we found a
 * treasure!" and print only the ciphertext (in lower case).
 * 
 * To encrypt the message, replace each letter with the letter that is in the
 * corresponding position from the end of the English alphabet (a→z, b→y, c→x,
 * ... x→c, y →b, z→a). Do not replace spaces or the exclamation mark.
 * 
 * Use the given template to your program to print the ciphertext instead of the
 * original message.
 * 
 * The output should look like ## ##### # ########! where # is a lower-case
 * English letter.
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
		final String message = "we found a treasure!";
		encodedMessage = decodeMessage(message);
		System.out.println(encodedMessage);
	}

	private static String decodeMessage(String message) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < message.length(); i++) {
			char chTemp = message.charAt(i);
			if (Character.isAlphabetic(chTemp)) {
				int index = (int) Math
						.abs(sbCharacters.indexOf(Character.toString(chTemp)) - sbCharacters.length() + 1);
				sb.append(Character.toString(sbCharacters.charAt(index)));
			} else {
				sb.append(Character.toString(message.charAt(i)));
			}
		}
		return sb.toString();
	}

}
