package be.kdg.groepi.controller;

import be.kdg.groepi.model.*;
import be.kdg.groepi.service.*;
import be.kdg.groepi.utils.DateUtil;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import java.sql.Date;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            modelAndView.addObject("userObject", session.getAttribute("userObject"));
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
        stop.setStopnumber(trip.getStops().size());
        stop.setTrip(trip);
        StopService.createStop(stop);
        return new ModelAndView("trips/editstop", "tripObject", trip);
    }

    @RequestMapping(value = "/list")
    public ModelAndView getPublicTrips(HttpSession session) {
        User user = (User) session.getAttribute("userObject");
        List<Trip> publicTrips = TripService.getPublicTrips();
        List<Trip> ownTrips = TripService.getTripsByOrganiserId(user.getId());

        if (publicTrips != null) {
            logger.debug("Returning publicTrips containing " + publicTrips.size() + " Trips");
        } else {
            logger.debug("Returning publicTrips = NULL");
        }
        if (ownTrips != null) {
            logger.debug("Returning ownTrips containing " + ownTrips.size() + " Trips");
        } else {
            logger.debug("Returning ownTrips = NULL");
        }

        ModelAndView modelAndView = new ModelAndView("trips/list");
        modelAndView.addObject("publicTrips", publicTrips);
        modelAndView.addObject("ownTrips", ownTrips);
        return modelAndView;
    }

    @RequestMapping(value = "/edittrip/{tripId}", method = RequestMethod.GET)
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
        return getPublicTrips(session);
    }

    @RequestMapping(value = "/editStop/{stopId}", method = RequestMethod.GET)
    public ModelAndView editStopView(@PathVariable("stopId") String stopId) {
        ModelAndView modelAndView = new ModelAndView("trips/editstop");
        modelAndView.addObject("stopObject", StopService.getStopById(Long.parseLong(stopId)));
        return modelAndView;
    }

    @RequestMapping(value = "/updateStop", method = RequestMethod.POST)
//    public ModelAndView updateStop(@ModelAttribute("tripObject") Stop stop) {
    public ModelAndView updateStop(@ModelAttribute("stopObject") Stop stop) {
        StopService.updateStop(stop);
        return new ModelAndView("trips/view", "stopObject", stop);
    }

    @RequestMapping(value = "/addrequirement/{tripId}", method = RequestMethod.GET)
    public ModelAndView addRequirement(@PathVariable(value = "tripId") String tripId) {
        return new ModelAndView("trips/addtriprequirement", "tripId", tripId);
    }
    /*return new ModelAndView("trips/addtriprequirement", "tripId", trip.getId().toString());*/

    @RequestMapping(value = "/showUserTripParticipations/{userId}", method = RequestMethod.GET)
    public void getUserTripParticipations(@PathVariable(value = "userId") String userId,HttpServletRequest request, HttpServletResponse response) {
        List<TripInstance> trips = TripInstanceService.getTripInstancesByUserId(Long.parseLong(userId));
        for (TripInstance trip : trips){

        }
        JSONObject jo = new JSONObject();
        try {
            jo.put("trips",trips);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            response.getWriter().print(jo.toString());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    ////////////////////////////////////////


    @RequestMapping(value = "/addinstance/{tripId}")
    public ModelAndView addinstance(@PathVariable("tripId") String tripId, HttpSession session) {
        System.out.println("AddInstance: Passing through...");
        Trip trip = TripService.getTripById(Long.parseLong(tripId));
        ModelAndView modelAndView = new ModelAndView("trips/addinstance");
        modelAndView.addObject("tripObject", trip);
//        User user = (User) session.getAttribute("userObject");
//        modelAndView.addObject("userObject", user);

        return modelAndView;//("trips/addinstance", "tripObject", trip);
    }

    @RequestMapping(value = "/createinstance", method = RequestMethod.POST)
    public ModelAndView createinstance(HttpSession session, @ModelAttribute("tripInstanceObject") TripInstance tempTripInstance,
                                       @RequestParam(value = "tripId") Long tripId,
                                       @RequestParam(value = "date") String date,
                                       @RequestParam(value = "startTimeString") String startTimeString,
                                       @RequestParam(value = "endTimeString") String endTimeString,
                                       @RequestParam(value = "repeatable") String repeatable,
                                       @RequestParam(value = "endDate") String endDate) {

        User user = (User) session.getAttribute("userObject");
        Trip trip = TripService.getTripById(tripId);
        tempTripInstance.setOrganiser(user);
        tempTripInstance.setTrip(trip);

        if (tempTripInstance.getAvailable() == null) tempTripInstance.setAvailable(false);

        long startTime = DateUtil.dateStringToLong(date, startTimeString);
        long endTime = DateUtil.dateStringToLong(date, endTimeString);

        if (startTime >= endTime) endTime += 24 * 60 * 60 * 1000;

        if (trip.getRepeatable() != null && trip.getRepeatable()) {
            long timeBetween = DateUtil.dateStringToLong(endDate, startTimeString) - DateUtil.dateStringToLong(date, startTimeString);
            long interval = 24 * 60 * 60 * 1000;
            if (repeatable.equals("weekly")) interval *= 7;

            long repeatCount = timeBetween / interval + 1;

            for (int i = 0; i < repeatCount; i++) {
                long newStartTime = startTime + (i * interval);
                long newEndTime = endTime + (i * interval);
                createNewTripInstance(tempTripInstance, newStartTime, newEndTime, user);
            }
            return getTrip(trip.getId().toString(), session);
        } else {
            TripInstance tripInstance = createNewTripInstance(tempTripInstance, startTime, endTime, user);
            return getTripInstance(tripInstance.getId().toString(), session);
        }
//        return getTripInstance(tripInstance.getId().toString(), session);
//        return new ModelAndView("trips/viewinstance", "tripInstanceId", tripInstance.getId().toString());
    }

    private TripInstance createNewTripInstance(TripInstance tempTripInstance, long startTime, long endTime, User user) {
        TripInstance tripInstance = new TripInstance(tempTripInstance, startTime, endTime);
        tripInstance.addParticipantToTripInstance(user);
        TripInstanceService.createTripInstance(tripInstance);

        for (Requirement req : tempTripInstance.getTrip().getRequirements()) {
            RequirementInstance reqIns = new RequirementInstance(req, tripInstance);
            RequirementInstanceService.createRequirementInstance(reqIns);
        }

        for (Stop stop : tempTripInstance.getTrip().getStops()) {
            StopInstance stopIns = new StopInstance(stop, tripInstance);
            StopInstanceService.createStopInstance(stopIns);
        }

        return tripInstance;
    }

    @RequestMapping(value = "/editinstance/{tripInstanceId}", method = RequestMethod.GET)
    public ModelAndView editInstanceView(@PathVariable("tripInstanceId") String tripInstanceId) {
        //TODO: tripInstance not found -> page not found (404)
        //TODO: check user --> not valid = error page user not authorized (401)
        ModelAndView modelAndView = new ModelAndView("trips/editinstance");
        TripInstance tripInstance = TripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId));
        modelAndView.addObject("tripInstanceObject", tripInstance);
//        modelAndView.addObject("date", DateUtil.formatDate(tripInstance.getStartTime()));
        modelAndView.addObject("startTimeString", DateUtil.formatTime(tripInstance.getStartTime()));
        modelAndView.addObject("endTimeString", DateUtil.formatTime(tripInstance.getEndTime()));
        return modelAndView;
    }

    @RequestMapping(value = "/updateinstance", method = RequestMethod.POST)
    public ModelAndView updateInstance(//@ModelAttribute("tripInstanceObject") TripInstance tripInstance,
                                       @RequestParam(value = "tripInstanceId") Long tripInstanceId,
                                       @RequestParam(value = "title") String title,
                                       @RequestParam(value = "description") String description,
                                       @RequestParam(value = "available") Boolean available,
                                       @RequestParam(value = "date") String date,
                                       @RequestParam(value = "startTimeString") String startTimeString,
                                       @RequestParam(value = "endTimeString") String endTimeString) {

        TripInstance tripInstance = TripInstanceService.getTripInstanceById(tripInstanceId);
        tripInstance.setTitle(title);
        tripInstance.setDescription(description);
        tripInstance.setAvailable(available);

        long startTime = DateUtil.dateStringToLong(date, startTimeString);
        long endTime = DateUtil.dateStringToLong(date, endTimeString);

        if (startTime >= endTime) endTime += 24 * 60 * 60 * 1000;
        tripInstance.setStartTime(startTime);
        tripInstance.setEndTime(endTime);

        TripInstanceService.updateTripInstance(tripInstance);
        return new ModelAndView("trips/view", "tripInstanceObject", tripInstance);
    }


    @RequestMapping(value = "/listinstance")
    public ModelAndView getPublicTripInstances(HttpSession session) {
        User user = (User) session.getAttribute("userObject");
        List<TripInstance> publicTripInstances = TripInstanceService.getPublicTripInstances();
        List<TripInstance> ownTripInstances = TripInstanceService.getTripInstancesByOrganiserId(user.getId());

        if (publicTripInstances != null) {
            logger.debug("Returning publicTripInstances containing " + publicTripInstances.size() + " TripInstances");
        } else {
            logger.debug("Returning publicTripInstances = NULL");
        }
        if (ownTripInstances != null) {
            logger.debug("Returning ownTripInstances containing " + ownTripInstances.size() + " TripInstances");
        } else {
            logger.debug("Returning ownTripInstances = NULL");
        }

        Map<Long, String> publicTripInstanceDates = new HashMap<>();
        Map<Long, String> publicTripInstanceStartTimes = new HashMap<>();
        Map<Long, String> publicTripInstanceEndTimes = new HashMap<>();
        for (TripInstance tripInstance : publicTripInstances) {
            publicTripInstanceDates.put(tripInstance.getId(),
                    DateUtil.formatDate(DateUtil.longToDate(tripInstance.getStartTime())));
            publicTripInstanceStartTimes.put(tripInstance.getId(),
                    DateUtil.formatTime(DateUtil.longToDate(tripInstance.getStartTime())));
            publicTripInstanceEndTimes.put(tripInstance.getId(),
                    DateUtil.formatTime(DateUtil.longToDate(tripInstance.getEndTime())));
        }

        Map<Long, String> ownTripInstanceDates = new HashMap<>();
        Map<Long, String> ownTripInstanceStartTimes = new HashMap<>();
        Map<Long, String> ownTripInstanceEndTimes = new HashMap<>();
        for (TripInstance tripInstance : ownTripInstances) {
            ownTripInstanceDates.put(tripInstance.getId(),
                    DateUtil.formatDate(DateUtil.longToDate(tripInstance.getStartTime())));
            ownTripInstanceStartTimes.put(tripInstance.getId(),
                    DateUtil.formatTime(DateUtil.longToDate(tripInstance.getStartTime())));
            ownTripInstanceEndTimes.put(tripInstance.getId(),
                    DateUtil.formatTime(DateUtil.longToDate(tripInstance.getEndTime())));
        }

        ModelAndView modelAndView = new ModelAndView("trips/listinstance");
        modelAndView.addObject("publicTripInstances", publicTripInstances);
        modelAndView.addObject("publicTripInstanceDates", publicTripInstanceDates);
        modelAndView.addObject("publicTripInstanceStartTimes", publicTripInstanceStartTimes);
        modelAndView.addObject("publicTripInstanceEndTimes", publicTripInstanceEndTimes);

        modelAndView.addObject("ownTripInstances", ownTripInstances);
        modelAndView.addObject("ownTripInstanceDates", ownTripInstanceDates);
        modelAndView.addObject("ownTripInstanceStartTimes", ownTripInstanceStartTimes);
        modelAndView.addObject("ownTripInstanceEndTimes", ownTripInstanceEndTimes);


        return modelAndView;
    }

    @RequestMapping(value = "/viewinstance/{tripInstanceId}", method = RequestMethod.GET)
    public ModelAndView getTripInstance(@PathVariable("tripInstanceId") String tripInstanceId, HttpSession session) {
        TripInstance tripInstance;
        tripInstance = TripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId));

        if (tripInstance != null) {
            logger.debug("Returning TripInstance: " + tripInstance.toString() + " with tripInstance #" + tripInstanceId);
            ModelAndView modelAndView = new ModelAndView("trips/viewinstance");
            modelAndView.addObject("tripInstanceObject", tripInstance);
            modelAndView.addObject("userObject", session.getAttribute("userObject"));
            modelAndView.addObject("date", DateUtil.formatDate(tripInstance.getStartTime()));
            modelAndView.addObject("startTimeString", DateUtil.formatTime(tripInstance.getStartTime()));
            modelAndView.addObject("endTimeString", DateUtil.formatTime(tripInstance.getEndTime()));
            return modelAndView;
        } else {
            return new ModelAndView("error/displayerror");
        }
    }

    @RequestMapping(value = "/addinstancerequirement/{tripInstanceId}", method = RequestMethod.GET)
    public ModelAndView addInstanceRequirement(@PathVariable(value = "tripInstanceId") String tripInstanceId) {
        return new ModelAndView("trips/addinstancerequirement", "tripInstanceId", tripInstanceId);
    }

    @RequestMapping(value = "/doaddinstancerequirement", method = RequestMethod.POST)
    public ModelAndView doAddInstanceRequirement(@RequestParam(value = "tripInstanceId") String tripInstanceId,/*
             @ModelAttribute("requirementObject") Requirement requirement*/
                                                 @RequestParam(value = "name") String name,
                                                 @RequestParam(value = "amount") Long amount,
                                                 @RequestParam(value = "description") String description) {

        TripInstance tripInstance = TripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId));
        RequirementInstance requirementInstance = new RequirementInstance(name, amount, description, tripInstance);
        RequirementInstanceService.createRequirementInstance(requirementInstance);
        //       trip.addRequirementToTrip(requirement);
//        tripInstance.addRequirementInstanceToTripInstance(requirementInstance);
//        TripInstanceService.updateTripInstance();
//        return "trips/viewInstance/" + tripInstanceId;
        return new ModelAndView("trips/viewInstance", "tripInstanceObject", tripInstance);
    }

    @RequestMapping(value = "/addmessage/{tripInstanceId}", method = RequestMethod.GET)
    public ModelAndView addMessage(@PathVariable("tripInstanceId") String tripInstanceId) {
        return new ModelAndView("trips/addmessage", "tripInstanceId", tripInstanceId);
    }

    @RequestMapping(value = "/doaddmessage", method = RequestMethod.POST)
    public ModelAndView doAddMessage(HttpSession session, @RequestParam(value = "tripInstanceId") String tripInstanceId,
                                     @RequestParam(value = "content") String content) {

        TripInstance tripInstance = TripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId));
        User sessionUser = (User) session.getAttribute("userObject");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        Long date = cal.getTime().getTime();
        Message message = new Message(content, date, tripInstance, sessionUser);
        MessageService.createMessage(message);
        return new ModelAndView("trips/viewinstance", "tripInstanceObject", tripInstance);
    }
}