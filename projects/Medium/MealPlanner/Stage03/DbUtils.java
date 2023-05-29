package projects.Medium.MealPlanner.Stage03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtils {

    private static Connection con = null;
    private static int meals_id = 1;
    private static int ingredient_id = 1;

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
            String deleteMealsTable = "DROP TABLE IF EXISTS meals;";
            String deleteIngredientsTable = "DROP TABLE IF EXISTS ingredients;";
            st.executeUpdate(deleteMealsTable);
            st.executeUpdate(deleteIngredientsTable);
        } catch (SQLException sql) {
            System.out.println("Error by deleting tables from databes");
        }
    }

    public static void createTables() {
        try (Statement st = con.createStatement()) {
            String createMealsTable = "CREATE TABLE IF NOT EXISTS meals(category VARCHAR(20), meal VARCHAR(50), meal_id Integer);";
            String createIngredientsTABLE = "CREATE TABLE IF NOT EXISTS ingredients(ingredient VARCHAR(70) NOT NULL, ingredient_id INTEGER, meal_id INTEGER);";
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
            String addMeals = String.format("INSERT INTO meals VALUES('%s', '%s', %d);", category, name, meals_id);
            st.executeUpdate(addMeals);
            for (String s : ingredients) {
                String addIngredients = String.format(
                        "INSERT INTO ingredients(ingredient, ingredient_id, meal_id) VALUES('%s', %d, %d)",
                        s,
                        ingredient_id,
                        meals_id);
                st.executeUpdate(addIngredients);
                ingredient_id++;
            }
        } catch (SQLException sqle) {
            System.out.println("The data are not saved in database!");
        }
        meals_id++;
    }

    public static Collection<Food> getMeal() {
        Map<Integer, Food> map = new HashMap<>();
        try (Statement st = con.createStatement()) {
            String getDataFromMealsTable = "SELECT * FROM meals;";
            ResultSet rs = st.executeQuery(getDataFromMealsTable);
            while (rs.next()) {
                String category = rs.getString("category");
                String name = rs.getString("meal");
                int meal_id = rs.getInt("meal_id");
                Food f = new Food();
                f.setCategory(category);
                f.setName(name);
                map.put(meal_id, f);
            }
            for (Map.Entry<Integer, Food> entr : map.entrySet()) {
                Food f = entr.getValue();
                int meal_id = entr.getKey();
                String getDataFromIngredients = String.format("SELECT ingredient FROM ingredients WHERE meal_id=%d",
                        meal_id);
                List<String> ingredients = new ArrayList<>();
                ResultSet rsIngredients = st.executeQuery(getDataFromIngredients);
                while (rsIngredients.next()) {
                    String ingredient = rsIngredients.getString("ingredient");
                    ingredients.add(ingredient);
                }
                String[] ingred = ingredients.toArray(new String[0]);
                f.setIngredients(ingred);
            }
        } catch (SQLException sqle) {
            System.out.println("Cann't read data from database");
        }
        return map.values();
    }

    public static void closeConnection() {
        try {
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
