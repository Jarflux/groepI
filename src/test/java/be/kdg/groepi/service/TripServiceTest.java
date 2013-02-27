package be.kdg.groepi.service;

import be.kdg.groepi.model.Requirement;
import be.kdg.groepi.model.Trip;
import be.kdg.groepi.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    private User user;
    //private Long tripId;

    @Before
    public void beforeEachTest(){
        user = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4,5,2011,15,32,0));
        UserService.createUser(user);
        trip = new Trip("Onze eerste trip","Hopelijk is deze niet te saai!",true,true,user);// trip aanmaken
        TripService.createTrip(trip);
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
        assertEquals("createTrip: ", trip, TripService.getTripById(trip.getId()));
    }

    @Test
    public void updateTrip(){
        trip.setAvailable(Boolean.FALSE);
        trip.setDescription("Ho-ho-ho edited");

        TripService.updateTrip(trip);
        assertEquals("updateTrip: ",trip,TripService.getTripById(trip.getId()));
    }

    @Test
    public void deleteTrip(){
        assertNotNull("deleteTrip: Trip found", TripService.getTripById(trip.getId()));
        TripService.deleteTrip(trip);
        assertNull("deleteTrip: Trip not found", TripService.getTripById(trip.getId()));
    }



    @Test
    public void addRequirementToTrip(){
        assertTrue("Trip: trip should have no requirements", trip.getRequirements().isEmpty());
        Requirement requirement = new Requirement("BEN");
        RequirementService.createRequirement(requirement);
        trip.addRequirementToTrip(requirement);
        assertFalse("Trip: trip should have requirements", trip.getRequirements().isEmpty());
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
