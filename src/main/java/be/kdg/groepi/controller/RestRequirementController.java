package be.kdg.groepi.controller;

import be.kdg.groepi.model.Requirement;
import be.kdg.groepi.model.Trip;
import be.kdg.groepi.service.RequirementService;
import be.kdg.groepi.service.TripService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestRequirementController {

    @RequestMapping(value = "/trips/doAddTripRequirement", method = RequestMethod.POST)
    public ModelAndView doAddTripRequirement(@RequestParam(value = "tripId") String tripId,
                                             @RequestParam(value = "name") String name,
                                             @RequestParam(value = "amount") Long amount,
                                             @RequestParam(value = "description") String description) {

        Trip trip = TripService.getTripById(Long.parseLong(tripId));
        Requirement requirement = new Requirement(name, amount, description, trip);
        RequirementService.createRequirement(requirement);
        trip = TripService.getTripById(trip.getId());
        return new ModelAndView("trips/view", "tripObject", trip);
    }

    @RequestMapping(value = "/trips/addrequirement/{tripId}", method = RequestMethod.GET)
    public ModelAndView addRequirement(@PathVariable(value = "tripId") String tripId) {
        return new ModelAndView("trips/addtriprequirement", "tripId", tripId);
    }
}
