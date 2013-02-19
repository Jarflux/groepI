package be.kdg.groepi.controller;

import be.kdg.groepi.model.User;
import be.kdg.groepi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("trip")
public class RestTripController {
    @RequestMapping(value = "view")
    public ModelAndView viewTrip() {

        return new ModelAndView("trips/view");
    }
}
