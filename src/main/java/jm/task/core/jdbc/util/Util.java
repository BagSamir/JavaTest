package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final String USERNAME = "sam";
    private final String PASSWORD = "12345";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection OK");
        } catch (SQLException e) {
            System.out.println("Connection ERROR");
            throw new RuntimeException(e);
        }
        return connection;
    }
}
