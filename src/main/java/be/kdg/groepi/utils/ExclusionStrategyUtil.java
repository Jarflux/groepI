package be.kdg.groepi.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 10-3-13
 * Time: 17:05
 * To change this template use File | Settings | File Templates.
 */
public class ExclusionStrategyUtil implements ExclusionStrategy {
    private final Class<?> excludedThisClass;

    public ExclusionStrategyUtil(Class<?> excludedThisClass) {
        this.excludedThisClass = excludedThisClass;
    }

    public boolean shouldSkipClass(Class<?> clazz) {
        return excludedThisClass.equals(clazz);
    }

    public boolean shouldSkipField(FieldAttributes f) {
        return excludedThisClass.equals(f.getDeclaredClass());
    }
}
