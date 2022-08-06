package LocalDate;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * The last day of a month 
 * 
 * We normally speak of a day by addressing a month,
 * for example, August 30. Another way to indicate a day is to give an order
 * number to each day in a year. Then we could say, for example, "242nd day of
 * the year".
 * 
 * Write a program that reads a year and the number of a day in this year, and
 * checks if the day is the last day of a month or not.
 * 
 * The program must output either "true" or "false".
 * 
 * Report a typo Sample 
 * 
 * Input 1:
 * 
 * 2017 31 
 * 
 * Sample Output 1:
 * 
 * true
 * 
 * @author SMD_ASY
 *
 */

public class LastDayOfMonth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int[] date = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::valueOf).toArray();
		LocalDate ld = LocalDate.ofYearDay(date[0], date[1]);
		System.out.println(ld.lengthOfMonth() == ld.getDayOfMonth());
		sc.close();
	}

}
