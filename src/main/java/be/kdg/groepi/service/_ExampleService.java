package be.kdg.groepi.service;

//package be.kdg.Service;

/**
 * Author: Ben Oeyen
 * Date: 7/02/13
 * Class:  Example Ticket Service DOA
 * Description:  Example of a service to handle tickets
 */

//public class _ExampleService {

//    public static Ticket getById(Integer ticketId) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            Query query = session.createQuery("from Ticket t where t.id = :ticketId");
//            query.setInteger("ticketId", ticketId);
//            query.setReadOnly(true);
//            Ticket tmpTicket = (Ticket) query.uniqueResult();
//            tx.commit();
//            return tmpTicket;
//        } catch (Exception e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            return null;
//        }
//    }
//
//
//    public static void addTicket(AankoopOrder aankoopOrder, Voorstelling voorstelling, Tarief tarief) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//
//        Ticket tmpTicket = new Ticket(aankoopOrder, voorstelling, tarief);
//        aankoopOrder.addTicket(tmpTicket);
//        session.saveOrUpdate(tmpTicket);
//        session.saveOrUpdate(aankoopOrder);
//        tx.commit();
//    }
//
//    public static List<Ticket> getByAankoopOrderId(Integer orderId) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//        Query query = session.createQuery("from Ticket t where t.aankoopOrder.id = :orderId");
//        query.setInteger("orderId", orderId);
//        query.setReadOnly(true);
//        List<Ticket> tickets = query.list();
//        tx.commit();
//        return tickets;
//    }
//
//    public static void addTicket(AankoopOrder aankoopOrder, Voorstelling voorstelling, Tarief tarief, Zetel zetel) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//
//        session.saveOrUpdate(new Ticket(aankoopOrder, voorstelling, tarief,zetel));
//        tx.commit();
//    }
//
//    public static void addTicket(AankoopOrder aankoopOrder, Voorstelling voorstelling, Tarief tarief, Klant klant) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//
//        session.saveOrUpdate(new Ticket(aankoopOrder, voorstelling, tarief, klant));
//        tx.commit();
//    }
//
//    public static void addTicket(AankoopOrder aankoopOrder, Voorstelling voorstelling, Tarief tarief, Zetel zetel, Klant klant) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction tx = session.beginTransaction();
//
//        session.saveOrUpdate(new Ticket(aankoopOrder, voorstelling, tarief, klant, zetel));
//        tx.commit();
//    }
//}
