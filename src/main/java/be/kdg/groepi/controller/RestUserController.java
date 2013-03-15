package be.kdg.groepi.controller;

import be.kdg.groepi.model.TripInstance;
import be.kdg.groepi.model.User;
import be.kdg.groepi.security.MyUserDetailsService;
import be.kdg.groepi.service.TripInstanceService;
import be.kdg.groepi.service.UserService;
import be.kdg.groepi.utils.CompareUtil;
import be.kdg.groepi.utils.DateUtil;
import be.kdg.groepi.utils.FileUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller("restUserController")
@RequestMapping("profile")
public class RestUserController {

    private static final Logger logger = Logger.getLogger(RestUserController.class);
    @Autowired
    protected UserService userService;
    @Autowired
    protected MyUserDetailsService userDetailsService;

    @Autowired
    protected TripInstanceService tripInstanceService;

    @RequestMapping(value = "/view/{userId}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable("userId") String userId) {
        User user = userService.getUserById(Long.parseLong(userId));

        if (user != null) {
            logger.debug("Returning User: " + user.toString() + " with user #" + userId);

            ModelAndView modelAndView = new ModelAndView("profile/user");
            modelAndView.addObject("userObject", user);
            modelAndView.addObject("dob", DateUtil.formatDate(user.getDateOfBirth()));

            return modelAndView;//ModelAndView("profile/user", "userObject", user);
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
        userService.createUser(user);
        return new ModelAndView("home", "userObject", user);
    }

    @RequestMapping(value = "/fblogin", method = RequestMethod.POST)
    public ModelAndView fbLogin(@RequestParam(value = "id") String FBUserID, @RequestParam(value = "name") String naam, @RequestParam(value = "email") String email, @RequestParam(value = "birthday") String verjaardag, HttpSession session) {
        User user = userService.getUserByFbUserId(FBUserID);
        if (user == null) {
            user = new User();
            user.setName(naam);
            user.setEmail(email);
            user.setDateOfBirth(DateUtil.dateStringToLongAlt(verjaardag, null));
            user.setFBUserID(FBUserID);
            user.setPassword(" ");
            userService.createUser(user);
        }


        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, " ", userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute("userObject", user);
        String response = "OK";
        return new ModelAndView("jsonresponse", "antwoord", response);
    }

    private void throwException() {
        throw new NullPointerException();
    }

    @RequestMapping(value = "/myprofile")
    public ModelAndView myProfile(HttpSession session) {

//        throw new NullPointerException();
        throwException();


        User sessionUser = (User) session.getAttribute("userObject");
        Map<Long, String> tripInstanceDates = new HashMap<>();
        Map<Long, String> tripInstanceStartTimes = new HashMap<>();
        Map<Long, String> tripInstanceEndTimes = new HashMap<>();
        SortedSet<TripInstance> userPastTripInstances = new TreeSet<>();
        SortedSet<TripInstance> userFutureTripInstances = new TreeSet<>();

        long today = DateUtil.dateStringToLong(DateUtil.formatDate(Calendar.getInstance().getTime()));

        for (TripInstance tripInstance : tripInstanceService.getAllTripInstances()) {
            if (tripInstance.getParticipants().contains(sessionUser)) {

                if (tripInstance.getStartTime() < today) {
                    userPastTripInstances.add(tripInstance);
                } else {
                    userFutureTripInstances.add(tripInstance);
                }

                tripInstanceDates.put(tripInstance.getId(), DateUtil.formatDate(DateUtil.longToDate(tripInstance.getStartTime())));
                tripInstanceStartTimes.put(tripInstance.getId(), DateUtil.formatTime(DateUtil.longToDate(tripInstance.getStartTime())));
                tripInstanceEndTimes.put(tripInstance.getId(), DateUtil.formatTime(DateUtil.longToDate(tripInstance.getEndTime())));
            }
        }

        ModelAndView modelAndView = new ModelAndView("profile/userprofile");
        modelAndView.addObject("userObject", session.getAttribute("userObject"));
        modelAndView.addObject("dob", DateUtil.formatDate(session));
        modelAndView.addObject("userPastTripInstances", userPastTripInstances);
        modelAndView.addObject("userFutureTripInstances", userFutureTripInstances);
        modelAndView.addObject("tripInstanceDates", tripInstanceDates);
        modelAndView.addObject("tripInstanceStartTimes", tripInstanceStartTimes);
        modelAndView.addObject("tripInstanceEndTimes", tripInstanceEndTimes);
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
        if (!uploadedFile.isEmpty()) {
            sessionUser.setProfilePicture(FileUtil.savePicture(session, uploadedFile, sessionUser.getId()));
        }
        userService.updateUser(sessionUser);
        return myProfile(session);
    }

    @RequestMapping(value = "/reset/forgotPassword")
    public ModelAndView forgotpassword() {
        System.out.println("forgotpassword: Passing through...");
//        return "profile/forgotpassword";
        return new ModelAndView("profile/forgotpassword", "message", "");
    }

    @RequestMapping(value = "/reset/doResetPassword", method = RequestMethod.POST)
    public ModelAndView doResetPassword(@RequestParam(value = "email") String email) {
        if (userService.resetPassword(email)) {
            return new ModelAndView("profile/forgotpassword", "message", "An email has been sent. Please check your inbox for further instructions.");
        } else {
            return new ModelAndView("profile/forgotpassword", "message", "Email address not found!");
        }
    }

    @RequestMapping(value = "/reset/{resetString}", method = RequestMethod.GET)
    public ModelAndView resetPassword(@PathVariable("resetString") String resetString) {
        User user = userService.getUserByResetString(resetString);
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
        User user = userService.getUserByResetString(passwordResetString);

        user.setPassword(CompareUtil.getHashedPassword(password));
        user.setPasswordResetString(null);
        user.setPasswordResetTimestamp(null);
        userService.updateUser(user);

        return "home";
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(Exception e) {
        logger.debug("RestUserController - Unexpected exception", e);
        ModelAndView modelAndView = new ModelAndView("error/displayerror");
        modelAndView.addObject("errorid", "defaultError");
        return modelAndView;
    }
}
