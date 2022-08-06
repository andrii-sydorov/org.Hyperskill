package LocalDate;

import java.util.Scanner;
import java.time.LocalDate;

/**
 * 30 years before and after  
 * 
 * Write a program that reads a date from the
 * standard input and prints two dates: 30 years before and after compared to
 * the given date.
 * 
 * Report a typo 
 * 
 * Sample Input 1:
 * 
 * 2017-06-03 
 * 
 * Sample Output 1:
 * 
 * 1987-06-03 
 * 2047-06-03 
 * 
 * Sample Input 2:
 * 
 * 2017-04-28 
 * 
 * Sample Output 2:
 * 
 * 1987-04-28 
 * 2047-04-28
 * 
 * @author SMD_ASY
 *
 */

public class BeforeAndAfter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		LocalDate ld = LocalDate.parse(sc.nextLine());
		System.out.println(ld.minusYears(30));
		System.out.println(ld.plusYears(30));
		sc.close();
	}

}
