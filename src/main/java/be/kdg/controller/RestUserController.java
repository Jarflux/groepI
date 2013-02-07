package be.kdg.controller;

import be.kdg.model.User;
import be.kdg.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author: Ben Oeyen
 * Date: 7/02/13
 * Class: User REST Controller
 * Description:  Controller to handle REST service calls
 */

public class RestUserController {
    private static final Logger logger= Logger.getLogger(RestUserController.class);

    @RequestMapping(value = "/rest/users/{userId}", method = RequestMethod.GET)
    public void getUser(@PathVariable("userId") String userId) {
        User user = null;

        // validate input
        if (userId.isEmpty()) {
            // validation error
        }

        try {
            user = UserService.getUserById(Long.parseLong(userId));
        } catch (Exception e) {
            // run-time exception
        }

        logger.debug("Returning User: " + user.toString());
    }
}
