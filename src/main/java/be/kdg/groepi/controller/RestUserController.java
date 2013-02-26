package be.kdg.groepi.controller;

import be.kdg.groepi.model.User;
import be.kdg.groepi.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

/**
 * Author: Ben Oeyen
 * Date: 7/02/13
 * Class: User REST Controller
 * Description: Controller to handle REST service calls
 */
@Controller
@RequestMapping("profile")
public class RestUserController {

    private static final Logger logger = Logger.getLogger(RestUserController.class);

    @RequestMapping(value = "/view/{userId}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable("userId") String userId) {
        User user;
        // validate input
        /* if (userId.isEmpty() || userId.length() < 5) {
         * String sMessage = "Error invoking getFund - Invalid fund Id parameter";
         * // return createErrorResponse(sMessage);
         * } */
        //try {
        user = UserService.getUserById(Long.parseLong(userId));
        if (user != null) {
            logger.debug("Returning User: " + user.toString() + " with user #" + userId);
            return new ModelAndView("profile/user", "userObject", user);
        } else {
            return new ModelAndView("profile/user", "userId", userId);
        }
        /* } catch (Exception e) {
         * String sMessage = "Error invoking getFund. [%1$s]";
         * //return createErrorResponse(String.format(sMessage, e.toString()));
         * } */
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute("userObject") User user) {
        //TODO: encrypt password
        UserService.createUser(user);
        return new ModelAndView("profile/user", "userObject", user);
    }

    @RequestMapping(value = "/myprofile")
    public ModelAndView myProfile(@ModelAttribute("userObject") User user) {
        return new ModelAndView("profile/userprofile", "userObject", user);
    }

    @RequestMapping(value = "/myprofile/edit", method = RequestMethod.GET)
    public ModelAndView editUser(@ModelAttribute("userObject") User user) {
        user = UserService.getUserById(Long.parseLong("1")); //TODO: user hardcoded remove after testing
        return new ModelAndView("profile/editprofile", "userObject", user);
    }

    @RequestMapping(value = "/reset/forgotPassword")
    public ModelAndView forgotpassword() {
        System.out.println("forgotpassword: Passing through...");
//        return "profile/forgotpassword";
        return new ModelAndView("profile/forgotpassword", "message", "");
    }

    @RequestMapping(value = "/reset/doResetPassword", method = RequestMethod.POST)
    public ModelAndView doResetPassword(@RequestParam(value = "email") String email) {
        if (UserService.resetPassword(email)) {
            return new ModelAndView("profile/forgotpassword", "message", "An email has been sent. Please check your inbox for further instructions.");
        } else {
            return new ModelAndView("profile/forgotpassword", "message", "Email address not found!");
        }
    }

    @RequestMapping(value = "/reset/{resetString}", method = RequestMethod.GET)
    public ModelAndView resetPassword(@PathVariable("resetString") String resetString) {
        User user = UserService.getUserByResetString(resetString);
        if (user != null) {
            if (user.getPasswordResetTimestamp().getTime() > Calendar.getInstance().getTime().getTime()) {
                return new ModelAndView("profile/resetpassword", "passwordResetString", user.getPasswordResetString());
            } else {
                return new ModelAndView("error/displayerror/resetpasswordtimeerror"); //TODO: pagenotfound
            }
        } else {
            return new ModelAndView("error/displayerror/resetstringnotfound"); //TODO: pagenotfound
        }
    }

    @RequestMapping(value = "/reset/setNewPassword", method = RequestMethod.POST)
    public String setNewPassword(/* @RequestParam(value = "userObject") */ /* @ModelAttribute("userObject") User user, */
            @RequestParam(value = "passwordResetString") String passwordResetString,
            @RequestParam(value = "password") String password) {
        User user = UserService.getUserByResetString(passwordResetString);

        user.setPassword(password);
        user.setPasswordResetString(null);
        user.setPasswordResetTimestamp(null);
        UserService.updateUser(user);

        //TODO: navigeer succesvol naar login (ipv naar home ZONDER css)

        return "/home";
    }
}
