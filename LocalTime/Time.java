package LocalTime;

import java.time.LocalTime;

public class Time {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalTime lt = LocalTime.now();
		System.out.println(lt);
		///
		lt = LocalTime.of(11, 45);
		System.out.println(lt);
		lt = LocalTime.of(11, 45, 23);
		System.out.println(lt);
		lt = LocalTime.parse("11:45:45");
		System.out.println(lt);
		///
		System.out.println(LocalTime.MIDNIGHT);
		System.out.println(LocalTime.NOON);
		System.out.println(LocalTime.MIN);
		System.out.println(LocalTime.MAX);
		lt = LocalTime.MAX;
		///
		System.out.println("Hours are " + lt.getHour());
		System.out.println("Minutes are " + lt.getMinute());
		System.out.println("Seconds are " + lt.getSecond());
		///
		lt = LocalTime.now();
		System.out.println(lt.toSecondOfDay());
		System.out.println("Seconds are " + lt.getSecond());
		
		LocalTime time = LocalTime.of(1, 30).plusMinutes(690);
		System.out.println(time.getHour());
	}

}
