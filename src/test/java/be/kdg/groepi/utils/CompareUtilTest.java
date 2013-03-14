package be.kdg.groepi.utils;

import be.kdg.groepi.model.User;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Dave
 * Date: 1/03/13
 * Time: 13:41
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
@Transactional
public class CompareUtilTest {

    @Test
    public void testCompareString() {
        assertFalse("Strings should not be equal", CompareUtil.compareString("testtest", "test2"));
        assertFalse("Strings should not be equal", CompareUtil.compareString("test", null));
        assertFalse("Strings should not be equal", CompareUtil.compareString(null, "test"));
        assertTrue("Strings should be equal", CompareUtil.compareString("testtest", "testtest"));
        assertTrue("Strings should be equal", CompareUtil.compareString(null, null));
    }

    @Test
    public void testCompareLong() {
        assertFalse("Long should not be equal", CompareUtil.compareLong(101L, 911L));
        assertFalse("Long should not be equal", CompareUtil.compareLong(101L, null));
        assertFalse("Long should not be equal", CompareUtil.compareLong(null, 911L));
        assertTrue("Long should be equal", CompareUtil.compareLong(911L, 911L));
        assertTrue("Long should be equal", CompareUtil.compareLong(null, null));
    }

    @Test
    public void testCompareInteger() {
        assertFalse("Integers should not be equal", CompareUtil.compareInteger(101, 911));
        assertFalse("Integers should not be equal", CompareUtil.compareInteger(101, null));
        assertFalse("Integers should not be equal", CompareUtil.compareInteger(null, 911));
        assertTrue("Integers should be equal", CompareUtil.compareInteger(911, 911));
        assertTrue("Integers should be equal", CompareUtil.compareInteger(null, null));
    }

    @Test
    public void testCompareSet() {
        Set<String> testSet = new HashSet<>(5);
        Set<String> testSet2 = new HashSet<>(5);

        testSet.addAll(fillSet(testSet, 5));
        assertFalse("Sets should not be equal", CompareUtil.compareSet(testSet, testSet2));
        assertFalse("Sets should not be equal", CompareUtil.compareSet(testSet, null));

        testSet2.addAll(fillSet(testSet2, 5));
        assertTrue("Sets should be equal", CompareUtil.compareSet(testSet, testSet2));
        assertTrue("Sets should be equal", CompareUtil.compareSet(null, null));
    }

    @Test
    public void testCompareList() {
        List<String> testList = new ArrayList<>(5);
        List<String> testList2 = new ArrayList<>(5);

        testList.addAll(fillList(testList, 5));
        assertFalse("Lists should not be equal", CompareUtil.compareList(testList, testList2));
        assertFalse("Lists should not be equal", CompareUtil.compareList(testList, null));
        testList2.addAll(fillList(testList2, 5));
        assertTrue("Lists should be equal", CompareUtil.compareList(testList, testList2));
        assertTrue("Lists should be equal", CompareUtil.compareList(testList, testList));
        assertTrue("Lists should be equal", CompareUtil.compareList(null, null));
    }

    @Test
    public void testCompareTimeStamp() {
        assertFalse("Timestamps should not be equal", CompareUtil.compareTimestamp(new Timestamp(101), new Timestamp(911)));
        assertFalse("Timestamps should not be equal", CompareUtil.compareTimestamp(new Timestamp(101), null));
        assertFalse("Timestamps should not be equal", CompareUtil.compareTimestamp(null, new Timestamp(911)));
        assertTrue("Timestamps should be equal", CompareUtil.compareTimestamp(new Timestamp(911), new Timestamp(911)));
        assertTrue("Timestamps should be equal", CompareUtil.compareTimestamp(null, null));
    }

    @Test
    public void testCompareUser() {
        User user1 = new User("TIMMEH", "TIM@M.EH", "hemmit", DateUtil.dateToLong(4, 5, 2011, 15, 32, 0));
        User user2 = new User("TIMMEH2", "TIM@M.EH2", "hemmit2", DateUtil.dateToLong(4, 5, 2013, 15, 32, 0));
        assertFalse("Users should not be equal", CompareUtil.compareUser(user1, user2));
        assertFalse("Users should not be equal", CompareUtil.compareUser(user1, null));
        assertFalse("Users should not be equal", CompareUtil.compareUser(null, user2));
        assertTrue("Users should be equal", CompareUtil.compareUser(user2, user2));
        assertTrue("Users should be equal", CompareUtil.compareUser(null, null));
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
