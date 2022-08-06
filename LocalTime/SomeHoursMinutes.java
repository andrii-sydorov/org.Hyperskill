package LocalTime;

import java.util.Scanner;
import java.time.LocalTime;

/**
 * Some hours and minutes ago  
 * 
 * Write a program that reads a point in
 * time and prints another point in time at the specified number of hours and
 * minutes before the given one.
 * 
 * Input data format
 * 
 * The first line contains a point in time in hours:minutes format. The second
 * line contains two numbers: hours and minutes separated by a space.
 * 
 * Output data format
 * 
 * The single output line should contain a point in time before the input time
 * in the same format.
 * 
 * Report a typo 
 * Sample Input 1:
 * 
 * 18:10 
 * 2 30 
 * 
 * Sample Output 1:
 * 
 * 15:40
 * 
 * @author SMD_ASY
 *
 */

public class SomeHoursMinutes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		LocalTime lt = LocalTime.parse(sc.nextLine());
		int hours = sc.nextInt();
		int minutes = sc.nextInt();
		System.out.println(lt.minusHours(hours).minusMinutes(minutes));
		sc.close();
	}

}
