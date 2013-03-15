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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        costService.deleteCost(costService.getCostById(Long.parseLong(costId)));
        return ModelAndViewUtil.getModelAndViewForViewInstance(messageService, session,
                tripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId)));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editCost(HttpSession session,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "amount") String amount,
            @RequestParam(value = "costid") String costId) {
        logger.debug("RestCostController: editCost");
        Cost cost = costService.getCostById(Long.parseLong(costId));
        cost.setDescription(description);
        cost.setAmount(Double.parseDouble(amount));
        costService.updateCost(cost);
        return ModelAndViewUtil.getModelAndViewForViewInstance(messageService, session,
                tripInstanceService.getTripInstanceById(cost.getTripInstance().getId()));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView doAddCost(HttpSession session, @RequestParam(value = "tripInstanceId") String tripInstanceId,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "amount") String amount) {
        logger.debug("RestCostController: doAddCost");
        TripInstance tripInstance = tripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId));
        User sessionUser = (User) session.getAttribute("userObject");
        Cost cost = new Cost(description, Double.parseDouble(amount), tripInstance, sessionUser);
        costService.createCost(cost);
        return new ModelAndView("redirect:/trips/viewinstance/" + tripInstanceId);
    }
}
