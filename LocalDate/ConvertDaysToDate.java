package LocalDate;

import java.util.Scanner;
import java.time.LocalDate;

/**
 * Convert numbers to days 
 * 
 * Write a program that reads a year and
 * three days of this year (by their numbers) from the standard input and output
 * all dates corresponding to these numbers in the same order.
 * 
 * Report a typo 
 * 
 * Sample Input 1:
 * 
 * 2017 
 * 315 
 * 5 
 * 42 
 * 
 * Sample Output 1:
 * 
 * 2017-11-11 
 * 2017-01-05 
 * 2017-02-11
 * 
 * @author SMD_ASY
 *
 */

public class ConvertDaysToDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		LocalDate ld = LocalDate.of(Integer.valueOf(sc.nextLine()), 1, 1);
		int d1 = Integer.valueOf(sc.nextLine());
		int d2 = Integer.valueOf(sc.nextLine());
		int d3 = Integer.valueOf(sc.nextLine());
		sc.close();
		System.out.println(ld.withDayOfYear(d1));
		System.out.println(ld.withDayOfYear(d2));
		System.out.println(ld.withDayOfYear(d3));
	}

}
