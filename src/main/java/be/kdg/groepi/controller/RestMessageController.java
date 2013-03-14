package be.kdg.groepi.controller;

import be.kdg.groepi.model.Message;
import be.kdg.groepi.model.TripInstance;
import be.kdg.groepi.model.User;
import be.kdg.groepi.service.MessageService;
import be.kdg.groepi.service.TripInstanceService;
import be.kdg.groepi.utils.ModelAndViewUtil;
import java.util.Calendar;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("restMessageController")
public class RestMessageController {

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
        User sessionUser = (User) session.getAttribute("userObject");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        Long date = cal.getTime().getTime();
        Message message = new Message(content, date, tripInstance, sessionUser);
        messageService.createMessage(message);
        return new ModelAndView("redirect:/trips/viewinstance/" + tripInstance.getId());
    }

    @RequestMapping(value = "/trips/deletemessage", method = RequestMethod.POST)
    public ModelAndView deleteMessageFromTripInstance(HttpSession session,
            @RequestParam(value = "messageId") String messageId,
            @RequestParam(value = "tripInstanceId") String tripInstanceId) {
        messageService.deleteMessage(messageService.getMessageById(Long.parseLong(messageId)));
        return ModelAndViewUtil.getModelAndViewForViewInstance(messageService, session,
                tripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId)));
    }
}
