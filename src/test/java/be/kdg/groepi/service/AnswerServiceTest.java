package be.kdg.groepi.service;

import be.kdg.groepi.model.Answer;
import be.kdg.groepi.model.Stop;
import be.kdg.groepi.model.Trip;
import be.kdg.groepi.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static be.kdg.groepi.utils.DateUtil.dateToLong;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Tim
 * Date: 1/03/13
 * Time: 11:57
 * To change this template use File | Settings | File Templates.
 */
public class AnswerServiceTest {
    User user;
    Trip trip;
    Stop stop;
    Answer answer;

    @Before
    public void beforeEachTest() {
        user = new User("Tim", "tim@junittest.com", "tim", dateToLong(4, 5, 2011, 15, 32, 0));
        UserService.createUser(user);
        trip = new Trip("Onze eerste trip", "Hopelijk is deze niet te saai!", true, true, user);// trip aanmaken
        TripService.createTrip(trip);
        stop = new Stop("Stoppie", "lalala", "nog meer lalaal", 1, 1, 1, "STOP!", trip);
        //public Stop(String fName, String fLongitude, String fLatitude, Integer fOrder, Integer fType, Integer fDisplayMode, String fStopText, Trip fTrip) {

        StopService.createStop(stop);

        List<String> answers = new ArrayList<String>();
        answers.add("Answer 1");
        answers.add("Answer 2");
        answers.add("Answer 3");
        answer = new Answer(answers, 1, "Answer 2 is correct because it too is correct.", stop);
        AnswerService.createAnswer(answer);
    }

    @After
    public void afterEachTest() {
/*        if (AnswerService.getAllAnswers().contains(answer)) AnswerService.deleteAnswer(answer);
        StopService.deleteStop(stop);
        TripService.deleteTrip(trip);
        UserService.deleteUser(user);*/
    }

    @Test
    public void createAnswer() {
/*        List<String> answers = new ArrayList<String>();
        answers.add("Answer 1");
        answers.add("Answer 2");
        answers.add("Answer 3");
        Answer answer = new Answer(answers, 1, "Answer 2 is correct because it too is correct.", stop);

        AnswerService.createAnswer(answer);*/

        assertTrue("createAnswer: answer was not created", answer.equals(AnswerService.getAnswerById(answer.getId())));
    }


    @Test
    public void updateAnswer() {
        Answer originalAnswer = new Answer(answer.getAnswers(), answer.getCorrectAnswer(), answer.getCorrectAnswerDescription(), answer.getStop());

        answer.setCorrectAnswer(1);
        answer.setCorrectAnswerDescription("One is the one!");

        AnswerService.updateAnswer(answer);
        Answer newAnswer = AnswerService.getAnswerById(answer.getId());
        assertFalse("updateAnswer: answer was not updated", originalAnswer.equals(AnswerService.getAnswerById(answer.getId())));
    }

    @Test
    public void deleteAnswer() {

        while (!AnswerService.getAllAnswers().isEmpty()) {
            Answer firstAnswer = AnswerService.getAllAnswers().get(0);
            AnswerService.deleteAnswer(firstAnswer);
        }
        assertTrue(AnswerService.getAllAnswers().isEmpty());
    }

    @Test
    public void addAndRemoveAnswers() {
        int initialSize = answer.getAnswers().size();
        String newAnswer = "THE A-TEAM";
        answer.addAnswer(newAnswer);
        newAnswer = "THE B-TEAM";
        answer.addAnswer(newAnswer);
        newAnswer = "THE C-TEAM";
        answer.addAnswer(newAnswer);
        AnswerService.updateAnswer(answer);
        assertTrue("addAndRemoveAnswers: answers have not been added", answer.getAnswers().size() > initialSize);

        int tempSize = answer.getAnswers().size();

        answer.removeAnswer("THE A-TEAM");
        AnswerService.updateAnswer(answer);
        tempSize--;
        assertTrue("addAndRemoveAnswers: the answer has not been deleted [removeAnswer(String)]", tempSize == answer.getAnswers().size());

//        answer.removeAnswer(answer.getAnswers().indexOf("THE B-TEAM"));
        answer.setCorrectAnswer(1);
        answer.removeAnswer(2);
        AnswerService.updateAnswer(answer);
        tempSize--;
        assertTrue("addAndRemoveAnswers: the answer has not been deleted [removeAnswer(int) n°1]", tempSize == answer.getAnswers().size());

        List<String> karen = AnswerService.getAllAnswers().get(0).getAnswers();

        answer.setCorrectAnswer(2);
        answer.removeAnswer(1);
        AnswerService.updateAnswer(answer);
        tempSize--;
        assertTrue("addAndRemoveAnswers: the answer has not been deleted [removeAnswer(int) n°2]", tempSize == answer.getAnswers().size());
        assertTrue("addAndRemoveAnswers: the correctAnswer was not changed", answer.getCorrectAnswer() == 1);
        assertTrue("addAndRemoveAnswers: # creates != # deletes", initialSize == answer.getAnswers().size());

        assertFalse("test: ophalen met arrAnswers 1", AnswerService.getAllAnswers().isEmpty());
        Answer testAnswer = AnswerService.getAllAnswers().get(0);

        List<String> testAnswers = testAnswer.getAnswers();
        assertTrue("test: ophalen met arrAnswers 2", testAnswers.size() > 0);
    }

    @Test
    public void isAnswerCorrect() {
        assertTrue("isAnswerCorrect", answer.isAnswerCorrect(1));
        assertFalse("isAnswerCorrect", answer.isAnswerCorrect(0));
    }

}
