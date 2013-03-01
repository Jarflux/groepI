package be.kdg.groepi.controller;

import be.kdg.groepi.model.User;
import be.kdg.groepi.service.UserService;
import be.kdg.groepi.utils.CompareUtil;
import be.kdg.groepi.utils.DateUtil;
import be.kdg.groepi.utils.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
        user = UserService.getUserById(Long.parseLong(userId));
        if (user != null) {
            logger.debug("Returning User: " + user.toString() + " with user #" + userId);
            return new ModelAndView("profile/user", "userObject", user);
        } else {
            return new ModelAndView("error/displayerror");
        }
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute("userObject") User user,
                                   @RequestParam(value = "dob") String dateOfBirth)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        user.setDateOfBirth(DateUtil.dateStringToLong(dateOfBirth, null));
        user.setPassword(CompareUtil.getHashedPassword(user.getPassword()));
        UserService.createUser(user);
        return new ModelAndView("home", "userObject", user);
    }

    @RequestMapping(value = "/myprofile")
    public ModelAndView myProfile(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("profile/userprofile");
        modelAndView.addObject("userObject", (User) session.getAttribute("userObject"));
        modelAndView.addObject("dob", DateUtil.formatDate(session));
        return modelAndView;
    }

    @RequestMapping(value = "/myprofile/edit", method = RequestMethod.GET)
    public ModelAndView editUserView(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("profile/editprofile");
        modelAndView.addObject("userObject", (User) session.getAttribute("userObject"));
        modelAndView.addObject("dob", DateUtil.formatDate(session));
        return modelAndView;
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public ModelAndView editUser(HttpSession session, @ModelAttribute("userObject") User user,
                                 @RequestParam(value = "dob") String dateOfBirth,
                                 @RequestParam(value = "photo") MultipartFile uploadedFile) throws IOException {
        User sessionUser = (User) session.getAttribute("userObject");
        sessionUser.setName(user.getName());
        sessionUser.setEmail(user.getEmail());
        sessionUser.setDateOfBirth(DateUtil.dateStringToLong(dateOfBirth, null));
        sessionUser.setProfilePicture(FileUtil.savePicture(uploadedFile, sessionUser.getId()));
        //sessionUser.setProfilePicture(user.getProfilePicture());
        UserService.updateUser(sessionUser);
        ModelAndView modelAndView = new ModelAndView("profile/userprofile");
        modelAndView.addObject("userObject", sessionUser);
        modelAndView.addObject("dob", DateUtil.formatDate(session));
        return modelAndView;
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
                String error = "resetpasswordtimeerror";
                return new ModelAndView("error/displayerror/resetpasswordtimeerror", "errorid", error);
            }
        } else {
            String error = "resetstringnotfound";
            return new ModelAndView("error/displayerror/resetstringnotfound", "errorid", error);
        }
    }

    @RequestMapping(value = "/reset/setNewPassword", method = RequestMethod.POST)
    public String setNewPassword(/* @RequestParam(value = "userObject") */ /* @ModelAttribute("userObject") User user, */
                                 @RequestParam(value = "passwordResetString") String passwordResetString,
                                 @RequestParam(value = "password") String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = UserService.getUserByResetString(passwordResetString);

        user.setPassword(CompareUtil.getHashedPassword(password));
        user.setPasswordResetString(null);
        user.setPasswordResetTimestamp(null);
        UserService.updateUser(user);

        return "home";
    }

}
