package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Заур","Гизатуллин", (byte) 25);
        userService.saveUser("Наиль","Толибеков", (byte) 22);
        userService.saveUser("Маска","Зорро", (byte) 70);
        userService.saveUser("Дана","Мансурова", (byte) 99);

        userService.removeUserById(3);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
