package Patterns.Teamplate;

import java.util.Scanner;

/**
 * Build a house
 * 
 * There are three classes: an abstract class House, and two concrete classes,
 * Wooden and Stone. You must implement the abstract class House with a template
 * method called build() to build a new house using the following algorithm:
 * 
 * Choose a location Place a foundation Place walls Place windows Place doors
 * Place roofs Connect the house to the electrical grid
 * 
 * The Stone class is already provided. Use it to help you write the abstract
 * methods in the abstract House class. Make the Wooden class inherit from the
 * House class and implement the methods according to the console output. Report
 * a typo
 * 
 * Sample Input 1:
 * 
 * stone
 * 
 * Sample Output 1:
 * 
 * Choose the best location for the new house 
 * Place a stone foundation Place stone walls 
 * Place reinforced windows 
 * Place reinforced doors 
 * Place metal sheet roofs 
 * Connect the house to the electrical grid
 * 
 * Sample Input 2:
 * 
 * wooden
 * 
 * Sample Output 2:
 * 
 * Choose the best location for the new house 
 * Place a wooden foundation 
 * Place wooden walls 
 * Place wooden windows 
 * Place wooden doors 
 * Place metal sheet roofs
 * Connect the house to the electrical grid
 * 
 * @author SMD_ASY
 *
 */

public class BuildHouse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Scanner scanner = new Scanner(System.in);
		final String type = scanner.nextLine();
		scanner.close();
		House house = null;
		if ("wooden".equalsIgnoreCase(type)) {
			house = new Wooden();
		} else if ("stone".equalsIgnoreCase(type)) {
			house = new Stone();
		} else {
			System.out.println("Error!");
			System.exit(0);
		}
		house.build();
	}

}

abstract class House {

	void build() {
		chooseLocation();
		placeFoundations();
		placeWalls();
		placeWindows();
		placeDoors();
		placeRoofs();
		connectElectricity();
	}

	abstract void placeFoundations();

	abstract void placeWalls();

	abstract void placeWindows();

	abstract void placeDoors();

	// Do not change the code below
	public void chooseLocation() {
		System.out.println("Choose the best location for the new house");
	}

	public void placeRoofs() {
		System.out.println("Place metal sheet roofs");
	}

	public void connectElectricity() {
		System.out.println("Connect the house to the electrical grid");
	}
}

class Wooden extends House {
	// write your code here ...
	@Override
	public void placeFoundations() {
		System.out.println("Place a wooden foundation");
	}

	@Override
	public void placeWalls() {
		System.out.println("Place wooden walls");
	}

	@Override
	public void placeWindows() {
		System.out.println("Place wooden windows");
	}

	@Override
	public void placeDoors() {
		System.out.println("Place wooden doors");
	}
}

class Stone extends House {

	@Override
	public void placeFoundations() {
		System.out.println("Place a stone foundation");
	}

	@Override
	public void placeWalls() {
		System.out.println("Place stone walls");
	}

	@Override
	public void placeWindows() {
		System.out.println("Place reinforced windows");
	}

	@Override
	public void placeDoors() {
		System.out.println("Place reinforced doors");
	}
}
