/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.controller;

import be.kdg.groepi.model.*;
import be.kdg.groepi.service.*;
import be.kdg.groepi.utils.CompareUtil;
import be.kdg.groepi.utils.DateUtil;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author: Ben Oeyen
 * Date: 1/03/13
 * Class: Filler REST Controller
 * Description: Controller to handle REST service calls
 */
@Controller("restFillerController")
@RequestMapping("database")
public class RestFillerController {

    private static final Logger logger = Logger.getLogger(RestFillerController.class);

    @Autowired
    protected UserService userService;
    @Autowired
    protected TripService tripService;
    @Autowired
    protected TripInstanceService tripInstanceService;
    @Autowired
    protected StopService stopService;
    @Autowired
    protected AnswerService answerService;

    @RequestMapping(value = "/fill")
    public String fillDatabase() {
        System.out.println("fillDatabase: Passing through...");
        // Create Databank na test
        try {
            long dateOfBirth = DateUtil.dateToLong(24, 3, 1988, 0, 0, 0);

            User django = new User("Django", "django@candyland.com", CompareUtil.getHashedPassword("Django"), dateOfBirth);
            User ben = new User("Ben", "Ben@trippie.com", CompareUtil.getHashedPassword("ben"), dateOfBirth);
            User tim = new User("Tim", "Tim@trippie.com", CompareUtil.getHashedPassword("tim"), dateOfBirth);
            User mitch = new User("Mitch", "Mitch@trippie.com", CompareUtil.getHashedPassword("mitch"), dateOfBirth);
            User vincent = new User("Vincent", "Vincent@trippie.com", CompareUtil.getHashedPassword("vincent"), dateOfBirth);
            User gregory = new User("Gregory", "Gregory@trippie.com", CompareUtil.getHashedPassword("gregory"), dateOfBirth);
            User dave = new User("Dave", "Dave@trippie.com", CompareUtil.getHashedPassword("dave"), dateOfBirth);
            userService.createUser(django);
            userService.createUser(ben);
            userService.createUser(tim);
            userService.createUser(mitch);
            userService.createUser(vincent);
            userService.createUser(gregory);
            userService.createUser(dave);

            Trip tripA = new Trip("Onze eerste trip", "Hopelijk is deze niet saai!", true, true, ben);
            Trip tripB = new Trip("Onze tweede trip", "Hopelijk is deze niet saaier!", true, true, tim);
            Trip tripC = new Trip("Onze derde trip", "Hopelijk is deze niet de saaiste!", true, true, mitch);
            Trip tripD = new Trip("The Candy Land Tour", "Een rit doorheen de domeinen van Mr Calvin Candy", true, true, django);
            tripService.createTrip(tripA);
            tripService.createTrip(tripB);
            tripService.createTrip(tripC);
            tripService.createTrip(tripD);

            Stop stop = new Stop("Front Gate", "4.399166", "51.221212", 1, 1, 1, "Wie speelt Calvin Candy in de film Django Unchained?", 1000, tripD);
            stopService.createStop(stop);
            answerService.createAnswer(new Answer("Leonardo DiCaprio", true, stop));
            answerService.createAnswer(new Answer("Jamie Foxx", false, stop));
            answerService.createAnswer(new Answer("David Shultz", false, stop));
            answerService.createAnswer(new Answer("Samuel L Jackson", false, stop));
            stopService.createStop(new Stop("The Fields", "4.399786", "51.221212", 1, 1, 1, "De velden waar allerlei stuff wordt geteeld.", 1000, tripD));
            stopService.createStop(new Stop("The Mansion", "4.392166", "51.227812", 1, 1, 1, "De residentie van Mr Candy himself.", 1000, tripD));

            stopService.createStop(new Stop("Stopplaats 1", "4.399166", "51.221212", 1, 1, 1, "Dit is de eerste stopplaats", 1000, tripA));
            stopService.createStop(new Stop("Stopplaats 2", "4.399166", "51.221212", 2, 1, 1, "Dit is de tweede stopplaats", 1000, tripA));
            stopService.createStop(new Stop("Stopplaats 3", "4.399166", "51.221212", 3, 1, 1, "Dit is de derde stopplaats", 1000, tripA));

            stopService.createStop(new Stop("Stopplaats 1", "4.399166", "51.221212", 1, 1, 1, "Dit is de eerste stopplaats", 1000, tripB));
            stopService.createStop(new Stop("Stopplaats 2", "4.399166", "51.221212", 2, 1, 1, "Dit is de tweede stopplaats", 1000, tripB));
            stopService.createStop(new Stop("Stopplaats 3", "4.399166", "51.221212", 3, 1, 1, "Dit is de derde stopplaats", 1000, tripB));

            stopService.createStop(new Stop("Stopplaats 1", "4.399166", "51.221212", 1, 1, 1, "Dit is de eerste stopplaats", 1000, tripC));
            stopService.createStop(new Stop("Stopplaats 2", "4.399166", "51.221212", 2, 1, 1, "Dit is de tweede stopplaats", 1000, tripC));
            stopService.createStop(new Stop("Stopplaats 3", "4.399166", "51.221212", 3, 1, 1, "Dit is de derde stopplaats", 1000, tripC));

            long startDate1 = DateUtil.dateToLong(27, 02, 2013, 16, 00, 00);
            long endDate1 = DateUtil.dateToLong(27, 02, 2013, 20, 00, 00);
            long startDate2 = DateUtil.dateToLong(27, 03, 2013, 16, 00, 00);
            long endDate2 = DateUtil.dateToLong(27, 03, 2013, 20, 00, 00);
            long startDate3 = DateUtil.dateToLong(27, 04, 2013, 16, 00, 00);
            long endDate3 = DateUtil.dateToLong(27, 04, 2013, 20, 00, 00);

            TripInstance tripinstance1A = new TripInstance("Bachelor feestje", "eerste Trip, eerste Instance", false, startDate1, endDate1, gregory, tripA);
            TripInstance tripinstance2A = new TripInstance("Bachelor feestje", "eerste Trip, tweede Instance", true, startDate2, endDate2, vincent, tripA);
            TripInstance tripinstance3A = new TripInstance("Bachelor feestje", "eerste Trip, derde Instance", false, startDate3, endDate3, tim, tripA);

            TripInstance tripinstance1B = new TripInstance("Stadswandeling Gent", "tweede  Trip, eerste Instance", false, startDate1, endDate1, ben, tripB);
            TripInstance tripinstance2B = new TripInstance("Stadswandeling Gent", "tweede  Trip, tweede Instance", false, startDate2, endDate2, mitch, tripB);
            TripInstance tripinstance3B = new TripInstance("Stadswandeling Gent", "tweede Trip, derde Instance", false, startDate3, endDate3, dave, tripB);

            TripInstance tripinstance1C = new TripInstance("Stadswandeling Antwerpen", "ederde Trip, eerste Instance", false, startDate1, endDate1, mitch, tripC);
            TripInstance tripinstance2C = new TripInstance("Stadswandeling Antwerpen", "derde Trip, tweede Instance", false, startDate2, endDate2, ben, tripC);
            TripInstance tripinstance3C = new TripInstance("Stadswandeling Antwerpen", "derde Trip, derde Instance", false, startDate3, endDate3, dave, tripC);

            tripInstanceService.createTripInstance(tripinstance1A);
            tripInstanceService.createTripInstance(tripinstance2A);
            tripInstanceService.createTripInstance(tripinstance3A);
            tripInstanceService.createTripInstance(tripinstance1B);
            tripInstanceService.createTripInstance(tripinstance2B);
            tripInstanceService.createTripInstance(tripinstance3B);
            tripInstanceService.createTripInstance(tripinstance1C);
            tripInstanceService.createTripInstance(tripinstance2C);
            tripInstanceService.createTripInstance(tripinstance3C);

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "home";
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(Exception e) {
        logger.debug("RestFillerController - Unexpected exception", e);
        ModelAndView modelAndView = new ModelAndView("error/displayerror");
        modelAndView.addObject("errorid", "defaultError");
        return modelAndView;
    }
}
