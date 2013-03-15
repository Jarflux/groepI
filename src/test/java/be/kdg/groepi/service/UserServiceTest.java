package be.kdg.groepi.service;

import be.kdg.groepi.model.User;
import be.kdg.groepi.security.StandardPasswordEncoder;
import static be.kdg.groepi.utils.DateUtil.dateToLong;
import java.sql.Timestamp;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Tim
 * Date: 8/02/13
 * Time: 10:20
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Transactional
public class UserServiceTest {
    //    private final Session session = HibernateUtil.getSessionFactory().openSession();
    //private final userService userService = new userService();

    private User user;
    @Autowired
    protected UserService userService;

    @Before
    public void beforeEachTest() {
        user = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        user.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
    }

    @After
    public void afterEachTest() {
        user = null;
        for (User user : userService.getAllUsers()) {
            userService.deleteUser(user);
        }
    }

    @Test
    public void testCreateUser() {
        userService.createUser(user);
        assertEquals("createUser: users are the same", user, userService.getUserById(user.getId()));
    }

    @Test
    public void testUpdateUser() {
        userService.createUser(user);
        user.setName("NOT TIMMEH");
        user.setPassword("hemmitton");
        user.setDateOfBirth(dateToLong(3, 6, 2012, 12, 45, 0));
        user.setEmail("NOTTIM@M.EH");
        user.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        userService.updateUser(user);
        assertEquals("testUpdateUser: User was not updated", userService.getUserById(user.getId()));
    }

    @Test
    public void testNullPicture() {
        user.setProfilePicture(null);
        userService.createUser(user);
        assertEquals("User profile picture should be null.", user.getProfilePicture(), null);
    }

    @Test
    public void testDeleteUser() {
        userService.createUser(user);
        assertNotNull("deleteUser: User must exist", userService.getUserById(user.getId()));
        userService.deleteUser(user);
        assertNull("deleteUser: User may not exist", userService.getUserById(user.getId()));
    }

    @Test
    public void testGetAllUsers() {
        int sizeBeforeThisTest = userService.getAllUsers().size();
        for (int i = 1; i <= 10; i++) {
            User newUser = new User("User " + i, "user" + i + "@M.EH", "pwd", user.getDateOfBirth());
            userService.createUser(newUser);
        }
        assertEquals("getAllUsers: listsize", sizeBeforeThisTest + 10, userService.getAllUsers().size());
    }

    @Test
    public void testChangePassword() {
        String newPassword = "newPassword";
        userService.createUser(user);
        user.setPassword(newPassword);
        userService.updateUser(user);
        assertTrue("testChangePassword: password wasn't reset", user.getPassword().equals(newPassword));
    }

    @Test
    public void testResetPassword() {
        StandardPasswordEncoder spe = new StandardPasswordEncoder();
        String oldPassword = user.getPassword();
        userService.createUser(user);
        user.setPassword(spe.encodePassword("newpassword", user));
        userService.updateUser(user);
        assertTrue("testResetPassword: user was not found", userService.resetPassword(user.getEmail()));
        assertFalse("testResetPassword: password was not changed", user.getPassword().equals(oldPassword));
    }

    @Test
    public void testGetByEmail() {
        userService.createUser(user);
        userService.getUserByEmail("TIM@M.EH");
        assertEquals("testGetUserByEmail: kztugelhiugshdl EMAILNOTHERE", user, userService.getUserById(user.getId()));
    }

    @Test
    public void testGetByFBUserID() {
        userService.createUser(user);
        user.setFBUserID("123456789");
        userService.updateUser(user);
        assertEquals("testGetByFBUserID: ", user, userService.getUserByFbUserId(user.getFBUserID()));
    }

    @Test
    public void testCompareUser() {
        User user2 = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        userService.createUser(user2);
        user2.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        userService.updateUser(user2);
        assertTrue("Users should be the same", user.equals(user2));
    }

    @Test
    public void testCompareUserNullObject() {
        User user2 = null;
        assertFalse("Users should not the same", user.equals(user2));
    }

    @Test
    public void testCompareUserName() {
        User user2 = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        userService.createUser(user2);
        user2.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        userService.updateUser(user2);

        user2.setName("nope");
        userService.updateUser(user2);
        assertFalse("Users should not the same", user.equals(user2));
    }

    @Test
    public void testCompareUserEmail() {
        User user2 = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        userService.createUser(user2);
        user2.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        userService.updateUser(user2);

        user2.setEmail("email@nope.be");
        userService.updateUser(user2);
        assertFalse("Users should not the same", user.equals(user2));
    }

    @Test
    public void testCompareUserDoB() {
        User user2 = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        userService.createUser(user2);
        user2.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        userService.updateUser(user2);

        user2.setDateOfBirth(dateToLong(3, 6, 2013, 12, 45, 0));
        userService.updateUser(user2);
        assertFalse("Users should not the same", user.equals(user2));
    }

    @Test
    public void testCompareUserProfilePicture() {
        User user2 = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        userService.createUser(user2);
        user2.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        userService.updateUser(user2);

        user2.setProfilePicture("nope");
        userService.updateUser(user2);
        assertFalse("Users should not the same", user.equals(user2));
    }

    @Test
    public void testCompareUserPassword() {
        user.setPassword("heheheheheh");
        User user2 = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        userService.createUser(user2);
        user2.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        user2.setPassword("heheheheheh");
        userService.updateUser(user2);

        user2.setPassword("hohohohoho");
        userService.updateUser(user2);
        assertFalse("Users should not the same", user.equals(user2));
    }

    @Test
    public void testCompareUserPasswordResetString() {
        user.setPasswordResetString("heheheheheh");
        User user2 = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        userService.createUser(user2);
        user2.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        user2.setPasswordResetString("heheheheheh");
        userService.updateUser(user2);

        user2.setPasswordResetString("hohohohoho");
        userService.updateUser(user2);
        assertFalse("Users should not the same", user.equals(user2));
    }

    @Test
    public void testCompareUserPasswordResetTimestamp() {
        user.setPasswordResetTimestamp(new Timestamp(1500));
        User user2 = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        userService.createUser(user2);
        user2.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        user.setPasswordResetTimestamp(new Timestamp(1500));
        userService.updateUser(user2);

        user.setPasswordResetTimestamp(new Timestamp(2000));
        userService.updateUser(user2);
        assertFalse("Users should not the same", user.equals(user2));
    }
}
