package LocalDate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The first day of a month or not  
 * 
 * We are used to talking about days in
 * relation to their position in a month, for example, February 22. But as we've
 * already mentioned, there's another way to address a day: to give an order
 * number to each day in a year. Then we could say, for example, "53rd day of
 * the year".
 * 
 * Write a program that reads a year and the order number of a day, and checks
 * if this day is the first day of a month or not.
 * 
 * The program must output either "true" or "false".
 * 
 * Report a typo 
 * 
 * Sample Input 1:
 * 
 * 2017 31 
 * 
 * Sample Output 1:
 * 
 * false
 * 
 * @author SMD_ASY
 *
 */

public class TheFirstDay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int[] date = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::valueOf).toArray();
		LocalDate ld = LocalDate.ofYearDay(date[0], date[1]);
		System.out.println(ld.getDayOfMonth() == 1);
		sc.close();
	}

}
