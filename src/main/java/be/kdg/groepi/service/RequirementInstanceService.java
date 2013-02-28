package be.kdg.groepi.service;

import be.kdg.groepi.model.Requirement;
import be.kdg.groepi.model.RequirementInstance;
import be.kdg.groepi.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Requirement Service
 * Description:
 */

public class RequirementInstanceService {
    public static RequirementInstance getRequirementInstanceById(long Id) {
        RequirementInstance requirementInstance = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            List<RequirementInstance> requirementInstances = session.createQuery("FROM RequirementInstance requirementInstance WHERE requirementInstance.fId = :Id").
                    setString("Id", String.valueOf(Id)).setReadOnly(true).list();
            if (requirementInstances.size() > 0) {
                requirementInstance = requirementInstances.get(0);
            }

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return requirementInstance;
    }

    public static void createRequirementInstance(RequirementInstance requirementInstance) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(requirementInstance);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void updateRequirementInstance(RequirementInstance requirementInstance) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(requirementInstance);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void deleteRequirementInstance(RequirementInstance requirementInstance) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.delete(requirementInstance);

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static List<RequirementInstance> getAllRequirementInstances() {
        List<RequirementInstance> requirementInstances = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            requirementInstances = session.createQuery("FROM RequirementInstance").list();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return requirementInstances;
    }
}

