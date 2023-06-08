package projects.Medium.MealPlanner.Stage06;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <h5>Stage 6/6: Shopping list</h5>
 * 
 * <h6>$1. Description</h6>
 * 
 * <p>
 * Meal planning is only half of the job: we also need to be sure that we have
 * all ingredients! In this stage, the program should generate a shopping list
 * that contains the required ingredients for meals. If several meals require
 * one ingredient, put it in the list only once in the following format:
 * ingredient xN, where N is how many times we need this ingredient.
 * </p>
 * 
 * <p>
 * Finally, let's add the option of saving the list as a file. Add the save
 * command to the menu. Save the shopping list to a file and print an
 * appropriate message. Don't forget to ask users for the file name.
 * </p>
 * 
 * <h6>$2. Objectives</h6>
 * <ol>
 * <li>Add the <b>save</b> option to the menu. This is how the first message
 * should look in
 * this stage: <b>What would you like to do (add, show, plan, save,
 * exit)?</b></li>
 * <li>When users choose plan:</li>
 * <ul>
 * <li>The <b>save</b> option is available only after users have made the plan
 * for the
 * week. If the plan isn't ready, print <b>Unable to save. Plan your meals
 * first.</b>
 * and go back to the menu;</li>
 * <li>Ask users about a filename with the message: <b>Input a
 * filename:</b></li>
 * <li>When the list has been saved, print <b>Saved!</b></li>
 * </ul>
 * </ol>
 * <p>
 * The format of the shopping list is as follows:
 * </p>
 * 
 * <br>
 * eggs<br>
 * tomato x3<br>
 * beef<br>
 * broccoli<br>
 * salmon<br>
 * chicken x2<br>
 * 
 * <p>
 * The ingredients can be stored in any order.
 * </p>
 * 
 * <h6>$3. Examples</h6>
 * 
 * <p>
 * The greater-than symbol followed by a space (<b>></b> ) represents the user
 * input.
 * Note that it's not part of the input.
 * </p>
 * <br>
 * <p>
 * Example 1: the database has a few meals; the plan command has been executed
 * </p>
 * 
 * What would you like to do (add, show, plan, save, exit)?<br>
 * > save<br>
 * Input a filename:<br>
 * > shoppinglist.txt<br>
 * Saved!<br>
 * What would you like to do (add, show, plan, save, exit)?<br>
 * > exit<br>
 * Bye!<br>
 * 
 * <p>
 * Example 2: the database has a few meals; the plan command hasn't been
 * executed
 * </p>
 * 
 * What would you like to do (add, show, plan, save, exit)?<br>
 * > save<br>
 * Unable to save. Plan your meals first.<br>
 * What would you like to do (add, show, plan, save, exit)?<br>
 * > exit<br>
 * Bye!<br>
 * 
 * @author SMD_ASY
 *
 */

public class ShoppingList {

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
            System.out.println("What would you like to do (" + showMenu() + ")?");
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
                    break;
                case "plan":
                    buildPlan(sc);
                    showPlan();
                    break;
                case "save":
                    saveDataToFile(sc);
                    break;
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

    public static String showMenu() {
        String[] menu = Arrays.stream(Menu.values()).map(x -> x.getMenu()).collect(Collectors.toList())
                .toArray(new String[0]);
        return String.join(", ", menu);
    }

    public static String showCategory() {
        String[] category = Arrays.stream(Category.values()).map(x -> x.getCategory()).collect(Collectors.toList())
                .toArray(new String[0]);
        return String.join(", ", category);
    }

    public static void buildPlan(Scanner sc) {
        DbUtils.truncatePlanTable();
        for (Days day : Days.values()) { // days cycle
            System.out.println(day.getDayNames()); // Monday etc
            for (Category c : Category.values()) { // category cycle lunch, breakfast etc
                String category = c.getCategory();
                Map<Integer, String> foods = DbUtils.getCategory(category);
                foods.values().forEach(x -> System.out.println(x));
                System.out.printf("Choose the %s for %s from the list above:\n", category, day.getDayNames());
                String meal = null;
                int plan_id = 0;
                boolean isDone = false;
                while (!isDone) {
                    meal = sc.nextLine();
                    for (Map.Entry<Integer, String> entr : foods.entrySet()) {
                        if (entr.getValue().equals(meal)) {
                            plan_id = entr.getKey();
                            isDone = true;
                            break;
                        }
                    }
                    if (!isDone) {
                        System.out.println("This meal doesn’t exist. Choose a meal from the list above.");
                    }
                }
                // not the best solution, but to have many hashmap to show the plan isn't good!
                // that's why save in database category + meal, day, plan_id
                // category should start with uppercase
                String cat = Character.toString(Character.toUpperCase(category.charAt(0))) + category.substring(1);
                DbUtils.addFoodToPlan(day.getDayNames(), cat, meal, plan_id);
            }
            System.out.printf("Yeah! We planned the meals for %s.\n", day.getDayNames());
            System.out.println();
        }
    }

    public static void showPlan() {
        for (Days d : Days.values()) {
            String day = d.getDayNames();
            List<String> ls = DbUtils.getPlan(day);
            System.out.println(day);
            ls.forEach(x -> System.out.println(x));
            System.out.println();
        }
        // System.out.println();
    }

    public static void saveDataToFile(Scanner sc) {
        if (DbUtils.planTableIsEmpty() == 0) {
            System.out.println("Unable to save. Plan your meals first.");
            return;
        }
        System.out.println("Input a filename:");
        String fileName = sc.nextLine();
        File f = new File(fileName);
        Map<String, Integer> map = DbUtils.getListOfIngredients();
        try (PrintWriter pw = new PrintWriter(f)) {
            for (Map.Entry<String, Integer> entr : map.entrySet()) {
                String first = entr.getKey();
                String second = entr.getValue() == 1 ? "" : String.format(" x%d", entr.getValue());
                pw.println(first + second);
            }
            System.out.println("Saved!");
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: save data to file!");
        }
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

enum Days {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private String dayNames;

    Days(String dayNames) {
        this.dayNames = dayNames;
    }

    public String getDayNames() {
        return this.dayNames;
    }
}

enum Menu {
    ADD("add"),
    SHOW("show"),
    PLAN("plan"),
    SAVE("save"),
    EXIT("exit");

    private String menu;

    Menu(String menu) {
        this.menu = menu;
    }

    public String getMenu() {
        return this.menu;
    }
}

enum Category {
    BREAKFAST("breakfast"),
    LUNCH("lunch"),
    DINNER("dinner");

    private String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }
}
