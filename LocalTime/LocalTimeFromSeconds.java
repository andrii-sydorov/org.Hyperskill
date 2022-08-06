package LocalTime;

import java.util.Scanner;
import java.time.LocalTime;

/**
 * Seconds since start of a day 
 * 
 * Write a program that reads a number of
 * seconds from the start of a day and prints the current time.
 * 
 * If the resulting time has zero seconds, do not output them.
 * 
 * Report a typo 
 * 
 * Sample Input 1:
 * 
 * 12345 
 * 
 * Sample Output 1:
 * 
 * 03:25:45 
 * 
 * Sample Input 2:
 * 
 * 60000 
 * 
 * Sample Output 2:
 * 
 * 16:40 
 * 
 * Sample Input 3:
 * 
 * 3600 
 * 
 * Sample Output 3:
 * 
 * 01:00
 * 
 * @author SMD_ASY
 *
 */

public class LocalTimeFromSeconds {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int time = Integer.valueOf(sc.nextLine());
		LocalTime lt = LocalTime.ofSecondOfDay(time);
		System.out.println(lt);
		sc.close();
	}

}
