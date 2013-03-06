package be.kdg.groepi.service;

import be.kdg.groepi.model.*;
import be.kdg.groepi.utils.DateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static be.kdg.groepi.utils.DateUtil.dateToLong;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StopInstanceServiceTest {

    private Trip trip;
    private TripInstance tripInstance;
    private User user;
    private Stop stop;
    private StopInstance stopInstance;

    @Before
    public void beforeEachTest() {
        user = new User("Tim", "tim@junittest.com", "tim", dateToLong(4, 5, 2011, 15, 32, 0));
        UserService.createUser(user);
        trip = new Trip("Onze eerste trip", "Hopelijk is deze niet te saai!", true, true, user);// trip aanmaken
        TripService.createTrip(trip);
        tripInstance = new TripInstance("Tripje 1", "Eerste uitvoering van de 'Onze eerste trip'-trip", true,
                DateUtil.dateToLong(2, 3, 2013, 12, 0, 0), DateUtil.dateToLong(2, 3, 2013, 16, 0, 0), user, trip);
        TripInstanceService.createTripInstance(tripInstance);
        stop = new Stop("Stop 1", "", "", 1, 0, 0, "Eerste Stopplaats", trip);
        StopService.createStop(stop);
        stopInstance = new StopInstance(stop, tripInstance);
        StopInstanceService.createStopInstance(stopInstance);
    }

    @After
    public void afterEachTest() {
        TripService.deleteTrip(trip);
        UserService.deleteUser(user);
    }

    @Test
    public void createStopInstance() {
        assertEquals(stopInstance, StopInstanceService.getStopInstanceById(stopInstance.getId()));
    }

    @Test
    public void deleteStopInstances() {
        while (!StopInstanceService.getAllStopInstances().isEmpty()) {
            StopInstance firstStopInstance = StopInstanceService.getAllStopInstances().get(0);
            StopInstanceService.deleteStopInstance(firstStopInstance);
        }
        assertTrue(StopInstanceService.getAllStopInstances().isEmpty());
    }
}
