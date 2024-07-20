package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "sam";
    private static final String PASSWORD = "12345";
    private static SessionFactory sessionFactory = getSessionFactory();

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                Properties settings = new Properties();

                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USERNAME);
                settings.put(Environment.PASS, PASSWORD);

                settings.put(Environment.SHOW_SQL, true);

                sessionFactory = new Configuration()
                        .addAnnotatedClass(User.class)
                        .setProperties(settings)
                        .buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Error is Util");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

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
