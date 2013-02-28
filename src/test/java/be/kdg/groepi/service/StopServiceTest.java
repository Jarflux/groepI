package be.kdg.groepi.service;

import be.kdg.groepi.model.Stop;
import be.kdg.groepi.model.Trip;
import be.kdg.groepi.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static be.kdg.groepi.utils.DateUtil.dateToLong;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class StopServiceTest {

    private Trip trip;
    private User user;

    @Before
    public void beforeEachTest(){
        user = new User("Tim", "tim@junittest.com", "tim", dateToLong(4,5,2011,15,32,0));
        UserService.createUser(user);
        trip = new Trip("Onze eerste trip","Hopelijk is deze niet te saai!",true,true,user);// trip aanmaken
        TripService.createTrip(trip);
    }
    @After
    public void afterEachTest(){
        TripService.deleteTrip(trip);
        UserService.deleteUser(user);
    }
    @Test
    public void createStop()
    {
        Stop stop = new Stop("Stop 1", "", 1, 0, 0, "Eerste Stopplaats", trip);
        StopService.createStop(stop);
        assertEquals(stop, StopService.getStopById(stop.getId()));
    }
    @Test
    public void editStopFromTrip()
    {
        for (Stop s : trip.getStops())
        {
            s.setLocation(s.getLocation().concat(" Edited"));
            s.setStopText(s.getStopText().concat(" Edited"));
            s.setName(s.getName().concat(" Edited"));
            s.setOrder(s.getOrder() + 1);
            s.setType(s.getType() +1);
            s.setDisplayMode(s.getDisplayMode() + 1);
            StopService.updateStop(s);
            assertEquals(s, StopService.getStopById(s.getId()));
        }
    }
    @Test
    public void deleteStopsFromTrip()
    {
        for (Stop s : trip.getStops())
        {
            StopService.deleteStop(s);
        }
        assertTrue(trip.getStops().isEmpty());
    }
}
