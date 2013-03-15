package be.kdg.groepi;

/**
 * Created with IntelliJ IDEA.
 * User: Vincent
 * Date: 6/02/13
 * Time: 9:05
 * To change this template use File | Settings | File Templates.
 */
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class);

    @RequestMapping(value = "/")
    public String home() {
        logger.debug("HomeController: home");
        return "home";
    }

    @RequestMapping(value = "/fbloginpage")
    public String fbloginpage() {
        logger.debug("HomeController: fbloginpage");
        return "fbloginpage";
    }

    @RequestMapping(value = "/login")
    public String login() {
        logger.debug("HomeController: login");
        return "login/showlogin";
    }

    @RequestMapping(value = "/login/incorrect")
    public ModelAndView loginincorrect(String errormessage) {

        logger.debug("HomeController: loginincorrect");
        errormessage = "Deze gegevens konden niet worden gevalideerd. Probeer opnieuw";
        return new ModelAndView("login/showlogin", "errormsg", errormessage);
    }

    @RequestMapping(value = "/topmenu")
    public String topmenu() {
        return "menu/topmenu";
    }

    @RequestMapping(value = "/error/{error}")
    public ModelAndView errorpage(@PathVariable("error") String errorid) {
        if (errorid.isEmpty()){
            errorid = "defaulterror";
        }
        logger.debug("HomeController: errorpage");
        return new ModelAndView("error/displayerror", "errorid", errorid);
    }

    @RequestMapping(value = "/profile/register")
    public String register() {
        logger.debug("HomeController: register");
        return "profile/register";
    }
}
