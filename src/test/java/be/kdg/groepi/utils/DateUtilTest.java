package be.kdg.groepi.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

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
    public void testDateToLong(){
        cal.set(1992,05,30,0,0,0);
        cal.set(Calendar.MILLISECOND, 0);
        assertEquals("De long die wordt gereturned is incorrect",DateUtil.dateToLong(30,06,1992,0,0,0),(Long)cal.getTimeInMillis());
    }

    @Test
    public void testDateStringToLong(){
     cal.set(1992,05,30,0,0,0);
     cal.set(Calendar.MILLISECOND, 0);
     assertEquals("De long die wordt gereturned is incorrect",DateUtil.dateStringToLong("1992-06-30","0:0:0"),(Long)cal.getTimeInMillis());
    }

    @Test
    public void testLongToDate(){
        Long longDate = cal.getTime().getTime();
        assertEquals("De datum die wordt gereturned is incorrect", DateUtil.longToDate(longDate),cal.getTime());
    }


}
