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

public class AnswerServiceTest {
    User user;
    Trip trip;
    Stop stop;
    Answer answer;

    @Before
    public void beforeEachTest() {
        user = new User("Gregory", "gregory@trippie.com", "greg", dateToLong(4, 5, 1988, 15, 32, 0));
        UserService.createUser(user);
        trip = new Trip("Stadswandeling, Antwerp Edition", "Een wandeling doorheen het centrum met als afsluiter een etentje op het nieuwe Zuid.", true, true, user);// trip aanmaken
        TripService.createTrip(trip);
        stop = new Stop("Groenplaats",  "4.399166", "51.221212", 1, 1, 1, "Van wie is dit stambeeld?", trip);
        StopService.createStop(stop);
        answer = new Answer("Den Tester", false, stop);
        AnswerService.createAnswer(answer);
        stop = StopService.getStopById(stop.getId());
    }

    @After
    public void afterEachTest() {
        StopService.deleteStop(stop);
        TripService.deleteTrip(trip);
        UserService.deleteUser(user);
    }

    @Test
    public void createAnswer() {
        assertTrue("createAnswer: answer was not created", answer.equals(AnswerService.getAnswerById(answer.getId())));
    }

    @Test
    public void updateAnswer() {
        answer.setAnswerText("Den Tester, Edited");
        answer.setIsCorrect(true);
        answer.setStop(stop);
        AnswerService.updateAnswer(answer);
        assertTrue("updateAnswer: answer was not updated", answer.equals(AnswerService.getAnswerById(answer.getId())));
    }

    @Test
    public void deleteAnswer() {

        for (Answer answer : stop.getAnswers())
        {
            AnswerService.deleteAnswer(answer);
        }
        assertTrue(AnswerService.getAnswersByStopID(stop.getId()).isEmpty());
    }

    @Test
    public void saveAnswerCollection() {
        stop.getAnswers().add(new Answer("Van Gogh", false, stop));
        stop.getAnswers().add(new Answer("Rubens", true, stop));
        stop.getAnswers().add(new Answer("Picasso", false, stop));
        stop.getAnswers().add(new Answer("Rembrandt", false, stop));
        StopService.updateStop(stop);
        //stop = StopService.getStopById(stop.getId());
        assertTrue(AnswerService.getAnswersByStopID(stop.getId()).size() == 5);
    }

    @Test
    public void deleteStopWithAnswers() {
        long stopId = stop.getId();
        StopService.deleteStop(stop);
        boolean test = AnswerService.getAnswersByStopID(stopId).isEmpty();
        assertTrue(AnswerService.getAnswersByStopID(stopId).isEmpty());
    }
}
