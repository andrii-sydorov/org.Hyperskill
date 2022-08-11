package AnonymousСlasses;

/**
 * Single method 
 * 
 * There is an interface:
 * 
 * interface SingleMethodInterface {
 * 
 * 	void doSomething(); 
 * 
 * } 
 * 
 * You should create an anonymous class that implements
 * the given interface and assign the instance to the variable instance. The
 * anonymous class must override the method doSomething so that it outputs "The
 * anonymous class does something" to the standard output.
 * 
 * Use the provided code template, do not copy the interface to the code.
 * 
 * @author SMD_ASY
 *
 */

public class SingleMethod {

	public static void main(String[] args) {

		SingleMethodInterface instance = new SingleMethodInterface() {
			@Override
			public void doSomething() {
				System.out.println("The anonymous class does something");
			}
		};

		instance.doSomething();
	}
}

interface SingleMethodInterface {

	void doSomething();
}
