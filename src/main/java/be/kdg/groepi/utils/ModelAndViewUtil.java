package be.kdg.groepi.utils;

import be.kdg.groepi.model.Message;
import be.kdg.groepi.model.RequirementInstance;
import be.kdg.groepi.model.TripInstance;
import be.kdg.groepi.model.User;
import be.kdg.groepi.service.MessageService;

import be.kdg.groepi.service.TripInstanceService;
import java.util.*;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: Dave
 * Date: 13/03/13
 * Time: 11:15
 * To change this template use File | Settings | File Templates.
 */
public class ModelAndViewUtil {



    public static ModelAndView getModelAndViewForViewInstance(MessageService messageService, HttpSession session, TripInstance tripInstance) {
        Map<Long, String> messageDates = new HashMap<>();
        SortedSet<Message> sortedMessages = new TreeSet<>();
        sortedMessages.addAll(messageService.getMessagesByTripInstanceId(tripInstance.getId()));
        tripInstance.setMessages(sortedMessages);

        for (Message message : sortedMessages) {
            messageDates.put(message.getId(), DateUtil.formatDate(DateUtil.longToDate(message.getDate())) + " " +
                                                    DateUtil.formatTime(DateUtil.longToDate(message.getDate())));
        }

        Set<User> participants = tripInstance.getParticipants();
        User sessionUser = (User) session.getAttribute("userObject");
        Boolean isUserParticipating = false;
        for (User user : participants){
            if (user.getId().equals(sessionUser.getId())){
                isUserParticipating = true;
            }
        }

        SortedSet<RequirementInstance> sortedRequirements = new TreeSet<>();
        sortedRequirements.addAll(tripInstance.getRequirementInstances());
        tripInstance.setRequirementInstances(sortedRequirements);

        ModelAndView modelAndView = new ModelAndView("/trips/viewinstance");
        modelAndView.addObject("tripInstanceObject", tripInstance);
        modelAndView.addObject("isUserParticipating", isUserParticipating);
        modelAndView.addObject("messageDates", messageDates);
        modelAndView.addObject("userObject", session.getAttribute("userObject"));
        modelAndView.addObject("date", DateUtil.formatDate(tripInstance.getStartTime()));
        modelAndView.addObject("startTimeString", DateUtil.formatTime(tripInstance.getStartTime()));
        modelAndView.addObject("endTimeString", DateUtil.formatTime(tripInstance.getEndTime()));
        return modelAndView;
    }

    public static ModelAndView getModelAndViewForViewInstance(MessageService messageService, HttpSession session, long tripInstanceId) {
        TripInstanceService tripInstanceService = new TripInstanceService();
        TripInstance tripInstance = tripInstanceService.getTripInstanceById(tripInstanceId);
        return getModelAndViewForViewInstance(messageService, session, tripInstance);
    }

}
