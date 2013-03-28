package be.kdg.groepi.controller;

import be.kdg.groepi.model.Message;
import be.kdg.groepi.model.TripInstance;
import be.kdg.groepi.model.User;
import be.kdg.groepi.service.MessageService;
import be.kdg.groepi.service.TripInstanceService;
import be.kdg.groepi.service.UserService;
import be.kdg.groepi.utils.ExclusionStrategyUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 8-3-13
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
@Controller("restAndroidController")
@RequestMapping("android")
public class AndroidRestController {

    private static final Logger logger = Logger.getLogger(AndroidRestController.class);
    private Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategyUtil()).serializeNulls().create();

    @Autowired
    protected TripInstanceService tripInstanceService;
    @Autowired
    protected MessageService messageService;
    @Autowired
    protected UserService userService;

    @RequestMapping(value = "/showUserTripParticipations/{userId}", method = RequestMethod.GET)
    public void getUserTripParticipations(@PathVariable(value = "userId") String userId, HttpServletResponse response) {
        try {
            List<Object[]> trips = tripInstanceService.getTripInstancesByUserId(Long.parseLong(userId));
            List<TripInstance> trips2= new ArrayList<TripInstance>();
            for (int i = 0; i < trips.size(); i++) {
                        TripInstance temp = (TripInstance) trips.get(i)[0];
                                        trips2.add(temp);
            }
            String json = gson.toJson(trips2);
            response.getWriter().print(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getParticipants/{tripId}", method = RequestMethod.GET)
    public void getParticipants(@PathVariable(value = "tripId") String tripId, HttpServletResponse response) {
        try {
            List<User> participants = tripInstanceService.getTripParticipants(Long.getLong(tripId));
            String json = gson.toJson(participants);
            response.getWriter().print(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public void receiveMessage(HttpServletRequest request) {
        try{
        JSONObject jMessage = new JSONObject(request.getParameter("message"));
        User user = gson.fromJson(jMessage.getString("fUser"),User.class);
        Message message = gson.fromJson(jMessage.toString(),Message.class);
        message.setUser(user);
        messageService.createMessage(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/getMessages", method = RequestMethod.GET)
    public void getMessages(HttpServletResponse response) {
        List<Message> messages = messageService.getLastTenMessages();
        String jMessages = gson.toJson(messages);
        try{
        response.getWriter().print(jMessages);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(Exception e) {
        logger.debug("AndroidRestController - Unexpected exception", e);
        ModelAndView modelAndView = new ModelAndView("error/displayerror");
        modelAndView.addObject("errorid", "defaultError");
        return modelAndView;
    }
}
