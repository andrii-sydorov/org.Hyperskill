package NumberBaseConverter.Project.Stage01;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// write your code here
		Scanner sc = new Scanner(System.in);
		int inputData = enterNumberInDecimalFormat(sc);
		int baseData = enterBaseNumber(sc);
		convertDecimal(inputData, baseData);
		sc.close();
	}

	private static int enterNumberInDecimalFormat(Scanner sc) {
		int ans = 0;
		while (true) {
			System.out.println("Enter number in decimal system:");
			try {
				ans = Integer.valueOf(sc.nextLine());
				break;
			} catch (NumberFormatException nfe) {
				System.out.println("Should be a number!");
			}
		}
		return ans;
	}

	private static int enterBaseNumber(Scanner sc) {
		int targetBase = 0;
		while (true) {
			System.out.println("Enter target base:");
			try {
				targetBase = Integer.valueOf(sc.nextLine());
				break;
			} catch (NumberFormatException nfe) {
				System.out.println("The targetbase should be a number!");
			}
		}
		return targetBase;
	}

	private static void convertDecimal(int inputData, int baseData) {
		String ans = "";
		int reminder = 0;
		while (true) {
			reminder = inputData % baseData;
			String rem = stringRepresentationOfReminder(reminder);
			if (inputData < baseData) {
				ans = rem + ans;
				break;
			} else {
				ans = rem + ans;
				inputData /= baseData;
			}
		}
		System.out.println("Conversion result:" + "\n" + ans);
	}

	private static String stringRepresentationOfReminder(int reminder) {
		String rem;
		switch (reminder) {
		case 10:
			rem = "A";
			break;
		case 11:
			rem = "B";
			break;
		case 12:
			rem = "C";
			break;
		case 13:
			rem = "D";
			break;
		case 14:
			rem = "E";
			break;
		case 15:
			rem = "F";
			break;
		default:
			rem = String.valueOf(reminder);
			break;
		}
		return rem;
	}

}
