package be.kdg.groepi.controller;

import be.kdg.groepi.model.Cost;
import be.kdg.groepi.model.TripInstance;
import be.kdg.groepi.model.User;
import be.kdg.groepi.service.CostService;
import be.kdg.groepi.service.MessageService;
import be.kdg.groepi.service.TripInstanceService;
import be.kdg.groepi.utils.ModelAndViewUtil;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("restCostController")
public class RestCostController {

    @Autowired
    protected TripInstanceService tripInstanceService;
    @Autowired
    protected CostService costService;
    @Autowired
    protected MessageService messageService;

    @RequestMapping(value = "/trips/deletecost", method = RequestMethod.POST)
    public ModelAndView deleteCostFromTripInstance(HttpSession session,
            @RequestParam(value = "costId") String costId,
            @RequestParam(value = "tripInstanceId") String tripInstanceId) {
        costService.deleteCost(costService.getCostById(Long.parseLong(costId)));
        return ModelAndViewUtil.getModelAndViewForViewInstance(messageService, session,
                tripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId)));
    }

    @RequestMapping(value = "/trips/editcost", method = RequestMethod.POST)
    public ModelAndView editCost(HttpSession session,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "amount") String amount,
            @RequestParam(value = "costid") String costId) {
        Cost cost = costService.getCostById(Long.parseLong(costId));
        cost.setDescription(description);
        cost.setAmount(Double.parseDouble(amount));
        costService.updateCost(cost);
        return ModelAndViewUtil.getModelAndViewForViewInstance(messageService, session,
                tripInstanceService.getTripInstanceById(cost.getTripInstance().getId()));
    }

    @RequestMapping(value = "/trips/addcost/{tripInstanceId}", method = RequestMethod.GET)
    public ModelAndView addCost(@PathVariable("tripInstanceId") String tripInstanceId) {
        return new ModelAndView("trips/addcost", "tripInstanceId", tripInstanceId);
    }

    @RequestMapping(value = "/trips/doaddcost", method = RequestMethod.POST)
    public ModelAndView doAddCost(HttpSession session, @RequestParam(value = "tripInstanceId") String tripInstanceId,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "amount") String amount) {

        TripInstance tripInstance = tripInstanceService.getTripInstanceById(Long.parseLong(tripInstanceId));
        User sessionUser = (User) session.getAttribute("userObject");
        Cost cost = new Cost(description, Double.parseDouble(amount), tripInstance, sessionUser);
        costService.createCost(cost);
        return new ModelAndView("redirect:/trips/addcost/" + tripInstanceId, "tripInstanceObject", tripInstance);
    }
}
