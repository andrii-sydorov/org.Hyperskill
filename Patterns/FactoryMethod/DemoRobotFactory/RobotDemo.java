package Patterns.FactoryMethod.DemoRobotFactory;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

/**
 * Let's expand our RobotFactory! We've added a new Robot type — the Guardian.
 * 
 * Provide the RobotGuardian class and implement a factory method in
 * RobotFactory methods to create Robotinstances.
 * 
 * Please, do not change the provided code of the classes. Report a typo
 * 
 * Sample Input 1:
 * 
 * TYU11 
 * O13L3
 * 
 * Sample Output 1:
 * 
 * cleaner-robot: { 
 * 			name : TYU11 
 * 			description : Robot will clean my room and dry my socks 
 * 			power : 100 
 * } 
 * guardian-robot: { 
 * 			name : O13L3 
 * 			description : Knight	will secure my daughter while she sleeping 
 * 			power : 200 
 * }
 * 
 * @author SMD_ASY
 *
 */

public class RobotDemo {

	private static final int CLEANER_POWER = 100;
	private static final int GUARDIAN_POWER = 200;
	static List<String> ls = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		File f = new File("./src/FactoryMethod/DemoRobotFactory/data.txt");
		StringBuilder sb = new StringBuilder();
		Scanner scanner = new Scanner(f);
		while (scanner.hasNext()) {
			ls.add(scanner.nextLine());
		}
		scanner.close();
		// final Scanner sc = new Scanner(System.in);
		RobotFactory rf = new RobotFactory();

//		final String cleanerName = sc.nextLine();
//		final String guardianName = sc.nextLine();

		final String cleanerName = ls.get(0);
		final String guardianName = ls.get(1);
		Robot cleaner = rf.getRobot(RobotType.ROBOT_CLEANER, cleanerName, "Robot will clean my room and dry my socks",
				CLEANER_POWER);
		Robot guardian = rf.getRobot(RobotType.ROBOT_GUARDIAN, guardianName,
				"Knight will secure my daughter while she sleeping", GUARDIAN_POWER);

		System.out.println(cleaner);
		System.out.println(guardian);
	}

}

/** Product - Robot */
abstract class Robot {

	private int power;

	abstract String getName();

	abstract String getDescription();

	public Robot(int power) {
		this.power = power;
	}

	public int getPower() {
		return this.power;
	}

	@Override
	public String toString() {
		return "robot: {\n\t" + "name : " + getName() + "\n\t" + "description : " + getDescription() + "\n\t"
				+ "power: " + getPower() + "\n}";
	}
}

/** Robot types */
enum RobotType {
	ROBOT_CLEANER, ROBOT_GUARDIAN;
}

/** Concrete Product - Robot Cleaner */
class RobotCleaner extends Robot {

	private String name;
	private String description;

	public RobotCleaner(String name, String description, int power) {
		super(power);
		this.name = name;
		this.description = description;
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	String getDescription() {
		// TODO Auto-generated method stub
		return this.description;
	}

	@Override
	public String toString() {
		return "cleaner-" + super.toString();
	}
}

/** Concrete Product - Robot Guardian */
class RobotGuardian extends Robot {

	private String name;
	private String description;

	public RobotGuardian(String name, String description, int power) {
		super(power);
		this.name = name;
		this.description = description;
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	String getDescription() {
		// TODO Auto-generated method stub
		return this.description;
	}

	@Override
	public String toString() {
		return "guardian-" + super.toString();
	}
}

class RobotFactory {
	/** Factory method */
	Robot getRobot(RobotType type, String name, String description, int power) {
		Robot rb = null;
		switch (type) {
		case ROBOT_CLEANER:
			rb = new RobotCleaner(name, description, power);
			break;
		case ROBOT_GUARDIAN:
			rb = new RobotGuardian(name, description, power);
			break;
		default:
		}
		return rb;

	}
}
