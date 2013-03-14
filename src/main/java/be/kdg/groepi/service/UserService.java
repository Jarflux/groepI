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
    private UserDao userDoa;

    public User getUserById(long id) {
        return userDoa.getUserById(id);
    }

    public User getUserByResetString(String resetString) {
        return userDoa.getUserByResetString(resetString);
    }

    public User getUserByFbUserId(String fbUserId) {
        return userDoa.getUserByFbUserId(fbUserId);
    }

    public User getUserByEmail(String email) {
        return userDoa.getUserByEmail(email);
    }

    public void createUser(User user) {
        userDoa.createUser(user);
    }

    public void updateUser(User user) {
        userDoa.updateUser(user);
    }

    public void deleteUser(User user) {
        userDoa.deleteUser(user);
    }

    public List<User> getAllUsers() {
        return userDoa.getAllUsers();
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
        User user = userDoa.getUserByEmail(email);
        if (user != null) {
            user.setPasswordResetString(passwordResetString);
            user.setPasswordResetTimestamp(passwordResetTimestamp);
            userDoa.updateUser(user);
            ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
            TripMail tim = (TripMail) context.getBean("tripMail");
            //TODO: pas email aan aan die van de gebruiker
            tim.sendMail("info@trippie.be", "info@trippie.be", "Reset password",
                    "Please follow this link to reset your password:\n http://localhost:8080/profile/reset/"
                    + /*user.getId() + "/" + */ passwordResetString + "\n\nThis link expires at:\n" + passwordResetTimestamp);
            return true;
        } else {
            return false;
        }
    }
}
