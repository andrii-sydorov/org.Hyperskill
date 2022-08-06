package InstantTime;

import java.time.Instant;
import java.util.Scanner;

/**
 * The Instant to a long
 * 
 * Implement the dateInEpochSeconds() method to accept some text representing an
 * Instant unit and print the long equivalent of that unit. For this task, you
 * should use the getEpochSecond() method to parse an Instant unit to a long
 * value. Report a typo 
 * 
 * Sample Input 1:
 * 
 * 1991-04-15T17:30:00Z 
 * 
 * Sample Output 1:
 * 
 * 671736600
 * 
 * @author SMD_ASY
 *
 */

public class InstantLong {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String time = sc.nextLine();
		System.out.println(dateInEpochSecond(time));
		sc.close();
	}

	private static long dateInEpochSecond(String time) {
		Instant inst = Instant.parse(time);
		return inst.getEpochSecond();
	}

}
