package be.kdg.groepi.service;

import be.kdg.groepi.dao.UserDao;
import be.kdg.groepi.model.User;
import be.kdg.groepi.utils.TripMail;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    public User getUserByResetString(String resetString) {
        return userDao.getUserByResetString(resetString);
    }

    public User getUserByFbUserId(String fbUserId) {
        return userDao.getUserByFbUserId(fbUserId);
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public void createUser(User user) {
        userDao.createUser(user);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public boolean resetPassword(String email) {
        final int RESET_TIME = 3;
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(32);

        for (int i = 0; i < 32; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }

        String passwordResetString = sb.toString();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR_OF_DAY) + RESET_TIME);
        Timestamp passwordResetTimestamp = new Timestamp(cal.getTime().getTime());
        User user = userDao.getUserByEmail(email);
        if (user != null) {
            user.setPasswordResetString(passwordResetString);
            user.setPasswordResetTimestamp(passwordResetTimestamp);
            userDao.updateUser(user);
            ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
            TripMail tim = (TripMail) context.getBean("tripMail");
            //TODO: is dit volledig?
            tim.sendMail("info@trippie.be", user.getEmail(), "Reset password",
                    "Please follow this link to reset your password:\n http://localhost:8080/profile/reset/"
                    + passwordResetString + "\n\nThis link expires at:\n" + passwordResetTimestamp);
            return true;
        } else {
            return false;
        }
    }
}
