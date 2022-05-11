package Patterns.Teamplate;

import java.util.Scanner;

/**
 * Now, we'll add a new concrete class Sandwich
 * 
 * Sandwich is different from Steak, so that's why you need to implement it.
 * 
 * The first line of the standard input is the concrete meal.
 * 
 * You must output the meal procedure.
 * 
 * Please do not change the provided code of the classes. Report a typo
 * 
 * Sample Input 1:
 * 
 * Sandwich
 * 
 * Sample Output 1:
 * 
 * Ingredients: bacon, white bread, egg, cheese, mayonnaise, tomato 
 * Paste ingredients between bread slices. Toast sandwich 
 * That's good 
 * Lick fingers and go to sleep
 * 
 * @author SMD_ASY
 *
 */

public class Food {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String order = sc.nextLine();
		sc.close();
		Meal m = null;
		switch (order) {
		case "Sandwich":
			m = new Sandwich();
			break;
		case "Steak":
			m = new Steak();
			break;
		default:
			System.out.println("Error");
			break;
		}
		m.doMeal();
	}

}

abstract class Meal {

	public void doMeal() {
		prepareIngredients();
		cook();
		eat();
		cleanUp();
	}

	public void eat() {
		System.out.println("That's good");
	}

	abstract void cook();

	abstract void prepareIngredients();

	abstract void cleanUp();

}

class Sandwich extends Meal {

	@Override
	void cook() {
		System.out.println("Paste ingredients between bread slices. Toast sandwich");
	}

	@Override
	void prepareIngredients() {
		System.out.println("Ingredients: bacon, white bread, egg, cheese, mayonnaise, tomato");
	}

	@Override
	void cleanUp() {
		System.out.println("Lick fingers and go to sleep");
	}

}

class Steak extends Meal {

	@Override
	void cook() {
		System.out.println("Fry the steak in the pan");
	}

	@Override
	void prepareIngredients() {
		System.out.println("Ingredients: beef steak, lemon, olive oil, salt, sugar");
	}

	@Override
	void cleanUp() {
		System.out.println("Push dishes in the sink and go coding");
	}

}
