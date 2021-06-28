package AbstractClasses;

/**
 * Here's a hierarchy consisting of three classes A, B and C. The class C is
 * incomplete.
 * 
 * abstract class A {
 * 
 * 		public static void staticFoo() { }
 * 
 * 		public void instanceBar() { }
 * 
 *		public abstract void abstractFoo();
 * 
 * 		public abstract void abstractBar(); 
 * }
 * 
 * abstract class B extends A {
 * 
 * 		public static void anotherStaticFoo() { }
 * 
 * 		public void anotherInstanceBar() { }
 * 
 * 		@Override 
 * 		public void abstractBar() { }
 * 
 *      public abstract void qq(); 
 * }
 * 
 * class C extends B { }
 * 
 * Select all methods which should be overridden in the class C.
 * @author SMD_ASY
 *
 */

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

abstract class A {

	public static void staticFoo() {
	}

	public void instanceBar() {
	}

	public abstract void abstractFoo();

	public abstract void abstractBar();
}

abstract class B extends A {

	public static void anotherStaticFoo() {
		System.out.println("It's another static Foo method");
	}

	public void anotherInstanceBar() {
	}

	@Override
	public void abstractBar() {
	}

	public abstract void qq();
}

class C extends B {

	public void qq() {
		System.out.println("It's qq method");
	}

	public void abstractFoo() {
		System.out.println("It's abstractFoo method");
	}

}
