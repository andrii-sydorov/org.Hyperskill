package DAO;

import DAO.Impl.UserDAOImpl;
import DAO.controller.UserDao;
import DAO.models.*;

public class MemoryDAO {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        userDAO();
    }

    private static void userDAO() {
        UserDao userDao = new UserDAOImpl();
        userDao.add(new User(0, "Ada"));
        userDao.add(new User(1, "Rob"));

        // print all users
        System.out.println("Printing all users");
        for (User u : userDao.getAll()) {
            System.out.println(u);
        }

        System.out.println("Finding users according their id");
        userDao.getById(0);
        userDao.getById(2);

        System.out.println("Updating user info");
        userDao.update(new User(10, "Andrii"));
        userDao.update(new User(1, "Bob"));

        System.out.println("Deleting user");
        userDao.deleteById(1);
        userDao.deleteById(3);

        System.out.println("Finding user, which was deleted");
        userDao.getById(1);
    }

}
