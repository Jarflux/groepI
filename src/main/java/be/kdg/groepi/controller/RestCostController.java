package be.kdg.groepi.controller;

import be.kdg.groepi.model.Cost;
import be.kdg.groepi.model.TripInstance;
import be.kdg.groepi.model.User;
import be.kdg.groepi.service.CostService;
import be.kdg.groepi.service.MessageService;
import be.kdg.groepi.service.TripInstanceService;
import be.kdg.groepi.utils.ModelAndViewUtil;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller("restCostController")
@RequestMapping("cost")
public class RestCostController {

    private static final Logger logger = Logger.getLogger(RestCostController.class);

    @Autowired
    protected TripInstanceService tripInstanceService;
    @Autowired
    protected CostService costService;
    @Autowired
    protected MessageService messageService;

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteCostFromTripInstance(HttpSession session,
            @RequestParam(value = "costId") String costId,
            @RequestParam(value = "tripInstanceId") String tripInstanceId) {
        logger.debug("RestCostController: deleteCostFromTripInstance");
        Cost cost = costService.getCostById(Long.parseLong(costId));
        if (cost != null) {
            costService.deleteCost(cost);
            return ModelAndViewUtil.getModelAndViewForViewInstance(messageService, session,
                    tripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId)));
        } else {
            logger.debug("RestCostController - deleteCostFromTripInstance - Cost not found");
            ModelAndView modelAndView = new ModelAndView("error/displayerror");
            modelAndView.addObject("errorid", "costNotFound");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editCost(HttpSession session,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "amount") String amount,
            @RequestParam(value = "costid") String costId) {
        logger.debug("RestCostController: editCost");
        Cost cost = costService.getCostById(Long.parseLong(costId));
        if (cost != null) {
            cost.setDescription(description);
            cost.setAmount(Double.parseDouble(amount));
            costService.updateCost(cost);
            return ModelAndViewUtil.getModelAndViewForViewInstance(messageService, session, cost.getTripInstance().getId());
        } else {
            logger.debug("RestCostController - editCost - Cost not found");
            ModelAndView modelAndView = new ModelAndView("error/displayerror");
            modelAndView.addObject("errorid", "costNotFound");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView doAddCost(HttpSession session, @RequestParam(value = "tripInstanceId") String tripInstanceId,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "amount") String amount) {
        logger.debug("RestCostController: doAddCost");
        TripInstance tripInstance = tripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId));
        if (tripInstance != null) {
        User sessionUser = (User) session.getAttribute("userObject");
        Cost cost = new Cost(description, Double.parseDouble(amount), tripInstance, sessionUser);
        costService.createCost(cost);
        return new ModelAndView("redirect:/trips/viewinstance/" + tripInstanceId);
    } else {
        logger.debug("RestCostController - doAddCost - TripInstance not found");
        ModelAndView modelAndView = new ModelAndView("error/displayerror");
        modelAndView.addObject("errorid", "tripInstanceNotFound");
        return modelAndView;
    }
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(Exception e) {
        logger.debug("RestCostController - Unexpected exception", e);
        ModelAndView modelAndView = new ModelAndView("error/displayerror");
        modelAndView.addObject("errorid", "defaultError");
        return modelAndView;
    }
}
