package be.kdg.groepi.service;

import be.kdg.groepi.dao.TripDao;
import be.kdg.groepi.model.Trip;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Trip Service
 * Description:
 */
@Service("tripService")
@Transactional
public class TripService {

    @Autowired
    private TripDao tripDoa;

    public Trip getTripById(long id) {
        return tripDoa.getTripById(id);
    }

    public void createTrip(Trip trip) {
        tripDoa.createTrip(trip);
    }

    public void updateTrip(Trip trip) {
        tripDoa.updateTrip(trip);
    }

    public void deleteTrip(Trip trip) {
        tripDoa.deleteTrip(trip);
    }

    public List<Trip> getAllTrips() {
        return tripDoa.getAllTrips();
    }

    public List<Trip> getTripsByOrganiserId(long id) {
        return tripDoa.getTripsByOrganiserId(id);
    }

    public List<Trip> getPublicTrips() {
        return tripDoa.getPublicTrips();
    }
}