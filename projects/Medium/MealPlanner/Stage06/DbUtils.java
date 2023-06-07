package projects.Medium.MealPlanner.Stage06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
                    String getDataFromIngredients = String.format(
                            "SELECT ingredient FROM ingredients WHERE meal_id=%d;",
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

    public static Map<Integer, String> getCategory(String category) {
        Map<Integer, String> map = new LinkedHashMap<>();
        String categoryQuery = String.format("SELECT meal_id, meal FROM meals WHERE category = '%s' ORDER BY meal;",
                category);
        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(categoryQuery);
            while (rs.next()) {
                map.put(rs.getInt("meal_id"), rs.getString("meal"));
            }
        } catch (SQLException sqle) {
            System.out.println("Error: get category from meals table");
        }
        return map;
    }

    public static void addFoodToPlan(String day, String category, String option, int plan_id) {
        try (Statement st = con.createStatement()) {
            String saveToPlan = String.format("INSERT INTO plan VALUES('%s','%s', '%s', %d);", day, category, option,
                    plan_id);
            st.executeUpdate(saveToPlan);
        } catch (SQLException sqle) {
            System.out.println("Error by saving data to plan table!");
        }
    }

    public static void deletePlanTable() {
        try (Statement st = con.createStatement()) {
            String deletePlanTable = "DROP TABLE IF EXISTS plan;";
            st.executeUpdate(deletePlanTable);
        } catch (SQLException sqle) {
            System.out.println("Error by deleting plan table");
        }
    }

    public static void createPlanTable() {
        try (Statement st = con.createStatement()) {
            // day = Monday, category = breakfast etc., option = meal, plan_id = meal_id
            String createPlanTABLE = "CREATE TABLE IF NOT EXISTS plan(day TEXT,category VARCHAR(70) NOT NULL, option VARCHAR(70), plan_id INTEGER);";
            st.executeUpdate(createPlanTABLE);
        } catch (SQLException sql) {
            System.out.println("Error: create datatable plan!");
        }
    }

    public static List<String> getPlan(String day) {
        List<String> ls = new ArrayList<>();
        try (Statement st = con.createStatement()) {
            String query = String.format("SELECT category FROM plan WHERE day = '%s';", day);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String category = rs.getString("category");
                String option = rs.getString("option");
                ls.add(Character.toString(Character.toUpperCase(category.charAt(0))) + category.substring(1) + ": "
                        + option);
            }
        } catch (SQLException sqle) {
            System.out.println("Error by getting data from plan table!");
        }
        return ls;
    }

    public static int planTableIsEmpty() {
        int ans = 0;
        try (Statement st = con.createStatement()) {
            String count = "SELECT COUNT(*) FROM plan;";
            ResultSet rs = st.executeQuery(count);
            while (rs.next()) {
                ans = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            System.out.println("Error: define the the size of plan table!");
        }
        return ans;
    }

    public static Map<String, Integer> getListOfIngredients() {
        Map<String, Integer> map = new HashMap<>();
        try (Statement stPlan = con.createStatement()) {
            String getPlanId = "SELECT plan_id FROM plan;";
            ResultSet rsGetPlanId = stPlan.executeQuery(getPlanId);
            while (rsGetPlanId.next()) {
                int plan_id = rsGetPlanId.getInt(1);
                try (Statement stIngred = con.createStatement()) {
                    String getIngred = String.format("SELECT ingredient FROM ingredients WHERE meal_id = '%s'",
                            plan_id);
                    ResultSet rsGetIngred = stIngred.executeQuery(getIngred);
                    while (rsGetIngred.next()) {
                        String ingredient = rsGetIngred.getString(1);
                        map.put(ingredient, map.getOrDefault(ingredient, 0) + 1);
                    }
                }
            }
        } catch (SQLException sqle) {
            System.out.println("Error: get List of ingredients");
        }
        return map;
    }

    public static void closeConnection() {
        try {
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
