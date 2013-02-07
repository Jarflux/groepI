package be.kdg.Controller;

/**
* Author: Ben Oeyen
* Date: 7/02/13
* Class:
* Description:
*/

public class UserController {


//import Model.*;
//import Persistence.HibernateUtil;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import java.util.Date;

//
//public class AankoopOrderController {
//
//    public static AankoopOrder getById(Integer aankoopOrderId) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            Query query = session.createQuery("from AankoopOrder a where a.id = :aankoopOrderId");
//            query.setInteger("aankoopOrderId", aankoopOrderId);
//            query.setReadOnly(true); //deze lijn toevoegen bij alle read only queries zorgt voor speed-up (geen dirty checking)
//            AankoopOrder tmpAankoopOrder = (AankoopOrder) query.uniqueResult();
//            tx.commit();
//            return tmpAankoopOrder;
//        } catch (Exception e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            return null;
//        }
//    }
//
//    public static AankoopOrder getNewAankoopOrder(AankoopPunt aankoopPunt) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//
//        Date tmpDate = new Date();
//        AankoopOrder tmpAankoopOrder = new AankoopOrder(tmpDate, aankoopPunt);
//        session.saveOrUpdate(tmpAankoopOrder);
//        tx.commit();
//        return tmpAankoopOrder;
//    }
//
//    public static AankoopOrder addNewAankoopOrderWithTickets(AankoopPunt aankoopPunt, Voorstelling voorstelling, Tarief tarief, Klant klant, Integer tickets) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        Date tmpDate = new Date();
//        AankoopOrder tmpAankoopOrder = new AankoopOrder(tmpDate, aankoopPunt);
//        session.saveOrUpdate(tmpAankoopOrder);
//        tx.commit();
//        for (Integer i=0; i<tickets;i++)
//        {
//            TicketController.addTicket(tmpAankoopOrder, voorstelling, tarief, klant);
//        }
//        return tmpAankoopOrder;
//    }
}
