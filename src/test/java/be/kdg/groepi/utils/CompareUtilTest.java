package be.kdg.groepi.utils;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Dave
 * Date: 1/03/13
 * Time: 13:41
 * To change this template use File | Settings | File Templates.
 */
public class CompareUtilTest {

    @Test
    public void testCompareSet() {
        Set<String> testSet = new HashSet<>(5);
        Set<String> testSet2 = new HashSet<>(5);

        testSet.addAll(fillSet(testSet, 5));
        assertFalse("Sets should not be equal", CompareUtil.compareSet(testSet, testSet2));

        testSet2.addAll(fillSet(testSet2, 5));
        assertTrue("Sets should be equal", CompareUtil.compareSet(testSet, testSet2));
    }

    @Test
    public void testCompareList() {
        List<String> testList = new ArrayList<>(5);
        List<String> testList2 = new ArrayList<>(5);

        testList.addAll(fillList(testList, 5));
        assertFalse("Lists should not be equal", CompareUtil.compareList(testList, testList2));

        testList2.addAll(fillList(testList2, 5));
        assertTrue("Lists should be equal", CompareUtil.compareList(testList, testList2));
    }

    @Test
    public void testHashPassword() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        assertFalse("Password should be different after hash",
                CompareUtil.getHashedPassword("thiswillbehashed").equals("thiswillbehashed"));
    }

    public Set<String> fillSet(Set<String> set, int aantal) {
        for (int i = 0; i < 5; i++) {
            set.add("string" + i);
        }
        return set;
    }

    public List<String> fillList(List<String> list, int aantal) {
        for (int i = 0; i < 5; i++) {
            list.add("string" + i);
        }
        return list;
    }
}
