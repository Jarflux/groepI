package be.kdg.groepi.service;

import be.kdg.groepi.model.TripMail;
import be.kdg.groepi.model.User;
import be.kdg.groepi.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
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

            List<User> users = session.createQuery("FROM User user WHERE user.id = :Id").
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

    public static void resetPassword(User user) {
        final int RESET_TIME = 3;

        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(32);
        for (int i = 0; i < 32; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }

        String passwordResetString = sb.toString();

        Calendar cal = Calendar.getInstance();
        //cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR_OF_DAY) + RESET_TIME);

        Timestamp passwordResetTimestamp = new Timestamp(cal.getTime().getTime());

        user.setPasswordResetString(passwordResetString);
        user.setPasswordResetTimestamp(passwordResetTimestamp);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(user);
        tx.commit();

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

        TripMail tim = (TripMail) context.getBean("tripMail");

        //TODO: can't connect to host
        tim.sendMail("info@trippie.be", "vincentjanv@gmail.com", "Subject?",
                "text\n\nHEY MEZELF WAT ZIE JE ER GOED UIT\n" + newPassword);
    }
}
