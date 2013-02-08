package be.kdg.groepi.service;

import be.kdg.groepi.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: User Service
 * Description:
 */

public class UserService {
    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Transaction tx = session.beginTransaction();


    public static User getUserById(long Id) {
        List<User> users = session.createQuery("FROM User user WHERE user.id = :Id").setString("Id", String.valueOf(Id)).list();
        //User user = (User) session.createQuery("FROM User user WHERE user.id = :Id").setString("Id", String.valueOf(Id)).list().get(0);
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    public static User createUser(User user) {
        session.save(user);
        return null;  //TODO: return?!
    }

    public static User updateUser(User user) {
        session.update(user);
        return null;  //TODO: return?!
    }

    public static User deleteUser(User user) {
        session.delete(user);
        return null;  //TODO: return?!
    }

    public static List<User> getAllUsers() {
        /*List<User> users = session.createQuery("FROM User").list();
        return users;*/
        return session.createQuery("FROM User").list();
    }
}
