package LocalDate;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * 
 * 2 weeks after 
 * 
 * Write a program that reads a date from the standard input
 * and prints a date that is 2 weeks after.
 * 
 * Report a typo 
 * 
 * Sample Input 1:
 * 
 * 2017-06-03 
 * 
 * Sample Output 1:
 * 
 * 2017-06-17 
 * 
 * Sample Input 2:
 * 
 * 2017-04-28 
 * 
 * Sample Output 2:
 * 
 * 2017-05-12
 * 
 * @author SMD_ASY
 *
 */

public class TwoWeeksAfter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		LocalDate ld = LocalDate.parse(sc.nextLine());
		sc.close();
		System.out.println(ld.plusDays(14));
	}

}
