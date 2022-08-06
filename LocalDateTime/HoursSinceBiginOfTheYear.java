package LocalDateTime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;


public class HoursSinceBiginOfTheYear {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		LocalDateTime ldtStop = LocalDateTime.parse(sc.nextLine());
		LocalDateTime ldtStart = ldtStop.withYear(ldtStop.getYear()).withDayOfYear(1).withHour(0).withMinute(0).withSecond(0);
		Duration d = Duration.between(ldtStart, ldtStop);
		System.out.println(d.toHours());
		sc.close();
	}

}
