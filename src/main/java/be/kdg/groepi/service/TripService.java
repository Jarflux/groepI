package be.kdg.groepi.service;

import be.kdg.groepi.model.Trip;
import be.kdg.groepi.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Trip Service
 * Description:
 */

public class TripService {

    public static Trip getTripById(long Id) {
        Trip trip = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            List<Trip> trips = session.createQuery("FROM Trip trip WHERE trip.id = :Id").
                    setString("Id", String.valueOf(Id)).setReadOnly(true).list();
            if (trips.size() > 0) {
                trip = trips.get(0);
            }

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return trip;
    }

    public static void createTrip(Trip trip) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(trip);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void updateTrip(Trip trip) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(trip);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void deleteTrip(Trip trip) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.delete(trip);

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static List<Trip> getAllTrips() {
        List<Trip> trips = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            trips = session.createQuery("FROM Trip").list();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return trips;
    }
}