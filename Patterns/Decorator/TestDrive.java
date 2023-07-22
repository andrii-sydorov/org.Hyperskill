package Patterns.Decorator;

import java.util.Locale;

/**
 * Pizza Decorator
 * This fun task involves Pizza decorating. Imagine that the basic ingredients
 * are used, but you want to add something more.
 * 
 * Sample Input 1:
 * 
 * Sample Output 1:
 * 
 * Vegan $4.99
 * Vegan, Broccoli, Tomato, Spinach $5.27
 * MeatHeaven, Ham, Chicken, Cheese $6.70
 */

class TestDrive {
    public static void main(String[] args) {
        /*
         * Vegan $4.99
         */
        PizzaTestDrive simpleVeganPizza = new Vegan();
        System.out.println(simpleVeganPizza.getDescription() + " $" + formatSum(simpleVeganPizza.cost()));

        /*
         * Vegan, Broccoli, Tomato, Spinach $5.27
         */

        PizzaTestDrive veganPizzaDecor = new Vegan();
        veganPizzaDecor = new Broccoli(veganPizzaDecor);
        veganPizzaDecor = new Tomato(veganPizzaDecor);
        veganPizzaDecor = new Spinach(veganPizzaDecor);
        System.out.println(veganPizzaDecor.getDescription() + " $" + formatSum(veganPizzaDecor.cost()));

        PizzaTestDrive meatPizzaDecor = new MeatHeaven();
        meatPizzaDecor = new Ham(meatPizzaDecor);
        meatPizzaDecor = new Chicken(meatPizzaDecor);
        meatPizzaDecor = new Cheese(meatPizzaDecor);
        System.out.println(meatPizzaDecor.getDescription() + " $" + formatSum(meatPizzaDecor.cost()));

    }

    private static String formatSum(double sum) {
        return String.format(Locale.ROOT, "%.2f", sum);
    }
}

abstract class PizzaTestDrive {
    String description;

    String getDescription() {
        return description;
    }

    abstract double cost();
}

class Vegan extends PizzaTestDrive {

    Vegan() {
        description = "Vegan";
    }

    @Override
    double cost() {
        return 4.99;
    }
}

class MeatHeaven extends PizzaTestDrive {

    MeatHeaven() {
        description = "MeatHeaven";
    }

    @Override
    double cost() {
        return 4.0;
    }
}

abstract class Decorator extends PizzaTestDrive {
    
    PizzaTestDrive pizza;
    
    public Decorator(PizzaTestDrive pizza) {
        this.pizza = pizza;
    }
    abstract String getDescription();
}

class Ham extends Decorator {
    
    Ham(PizzaTestDrive pizza) {
        super(pizza);
    }

    @Override
    String getDescription() {
        return pizza.getDescription() + ", Ham";
    }

    @Override
    double cost() {
        return 1.0 + pizza.cost();
    }
}

class Chicken extends Decorator {

    Chicken(PizzaTestDrive pizza) {
        super(pizza);
    }

    @Override
    String getDescription() {
        return pizza.getDescription() + ", Chicken";
    }

    @Override
    double cost() {
        return 1.5 + pizza.cost();
    }
}

class Cheese extends Decorator {

    Cheese(PizzaTestDrive pizza) {
        super(pizza);
    }

    @Override
    String getDescription() {
        return pizza.getDescription() + ", Cheese";
    }

    @Override
    double cost() {
        return .20 + pizza.cost();
    }
}

class Broccoli extends Decorator {

    Broccoli(PizzaTestDrive pizza) {
        super(pizza);
    }

    @Override
    String getDescription() {
        return pizza.getDescription() + ", Broccoli";
    }

    @Override
    double cost() {
        return .10 + pizza.cost();
    }
}

class Tomato extends Decorator {

    Tomato(PizzaTestDrive pizza) {
        super(pizza);
    }

    @Override
    String getDescription() {
        return pizza.getDescription() + ", Tomato";
    }

    @Override
    double cost() {
        return .09 + pizza.cost();
    }
}

class Spinach extends Decorator {

    Spinach(PizzaTestDrive pizza) {
        super(pizza);
    }

    @Override
    String getDescription() {
        return pizza.getDescription() + ", Spinach";
    }

    @Override
    double cost() {
        return .09 + pizza.cost();
    }
}