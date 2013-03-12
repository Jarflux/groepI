/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.service;

import be.kdg.groepi.model.Stop;
import be.kdg.groepi.utils.HibernateUtil;
import be.kdg.groepi.utils.VuforiaUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class StopService {
    public static Stop getStopById(long Id) {
        Stop stop = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            List<Stop> stops = session.createQuery("FROM Stop stop WHERE stop.fId = :Id").
                    setString("Id", String.valueOf(Id)).setReadOnly(true).list();
            if (stops.size() > 0) {
                stop = stops.get(0);
            }

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return stop;
    }

    public static void createStop(Stop stop) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(stop);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void updateStop(Stop stop) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(stop);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void deleteStop(Stop stop) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.delete(stop);

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static List<Stop> getAllStops() {
        List<Stop> stops = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            stops = session.createQuery("FROM Stop").list();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return stops;
    }
    
        public static Object getAllTripStopsByTripId(Long tripId) {
        List<Stop> stops = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            stops = session.createQuery("FROM Stop stop WHERE stop.fTrip = :tripId").
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
        return stops;
    }

    public static void addToVuforia(Long stopid,File image) throws JSONException, IOException, URISyntaxException {
        VuforiaUtil.postTarget(image,stopid);
    }



}
