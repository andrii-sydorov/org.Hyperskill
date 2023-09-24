package DAO.controller;

import java.util.List;

import DAO.models.User;

public interface UserDao {

    List<User> getAll();

    User getById(int id);

    void add(User u);

    void update(User u);

    void deleteById(int id);

}
