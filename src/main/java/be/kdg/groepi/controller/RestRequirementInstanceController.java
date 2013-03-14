package be.kdg.groepi.controller;


import be.kdg.groepi.model.RequirementInstance;
import be.kdg.groepi.model.TripInstance;
import be.kdg.groepi.service.MessageService;
import be.kdg.groepi.service.RequirementInstanceService;
import be.kdg.groepi.service.TripInstanceService;
import be.kdg.groepi.service.UserService;
import be.kdg.groepi.utils.ModelAndViewUtil;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("restRequirementInstanceController")
public class RestRequirementInstanceController {

    @Autowired
    protected UserService userService;
    @Autowired
    protected TripInstanceService tripInstanceService;
    @Autowired
    protected RequirementInstanceService requirementInstanceService;
    @Autowired
    protected MessageService messageService;

    @RequestMapping(value = "/trips/addinstancerequirement/{tripInstanceId}", method = RequestMethod.GET)
    public ModelAndView addInstanceRequirement(@PathVariable(value = "tripInstanceId") String tripInstanceId) {
        return new ModelAndView("trips/addinstancerequirement", "tripInstanceId", tripInstanceId);
    }

    @RequestMapping(value = "/trips/doaddinstancerequirement", method = RequestMethod.POST)
    public ModelAndView doAddInstanceRequirement(@RequestParam(value = "tripInstanceId") String tripInstanceId,
                                                 @RequestParam(value = "name") String name,
                                                 @RequestParam(value = "amount") Long amount,
                                                 @RequestParam(value = "description") String description) {

        TripInstance tripInstance = tripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId));
        RequirementInstance requirementInstance = new RequirementInstance(name, amount, description, tripInstance);
        requirementInstanceService.createRequirementInstance(requirementInstance);
        return new ModelAndView("redirect:/trips/viewinstance/" + tripInstanceId);
    }

    @RequestMapping(value = "/trips/assignrequirementtouser", method = RequestMethod.POST)
    public ModelAndView assignRequirement(HttpSession session,
                                          @RequestParam(value = "responsibleuser") String userId,
                                          @RequestParam(value = "requirementinstanceid") String requirementInstanceid) {
        RequirementInstance requirementInstance = requirementInstanceService
                .getRequirementInstanceById(Long.parseLong(requirementInstanceid));
        if (userId.equals("0")) {
            requirementInstance.setUser(null);
        } else {
            requirementInstance.setUser(userService.getUserById(Long.parseLong(userId)));
        }
        requirementInstanceService.updateRequirementInstance(requirementInstance);

        return ModelAndViewUtil.getModelAndViewForViewInstance(messageService, session, requirementInstance.getTripInstance());
    }
}
