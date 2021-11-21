package ReadabilityScore.Stage03;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.text.NumberFormat;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String data = readAllLines(args[0]);
		printText(data);
		printSettings(data);
		data = readAllLines(args[1]);
		printText(data);
		printSettings(data);
	}

	private static void printText(String data) {
		System.out.println("The text is:");
		System.out.println(data + "\n");
	}

	private static void printSettings(String data) {
		String[] sentences = data.split("[\\.?!]");
		String[] words = data.replaceAll("[\\W]", " ").split("\\s+");
		String characters = data.replaceAll("\\n", "").replaceAll("\\t", "").replaceAll(" ", "");
		System.out.println("Words " + words.length);
		System.out.println("Sentences: " + sentences.length);
		System.out.println("Characters: " + characters.length());
		double score = 4.71 * (characters.length() * 1.0 / words.length) + 0.5 * (words.length / sentences.length)
				- 21.43;
		NumberFormat nfe = NumberFormat.getInstance();
		nfe.setMaximumFractionDigits(2);
		int n = (int) Math.round(score);
		System.out.println("The score is: " + nfe.format(score));
		System.out.println("This text should be understood by " + getAgesFromTable(n) + "-year-olds.");
	}

	private static String getAgesFromTable(int n) {
		String[] gradeLavel = { "5-6", "6-7", "7-8", "8-9", "9-10", "10-11", "11-12", "12-13", "13-14", "14-15",
				"15-16", "16-17", "17-18", "18-22" };
		int score = 0;
		switch (n) {
		case 1:
			score = 0;
			break;
		case 2:
			score = 1;
			break;
		case 3:
			score = 2;
			break;
		case 4:
			score = 3;
			break;
		case 5:
			score = 4;
			break;
		case 6:
			score = 5;
			break;
		case 7:
			score = 6;
			break;
		case 8:
			score = 7;
			break;
		case 9:
			score = 8;
			break;
		case 10:
			score = 9;
			break;
		case 11:
			score = 10;
			break;
		case 12:
			score = 11;
			break;
		case 13:
			score = 12;
			break;
		case 14:
			score = 13;
			break;
		default:
			break;
		}
		return gradeLavel[score + 1];
	}

	private static String readAllLines(String fileName) throws IOException {
		return new String(Files.readAllBytes(Paths.get(fileName)));
	}

}
