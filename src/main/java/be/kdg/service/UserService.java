package be.kdg.service;

import be.kdg.model.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: User Service
 * Description:
 */
public class UserService {

    public static User getUserById(long userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from User u where u.id = :userId");
            query.setLong("userId", userId);
            query.setReadOnly(true);
            User tmpUser = (User) query.uniqueResult();
            tx.commit();
            return tmpUser;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return null;
        }
    }

    public static User createUser() {
        return null;
    }

    public static User updateUser(User user) {
        return null;
    }

    public static User deleteUser(User user) {
        return null;
    }

    public static List<User> getAllUsers() {
        return null;
    }
}
