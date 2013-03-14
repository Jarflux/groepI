/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.AnswerInstance;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 * @author: Ben Oeyen
 * @date: 7-mrt-2013
 */
@Repository
public class AnswerInstanceDaoImpl implements AnswerInstanceDao {

    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createAnswerInstance(AnswerInstance user) throws DataAccessException {
        getEntityManager().persist(user);
    }

    @Override
    public void deleteAnswerInstance(AnswerInstance user) throws DataAccessException {
        getEntityManager().remove(user);
    }

    @Override
    public void updateAnswerInstance(AnswerInstance user) throws DataAccessException {
        getEntityManager().merge(user);
    }

    @Override
    public AnswerInstance getAnswerInstanceById(Long id) throws DataAccessException {
        return getEntityManager().find(AnswerInstance.class, id);
    }

    @Override
    public List<AnswerInstance> getAllAnswerInstances() throws DataAccessException {
        Query query = getEntityManager().createQuery("from AnswerInstance a");
        List<AnswerInstance> result = query.getResultList();
        return result;
    }
}