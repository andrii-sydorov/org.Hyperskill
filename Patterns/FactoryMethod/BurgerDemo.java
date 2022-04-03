package Patterns.FactoryMethod;

/**
 * Burger store In this task, you'll be asked to write almost a full working
 * code by yourself. However, you can find some tips in the correct output and
 * in parts of the code.
 * 
 * Let's create the BurgerStore. We will stick to the good old classic and
 * simply create a burger with Bun, Cutlet and Sauce. Do not forget that
 * FactoryMethod does not include details, it only knows the general creation
 * process.
 * 
 * 
 * Sample Input:
 * 
 * Sample Output: 
 * Making a Chinese Burger 
 * Putting bun 
 * Putting cutlet 
 * Putting sauce 
 * Done a Chinese Burger
 * 
 * Making a American Burger 
 * Putting bun 
 * Putting cutlet 
 * Putting sauce 
 * Done a American Burger
 * 
 * Making a Russian Burger 
 * Putting bun 
 * Putting cutlet 
 * Putting sauce 
 * Done a Russian Burger
 * 
 * @author SMD_ASY
 *
 */

class BurgerDemo {
	public static void main(String[] args) throws InterruptedException {
		/* write your code here */
		BurgerFactory bg = new BurgerStore();
		Burger burgerChinese = bg.orderBurger("chinese");
		Burger burgerAmerican = bg.orderBurger("american");
		Burger burgerRussian = bg.orderBurger("russian");
	}
}

abstract class BurgerFactory {

	abstract Burger createBurger(String type);

	Burger orderBurger(String type) throws InterruptedException {
		Burger burger = createBurger(type);
		if (burger == null) {
			System.out.println("Sorry, we are not able to create this kind of burger\n");
			return null;
		}
		System.out.println("Making a " + burger.getName());
		burger.putBun();
		burger.putCutlet();
		burger.putSauce();
		Thread.sleep(1500L);
		System.out.println("Done a " + burger.getName() + "\n");
		return burger;
	}
}

class BurgerStore extends BurgerFactory {
	// @Override
	Burger createBurger(String type) {
		switch (type) {
		case "american":
			return new AmericanBurger("American Burger");
		case "chinese":
			return new ChineseBurger("Chinese Burger");
		case "russian":
			return new RussianBurger("Russian Burger");
		default:
			return null;
		}
	}
}

abstract class Burger {
	private String name;

	Burger(String name) {
		this.name = name;
	}

	String getName() {
		return name;
	}

	void putBun() {
		System.out.println("Putting bun");
	}

	void putCutlet() {
		System.out.println("Putting cutlet");
	}

	void putSauce() {
		System.out.println("Putting sauce");
	}

}

class ChineseBurger extends Burger {
	/* write your code here */
	public ChineseBurger(String type) {
		super(type);
	}
}

class AmericanBurger extends Burger {
	/* write your code here */
	public AmericanBurger(String type) {
		super(type);
	}
}

class RussianBurger extends Burger {
	/* write your code here */
	public RussianBurger(String type) {
		super(type);
	}
}