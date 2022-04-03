package EncapsulationCreatingObject;

import java.util.Scanner;

/**
 * The normal way to create an instance of a class is to use a public
 * constructor of the class. But there is another technique. A class can provide
 * a public static factory method that returns an instance of the class. An
 * advantage of static factory methods is that they have names that make the
 * code easier to read.
 * 
 * In this problem, you have the class Time containing three fields: hour,
 * minute and second. Implement the following static factory methods of this
 * class:
 * 
 * - noon() returns an instance initialized with 12 hours, 0 minutes, and 0
 * seconds. 
 * - midnight() returns an instance initialized with 0 hours, 0 minutes,
 * and 0 seconds. 
 * - of(int hour, int minute, int second) returns an instance initialized with 
 * passed hour, minute and second if the passed arguments are correct 
 * (hour: 0-23, minute: 0-59, seconds: 0-59), otherwise, null.
 * - ofSeconds(long seconds) returns an instance initialized with seconds passed
 * since midnight; as an example, the invocation Time.ofSeconds(500000) must
 * create an instance with 18 hours, 53 minutes and 20 seconds (days are
 * skipped);
 * 
 * As you see, the methods are more readable than the same constructors. If you
 * want to create an instance of Time representing noon, you can write:
 * 
 * Time noon = Time.noon();
 * 
 * Note:
 * 
 * do not change fields of the class Time; in a real application, it may be
 * better to throw an exception than return null when arguments are incorrect.
 * 
 * You must not read or output something in this problem. Just implement the
 * static factory methods. Report a typo
 * 
 * Sample Input 1:
 * 
 * noon
 * 
 * Sample Output 1:
 * 
 * 12 0 0
 * 
 * Sample Input 2:
 * 
 * midnight
 * 
 * Sample Output 2:
 * 
 * 0 0 0
 * 
 * @author SMD_ASY
 *
 */

class Time {

	int hour;
	int minute;
	int second;

	public static Time noon() {
		Time t = new Time();
		t.hour = 12;
		return t;
	}

	public static Time midnight() {
		Time t = new Time();
		return t;
	}

	public static Time of(int hour, int minute, int second) {
		if ((hour >= 0 && hour <= 23) && (minute >= 0 && minute <= 59) && (second >= 0 && second <= 59)) {
			Time t = new Time();
			t.hour = hour;
			t.minute = minute;
			t.second = second;
			return t;
		}
		return null;
	}

	public static Time ofSeconds(long seconds) {
		Time t = new Time();
		t.hour = (int) (seconds % (3600 * 24)) / 3600;
		t.minute = (int) (seconds % 3600) / 60;
		t.second = (int) (seconds % 3600) % 60;
		return t;
	}
}

public class Timer {

	public static void main(String[] args) {

		final Scanner scanner = new Scanner(System.in);

		final String type = scanner.next();
		Time time = null;

		switch (type) {
		case "noon":
			time = Time.noon();
			break;
		case "midnight":
			time = Time.midnight();
			break;
		case "hms":
			int h = scanner.nextInt();
			int m = scanner.nextInt();
			int s = scanner.nextInt();
			time = Time.of(h, m, s);
			break;
		case "seconds":
			time = Time.ofSeconds(scanner.nextInt());
			break;
		default:
			time = null;
			break;
		}
		scanner.close();
		if (time == null) {
			System.out.println(time);
		} else {
			System.out.printf("%s %s %s", time.hour, time.minute, time.second);
		}

	}

}
