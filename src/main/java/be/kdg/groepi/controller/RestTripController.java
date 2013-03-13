package be.kdg.groepi.controller;

import be.kdg.groepi.model.*;
import be.kdg.groepi.service.*;
import be.kdg.groepi.utils.DateUtil;
import be.kdg.groepi.utils.VuforiaUtil;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

//import java.sql.Date;

//import java.sql.Date;

@Controller
@RequestMapping("trips")
public class RestTripController {

    private static final Logger logger = Logger.getLogger(RestTripController.class);

    @RequestMapping(value = "/addtrip")
    public String addtrip() {
        System.out.println("AddTrip: Passing through...");
        return "trips/addtrip";
    }

    @RequestMapping(value = "/createTrip", method = RequestMethod.POST)
    public ModelAndView createTrip(HttpSession session, @ModelAttribute("tripObject") Trip trip) {
        User user = (User) session.getAttribute("userObject");
        trip.setOrganiser(user);
        TripService.createTrip(trip);
        return new ModelAndView("trips/view", "tripId", trip.getId().toString());
    }

    @RequestMapping(value = "/view/{tripId}", method = RequestMethod.GET)
    public ModelAndView getTrip(@PathVariable("tripId") String tripId, HttpSession session) {
        Trip trip;
        trip = TripService.getTripById(Long.parseLong(tripId));
        User user = (User) session.getAttribute("userObject");

        if (trip != null) {
            logger.debug("Returning Trip: " + trip.toString() + " with trip #" + tripId);

            Map<Long, String> tripInstanceDates = new HashMap<>();
            Map<Long, String> tripInstanceStartTimes = new HashMap<>();
            Map<Long, String> tripInstanceEndTimes = new HashMap<>();
            SortedSet<TripInstance> tripInstances = new TreeSet<>();

            for (TripInstance tripInstance : TripInstanceService.getAllTripInstancesByTripId(trip.getId())) {
                if (tripInstance.getAvailable() || tripInstance.getOrganiser().getId().equals(user.getId())) {
                    tripInstances.add(tripInstance);
                    tripInstanceDates.put(tripInstance.getId(), DateUtil.formatDate(DateUtil.longToDate(tripInstance.getStartTime())));
                    tripInstanceStartTimes.put(tripInstance.getId(), DateUtil.formatTime(DateUtil.longToDate(tripInstance.getStartTime())));
                    tripInstanceEndTimes.put(tripInstance.getId(), DateUtil.formatTime(DateUtil.longToDate(tripInstance.getEndTime())));
                }
            }

            ModelAndView modelAndView = new ModelAndView("trips/view");
            modelAndView.addObject("tripObject", trip);
            modelAndView.addObject("tripInstances", tripInstances);
            modelAndView.addObject("tripInstanceDates", tripInstanceDates);
            modelAndView.addObject("tripInstanceStartTimes", tripInstanceStartTimes);
            modelAndView.addObject("tripInstanceEndTimes", tripInstanceEndTimes);

            modelAndView.addObject("userObject", session.getAttribute("userObject"));
            return modelAndView;
        } else {
            return new ModelAndView("error/displayerror");
        }
    }

    @RequestMapping(value = "/list")
    public ModelAndView getPublicTrips(HttpSession session) {
        User user = (User) session.getAttribute("userObject");

        SortedSet<Trip> publicTrips = new TreeSet<>();
        SortedSet<Trip> ownTrips = new TreeSet<>();

        for (Trip trip : TripService.getPublicTrips()) {
            publicTrips.add(trip);
        }

        for (Trip trip : TripService.getTripsByOrganiserId(user.getId())) {
            ownTrips.add(trip);
        }

        if (publicTrips.size() == 0) {
            logger.debug("Returning publicTrips containing " + publicTrips.size() + " Trips");
        } else {
            logger.debug("Returning publicTrips is empty");
        }
        if (ownTrips.size() == 0) {
            logger.debug("Returning ownTrips containing " + ownTrips.size() + " Trips");
        } else {
            logger.debug("Returning ownTrips is empty");
        }

        ModelAndView modelAndView = new ModelAndView("trips/list");
        modelAndView.addObject("publicTrips", publicTrips);
        modelAndView.addObject("ownTrips", ownTrips);
        return modelAndView;
    }

    @RequestMapping(value = "/edittrip/{tripId}", method = RequestMethod.GET)
    public ModelAndView editTripView(@PathVariable("tripId") String tripId, HttpSession session) {
        User user = (User) session.getAttribute("userObject");
        Trip trip = TripService.getTripById(Long.parseLong(tripId));
        if (trip.getOrganiser().getId() == user.getId()) {
            ModelAndView modelAndView = new ModelAndView("trips/edittrip");
            modelAndView.addObject("tripObject", trip);
            return modelAndView;
        } else {
            //TODO correcte error weergeven
            return new ModelAndView("error/displayerror");
        }
    }

    @RequestMapping(value = "/updateTrip", method = RequestMethod.POST)
    public ModelAndView updateTrip(@ModelAttribute("tripObject") Trip trip) {
        TripService.updateTrip(trip);
        return new ModelAndView("trips/view", "tripObject", trip);
    }
}