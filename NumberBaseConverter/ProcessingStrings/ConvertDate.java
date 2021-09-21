package NumberBaseConverter.ProcessingStrings;

import java.util.Scanner;

/**
 * Convert a date 
 * Write a program that takes a date string formatted as
 * YYYY-MM-DD as input, then converts and outputs it as MM/DD/YYYY.
 * 
 * For instance, the input 2007-07-21 will result in the following output
 * 07/21/2007.
 * 
 * 
 * Sample Input: 
 * 2012-09-28
 * 
 * Sample Output: 
 * 09/28/2012
 * 
 * @author SMD_ASY
 *
 */

public class ConvertDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String[] data = sc.nextLine().split("-");
		System.out.println(printData(data));
	}

	private static String printData(String[] data) {
		String[] newData = new String[data.length];
		for (int i = 0; i < newData.length; i++) {
			newData[i] = data[(i + 1) % newData.length];
		}
		return String.join("/", newData);
	}

}
