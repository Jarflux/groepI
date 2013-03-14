package be.kdg.groepi.utils;

import be.kdg.groepi.model.Message;
import be.kdg.groepi.model.RequirementInstance;
import be.kdg.groepi.model.TripInstance;
import be.kdg.groepi.service.MessageService;
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
