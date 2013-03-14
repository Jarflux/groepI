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

    public void createRequirement(Requirement user) throws DataAccessException;

    public void deleteRequirement(Requirement user) throws DataAccessException;

    public void updateRequirement(Requirement user) throws DataAccessException;

    public Requirement getRequirementById(Long id) throws DataAccessException;

    public List<Requirement> getAllRequirements() throws DataAccessException;
}