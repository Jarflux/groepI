package be.kdg.groepi.service;

import be.kdg.groepi.model.Answer;
import be.kdg.groepi.model.Stop;
import be.kdg.groepi.model.Trip;
import be.kdg.groepi.model.User;
import static be.kdg.groepi.utils.DateUtil.dateToLong;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Transactional
public class AnswerServiceTest {

    User user;
    Trip trip;
    Stop stop;
    Answer answer;
    @Autowired
    protected UserService userService;
    @Autowired
    protected TripService tripService;
    @Autowired
    protected StopService stopService;
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
        answer = new Answer("Den Tester", false, stop);
        answerService.createAnswer(answer);
        stop = stopService.getStopById(stop.getId());
    }

    @After
    public void afterEachTest() {
        stopService.deleteStop(stop);
        tripService.deleteTrip(trip);
        userService.deleteUser(user);
    }

    @Test
    public void createAnswer() {
        assertTrue("createAnswer: answer was not created", answer.equals(answerService.getAnswerById(answer.getId())));
    }

    @Test
    public void updateAnswer() {
        answer.setAnswerText("Den Tester, Edited");
        answer.setIsCorrect(true);
        answer.setStop(stop);
        answerService.updateAnswer(answer);
        assertTrue("updateAnswer: answer was not updated", answer.equals(answerService.getAnswerById(answer.getId())));
    }

    @Test
    public void deleteAnswer() {
        assertNotNull("deleteAnswer: Answer must exist", answerService.getAnswerById(answer.getId()));
        answerService.deleteAnswer(answer);
        assertNull("deleteAnswer: Answer may not exist", answerService.getAnswerById(answer.getId()));
    }

    @Test
    public void saveAnswerCollection() {
        Answer answer1 = new Answer("Van Gogh", false, stop);
        answerService.createAnswer(answer1);
        Answer answer2 = new Answer("Rubens", true, stop);
        answerService.createAnswer(answer2);
        Answer answer3 = new Answer("Picasso", false, stop);
        answerService.createAnswer(answer3);
        Answer answer4 = new Answer("Rembrandt", false, stop);
        answerService.createAnswer(answer4);   
        /*stop.getAnswers().add(answer1);
        stop.getAnswers().add(answer2);
        stop.getAnswers().add(answer3);
        stop.getAnswers().add(answer4);*/
        /*stopService.updateStop(stop);*/
        stop = stopService.getStopById(stop.getId());
        assertTrue("saveAnswerCollection: Answers were not added", answerService.getAnswersByStopID(stop.getId()).size() == 5);
        stop.setCorrectAnswer(answer4.getId());
        stopService.updateStop(stop);
        stop = stopService.getStopById(stop.getId());
        assertTrue("New Correct Answer:", stop.getAnswers().get(4).getIsCorrect());
    }
}
