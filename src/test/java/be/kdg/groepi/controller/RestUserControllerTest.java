package be.kdg.groepi.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.*;

public class RestUserControllerTest {
    RestUserController restUserController;

    @Before
    public void beforeEachTest(){
        restUserController = new RestUserController();
    }

    @Test
    public void testGetUser(){
        ModelAndView modelAndView = restUserController.getUser("1");
        assertEquals("user.jsp", modelAndView.getViewName());
    }
}
