package projects.Medium.MealPlanner.Stage01;

import java.util.Scanner;

/**
 * Stage 1/6: Add meals
 * 
 * $1. Description
 * 
 * Let's start with something simple. Write a program that can store meals and
 * their properties. Prompt users about the category of a meal (breakfast,
 * lunch, or dinner), name of a meal, and necessary ingredients. The program
 * should print that information with the meal properties in the correct format.
 * In this stage, you don't need to validate user input.
 * 
 * $2. Objectives
 * 
 * To complete this stage, your program should:
 * 
 * - Ask about the meal category with the following message: Which meal do you
 *   want to add (breakfast, lunch, dinner)?;
 * - Ask about the name of the meal with the message Input the meal's name:;
 * - Inquire about the necessary ingredients with the message Input the
 *   ingredients:. The input contains ingredients in one line separated by commas.
 *   The output displays each ingredient on a new line (see Examples);
 * - Print all the information about the meal in the following format:
 * 
 * Category: category
 * Name: meal's name
 * Ingredients:
 * ingredient 1
 * ingredient 2
 * ingredient 3
 * 
 * - Print the message that the meal is saved successfully: The meal has been
 *   added!.
 *   
 * $3. Examples
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1: standard execution — lunch
 * 
 * Which meal do you want to add (breakfast, lunch, dinner)?
 * > lunch
 * Input the meal's name:
 * > salad
 * Input the ingredients:
 * > lettuce,tomato,onion,cheese,olives
 * 
 * Category: lunch
 * Name: salad
 * Ingredients:
 * lettuce
 * tomato
 * onion
 * cheese
 * olives
 * The meal has been added!
 * 
 * Example 2: standard execution — breakfast
 * 
 * Which meal do you want to add (breakfast, lunch, dinner)?
 * > breakfast
 * Input the meal's name:
 * > oatmeal
 * Input the ingredients:
 * > oats,milk,banana,peanut butter
 * 
 * Category: breakfast
 * Name: oatmeal
 * Ingredients:
 * oats
 * milk
 * banana
 * peanut butter
 * The meal has been added!
 * 
 * @author SMD_ASY
 *
 */

public class AddMeals {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");
        String dish = sc.nextLine();
        Food f = new Food();
        switch (dish) {
            case "breakfast":
            case "lunch":
            case "dinner":
                f.setCategory(dish);
                break;
            default:
                System.out.println("No available category name!");
                return;
        }
        System.out.println("Input the meal's name:");
        String name = sc.nextLine();
        f.setName(name);
        System.out.println("Input the ingredients:");
        String[] ingredients = sc.nextLine().split(",");
        f.setIngredients(ingredients);
        System.out.println();
        System.out.println(f.toString());
        System.out.println("The meal has been added!");
        sc.close();
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
