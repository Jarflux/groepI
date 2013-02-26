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

    public static Long dateStringToLong(String dateString, String timeString){
        int day=0,month=0,year=0,hour=0,min=0,sec=0;

        if(dateString != null){
        String[] dateArray = dateString.split("-");
        day = Integer.parseInt(dateArray[2]);
        month = Integer.parseInt(dateArray[1]);
        year = Integer.parseInt(dateArray[0]);
        }
        if(dateString != null){
        String[] timeArray = timeString.split(":");
        hour = Integer.parseInt(timeArray[0]);
        min = Integer.parseInt(timeArray[1]);
        sec = Integer.parseInt(timeArray[2]);
        }
        Long date = DateUtil.dateToLong(day,month,year,hour,min,sec);
        return date;
    }

    public static Date longToDate(Long date){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);
        return cal.getTime();
    }
}
