/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.Stop;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 * @author: Ben Oeyen
 * @date: 7-mrt-2013
 */
public interface StopDao {

    public void createStop(Stop user) throws DataAccessException;

    public void deleteStop(Stop user) throws DataAccessException;

    public void updateStop(Stop user) throws DataAccessException;

    public Stop getStopById(Long id) throws DataAccessException;

    public List<Stop> getAllStops() throws DataAccessException;

    public List<Stop> getAllTripStopsByTripId(Long tripId) throws DataAccessException;
}