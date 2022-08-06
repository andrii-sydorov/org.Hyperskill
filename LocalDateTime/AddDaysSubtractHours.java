package LocalDateTime;

import java.util.Scanner;
import java.time.LocalDateTime;

/**
 * Add days and subtract hours 
 * 
 * Write a program that changes the given
 * point of time: adds a certain number of days and subtracts a few hours.
 * 
 * Input and output date-time like this "2017-12-31T22:30".
 * 
 * Input data format
 * 
 * The single line containing date-time and two numbers: days to add and hours
 * to subtract. Input elements are separated by spaces.
 * 
 * Output data format
 * 
 * The output must contain only a date-time in the specified format.
 * 
 * Report a typo Sample 
 * 
 * Input 1:
 * 
 * 2017-12-31T22:30 10 5 
 * 
 * Sample Output 1:
 * 
 * 2018-01-10T17:30
 * 
 * @author SMD_ASY
 *
 */

public class AddDaysSubtractHours {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String[] time = sc.nextLine().split("\\s+");
		LocalDateTime ldt = LocalDateTime.parse(time[0]);
		int daysToAdd = Integer.valueOf(time[1]);
		int hoursToSub = Integer.valueOf(time[2]);
		System.out.println(ldt.plusDays(daysToAdd).minusHours(hoursToSub));
		sc.close();
	}

}
