package be.kdg.groepi.controller;

import be.kdg.groepi.model.User;
import be.kdg.groepi.persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Gregory
 * Date: 7/02/13
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
public class UserController {

    public static void AddUser()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(new User());
        tx.commit();
    }
}
