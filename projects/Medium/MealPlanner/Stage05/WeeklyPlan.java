package projects.Medium.MealPlanner.Stage05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <h6>Stage 5/6: Weekly plan</h6>
 * 
 * <h5>$1. Description</h5>
 * 
 * <p>A solid person plans for the week ahead! Let's help our users plan their
 * meals for the entire week. In this stage, we will add a new command — <b>plan</b>.
 * We also need to change the main menu accordingly. From now on, it should read
 * as <b>What would you like to do (add, show, plan, exit)?</b></p>
 * 
 * <p>When users input <b>plan</b>, the program should print the first day of the week,
 * <b>Monday</b>, and print the list of all breakfasts stored in the database in
 * alphabetical order. After this, the program should ask users to pick a meal
 * with the following message: <b>Choose the breakfast for Monday from the list
 * above:</b></p>
 * 
 * <p>After users input a meal option, the program should verify it. If it's not
 * stored in the database, print <b>This meal doesn’t exist. Choose a meal from the
 * list above.</b> If the input is correct, move on to the next category — <b>lunch</b> and
 * then to <b>dinner</b>. Once the meals for three categories are picked, print <b>Yeah!
 * We planned the meals for Monday.</b> Repeat these steps for other weekdays. In
 * the end, print the whole plan for the week.</p>
 * 
 * <p>Save the plan to the database. For this purpose, create a new table named
 * <b>plan</b> when the program starts. This table contains the meal option, meal
 * category, and <b>meal_id</b>. The third column must match the <b>meal_id</b> columns of the
 * other two tables. You are free to choose how to implement the fields in this
 * table. If a new plan is created, delete the old plan.</p>
 * 
 * <h5>$2. Objectives</h5>
 * <ol>
 * <li>Create a table in the database named <b>plan</b>;</li>
 * <li>Add the <b>plan</b> option to the menu;</li>
 * <li>When users choose the <b>plan</b> option:</li>
 * <ul>
 * <li> Print <b>Monday</b>;</li>
 * <li> Print the meal names of the breakfast category in alphabetical order;</li>
 * <li> Prompt <b>Choose the breakfast for Monday from the list above:</b></li>
 * <li> Once users input a meal, print the meal names of the lunch category in
 *      alphabetical order;</li>
 * <li> Prompt <b>Choose the lunch for Monday from the list above:</b></li>
 * <li> Once users input a meal, print the meal names of the dinner category in
 *      alphabetical order;</li>
 * <li> Prompt <b>Choose the dinner for Monday from the list above:</b></li>
 * <li> Once users input a meal, print <b>Yeah! We planned the meals for Monday.</b></li>
 * <li> If a meal option isn't in the provided list, print <b>This meal doesn’t exist. Choose a meal from the list above.</b>;</li>
 * <li> Print a blank line and repeat for the rest of the week;</li>
 * <li> Once the plan for the week is drawn, print it. The plan print format is as follows:</li>
 * </ul>
 * </ol>
 * 
 * <p>
 * Monday<br>
 * Breakfast: [meal's name]<br>
 * Lunch: [meal's name]<br>
 * Dinner: [meal's name]<br>
 * <br>
 * Tuesday etc.<br></p>
 * 
 * <ul>
 * <li> Save the plan data in the <b>plan</b> table. Overwrite the old plan every time a new
 *   plan is created.</li></ul>
 *   
 * <h5>$3. Example</h5>
 * 
 * <p>The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.</p>
 * 
 * <p>The example below assumes that the database stores a few meals.</p>
 * 
 * <p>Example 1: planning for the week</p>
 * 
 * What would you like to do (add, show, plan, exit)?<br>
 * > plan<br>
 * Monday<br>
 * oatmeal<br>
 * sandwich<br>
 * scrambled eggs<br>
 * yogurt<br>
 * Choose the breakfast for Monday from the list above:<br>
 * > yogurt<br>
 * avocado egg salad<br>
 * chicken salad<br>
 * sushi<br>
 * tomato salad<br>
 * wraps<br>
 * Choose the lunch for Monday from the list above:<br>
 * > tomato salad<br>
 * beef with broccoli<br>
 * pesto chicken<br>
 * pizza<br>
 * ramen<br>
 * tomato soup<br>
 * Choose the dinner for Monday from the list above:<br>
 * > spaghetti<br>
 * This meal doesn’t exist. Choose a meal from the list above.<br>
 * > ramen<br>
 * Yeah! We planned the meals for Monday.<br>
 * <br>
 * Tuesday<br>
 * oatmeal<br>
 * sandwich<br>
 * scrambled eggs<br>
 * yogurt<br>
 * Choose the breakfast for Tuesday from the list above:<br>
 * > oatmeal<br>
 * avocado egg salad<br>
 * chicken salad<br>
 * sushi<br>
 * tomato salad<br>
 * wraps<br>
 * Choose the lunch for Tuesday from the list above:<br>
 * > wraps<br>
 * beef with broccoli<br>
 * pesto chicken<br>
 * pizza<br>
 * ramen<br>
 * tomato soup<br>
 * Choose the dinner for Tuesday from the list above:<br>
 * > ramen<br>
 * Yeah! We planned the meals for Tuesday.<br>
 * <br>
 * Wednesday<br>
 * oatmeal<br>
 * sandwich<br>
 * scrambled eggs<br>
 * yogurt<br>
 * Choose the breakfast for Wednesday from the list above:<br>
 * > sandwich<br>
 * avocado egg salad<br>
 * chicken salad<br>
 * sushi<br>
 * tomato salad<br>
 * wraps<br>
 * Choose the lunch for Wednesday from the list above:<br>
 * > avocado egg salad<br>
 * beef with broccoli<br>
 * pesto chicken<br>
 * pizza<br>
 * ramen<br>
 * tomato soup<br>
 * Choose the dinner for Wednesday from the list above:<br>
 * > pesto chicken<br>
 * Yeah! We planned the meals for Wednesday.<br>
 * <br>
 * Thursday<br>
 * oatmeal<br>
 * sandwich<br>
 * scrambled eggs<br>
 * yogurt<br>
 * Choose the breakfast for Thursday from the list above:<br>
 * > oatmeal<br>
 * avocado egg salad<br>
 * chicken salad<br>
 * sushi<br>
 * tomato salad<br>
 * wraps<br>
 * Choose the lunch for Thursday from the list above:<br>
 * > chicken salad<br>
 * beef with broccoli<br>
 * pesto chicken<br>
 * pizza<br>
 * ramen<br>
 * tomato soup<br>
 * Choose the dinner for Thursday from the list above:<br>
 * > tomato soup<br>
 * Yeah! We planned the meals for Thursday.<br>
 * <br>
 * Friday<br>
 * oatmeal<br>
 * sandwich<br>
 * scrambled eggs<br>
 * yogurt<br>
 * Choose the breakfast for Friday from the list above:<br>
 * > yogurt<br>
 * avocado egg salad<br>
 * chicken salad<br>
 * sushi<br>
 * tomato salad<br>
 * wraps<br>
 * Choose the lunch for Friday from the list above:<br>
 * > sushi<br>
 * beef with broccoli<br>
 * pesto chicken<br>
 * pizza<br>
 * ramen<br>
 * tomato soup<br>
 * Choose the dinner for Friday from the list above:<br>
 * > pizza<br>
 * Yeah! We planned the meals for Friday.<br>
 * <br>
 * Saturday<br>
 * oatmeal<br>
 * sandwich<br>
 * scrambled eggs<br>
 * yogurt<br>
 * Choose the breakfast for Saturday from the list above:<br>
 * > scrambled eggs<br>
 * avocado egg salad<br>
 * chicken salad<br>
 * sushi<br>
 * tomato salad<br>
 * wraps<br>
 * Choose the lunch for Saturday from the list above:<br>
 * > wraps<br>
 * beef with broccoli<br>
 * pesto chicken<br>
 * pizza<br>
 * ramen<br>
 * tomato soup<br>
 * Choose the dinner for Saturday from the list above:<br>
 * > pesto chicken<br>
 * Yeah! We planned the meals for Saturday.<br>
 * <br>
 * Sunday<br>
 * oatmeal<br>
 * sandwich<br>
 * scrambled eggs<br>
 * yogurt<br>
 * Choose the breakfast for Sunday from the list above:<br>
 * > scrambled eggs<br>
 * avocado egg salad<br>
 * chicken salad<br>
 * sushi<br>
 * tomato salad<br>
 * wraps<br>
 * Choose the lunch for Sunday from the list above:<br>
 * > tomato salad<br>
 * beef with broccoli<br>
 * pesto chicken<br>
 * pizza<br>
 * ramen<br>
 * tomato soup<br>
 * Choose the dinner for Sunday from the list above:<br>
 * > beef with broccoli<br>
 * Yeah! We planned the meals for Sunday.<br>
 * <br>
 * Monday<br>
 * Breakfast: yogurt<br>
 * Lunch: tomato salad<br>
 * Dinner: ramen<br>
 * <br>
 * Tuesday<br>
 * Breakfast: oatmeal<br>
 * Lunch: wraps<br>
 * Dinner: ramen<br>
 * <br>
 * Wednesday<br>
 * Breakfast: sandwich<br>
 * Lunch: avocado egg salad<br>
 * Dinner: pesto chicken<br>
 * <br>
 * Thursday<br>
 * Breakfast: oatmeal<br>
 * Lunch: chicken salad<br>
 * Dinner: tomato soup<br>
 * <br>
 * Friday<br>
 * Breakfast: yogurt<br>
 * Lunch: sushi<br>
 * Dinner: pizza<br>
 * <br>
 * Saturday<br>
 * Breakfast: scrambled eggs<br>
 * Lunch: wraps<br>
 * Dinner: pesto chicken<br>
 * <br>
 * Sunday<br>
 * Breakfast: scrambled eggs<br>
 * Lunch: tomato salad<br>
 * Dinner: beef with broccoli<br>
 * <br>
 * What would you like to do (add, show, plan, exit)?<br>
 * > exit<br>
 * Bye!<br>
 * 
 * @author SMD_ASY
 *
 */

public class WeeklyPlan {

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
        DbUtils.deletePlanTable();
        DbUtils.createPlanTable();
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
                DbUtils.addFoodToPlan(cat + ": " + meal, day.getDayNames(), plan_id);
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
