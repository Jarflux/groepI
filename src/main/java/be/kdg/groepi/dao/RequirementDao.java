/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.Requirement;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 * @author: Ben Oeyen
 * @date: 7-mrt-2013
 */
public interface RequirementDao {

    public void createRequirement(Requirement requirement) throws DataAccessException;

    public void deleteRequirement(Requirement requirement) throws DataAccessException;

    public void updateRequirement(Requirement requirement) throws DataAccessException;

    public Requirement getRequirementById(Long id) throws DataAccessException;

    public List<Requirement> getAllRequirements() throws DataAccessException;
}