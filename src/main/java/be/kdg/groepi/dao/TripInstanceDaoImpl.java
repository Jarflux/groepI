/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.TripInstance;
import be.kdg.groepi.model.User;
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
public class TripInstanceDaoImpl implements TripInstanceDao {

    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createTripInstance(TripInstance user) throws DataAccessException {
        getEntityManager().persist(user);
    }

    @Override
    public void deleteTripInstance(TripInstance user) throws DataAccessException {
        getEntityManager().remove(user);
    }

    @Override
    public void updateTripInstance(TripInstance user) throws DataAccessException {
        getEntityManager().merge(user);
    }

    @Override
    public TripInstance getTripInstanceById(Long id) throws DataAccessException {
        return getEntityManager().find(TripInstance.class, id);
    }

    @Override
    public List<TripInstance> getAllTripInstances() throws DataAccessException {
        Query query = getEntityManager().createQuery("from TripInstance t");
        List<TripInstance> result = query.getResultList();
        return result;
    }

    @Override
    public List<TripInstance> getTripInstancesByUserId(Long userId) {
        Query query = getEntityManager().createQuery("from TripInstance t join t.fParticipants p where p.fId = :userId");
        query.setParameter("userId", userId);
        List<TripInstance> result = query.getResultList();
        return result; 
    }

    @Override
    public List<TripInstance> getAllTripInstancesByTripId(long tripId) {
        Query query = getEntityManager().createQuery("from TripInstance t where t.fTrip.fId = :tripId");
        query.setParameter("tripId", tripId);
        List<TripInstance> result = query.getResultList();
        return result;
    }

    @Override
    public List<TripInstance> getTripInstancesByOrganiserId(long id) {
        Query query = getEntityManager().createQuery("from TripInstance t where t.fOrganiser.fId = :id");
        query.setParameter("id", id);
        List<TripInstance> result = query.getResultList();
        return result;
    }

    @Override
    public List<TripInstance> getPublicTripInstances() {
        Query query = getEntityManager().createQuery("from TripInstance t where t.fAvailable = 1");
        List<TripInstance> result = query.getResultList();
        return result;
    }
}