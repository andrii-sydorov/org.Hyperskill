package projects.Medium.MealPlanner.Stage04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stage 4/6: Show saved meals
 * 
 * $1. Description
 * 
 * Let's improve the navigation in the program and make it more user-friendly!
 * In this stage, we will enhance the show command. The program will ask users
 * to specify the meal category with the following message: Which category do
 * you want to print (breakfast, lunch, dinner)? After this, the program will
 * search through the database and print only the chosen category. If the
 * requested category is empty, the program should show an appropriate message.
 * 
 * $2. Objectives
 * 
 * When users input show, your program should:
 * 
 * - Ask users for the meal category;
 * - Search through the database for meals from the chosen category;
 * - Print Category: <category>. For each meal, print Meal's name: <meal name>,
 *   followed by the specific meal ingredients list, each on a new line. The meals
 *   and ingredients should be printed in the same order they've been added;
 * - If the input category doesn't exist, print Wrong meal category! Choose from:
 *   breakfast, lunch, dinner.;
 * - If there're no meals in the category, print No meals found.
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
 * > lunch
 * Input the meal's name:
 * > omelette
 * Input the ingredients:
 * > eggs, milk, cheese
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
 * Which category do you want to print (breakfast, lunch, dinner)?
 * > breakfast
 * Category: breakfast
 * Name: oatmeal
 * Ingredients:
 * oats
 * milk
 * banana
 * peanut butter
 * What would you like to do (add, show, exit)?
 * > show
 * Which category do you want to print (breakfast, lunch, dinner)?
 * > lunch
 * Category: lunch
 * 
 * Name: salad
 * Ingredients:
 * lettuce
 * tomato
 * onion
 * cheese
 * olives
 * 
 * Name: omelette
 * Ingredients:
 * eggs
 * milk
 * cheese
 * 
 * What would you like to do (add, show, exit)?
 * > exit
 * Bye!
 * Example 2: warnings during execution
 * 
 * What would you like to do (add, show, exit)?
 * > show
 * Which category do you want to print (breakfast, lunch, dinner)?
 * > dinner
 * No meals found.
 * What would you like to do (add, show, exit)?
 * > show
 * Which category do you want to print (breakfast, lunch, dinner)?
 * > brunch
 * Wrong meal category! Choose from: breakfast, lunch, dinner.
 * > dinner
 * No meals found.
 * What would you like to do (add, show, exit)?
 * > exit
 * Bye!
 * 
 * @author SMD_ASY
 *
 */

public class ShowSavedMeals {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        String url = "jdbc:postgresql:meals_db";
        String user = "postgres";
        String pass = "1111";
        if (!DbUtils.createConnection(url, user, pass)) {
            System.out.println("No connection established!");
        }
        // DbUtils.deleteTable();
        DbUtils.createTables();
        while (isRunning) {
            System.out.println("What would you like to do (add, show, exit)?");
            String option = sc.nextLine();
            switch (option) {
                case "add":
                    addMeal(sc);
                    System.out.println("The meal has been added!");
                    break;
                case "show":
                    showMeals(sc);
                    break;
                case "exit":
                    isRunning = false;
                default:
                    break;
            }

        }
        DbUtils.closeConnection();
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
        DbUtils.addMeal(f);
    }

    public static void showMeals(Scanner sc) {
        System.out.println("Which category do you want to print (breakfast, lunch, dinner)?");
        String category = null;
        List<Food> meals = null;
        boolean showCategory = false;
        while (!showCategory) {
            category = sc.nextLine();
            switch (category) {
                case "breakfast":
                case "lunch":
                case "dinner":
                    showCategory = true;
                    break;
                default:
                    System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
                    break;
            }
        }
        meals = new ArrayList<>(DbUtils.getMeal(category));
        if (meals.size() == 0) {
            System.out.println("No meals found.");
            return;
        }
        System.out.println("Category: " + category);
        System.out.println();
        for (Food f : meals) {
            System.out.println("Name: " + f.getName());
            System.out.println("Ingredients:");
            for (String s : f.getIngredients()) {
                System.out.println(s);
            }
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
