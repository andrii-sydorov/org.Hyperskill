package LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Same date and time 
 * 
 * Select all statements that are equal and represent
 * the date-time point of 31 December 2019; 23:59:59.
 * 
 * Report a typo Select one or more options from the list
 * 
 * LocalDateTime.of(LocalDate.of(2020, 1, 1),
 * LocalTime.MIDNIGHT).minusSeconds(1);
 * 
 * LocalDateTime.of(2020, 12, 31, 23, 59, 59).minusYears(1);
 * 
 * LocalDateTime.parse("2019-12-31T23:59").withSecond(59);
 * 
 * LocalDateTime.of(2020, 1, 1, 0, 1, 1).minusSeconds(61);
 * 
 * LocalDateTime.parse("2017-12-31T23:59").withYear(2019);
 * 
 * LocalDateTime.of(LocalDate.of(2019, 12, 31), LocalTime.MAX);
 * 
 * @author SMD_ASY
 *
 */

public class SameDataAndTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(LocalDateTime.of(2020, 12, 31, 23, 59, 59).minusYears(1));

		System.out.println(LocalDateTime.parse("2017-12-31T23:59").withYear(2019));

		System.out.println(LocalDateTime.of(LocalDate.of(2019, 12, 31), LocalTime.MAX));

		System.out.println(LocalDateTime.parse("2019-12-31T23:59").withSecond(59));

		System.out.println(LocalDateTime.of(LocalDate.of(2020, 1, 1), LocalTime.MIDNIGHT).minusSeconds(1));

		System.out.println(LocalDateTime.of(2020, 1, 1, 0, 1, 1).minusSeconds(61));
	}

}
