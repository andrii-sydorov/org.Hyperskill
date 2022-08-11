package AnonymousСlasses;

/**
 * An anonymous class with three methods  
 * 
 * There is an interface:
 * interface ThreeMethodsInterface {
 * 
 * 	void do1();
 * 
 * 	void do2();
 * 
 * 	void do3(); 
 * 
 * } 
 * You should create an anonymous class that implements the given
 * interface and assign the instance to the variable instance. The anonymous
 * class must override all methods. The overridden methods do1, do2, do3 must
 * output strings "Implemented do1", "Implemented do2", "Implemented do3"
 * respectively to the standard output. Each output should be on a new line.
 * 
 * @author SMD_ASY
 *
 */

public class ThreeMethods {

	public static void main(String[] args) {

		ThreeMethodsInterface instance = new ThreeMethodsInterface() {
			@Override
			public void do1() {
				System.out.println("Implemented do1");
			}

			@Override
			public void do2() {
				System.out.println("Implemented do2");
			}

			@Override
			public void do3() {
				System.out.println("Implemented do3");
			}
		};

		instance.do1();
		instance.do2();
		instance.do3();
	}
}

interface ThreeMethodsInterface {

	void do1();

	void do2();

	void do3();
}
