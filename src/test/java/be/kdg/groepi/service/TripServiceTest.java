package be.kdg.groepi.service;

import be.kdg.groepi.model.*;
import org.junit.*;

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
        trip = new Trip("Stadwandeling Nieuw Zuid", "Ho-ho-ho", Boolean.TRUE, dateToLong(4,5,2011,15,32,0),dateToLong(4,5,2011,19,32,0),user );// trip aanmaken
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
        trip.setStart(dateToLong(4,5,2011,15,32,0));
        trip.setEnd(dateToLong(4,5,2011,15,32,0));
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
    public void addUserToTrip(){
        assertTrue("Trip: trip should have no participants", trip.getParticipants().isEmpty());
        trip.addParticipantToTrip(user);
        assertFalse("Trip: trip should have participants", trip.getParticipants().isEmpty());
    }

    @Test
    public void addCostToTrip(){
        assertTrue("Trip: trip should have no costs", trip.getCosts().isEmpty());
        Cost cost = new Cost("BEN's cost", 35.53);
        CostService.createCost(cost);
        trip.addCostToTrip(cost);
        assertFalse("Trip: trip should have costs", trip.getCosts().isEmpty());
    }

    @Test
    public void addRequirementToTrip(){
        assertTrue("Trip: trip should have no requirements", trip.getRequirements().isEmpty());
        Requirement requirement = new Requirement("BEN");
        RequirementService.createRequirement(requirement);
        trip.addRequirementToTrip(requirement);
        assertFalse("Trip: trip should have requirements", trip.getRequirements().isEmpty());
    }

    @Test
    public void addMessageToTrip(){
        assertTrue("Trip: trip should have no messages", trip.getMessages().isEmpty());
        Message message = new Message("BEN's message", dateToLong(12, 10, 1990, 8, 17, 35));
        MessageService.createMessage(message);
        trip.addMessageToTrip(message);
        assertFalse("Trip: trip should have messages", trip.getMessages().isEmpty());
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
