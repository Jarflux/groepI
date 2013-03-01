package be.kdg.groepi.controller;

import be.kdg.groepi.model.Requirement;
import be.kdg.groepi.model.Trip;
import be.kdg.groepi.model.User;
import be.kdg.groepi.service.RequirementService;
import be.kdg.groepi.service.TripService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("trips")
public class RestTripController {

    private static final Logger logger = Logger.getLogger(RestTripController.class);

    @RequestMapping(value = "/addtrip")
    public String addtrip() {
        System.out.println("AddTrip: Passing through...");
        return "trips/addtrip";
    }

    @RequestMapping(value = "/createTrip", method = RequestMethod.POST)
    public ModelAndView createTrip(HttpSession session, @ModelAttribute("tripObject") Trip trip) {

        User user = (User) session.getAttribute("userObject");

        trip.setOrganiser(user);
        TripService.createTrip(trip);
        return new ModelAndView("trips/addtriprequirement", "tripId", trip.getId().toString());
    }

    @RequestMapping(value = "/doAddTripRequirement", method = RequestMethod.POST)
    public ModelAndView doAddTripRequirement(@RequestParam(value = "tripId") String tripId,/*
             @ModelAttribute("requirementObject") Requirement requirement*/
                                             @RequestParam(value = "name") String name,
                                             @RequestParam(value = "amount") Long amount,
                                             @RequestParam(value = "description") String description) {

        Trip trip = TripService.getTripById(Long.parseLong(tripId));

        Requirement requirement = new Requirement(name, amount, description, trip);

        RequirementService.createRequirement(requirement);
        //       trip.addRequirementToTrip(requirement);
        TripService.updateTrip(trip);
        return new ModelAndView("trips/addtriprequirement", "tripId", trip.getId().toString());
    }

    @RequestMapping(value = "/view/{tripId}", method = RequestMethod.GET)
    public ModelAndView getTrip(@PathVariable("tripId") String tripId) {
        Trip trip;
        // validate input
        /*if (tripId.isEmpty() || tripId.length() < 5) {
         String sMessage = "Error invoking getFund - Invalid fund Id parameter";
         // return createErrorResponse(sMessage);
         }*/
        //try {
        trip = TripService.getTripById(Long.parseLong(tripId));
        if (trip != null) {
            logger.debug("Returning Trip: " + trip.toString() + " with trip #" + tripId);
            return new ModelAndView("trips/view", "tripObject", trip);
        } else {
            return new ModelAndView("trips/view", "tripId", tripId);
        }
        /*} catch (Exception e) {
         String sMessage = "Error invoking getFund. [%1$s]";
         //return createErrorResponse(String.format(sMessage, e.toString()));
         }*/
    }

    @RequestMapping(value = "/addstop")
    public String addstop() {
        System.out.println("AddStop: Passing through...");
        return "trips/addstop";
    }

    @RequestMapping(value = "/list")
    public ModelAndView getAllTrips() {
        List<Trip> tripList = TripService.getAllTrips();
        if (tripList != null) {
            logger.debug("Returning TripList containing " + tripList.size() + " TripInstances");
        } else {
            logger.debug("Returning TripList = NULL");
        }
        return new ModelAndView("trips/list", "tripListObject", tripList);
    }
}
