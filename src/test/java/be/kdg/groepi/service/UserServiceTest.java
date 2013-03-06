package be.kdg.groepi.service;

import be.kdg.groepi.model.User;
import be.kdg.groepi.security.StandardPasswordEncoder;
import be.kdg.groepi.utils.CompareUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static be.kdg.groepi.utils.DateUtil.dateToLong;
import java.sql.Timestamp;
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
        user = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        user.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
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
        assertEquals("createUser: users are the same", user, UserService.getUserById(user.getId()));
    }

    @Test
    public void testUpdateUser() {
        UserService.createUser(user);
        user.setName("NOT TIMMEH");
        user.setPassword("hemmitton");
        user.setDateOfBirth(dateToLong(3, 6, 2012, 12, 45, 0));
        user.setEmail("NOTTIM@M.EH");
        user.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        UserService.updateUser(user);
        assertEquals(user, UserService.getUserById(user.getId()));
    }

    @Test
    public void testNullPicture() {
        user.setProfilePicture(null);
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
        int sizeBeforeThisTest = UserService.getAllUsers().size();
        for (int i = 1; i <= 10; i++) {
            User newUser = new User("User " + i, "user" + i + "@M.EH", "pwd", user.getDateOfBirth());
            UserService.createUser(newUser);
        }
        assertEquals("getAllUsers: listsize", sizeBeforeThisTest + 10, UserService.getAllUsers().size());
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
        StandardPasswordEncoder spe = new StandardPasswordEncoder();
        String oldPassword = user.getPassword();
        UserService.createUser(user);
        user.setPassword(spe.encodePassword("newpassword", user));
        UserService.updateUser(user);
        assertTrue("testResetPassword: user was not found", UserService.resetPassword(user.getEmail()));
        assertFalse("testResetPassword: password was not changed", user.getPassword().equals(oldPassword));
    }

    @Test
    public void testGetByEmail() {
        UserService.createUser(user);
        UserService.getUserByEmail("TIM@M.EH");
        assertEquals("testGetUserByEmail: kztugelhiugshdl EMAILNOTHERE", user, UserService.getUserById(user.getId()));
    }

    @Test
    public void testCompareUser() {
        User user2 = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        UserService.createUser(user2);
        user2.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        UserService.updateUser(user2);
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
        UserService.createUser(user2);
        user2.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        UserService.updateUser(user2);

        user2.setName("nope");
        UserService.updateUser(user2);
        assertFalse("Users should not the same", user.equals(user2));
    }

    @Test
    public void testCompareUserEmail() {
        User user2 = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        UserService.createUser(user2);
        user2.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        UserService.updateUser(user2);

        user2.setEmail("email@nope.be");
        UserService.updateUser(user2);
        assertFalse("Users should not the same", user.equals(user2));
    }

    @Test
    public void testCompareUserDoB() {
        User user2 = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        UserService.createUser(user2);
        user2.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        UserService.updateUser(user2);

        user2.setDateOfBirth(dateToLong(3, 6, 2013, 12, 45, 0));
        UserService.updateUser(user2);
        assertFalse("Users should not the same", user.equals(user2));
    }

    @Test
    public void testCompareUserProfilePicture() {
        User user2 = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        UserService.createUser(user2);
        user2.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        UserService.updateUser(user2);

        user2.setProfilePicture("nope");
        UserService.updateUser(user2);
        assertFalse("Users should not the same", user.equals(user2));
    }

    @Test
    public void testCompareUserPassword() {
        user.setPassword("heheheheheh");
        User user2 = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        UserService.createUser(user2);
        user2.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        user2.setPassword("heheheheheh");
        UserService.updateUser(user2);

        user2.setPassword("hohohohoho");
        UserService.updateUser(user2);
        assertFalse("Users should not the same", user.equals(user2));
    }

    @Test
    public void testCompareUserPasswordResetString() {
        user.setPasswordResetString("heheheheheh");
        User user2 = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        UserService.createUser(user2);
        user2.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        user2.setPasswordResetString("heheheheheh");
        UserService.updateUser(user2);

        user2.setPasswordResetString("hohohohoho");
        UserService.updateUser(user2);
        assertFalse("Users should not the same", user.equals(user2));
    }

    @Test
    public void testCompareUserPasswordResetTimestamp() {
        user.setPasswordResetTimestamp(new Timestamp(1500));
        User user2 = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        UserService.createUser(user2);
        user2.setProfilePicture("http://www.nawang.com/Photos/10Logos/Profile_LOGO.jpg");
        user.setPasswordResetTimestamp(new Timestamp(1500));
        UserService.updateUser(user2);

        user.setPasswordResetTimestamp(new Timestamp(2000));
        UserService.updateUser(user2);
        assertFalse("Users should not the same", user.equals(user2));
    }
}
