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
public class AnswerInstanceServiceTest {
    User user;
    Trip trip;
    Stop stop;
    //Answer answer;
    TripInstance tripInstance;
    AnswerInstance answerInstance;
    long id;

    @Before
    public void beforeEachTest() {
        user = new User("Gregory", "gregory@trippie.com", "greg", dateToLong(4, 5, 1988, 15, 32, 0));
        UserService.createUser(user);
        trip = new Trip("Stadswandeling, Antwerp Edition", "Een wandeling doorheen het centrum met als afsluiter een etentje op het nieuwe Zuid.", true, true, user);// trip aanmaken
        TripService.createTrip(trip);
        stop = new Stop("Groenplaats",  "4.399166", "51.221212", 1, 1, 1, "Van wie is dit stambeeld?", trip);
        stop.getAnswers().add(new Answer("Van Gogh", false, null));
        stop.getAnswers().add(new Answer("Rubens", true, null));
        stop.getAnswers().add(new Answer("Picasso", false, null));
        stop.getAnswers().add(new Answer("Rembrandt", false, null));
        StopService.createStop(stop);
        tripInstance = new TripInstance("KdG's Stadswandeling, Antwerp Edition", "Karel de Grote organizeert een stadswandeling met Trippie Trip Advisor", true,
                DateUtil.dateToLong(2, 3, 2013, 12, 0, 0), DateUtil.dateToLong(2, 3, 2013, 16, 0, 0), user, trip);
        stop = StopService.getStopById(stop.getId());
        TripInstanceService.createTripInstance(tripInstance);
        answerInstance = new AnswerInstance(stop.getAnswers().get(0),user,tripInstance);
        AnswerInstanceService.createAnswerInstance(answerInstance);
        id = answerInstance.getId();
    }

    @After
    public void afterEachTest() {
        StopService.deleteStop(stop);
        TripService.deleteTrip(trip);
        UserService.deleteUser(user);
    }

    @Test
    public void createAnswerInstance() {
        long id2 = answerInstance.getId();
        assertTrue("createAnswerInstance: answerInstance was not created",
                answerInstance.equals(AnswerInstanceService.getAnswerInstanceById(answerInstance.getId())));
    }


    @Test
    public void updateAnswerInstance() {
        answerInstance.setAnswer(stop.getAnswers().get(1));
        AnswerInstanceService.updateAnswerInstance(answerInstance);
        answerInstance = AnswerInstanceService.getAnswerInstanceById(answerInstance.getId());
        assertTrue("Answer should be correct now:", answerInstance.isCorrect());
    }

    @Test
    public void deleteAnswerInstances() {
        AnswerInstanceService.deleteAnswerInstance(answerInstance);
        assertTrue(AnswerInstanceService.getAllAnswerInstances().isEmpty());
    }

}
