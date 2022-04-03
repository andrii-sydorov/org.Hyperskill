package Patterns.FactoryMethod;

import java.util.Scanner;

/**
 * Given classes are the Clock interface of products, specified clocks, and the
 * factory class ClockFactory to produce instances.
 * 
 * Your task is to implement the factory method produce. It should return a
 * clock according to the specified type string:
 * 
 * "Sand" — SandClock; 
 * "Digital" — DigitalClock; 
 * "Mechanical" — MechanicalClock.
 * 
 * Please, do not change the provided code of the clock classes. Report a typo
 * 
 * Sample Input 1:
 * 
 * Digital
 * 
 * Sample Output 1:
 * 
 * ...pim...
 * 
 * @author SMD_ASY
 *
 */

public class Clocks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Scanner scanner = new Scanner(System.in);
		final String type = scanner.next();
		final ClockFactory clockFactory = new ClockFactory();
		final Clock clock = clockFactory.produce(type);
		clock.tick();
		scanner.close();
	}

}

/* Product - Clock */
interface Clock {
	void tick();
}

/* Concrete Product - Sand Clock */
class SandClock implements Clock {
	public void tick() {
		System.out.println("...sand noise...");
	}
}

/* Concrete Product - Digital Clock */
class DigitalClock implements Clock {
	public void tick() {
		System.out.println("...pim...");
	}
}

/* Concrete Product - Mechanical Clock */
class MechanicalClock implements Clock {
	public void tick() {
		System.out.println("...clang mechanism...");
	}
}

class ClockFactory {

	/*
	 * It produces concrete clocks corresponding their types : Digital, Sand or
	 * Mechanical
	 */
	public Clock produce(String type) {
		// write your code here ...
		Clock cl = null;
		switch (type) {
		case "Sand":
			cl = new SandClock();
			break;
		case "Digital":
			cl = new DigitalClock();
			break;
		case "Mechanical":
			cl = new MechanicalClock();
			break;
		default:
		}
		return cl;
	}
}
