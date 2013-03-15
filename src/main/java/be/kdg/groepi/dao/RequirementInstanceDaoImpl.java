/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.RequirementInstance;
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
public class RequirementInstanceDaoImpl implements RequirementInstanceDao {

    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createRequirementInstance(RequirementInstance requirementInstance) throws DataAccessException {
        getEntityManager().persist(requirementInstance);
    }

    @Override
    public void deleteRequirementInstance(RequirementInstance requirementInstance) throws DataAccessException {
        getEntityManager().remove(requirementInstance);
    }

    @Override
    public void updateRequirementInstance(RequirementInstance requirementInstance) throws DataAccessException {
        getEntityManager().merge(requirementInstance);
    }

    @Override
    public RequirementInstance getRequirementInstanceById(Long id) throws DataAccessException {
        return getEntityManager().find(RequirementInstance.class, id);
    }

    @Override
    public List<RequirementInstance> getAllRequirementInstances() throws DataAccessException {
        Query query = getEntityManager().createQuery("from RequirementInstance r");
        List<RequirementInstance> result = query.getResultList();
        return result;
    }
}