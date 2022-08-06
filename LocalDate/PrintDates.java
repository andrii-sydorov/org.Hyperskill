package LocalDate;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Print dates of a year with an offset  
 * Write a program that prints all
 * dates of the given year with a specified offset applied.
 * 
 * It should read a starting date and a value of an offset (in days).
 * 
 * In the output, dates should be printed in ascending order with the starting
 * date included. Do not output the dates from the next year.
 * 
 * Report a typo 
 * Sample Input 1:
 * 
 * 2017-12-19 
 * 4
 *  
 * Sample Output 1:
 * 
 * 2017-12-19 
 * 2017-12-23 
 * 2017-12-27 
 * 2017-12-31 
 * 
 * Sample Input 2:
 * 
 * 2017-09-22 
 * 100
 *  
 * Sample Output 2:
 * 
 * 2017-09-22 
 * 2017-12-31
 * 
 * @author SMD_ASY
 *
 */

public class PrintDates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		LocalDate ld = LocalDate.parse(sc.nextLine());
		int toAdd = Integer.valueOf(sc.nextLine());
		int limit = ld.getYear();
		do {
			System.out.println(ld);
			ld = ld.plusDays(toAdd);
		} while (limit >= ld.getYear());
		sc.close();
	}

}
