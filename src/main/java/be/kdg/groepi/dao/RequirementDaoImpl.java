/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.Requirement;
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
public class RequirementDaoImpl implements RequirementDao {

    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createRequirement(Requirement requirement) throws DataAccessException {
        getEntityManager().persist(requirement);
    }

    @Override
    public void deleteRequirement(Requirement requirement) throws DataAccessException {
        getEntityManager().remove(requirement);
    }

    @Override
    public void updateRequirement(Requirement requirement) throws DataAccessException {
        getEntityManager().merge(requirement);
    }

    @Override
    public Requirement getRequirementById(Long id) throws DataAccessException {
        return getEntityManager().find(Requirement.class, id);
    }

    @Override
    public List<Requirement> getAllRequirements() throws DataAccessException {
        Query query = getEntityManager().createQuery("from Requirement r");
        List<Requirement> result = query.getResultList();
        return result;
    }
}