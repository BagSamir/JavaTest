package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Transaction transaction = null;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(
                    "CREATE TABLE IF NOT EXISTS users " +
                            "(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                            "name VARCHAR(64), last_name VARCHAR(64), " +
                            "age INT)"
            ).addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error is create ---->" + e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error is drop---->" + e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error is save---->" + e);
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(session.find(User.class, id));
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error is remove ---->" + e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()){
            Query query = session.createSQLQuery("select * from users").addEntity(User.class);

            return (List<User>) query.list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error is clean ---->" + e);
        }
    }
}
