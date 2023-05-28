package projects.Medium.MealPlanner.Stage03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils {

    private static Connection con = null;
    private static int meals_id = 1;

    public static boolean createConnection(String url, String user, String pass) {
        try {
            con = DriverManager.getConnection(url, user, pass);
            if (con != null) {
                System.out.println("Connection is established!");
            }
            return true;
        } catch (SQLException sql) {
            System.out.println("Connection isn't established!");
        }
        return false;
    }

    public static void deleteTable() {
        try (Statement st = con.createStatement()) {
            String deleteMealsTable = "DELETE TABEL IF EXISTS meals";
            String deleteIngredientsTable = "DELETE TABEL IF EXISTS ingredients";
            st.executeUpdate(deleteMealsTable);
            st.executeUpdate(deleteIngredientsTable);
        } catch (SQLException sql) {
            System.out.println("Error by deleting tables from databes");
        }
    }

    public static void createTables() {
        try (Statement st = con.createStatement()) {
            String createMealsTable = "CREATE TABLE IF NOT EXISTS meals(category VARCHAR(20), meal VRACHAR(50), meal_id Integer;";
            String createIngredientsTABLE = "CREATE TABLE IF NOT EXISTS ingredients(ingredient VARCHAR(70) NOT NULL, ingredient_id SERIAL, meal_id INTEGER";
            st.executeUpdate(createMealsTable);
            st.executeUpdate(createIngredientsTABLE);
        } catch (SQLException sql) {
            System.out.println("No datatable was added!");
        }
    }

    public static void addMeal(Food f) {
        String category = f.getCategory();
        String name = f.getName();
        String[] ingredients = f.getIngredients();
        try (Statement st = con.createStatement()) {
            String addMeals = String.format("INSERT INTO meals VALUES(%s, %s, %d);", category, name, meals_id);
            st.executeUpdate(addMeals);
            for (String s : ingredients) {
                String addIngredients = String.format("INSERT INTO ingredients(ingredient, meal_id) VALUES(%s, %d)", s,
                        meals_id);
                st.executeUpdate(addIngredients);
            }
        } catch (SQLException sqle) {
            System.out.println("The data are not saved in database!");
        }
    }
}
