package be.kdg.groepi.controller;

import be.kdg.groepi.model.*;
import be.kdg.groepi.service.MessageService;
import be.kdg.groepi.service.RequirementInstanceService;
import be.kdg.groepi.service.TripInstanceService;
import be.kdg.groepi.service.TripService;
import be.kdg.groepi.utils.DateUtil;
import be.kdg.groepi.utils.ModelAndViewUtil;
import java.util.*;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller("restTripInstanceController")
public class RestTripInstanceController {

    private static final Logger logger = Logger.getLogger(RestTripInstanceController.class);
    @Autowired
    protected TripInstanceService tripInstanceService;
    @Autowired
    protected TripService tripService;
    @Autowired
    protected RequirementInstanceService requirementInstanceService;
    @Autowired
    protected MessageService messageService;

    @RequestMapping(value = "/trips/addinstance/{tripId}")
    public ModelAndView addinstance(@PathVariable("tripId") String tripId, HttpSession session) {
        System.out.println("AddInstance: Passing through...");
        Trip trip = tripService.getTripById(Long.parseLong(tripId));
        ModelAndView modelAndView = new ModelAndView("trips/addinstance");
        modelAndView.addObject("tripObject", trip);
        return modelAndView;
    }

    @RequestMapping(value = "/trips/createinstance", method = RequestMethod.POST)
    public ModelAndView createinstance(HttpSession session, @ModelAttribute("tripInstanceObject") TripInstance tempTripInstance,
            @RequestParam(value = "tripId") Long tripId,
            @RequestParam(value = "date") String date,
            @RequestParam(value = "startTimeString") String startTimeString,
            @RequestParam(value = "endTimeString") String endTimeString,
            @RequestParam(value = "repeatable") String repeatable,
            @RequestParam(value = "endDate") String endDate) {

        User user = (User) session.getAttribute("userObject");
        Trip trip = tripService.getTripById(tripId);
        tempTripInstance.setOrganiser(user);
        tempTripInstance.setTrip(trip);

        if (tempTripInstance.getAvailable() == null) {
            tempTripInstance.setAvailable(false);
        }

        long startTime = DateUtil.dateStringToLong(date, startTimeString);
        long endTime = DateUtil.dateStringToLong(date, endTimeString);

        if (startTime >= endTime) {
            endTime += 24 * 60 * 60 * 1000;
        }

        if (trip.getRepeatable() != null && trip.getRepeatable()) {
            long timeBetween = DateUtil.dateStringToLong(endDate, startTimeString) - DateUtil.dateStringToLong(date, startTimeString);
            long interval = 24 * 60 * 60 * 1000;
            if (repeatable.equals("weekly")) {
                interval *= 7;
            }

            long repeatCount = timeBetween / interval + 1;

            for (int i = 0; i < repeatCount; i++) {
                long newStartTime = startTime + (i * interval);
                long newEndTime = endTime + (i * interval);
                createNewTripInstance(tempTripInstance, newStartTime, newEndTime, user);
            }
            return getTripInstances(session);
            //return getTrip(trip.getId().toString(), session);
        } else {
            TripInstance tripInstance = createNewTripInstance(tempTripInstance, startTime, endTime, user);
            return getTripInstance(tripInstance.getId().toString(), session);
        }

    }

    private TripInstance createNewTripInstance(TripInstance tempTripInstance, long startTime, long endTime, User user) {
        TripInstance tripInstance = new TripInstance(tempTripInstance, startTime, endTime);
        tripInstance.addParticipantToTripInstance(user);
        tripInstanceService.createTripInstance(tripInstance);

        for (Requirement req : tempTripInstance.getTrip().getRequirements()) {
            RequirementInstance reqIns = new RequirementInstance(req, tripInstance);
            requirementInstanceService.createRequirementInstance(reqIns);
        }

        return tripInstance;
    }

    @RequestMapping(value = "/trips/editinstance/{tripInstanceId}", method = RequestMethod.GET)
    public ModelAndView editInstanceView(@PathVariable("tripInstanceId") String tripInstanceId) {
        //TODO: tripInstance not found -> page not found (404)
        //TODO: check user --> not valid = error page user not authorized (401)
        ModelAndView modelAndView = new ModelAndView("trips/editinstance");
        TripInstance tripInstance = tripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId));
        modelAndView.addObject("tripInstanceObject", tripInstance);
        modelAndView.addObject("startTimeString", DateUtil.formatTime(tripInstance.getStartTime()));
        modelAndView.addObject("endTimeString", DateUtil.formatTime(tripInstance.getEndTime()));
        return modelAndView;
    }

    @RequestMapping(value = "/trips/updateinstance", method = RequestMethod.POST)
    public ModelAndView updateInstance(@RequestParam(value = "tripInstanceId") Long tripInstanceId,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "available") Boolean available,
            @RequestParam(value = "date") String date,
            @RequestParam(value = "startTimeString") String startTimeString,
            @RequestParam(value = "endTimeString") String endTimeString) {

        TripInstance tripInstance = tripInstanceService.getTripInstanceById(tripInstanceId);
        tripInstance.setTitle(title);
        tripInstance.setDescription(description);
        tripInstance.setAvailable(available);

        long startTime = DateUtil.dateStringToLong(date, startTimeString);
        long endTime = DateUtil.dateStringToLong(date, endTimeString);

        if (startTime >= endTime) {
            endTime += 24 * 60 * 60 * 1000;
        }
        tripInstance.setStartTime(startTime);
        tripInstance.setEndTime(endTime);

        tripInstanceService.updateTripInstance(tripInstance);
        return new ModelAndView("trips/view", "tripInstanceObject", tripInstance);
    }

    @RequestMapping(value = "/trips/listinstance")
    public ModelAndView getTripInstances(HttpSession session) {
        User user = (User) session.getAttribute("userObject");

        SortedSet<TripInstance> publicTripInstances = new TreeSet<>();
        SortedSet<TripInstance> ownTripInstances = new TreeSet<>();

        long today = DateUtil.dateStringToLong(DateUtil.formatDate(Calendar.getInstance().getTime()));

        for (TripInstance tripInstance : tripInstanceService.getPublicTripInstances()) {
            if (tripInstance.getStartTime() > today) {
                publicTripInstances.add(tripInstance);
            }
        }

        for (TripInstance tripInstance : tripInstanceService.getTripInstancesByOrganiserId(user.getId())) {
            if (tripInstance.getStartTime() > today) {
                ownTripInstances.add(tripInstance);
            }
        }

        if (publicTripInstances.size() == 0) {
            logger.debug("Returning publicTripInstances containing " + publicTripInstances.size() + " TripInstances");
        } else {
            logger.debug("Returning publicTripInstances is empty");
        }
        if (ownTripInstances.size() == 0) {
            logger.debug("Returning ownTripInstances containing " + ownTripInstances.size() + " TripInstances");
        } else {
            logger.debug("Returning ownTripInstances is empty");
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

    @RequestMapping(value = "/trips/jointrip", method = RequestMethod.POST)
    public ModelAndView joinTrip(HttpSession session, @RequestParam("tripId") String tripId) {
        User sessionUser = (User) session.getAttribute("userObject");
        TripInstance tripInstance = tripInstanceService.getTripInstanceById(Long.parseLong(tripId));
        tripInstance.addParticipantToTripInstance(sessionUser);
        tripInstanceService.updateTripInstance(tripInstance);
        return getTripInstances(session);
    }

    @RequestMapping(value = "/trips/viewinstance/{tripInstanceId}", method = RequestMethod.GET)
    public ModelAndView getTripInstance(@PathVariable("tripInstanceId") String tripInstanceId, HttpSession session) {
        TripInstance tripInstance;
        tripInstance = tripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId));

        if (tripInstance != null) {
            logger.debug("Returning TripInstance: " + tripInstance.toString() + " with tripInstance #" + tripInstanceId);

            return ModelAndViewUtil.getModelAndViewForViewInstance(messageService, session, tripInstance);
        } else {
            return new ModelAndView("error/displayerror");
        }
    }

    @RequestMapping(value = "/invitebymail", method = RequestMethod.POST)
    public ModelAndView invitebymail(@RequestParam(value = "instanceid") String instanceid, @RequestParam(value = "receipients") String receivers, @RequestParam(value = "message") String message, HttpSession session) {
        TripInstance tripInstance = tripInstanceService.getTripInstanceById(Long.parseLong(instanceid));

        tripInstanceService.inviteByEmail(receivers, message, Long.parseLong(instanceid));

        return getModelAndViewForViewInstance(session, tripInstance);
    }

    private ModelAndView getModelAndViewForViewInstance(HttpSession session, TripInstance tripInstance) {
        Map<Long, String> messageDates = new HashMap<>();
        List<Message> messages = messageService.getMessagesByTripInstanceId(tripInstance.getId());

        for (Message message : messages) {
            messageDates.put(tripInstance.getId(), DateUtil.formatDate(DateUtil.longToDate(message.getDate())));
        }
        SortedSet<RequirementInstance> sortedRequirements = new TreeSet<>();
        sortedRequirements.addAll(tripInstance.getRequirementInstances());
        tripInstance.setRequirementInstances(sortedRequirements);
        ModelAndView modelAndView = new ModelAndView("/trips/viewinstance");
        modelAndView.addObject("tripInstanceObject", tripInstance);
        modelAndView.addObject("messageDates", messageDates);
        modelAndView.addObject("userObject", session.getAttribute("userObject"));
        modelAndView.addObject("date", DateUtil.formatDate(tripInstance.getStartTime()));
        modelAndView.addObject("startTimeString", DateUtil.formatTime(tripInstance.getStartTime()));
        modelAndView.addObject("endTimeString", DateUtil.formatTime(tripInstance.getEndTime()));
        return modelAndView;
    }
}
