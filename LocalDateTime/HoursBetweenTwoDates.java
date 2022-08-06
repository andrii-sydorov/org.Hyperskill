package LocalDateTime;

import java.util.Scanner;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Whole hours between two date-time pairs 
 * 
 * The problem statement Write a
 * program that calculates how many whole hours are between the two date-time
 * pairs of the same year.
 * 
 * Input data format Two lines containing a date-time in a
 * year-month-dayThour:minute format (without seconds and nanoseconds).
 * 
 * Output data format The line containing an integer non-negative number.
 * 
 * Report a typo 
 * 
 * Sample Input 1:
 * 
 * 2017-06-15T01:50 
 * 2017-06-16T01:10 
 * 
 * Sample Output 1:
 * 
 * 23 
 * 
 * Sample Input 2:
 * 
 * 2017-06-15T01:50 
 * 2017-06-15T02:50 
 * 
 * Sample Output 2:
 * 
 * 1
 * 
 * @author SMD_ASY
 *
 */

public class HoursBetweenTwoDates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		LocalDateTime ldt1 = LocalDateTime.parse(sc.nextLine());
		LocalDateTime ldt2 = LocalDateTime.parse(sc.nextLine());
		sc.close();
		Duration d = Duration.between(ldt1, ldt2);
		System.out.println(Math.abs(d.toHours()));
	}

}
