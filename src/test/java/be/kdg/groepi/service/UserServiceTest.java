package be.kdg.groepi.service;

import be.kdg.groepi.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;

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
        Calendar cal = Calendar.getInstance();
        cal.set(1992, Calendar.JULY, 15);
        Date dateOfBirth = new Date(cal.getTime().getTime());

        user = new User("TIMMEH", "TIM@M.EH", "hemmit", dateOfBirth);
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
        assertEquals("createUser: userEquals", user, UserService.getUserById(user.getId()));
    }


    @Test
    public void testUpdateUser() {
        UserService.createUser(user);

        Calendar cal = Calendar.getInstance();
        cal.set(2007, Calendar.MAY, 12);
        Date dateOfBirth = new Date(cal.getTime().getTime());

        User oldUser = new User(user.getName(), user.getEmail(), user.getPassword(), user.getDateOfBirth());

        user.setName("NOT TIMMEH");
        user.setPassword("hemmitton");
        user.setDateOfBirth(dateOfBirth);
        user.setEmail("NOTTIM@M.EH");

        UserService.updateUser(user);

        assertFalse("updateUser: name", oldUser.getName().equals(user.getName()));
        assertFalse("updateUser: password", oldUser.getPassword().equals(user.getPassword()));
        assertFalse("updateUser: dateOfBirth", oldUser.getDateOfBirth().equals(user.getDateOfBirth()));
        assertFalse("updateUser: email", oldUser.getEmail().equals(user.getEmail()));
    }

    @Test
    public void testDeleteUser() {
        UserService.createUser(user);

        assertNotNull("deleteUser: User found", UserService.getUserById(user.getId()));

        UserService.deleteUser(user);

        assertNull("deleteUser: User not found", UserService.getUserById(user.getId()));
    }

    @Test
    public void testGetAllUsers() {
        for (int i = 1; i <= 10; i++) {
            User newUser = new User("User " + i, "user" + i + "@M.EH", "pwd", user.getDateOfBirth());
            UserService.createUser(newUser);
        }

        assertEquals("getAllUsers: listsize", 10, UserService.getAllUsers().size());
    }
}
