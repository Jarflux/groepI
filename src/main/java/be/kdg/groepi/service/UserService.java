package be.kdg.groepi.service;

import be.kdg.groepi.model.TripMail;
import be.kdg.groepi.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
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

            // start work
            List<User> users = new ArrayList<>();
            users = session.createQuery("FROM User user WHERE user.id = :Id").
                    setString("Id", String.valueOf(Id)).setReadOnly(true).list();
            if (users.size() > 0) {
                user = users.get(0);
            }
            // end work 

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
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = session.beginTransaction();
//        session.save(user);
//        tx.commit();

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
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = session.beginTransaction();
//        session.update(user);
//        tx.commit();


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
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = session.beginTransaction();
//        session.delete(user);
//        tx.commit();

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
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = session.beginTransaction();
//        List<User> users = session.createQuery("FROM User").list();
//        tx.commit();
//        return users;

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

    public static void resetPassword(User user) {                    //TODO: mail new password to user
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 16; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }

        String newPassword = sb.toString();

        System.out.println(newPassword);

        user.setPassword(newPassword);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(user);
        tx.commit();

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

        TripMail tim = (TripMail) context.getBean("tripMail");

        //TODO: can't connect to host
        tim.sendMail("info@trippie.be", "info@trippie.be", "Subject?",
                "Je nieuw wachtwoord is:\n" + newPassword);
    }
}
