/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.Message;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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
    public void createMessage(Message message) throws DataAccessException {
        getEntityManager().persist(message);
    }

    @Override
    public void deleteMessage(Message message) throws DataAccessException {
        getEntityManager().remove(getEntityManager().merge(message));
    }

    @Override
    public void updateMessage(Message message) throws DataAccessException {
        getEntityManager().merge(message);
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