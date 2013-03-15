package be.kdg.groepi.controller;

import be.kdg.groepi.model.Requirement;
import be.kdg.groepi.model.Trip;
import be.kdg.groepi.service.RequirementService;
import be.kdg.groepi.service.TripService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller("restRequirementController")
public class RestRequirementController {

    private static final Logger logger = Logger.getLogger(RestRequirementController.class);

    @Autowired
    protected TripService tripService;
    @Autowired
    protected RequirementService requirementService;

    @RequestMapping(value = "/trips/doAddTripRequirement", method = RequestMethod.POST)
    public ModelAndView doAddTripRequirement(@RequestParam(value = "tripId") String tripId,
                                             @RequestParam(value = "name") String name,
                                             @RequestParam(value = "amount") Long amount,
                                             @RequestParam(value = "description") String description) {

        Trip trip = tripService.getTripById(Long.parseLong(tripId));
        if (trip != null) {
            if (trip.getOrganiser().getId().equals(trip.getId())) {
                Requirement requirement = new Requirement(name, amount, description, trip);
                requirementService.createRequirement(requirement);
                trip = tripService.getTripById(trip.getId());
                return new ModelAndView("trips/view", "tripObject", trip);
            } else {
                logger.debug("RestRequirementController - doAddTripRequirement - User not authorized to add requirement");
                ModelAndView modelAndView = new ModelAndView("error/displayerror");
                modelAndView.addObject("errorid", "userNotAuthorizedToAddRequirement");
                return modelAndView;
            }
        } else {
            logger.debug("RestRequirementController - doAddTripRequirement - Trip not found");
            ModelAndView modelAndView = new ModelAndView("error/displayerror");
            modelAndView.addObject("errorid", "tripNotFound");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/trips/addrequirement/{tripId}", method = RequestMethod.GET)
    public ModelAndView addRequirement(@PathVariable(value = "tripId") String tripId) {
        return new ModelAndView("trips/addtriprequirement", "tripId", tripId);
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(Exception e) {
        logger.debug("RestRequirementController - Unexpected exception", e);
        ModelAndView modelAndView = new ModelAndView("error/displayerror");
        modelAndView.addObject("errorid", "defaultError");
        return modelAndView;
    }
}
