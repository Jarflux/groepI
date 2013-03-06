package be.kdg.groepi.service;

import be.kdg.groepi.model.Requirement;
import be.kdg.groepi.model.Trip;
import be.kdg.groepi.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static be.kdg.groepi.utils.DateUtil.dateToLong;
import static org.junit.Assert.*;

public class TripServiceTest {
    //TODO: cascade delete-dinges

    private Trip trip;
    private User user;
    //private Long tripId;

    @Before
    public void beforeEachTest() {
        user = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        UserService.createUser(user);
        trip = new Trip("Onze eerste trip", "Hopelijk is deze niet te saai!", true, true, user);// trip aanmaken
        TripService.createTrip(trip);
    }

    @After
    public void afterEachTest() {
        trip = null;
        List<Trip> trips = TripService.getAllTrips();
        for (Trip trip : trips) {
            TripService.deleteTrip(trip);
        }
    }

    @Test
    public void createTrip() {
        assertEquals("createTrip: ", trip, TripService.getTripById(trip.getId()));
    }

    @Test
    public void updateTrip() {
        trip.setAvailable(Boolean.FALSE);
        trip.setDescription("Ho-ho-ho edited");

        TripService.updateTrip(trip);
        assertEquals("updateTrip: ", trip, TripService.getTripById(trip.getId()));
    }

    @Test
    public void deleteTrip() {
        assertNotNull("deleteTrip: Trip found", TripService.getTripById(trip.getId()));
        TripService.deleteTrip(trip);
        assertNull("deleteTrip: Trip not found", TripService.getTripById(trip.getId()));
    }

    @Test
    public void addAndRemoveRequirementToTrip() {
        assertTrue("Trip: trip should have no requirements", trip.getRequirements().isEmpty());
        Requirement requirement = new Requirement("BEN", 1, "", trip);
        RequirementService.createRequirement(requirement);
        trip.addRequirementToTrip(requirement);
        assertFalse("Trip: trip should have requirements", trip.getRequirements().isEmpty());
        trip.removeRequirementFromTrip(requirement);
        assertTrue("Trip: trip should have no requirements", trip.getRequirements().isEmpty());
    }

    @Test
    public void getPublicTrips() {
        int size = TripService.getAllTrips().size();
        Trip newFalseTrip = new Trip("falsetrip", "mdahnebzalem", false, true, user);
        TripService.createTrip(newFalseTrip);
        Trip newTrueTrip = new Trip("truetrip", "first false trip in test", true, true, user);
        TripService.createTrip(newTrueTrip);
        List<Trip> trips = TripService.getPublicTrips();
        assertTrue("getPublicTrips: didn't receive public-only trips", trips.size() == size + 1);
    }

    @Test
    public void getTripsByOrganiserId() {
        int size = TripService.getAllTrips().size();
        Trip oldOrganiserTrip = new Trip("falsetrip", "mdahnebzalem", false, true, user);
        TripService.createTrip(oldOrganiserTrip);

        User newUser = new User("newUser", "new@us.er", "yitfluyfkytfglkyu", DateUtil.dateToLong(15, 7, 1992, 0, 0, 0));
        UserService.createUser(newUser);
        Trip newOrganiserTrip = new Trip("truetrip", "first false trip in test", false, true, newUser);
        TripService.createTrip(newOrganiserTrip);

        List<Trip> trips = TripService.getTripsByOrganiserId(user.getId());
        assertTrue("getPublicTrips: didn't receive user-only trips", trips.size() == size + 1);
    }
}
