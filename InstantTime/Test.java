package InstantTime;

import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(ZoneId.systemDefault().getId());
		System.out.println(ZoneId.systemDefault().getRules());
		Instant inst = Instant.EPOCH;
		System.out.println(inst);
		System.out.println(inst.atZone(ZoneId.of("GMT+4")));
		System.out.println(ZoneId.systemDefault());
		
		Instant instant1 = Instant.ofEpochSecond(123456L);
		Instant instant2 = Instant.ofEpochSecond(123456789L);
		System.out.println(instant1.isAfter(instant2));
		System.out.println(instant2.isBefore(instant1));
		System.out.println(instant2.compareTo(instant1));
	    
	    Instant instant  = Instant.EPOCH;
	    instant.minus(Period.ofDays(1));

	    System.out.println(instant.minus(Duration.ofDays(32)).atZone(ZoneId.of("GMT+6")));
	    
	    Instant instant3  = Instant.EPOCH;

	    System.out.println(instant3.plus(1, ChronoUnit.WEEKS).atZone(ZoneId.of("GMT+4")));
	}

}
