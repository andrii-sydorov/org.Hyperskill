package DAO.Impl;

import java.util.ArrayList;
import java.util.List;

import DAO.controller.UserDao;
import DAO.models.User;

public class UserDAOImplDataBase implements UserDao {
    
    private final String DROP_TABLE = "DROP TABLE IF EXISTS INFO;";
    private final String CONNECTION_URL = "jdbc:sqlite:./src/DAO/user.db";
    private final String CREATE_DB = "CREATE TABLE IF NOT EXISTS INFO(id INTEGER PRIMARY KEY, name VARCHAR(20) NOT NULL);";
    private final String SELECT_ALL = "SELECT * FROM INFO;";
    private final String SELECT = "SELECT * FROM INFO WHERE id = %d;";
    private final String INSERT_DATA = "INSERT INTO INFO VALUES(%d, \"%s\");";
    private final String UPDATE_DATA = "UPDATE INFO SET name = \"%s\" WHERE id = %d;";
    private final String DELETE_DATA = "DELETE FROM INFO WHERE id = %d;";

    private final DbClient dbClient;

    public UserDAOImplDataBase() {
        super();
        dbClient = new DbClient(CONNECTION_URL);
        dbClient.run(DROP_TABLE);
        dbClient.run(CREATE_DB);
        System.out.println("Users data structure create");
    }

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        return new ArrayList<>(dbClient.selectForList(SELECT_ALL));
    }

    @Override
    public User getById(int id) {
        // TODO Auto-generated method stub
        User u = dbClient.select(String.format(SELECT, id));
        if (u != null) {
            System.out.println("User: Id " + id + ", found");
            return u;
        } else {
            System.out.println("User: Id " + id + ", not found");
            return null;
        }
    }

    @Override
    public void add(User u) {
        // TODO Auto-generated method stub
        dbClient.run(String.format(INSERT_DATA, u.getId(), u.getName()));
        System.out.println("User: Id " + u.getId() +
                ", name: " + u.getName() + " added");
    }

    @Override
    public void update(User u) {
        // TODO Auto-generated method stub
        dbClient.run(String.format(UPDATE_DATA, u.getName(), u.getId()));
    }

    @Override
    public void deleteById(int id) {
        dbClient.run(String.format(DELETE_DATA, id));
    }

}
