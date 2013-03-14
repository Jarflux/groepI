package be.kdg.groepi.utils;

import be.kdg.groepi.model.User;
import be.kdg.groepi.service.UserService;
import static be.kdg.groepi.utils.DateUtil.dateToLong;

import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 26-2-13
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Transactional
public class DateUtilTest {

    private Calendar cal;
    @Autowired
    protected UserService userService;

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
    public void testFormatDate() {
        MockHttpSession mockHttpSession = new MockHttpSession();
        User user = new User("TIMMEH", "TIM@M.EH", "hemmit", dateToLong(4, 5, 2011, 15, 32, 0));
        userService.createUser(user);
        mockHttpSession.setAttribute("userObject", user);
        assertTrue("Formatted date should be dd-MM-yyyy [mock]", DateUtil.formatDate(mockHttpSession).equals("04-05-2011"));
        Calendar cal = Calendar.getInstance();
        cal.set(2011, Calendar.MAY, 4);
        Date date = new Date(cal.getTime().getTime());
        assertTrue("Formatted date should be dd-MM-yyyy [date]", DateUtil.formatDate(date).equals("04-05-2011"));
        assertTrue("Formatted date should be dd-MM-yyyy [long]", DateUtil.formatDate(date.getTime()).equals("04-05-2011"));
    }

    @Test
    public void testDateStringToLong2() {
        assertTrue("Datetring does not return correct date", DateUtil.dateStringToLong("04-05-2011").equals(DateUtil.dateStringToLong("04-05-2011", null)));
    }

    @Test
    public void timeStringToLong() {
        assertTrue("Timestring does not return correct timestamp", DateUtil.timeStringToLong("11:30").equals(DateUtil.dateStringToLong(null, "11:30")));
    }

    @Test
    public void testFormatTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(2011, Calendar.MAY, 4, 15, 30, 0);
        Date date = new Date(cal.getTime().getTime());
        assertTrue("Formatted time should be HH:mm [date]", DateUtil.formatTime(date).equals("15:30"));
        assertTrue("Formatted time should be HH:mm [long]", DateUtil.formatTime(date.getTime()).equals("15:30"));
    }
    /*
     @Test
     public void testLongToDateString() {
     long date = DateUtil.dateToLong(15, 7, 1992, 16, 30, 0);
     assertTrue("testLongToDateString: does not return correct date format", DateUtil.longToDateString(date).equals("15-07-1992"));
     }

     @Test
     public void testLongToTimeString() {
     long date = DateUtil.dateToLong(15, 7, 1992, 16, 30, 0);
     assertTrue("testLongToDateString: does not return correct date format", DateUtil.longToTimeString(date).equals("16:30"));

     }*/
}
