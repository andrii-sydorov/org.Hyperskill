package Patterns.FactoryMethod;

import java.util.Scanner;

/** Product */
abstract class Robot {

	public abstract String getName();

	public abstract String getDescription();

	public abstract int getPower();

	@Override
	public String toString() {
		return "robot: {\n\t" + "name : " + getName() + "\n\t" + "description : " + getDescription() + "\n\t"
				+ "power : " + getPower() + "\n}";
	}
}

/** Type of product - Robot Type */
enum RobotType {
	ROBOT_CLEANER
}

/** Concrete Product - Robot Cleaner */
class RobotCleaner extends Robot {

	private String name;
	private String description;
	private int power;

	public RobotCleaner(String name, String description, int power) {
		this.name = name;
		this.description = description;
		this.power = power;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public int getPower() {
		return this.power;
	}

	@Override
	public String toString() {
		return "cleaner-" + super.toString();
	}

}

/** Factory */
class RobotFactory {

	/** Factory Method **/
	public Robot getRobot(RobotType type, String name, String description, int power) {
		/** write your code here ... */
		// only for repetetion iterating over enums
		if (false) {
			for (RobotType rt : RobotType.values()) {
				System.out.println(rt);
			}
		}
		Robot rb = null;
		switch (type) {
		case ROBOT_CLEANER:
			rb = new RobotCleaner(name, description, power);
			return rb;
		default:
			return null;
		}
	}
}

/** Factory Demo */
/**
 * The given classes are components of the Factory Method pattern.
 * 
 * Robot is the product and RobotCleaner is the concrete product.
 * 
 * Implement a factory method in RobotFactory methods to create RobotCleaner.
 * 
 * Please, do not change the provided code of the classes. Report a typo
 * 
 * Sample Input 1:
 * 
 * RAS012
 * 
 * Sample Output 1:
 * 
 * cleaner-robot: { 
 * 			name : RAS012 
 * 			description : Robot will clean my room and dry my socks 
 * 			power : 100 
 * }
 * 
 * @author SMD_ASY
 *
 */
public class RobotDemo {
	public static void main(String[] args) {

		RobotFactory robotFactory = new RobotFactory();
		Scanner scanner = new Scanner(System.in);

		String nameCleaner = scanner.nextLine();

		Robot robotCleaner = robotFactory.getRobot(RobotType.ROBOT_CLEANER, nameCleaner,
				"Robot will clean my room and dry my socks", 100);

		System.out.println(robotCleaner);

		scanner.close();
	}
}