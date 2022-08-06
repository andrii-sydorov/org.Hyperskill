package LocalDate;

import java.util.Scanner;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public class FindAllMondays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int[] date = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer :: valueOf).toArray();
		LocalDate ld = LocalDate.of(date[0], date[1], 1);
		int month = ld.getMonthValue();
		do {
			if (ld.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
				System.out.println(ld);
			}
			ld = ld.plusDays(1);
		} while (ld.getMonthValue() == month);
		sc.close();
	}

}
