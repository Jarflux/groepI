package be.kdg.groepi.utils;

import be.kdg.groepi.model.User;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpSession;

public class DateUtil {

    public static Long dateToLong(int day, int month, int year, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, hour, minute, second);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime().getTime();
    }

    public static Long dateStringToLong(String dateString, String timeString) {
        int day = 0, month = 0, year = 0, hour = 0, min = 0, sec = 0;

        if (dateString != null) {
            String[] dateArray = dateString.split("-");
            day = Integer.parseInt(dateArray[0]);
            month = Integer.parseInt(dateArray[1]);
            year = Integer.parseInt(dateArray[2]);
        }
        if (timeString != null) {
            String[] timeArray = timeString.split(":");
            hour = Integer.parseInt(timeArray[0]);
            min = Integer.parseInt(timeArray[1]);
//            sec = Integer.parseInt(timeArray[2]);
        }
        Long date = DateUtil.dateToLong(day, month, year, hour, min, sec);
        return date;
    }

    public static Long dateStringToLongAlt(String dateString, String timeString) {
        int day = 0, month = 0, year = 0, hour = 0, min = 0, sec = 0;

        if (dateString != null) {
            String[] dateArray = dateString.split("/");
            day = Integer.parseInt(dateArray[1]);
            month = Integer.parseInt(dateArray[0]);
            year = Integer.parseInt(dateArray[2]);
        }
        if (timeString != null) {
            String[] timeArray = timeString.split(":");
            hour = Integer.parseInt(timeArray[0]);
            min = Integer.parseInt(timeArray[1]);
//            sec = Integer.parseInt(timeArray[2]);
        }
        Long date = DateUtil.dateToLong(day, month, year, hour, min, sec);
        return date;
    }

    public static Long dateStringToLong(String dateString) {
        return dateStringToLong(dateString, null);
    }

    public static Long timeStringToLong(String timeString) {
        return dateStringToLong(null, timeString);
    }

    public static Date longToDate(Long date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);
        return cal.getTime();
    }

    public static String formatDate(HttpSession session) {
        Date date = DateUtil.longToDate(((User) session.getAttribute("userObject")).getDateOfBirth());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    public static String formatTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }

    public static String formatTime(long date) {
        return formatTime(longToDate(date));
    }

    public static String formatDate(long date) {
        return formatDate(longToDate(date));
    }
}
