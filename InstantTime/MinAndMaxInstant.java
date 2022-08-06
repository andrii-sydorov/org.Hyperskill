package InstantTime;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.time.Instant;
import java.util.Scanner;

/**
 * Min and max Instant units
 * 
 * You are given a list of Instant units. Find how many seconds have passed
 * starting from the minimum unit to the maximum unit. For this purpose, you
 * must explore the minusSeconds() method of the Instant class. Report a typo
 * 
 * 
 * Sample Input 1:
 * 
 * 1991-04-15T17:30:00Z 
 * 1995-05-23T17:30:00Z 
 * 2011-07-22T17:30:00Z
 * 2022-07-04T18:30:00Z 
 * 
 * Sample Output 1:
 * 
 * 985222800
 * 
 * @author SMD_ASY
 *
 */

public class MinAndMaxInstant {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		List<Instant> ls = createInstantList(sc);

		long result = getMaxMinusMin(ls);

		System.out.println(result);
	}

	private static long getMaxMinusMin(List<Instant> ls) {
		Collections.sort(ls);

		return ls.get(ls.size() - 1).getEpochSecond() - ls.get(0).getEpochSecond();
	}

	public static List<Instant> createInstantList(Scanner scanner) {
		List<Instant> instantList = new ArrayList<>();
		instantList.add(Instant.parse(scanner.nextLine()));
		instantList.add(Instant.parse(scanner.nextLine()));
		instantList.add(Instant.parse(scanner.nextLine()));
		instantList.add(Instant.parse(scanner.nextLine()));

		return instantList;
	}

}
