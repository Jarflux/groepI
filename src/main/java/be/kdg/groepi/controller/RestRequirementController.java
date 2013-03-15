package be.kdg.groepi.controller;

import be.kdg.groepi.model.Requirement;
import be.kdg.groepi.model.Trip;
import be.kdg.groepi.model.User;
import be.kdg.groepi.service.RequirementService;
import be.kdg.groepi.service.TripService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller("restRequirementController")
@RequestMapping("requirement")
public class RestRequirementController {

    private static final Logger logger = Logger.getLogger(RestRequirementController.class);

    @Autowired
    protected TripService tripService;
    @Autowired
    protected RequirementService requirementService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView doAddTripRequirement(HttpSession session,
                                             @RequestParam(value = "tripId") String tripId,
                                             @RequestParam(value = "name") String name,
                                             @RequestParam(value = "amount") Long amount,
                                             @RequestParam(value = "description") String description) {
        logger.debug("RestRequirementService: doAddTripRequirement");
        Trip trip = tripService.getTripById(Long.parseLong(tripId));
        if (trip != null) {
            User sessionUser = (User) session.getAttribute("userObject");
            if (trip.getOrganiser().getId().equals(sessionUser.getId())) {
                Requirement requirement = new Requirement(name, amount, description, trip);
                requirementService.createRequirement(requirement);
                return new ModelAndView("redirect:/template/view/" + tripId);
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

    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(Exception e) {
        logger.debug("RestRequirementController - Unexpected exception", e);
        ModelAndView modelAndView = new ModelAndView("error/displayerror");
        modelAndView.addObject("errorid", "defaultError");
        return modelAndView;
    }
}
