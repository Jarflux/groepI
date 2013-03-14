/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.Stop;
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
public class StopDaoImpl implements StopDao {

    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createStop(Stop user) throws DataAccessException {
        getEntityManager().persist(user);
    }

    @Override
    public void deleteStop(Stop user) throws DataAccessException {
        getEntityManager().remove(user);
    }

    @Override
    public void updateStop(Stop user) throws DataAccessException {
        getEntityManager().merge(user);
    }

    @Override
    public Stop getStopById(Long id) throws DataAccessException {
        return getEntityManager().find(Stop.class, id);
    }

    @Override
    public List<Stop> getAllStops() throws DataAccessException {
        Query query = getEntityManager().createQuery("from Stop s");
        List<Stop> result = query.getResultList();
        return result;
    }

    @Override
    public List<Stop> getAllTripStopsByTripId(Long tripId) throws DataAccessException {
        Query query = getEntityManager().createQuery("from Stop s where s.fTrip.fId = :tripId");
        query.setParameter("tripId", tripId);
        List<Stop> result = query.getResultList();
        return result;
    }
}