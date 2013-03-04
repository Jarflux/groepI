package be.kdg.groepi.service;

import be.kdg.groepi.model.*;
import be.kdg.groepi.utils.DateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static be.kdg.groepi.utils.DateUtil.dateToLong;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Tim
 * Date: 1/03/13
 * Time: 11:57
 * To change this template use File | Settings | File Templates.
 */
public class RequirementServiceTest {
    User user;
    Trip trip;
    //    Stop stop;
//    Answer answer;
//    StopInstance stopInstance;
//    TripInstance tripInstance;
    //    AnswerInstance answerInstance;
    Requirement requirement;

    @Before
    public void beforeEachTest() {
        user = new User("Tim", "tim@junittest.com", "tim", dateToLong(4, 5, 2011, 15, 32, 0));
        UserService.createUser(user);
        trip = new Trip("Onze eerste trip", "Hopelijk is deze niet te saai!", true, true, user);// trip aanmaken
        TripService.createTrip(trip);

        requirement = new Requirement("Zaklamp", 5, "Zo een ding om licht te geven", trip);
        RequirementService.createRequirement(requirement);
    }

    @After
    public void afterEachTest() {

    }

    @Test
    public void createRequirement() {
        assertTrue("createRequirement: requirement was not created",
                requirement.equals(RequirementService.getRequirementById(requirement.getId())));
    }

    @Test
    public void updateRequirement() {
        Requirement originalRequirement = new Requirement(requirement.getName(),
                requirement.getAmount(), requirement.getDescription(), requirement.getTrip());

        requirement.setName("New name");
        requirement.setAmount(25);
        requirement.setDescription("HOLY SHITBALLS THAT'S A LOT OF FLASHLIGHTS");

        RequirementService.updateRequirement(requirement);

        assertFalse("updateRequirement: requirement was not updated",
                originalRequirement.equals(RequirementService.getRequirementById(requirement.getId())));
    }

    @Test
    public void deleteRequirements() {
        while (!RequirementService.getAllRequirements().isEmpty()) {
            Requirement firstReq = RequirementService.getAllRequirements().get(0);
            RequirementService.deleteRequirement(firstReq);
        }
        assertTrue("deleteRequirements: requirements were not deleted",
                RequirementService.getAllRequirements().isEmpty());
    }

}
