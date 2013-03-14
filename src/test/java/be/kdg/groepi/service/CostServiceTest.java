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
public class CostServiceTest {

    User user;
    Trip trip;
    TripInstance tripInstance;
    Cost cost;
    @Autowired
    protected UserService userService;
    @Autowired
    protected TripService tripService;
    @Autowired
    protected TripInstanceService tripInstanceService;
    @Autowired
    protected CostService costService;

    @Before
    public void beforeEachTest() {
        user = new User("Tim", "tim@junittest.com", "tim", dateToLong(4, 5, 2011, 15, 32, 0));
        userService.createUser(user);
        trip = new Trip("Onze eerste trip", "Hopelijk is deze niet te saai!", true, true, user);// trip aanmaken
        tripService.createTrip(trip);

        tripInstance = new TripInstance("Tripje 1", "Eerste uitvoering van de 'Onze eerste trip'-trip", true,
                DateUtil.dateToLong(2, 3, 2013, 12, 0, 0), DateUtil.dateToLong(2, 3, 2013, 16, 0, 0), user, trip);
        tripInstanceService.createTripInstance(tripInstance);

        cost = new Cost("Bak bier", 14.50, tripInstance, user);
        costService.createCost(cost);
    }

    @After
    public void afterEachTest() {
    }

    @Test
    public void createCost() {
        assertTrue("createCost: cost was not created",
                cost.equals(costService.getCostById(cost.getId())));
    }

    @Test
    public void updateCost() {
        Cost originalCost = new Cost(cost.getDescription(), cost.getAmount(), cost.getTripInstance(), cost.getUser());

        cost.setAmount(24.62);
        cost.setDescription("Bak Duvel");

        costService.updateCost(cost);

        assertFalse("updateCost: cost was not updated", originalCost.equals(costService.getCostById(cost.getId())));
    }

    @Test
    public void deleteCosts() {
        //TODO: Als Delete niet werkt dan faalt de test niet maar hebt ge een endless loop
        while (!costService.getAllCosts().isEmpty()) {
            Cost firstCost = costService.getAllCosts().get(0);
            costService.deleteCost(firstCost);
        }
        //TODO: Add explanation string to assert
        assertTrue(costService.getAllCosts().isEmpty());
    }
}
