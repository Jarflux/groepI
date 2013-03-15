package be.kdg.groepi.service;

import be.kdg.groepi.model.*;
import be.kdg.groepi.utils.DateUtil;
import static be.kdg.groepi.utils.DateUtil.dateToLong;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Tim
 * Date: 1/03/13
 * Time: 11:57
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Transactional
public class RequirementInstanceServiceTest {

    User user;
    Trip trip;
    TripInstance tripInstance;
    RequirementInstance requirementInstance;
    @Autowired
    protected UserService userService;
    @Autowired
    protected TripService tripService;
    @Autowired
    protected TripInstanceService tripInstanceService;
    @Autowired
    protected RequirementInstanceService requirementInstanceService;

    @Before
    public void beforeEachTest() {
        user = new User("Tim", "tim@junittest.com", "tim", dateToLong(4, 5, 2011, 15, 32, 0));
        userService.createUser(user);
        trip = new Trip("Onze eerste trip", "Hopelijk is deze niet te saai!", true, true, true, user);// trip aanmaken
        tripService.createTrip(trip);
        tripInstance = new TripInstance("Tripje 1", "Eerste uitvoering van de 'Onze eerste trip'-trip", true,
                DateUtil.dateToLong(2, 3, 2013, 12, 0, 0), DateUtil.dateToLong(2, 3, 2013, 16, 0, 0), user, trip);
        tripInstanceService.createTripInstance(tripInstance);
        requirementInstance = new RequirementInstance("Zaklamp", 5, "Zo een ding om licht te geven", tripInstance, user);
        requirementInstanceService.createRequirementInstance(requirementInstance);
    }

    @After
    public void afterEachTest() {
    }

    @Test
    public void createRequirementInstance() {
        assertTrue("createRequirementInstance: requirementInstance was not created",
                requirementInstance.equals(requirementInstanceService.getRequirementInstanceById(requirementInstance.getId())));
    }

    @Test
    public void updateRequirementInstance() {
        RequirementInstance originalRequirementInstance = new RequirementInstance(requirementInstance.getName(),
                requirementInstance.getAmount(), requirementInstance.getDescription(), requirementInstance.getTripInstance());
        requirementInstance.setAmount(25);
        requirementInstance.setDescription("HOLY SHITBALLS THAT'S A LOT OF FLASHLIGHTS");
        requirementInstanceService.updateRequirementInstance(requirementInstance);
        assertFalse("updateRequirementInstance: requirementInstance was not updated",
                originalRequirementInstance.equals(requirementInstanceService.getRequirementInstanceById(requirementInstance.getId())));
    }

    @Test
    public void deleteRequirementInstances() {
        while (!requirementInstanceService.getAllRequirementInstances().isEmpty()) {
            RequirementInstance firstReq = requirementInstanceService.getAllRequirementInstances().get(0);
            requirementInstanceService.deleteRequirementInstance(firstReq);
        }
        assertTrue("deleteRequirementsInstance: requirementInstances were not deleted",
                requirementInstanceService.getAllRequirementInstances().isEmpty());
    }
}
