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

    public static Long dateStringToLong(String dateString){
        String[] dateArray = dateString.split("-");
        int day = Integer.parseInt(dateArray[2]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[0]);
        Long date = DateUtil.dateToLong(day,month,year,0,0,0);
        return date;
    }

    public static Long dateTimeStringToLong(String dateString, String timeString){
        String[] dateArray = dateString.split("-");
        int day = Integer.parseInt(dateArray[2]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[0]);
        String[] timeArray = timeString.split(":");
        int hour = Integer.parseInt(timeArray[0]);
        int min = Integer.parseInt(timeArray[1]);
        int sec = Integer.parseInt(timeArray[2]);
        Long date = DateUtil.dateToLong(day,month,year,hour,min,sec);
        return date;
    }

    public static Long timeStringToLong(String timeString){
        String[] timeArray = timeString.split(":");
        int hour = Integer.parseInt(timeArray[0]);
        int min = Integer.parseInt(timeArray[1]);
        int sec = Integer.parseInt(timeArray[2]);
        Long date = DateUtil.dateToLong(0,0,0,hour,min,sec);
        return date;
    }

    public static Date longToDate(Long date){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);
        return cal.getTime();
    }
}
