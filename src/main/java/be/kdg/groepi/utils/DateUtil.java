package be.kdg.groepi.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Long dateToLong(int day, int month, int year, int hour, int minute, int second){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, day,hour,minute,second);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime().getTime();
    }
}
