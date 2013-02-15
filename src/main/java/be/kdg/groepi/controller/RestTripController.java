package be.kdg.groepi.controller;

import be.kdg.groepi.model.Trip;
import be.kdg.groepi.service.TripService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author: Ben Oeyen
 * Date: 7/02/13
 * Class: Trip REST Controller
 * Description:  Controller to handle REST service calls
 */
@Controller
@RequestMapping("trips")
public class RestTripController {
    private static final Logger logger = Logger.getLogger(RestTripController.class);

    @RequestMapping(value = "/{tripId}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable("tripId") String tripId) {
        Trip trip;
        // validate input
        //try {
        trip = TripService.getTripById(Long.parseLong(tripId));
        if (trip != null) {
            logger.debug("Returning Trip: " + trip.getTitle() + " with tripId #" + tripId);
            return new ModelAndView("trips/trip", "tripObject", trip);
        } else {
            return new ModelAndView("trips/trip", "tripId", tripId);
        }
        /*} catch (Exception e) {
            String sMessage = "Error invoking getFund. [%1$s]";
            //return createErrorResponse(String.format(sMessage, e.toString()));
        }*/
    }

    @RequestMapping(value = "/createTrip", method = RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute("tripObject") Trip trip) {
        TripService.createTrip(trip);
        return new ModelAndView("trips/createTrip", "tripObject", trip);
    }
}
