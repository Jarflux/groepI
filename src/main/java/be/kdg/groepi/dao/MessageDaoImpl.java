/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.Message;
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
public class MessageDaoImpl implements MessageDao {

    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createMessage(Message user) throws DataAccessException {
        getEntityManager().persist(user);
    }

    @Override
    public void deleteMessage(Message user) throws DataAccessException {
        getEntityManager().remove(user);
    }

    @Override
    public void updateMessage(Message user) throws DataAccessException {
        getEntityManager().merge(user);
    }

    @Override
    public Message getMessageById(Long id) throws DataAccessException {
        return getEntityManager().find(Message.class, id);
    }

    @Override
    public List<Message> getAllMessages() throws DataAccessException {
        Query query = getEntityManager().createQuery("from Message m");
        List<Message> result = query.getResultList();
        return result;
    }

    @Override
    public List<Message> getMessagesByTripInstanceId(Long id) throws DataAccessException {
        Query query = getEntityManager().createQuery("from Message m where m.fTripInstance.fId = :id");
        query.setParameter("id", id);
        List<Message> result = query.getResultList();
        return result;
    }
}