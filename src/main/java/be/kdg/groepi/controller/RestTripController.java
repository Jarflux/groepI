package be.kdg.groepi.controller;

import be.kdg.groepi.model.Trip;
import be.kdg.groepi.service.TripService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;

@Controller
@RequestMapping("trips")
public class RestTripController {
    private static final Logger logger = Logger.getLogger(RestTripController.class);

    @RequestMapping(value = "/addtrip")
    public String addtrip(){
        System.out.println("AddTrip: Passing through...");
        return "trips/addtrip";
    }

    @RequestMapping(value = "/createTrip", method = RequestMethod.POST)
    public ModelAndView createTrip(@ModelAttribute("tripObject") Trip trip) {
        TripService.createTrip(trip);
        return new ModelAndView("trips/view", "tripObject", trip);
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
    
}

