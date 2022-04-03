package Patterns.FactoryMethod;

/**
 * The first part of the task is to imagine you are the boss of a LaptopStore.
 * The real task though is the code: your engineer should be able to create
 * 17'', 15'' or 13'' laptops without any concrete details. Report a typo
 * 
 * Sample Input 1:
 * 
 * Sample Output 1:
 * 
 * Making a 13 inch Laptop 
 * Attaching keyboard 
 * Attaching trackpad 
 * Attaching display 
 * Done a 13 inch Laptop
 * 
 * Making a 15 inch Laptop 
 * Attaching keyboard 
 * Attaching trackpad 
 * Attaching display 
 * Done a 15 inch Laptop
 * 
 * Making a 17 inch Laptop 
 * Attaching keyboard 
 * Attaching trackpad 
 * Attaching display 
 * Done a 17 inch Laptop
 * 
 * Available laptops in the store: 
 * 13 inch Laptop 
 * 15 inch Laptop 
 * 17 inch Laptop
 * 
 * @author SMD_ASY
 *
 */

public class TestDrive {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		LaptopStore laptopStore = new LaptopStore();

		Laptop laptop13 = laptopStore.orderLaptop("13 inch");
		Laptop laptop15 = laptopStore.orderLaptop("15 inch");
		Laptop laptop17 = laptopStore.orderLaptop("17 inch");

		System.out.println("Availiable laptops in the store:");
		System.out.println(laptop13);
		System.out.println(laptop15);
		System.out.println(laptop17);
	}

}

abstract class LaptopFactory {

	abstract Laptop createLaptop(String type);

	Laptop orderLaptop(String type) throws InterruptedException {
		// TODO the realisation of createLaptop goes from instance and allways!!!
		Laptop laptop = createLaptop(type);
		if (laptop == null) {
			System.out.println("Sorry, we are not able to create this kind of laptop\n");
			return null;
		}
		System.out.println("Making a " + laptop.getName());

		laptop.attachKeyboard();
		laptop.attachTrackpad();
		laptop.attachDisplay();
		Thread.sleep(1500);

		System.out.println("Done a " + laptop.getName() + "\n");
		return laptop;
	}
}

class LaptopStore extends LaptopFactory {

	Laptop createLaptop(String type) {
		switch (type) {
		case "13 inch":
			return new Laptop13("13 inch Laptop");
		case "15 inch":
			return new Laptop15("15 inch Laptop");
		case "17 inch":
			return new Laptop17("17 inch Laptop");
		default:
			return null;
		}
	}

}

class Laptop {
	private String name;

	public Laptop(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void attachKeyboard() {
		System.out.println("Attachning keyboard");
	}

	public void attachTrackpad() {
		System.out.println("Attaching trackpad");
	}

	public void attachDisplay() {
		System.out.println("Attaching display");
	}

	@Override
	public String toString() {
		return name + " Laptop";
	}
}

class Laptop13 extends Laptop {
	public Laptop13(String name) {
		super(name);
	}
}

class Laptop15 extends Laptop {
	public Laptop15(String name) {
		super(name);
	}
}

class Laptop17 extends Laptop {
	public Laptop17(String name) {
		super(name);
	}
}
