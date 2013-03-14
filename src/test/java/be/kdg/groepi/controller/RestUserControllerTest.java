package be.kdg.groepi.controller;

import be.kdg.groepi.model.User;
import be.kdg.groepi.service.UserService;
import static be.kdg.groepi.utils.DateUtil.dateToLong;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Transactional
public class RestUserControllerTest {

    
    User user;
    @Autowired
    protected UserService userService;
    @Autowired
    protected RestUserController restUserController;

    @Before
    public void beforeEachTest() {
        user = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        userService.createUser(user);
    }

    @After
    public void afterEachTest() {
        userService.deleteUser(user);
    }

    @Test
    public void testGetUser() {
        ModelAndView modelAndView = restUserController.getUser(String.valueOf(user.getId()));
        assertEquals("profile/user", modelAndView.getViewName());
        assertNotNull("Model should not be null", modelAndView.getModel());
        User returnedUser = (User) modelAndView.getModel().get("userObject");
        assertNotNull("User with ID 1 should be present in the ModelAndView", returnedUser);

        modelAndView = restUserController.getUser(String.valueOf(user.getId() + 1)); // + 1 om een user te krijgen die niet bestaat.
/*        String str1 = String.valueOf(user.getId() + 1);
         String str2 = modelAndView.getModel().get("userId").toString();*/

        assertEquals("This user does not exist, so getUser should return \"error/displayerror\"",
                "error/displayerror", modelAndView.getViewName());
    }

    @Test
    public void testCreateUser() {
        ModelAndView modelAndView = null;
        try {
            modelAndView = restUserController.createUser(user, null);
            assertNotNull("User object should exist", modelAndView.getModel().get("userObject"));
            User createdUser = (User) modelAndView.getModel().get("userObject");
            assertNotNull("User should have a name", createdUser.getName());
            assertNotNull("User not found in DB", userService.getUserById(createdUser.getId()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
