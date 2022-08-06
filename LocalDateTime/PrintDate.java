package LocalDateTime;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Add 11 hours and print the date  
 * 
 * Write a program that reads date-time,
 * adds 11 hours to it and then prints out the resulting date.
 * 
 * The input date-time format should be, for example, "2017-12-31T22:30". The
 * resulting date then must be "2018-01-01".
 * 
 * Sample Input 1:
 * 
 * 2017-12-31T22:30 
 * 
 * Sample Output 1:
 * 
 * 2018-01-01
 * 
 * @author SMD_ASY
 *
 */

class PrintDate {

	public static void main(String[] args) {
		// put your code here
		Scanner sc = new Scanner(System.in);
		LocalDateTime ldt = LocalDateTime.parse(sc.nextLine());
		ldt = ldt.plusHours(11);
		LocalDateTime ldt1 = LocalDateTime.of(ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth(), 0, 0);
		sc.close();
		System.out.println(ldt1.toLocalDate());

	}
}
