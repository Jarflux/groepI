/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.TripInstance;
import java.util.List;

import be.kdg.groepi.model.User;
import org.springframework.dao.DataAccessException;

/**
 * @author: Ben Oeyen
 * @date: 7-mrt-2013
 */
public interface TripInstanceDao {

    public void createTripInstance(TripInstance tripInstance) throws DataAccessException;

    public void deleteTripInstance(TripInstance tripInstance) throws DataAccessException;

    public void updateTripInstance(TripInstance tripInstance) throws DataAccessException;

    public TripInstance getTripInstanceById(Long id) throws DataAccessException;

    public List<TripInstance> getAllTripInstances() throws DataAccessException;

    public List<Object[]> getTripInstancesByUserId(Long userId) throws DataAccessException;

    public List<TripInstance> getAllTripInstancesByTripId(long tripId) throws DataAccessException;

    public List<TripInstance> getTripInstancesByOrganiserId(long userId) throws DataAccessException;

    public List<TripInstance> getPublicTripInstances() throws DataAccessException;

    public List<User> getTripParticipants(Long tripId) throws DataAccessException;
}