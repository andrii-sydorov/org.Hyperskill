package InstantTime;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Scanner;

/**
 * Print the correct Instant unit
 * 
 * Implement the subtractFromEpoch() method to subtract the given days from the
 * EPOCH and print the result in the given time zone. Report a typo
 * 
 * Sample Input 1:
 * 
 * 10 
 * Asia/Yerevan
 * 
 * Sample Output 1:
 * 
 * 1969-12-22T04:00+04:00[Asia/Yerevan]
 * 
 * @author SMD_ASY
 *
 */

public class InstantUnit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int daysToSubtract = Integer.valueOf(sc.nextLine());
		String zone = sc.nextLine();
		subtractFromEpoch(daysToSubtract, zone);
		sc.close();

		Instant instant = Instant.ofEpochSecond(0l, 1_000_000_001);
		System.out.println(instant);
	}

	private static void subtractFromEpoch(int days, String zone) {
		Instant inst = Instant.EPOCH;
		System.out.println(inst.minus(Duration.ofDays(days)).atZone(ZoneId.of(zone)));
	}

}
