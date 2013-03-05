package be.kdg.groepi.utils;

import be.kdg.groepi.model.User;
import be.kdg.groepi.service.UserService;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

import static be.kdg.groepi.utils.DateUtil.dateToLong;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 26-2-13
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class DateUtilTest {
    private Calendar cal;

    @Before
    public void beforeEachTest() {
        cal = Calendar.getInstance();
    }

    @After
    public void afterEachTest() {
        cal.clear();
    }

    @Test
    public void testDateToLong() {
        cal.set(1992, 05, 30, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        assertEquals("De long die wordt gereturned is incorrect", DateUtil.dateToLong(30, 06, 1992, 0, 0, 0), (Long) cal.getTimeInMillis());
    }

    @Test
    public void testDateStringToLong() {
        cal.set(1992, 05, 30, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        assertEquals("De long die wordt gereturned is incorrect", DateUtil.dateStringToLong("30-06-1992", "0:0:0"), (Long) cal.getTimeInMillis());
    }

    @Test
    public void testLongToDate() {
        Long longDate = cal.getTime().getTime();
        assertEquals("De datum die wordt gereturned is incorrect", DateUtil.longToDate(longDate), cal.getTime());
    }

    @Test
    public void testFormatDate(){
        MockHttpSession mockHttpSession = new MockHttpSession();
        User user = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        UserService.createUser(user);
        mockHttpSession.setAttribute("userObject", user);
        assertTrue("Formatted date should be dd-MM-yyyy", DateUtil.formatDate(mockHttpSession).equals("04-05-2011"));
        Calendar cal = Calendar.getInstance();
        cal.set(2011, Calendar.MAY, 4);
        Date date = new Date(cal.getTime().getTime());
        assertTrue("Formatted date should be dd-MM-yyyy", DateUtil.formatDate(date).equals("04-05-2011"));
    }

}
