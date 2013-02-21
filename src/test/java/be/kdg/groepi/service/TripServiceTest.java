package be.kdg.groepi.service;

import be.kdg.groepi.model.Trip;
import be.kdg.groepi.model.User;
import org.junit.*;

import java.sql.Date;
import java.util.Calendar;

import static be.kdg.groepi.utils.DateUtil.dateToLong;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Gregory
 * Date: 12/02/13
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public class TripServiceTest {

    private Trip trip;
    //private Long tripId;

    @Before
    public void beforeEachTest(){
        User user = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4,5,2011,15,32,0));
        UserService.createUser(user);
        trip = new Trip("Stadwandeling Nieuw Zuid", "Ho-ho-ho", Boolean.TRUE, dateToLong(4,5,2011,15,32,0),dateToLong(4,5,2011,19,32,0),user );// trip aanmaken
    }

    @After
    public void afterEachTest(){
        trip = null;
        for (Trip trip : TripService.getAllTrips()) {
            TripService.deleteTrip(trip);
        }
    }

    @Test
    public void createTrip(){
        TripService.createTrip(trip);
        assertEquals("createTrip: ", trip, TripService.getTripById(trip.getId()));
    }

    @Test
    public void updateTrip(){
        TripService.createTrip(trip);
        trip.setAvailable(Boolean.FALSE);
        trip.setDescription("Ho-ho-ho edited");
        trip.setStart(dateToLong(4,5,2011,15,32,0));
        trip.setEnd(dateToLong(4,5,2011,15,32,0));
        TripService.updateTrip(trip);
        assertEquals("updateTrip: ",trip,TripService.getTripById(trip.getId()));
    }

    @Test
    public void deleteTrip(){
        TripService.createTrip(trip);
        TripService.deleteTrip(trip);
        assertEquals("deleteTrip: ",trip,TripService.getTripById(trip.getId()));
    }

    /*@Test
    public void createTimedTrip(){}
    @Test
    public void updateTimedTrip(){}
    @Test
    public void deleteTimedTrip(){}
    @Test
    public void createRecurrentTrip(){}
    @Test
    public void updateRecurrence(){}
    @Test
    public void deleteRecurrenceInstances(){}
    @Test
    public void deleteRecurrence(){} */
}
