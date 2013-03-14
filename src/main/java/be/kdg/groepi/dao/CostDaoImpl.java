/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.Cost;
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
public class CostDaoImpl implements CostDao {

    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createCost(Cost user) throws DataAccessException {
        getEntityManager().persist(user);
    }

    @Override
    public void deleteCost(Cost user) throws DataAccessException {
        getEntityManager().remove(user);
    }

    @Override
    public void updateCost(Cost user) throws DataAccessException {
        getEntityManager().persist(user);
    }

    @Override
    public Cost getCostById(Long id) throws DataAccessException {
        return getEntityManager().find(Cost.class, id);
    }

    @Override
    public List<Cost> getAllCosts() throws DataAccessException {
        Query query = getEntityManager().createQuery("from Cost c");
        List<Cost> result = query.getResultList();
        return result;
    }
}
