package be.kdg.groepi.controller;

import be.kdg.groepi.model.Answer;
import be.kdg.groepi.model.Stop;
import be.kdg.groepi.model.Trip;
import be.kdg.groepi.model.User;
import be.kdg.groepi.service.AnswerService;
import be.kdg.groepi.service.StopService;
import be.kdg.groepi.service.TripService;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
public class RestStopController {

    @RequestMapping(value = "/trips/createStop", method = RequestMethod.POST)
    public ModelAndView createStop(HttpSession session, @ModelAttribute("stopObject") Stop stop, @RequestParam(value = "tripId") String tripId) {
        Trip trip = TripService.getTripById(Long.parseLong(tripId));
        stop.setStopnumber(trip.getStops().size());
        stop.setTrip(trip);
        StopService.createStop(stop);
        return new ModelAndView("trips/editstop", "tripObject", trip);
    }

    @RequestMapping(value = "/trips/addstop/{tripId}", method = RequestMethod.GET)
    public ModelAndView addStop(@PathVariable("tripId") String tripId) {
        Trip trip = TripService.getTripById(Long.parseLong(tripId));
        return new ModelAndView("trips/addstop", "tripObject", trip);
    }

    @RequestMapping(value = "/trips/editStop/{stopId}", method = RequestMethod.GET)
    public ModelAndView editStopView(@PathVariable("stopId") String stopId, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("trips/editstop");
        Stop stop = StopService.getStopById(Long.parseLong(stopId));
        User user = (User) session.getAttribute("userObject");
        if (stop.getTrip().getOrganiser().getId() == user.getId()) {
            modelAndView.addObject("stopObject", stop);
            return modelAndView;
        } else {
            //TODO correcte error weergeven
            return new ModelAndView("error/displayerror/invaliduser", "errorid", "");
        }
    }

    @RequestMapping(value = "/trips/updateStop", method = RequestMethod.POST)
    public ModelAndView updateStop(@ModelAttribute("stopObject") Stop stop, @RequestParam String tripId) {
        Trip trip = TripService.getTripById(Long.parseLong(tripId));
        stop.setTrip(trip);
        StopService.updateStop(stop);
        return new ModelAndView("trips/view", "tripObject", trip);
    }

    @RequestMapping(value = "/trips/setStopIsCorrect", method = RequestMethod.POST)
    public String setStopIsCorrect(@RequestParam String answerId) {
        Answer answer = AnswerService.getAnswerById(Long.parseLong(answerId));
        Stop stop = StopService.getStopById(answer.getStop().getId());
        stop.setCorrectAnswer(Long.parseLong(answerId));
        StopService.updateStop(stop);
        return "saved";
    }

    @RequestMapping(value = "/trips/addAR", method = RequestMethod.POST)
    public ModelAndView addToVuforia(@RequestParam(value = "photo") MultipartFile uploadedFile, @RequestParam(value = "stopid") Long stopId) throws IOException, JSONException, URISyntaxException {
        File tempfile = File.createTempFile("tijdelijk", "juha");
        System.out.println("Trying for Vuforia voor stopId: " + stopId);
        FileOutputStream fos = new FileOutputStream(tempfile);
        fos.write(uploadedFile.getBytes());

        StopService.addToVuforia(stopId, tempfile);
        Stop stop = StopService.getStopById(stopId);
        Trip trip = TripService.getTripById(stop.getTrip().getId());

        return new ModelAndView("trips/editstop", "tripObject", trip);
    }
}


