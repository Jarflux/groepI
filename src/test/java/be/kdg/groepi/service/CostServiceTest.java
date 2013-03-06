package be.kdg.groepi.service;

import be.kdg.groepi.model.*;
import be.kdg.groepi.utils.DateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
public class CostServiceTest {
    User user;
    Trip trip;
    TripInstance tripInstance;
    Cost cost;

    @Before
    public void beforeEachTest() {
        user = new User("Tim", "tim@junittest.com", "tim", dateToLong(4, 5, 2011, 15, 32, 0));
        UserService.createUser(user);
        trip = new Trip("Onze eerste trip", "Hopelijk is deze niet te saai!", true, true, user);// trip aanmaken
        TripService.createTrip(trip);

        tripInstance = new TripInstance("Tripje 1", "Eerste uitvoering van de 'Onze eerste trip'-trip", true,
                DateUtil.dateToLong(2, 3, 2013, 12, 0, 0), DateUtil.dateToLong(2, 3, 2013, 16, 0, 0), user, trip);
        TripInstanceService.createTripInstance(tripInstance);

        cost = new Cost("Bak bier", 14.50, tripInstance, user);
        CostService.createCost(cost);
    }

    @After
    public void afterEachTest() {

    }

    @Test
    public void createCost() {
        assertTrue("createCost: cost was not created",
                cost.equals(CostService.getCostById(cost.getId())));
    }

    @Test
    public void updateCost() {
        Cost originalCost = new Cost(cost.getDescription(), cost.getAmount(), cost.getTripInstance(), cost.getUser());

        cost.setAmount(24.62);
        cost.setDescription("Bak Duvel");

        CostService.updateCost(cost);

        assertFalse("updateCost: cost was not updated", originalCost.equals(CostService.getCostById(cost.getId())));
    }

    @Test
    public void deleteCosts() {
        while (!CostService.getAllCosts().isEmpty()) {
            Cost firstCost = CostService.getAllCosts().get(0);
            CostService.deleteCost(firstCost);
        }
        assertTrue(CostService.getAllCosts().isEmpty());
    }

}
