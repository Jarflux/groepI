package be.kdg.groepi;

import be.kdg.groepi.model.User;
import be.kdg.groepi.service.HibernateUtil;
import be.kdg.groepi.service.UserService;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class UserTest {
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
    }

    @Test
    public void createUser() {
        UserService.createUser(user);
        assertEquals(user, UserService.getUserById(user.getId()));
    }


    @Test
    public void updateUser() {
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

        assertFalse("name", oldUser.getName().equals(user.getName()));
        assertFalse("password", oldUser.getPassword().equals(user.getPassword()));
        assertFalse("dateOfBirth", oldUser.getDateOfBirth().equals(user.getDateOfBirth()));
        assertFalse("email", oldUser.getEmail().equals(user.getEmail()));
    }


}
