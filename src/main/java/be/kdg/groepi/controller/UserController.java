package be.kdg.groepi.controller;

import be.kdg.groepi.model.User;
import be.kdg.groepi.persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserController {
    public static void addUser()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(new User());
        tx.commit();
    }
}
