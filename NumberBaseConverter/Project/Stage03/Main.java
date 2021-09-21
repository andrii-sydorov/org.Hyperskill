package NumberBaseConverter.Project.Stage03;

import java.util.Scanner;
import java.math.BigInteger;

/**
 * Description
 * 
 * Ideally, we want to convert numbers in different bases, not only from or to
 * the decimal system. In this stage, we will add support for converting numbers
 * from any given source base to any target base. As there are 26 Latin letters
 * and 10 digits, the maximum base is 26 + 10 = 36. So, the target and source
 * base will be between 2 and 36.
 * 
 * Also, it might be more convenient for our users if the program didn't ask for
 * the base before each conversion and instead remembered the previously input
 * base. This way, the users will have to do much less typing when they need to
 * convert a bunch of numbers from base A to base B.
 * 
 * To convert a number from the source base to the target base, you should first
 * convert it to the decimal system and then convert the decimal number to the
 * target base.
 * 
 * Note that from this stage on, numbers might be larger than you expect, so you
 * should use BigInteger instead of Int or Long to avoid errors. Objectives
 * 
 * Your program should have a two-level menu:
 * 
 * On the first level, the user sees the following prompt: Enter two numbers in
 * format: {source base} {target base} (To quit type /exit). Then, they input
 * two numbers separated by a single space: source base and target base. The
 * user also has the option to use the /exit command to quit the program. On the
 * second level, the user sees another prompt: Enter number in base {user source
 * base} to convert to base {user target base} (To go back type /back), and
 * inputs the number in the source base. The program outputs the message
 * Conversion result: followed by the number in the target base. Then, the
 * program asks for the new number to convert from the previously provided
 * source base to the target base. To change the bases, the user can choose the
 * BACK command and return to the first level menu to make the necessary
 * changes.
 * 
 * Example
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Enter two numbers in format: {source base} {target base} (To quit type /exit)
 * > 10 2 Enter number in base 10 to convert to base 2 (To go back type /back) >
 * 11 Conversion result: 1011
 * 
 * Enter number in base 10 to convert to base 2 (To go back type /back) > 18
 * Conversion result: 10010
 * 
 * Enter number in base 10 to convert to base 2 (To go back type /back) > 127
 * Conversion result: 1111111
 * 
 * Enter number in base 10 to convert to base 2 (To go back type /back) >
 * 189344562689000108753301247 Conversion result:
 * 1001110010011111010001010100111110001111101101011010101101001001111110100010111011111111
 * 
 * Enter number in base 10 to convert to base 2 (To go back type /back) > /back
 * 
 * Enter two numbers in format: {source base} {target base} (To quit type /exit)
 * > 36 10 Enter number in base 36 to convert to base 10 (To go back type /back)
 * > abcde Conversion result: 17325410
 * 
 * Enter number in base 36 to convert to base 10 (To go back type /back) > 13a0
 * Conversion result: 50904
 * 
 * Enter number in base 36 to convert to base 10 (To go back type /back) > az
 * Conversion result: 395
 * 
 * Enter number in base 36 to convert to base 10 (To go back type /back) > /back
 * 
 * Enter two numbers in format: {source base} {target base} (To quit type /exit)
 * > /exit
 * 
 * @author SMD_ASY
 *
 */

public class Main {

	private static String avaliableRange = "0123456789abcdefghijklmnopqrstuvwxyz";
	private static String[] avaliableBase = new String[36];
	private static int sourceBase;
	private static int targetBase;
	private static String numberToConvert;

	static {
		int start = 1;
		int stop = 36;
		for (int i = start; i <= stop; i++) {
			avaliableBase[i - 1] = String.valueOf(i);
		}
	}

	public static void main(String[] args) {
		// write your code here
		Scanner sc = new Scanner(System.in);
		start(sc);
		sc.close();
	}

	private static void start(Scanner sc) {
		getDataFitsrLevel(sc);
	}

	private static void getDataSecondLevel(Scanner sc) {
		printMessageSecondLevel();
		String inputData = sc.nextLine();
		while(!inputData.equals("/back")) {
			if (!checkDataSecondLevel(inputData)) {
				System.out.println("Invalid data input!");
				getDataSecondLevel(sc);
			}
			BigInteger toDecimal = convertToDecimal();
			convertToTargetBase(toDecimal, BigInteger.valueOf(targetBase));
			printMessageSecondLevel();
			inputData = sc.nextLine();
		}
		System.out.println();
		getDataFitsrLevel(sc);
	}

	private static void printMessageSecondLevel() {
		System.out.print("Enter number in base " + sourceBase + " to convert to base " + targetBase
				+ " (To go back type /back) ");
	}

	private static BigInteger convertToDecimal() {
		BigInteger result = BigInteger.ZERO;
		BigInteger toAdd = BigInteger.ONE;
		int j = 0;
		for (int i = numberToConvert.length() - 1; i >= 0; i--) {
			toAdd = BigInteger.valueOf(Character.getNumericValue(numberToConvert.charAt(i)))
					.multiply(BigInteger.valueOf(sourceBase).pow(j));
			result = result.add(toAdd);
			j++;
		}
		return result;
	}

	private static void getDataFitsrLevel(Scanner sc) {
		printMessageFirstLevel();
		String dataFirstLevel = sc.nextLine();
		switch (dataFirstLevel) {
		case "/exit":
			break;
		default:
			if (!checkDataFirstLevel(dataFirstLevel)) {
				System.out.println("Invalid base range, should be from 1 till 36");
				break;
			}
			getDataSecondLevel(sc);
			break;
		}
	}

	private static void printMessageFirstLevel() {
		System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
	}

	private static boolean checkDataFirstLevel(String dataFirstLevel) {
		String[] arDataFirstLevel = dataFirstLevel.split(" ");
		for (int i = 0; i < arDataFirstLevel.length; i++) {
			boolean isFound = false;
			for (int j = 0; j < avaliableBase.length; j++) {
				if (arDataFirstLevel[i].equals(avaliableBase[j])) {
					isFound = true;
					break;
				}
			}
			if (!isFound) {
				return false;
			}

			sourceBase = Integer.valueOf(arDataFirstLevel[0]);
			targetBase = Integer.valueOf(arDataFirstLevel[1]);

		}
		return true;
	}

	private static boolean checkDataSecondLevel(String inputData) {
		String[] dataToCheck = inputData.split("");
		String validRange = avaliableRange.substring(0, sourceBase);
		for (int i = 0; i < dataToCheck.length; i++) {
			if (!validRange.contains(dataToCheck[i])) {
				return false;
			}
		}
		makeDataToConvert(inputData);
		return true;
	}

	private static void makeDataToConvert(String inputData) {
		numberToConvert = inputData;
	}

	private static void convertToTargetBase(BigInteger inputData, BigInteger baseData) {
		String ans = "";
		BigInteger[] reminder;
		while (true) {
			reminder = inputData.divideAndRemainder(baseData);
			String rem = stringRepresentationOfReminder(reminder[1].intValue());
			if (inputData.compareTo(baseData) == -1) {
				ans = rem + ans;
				break;
			} else {
				ans = rem + ans;
				inputData = inputData.divide(baseData);
			}
		}
		System.out.println("Conversion result: " + ans + "\n");
		//System.out.println(ans.equals("1001110010011111010001010100111110001111101101011010101101001001111110100010111011111111"));
	}

	private static String stringRepresentationOfReminder(int reminder) {
		String rem = Character.toString(avaliableRange.charAt(reminder));
		return rem;
	}

}
