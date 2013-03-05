package be.kdg.groepi.controller;

import be.kdg.groepi.model.*;
import be.kdg.groepi.service.*;
import be.kdg.groepi.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Calendar;
//import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/doAddTripRequirement", method = RequestMethod.POST)
    public ModelAndView doAddTripRequirement(@RequestParam(value = "tripId") String tripId,/*
             @ModelAttribute("requirementObject") Requirement requirement*/
                                             @RequestParam(value = "name") String name,
                                             @RequestParam(value = "amount") Long amount,
                                             @RequestParam(value = "description") String description) {

        Trip trip = TripService.getTripById(Long.parseLong(tripId));
        Requirement requirement = new Requirement(name, amount, description, trip);
        RequirementService.createRequirement(requirement);
        //       trip.addRequirementToTrip(requirement);
        trip = TripService.getTripById(trip.getId());
        return new ModelAndView("trips/view", "tripObject", trip);
    }

    //TODO trip-instance van maken?
    @RequestMapping(value = "/view/{tripId}", method = RequestMethod.GET)
    public ModelAndView getTrip(@PathVariable("tripId") String tripId, HttpSession session) {
        Trip trip;
        trip = TripService.getTripById(Long.parseLong(tripId));

        if (trip != null) {
            logger.debug("Returning Trip: " + trip.toString() + " with trip #" + tripId);
            ModelAndView modelAndView = new ModelAndView("trips/view");
            modelAndView.addObject("tripObject", trip);
            /*modelAndView.addObject("stopListObject", StopService.getAllTripStopsByTripId(trip.getId()));*/
            modelAndView.addObject("tripInstanceListObject", TripInstanceService.getAllTripInstancesByTripId(trip.getId()));
            return modelAndView;
        } else {
            return new ModelAndView("error/displayerror");
        }
    }

    @RequestMapping(value = "/addstop/{tripId}", method = RequestMethod.GET)
    public ModelAndView addStop(@PathVariable("tripId") String tripId) {
        Trip trip = TripService.getTripById(Long.parseLong(tripId));
        return new ModelAndView("trips/addstop", "tripObject", trip);
    }

    @RequestMapping(value = "/createStop", method = RequestMethod.POST)
    public ModelAndView createStop(HttpSession session, @ModelAttribute("stopObject") Stop stop, @RequestParam(value = "tripId") String tripId) {
        Trip trip = TripService.getTripById(Long.parseLong(tripId));
        stop.setTrip(trip);
        StopService.createStop(stop);
        return new ModelAndView("trips/addstop", "tripObject", trip);
    }

    @RequestMapping(value = "/list")
    public ModelAndView getPublicTrips() {
        List<TripInstance> tripInstanceList = TripInstanceService.getPublicTripInstances();
        if (tripInstanceList != null) {
            logger.debug("Returning TripList containing " + tripInstanceList.size() + " TripInstances");
        } else {
            logger.debug("Returning TripList = NULL");
        }
        Map<Long, String> tripInstanceStartDates = new HashMap<>();
        Map<Long, String> tripInstanceEndDates = new HashMap<>();
        for (TripInstance tripInstance : tripInstanceList) {
            tripInstanceStartDates.put(tripInstance.getTrip().getId(),
                    DateUtil.formatDate(DateUtil.longToDate(tripInstance.getStartTime())));
            tripInstanceEndDates.put(tripInstance.getTrip().getId(),
                    DateUtil.formatDate(DateUtil.longToDate(tripInstance.getEndTime())));
        }
        ModelAndView modelAndView = new ModelAndView("trips/list");
        modelAndView.addObject("tripInstanceListObject", tripInstanceList);
        modelAndView.addObject("tripInstanceStartDates", tripInstanceStartDates);
        modelAndView.addObject("tripInstanceEndDates", tripInstanceEndDates);
        return modelAndView;
    }

    @RequestMapping(value = "/editTrip/{tripId}", method = RequestMethod.GET)
    public ModelAndView editTripView(@PathVariable("tripId") String tripId) {
        ModelAndView modelAndView = new ModelAndView("trips/edittrip");
        modelAndView.addObject("tripObject", TripService.getTripById(Long.parseLong(tripId)));
        return modelAndView;
    }

    @RequestMapping(value = "/updateTrip", method = RequestMethod.POST)
    public ModelAndView updateTrip(@ModelAttribute("tripObject") Trip trip) {
        TripService.updateTrip(trip);
        return new ModelAndView("trips/view", "tripObject", trip);
    }

    @RequestMapping(value = "/jointrip", method = RequestMethod.POST)
    public ModelAndView joinTrip(HttpSession session, @RequestParam("tripId") String tripId) {
        User sessionUser = (User) session.getAttribute("userObject");
        TripInstance tripInstance = TripInstanceService.getTripInstanceById(Long.parseLong(tripId));
        tripInstance.addParticipantToTripInstance(sessionUser);
        TripInstanceService.updateTripInstance(tripInstance);
        return getPublicTrips();
    }

    @RequestMapping(value = "/editStop/{stopId}", method = RequestMethod.GET)
    public ModelAndView editStopView(@PathVariable("stopId") String stopId) {
        ModelAndView modelAndView = new ModelAndView("trips/editstop");
        modelAndView.addObject("stopObject", StopService.getStopById(Long.parseLong(stopId)));
        return modelAndView;
    }

    @RequestMapping(value = "/updateStop", method = RequestMethod.POST)
    public ModelAndView updateStop(@ModelAttribute("tripObject") Stop stop) {
        StopService.updateStop(stop);
        return new ModelAndView("trips/view", "stopObject", stop);
    }

    @RequestMapping(value = "/addrequirement/{tripId}", method = RequestMethod.GET)
    public ModelAndView addRequirement(@PathVariable(value = "tripId") String tripId) {
        return new ModelAndView("trips/addtriprequirement", "tripId", tripId);
    }
    /*return new ModelAndView("trips/addtriprequirement", "tripId", trip.getId().toString());*/


    ////////////////////////////////////////


    @RequestMapping(value = "/addinstance/{tripId}")
    public ModelAndView addinstance(@PathVariable("tripId") String tripId/*, HttpSession session*/) {
        System.out.println("AddInstance: Passing through...");
        Trip trip = TripService.getTripById(Long.parseLong(tripId));
        return new ModelAndView("trips/addinstance", "tripObject", trip);
    }

    @RequestMapping(value = "/createinstance", method = RequestMethod.POST)
    public ModelAndView createinstance(HttpSession session, @ModelAttribute("tripInstanceObject") TripInstance tripInstance,
                                       @RequestParam(value = "tripId") Long tripId,
                                       @RequestParam(value = "date") String date,
                                       @RequestParam(value = "startTimeString") String startTimeString,
                                       @RequestParam(value = "endTimeString") String endTimeString
    ) {

        User user = (User) session.getAttribute("userObject");
        Trip trip = TripService.getTripById(tripId);

        tripInstance.setOrganiser(user);
        tripInstance.setTrip(trip);

        for (Requirement req : trip.getRequirements()) {
            RequirementInstance reqIns = new RequirementInstance(req, tripInstance);
            RequirementInstanceService.createRequirementInstance(reqIns);

            tripInstance.addRequirementInstanceToTripInstance(reqIns);
        }

        for (Stop stop : trip.getStops()) {
            StopInstance stopIns = new StopInstance(stop, tripInstance);
            StopInstanceService.createStopInstance(stopIns);


        }

        long startTime = DateUtil.dateStringToLong(date, startTimeString);
        long endTime = DateUtil.dateStringToLong(date, endTimeString);

        if (startTime >= endTime) {
            endTime += 24 * 60 * 60 * 1000;
        }

        tripInstance.setStartTime(startTime);
        tripInstance.setEndTime(endTime);

        TripInstanceService.createTripInstance(tripInstance);


        return new ModelAndView("trips/viewinstance", "tripInstanceId", tripInstance.getId().toString());
//        return new ModelAndView("trips/addtrip");
    }
}