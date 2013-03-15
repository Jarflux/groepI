package be.kdg.groepi.controller;

import be.kdg.groepi.model.Message;
import be.kdg.groepi.model.TripInstance;
import be.kdg.groepi.model.User;
import be.kdg.groepi.service.MessageService;
import be.kdg.groepi.service.TripInstanceService;
import be.kdg.groepi.utils.ModelAndViewUtil;

import java.util.Calendar;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller("restMessageController")
public class RestMessageController {

    private static final Logger logger = Logger.getLogger(RestMessageController.class);

    @Autowired
    protected TripInstanceService tripInstanceService;
    @Autowired
    protected MessageService messageService;

    @RequestMapping(value = "/trips/addmessage/{tripInstanceId}", method = RequestMethod.GET)
    public ModelAndView addMessage(@PathVariable("tripInstanceId") String tripInstanceId) {
        return new ModelAndView("trips/addmessage", "tripInstanceId", tripInstanceId);
    }

    @RequestMapping(value = "/trips/doaddmessage", method = RequestMethod.POST)
    public ModelAndView doAddMessage(HttpSession session, @RequestParam(value = "tripInstanceId") String tripInstanceId, @RequestParam(value = "content") String content) {
        TripInstance tripInstance = tripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId));
        if (tripInstance != null) {
            if (tripInstance.getOrganiser().getId().equals(tripInstance.getId())) {
                User sessionUser = (User) session.getAttribute("userObject");
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(System.currentTimeMillis());
                Long date = cal.getTime().getTime();
                Message message = new Message(content, date, tripInstance, sessionUser);
                messageService.createMessage(message);
                return new ModelAndView("redirect:/trips/viewinstance/" + tripInstance.getId());
            } else {
                logger.debug("RestMessageController - doAddMessage - User not authorized to add message");
                ModelAndView modelAndView = new ModelAndView("error/displayerror");
                modelAndView.addObject("errorid", "userNotAuthorizedToAddMessages");
                return modelAndView;
            }
        } else {
            logger.debug("RestMessageController - doAddMessage - TripInstance not found");
            ModelAndView modelAndView = new ModelAndView("error/displayerror");
            modelAndView.addObject("errorid", "tripInstanceNotFound");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/trips/deletemessage", method = RequestMethod.POST)
    public ModelAndView deleteMessageFromTripInstance(HttpSession session,
                                                      @RequestParam(value = "messageId") String messageId,
                                                      @RequestParam(value = "tripInstanceId") String tripInstanceId) {
        Message message = messageService.getMessageById(Long.parseLong(messageId));
        if (message != null) {
            messageService.deleteMessage(message);
            return ModelAndViewUtil.getModelAndViewForViewInstance(messageService, session,
                    tripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId)));
        } else {
            logger.debug("RestMessageController - deleteMessageFromTripInstance - Message not found");
            ModelAndView modelAndView = new ModelAndView("error/displayerror");
            modelAndView.addObject("errorid", "messageNotFound");
            return modelAndView;
        }
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(Exception e) {
        logger.debug("RestMessageController - Unexpected exception", e);
        ModelAndView modelAndView = new ModelAndView("error/displayerror");
        modelAndView.addObject("errorid", "defaultError");
        return modelAndView;
    }
}
