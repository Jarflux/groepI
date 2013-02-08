package be.kdg.groepi;

/**
 * Created with IntelliJ IDEA.
 * User: Vincent
 * Date: 6/02/13
 * Time: 9:05
 * To change this template use File | Settings | File Templates.
 */

import be.kdg.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String home() {
        System.out.println("HomeController: Passing through...");
        UserService.createUser();
        return "home";
    }
}