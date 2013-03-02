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
public class RequirementInstanceServiceTest {
    User user;
    Trip trip;
    //    Stop stop;
//    Answer answer;
//    StopInstance stopInstance;
    TripInstance tripInstance;
    //    AnswerInstance answerInstance;
    //  Requirement requirement;
    RequirementInstance requirementInstance;

    @Before
    public void beforeEachTest() {
        user = new User("Tim", "tim@junittest.com", "tim", dateToLong(4, 5, 2011, 15, 32, 0));
        UserService.createUser(user);
        trip = new Trip("Onze eerste trip", "Hopelijk is deze niet te saai!", true, true, user);// trip aanmaken
        TripService.createTrip(trip);

        tripInstance = new TripInstance("Tripje 1", "Eerste uitvoering van de 'Onze eerste trip'-trip", true,
                DateUtil.dateToLong(2, 3, 2013, 12, 0, 0), DateUtil.dateToLong(2, 3, 2013, 16, 0, 0), user, trip);
        TripInstanceService.createTripInstance(tripInstance);

        requirementInstance = new RequirementInstance("Zaklamp", 5, "Zo een ding om licht te geven", tripInstance, user);
        RequirementInstanceService.createRequirementInstance(requirementInstance);
    }

    @After
    public void afterEachTest() {

    }

    @Test
    public void createRequirementInstance() {
        assertTrue("createRequirementInstance: requirementInstance was not created",
                requirementInstance.equals(RequirementInstanceService.getRequirementInstanceById(requirementInstance.getId())));
    }

    @Test
    public void updateRequirementInstance() {
        RequirementInstance originalRequirementInstance = new RequirementInstance(requirementInstance.getName(),
                requirementInstance.getAmount(), requirementInstance.getDescription(), requirementInstance.getTripInstance());

        requirementInstance.setAmount(25);
        requirementInstance.setDescription("HOLY SHITBALLS THAT'S A LOT OF FLASHLIGHTS");

        RequirementInstanceService.updateRequirementInstance(requirementInstance);

        assertFalse("updateRequirementInstance: requirementInstance was not updated",
                originalRequirementInstance.equals(RequirementInstanceService.getRequirementInstanceById(requirementInstance.getId())));
    }

    @Test
    public void deleteRequirementInstances() {
        while (!RequirementInstanceService.getAllRequirementInstances().isEmpty()) {
            RequirementInstance firstReq = RequirementInstanceService.getAllRequirementInstances().get(0);
            RequirementInstanceService.deleteRequirementInstance(firstReq);
        }
        assertTrue("deleteRequirementsInstance: requirementInstances were not deleted",
                RequirementInstanceService.getAllRequirementInstances().isEmpty());
    }

}
