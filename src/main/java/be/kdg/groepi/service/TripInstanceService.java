package be.kdg.groepi.service;

import be.kdg.groepi.model.TripInstance;
import be.kdg.groepi.model.User;
import be.kdg.groepi.utils.HibernateUtil;
import org.hibernate.Query;
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

public class TripInstanceService {

    public static TripInstance getTripInstanceById(long Id) {
        TripInstance tripinstance = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            List<TripInstance> tripinstances = session.createQuery("FROM TripInstance tripinstance WHERE tripinstance.fId = :Id").
                    setString("Id", String.valueOf(Id)).setReadOnly(true).list();
            if (tripinstances.size() > 0) {
                tripinstance = tripinstances.get(0);
            }

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return tripinstance;
    }

    public static void createTripInstance(TripInstance tripinstance) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(tripinstance);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void updateTripInstance(TripInstance tripinstance) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(tripinstance);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void deleteTripInstance(TripInstance tripinstance) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.delete(tripinstance);

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static List<TripInstance> getAllTripInstances() {
        List<TripInstance> tripinstances = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            tripinstances = session.createQuery("FROM TripInstance tripinstance").list();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return tripinstances;
    }

    public static List<TripInstance> getAllTripInstancesByTripId(long tripId) {
        List<TripInstance> tripinstances = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            tripinstances = session.createQuery("FROM TripInstance tripinstance WHERE tripinstance.fTrip = :tripId").
                    setString("tripId", String.valueOf(tripId)).
                    setReadOnly(true).
                    list();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return tripinstances;
    }

    public static List<TripInstance> getTripInstancesByOrganiserId(long id) {
        List<TripInstance> tripInstances = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from TripInstance tripInstance where tripInstance.fOrganiser = :Organiser").
                    setLong("Organiser", id);
            tripInstances = (List<TripInstance>) query.list();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return tripInstances;
    }

    public static List<TripInstance> getPublicTripInstances() {
        List<TripInstance> tripinstances = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from TripInstance tripinstance where tripinstance.fAvailable = :Availability").
                    setInteger("Availability", 1);
            tripinstances = (List<TripInstance>) query.list();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return tripinstances;
    }

    public static List<TripInstance> getTripInstancesByUserId(Long userId) {
        List<TripInstance> trips = new ArrayList<>();
        List<TripInstance> tripInstances = TripInstanceService.getAllTripInstances();
        for (TripInstance tripInstance : tripInstances) {
            for (User participant : tripInstance.getParticipants()) {
                if (userId == participant.getId()) {
                    trips.add(tripInstance);
                }
            }
        }
        return trips;
    }
}