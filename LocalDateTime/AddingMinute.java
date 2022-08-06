package LocalDateTime;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Adding N minutes 
 * 
 * Write a program that adds N minutes to a given date
 * and time and prints out the resulting year, day of the year, hours, minutes
 * and seconds.
 * 
 * The input date-time should look like "2017-12-31T22:30:15", the result date
 * must be similar to "2018 139 19:50:15" (year, day of the year, hours,
 * minutes, seconds).
 * 
 * If the result has no seconds, the program must not print them.
 * 
 * Input data format
 * 
 * Two lines: the first one containing date-time, the second one containing a
 * number of minutes to add.
 * 
 * Output data format
 * 
 * The single line must contain a string with the result.
 * 
 * Report a typo 
 * 
 * Sample Input 1:
 * 
 * 2017-12-31T22:30:15 
 * 200000 
 * 
 * Sample Output 1:
 * 
 * 2018 139 19:50:15 
 * 
 * Sample Input 2:
 * 
 * 2017-05-05T15:20 
 * 20 
 * 
 * Sample Output 2:
 * 
 * 2017 125 15:40 
 * 
 * Sample Input 3:
 * 
 * 2017-01-01T01:01:01 
 * 5000000 
 * 
 * Sample Output 3:
 * 
 * 2026 186 06:21:01
 * 
 * @author SMD_ASY
 *
 */

public class AddingMinute {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		LocalDateTime ldt = LocalDateTime.parse(sc.nextLine());
		ldt = ldt.plusMinutes(sc.nextInt());
		System.out.println(ldt.getYear() + " " + ldt.getDayOfYear() + " " + ldt.toLocalTime());
		sc.close();
	}

}
