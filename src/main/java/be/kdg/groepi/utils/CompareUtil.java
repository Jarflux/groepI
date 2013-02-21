package be.kdg.groepi.utils;

import java.io.Serializable;
import java.util.Set;

/**
 * Author: Ben Oeyen
 * Date: 21/02/13
 * Class:
 * Description:
 */
public class CompareUtil {
    public static boolean compareSet(Set<?> x, Set<?> y) {
        if ((x == null || y == null) && !(x == null && y == null)){
            return false;
        }else if (x != null && y != null) {
            if (!(x.containsAll(y) && y.containsAll(x))) {
                return false;
            }
        }
        return true;
    }
}
