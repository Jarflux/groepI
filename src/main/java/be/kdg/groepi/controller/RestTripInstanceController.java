package be.kdg.groepi.controller;


import be.kdg.groepi.model.*;
import be.kdg.groepi.service.MessageService;
import be.kdg.groepi.service.RequirementInstanceService;
import be.kdg.groepi.service.TripInstanceService;
import be.kdg.groepi.service.TripService;
import be.kdg.groepi.utils.DateUtil;
import be.kdg.groepi.utils.ModelAndViewUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller("restTripInstanceController")
@RequestMapping("trip")
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

    @RequestMapping(value = "/add/{tripId}")
    public ModelAndView addinstance(@PathVariable("tripId") String tripId, HttpSession session) {
        logger.debug("RestTripInstanceController: addinstance");
        System.out.println("AddInstance: Passing through...");
        Trip trip = tripService.getTripById(Long.parseLong(tripId));
        if (trip != null) {
            ModelAndView modelAndView = new ModelAndView("trip/add");
            modelAndView.addObject("tripObject", trip);
            return modelAndView;
        } else {
            logger.debug("RestTripInstanceController - addinstance - Trip not found");
            ModelAndView modelAndView = new ModelAndView("error/displayerror");
            modelAndView.addObject("errorid", "tripNotFound");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createinstance(HttpSession session, @ModelAttribute("tripInstanceObject") TripInstance tempTripInstance,
                                       @RequestParam(value = "tripId") Long tripId,
                                       @RequestParam(value = "date") String date,
                                       @RequestParam(value = "startTimeString") String startTimeString,
                                       @RequestParam(value = "endTimeString") String endTimeString,
                                       @RequestParam(value = "repeatable") String repeatable,
                                       @RequestParam(value = "endDate") String endDate) {
        logger.debug("RestTripInstanceController: createinstance");
        User user = (User) session.getAttribute("userObject");
        Trip trip = tripService.getTripById(tripId);
        if (trip != null) {
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
                return getTripInstanceList(session);
                //return getTrip(trip.getId().toString(), session);
            } else {
                TripInstance tripInstance = createNewTripInstance(tempTripInstance, startTime, endTime, user);
//                return getTripInstance(tripInstance.getId().toString(), session);
                return getTripInstanceList(session);
            }
        } else {
            logger.debug("RestTripInstanceController - createinstance - Trip not found");
            ModelAndView modelAndView = new ModelAndView("error/displayerror");
            modelAndView.addObject("errorid", "tripNotFound");
            return modelAndView;
        }
    }

    private TripInstance createNewTripInstance(TripInstance tempTripInstance, long startTime, long endTime, User user) {
        logger.debug("RestTripInstanceController: createNewTripInstance");
        TripInstance tripInstance = new TripInstance(tempTripInstance, startTime, endTime);
        tripInstance.addParticipantToTripInstance(user);
        tripInstanceService.createTripInstance(tripInstance);

        for (Requirement req : tempTripInstance.getTrip().getRequirements()) {
            RequirementInstance reqIns = new RequirementInstance(req, tripInstance);
            requirementInstanceService.createRequirementInstance(reqIns);
        }

        return tripInstance;
    }

    @RequestMapping(value = "/edit/{tripInstanceId}", method = RequestMethod.GET)
    public ModelAndView editInstanceView(HttpSession session,
                                         @PathVariable("tripInstanceId") String tripInstanceId) {
        logger.debug("RestTripInstanceController: editInstanceView");
        TripInstance tripInstance = tripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId));
        if (tripInstance != null) {
            User sessionUser = (User) session.getAttribute("userObject");
            if (tripInstance.getOrganiser().getId().equals(sessionUser.getId())) {
                ModelAndView modelAndView = new ModelAndView("trip/edit");
                modelAndView.addObject("tripInstanceObject", tripInstance);
                modelAndView.addObject("startTimeString", DateUtil.formatTime(tripInstance.getStartTime()));
                modelAndView.addObject("endTimeString", DateUtil.formatTime(tripInstance.getEndTime()));
                return modelAndView;
            } else {
                logger.debug("RestTripInstanceController - editInstanceView - User not authorized to edit trip");
                ModelAndView modelAndView = new ModelAndView("error/displayerror");
                modelAndView.addObject("errorid", "userNotAuthorizedToEditTrip");
                return modelAndView;
            }
        } else {
            logger.debug("RestTripInstanceController - editInstanceView - TripInstance not found");
            ModelAndView modelAndView = new ModelAndView("error/displayerror");
            modelAndView.addObject("errorid", "tripInstanceNotFound");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateInstance(@RequestParam(value = "tripInstanceId") Long tripInstanceId,
                                       @RequestParam(value = "title") String title,
                                       @RequestParam(value = "description") String description,
                                       @RequestParam(value = "available") Boolean available,
                                       @RequestParam(value = "date") String date,
                                       @RequestParam(value = "startTimeString") String startTimeString,
                                       @RequestParam(value = "endTimeString") String endTimeString) {
        logger.debug("RestTripInstanceController: updateInstance");
        TripInstance tripInstance = tripInstanceService.getTripInstanceById(tripInstanceId);
        if (tripInstance != null) {
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
            return new ModelAndView("redirect:/trip/view/" + tripInstanceId);
        } else {
            logger.debug("RestTripInstanceController - updateInstance - TripInstance not found");
            ModelAndView modelAndView = new ModelAndView("error/displayerror");
            modelAndView.addObject("errorid", "tripInstanceNotFound");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/list")
    public ModelAndView getTripInstanceList(HttpSession session) {
        logger.debug("RestTripInstanceController: getTripInstanceList");
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
        Map<Long, Boolean> isUserParticipating = new HashMap<>();
        for (TripInstance tripInstance : publicTripInstances) {
            publicTripInstanceDates.put(tripInstance.getId(),
                    DateUtil.formatDate(DateUtil.longToDate(tripInstance.getStartTime())));
            publicTripInstanceStartTimes.put(tripInstance.getId(),
                    DateUtil.formatTime(DateUtil.longToDate(tripInstance.getStartTime())));
            publicTripInstanceEndTimes.put(tripInstance.getId(),
                    DateUtil.formatTime(DateUtil.longToDate(tripInstance.getEndTime())));
            if (tripInstance.getParticipants().contains(user)) {
                isUserParticipating.put(tripInstance.getId(), true);
            } else {
                isUserParticipating.put(tripInstance.getId(), false);
            }
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

        ModelAndView modelAndView = new ModelAndView("trip/list");
        modelAndView.addObject("publicTripInstances", publicTripInstances);
        modelAndView.addObject("publicTripInstanceDates", publicTripInstanceDates);
        modelAndView.addObject("publicTripInstanceStartTimes", publicTripInstanceStartTimes);
        modelAndView.addObject("publicTripInstanceEndTimes", publicTripInstanceEndTimes);

        modelAndView.addObject("ownTripInstances", ownTripInstances);
        modelAndView.addObject("ownTripInstanceDates", ownTripInstanceDates);
        modelAndView.addObject("ownTripInstanceStartTimes", ownTripInstanceStartTimes);
        modelAndView.addObject("ownTripInstanceEndTimes", ownTripInstanceEndTimes);

        modelAndView.addObject("isUserParticipating", isUserParticipating);

        return modelAndView;
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public ModelAndView joinTrip(HttpSession session, @RequestParam("tripId") String tripId) {
        logger.debug("RestTripInstanceController: joinTrip");
        User sessionUser = (User) session.getAttribute("userObject");
        TripInstance tripInstance = tripInstanceService.getTripInstanceById(Long.parseLong(tripId));
        if (tripInstance != null) {
            tripInstance.addParticipantToTripInstance(sessionUser);
            tripInstanceService.updateTripInstance(tripInstance);
            return new ModelAndView("redirect:/trip/view/" + tripId);
        } else {
            logger.debug("RestTripInstanceController - joinTrip - TripInstance not found");
            ModelAndView modelAndView = new ModelAndView("error/displayerror");
            modelAndView.addObject("errorid", "tripInstanceNotFound");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/view/{tripInstanceId}", method = RequestMethod.GET)
    public ModelAndView getTripInstance(@PathVariable("tripInstanceId") String tripInstanceId, HttpSession session) {
        logger.debug("RestTripInstanceController: getTripInstance");
        TripInstance tripInstance;
        tripInstance = tripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId));
        if (tripInstance != null) {
            logger.debug("Returning TripInstance: " + tripInstance.toString() + " with tripInstance #" + tripInstanceId);
            return ModelAndViewUtil.getModelAndViewForViewInstance(messageService, session, tripInstance);
        } else {
            logger.debug("RestTripInstanceController - getTripInstance - TripInstance not found");
            ModelAndView modelAndView = new ModelAndView("error/displayerror");
            modelAndView.addObject("errorid", "tripInstanceNotFound");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/invitebymail", method = RequestMethod.POST)
    public ModelAndView invitebymail(@RequestParam(value = "instanceid") String instanceid,
                                     @RequestParam(value = "receipients") String receivers,
                                     @RequestParam(value = "message") String message,
                                     HttpSession session) {
        logger.debug("RestTripInstanceController: invitebymail");
        TripInstance tripInstance = tripInstanceService.getTripInstanceById(Long.parseLong(instanceid));

        tripInstanceService.inviteByEmail(receivers, message, Long.parseLong(instanceid));
        return new ModelAndView("redirect:/trip/view/" + tripInstance.getId());
        //return ModelAndViewUtil.getModelAndViewForViewInstance(messageService, session, tripInstance);
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(Exception e) {
        logger.debug("RestTripInstanceController - Unexpected exception", e);
        ModelAndView modelAndView = new ModelAndView("error/displayerror");
        modelAndView.addObject("errorid", "defaultError");
        return modelAndView;
    }
}
