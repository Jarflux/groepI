package be.kdg.groepi.service;

import be.kdg.groepi.model.*;
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
public class RequirementServiceTest {

    User user;
    Trip trip;
    Requirement requirement;
    @Autowired
    protected UserService userService;
    @Autowired
    protected TripService tripService;
    @Autowired
    protected RequirementService requirementService;

    @Before
    public void beforeEachTest() {
        user = new User("Tim", "tim@junittest.com", "tim", dateToLong(4, 5, 2011, 15, 32, 0));
        userService.createUser(user);
        trip = new Trip("Onze eerste trip", "Hopelijk is deze niet te saai!", true, true, user);// trip aanmaken
        tripService.createTrip(trip);
        requirement = new Requirement("Zaklamp", 5, "Zo een ding om licht te geven", trip);
        requirementService.createRequirement(requirement);
    }

    @After
    public void afterEachTest() {
    }

    @Test
    public void createRequirement() {
        assertTrue("createRequirement: requirement was not created",
                requirement.equals(requirementService.getRequirementById(requirement.getId())));
    }

    @Test
    public void updateRequirement() {
        Requirement originalRequirement = new Requirement(requirement.getName(),
                requirement.getAmount(), requirement.getDescription(), requirement.getTrip());
        requirement.setName("New name");
        requirement.setAmount(25);
        requirement.setDescription("HOLY SHITBALLS THAT'S A LOT OF FLASHLIGHTS");
        requirementService.updateRequirement(requirement);
        assertFalse("updateRequirement: requirement was not updated",
                originalRequirement.equals(requirementService.getRequirementById(requirement.getId())));
    }

    @Test
    public void deleteRequirements() {
        while (!requirementService.getAllRequirements().isEmpty()) {
            Requirement firstReq = requirementService.getAllRequirements().get(0);
            requirementService.deleteRequirement(firstReq);
        }
        assertTrue("deleteRequirements: requirements were not deleted",
                requirementService.getAllRequirements().isEmpty());
    }
}
