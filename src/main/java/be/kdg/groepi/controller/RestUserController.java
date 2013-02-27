package be.kdg.groepi.controller;

import be.kdg.groepi.model.User;
import be.kdg.groepi.service.UserService;
import be.kdg.groepi.utils.CompareUtil;
import be.kdg.groepi.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

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
        user = UserService.getUserById(Long.parseLong(userId));
        if (user != null) {
            logger.debug("Returning User: " + user.toString() + " with user #" + userId);
            return new ModelAndView("profile/user", "userObject", user);
        } else {
            return new ModelAndView("error/displayerror");
        }
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute("userObject") User user, @RequestParam(value = "BirthDate") String DateOfBirth) {                 
        user.setDateOfBirth(DateUtil.dateStringToLong(DateOfBirth,null));
        user.setPassword(CompareUtil.getHashedPassword(user.getPassword())); //TODO Uncomment to encrypt passwords 
        UserService.createUser(user);
        return new ModelAndView("profile/user", "userObject", user);
    }

    @RequestMapping(value = "/myprofile")
    public ModelAndView myProfile(HttpSession session) {
        return new ModelAndView("profile/userprofile", "userObject", (User) session.getAttribute("userObject"));
    }

    @RequestMapping(value = "/myprofile/edit", method = RequestMethod.GET)
    public ModelAndView editUserView(HttpSession session){
        return new ModelAndView("profile/editprofile", "userObject", (User) session.getAttribute("userObject"));
    }
    
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public ModelAndView editUser(HttpSession session, @ModelAttribute("userObject") User user) {
        User sessionUser = (User) session.getAttribute("userObject");
        sessionUser.setName(user.getName());
        sessionUser.setEmail(user.getEmail());
        sessionUser.setDateOfBirth(user.getDateOfBirth());
        UserService.updateUser(sessionUser); 
        return new ModelAndView("profile/userprofile", "userObject", (User) session.getAttribute("userObject"));
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
