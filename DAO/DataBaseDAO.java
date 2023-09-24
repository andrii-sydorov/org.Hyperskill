package DAO;

import DAO.Impl.UserDAOImplDataBase;
import DAO.models.User;

public class DataBaseDAO {
    public static void main(String[] args) {
        UserDAOImplDataBase userDao = new UserDAOImplDataBase();
        System.out.println(userDao.getAll());
        userDao.add(new User(0, "Andrii"));
        userDao.add(new User(1, "Andrii"));
        userDao.add(new User(2, "Bob"));
        System.out.println(userDao.getAll());
        userDao.getById(2);
        userDao.deleteById(1);
        System.out.println(userDao.getAll());
        userDao.update(new User(2, "Artyom"));
        System.out.println(userDao.getAll());
        userDao.add(new User(1, "Mikhail"));
        userDao.getById(1);
    }
}
