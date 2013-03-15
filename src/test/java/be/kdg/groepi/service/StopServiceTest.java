package be.kdg.groepi.service;

import be.kdg.groepi.model.Stop;
import be.kdg.groepi.model.Trip;
import be.kdg.groepi.model.User;

import static be.kdg.groepi.utils.DateUtil.dateToLong;

import org.junit.After;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Transactional
public class StopServiceTest {

    private Trip trip;
    private User user;
    @Autowired
    protected UserService userService;
    @Autowired
    protected TripService tripService;
    @Autowired
    protected StopService stopService;

    @Before
    public void beforeEachTest() {
        user = new User("Tim", "tim@junittest.com", "tim", dateToLong(4, 5, 2011, 15, 32, 0));
        userService.createUser(user);
        trip = new Trip("Onze eerste trip", "Hopelijk is deze niet te saai!", true, true, user);// trip aanmaken
        tripService.createTrip(trip);
    }

    @After
    public void afterEachTest() {
        tripService.deleteTrip(trip);
        userService.deleteUser(user);
    }

    @Test
    public void createStop() {
        Stop stop = new Stop("Stop 1", "", "", 1, 0, 0, "Eerste Stopplaats", 1000, trip);
        stopService.createStop(stop);
        assertEquals("createStop: Stop was not created", stop, stopService.getStopById(stop.getId()));
    }

    @Test
    public void editStopFromTrip() {
        for (Stop s : trip.getStops()) {
            s.setLatitude(s.getLatitude().concat(" Edited"));
            s.setLongitude(s.getLongitude().concat(" Edited"));
            s.setStopText(s.getStopText().concat(" Edited"));
            s.setName(s.getName().concat(" Edited"));
            s.setStopnumber(s.getStopnumber() + 1);
            s.setType(s.getType() + 1);
            s.setDisplayMode(s.getDisplayMode() + 1);
            s.setRadius(s.getRadius() + 1);
            stopService.updateStop(s);
            assertEquals("editStopFromTrip: Stop was not edited", stopService.getStopById(s.getId()));
        }
    }

    @Test
    public void testCompareStop() {
        Stop stop = new Stop("Stop 1", "154545", "454845", 1, 2, 3, "Eerste Stopplaats", 1000, trip);
        Stop stop2 = new Stop("Stop 1", "154545", "454845", 1, 2, 3, "Eerste Stopplaats", 1000, trip);
        stopService.createStop(stop);
        stopService.createStop(stop2);
        assertTrue("Stops should be the same", stop.equals(stop2));
    }

    @Test
    public void testCompareStopNullObject() {
        Stop stop = new Stop("Stop 1", "154545", "454845", 1, 2, 3, "Eerste Stopplaats", 1000, trip);
        Stop stop2 = null;
        stopService.createStop(stop);
        assertFalse("Stops should not be the same", stop.equals(stop2));
    }

    @Test
    public void testCompareStopName() {
        Stop stop = new Stop("Stop 1", "154545", "454845", 1, 2, 3, "Eerste Stopplaats", 1000, trip);
        Stop stop2 = new Stop("Stap 2", "154545", "454845", 1, 2, 3, "Eerste Stopplaats", 1000, trip);
        stopService.createStop(stop);
        stopService.createStop(stop2);
        assertFalse("Stops should not be the same", stop.equals(stop2));
    }

    @Test
    public void testCompareStopLatitude() {
        Stop stop = new Stop("Stop 1", "154545", "454845", 1, 2, 3, "Eerste Stopplaats", 1000, trip);
        Stop stop2 = new Stop("Stop 1", "1545", "454845", 1, 2, 3, "Eerste Stopplaats", 1000, trip);
        stopService.createStop(stop);
        stopService.createStop(stop2);
        assertFalse("Stops should not be the same", stop.equals(stop2));
    }

    @Test
    public void testCompareStopLongtitude() {
        Stop stop = new Stop("Stop 1", "154545", "454845", 1, 2, 3, "Eerste Stopplaats", 1000, trip);
        Stop stop2 = new Stop("Stop 1", "154545", "455", 1, 2, 3, "Eerste Stopplaats", 1000, trip);
        stopService.createStop(stop);
        stopService.createStop(stop2);
        assertFalse("Stops should not be the same", stop.equals(stop2));
    }

    @Test
    public void testCompareStopNumber() {
        Stop stop = new Stop("Stop 1", "154545", "454845", 1, 2, 3, "Eerste Stopplaats", 1000, trip);
        Stop stop2 = new Stop("Stop 1", "154545", "454845", 3, 2, 3, "Eerste Stopplaats", 1000, trip);
        stopService.createStop(stop);
        stopService.createStop(stop2);
        assertFalse("Stops should not be the same", stop.equals(stop2));
    }

    @Test
    public void testCompareStopText() {
        Stop stop = new Stop("Stop 1", "154545", "454845", 1, 2, 3, "Eerste Stopplaats", 1000, trip);
        Stop stop2 = new Stop("Stop 1", "154545", "454845", 1, 2, 3, "Eerste bwahaha Stopplaats", 1000, trip);
        stopService.createStop(stop);
        stopService.createStop(stop2);
        assertFalse("Stops should not be the same", stop.equals(stop2));
    }

    @Test
    public void testCompareStopDisplayMode() {
        Stop stop = new Stop("Stop 1", "154545", "454845", 1, 2, 3, "Eerste Stopplaats", 1000, trip);
        Stop stop2 = new Stop("Stop 1", "154545", "454845", 1, 2, 0, "Eerste Stopplaats", 1000, trip);
        stopService.createStop(stop);
        stopService.createStop(stop2);
        assertFalse("Stops should not be the same", stop.equals(stop2));
    }

    @Test
    public void testCompareStopType() {
        Stop stop = new Stop("Stop 1", "154545", "454845", 1, 2, 3, "Eerste Stopplaats", 1000, trip);
        Stop stop2 = new Stop("Stop 1", "154545", "454845", 1, 0, 3, "Eerste Stopplaats", 1000, trip);
        stopService.createStop(stop);
        stopService.createStop(stop2);
        assertFalse("Stops should not be the same", stop.equals(stop2));
    }
}
