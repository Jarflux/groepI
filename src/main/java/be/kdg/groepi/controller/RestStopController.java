package be.kdg.groepi.controller;

import be.kdg.groepi.model.Answer;
import be.kdg.groepi.model.Stop;
import be.kdg.groepi.model.Trip;
import be.kdg.groepi.model.User;
import be.kdg.groepi.service.AnswerService;
import be.kdg.groepi.service.StopService;
import be.kdg.groepi.service.TripService;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller("restStopController")
@RequestMapping("stop")
public class RestStopController {

    private static final Logger logger = Logger.getLogger(RestStopController.class);

    @Autowired
    protected TripService tripService;
    @Autowired
    protected StopService stopService;
    @Autowired
    protected AnswerService answerService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createStop(HttpSession session, @ModelAttribute("stopObject") Stop stop,
                                   @RequestParam(value = "tripId") String tripId) {
        logger.debug("RestStopController: createStop");
        Trip trip = tripService.getTripById(Long.parseLong(tripId));
        if (trip != null) {
            stop.setStopnumber(trip.getStops().size());
            stop.setTrip(trip);
            stopService.createStop(stop);
            return new ModelAndView("template/editstop", "tripObject", trip);
        } else {
            logger.debug("RestStopController - createStop - Triptemplate not found");
            ModelAndView modelAndView = new ModelAndView("error/displayerror");
            modelAndView.addObject("errorid", "tripNotFound");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/add/{tripId}", method = RequestMethod.GET)
    public ModelAndView addStop(@PathVariable("tripId") String tripId, HttpSession session) {
        logger.debug("RestStopController: addStop");
        Trip trip = tripService.getTripById(Long.parseLong(tripId));

        if (trip != null) {
            User user = (User) session.getAttribute("userObject");
            if (user.getId().equals(trip.getOrganiser().getId())) {
                return new ModelAndView("template/addstop", "tripObject", trip);
            } else {
                logger.debug("RestStopController - addStop - User not Authorized");
                ModelAndView modelAndView = new ModelAndView("error/displayerror");
                modelAndView.addObject("errorid", "userNotAuthorizedToAddStop");
                return modelAndView;
            }
        } else {
            logger.debug("RestStopController - addStop - Triptemplate not found");
            ModelAndView modelAndView = new ModelAndView("error/displayerror");
            modelAndView.addObject("errorid", "tripNotFound");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/edit/{stopId}", method = RequestMethod.GET)
    public ModelAndView editStopView(@PathVariable("stopId") String stopId, HttpSession session) {
        logger.debug("RestStopController: editStopView");
        Stop stop = stopService.getStopById(Long.parseLong(stopId));
        User user = (User) session.getAttribute("userObject");
        if (stop != null) {
            if (stop.getTrip().getOrganiser().getId().equals(user.getId())) {
                ModelAndView modelAndView = new ModelAndView("template/editstop");
                modelAndView.addObject("stopObject", stop);
                return modelAndView;
            } else {
                logger.debug("RestStopController - editStopView - User is unauthorized to edit stops from this instance");
                ModelAndView modelAndView = new ModelAndView("error/displayerror");
                modelAndView.addObject("errorid", "userNotAuthorizedToEditStop");
                return modelAndView;
            }
        } else {
            logger.debug("RestStopController - editStopView - Stop not found");
            ModelAndView modelAndView = new ModelAndView("error/displayerror");
            modelAndView.addObject("errorid", "stopNotFound");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateStop(@ModelAttribute("stopObject") Stop stop, @RequestParam String tripId) {
        logger.debug("RestStopController: updateStop");
        Trip trip = tripService.getTripById(Long.parseLong(tripId));
        if (trip != null) {
            stop.setTrip(trip);
            stopService.updateStop(stop);
            return new ModelAndView("redirect:/template/view/" + tripId);
        } else {
            logger.debug("RestStopController - updateStop - Triptemplate not found");
            ModelAndView modelAndView = new ModelAndView("error/displayerror");
            modelAndView.addObject("errorid", "tripNotFound");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/setIsCorrect", method = RequestMethod.POST)
    public String setStopIsCorrect(@RequestParam String answerId) {
        logger.debug("RestStopController: setStopIsCorrect");
        Answer answer = answerService.getAnswerById(Long.parseLong(answerId));
        Stop stop = stopService.getStopById(answer.getStop().getId());
        stop.setCorrectAnswer(Long.parseLong(answerId));
        stopService.updateStop(stop);
        //stop = stopService.getStopById(stop.getId());
        return "saved";
    }

    @RequestMapping(value = "/addAR", method = RequestMethod.POST)
    public ModelAndView addToVuforia(@RequestParam(value = "photo") MultipartFile uploadedFile,
                                     @RequestParam(value = "stopid") Long stopId)
            throws IOException, JSONException, URISyntaxException {
        logger.debug("RestStopController: addToVuforia");
        File tempfile = File.createTempFile("tijdelijk", "juha");
        System.out.println("Trying for Vuforia voor stopId: " + stopId);
        FileOutputStream fos = new FileOutputStream(tempfile);
        fos.write(uploadedFile.getBytes());

        stopService.addToVuforia(stopId, tempfile);
        Stop stop = stopService.getStopById(stopId);
        Trip trip = tripService.getTripById(stop.getTrip().getId());

        return new ModelAndView("template/editstop", "tripObject", trip);
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(Exception e) {
        logger.debug("RestStopController - Unexpected exception", e);
        ModelAndView modelAndView = new ModelAndView("error/displayerror");
        modelAndView.addObject("errorid", "defaultError");
        return modelAndView;
    }
}
