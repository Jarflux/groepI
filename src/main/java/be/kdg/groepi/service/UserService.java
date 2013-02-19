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
    //TODO: Hibernate transaction management nodig bij GET? (-> Dirty read?)
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

    public static void createUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
    }

    public static void updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(user);
        tx.commit();
    }

    public static void deleteUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(user);
        tx.commit();
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
        /*tim.sendMail("info@trippie.be", "tim.bogaert@student.kdg.be", "Subject?",
                "text\n\nHEY MEZELF WAT ZIE JE ER GOED UIT\n" + newPassword);*/
    }
}
