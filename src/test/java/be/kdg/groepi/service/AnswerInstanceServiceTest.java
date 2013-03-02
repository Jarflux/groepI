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
    Answer answer;
    StopInstance stopInstance;
    TripInstance tripInstance;
    AnswerInstance answerInstance;

    @Before
    public void beforeEachTest() {
        user = new User("Tim", "tim@junittest.com", "tim", dateToLong(4, 5, 2011, 15, 32, 0));
        UserService.createUser(user);
        trip = new Trip("Onze eerste trip", "Hopelijk is deze niet te saai!", true, true, user);// trip aanmaken
        TripService.createTrip(trip);

        tripInstance = new TripInstance("Tripje 1", "Eerste uitvoering van de 'Onze eerste trip'-trip", true,
                DateUtil.dateToLong(2, 3, 2013, 12, 0, 0), DateUtil.dateToLong(2, 3, 2013, 16, 0, 0), user, trip);
        TripInstanceService.createTripInstance(tripInstance);

        stop = new Stop("Stop 1", "", "", 1, 0, 0, "Eerste Stopplaats", trip);
        StopService.createStop(stop);
        stopInstance = new StopInstance(stop, tripInstance);
        StopInstanceService.createStopInstance(stopInstance);

        List<String> answers = new ArrayList<String>();
        answers.add("Answer 1");
        answers.add("Answer 2");
        answers.add("Answer 3");
        answer = new Answer(answers, 1, "Answer 2 is correct because it too is correct.", stop);
        AnswerService.createAnswer(answer);

        answerInstance = new AnswerInstance(answer, user, stopInstance, answer.getAnswers().get(1), answer.isAnswerCorrect(1));
        AnswerInstanceService.createAnswerInstance(answerInstance);
    }

    @After
    public void afterEachTest() {
/*        if (AnswerService.getAllAnswers().contains(answer)) AnswerService.deleteAnswer(answer);
        StopService.deleteStop(stop);
        TripService.deleteTrip(trip);
        UserService.deleteUser(user);*/
    }

    @Test
    public void createAnswerInstance() {
        assertTrue("createAnswerInstance: answerInstance was not created",
                answerInstance.equals(AnswerInstanceService.getAnswerInstanceById(answerInstance.getId())));
    }


    @Test
    public void updateAnswerInstance() {
        AnswerInstance originalAnswerInstance = new AnswerInstance(answerInstance.getAnswer(), answerInstance.getUser(),
                answerInstance.getStopInstance(), answerInstance.getGivenAnswer(), answerInstance.isCorrect());

        answerInstance.answerQuestion(answer.getAnswers().get(2), answer.isAnswerCorrect(2));

        AnswerInstanceService.updateAnswerInstance(answerInstance);
        AnswerInstance newAnswerInstance = AnswerInstanceService.getAnswerInstanceById(answerInstance.getId());
        assertFalse("updateAnswer: answer was not updated",
                originalAnswerInstance.equals(AnswerInstanceService.getAnswerInstanceById(answerInstance.getId())));
    }

    @Test
    public void deleteAnswerInstances() {
        while (!AnswerInstanceService.getAllAnswerInstances().isEmpty()) {
            AnswerInstance firstAnswerInstance = AnswerInstanceService.getAllAnswerInstances().get(0);
            AnswerInstanceService.deleteAnswerInstance(firstAnswerInstance);
        }
        assertTrue(AnswerInstanceService.getAllAnswerInstances().isEmpty());
    }

}
