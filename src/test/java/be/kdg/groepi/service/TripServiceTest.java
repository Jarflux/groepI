package be.kdg.groepi.service;

import be.kdg.groepi.model.Trip;
import org.junit.*;

import java.sql.Date;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Gregory
 * Date: 12/02/13
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public class TripServiceTest {

    //private Trip trip;
    private Long tripId;

    @Before
    public void beforeEachTest(){

    }
    @After
    public void afterEachTest(){

    }

    private Date fillDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2007, Calendar.MAY, 12);
        return new Date(cal.getTime().getTime());
    }

    @Test
    public void createTrip(){
        Trip trip = new Trip("Stadwandeling Nieuw Zuid", "Ho-ho-ho", Boolean.TRUE, null,null);
        TripService.createTrip(trip);
        tripId = trip.getId();
        assertEquals("createUser: userEquals", trip, TripService.getTripById(trip.getId()));
    }
    @Test
    public void updateTrip(){
        Trip trip = TripService.getTripById(tripId);
        trip.setAvailable(Boolean.FALSE);
        trip.setDescription("Ho-ho-ho edited");
        trip.setStart(fillDate());
        trip.setEnd(fillDate());
        assertEquals("updateUser: userEquals", trip, TripService.getTripById(trip.getId()));
    }
    @Test
    public void deleteTrip(){
        Trip trip = TripService.getTripById(tripId);
        TripService.deleteTrip(trip);
    }
    /*@Test
    public void createTimedTrip(){}
    @Test
    public void updateTimedTrip(){}
    @Test
    public void deleteTimedTrip(){}*/
    @Test
    public void createRecurrentTrip(){}
    @Test
    public void updateRecurrence(){}
    @Test
    public void deleteRecurrenceInstances(){}
    @Test
    public void deleteRecurrence(){}
}
