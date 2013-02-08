package be.kdg.groepi.service;

import be.kdg.groepi.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

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

//    private final Session session = HibernateUtil.getSessionFactory().openSession();
    //private final UserService userService = new UserService();

//    @Autowired
//    private static SessionFactory sessionFactory;

    public static User getUserById(long Id) {
        User user = (User) session.createQuery("FROM User user WHERE user.id = :Id").setString("Id", String.valueOf(Id)).list().get(0);
        return user;
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
        return null;
    }

    public static List<User> getAllUsers() {
        return null;
    }
}
