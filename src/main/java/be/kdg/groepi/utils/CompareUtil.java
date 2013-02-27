package be.kdg.groepi.utils;

import java.security.MessageDigest;
import java.util.Set;

/**
 * Author: Ben Oeyen
 * Date: 21/02/13
 * Class:
 * Description:
 */
public class CompareUtil {

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

    public static String getHashedPassword(String password) {
        try {
            byte[] bytesOfMessage = password.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            return thedigest.toString();
        } catch (Exception e) {

        }
        return null;
    }
}
