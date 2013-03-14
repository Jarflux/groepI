/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.Trip;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 * @author: Ben Oeyen
 * @date: 7-mrt-2013
 */
public interface TripDao {

    public void createTrip(Trip user) throws DataAccessException;

    public void deleteTrip(Trip user) throws DataAccessException;

    public void updateTrip(Trip user) throws DataAccessException;

    public Trip getTripById(Long id) throws DataAccessException;

    public List<Trip> getAllTrips() throws DataAccessException;

    public List<Trip> getTripsByOrganiserId(long id) throws DataAccessException;

    public List<Trip> getPublicTrips() throws DataAccessException;
}
