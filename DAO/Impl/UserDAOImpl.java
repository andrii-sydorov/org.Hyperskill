package DAO.Impl;

import java.util.ArrayList;
import java.util.List;

import DAO.controller.UserDao;
import DAO.models.User;

public class UserDAOImpl implements UserDao {

    private final List<User> list;

    public UserDAOImpl() {
        super();
        this.list = new ArrayList<>();
        System.out.println("Users data structure created");
    }

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        List<User> users = new ArrayList<>(list);
        return users;
    }

    @Override
    public User getById(int id) {
        // TODO Auto-generated method stub
        User u = findUser(id);
        if (u == null) {
            System.out.println("User: Id " + id + ", not found");
            return null;
        }
        System.out.println("User: Id " + id + ", found");
        return new User(u.getId(), u.getName());
    }

    @Override
    public void add(User u) {
        // TODO Auto-generated method stub
        list.add(u);
        System.out.println("User: Id " + u.getId() +
                ", name: " + u.getName() + " added");
    }

    @Override
    public void update(User u) {
        // TODO Auto-generated method stub
        User us = findUser(u.getId());
        if (us == null) {
            System.out.println("User: Id " + u.getId() + ", not found");
            return;
        }
        us.setId(u.getId()); // not neceassary, while user was found according id
        us.setName(u.getName());
        System.out.println("User: Id " + u.getId() + ", updated");
    }

    @Override
    public void deleteById(int id) {
        // TODO Auto-generated method stub
        User u = findUser(id);
        if (u == null) {
            System.out.println("User: Id " + id + ", not found");
            return;
        }
        list.remove(u);
        System.out.println("User: Id " + id + ", deleted");
    }

    public User findUser(int id) {
        for (User u : list) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

}
