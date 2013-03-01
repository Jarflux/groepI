package be.kdg.groepi.utils;

import be.kdg.groepi.model.User;
import be.kdg.groepi.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static be.kdg.groepi.utils.DateUtil.dateToLong;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 28-2-13
 * Time: 15:22
 * To change this template use File | Settings | File Templates.
 */
public class FileUtilTest {
    private MockMultipartFile file;

    @Before
    public void beforeEachTest() {
        file = new MockMultipartFile("Test.txt", "Test".getBytes());
    }

    @After
    public void afterEachTest() {

    }

    @Test
    public void testUpload() throws IOException {
        User user = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        UserService.createUser(user);
        assertEquals("File has not been uploaded",FileUtil.savePicture(file,user.getId()),
                "src/main/webapp/images/profilepictures/" + user.getId() + ".jpg");

        assertTrue("File paths are not equal", FileUtil.savePicture(file,
                user.getId()).equals("src/main/webapp/images/profilepictures/" + user.getId() + ".jpg"));
    }
}
