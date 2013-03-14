package be.kdg.groepi.utils;

import be.kdg.groepi.model.User;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Author: Ben Oeyen
 * Date: 21/02/13
 * Class:
 * Description:
 */
public class CompareUtil {

    public static boolean compareString(String x, String y) {
        if ((x == null || y == null) && !(x == null && y == null)) {
            return false;
        } else if (x != null && y != null) {
            return (x.compareTo(y) == 0);
        }
        return true;
    }

    public static boolean compareLong(Long x, Long y) {
        if ((x == null || y == null) && !(x == null && y == null)) {
            return false;
        } else if (x != null && y != null) {
            return (x.compareTo(y) == 0);
        }
        return true;
    }

    public static boolean compareInteger(Integer x, Integer y) {
        if ((x == null || y == null) && !(x == null && y == null)) {
            return false;
        } else if (x != null && y != null) {
            return (x.compareTo(y) == 0);
        }
        return true;
    }

    public static boolean compareTimestamp(Timestamp x, Timestamp y) {
        if ((x == null || y == null) && !(x == null && y == null)) {
            return false;
        } else if (x != null && y != null) {
            return (x.compareTo(y) == 0);
        }
        return true;
    }

    public static boolean compareUser(User x, User y) {
        if ((x == null || y == null) && !(x == null && y == null)) {
            return false;
        } else if (x != null && y != null) {
            return (x.equals(y));
        }
        return true;
    }

    public static boolean compareSet(Set<?> x, Set<?> y) {
        if ((x == null || y == null) && !(x == null && y == null)) {
            return false;
        } else if (x != null && y != null) {
            if (!(x.containsAll(y) && y.containsAll(x))) {
                return false;
            }
        }
        return true;
    }

    public static boolean compareList(List<?> x, List<?> y) {
        if ((x == null || y == null) && !(x == null && y == null)) {
            return false;
        } else if (x != null && y != null) {
            if (!(x.containsAll(y) && y.containsAll(x))) {
                return false;
            }
        }
        return true;
    }

    public static String getHashedPassword(String text)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md;
        md = MessageDigest.getInstance("SHA-1");

        byte[] sha1hash = new byte[40];

        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        sha1hash = md.digest();

        return convertToHex(sha1hash);
    }

    private static String convertToHex(byte[] data) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            if (i % 4 == 0 && i != 0) {
                buffer.append("");
            }
            int x = (int) data[i];
            if (x < 0) {
                x += 256;
            }
            if (x < 16) {
                buffer.append("0");
            }
            buffer.append(Integer.toString(x, 16));
        }
        return buffer.toString();
    }
}
