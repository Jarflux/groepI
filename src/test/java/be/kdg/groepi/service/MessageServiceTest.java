package be.kdg.groepi.service;

import be.kdg.groepi.model.*;
import be.kdg.groepi.utils.DateUtil;
import static be.kdg.groepi.utils.DateUtil.dateToLong;
import static org.junit.Assert.*;

import org.junit.After;
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
public class MessageServiceTest {

    User user;
    Trip trip;
    TripInstance tripInstance;
    Message message;
    @Autowired
    protected UserService userService;
    @Autowired
    protected TripService tripService;
    @Autowired
    protected MessageService messageService;
    @Autowired
    protected TripInstanceService tripInstanceService;

    @Before
    public void beforeEachTest() {
        user = new User("Tim", "tim@junittest.com", "tim", dateToLong(4, 5, 2011, 15, 32, 0));
        userService.createUser(user);
        trip = new Trip("Onze eerste trip", "Hopelijk is deze niet te saai!", true, true, user);
        tripService.createTrip(trip);
        tripInstance = new TripInstance("Tripje 1", "Eerste uitvoering van de 'Onze eerste trip'-trip", true,
                DateUtil.dateToLong(2, 3, 2013, 12, 0, 0), DateUtil.dateToLong(2, 3, 2013, 16, 0, 0), user, trip);
        tripInstanceService.createTripInstance(tripInstance);
        message = new Message("Voorbeeldbericht over voorbeelden en berichten waarin deze kunnen zitten",
                DateUtil.dateToLong(2, 3, 2013, 17, 16, 0), tripInstance, user);
        messageService.createMessage(message);
    }

    @After
    public void afterEachTest() {
    }

    @Test
    public void createMessage() {
        assertTrue("createMessage: message was not created", message.equals(messageService.getMessageById(message.getId())));
    }

    @Test
    public void updateMessage() {
        Message originalMessage = new Message(message.getContent(), message.getDate(), message.getTripInstance(), message.getUser());
        message.setContent("Ofnee, we gaan het hebben over eenhoorns");
        message.setDate(DateUtil.dateToLong(2, 3, 2013, 17, 19, 0));
        messageService.createMessage(message);
        assertFalse("updateMessage: message was not updated", originalMessage.equals(messageService.getMessageById(message.getId())));
    }

    @Test
    public void deleteMessage() {
        assertNotNull("deleteMessave: Message must exist", messageService.getMessageById(message.getId()));
        messageService.deleteMessage(message);
        assertNull("deleteMessage: Message may not exist", messageService.getMessageById(message.getId()));
    }
}
