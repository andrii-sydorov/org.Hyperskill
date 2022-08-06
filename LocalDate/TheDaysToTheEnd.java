package LocalDate;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * The n-th day from the end of a month 
 * 
 * Write a program that prints the n-th day from the end of a month.
 * 
 * The program must read the year, the month, and the remaining number of days
 * till the end of the month from standard input and then output the date.
 * 
 * Report a typo 
 * 
 * Sample Input 1:
 * 
 * 2017 1 1 
 * 
 * Sample Output 1:
 * 
 * 2017-01-31
 * 
 * @author SMD_ASY
 *
 */

public class TheDaysToTheEnd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int[] date = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::valueOf).toArray();
		LocalDate ld = LocalDate.of(date[0], date[1], 1);
		System.out.println(ld.withDayOfMonth(ld.lengthOfMonth() - date[2] + 1));
		sc.close();
	}

}
