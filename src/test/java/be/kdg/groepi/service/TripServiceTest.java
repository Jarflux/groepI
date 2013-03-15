package be.kdg.groepi.service;

import be.kdg.groepi.model.Requirement;
import be.kdg.groepi.model.Trip;
import be.kdg.groepi.model.User;
import be.kdg.groepi.utils.DateUtil;
import static be.kdg.groepi.utils.DateUtil.dateToLong;
import java.util.List;
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
public class TripServiceTest {

    private Trip trip;
    private User user;
    @Autowired
    protected UserService userService;
    @Autowired
    protected TripService tripService;
    @Autowired
    protected RequirementService requirementService;

    @Before
    public void beforeEachTest() {
        user = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        userService.createUser(user);
        trip = new Trip("Onze eerste trip", "Hopelijk is deze niet te saai!", true, true, user);// trip aanmaken
        tripService.createTrip(trip);
    }

    @After
    public void afterEachTest() {
        trip = null;
        List<Trip> trips = tripService.getAllTrips();
        for (Trip trip : trips) {
            tripService.deleteTrip(trip);
        }
    }

    @Test
    public void createTrip() {
        assertEquals("createTrip: ", trip, tripService.getTripById(trip.getId()));
    }

    @Test
    public void updateTrip() {
        trip.setAvailable(Boolean.FALSE);
        trip.setDescription("Ho-ho-ho edited");

        tripService.updateTrip(trip);
        assertEquals("updateTrip: ", trip, tripService.getTripById(trip.getId()));
    }

    @Test
    public void deleteTrip() {
        assertNotNull("deleteTrip: Trip found", tripService.getTripById(trip.getId()));
        tripService.deleteTrip(trip);
        assertNull("deleteTrip: Trip not found", tripService.getTripById(trip.getId()));
    }

    @Test
    public void addAndRemoveRequirementToTrip() {
        assertTrue("Trip: trip should have no requirements", trip.getRequirements().isEmpty());
        Requirement requirement = new Requirement("BEN", 1, "", trip);
        requirementService.createRequirement(requirement);
        trip.addRequirementToTrip(requirement);
        assertFalse("Trip: trip should have requirements", trip.getRequirements().isEmpty());
        trip.removeRequirementFromTrip(requirement);
        assertTrue("Trip: trip should have no requirements", trip.getRequirements().isEmpty());
    }

    @Test
    public void getPublicTrips() {
        int size = tripService.getPublicTrips().size();
        Trip newFalseTrip = new Trip("falsetrip", "mdahnebzalem", false, true, user);
        tripService.createTrip(newFalseTrip);
        Trip newTrueTrip = new Trip("truetrip", "first false trip in test", true, true, user);
        tripService.createTrip(newTrueTrip);
        List<Trip> trips = tripService.getPublicTrips();
        assertTrue("getPublicTrips: didn't receive public-only trips", trips.size() == size + 1);
    }

    @Test
    public void getTripsByOrganiserId() {
        int size = tripService.getTripsByOrganiserId(user.getId()).size();
        Trip oldOrganiserTrip = new Trip("newusertrip", "mdahnebzalem", false, true, user);
        tripService.createTrip(oldOrganiserTrip);

        User newUser = new User("newUser", "new@us.er", "yitfluyfkytfglkyu", DateUtil.dateToLong(15, 7, 1992, 0, 0, 0));
        userService.createUser(newUser);
        Trip newOrganiserTrip = new Trip("newusertrip", "first new user trip in test", false, true, newUser);
        tripService.createTrip(newOrganiserTrip);

        List<Trip> trips = tripService.getTripsByOrganiserId(user.getId());
        assertTrue("getPublicTrips: didn't receive user-only trips", trips.size() == size + 1);
    }
}
