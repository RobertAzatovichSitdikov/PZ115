package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Заур","Гизатуллин", (byte) 25);
        userDao.saveUser("Наиль","Толибеков", (byte) 22);
        userDao.saveUser("Маска","Зорро", (byte) 70);
        userDao.saveUser("Алибек","Алибеков", (byte) 35);
        userDao.saveUser("Дана","Мансурова", (byte) 99);

        userDao.removeUserById(3);

        userDao.getAllUsers();

        userDao.cleanUsersTable();

        userDao.dropUsersTable();
    }
}
