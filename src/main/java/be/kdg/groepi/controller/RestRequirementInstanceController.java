package be.kdg.groepi.controller;


import be.kdg.groepi.model.RequirementInstance;
import be.kdg.groepi.model.TripInstance;
import be.kdg.groepi.service.MessageService;
import be.kdg.groepi.service.RequirementInstanceService;
import be.kdg.groepi.service.TripInstanceService;
import be.kdg.groepi.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller("restRequirementInstanceController")
@RequestMapping("requirementInstance")
public class RestRequirementInstanceController {

    private static final Logger logger = Logger.getLogger(RestRequirementInstanceController.class);

    @Autowired
    protected UserService userService;
    @Autowired
    protected TripInstanceService tripInstanceService;
    @Autowired
    protected RequirementInstanceService requirementInstanceService;
    @Autowired
    protected MessageService messageService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView doAddInstanceRequirement(@RequestParam(value = "tripInstanceId") String tripInstanceId,
                                                 @RequestParam(value = "name") String name,
                                                 @RequestParam(value = "amount") Long amount,
                                                 @RequestParam(value = "description") String description) {
        logger.debug("RestRequirementInstanceController: doAddInstanceRequirement");
        TripInstance tripInstance = tripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId));
        RequirementInstance requirementInstance = new RequirementInstance(name, amount, description, tripInstance);
        requirementInstanceService.createRequirementInstance(requirementInstance);
        return new ModelAndView("redirect:/trip/view/" + tripInstanceId);
    }

    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    public ModelAndView assignRequirement(HttpSession session,
                                          @RequestParam(value = "responsibleuser") String userId,
                                          @RequestParam(value = "requirementinstanceid") String requirementInstanceid) {
        logger.debug("RestRequirementInstanceController: assignRequirement");
        RequirementInstance requirementInstance = requirementInstanceService
                .getRequirementInstanceById(Long.parseLong(requirementInstanceid));
        if (userId.equals("0")) {
            requirementInstance.setUser(null);
        } else {
            requirementInstance.setUser(userService.getUserById(Long.parseLong(userId)));
        }
        requirementInstanceService.updateRequirementInstance(requirementInstance);
        return new ModelAndView("redirect:/trip/view/" + requirementInstance.getTripInstance().getId());
        //return ModelAndViewUtil.getModelAndViewForViewInstance(messageService, session, requirementInstance.getTripInstance());
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(Exception e) {
        logger.debug("RestRequirementInstanceController - Unexpected exception", e);
        ModelAndView modelAndView = new ModelAndView("error/displayerror");
        modelAndView.addObject("errorid", "defaultError");
        return modelAndView;
    }
}
