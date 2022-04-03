package Patterns.FactoryMethod;

import java.util.Scanner;

/**
 * Motors A lovely idea befalls you: you decide to automate the production of
 * motors.
 * 
 * You have 4 types of motors: electric, hydraulic, pneumatic and warp drive.
 * 
 * You must implement MotorFactory and the specified classes of motors.
 * 
 * Please, do not change the provided code of the motor classes.
 * 
 * 
 * Sample Input: 
 * E 
 * R-45 
 * 1000
 * 
 * Sample Output: 
 * Electric motor={model:R-45,power:1000}
 * 
 * @author SMD_ASY
 *
 */

class Main {
	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);
		final char type = scanner.next().charAt(0);
		final String model = scanner.next();
		final long power = scanner.nextLong();
		// write your code here ...
		Motor motor = MotorFactory.make(type, model, power);
		scanner.close();
		System.out.println(motor);
	}
}

/* Product - Motor */
abstract class Motor {

	String model;
	long power;

	public Motor(String model, long power) {
		this.model = model;
		this.power = power;
	}

	@Override
	public String toString() {
		return "motor={model:" + model + ",power:" + power + "}";
	}
}

class PneumaticMotor extends Motor {
	// write your code here ...
	public PneumaticMotor(String model, long power) {
		super(model, power);
	}

	public String toString() {
		return "Electric " + super.toString();
	}
}

class HydraulicMotor extends Motor {
	// write your code here ...
	public HydraulicMotor(String model, long power) {
		super(model, power);
	}

	public String toString() {
		return "Hydraulic " + super.toString();
	}
}

class ElectricMotor extends Motor {
	// write your code here ...
	public ElectricMotor(String model, long power) {
		super(model, power);
	}

	public String toString() {
		return "Electric " + super.toString();
	}
}

class WarpDrive extends Motor {
	// write your code here ...
	public WarpDrive(String model, long power) {
		super(model, power);
	}

	public String toString() {
		return "Warp " + super.toString();
	}
}

class MotorFactory {

	/**
	 * It returns an initialized motor according to the specified type by the first
	 * character: 'P' or 'p' - pneumatic, 'H' or 'h' - hydraulic, 'E' or 'e' -
	 * electric, 'W' or 'w' - warp.
	 */
	public static Motor make(char type, String model, long power) {
		// write your code here ...
		type = Character.toUpperCase(type);
		Motor m = null;
		switch (type) {
		case 'E':
			m = new ElectricMotor(model, power);
			break;
		case 'P':
			m = new PneumaticMotor(model, power);
			break;
		case 'H':
			m = new HydraulicMotor(model, power);
			break;
		case 'W':
			m = new WarpDrive(model, power);
			break;
		}
		return m;
	}
}
