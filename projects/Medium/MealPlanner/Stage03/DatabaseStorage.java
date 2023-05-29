package projects.Medium.MealPlanner.Stage03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stage 3/6: Database storage
 * 
 * $1. Description
 * 
 * At this point, when we close our program, we lose all our stored meals! Let's
 * improve our planner and connect a database to retrieve all meals after a
 * restart.
 * 
 * To connect a database to our project, we can use Java DataBase Connectivity
 * (JDBC), an API for database-independent connectivity between programs and
 * various databases. This standard ensures the same methods for connecting,
 * updating, querying, and results handling, regardless of the database you
 * employ. However, the choice of the database affects the SQL syntax, available
 * data types, and supported features.
 * 
 * In this project, we will refer to PostgreSQL. It is a powerful, open source
 * object-relational database system that contains great capabilities.
 * PostgreSQL runs on all major operating systems and supports advanced data
 * types such as arrays, hstore, and user-defined types. Although, there may be
 * some differences between different SQL databases. You can find more
 * information in the official PostgreSQL documentation
 * 
 * In this stage, we're going to store meal data in database tables. When the
 * program restarts, the saved data should be available in the program.
 * 
 * To use PostgreSQL in Java, you need to import the postgresql-jdbc library
 * into your project. For the Meal Planner, this is already done. You can find
 * an example of this driver on the postgresql-jdbc website.
 * 
 * import java.sql.*
 * 
 * public class Main {
 *   public static void main(String[] argv) throws SQLException {
 *    String DB_URL = "jdbc:postgresql:person_db";
 *    String USER = "postgres";
 *    String PASS = "1111";
 * 
 *    Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
 *    connection.setAutoCommit(true);
 * 
 *    Statement statement = connection.createStatement();
 *    statement.executeUpdate("drop table if exists person");
 *    statement.executeUpdate("create table person (" + "id integer," + "name varchar(1024) NOT NULL" + ")");
 *    statement.executeUpdate("insert into person (id, name) values (1, 'leo')");
 *    statement.executeUpdate("insert into person (id, name) values (2, 'yui')");
 * 
 *    ResultSet rs = statement.executeQuery("select * from person"); // Read the result set
 *    while (rs.next()) {
 *      System.out.println("name = " + rs.getString("name"));
 *      System.out.println("id = " + rs.getInt("id"));
 *    }
 *    statement.close();
 *    connection.close();
 *   }
 * }
 * 
 * Mind that the nested <b>resultset</b> requires different <b>statement</b> instances.
 * 
 * The <b>jdbc:postgresql:person_db</b> string includes three strings divided by
 * semicolons. The first one is the database interface, the second is the
 * database, and the third is the name of your database.
 * 
 * It's a good idea to use pgAdmin — a nice GUI tool for browsing and managing
 * PostgreSQL databases. You can inspect the tables you've created and the data
 * in your database.
 * 
 * If you are connected to the database file when you check your code, it may
 * lead to issues.
 * Make sure to create meals_db database and add postgres user with 1111
 * password to it before initializing tests.
 * 
 * $2. Objectives
 * 
 * 1. Your program should connect to a database named <b>meals_db</b>;
 * 2. Create two tables in this database schema. Name the first one as <b>meals</b> with
 *    three columns: <b>category</b> (varchar), <b>meal</b> (varchar), and <b>meal_id</b> (integer).
 *    Name the second table <b>ingredients</b>; it must contain three columns: <b>ingredient</b>
 *    (varchar), <b>ingredient_id</b> (integer), and <b>meal_id</b> (integer). <b>meal_id</b> in both
 *    tables must match!
 * 3. Read all data in the tables, so their contents are available before a <b>show</b>
 *    operation is requested;
 * 4. When users <b>add</b> a new meal, store it in your database.
 * 
 * There are no changes in the input/output structure in this stage.
 * 
 * Note: The contents of the database tables are cleared at the beginning of the
 * testing process.
 * 
 * $3. Example
 * 
 * The greater-than symbol followed by a space (> ) represents the user input.
 * Note that it's not part of the input.
 * 
 * Example 1: standard execution and a restart
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
 * @author SMD_ASY
 *
 */

public class DatabaseStorage {

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
                    showMeals();
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

    public static void showMeals() {
        List<Food> meals = new ArrayList<>(DbUtils.getMeal());
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
