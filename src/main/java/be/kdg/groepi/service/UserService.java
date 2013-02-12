package be.kdg.groepi.service;

import be.kdg.groepi.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: User Service
 * Description:
 */

public class UserService {

    public static User getUserById(long Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<User> users = new ArrayList<>();
        try {
            users = session.createQuery("FROM User user WHERE user.id = :Id").
                    setString("Id", String.valueOf(Id)).setReadOnly(true).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            if (users.size() > 0) {
                return users.get(0);
            } else {
                return null;
            }
        }
    }

    public static User createUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        return null;  //TODO: return?!
    }

    public static User updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(user);
        tx.commit();
        return null;  //TODO: return?!
    }

    public static User deleteUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(user);
        tx.commit();
        return null;  //TODO: return?!
    }

    public static List<User> getAllUsers() {
        /*List<User> users = session.createQuery("FROM User").list();
        return users;*/
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        tx.commit();
        return users;
    }
}
