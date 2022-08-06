package LocalDate;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * The first and the last day of a month 
 * 
 * Write a program that reads a year
 * and a month and outputs the first and the last day of this month.
 * 
 * Report a typo 
 * 
 * Sample Input 1:
 * 
 * 2017 
 * 1 
 * 
 * Sample Output 1:
 * 
 * 2017-01-01 
 * 2017-01-31
 * 
 * @author SMD_ASY
 *
 */

public class FirstAndLastDayOfMonth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int year = Integer.valueOf(sc.nextLine());
		int month = Integer.valueOf(sc.nextLine());
		sc.close();
		LocalDate ld = LocalDate.of(year, month, 1);
		System.out.print(ld.withDayOfMonth(1) + " " + ld.withDayOfMonth(ld.lengthOfMonth()));
	}

}
