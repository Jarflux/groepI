package be.kdg.groepi.service;

import be.kdg.groepi.model.*;
import be.kdg.groepi.utils.DateUtil;
import static be.kdg.groepi.utils.DateUtil.dateToLong;
import org.junit.After;
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
public class AnswerInstanceServiceTest {

    User user;
    Trip trip;
    Stop stop;
    TripInstance tripInstance;
    AnswerInstance answerInstance;
    long id;
    @Autowired
    protected UserService userService;
    @Autowired
    protected TripService tripService;
    @Autowired
    protected TripInstanceService tripInstanceService;
    @Autowired
    protected StopService stopService;
    @Autowired
    protected AnswerInstanceService answerInstanceService;
    @Autowired
    protected AnswerService answerService;

    @Before
    public void beforeEachTest() {
        user = new User("Gregory", "gregory@trippie.com", "greg", dateToLong(4, 5, 1988, 15, 32, 0));
        userService.createUser(user);
        trip = new Trip("Stadswandeling, Antwerp Edition", "Een wandeling doorheen het centrum met als afsluiter een etentje op het nieuwe Zuid.", true, true, true, user);
        tripService.createTrip(trip);
        stop = new Stop("Groenplaats", "4.399166", "51.221212", 1, 1, 1, "Van wie is dit stambeeld?", 1000, trip);
        stopService.createStop(stop);
        Answer answer1 = new Answer("Van Gogh", false, stop);
        answerService.createAnswer(answer1);
        Answer answer2 = new Answer("Rubens", true, stop);
        answerService.createAnswer(answer2);
        Answer answer3 = new Answer("Picasso", false, stop);
        answerService.createAnswer(answer3);
        Answer answer4 = new Answer("Rembrandt", false, stop);
        answerService.createAnswer(answer4);   
        stop.getAnswers().add(answer1);
        stop.getAnswers().add(answer2);
        stop.getAnswers().add(answer3);
        stop.getAnswers().add(answer4);
        tripInstance = new TripInstance("KdG's Stadswandeling, Antwerp Edition", "Karel de Grote organizeert een stadswandeling met Trippie Trip Advisor", true,
                DateUtil.dateToLong(2, 3, 2013, 12, 0, 0), DateUtil.dateToLong(2, 3, 2013, 16, 0, 0), user, trip);
        tripInstanceService.createTripInstance(tripInstance);
        answerInstance = new AnswerInstance(answer2, user, tripInstance);
        answerInstanceService.createAnswerInstance(answerInstance);
    }

    @After
    public void afterEachTest() {
        stopService.deleteStop(stop);
        tripService.deleteTrip(trip);
        userService.deleteUser(user);
    }

    @Test
    public void createAnswerInstance() {
        assertTrue("createAnswerInstance: answerInstance was not created",
                answerInstance.equals(answerInstanceService.getAnswerInstanceById(answerInstance.getId())));
    }

    @Test
    public void updateAnswerInstance() {
        /*answerInstance.setAnswer(stop.getAnswers().get(1));*/
        answerInstanceService.updateAnswerInstance(answerInstance);
        answerInstance = answerInstanceService.getAnswerInstanceById(answerInstance.getId());
        assertTrue("Answer should be correct now:", answerInstance.isCorrect());
    }

    @Test
    public void deleteAnswerInstance() {
        answerInstanceService.deleteAnswerInstance(answerInstance);
        assertTrue("deleteAnswerInstance: Answer was not deleted",answerInstanceService.getAnswerInstanceById(answerInstance.getId()) == null);
    }
}
