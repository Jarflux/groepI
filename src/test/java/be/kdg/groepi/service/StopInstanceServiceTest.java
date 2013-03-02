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
    StopInstance stopInstance;

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
    public void editStopInstance() {
        /*for (Stop s : trip.getStops()) {
            s.setLatitude(s.getLatitude().concat(" Edited"));
            s.setLongitude(s.getLongitude().concat(" Edited"));
            s.setStopText(s.getStopText().concat(" Edited"));
            s.setName(s.getName().concat(" Edited"));
            s.setOrder(s.getOrder() + 1);
            s.setType(s.getType() + 1);
            s.setDisplayMode(s.getDisplayMode() + 1);
            StopService.updateStop(s);
            assertEquals(s, StopService.getStopById(s.getId()));
        }*/
        TripInstance tempTripInstance = new TripInstance("Tripje 2", "Tweede uitvoering van de 'Onze eerste trip'-trip", true,
                DateUtil.dateToLong(2, 5, 2013, 12, 0, 0), DateUtil.dateToLong(2, 5, 2013, 16, 0, 0), user, trip);
        TripInstanceService.createTripInstance(tripInstance);
        Stop tempStop = new Stop("Stop 1", "", "", 1, 0, 0, "Tweede Stopplaats", trip);
        StopService.createStop(stop);

        StopInstance tempStopInstance = new StopInstance(stopInstance.getStop(), stopInstance.getTripInstance());

        stopInstance.setStop(tempStop);
        stopInstance.setTripInstance(tempTripInstance);

        StopInstanceService.updateStopInstance(stopInstance);
        assertFalse("updateStopInstance: stopInstance was not updated", tempStopInstance.equals(StopInstanceService.getStopInstanceById(stopInstance.getId())));
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
