package LocalDateTime;

import java.util.Scanner;
import java.time.LocalDateTime;

/**
 * Merging date-time instances 
 * 
 * Implement a method that takes two
 * instances of LocalDateTime class and merges them into one object by choosing
 * the largest value of each component for the target object. Consider the
 * following components: years, months, days of months, hours, minutes and
 * seconds.
 * 
 * Output the resulting LocalDateTime object.
 * 
 * Report a typo 
 * 
 * Sample Input 1:
 * 
 * 2018-10-20T22:30 
 * 2017-12-30T22:31:45 
 * 
 * Sample Output 1:
 * 
 * 2018-12-30T22:31:45
 * 
 * @author SMD_ASY
 *
 */

public class MergeDateTime {

	public static LocalDateTime merge(LocalDateTime dateTime1, LocalDateTime dateTime2) {
		// write your code here
		int year = dateTime1.getYear() >= dateTime2.getYear() ? dateTime1.getYear() : dateTime2.getYear();
		int month = dateTime1.getMonthValue() >= dateTime2.getMonthValue() ? dateTime1.getMonthValue()
				: dateTime2.getMonthValue();
		int days = dateTime1.getDayOfMonth() >= dateTime2.getDayOfMonth() ? dateTime1.getDayOfMonth()
				: dateTime2.getDayOfMonth();
		int hours = dateTime1.getHour() >= dateTime2.getHour() ? dateTime1.getHour() : dateTime2.getHour();
		int minutes = dateTime1.getMinute() >= dateTime2.getMinute() ? dateTime1.getMinute() : dateTime2.getMinute();
		int seconds = dateTime1.getSecond() >= dateTime2.getSecond() ? dateTime1.getSecond() : dateTime2.getSecond();
		return LocalDateTime.of(year, month, days, hours, minutes, seconds);
	}

	/* Do not change code below */
	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final LocalDateTime firstDateTime = LocalDateTime.parse(scanner.nextLine());
		final LocalDateTime secondDateTime = LocalDateTime.parse(scanner.nextLine());
		System.out.println(merge(firstDateTime, secondDateTime));
		scanner.close();
	}

}
