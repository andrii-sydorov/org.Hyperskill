package LocalDateTime;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Subtracting hours and adding minutes 
 * The problem statement Write a
 * program that subtracts N hours and adds M minutes to a date-time pair.
 * 
 * Input data format The first line contains a date-time pair
 * (year-month-dayThours:minutes). The second line contains two numbers
 * separated by a space: hours to subtract and minutes to add.
 * 
 * Output data format A single line with a date-time pair
 * (year-month-dayThours:minutes). Report a typo 
 * 
 * Sample Input 1:
 * 
 * 2017-12-15T11:11 
 * 1 58 
 * 
 * Sample Output 1:
 * 
 * 2017-12-15T11:09 
 * 
 * Sample Input 2:
 * 
 * 2016-11-22T00:00 
 * 5000 300010 
 * 
 * Sample Output 2:
 * 
 * 2016-11-22T00:10
 * 
 * @author SMD_ASY
 *
 */

public class MinusHoursPlusMinutes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		LocalDateTime ldt = LocalDateTime.parse(sc.nextLine());
		int hours = sc.nextInt();
		int minutes = sc.nextInt();
		System.out.println(ldt.minusHours(hours).plusMinutes(minutes));
		sc.close();
	}

}
