package be.kdg.groepi.service;

import be.kdg.groepi.model.Stop;
import be.kdg.groepi.model.Trip;
import be.kdg.groepi.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static be.kdg.groepi.utils.DateUtil.dateToLong;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class StopServiceTest {

    private Trip trip;
    private User user;

    @Before
    public void beforeEachTest(){
        user = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4,5,2011,15,32,0));
        UserService.createUser(user);
        trip = new Trip("Onze eerste trip","Hopelijk is deze niet te saai!",true,true,user);// trip aanmaken
        TripService.createTrip(trip);
    }
    @After
    public void afterEachTest(){

    }
    @Test
    public void addStopToStrip()
    {
        StopService.createStop(new Stop("Stop 1", "", 1, 0, 0, "Eerste Stopplaats"));
        StopService.createStop(new Stop("Stop 2", "", 1, 0, 0, "Tweede Stopplaats"));
        assertFalse("TripInstance: tripInstance should have stops", trip.getStops().isEmpty());
    }
    @Test
    public void editStopFromStrip()
    {
        for (Stop s : trip.getStops())
        {
            s.setStopText(s.getStopText().concat(" Edited"));
            StopService.updateStop(s);
            assertSame(s, StopService.getStopById(s.getId()));
        }
    }
    @Test
    public void deleteStopsFromTrip()
    {
        for (Stop s : trip.getStops())
        {
            StopService.deleteStop(s);
        }
        assertTrue("TripInstance: tripInstance should have stops", trip.getStops().isEmpty());
    }

    @Test
    public void addAndDeleteByTrip()
    {
        Stop stop = new Stop("Stop 1", "", 1, 0, 0, "Eerste Stopplaats via addStop");
        trip.getStops().add(stop);
        TripService.updateTrip(trip);
        assertFalse("TripInstance: tripInstance should have stops", trip.getStops().isEmpty());
        TripService.deleteTrip(trip);
        assertTrue("TripInstance: tripInstance should have stops", trip.getStops().isEmpty());
    }
}
