/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.controller;

import be.kdg.groepi.model.*;
import be.kdg.groepi.service.*;
import be.kdg.groepi.utils.CompareUtil;
import be.kdg.groepi.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Author: Ben Oeyen
 * Date: 1/03/13
 * Class: Filler REST Controller
 * Description: Controller to handle REST service calls
 */
@Controller
@RequestMapping("database")
public class RestFillerController {

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
            UserService.createUser(django);
            UserService.createUser(ben);
            UserService.createUser(tim);
            UserService.createUser(mitch);
            UserService.createUser(vincent);
            UserService.createUser(gregory);
            UserService.createUser(dave);

            Trip tripA = new Trip("Onze eerste trip", "Hopelijk is deze niet saai!", true, true, ben);
            Trip tripB = new Trip("Onze tweede trip", "Hopelijk is deze niet saaier!", true, true, tim);
            Trip tripC = new Trip("Onze derde trip", "Hopelijk is deze niet de saaiste!", true, true, mitch);
            Trip tripD = new Trip("The Candy Land Tour", "Een rit doorheen de domeinen van Mr Calvin Candy", true, true, django);
            TripService.createTrip(tripA);
            TripService.createTrip(tripB);
            TripService.createTrip(tripC);
            TripService.createTrip(tripD);

            Stop stop = new Stop("Front Gate", "4.399166", "51.221212", 1, 1, 1, "Wie speelt Calvin Candy in de film Django Unchained?", 1000, tripD);
            StopService.createStop(stop);
            AnswerService.createAnswer(new Answer("Leonardo DiCaprio", true, stop));
            AnswerService.createAnswer(new Answer("Jamie Foxx", false, stop));
            AnswerService.createAnswer(new Answer("David Shultz", false, stop));
            AnswerService.createAnswer(new Answer("Samuel L Jackson", false, stop));
            StopService.createStop(new Stop("The Fields", "4.399786", "51.221212", 1, 1, 1, "De velden waar allerlei stuff wordt geteeld.", 1000, tripD));
            StopService.createStop(new Stop("The Mansion", "4.392166", "51.227812", 1, 1, 1, "De residentie van Mr Candy himself.", 1000, tripD));

            StopService.createStop(new Stop("Stopplaats 1", "4.399166", "51.221212", 1, 1, 1, "Dit is de eerste stopplaats", 1000, tripA));
            StopService.createStop(new Stop("Stopplaats 2", "4.399166", "51.221212", 2, 1, 1, "Dit is de tweede stopplaats", 1000, tripA));
            StopService.createStop(new Stop("Stopplaats 3", "4.399166", "51.221212", 3, 1, 1, "Dit is de derde stopplaats", 1000, tripA));

            StopService.createStop(new Stop("Stopplaats 1", "4.399166", "51.221212", 1, 1, 1, "Dit is de eerste stopplaats", 1000, tripB));
            StopService.createStop(new Stop("Stopplaats 2", "4.399166", "51.221212", 2, 1, 1, "Dit is de tweede stopplaats", 1000, tripB));
            StopService.createStop(new Stop("Stopplaats 3", "4.399166", "51.221212", 3, 1, 1, "Dit is de derde stopplaats", 1000, tripB));

            StopService.createStop(new Stop("Stopplaats 1", "4.399166", "51.221212", 1, 1, 1, "Dit is de eerste stopplaats", 1000, tripC));
            StopService.createStop(new Stop("Stopplaats 2", "4.399166", "51.221212", 2, 1, 1, "Dit is de tweede stopplaats", 1000, tripC));
            StopService.createStop(new Stop("Stopplaats 3", "4.399166", "51.221212", 3, 1, 1, "Dit is de derde stopplaats", 1000, tripC));

            long startDate1 = DateUtil.dateToLong(27, 02, 2013, 16, 00, 00);
            long endDate1 = DateUtil.dateToLong(27, 02, 2013, 20, 00, 00);
            long startDate2 = DateUtil.dateToLong(27, 03, 2013, 16, 00, 00);
            long endDate2 = DateUtil.dateToLong(27, 03, 2013, 20, 00, 00);
            long startDate3 = DateUtil.dateToLong(27, 04, 2013, 16, 00, 00);
            long endDate3 = DateUtil.dateToLong(27, 04, 2013, 20, 00, 00);

            TripInstance tripinstance1A = new TripInstance("Bachelor feestje", "eerste Trip, eerste Instance", false, startDate1, endDate1, gregory, tripA);
            TripInstance tripinstance2A = new TripInstance("Bachelor feestje", "eerste Trip, tweede Instance", true, startDate2, endDate2, vincent, tripA);
            TripInstance tripinstance3A = new TripInstance("Bachelor feestje", "eerste Trip, derde Instance", false, startDate3, endDate3, tim, tripA);

            TripInstance tripinstance1B = new TripInstance("Bachelor feestje", "tweede  Trip, eerste Instance", false, startDate1, endDate1, ben, tripB);
            TripInstance tripinstance2B = new TripInstance("Bachelor feestje", "tweede  Trip, tweede Instance", false, startDate2, endDate2, mitch, tripB);
            TripInstance tripinstance3B = new TripInstance("Bachelor feestje", "tweede Trip, derde Instance", false, startDate3, endDate3, dave, tripB);

            TripInstance tripinstance1C = new TripInstance("Bachelor feestje", "ederde Trip, eerste Instance", false, startDate1, endDate1, mitch, tripC);
            TripInstance tripinstance2C = new TripInstance("Bachelor feestje", "derde Trip, tweede Instance", false, startDate2, endDate2, ben, tripC);
            TripInstance tripinstance3C = new TripInstance("Bachelor feestje", "derde Trip, derde Instance", false, startDate3, endDate3, dave, tripC);

            TripInstanceService.createTripInstance(tripinstance1A);
            TripInstanceService.createTripInstance(tripinstance2A);
            TripInstanceService.createTripInstance(tripinstance3A);
            TripInstanceService.createTripInstance(tripinstance1B);
            TripInstanceService.createTripInstance(tripinstance2B);
            TripInstanceService.createTripInstance(tripinstance3B);
            TripInstanceService.createTripInstance(tripinstance1C);
            TripInstanceService.createTripInstance(tripinstance2C);
            TripInstanceService.createTripInstance(tripinstance3C);


        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "home";
    }
}
