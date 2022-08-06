package LocalDate;

import java.util.Scanner;
import java.time.LocalDate;

/**
 * 10 days before 
 * Write a program that reads a date from the standard input
 * and prints a date that is 10 days before.
 * 
 * Report a typo 
 * Sample Input 1:
 * 
 * 2017-06-03 
 * 
 * Sample Output 1:
 * 
 * 2017-05-24 
 * 
 * Sample Input 2:
 * 
 * 2017-04-28 
 * 
 * Sample Output 2:
 * 
 * 2017-04-18
 * 
 * @author SMD_ASY
 *
 */

public class DayBefore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		LocalDate ld = LocalDate.parse(sc.nextLine());
		int toSub = 10;
		System.out.println(ld.minusDays(toSub));
		sc.close();
		// System.out.println(ld.minusDays(toSub).getDayOfWeek());
	}

}
