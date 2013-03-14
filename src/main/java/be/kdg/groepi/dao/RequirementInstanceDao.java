/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.RequirementInstance;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 * @author: Ben Oeyen
 * @date: 7-mrt-2013
 */
public interface RequirementInstanceDao {

    public void createRequirementInstance(RequirementInstance user) throws DataAccessException;

    public void deleteRequirementInstance(RequirementInstance user) throws DataAccessException;

    public void updateRequirementInstance(RequirementInstance user) throws DataAccessException;

    public RequirementInstance getRequirementInstanceById(Long id) throws DataAccessException;

    public List<RequirementInstance> getAllRequirementInstances() throws DataAccessException;
}