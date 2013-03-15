/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.Cost;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 * @author: Ben Oeyen
 * @date: 7-mrt-2013
 */
public interface CostDao {

    public void createCost(Cost cost) throws DataAccessException;

    public void deleteCost(Cost cost) throws DataAccessException;

    public void updateCost(Cost cost) throws DataAccessException;

    public Cost getCostById(Long id) throws DataAccessException;

    public List<Cost> getAllCosts() throws DataAccessException;
}
