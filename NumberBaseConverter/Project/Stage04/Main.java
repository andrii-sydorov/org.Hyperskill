package NumberBaseConverter.Project.Stage04;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Description
 * 
 * @author SMD_ASY
 *
 */

public class Main {

	private static String avaliableRange = "0123456789abcdefghijklmnopqrstuvwxyz";
	private static String[] avaliableBase = new String[36];
	private static int sourceBase;
	private static int targetBase;
	private static String intPart;
	private static String fractPart;

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
		while (!inputData.equals("/back")) {
			if (!checkDataSecondLevel(inputData)) {
				System.out.println("Invalid data input!");
				getDataSecondLevel(sc);
			}
			BigInteger DecimalIntPart = convertToDecimalIntPart();
			BigDecimal DecimalFractPart = convertToDecimalFractPart();
			convertToTargetBaseIntPart(DecimalIntPart, BigInteger.valueOf(targetBase));
			convertToTargetBaseFractPart(DecimalFractPart, BigDecimal.valueOf(targetBase));
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

	private static BigInteger convertToDecimalIntPart() {
		BigInteger result = BigInteger.ZERO;
		BigInteger toAdd = BigInteger.ONE;
		int j = 0;
		for (int i = intPart.length() - 1; i >= 0; i--) {
			toAdd = BigInteger.valueOf(Character.getNumericValue(intPart.charAt(i)))
					.multiply(BigInteger.valueOf(sourceBase).pow(j));
			result = result.add(toAdd);
			j++;
		}
		return result;
	}
	
	private static BigDecimal convertToDecimalFractPart() {
		BigDecimal result = BigDecimal.ZERO;
		BigDecimal toAdd = BigDecimal.ONE;
		int j = 1;
		for (int i = 0; i < fractPart.length(); i++) {
			toAdd = BigDecimal.valueOf(Character.getNumericValue(fractPart.charAt(i)))
					.divide(BigDecimal.valueOf(sourceBase).pow(j));
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
		String[] dataToCheck = inputData.split("\\.");
		String validRange = avaliableRange.substring(0, sourceBase);
		for (int i = 0; i < dataToCheck.length; i++) {
			for (int j = 0; j < dataToCheck[i].length(); j++) {
				if (!validRange.contains(Character.toString(dataToCheck[i].charAt(j)))) {
					return false;
				}
			}
		}
		makeDataToConvert(dataToCheck);
		return true;
	}

	private static void makeDataToConvert(String[] inputData) {
		intPart = inputData[0];
		fractPart = inputData[1];
	}

	private static void convertToTargetBaseIntPart(BigInteger inputData, BigInteger baseData) {
		String intPart = "";
		BigInteger[] reminder;
		while (true) {
			reminder = inputData.divideAndRemainder(baseData);
			String rem = stringRepresentationOfReminder(reminder[1].intValue());
			if (inputData.compareTo(baseData) == -1) {
				intPart = rem + intPart;
				break;
			} else {
				intPart = rem + intPart;
				inputData = inputData.divide(baseData);
			}
		}
		System.out.println("Conversion result of int part: " + intPart + "\n");
		// System.out.println(ans.equals("1001110010011111010001010100111110001111101101011010101101001001111110100010111011111111"));
	}
	
	private static void convertToTargetBaseFractPart(BigDecimal inputData, BigDecimal baseData) {
		String fractPart = "";
		BigDecimal rest = BigDecimal.ZERO;
		BigDecimal limit = baseData.subtract(BigDecimal.ONE);
		while (rest.compareTo(limit) != 0) {
			rest = inputData.multiply(baseData);
			fractPart += rest.intValue();
			if (rest.compareTo(limit) == 1) {
				rest = rest.subtract(limit);
			}
		}
		System.out.println("Conversion result of fractional part: " + fractPart + "\n");
		// System.out.println(ans.equals("1001110010011111010001010100111110001111101101011010101101001001111110100010111011111111"));
	}

	private static String stringRepresentationOfReminder(int reminder) {
		String rem = Character.toString(avaliableRange.charAt(reminder));
		return rem;
	}

}
