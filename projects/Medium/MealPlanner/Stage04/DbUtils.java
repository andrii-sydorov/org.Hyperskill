package projects.Medium.MealPlanner.Stage04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DbUtils {

    private static Connection con = null;

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
        int meals_id = getMealsId() + 1;
        int ingredient_id = getIngredientId() + 1;
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
    }

    public static int getMealsId() {
        String getMealsId = "SELECT MAX(meal_id) FROM meals;";
        int meals_id = 0;
        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(getMealsId);
            while (rs.next()) {
                meals_id = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            System.out.println("Getting the max value of meals_id");
        }
        return meals_id;
    }
    
    public static int getIngredientId() {
        String getIngredientsId = "SELECT MAX(ingredient_id) FROM ingredients;";
        int ingredient_id = 0;
        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(getIngredientsId);
            while (rs.next()) {
                ingredient_id = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            System.out.println("Getting the max value of ingredient_id");
        }
        return ingredient_id;
    }

    public static Collection<Food> getMeal(String category) {
        List<Food> ls = new ArrayList<>();
        try (Statement st = con.createStatement()) {
            String getDataFromMealsTable = String.format("SELECT * FROM meals WHERE category = '%s';", category);
            ResultSet rs = st.executeQuery(getDataFromMealsTable);
            while (rs.next()) {
                String name = rs.getString("meal");
                int meal_id = rs.getInt("meal_id");
                Food f = new Food();
                f.setCategory(category);
                f.setName(name);
                try (Statement stInner = con.createStatement()) {
                    String getDataFromIngredients = String.format("SELECT ingredient FROM ingredients WHERE meal_id=%d",
                            meal_id);
                    ResultSet rsInner = stInner.executeQuery(getDataFromIngredients);
                    List<String> ingredients = new ArrayList<>();
                    while (rsInner.next()) {
                        String ingredient = rsInner.getString("ingredient");
                        ingredients.add(ingredient);
                    }
                    String[] ingred = ingredients.toArray(new String[0]);
                    f.setIngredients(ingred);
                    ls.add(f);
                } catch (SQLException sqle) {
                    System.out.println("Some problems!");
                }
            }
        } catch (SQLException sqle) {
            System.out.println("Cann't read data from database");
        }
        return ls;
    }

    public static void closeConnection() {
        try {
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
