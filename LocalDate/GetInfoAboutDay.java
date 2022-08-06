package LocalDate;

import java.util.Scanner;
import java.time.LocalDate;

/**
 * Getting information on a day 
 * 
 * Write a program that reads a date from
 * the standard input and output the following information on it:
 * 
 * 1) number of the day in the year;
 * 
 * 2) the number of the day in the month.
 * 
 * Report a typo 
 * 
 * Sample Input 1:
 * 
 * 2017-06-03 
 * 
 * Sample Output 1:
 * 
 * 154 3 
 * 
 * Sample Input 2:
 * 
 * 2017-04-28 
 * 
 * Sample Output 2:
 * 
 * 118 28
 * 
 * @author SMD_ASY
 *
 */

public class GetInfoAboutDay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		LocalDate ld = LocalDate.parse(sc.nextLine());
		sc.close();
		System.out.println(ld.getDayOfYear() + " " + ld.getDayOfMonth());
	}

}
