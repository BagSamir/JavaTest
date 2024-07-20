package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Anna", "Sokolova", (byte) 35);
        userService.saveUser("Alex", "Lovbo", (byte) 18);
        userService.saveUser("Anton", "Efrevol", (byte) 29);
        userService.saveUser("Kate", "Flover", (byte) 56);

        userService.removeUserById(1);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
