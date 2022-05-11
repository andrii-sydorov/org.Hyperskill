package Patterns.Teamplate;

import java.util.Scanner;

/**
 * There are two classes: an abstract class Meal with the template method and a
 * concrete class Steak
 * 
 * Every meal is different, but the common algorithm is known.
 * 
 * You must implement the template method in Meal class to cook a meal with the
 * following approach:
 * 
 * Prepare the ingredients; Cook the meal; Enjoy the meal; Wash the dishes after
 * the meal.
 * 
 * Please, do not change the provided code of the classes. Report a typo
 * 
 * Sample Input 1:
 * 
 * Mike
 * 
 * Sample Output 1:
 * 
 * Mike wants to eat 
 * Mike decides to cook meal 
 * Ingredients: beef steak, lemon, olive oil, salt, sugar 
 * Fry the steak in the pan 
 * That's good 
 * Push dishes in the sink and go coding
 * 
 * @author SMD_ASY
 *
 */

public class PeopleFood {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String author = sc.nextLine();
		sc.close();
		newMeal m = new newSteak();
		System.out.println(author + " wants to eat");
		System.out.println(author + " decides to cook meal");
		m.doMeal();
	}

}

abstract class newMeal {

	public void doMeal() {
		prepareIngredients();
		cook();
		eat();
		cleanUp();
	}

	public abstract void prepareIngredients();

	public abstract void cook();

	public void eat() {
		System.out.println("That's good");
	}

	public abstract void cleanUp();
}

class newSteak extends newMeal {

	@Override
	public void prepareIngredients() {
		System.out.println("Ingredients: beef steak, lemon, olive oil, salt, sugar");
	}

	@Override
	public void cook() {
		System.out.println("Fry the steak in the pan");
	}

	@Override
	public void cleanUp() {
		System.out.println("Push dishes in the sink and go coding");
	}
}
