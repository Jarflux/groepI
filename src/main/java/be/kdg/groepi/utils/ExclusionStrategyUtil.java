package be.kdg.groepi.utils;

import be.kdg.groepi.annotations.ExcludeFromGson;
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
    public boolean shouldSkipClass(Class<?> clazz) {
        return clazz.getAnnotation(ExcludeFromGson.class) != null;
    }

    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(ExcludeFromGson.class) != null;

    }
}