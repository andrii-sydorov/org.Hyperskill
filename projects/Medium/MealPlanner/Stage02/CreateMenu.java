package projects.Medium.MealPlanner.Stage02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stage 2/6: Create a menu
 * 
 * $1. Description
 * 
 * One meal is not going to get you far! Let's create the main menu to add
 * several meals and display their properties. For this, we need to add a few
 * commands:
 * 
 * - <b>Add</b> starts the meal input process and prompts users for the meal
 *   properties;
 * - <b>Show</b> prints the list of saved meals with their names, categories,
 *   and ingredients;
 * - After executing <b>add</b> or <b>show</b>, the program should ask what
 *   users want to do next;
 * - The program must run until users input <b>exit</b> — the command that
 *   terminates the program.
 * 
 * In this stage, your program should also check the user input. What if users
 * enter something wrong?
 * 
 * - If the input meal category is something other than <b>breakfast</b>,
 *   <b>lunch</b>, or <b>dinner</b>, print <b>Wrong meal category! Choose from: breakfast, lunch,
 *   dinner.</b> and prompt users for input;
 * - If the meal's name or ingredients have a wrong format (for example, there
 *   are numbers or non-alphabet characters; ingredients are blank, and so on), print
 *   <b>Wrong format. Use letters only!</b> and prompt users for input. Meal's
 *   name or ingredients containing several words like "peanut butter" should not be
 *   matched as wrong format.
 * 
 * $2. Objectives
 * 
 * To complete this stage, the program must comply with the following
 * requirements:
 * 
 * 1. Create an infinite loop of your program that can be terminated with the
 *    <b>exit</b> command only;
 * 2. Prompt users to choose an operation with the message <b>What would you
 *    like to do (add, show, exit)?</b>
 * 3. After the command has been processed, ask for another operation;
 * 4. Make sure that the input and output formats are correct;
 * 5. If users want to <b>add</b> a meal, follow the sequence from the previous
 *   stage. Don't forget to validate input as explained above. Output <b>The meal
 *   has been added!</b> before proceeding;
 * 6. If users want to <b>show</b> the meals when no meals have been added,
 *    print <b>No meals saved. Add a meal first.</b> If there are meals that can be dislayed, print
 *    them in the order they've been added, following the format from Stage 1;
 * 7. Print <b>Bye!</b> and end the program once the <b>exit</b> command is
 *    entered;
 * 8. If users fail to input a valid command, print the following message again:
 *    <b>What would you like to do (add, show, exit)?</b>
 * 
 * $3. Examples
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1: standard execution
 * 
 * What would you like to do (add, show, exit)?
 * > add
 * Which meal do you want to add (breakfast, lunch, dinner)?
 * > lunch
 * Input the meal's name:
 * > salad
 * Input the ingredients:
 * > lettuce, tomato, onion, cheese, olives
 * The meal has been added!
 * What would you like to do (add, show, exit)?
 * > add
 * Which meal do you want to add (breakfast, lunch, dinner)?
 * > breakfast
 * Input the meal's name:
 * > oatmeal
 * Input the ingredients:
 * > oats, milk, banana, peanut butter
 * The meal has been added!
 * What would you like to do (add, show, exit)?
 * > show
 * 
 * Category: lunch
 * Name: salad
 * Ingredients:
 * lettuce
 * tomato
 * onion
 * cheese
 * olives
 * 
 * Category: breakfast
 * Name: oatmeal
 * Ingredients:
 * oats
 * milk
 * banana
 * peanut butter
 * 
 * What would you like to do (add, show, exit)?
 * > exit
 * Bye!
 * 
 * Example 2: invalid input format
 * 
 * What would you like to do (add, show, exit)?
 * > show
 * No meals saved. Add a meal first.
 * What would you like to do (add, show, exit)?
 * > new meal
 * What would you like to do (add, show, exit)?
 * > meal
 * What would you like to do (add, show, exit)?
 * > add
 * Which meal do you want to add (breakfast, lunch, dinner)?
 * > dessert
 * Wrong meal category! Choose from: breakfast, lunch, dinner.
 * > lunch
 * Input the meal's name:
 * > burger1
 * Wrong format. Use letters only!
 * >
 * Wrong format. Use letters only!
 * > soup
 * Input the ingredients:
 * > carrots, ginger, coconut milk, 123
 * Wrong format. Use letters only!
 * > carrots, ginger, coconut milk, curry
 * The meal has been added!
 * What would you like to do (add, show, exit)?
 * > exit
 * Bye!
 * 
 * @author SMD_ASY
 *
 */

public class CreateMenu {

    private static List<Food> meals = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("What would you like to do (add, show, exit)?");
            String option = sc.nextLine();
            switch (option) {
                case "add":
                    addMeal(sc);
                    System.out.println("The meal has been added!");
                    break;
                case "show":
                    showMeals();
                    break;
                case "exit":
                    isRunning = false;
                default:
                    break;
            }

        }
        System.out.println("Bye!");
        sc.close();
    }

    public static void addMeal(Scanner sc) {
        System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");
        Food f = new Food();
        boolean isCategoryAdded = false;
        while (!isCategoryAdded) {
            String dish = sc.nextLine();
            switch (dish) {
                case "breakfast":
                case "lunch":
                case "dinner":
                    f.setCategory(dish);
                    isCategoryAdded = true;
                    break;
                default:
                    System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
                    break;
            }
        }
        System.out.println("Input the meal's name:");
        boolean isNameAdded = false;
        while (!isNameAdded) {
            String name = sc.nextLine();
            if (hasNoDigitsAndNotEmpty(name)) {
                f.setName(name);
                isNameAdded = true;
            }
        }
        System.out.println("Input the ingredients:");
        boolean isIngredientsAdded = false;
        while (!isIngredientsAdded) {
            String[] ingredients = Arrays.stream(sc.nextLine().split(",")).map(String::trim).toArray(String[]::new);
            boolean isIngredientsCorrect = true;
            for (String s : ingredients) {
                if (!hasNoDigitsAndNotEmpty(s)) {
                    isIngredientsCorrect = false;
                    break;
                }
            }
            if (isIngredientsCorrect) {
                f.setIngredients(ingredients);
                isIngredientsAdded = true;
            }
        }
        meals.add(f);
    }

    public static void showMeals() {
        if (meals.size() == 0) {
            System.out.println("No meals saved. Add a meal first.");
            return;
        }
        System.out.println();
        for (Food f : meals) {
            System.out.println(f.toString());
            System.out.println();
        }
    }

    public static boolean hasNoDigitsAndNotEmpty(String name) {
        Pattern p = Pattern.compile("[^a-zA-Z ]");
        Matcher m = p.matcher(name);
        if (m.find() || name.isEmpty()) {
            System.out.println("Wrong format. Use letters only!");
            return false;
        }
        return true;
    }

}

class Food {

    private String category;
    private String name;
    private String[] ingredients;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getListOf() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ingredients.length; i++) {
            if (i < ingredients.length - 1) {
                sb.append(ingredients[i] + "\n");
            } else {
                sb.append(ingredients[i]);
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("Category: %s\nName: %s\nIngredients:\n%s", category, name, getListOf());
    }

}
