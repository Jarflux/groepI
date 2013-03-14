/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.Answer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 * @author: Ben Oeyen
 * 
 * @date: 7-mrt-2013
 */
@Repository
public class AnswerDaoImpl implements AnswerDao {

    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createAnswer(Answer user) throws DataAccessException {
        getEntityManager().persist(user);
    }

    @Override
    public void deleteAnswer(Answer user) throws DataAccessException {
        getEntityManager().remove(user);
    }

    @Override
    public void updateAnswer(Answer user) throws DataAccessException {
        getEntityManager().merge(user);
    }

    @Override
    public Answer getAnswerById(Long id) throws DataAccessException {
        return getEntityManager().find(Answer.class, id);
    }

    @Override
    public List<Answer> getAllAnswers() throws DataAccessException {
        Query query = getEntityManager().createQuery("from Answer a");
        List<Answer> result = query.getResultList();
        return result;
    }

    @Override
    public List<Answer> getAnswersByStopId(long id) {
        Query query = getEntityManager().createQuery("from Answer a where a.fStop.fId = :id");
        query.setParameter("id", id);
        List<Answer> result = query.getResultList();
        return result;
    }
}
