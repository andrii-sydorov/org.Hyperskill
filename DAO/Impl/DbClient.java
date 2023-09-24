package DAO.Impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.models.User;

public class DbClient {

    String url;

    public DbClient(String url) {
        this.url = url;
    }

    public void run(String str) {
        try (Connection con = DriverManager.getConnection(url);
                Statement st = con.createStatement()) {
            st.executeUpdate(str); // add, delete, update
        } catch (SQLException sql) {
            System.out.println("Connection was not created");
        }
    }

    public User select(String query) {
        List<User> ls = selectForList(query);
        if (ls.size() == 1) {
            return ls.get(0);
        } else if (ls.size() == 0) {
            return null;
        } else {
            throw new IllegalStateException("Query return more than one object");
        }
    }

    public List<User> selectForList(String query) {
        List<User> ls = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                // Retrieve column values
                int id = rs.getInt("id");
                String name = rs.getString("name");
                User user = new User(id, name);
                ls.add(user);
            }
        } catch (SQLException sql) {
            System.out.println("Select query doesn't work");
        }

        return ls;
    }
}
