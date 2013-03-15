package be.kdg.groepi.controller;

import be.kdg.groepi.model.Message;
import be.kdg.groepi.model.TripInstance;
import be.kdg.groepi.model.User;
import be.kdg.groepi.service.MessageService;
import be.kdg.groepi.service.TripInstanceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Calendar;

@Controller("restMessageController")
@RequestMapping("message")
public class RestMessageController {

    private static final Logger logger = Logger.getLogger(RestMessageController.class);

    @Autowired
    protected TripInstanceService tripInstanceService;
    @Autowired
    protected MessageService messageService;

    /*@RequestMapping(value = "/add/{tripInstanceId}", method = RequestMethod.GET)
    public ModelAndView addMessage(@PathVariable("tripInstanceId") String tripInstanceId) {
        return new ModelAndView("trips/addmessage", "tripInstanceId", tripInstanceId);
    }*/

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView doAddMessage(HttpSession session, @RequestParam(value = "tripInstanceId") String tripInstanceId, @RequestParam(value = "content") String content) {
        logger.debug("RestMessageController: doAddMessage");
        TripInstance tripInstance = tripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId));
        if (tripInstance != null) {
            User sessionUser = (User) session.getAttribute("userObject");
            if (tripInstance.getOrganiser().getId().equals(sessionUser.getId())) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(System.currentTimeMillis());
                Long date = cal.getTime().getTime();
                Message message = new Message(content, date, tripInstance, sessionUser);
                messageService.createMessage(message);
                return new ModelAndView("redirect:/trip/view/" + tripInstance.getId());
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

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteMessageFromTripInstance(HttpSession session,
                                                      @RequestParam(value = "messageId") String messageId,
                                                      @RequestParam(value = "tripInstanceId") String tripInstanceId) {
        logger.debug("RestMessageController: deleteMessageFromTripInstance");
        Message message = messageService.getMessageById(Long.parseLong(messageId));
        if (message != null) {
            messageService.deleteMessage(message);
            return new ModelAndView("redirect:/trip/view/" + tripInstanceId);
            /*return ModelAndViewUtil.getModelAndViewForViewInstance(messageService, session,
                    tripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId)));*/
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
