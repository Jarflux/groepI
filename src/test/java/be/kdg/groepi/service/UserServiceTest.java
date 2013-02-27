package be.kdg.groepi.service;

import be.kdg.groepi.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;

import static be.kdg.groepi.utils.DateUtil.dateToLong;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tim
 * Date: 8/02/13
 * Time: 10:20
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceTest {
    //    private final Session session = HibernateUtil.getSessionFactory().openSession();
    //private final UserService userService = new UserService();
    private User user;


    @Before
    public void beforeEachTest() {
        user = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4,5,2011,15,32,0));
    }

    @After
    public void afterEachTest() {
        user = null;
        for (User user : UserService.getAllUsers()) {
            UserService.deleteUser(user);
        }
    }

    @Test
    public void testCreateUser() {
        UserService.createUser(user);
        assertEquals("createUser: users are the same",user,UserService.getUserById(user.getId()));
    }


    @Test
    public void testUpdateUser() {
        UserService.createUser(user);
        user.setName("NOT TIMMEH");
        user.setPassword("hemmitton");
        user.setDateOfBirth(dateToLong(3,6,2012,12,45,0));
        user.setEmail("NOTTIM@M.EH");
        user.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        UserService.updateUser(user);
        assertEquals(user, UserService.getUserById(user.getId()));
    }

    @Test
    public void testNullPicture() {
        UserService.createUser(user);
        assertEquals("User profile picture should be null.", user.getProfilePicture(), null);
    }

    @Test
    public void testDeleteUser() {
        UserService.createUser(user);
        assertNotNull("deleteUser: User must exist", UserService.getUserById(user.getId()));
        UserService.deleteUser(user);
        assertNull("deleteUser: User may not exist", UserService.getUserById(user.getId()));
    }

    @Test
    public void testGetAllUsers() {
        for (int i = 1; i <= 10; i++) {
            User newUser = new User("User " + i, "user" + i + "@M.EH", "pwd", user.getDateOfBirth());
            UserService.createUser(newUser);
        }
        assertEquals("getAllUsers: listsize", 10, UserService.getAllUsers().size());
    }

    @Test
    public void testChangePassword() {
        String newPassword = "newPassword";
        UserService.createUser(user);
        user.setPassword(newPassword);
        UserService.updateUser(user);
        assertTrue("testChangePassword: password wasn't reset", user.getPassword().equals(newPassword));
    }

    @Test
    public void testResetPassword() {
        String oldPassword = user.getPassword();
        UserService.createUser(user);
        UserService.resetPassword(user.getEmail());
        assertFalse("testResetPassword: ", user.getPassword().equals(oldPassword));
    }

    @Test
    public void testGetByEmail() {
        UserService.createUser(user);
        UserService.getUserByEmail("TIM@M.EH");
        assertEquals("testGetUserByEmail: ", user, UserService.getUserById(user.getId()));
    }
}
