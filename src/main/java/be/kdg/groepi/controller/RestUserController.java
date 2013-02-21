package be.kdg.groepi.controller;

import be.kdg.groepi.model.User;
import be.kdg.groepi.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

/**
 * Author: Ben Oeyen
 * Date: 7/02/13
 * Class: User REST Controller
 * Description:  Controller to handle REST service calls
 */

@Controller
@RequestMapping("profile")
public class RestUserController {
    private static final Logger logger = Logger.getLogger(RestUserController.class);

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable("userId") String userId) {
        User user;
        // validate input
        /*if (userId.isEmpty() || userId.length() < 5) {
            String sMessage = "Error invoking getFund - Invalid fund Id parameter";
            // return createErrorResponse(sMessage);
        }*/
        //try {
        user = UserService.getUserById(Long.parseLong(userId));
        if (user != null) {
            logger.debug("Returning User: " + user.toString() + " with user #" + userId);
            return new ModelAndView("profile/user", "userObject", user);
        } else {
            return new ModelAndView("profile/user", "userId", userId);
        }
        /*} catch (Exception e) {
            String sMessage = "Error invoking getFund. [%1$s]";
            //return createErrorResponse(String.format(sMessage, e.toString()));
        }*/
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute("userObject") User user) {
        //TODO: encrypt password
        UserService.createUser(user);
        return new ModelAndView("profile/user", "userObject", user);
    }

    @RequestMapping(value = "/resetPassword/{resetString}", method = RequestMethod.GET)
    public ModelAndView resetPassword(@PathVariable("resetString") String resetString){
        User user = UserService.getUserByResetString(resetString);
        if(user != null){
            if(user.getPasswordResetTimestamp().getTime() > Calendar.getInstance().getTime().getTime()){
                return new ModelAndView("profile/resetPassword", "userObject", user);
            }else{
                // TODO: Gemakkelijk custom error creÃ«ren
                return new ModelAndView("error/displayerror");
            }
        }else{
            return new ModelAndView("error/displayerror");
        }

    }



}
