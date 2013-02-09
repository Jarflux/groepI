package be.kdg.groepi;

/**
 * Created with IntelliJ IDEA.
 * User: Vincent
 * Date: 6/02/13
 * Time: 9:05
 * To change this template use File | Settings | File Templates.
 */

import be.kdg.groepi.controller.RestUserController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String home() {
        System.out.println("HomeController: Passing through...");
        //UserService.createUser(); //TODO: HEY DAVE
        return "home";
    }

    @RequestMapping(value = "/login")
    public String login() {
        System.out.println("Login: Passing through...");
        return "login/showlogin";
    }


    /*@RequestMapping(value = "/profile")
    public String profile() {
        System.out.println("Profile: passing through...");
        return "profile/user";
    }

    @RequestMapping(value = "/profile/{userid}")
    public ModelAndView profile(){
        RestUserController restUserController = new RestUserController();
        return restUserController.getUser("1");
    } */
}
