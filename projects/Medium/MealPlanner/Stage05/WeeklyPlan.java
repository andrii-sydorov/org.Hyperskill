package projects.Medium.MealPlanner.Stage05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
                System.out.printf("Choose the breakfast for %s from the list above:\n", category);
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
        //System.out.println();
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
