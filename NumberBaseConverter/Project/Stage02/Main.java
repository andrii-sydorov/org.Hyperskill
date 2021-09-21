package NumberBaseConverter.Project.Stage02;

import java.util.Scanner;

/**
 * Description
 * 
 * At this point, the user needs to restart the program each time after
 * converting just one number, which is very inconvenient. Let's fix that and
 * make your program prompt the user for more numbers until the user is ready to
 * quit.
 * 
 * Also, we will add the reverse conversion: from binary, octal, and hexadecimal
 * systems to decimal. Converting from octal, hexadecimal, or any other system
 * to decimal works almost the same way as with binary numbers, only the base is
 * different. See for yourself:
 * 
 * 1726_8=1∗512+7∗64+2∗8+6∗1=982_10​
 * 
 * A3C_16=10∗256+3∗16+12∗1=2620_10
 * 
 * Objectives
 * 
 * Your program should output the prompt Do you want to convert /from decimal or
 * /to decimal? (To quit type /exit) to prompt the user for their next move. The
 * possible commands are /from, /to, and /exit.
 * 
 * If the user types /from, the program should behave as in the previous stage
 * and convert the user's number from the decimal system to binary, octal, or
 * hexadecimal. If the user types /to, the program should: Print the prompt
 * Enter source number: and read the user input number to be converted to
 * decimal. Print the prompt Enter source base: and read the target base (2, 8,
 * or 16). Output the message Conversion to decimal result: followed by the
 * number's representation in the decimal system. If the user types /exit, the
 * program stops. Otherwise, it should process the command and prompt for the
 * next one.
 * 
 * Examples
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1:
 * 
 * Do you want to convert /from decimal or /to decimal? (To quit type /exit) >
 * /from Enter a number in decimal system: > 8 Enter the target base: > 16
 * Conversion result: 8
 * 
 * Do you want to convert /from decimal or /to decimal? (To quit type /exit) >
 * /from Enter a number in decimal system: > 101 Enter the target base: > 2
 * Conversion result: 1100101
 * 
 * Do you want to convert /from decimal or /to decimal? (To quit type /exit) >
 * /from Enter a number in decimal system: > 103 Enter the target base: > 8
 * Conversion result: 147
 * 
 * Do you want to convert /from decimal or /to decimal? (To quit type /exit) >
 * /exit
 * 
 * Example 2:
 * 
 * Do you want to convert /from decimal or /to decimal? (To quit type /exit) >
 * /to Enter source number: > ff Enter source base: > 16 Conversion to decimal
 * result: 255
 * 
 * Do you want to convert /from decimal or /to decimal? (To quit type /exit) >
 * /to Enter source number: > 71 Enter source base: > 8 Conversion to decimal
 * result: 57
 * 
 * Do you want to convert /from decimal or /to decimal? (To quit type /exit) >
 * /to Enter source number: > 111001 Enter source base: > 2 Conversion to
 * decimal result: 57
 * 
 * Do you want to convert /from decimal or /to decimal? (To quit type /exit) >
 * /exit
 * 
 * Example 3:
 * 
 * Do you want to convert /from decimal or /to decimal? (To quit type /exit) >
 * /to Enter source number: > 25a Enter source base: > 16 Conversion to decimal
 * result: 602
 * 
 * Do you want to convert /from decimal or /to decimal? (To quit type /exit) >
 * /from Enter number in decimal system: > 602 Enter target base: > 16
 * Conversion result: 25a
 * 
 * Do you want to convert /from decimal or /to decimal? (To quit type /exit) >
 * /exit
 * 
 * @author SMD_ASY
 *
 */

public class Main {

	private static RegularWords command;
	private static String sourceData;

	public static void main(String[] args) {
		// write your code here
		Scanner sc = new Scanner(System.in);
		while (command != RegularWords.Exit) {
			command = dataInput(sc);
			chooseAction(command, sc);
			System.out.println();
		}
		sc.close();
	}

	private static void chooseAction(RegularWords command, Scanner sc) {
		switch (command) {
		case Exit:
			break;
		case From:
			int decimalNumber = getDecimalNumber(sc);
			int targetBase = getBaseNumber(sc);
			convertDecimal(decimalNumber, targetBase);
			break;
		case To:
			String sourceNumber = null;
			int sourceBase = 0;
			while(true) {
			sourceNumber = getSourceNumber(sc);
			sourceBase = getSourceBase(sc);
			if (checkInputData(sourceNumber, sourceBase)) {
				break;
			}
			}
			System.out.println("Conversion to decimal result: " + Integer.parseInt(sourceNumber, sourceBase));
			break;
		}
	}
	
	private static boolean checkInputData(String sourceNumber, int sourceBase) {
		switch(sourceBase) {
		case 2:
			return checkBinaryNumber(sourceNumber);
		case 8:
			return checkOctalNumber(sourceNumber);
		case 16:
			return checkHexadimalNumber(sourceNumber);
		}
		return true;
	}
	
	private static boolean checkBinaryNumber(String sourceNumber) {
		String[] inputData = sourceNumber.split("");
		int n = 2;
		for (int i = 0; i < inputData.length; i++) {
			if (Integer.valueOf(inputData[i]) >= n) {
				return false;
			}
		}
		sourceData = "0b" + sourceNumber;
		return true;
	}

	private static boolean checkHexadimalNumber(String sourceNumber) {
		String hexadimal = "0123456789abcdef";
		String[] inputData = sourceNumber.split("");
		for (int i = 0; i < inputData.length; i++) {
			if (hexadimal.indexOf(inputData[i]) < 0) {
				return false;
			}
		}
		sourceData = "0x" + sourceNumber;
		return true;
	}
	
	private static boolean checkOctalNumber(String sourceNumber) {
		String[] inputData = sourceNumber.split("");
		int n = 8;
		for (int i = 0; i < inputData.length; i++) {
			if (Integer.valueOf(inputData[i]) >= n) {
				return false;
			}
		}
		sourceData = "0" + sourceNumber;
		return true;
	}

	private static String getSourceNumber(Scanner sc) {
		System.out.print("Enter source number: ");
		return sc.nextLine();
	}
	
	private static int getSourceBase(Scanner sc) {
		int sourceBase = 0;
		while(true) {
			System.out.print("Enter source base: ");
			try {
				sourceBase = Integer.valueOf(sc.nextLine());
				return sourceBase;
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid data format");
			}
		}
	}

	private static int getBaseNumber(Scanner sc) {
		int baseNumber = 0;
		while (true) {
			System.out.print("Enter the target base: ");
			String data = sc.nextLine();
			try {
				baseNumber = Integer.valueOf(data);
				break;
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid data input!");
			}
		}
		return baseNumber;
	}

	private static int getDecimalNumber(Scanner sc) {
		int decimalNumber = 0;
		while (true) {
			System.out.print("Enter a number in decimal system: ");
			String data = sc.nextLine();
			try {
				decimalNumber = Integer.valueOf(data);
				break;
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid data input!");
			}
		}
		return decimalNumber;
	}

	private static RegularWords dataInput(Scanner sc) {
		while (true) {
			System.out.print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ");
			String data = sc.nextLine();
			RegularWords[] command = RegularWords.values();
			for (RegularWords rw : command) {
				if (data.equals(rw.getWords())) {
					return rw;
				}
			}
		}
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
		System.out.println("Conversion result: " + ans);
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

enum RegularWords {
	From("/from"), To("/to"), Exit("/exit");

	private String st;

	RegularWords(String regel) {
		st = regel;
	}

	public String getWords() {
		return this.st;
	}

}
