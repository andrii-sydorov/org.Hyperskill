package Patterns.FactoryMethod.DemoRobotFactory;

import java.util.Scanner;

public class MotorDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String model = sc.nextLine();
		String description = sc.nextLine();
		int power = Integer.valueOf(sc.nextLine());
		sc.close();
		MotorFactory mf = new MotorFactory();
		Motor m = mf.createMotor(model, description, power);
		System.out.println(m);
	}

}

class MotorFactory {
	Motor createMotor(String model, String description, int power) {
		Motor m = null;
		switch (model) {
		case "E":
			m = new ElectricMotor(model, description, power);
			break;
		case "H":
			m = new HydraulicMotor(model, description, power);
			break;
		case "P":
			m = new PneumaticMotor(model, description, power);
			break;
		case "W":
			m = new WarpMotor(model, description, power);
			break;
		}
		return m;
	}
}

class Motor {

	String model;
	String description;
	int power;

	public String getModel() {
		return this.model;
	}

	public String getDescription() {
		return this.description;
	}

	public int getPower() {
		return this.power;
	}

	public String toString() {
		return "{model:" + getModel() + ",power:" + getPower() + "}";
	}
}

class ElectricMotor extends Motor {

	public ElectricMotor(String model, String description, int power) {
		this.model = model;
		this.description = description;
		this.power = power;
	}

	public String toString() {
		return "Electric motor=" + super.toString();
	}

}

class HydraulicMotor extends Motor {

	public HydraulicMotor(String model, String description, int power) {
		this.model = model;
		this.description = description;
		this.power = power;
	}

	public String toString() {
		return "Hydraulic motor=" + super.toString();
	}
}

class PneumaticMotor extends Motor {

	public PneumaticMotor(String model, String description, int power) {
		this.model = model;
		this.description = description;
		this.power = power;
	}

	public String toString() {
		return "Pneumatic motor=" + super.toString();
	}
}

class WarpMotor extends Motor {

	public WarpMotor(String model, String description, int power) {
		this.model = model;
		this.description = description;
		this.power = power;
	}

	public String toString() {
		return "Warp motor=" + super.toString();
	}
}
