/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.service;

import be.kdg.groepi.model.Answer;
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

public class AnswerService {
    public static Answer getAnswerById(long Id) {
        Answer answer = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            List<Answer> answers = session.createQuery("FROM Answer answer WHERE answer.fId = :Id").
                    setString("Id", String.valueOf(Id)).setReadOnly(true).list();
            if (answers.size() > 0) {
                answer = answers.get(0);
            }

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return answer;
    }

    public static void createAnswer(Answer answer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(answer);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void updateAnswer(Answer answer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(answer);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static void deleteAnswer(Answer answer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.delete(answer);

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public static List<Answer> getAllAnswers() {
        List<Answer> answers = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            answers = session.createQuery("FROM Answer").list();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return answers;
    }
}
