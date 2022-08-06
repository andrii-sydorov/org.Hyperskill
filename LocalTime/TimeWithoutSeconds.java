package LocalTime;

import java.time.LocalTime;
import java.util.Scanner;

/**
 * Return time without seconds
 * 
 * Write a program that reads a point in time and outputs the same time without
 * seconds.
 * 
 * Note: the input time may not contain seconds.
 * 
 * Report a typo 
 * Sample Input 1:
 * 
 * 18:10:55 
 * 
 * Sample Output 1:
 * 
 * 18:10 
 * 
 * Sample Input 2:
 * 
 * 19:30:30 
 * 
 * Sample Output 2:
 * 
 * 19:30
 * 
 * @author SMD_ASY
 *
 */

public class TimeWithoutSeconds {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LocalTime lt = LocalTime.parse(sc.nextLine());
		System.out.println(lt.withSecond(0));
		sc.close();
	}
}
