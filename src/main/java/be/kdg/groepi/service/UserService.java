package be.kdg.groepi.service;

import be.kdg.groepi.utils.TripMail;
import be.kdg.groepi.model.User;
import be.kdg.groepi.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: User Service
 * Description:
 */
public class UserService {

    public static User getUserById(long Id) {
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            List<User> users = session.createQuery("FROM User user WHERE user.fId = :Id").
                    setString("Id", String.valueOf(Id)).setReadOnly(true).list();
            if (users.size() > 0) {
                user = users.get(0);
            }

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return user;
    }

    public static User getUserByResetString(String resetString) {
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<User> users = session.createQuery("FROM User user WHERE user.fPasswordResetString = :resetString").
                    setString("resetString", resetString).setReadOnly(true).list();
            if (users.size() > 0) {
                user = users.get(0);
            }
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return user;
    }

    public static User getUserByEmail(String email) {
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<User> users = session.createQuery("FROM User user WHERE user.fEmail = :email").
                    setString("email", email).setReadOnly(true).list();
            if (users.size() > 0) {
                user = users.get(0);
            }
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return user;
    }

    public static void createUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.save(user);

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.update(user);

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void deleteUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.delete(user);

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static List<User> getAllUsers() {
        List<User> users = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            users = session.createQuery("FROM User").list();

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return users;
    }

    public static boolean resetPassword(String email) {
        final int RESET_TIME = 3;

        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(32);
        for (int i = 0; i < 32; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }

        String passwordResetString = sb.toString();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR_OF_DAY) + RESET_TIME);

        Timestamp passwordResetTimestamp = new Timestamp(cal.getTime().getTime());

        User user = getUserByEmail(email);

        if (user != null) {

            user.setPasswordResetString(passwordResetString);
            user.setPasswordResetTimestamp(passwordResetTimestamp);

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(user);
            tx.commit();

            ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

            TripMail tim = (TripMail) context.getBean("tripMail");

            //TODO: pas email aan aan die van de gebruiker
            tim.sendMail("info@trippie.be", "info@trippie.be", "Reset password",
                    "Please follow this link to reset your password:\n http://localhost:8080/profile/" +
                            user.getId() + "/" + passwordResetString + "\n\nThis link expires at:\n" + passwordResetTimestamp);

            return true;
        } else {
            return false;
        }
    }
}
