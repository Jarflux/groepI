package be.kdg.groepi.service;

import be.kdg.groepi.model.Trip;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Trip Service
 * Description:
 */

public class TripService {

    public static Trip getTripById(long Id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Trip> trips = new ArrayList<>();
        try {
            trips = session.createQuery("FROM Trip trip WHERE trip.id = :Id").
                    setString("Id", String.valueOf(Id)).setReadOnly(true).list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
            if (trips.size() > 0) {
                return trips.get(0);
            } else {
                return null;
            }
        }
    }

    public static Trip createTrip(Trip trip) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(trip);
        tx.commit();
        return null;  //TODO: return?!
    }

    public static Trip updateTrip(Trip trip) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(trip);
        tx.commit();
        return null;  //TODO: return?!
    }

    public static Trip deleteTrip(Trip trip) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(trip);
        tx.commit();
        return null;  //TODO: return?!
    }

    public static List<Trip> getAllTrips() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Trip> trips = session.createQuery("FROM Trip").list();
        tx.commit();
        return trips;
    }
}