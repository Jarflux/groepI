package be.kdg.groepi.controller;

import be.kdg.groepi.model.*;
import be.kdg.groepi.service.*;
import be.kdg.groepi.utils.DateUtil;

import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller("restTripController")
@RequestMapping("template")
public class RestTripController {

    private static final Logger logger = Logger.getLogger(RestTripController.class);
    @Autowired
    protected TripService tripService;
    @Autowired
    protected TripInstanceService tripInstanceService;

    @RequestMapping(value = "/add")
    public String addtrip() {
        System.out.println("AddTrip: Passing through...");
        return "template/add";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createTrip(HttpSession session, @ModelAttribute("tripObject") Trip trip) {
        User user = (User) session.getAttribute("userObject");
        trip.setOrganiser(user);
        tripService.createTrip(trip);
        return new ModelAndView("template/view", "tripObject", trip);
    }

    @RequestMapping(value = "/view/{tripId}", method = RequestMethod.GET)
    public ModelAndView getTrip(@PathVariable("tripId") String tripId, HttpSession session) {
        Trip trip;
        trip = tripService.getTripById(Long.parseLong(tripId));
        User user = (User) session.getAttribute("userObject");

        if (trip != null) {
            logger.debug("Returning Trip: " + trip.toString() + " with trip #" + tripId);

            Map<Long, String> tripInstanceDates = new HashMap<>();
            Map<Long, String> tripInstanceStartTimes = new HashMap<>();
            Map<Long, String> tripInstanceEndTimes = new HashMap<>();
            SortedSet<TripInstance> tripInstances = new TreeSet<>();

            for (TripInstance tripInstance : tripInstanceService.getAllTripInstancesByTripId(trip.getId())) {
                if (tripInstance.getAvailable() || tripInstance.getOrganiser().getId().equals(user.getId())) {
                    tripInstances.add(tripInstance);
                    tripInstanceDates.put(tripInstance.getId(), DateUtil.formatDate(DateUtil.longToDate(tripInstance.getStartTime())));
                    tripInstanceStartTimes.put(tripInstance.getId(), DateUtil.formatTime(DateUtil.longToDate(tripInstance.getStartTime())));
                    tripInstanceEndTimes.put(tripInstance.getId(), DateUtil.formatTime(DateUtil.longToDate(tripInstance.getEndTime())));
                }
            }

            ModelAndView modelAndView = new ModelAndView("template/view");
            modelAndView.addObject("tripObject", trip);
            modelAndView.addObject("tripInstances", tripInstances);
            modelAndView.addObject("tripInstanceDates", tripInstanceDates);
            modelAndView.addObject("tripInstanceStartTimes", tripInstanceStartTimes);
            modelAndView.addObject("tripInstanceEndTimes", tripInstanceEndTimes);

            modelAndView.addObject("userObject", session.getAttribute("userObject"));
            return modelAndView;
        } else {
            logger.debug("RestTripController - getTrip - Triptemplate not found");
            ModelAndView modelAndView = new ModelAndView("error/displayerror");
            modelAndView.addObject("errorid", "tripNotFound");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/list")
    public ModelAndView getTripList(HttpSession session) {
        User user = (User) session.getAttribute("userObject");

        SortedSet<Trip> publicTrips = new TreeSet<>();
        SortedSet<Trip> ownTrips = new TreeSet<>();

        for (Trip trip : tripService.getPublicTrips()) {
            publicTrips.add(trip);
        }

        for (Trip trip : tripService.getTripsByOrganiserId(user.getId())) {
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

        ModelAndView modelAndView = new ModelAndView("template/list");
        modelAndView.addObject("publicTrips", publicTrips);
        modelAndView.addObject("ownTrips", ownTrips);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{tripId}", method = RequestMethod.GET)
    public ModelAndView editTripView(@PathVariable("tripId") String tripId, HttpSession session) {
        User user = (User) session.getAttribute("userObject");
        Trip trip = tripService.getTripById(Long.parseLong(tripId));
        if (trip != null) {
            if (trip.getOrganiser().getId().equals(user.getId())) {
                ModelAndView modelAndView = new ModelAndView("template/edit");
                modelAndView.addObject("tripObject", trip);
                return modelAndView;
            } else {
                logger.debug("RestTripController - editTripView - User not authorized to edit trip");
                ModelAndView modelAndView = new ModelAndView("error/displayerror");
                modelAndView.addObject("errorid", "userNotAuthorizedToEditTrip");
                return modelAndView;
            }
        } else {
            logger.debug("RestTripController - editTripView - Triptemplate not found");
            ModelAndView modelAndView = new ModelAndView("error/displayerror");
            modelAndView.addObject("errorid", "tripNotFound");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateTrip(@ModelAttribute("tripObject") Trip trip) {    
        tripService.updateTrip(trip);
        return new ModelAndView("redirect:/template/view/" + trip.getId());
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(Exception e) {
        logger.debug("RestTripController - Unexpected exception", e);
        ModelAndView modelAndView = new ModelAndView("error/displayerror");
        modelAndView.addObject("errorid", "defaultError");
        return modelAndView;
    }
}