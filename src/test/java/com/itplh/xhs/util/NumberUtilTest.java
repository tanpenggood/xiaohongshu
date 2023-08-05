package com.itplh.xhs.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: tanpenggood
 * @date: 2023-08-05 13:55
 */
public class NumberUtilTest {

    @Test
    public void isNumber() {
        Assert.assertFalse(NumberUtil.isNumber(null));
        Assert.assertFalse(NumberUtil.isNumber(""));
        Assert.assertFalse(NumberUtil.isNumber(" "));
        Assert.assertFalse(NumberUtil.isNumber("11+"));
        Assert.assertFalse(NumberUtil.isNumber("1k"));
        Assert.assertFalse(NumberUtil.isNumber("1w"));

        Assert.assertTrue(NumberUtil.isNumber("11"));
        Assert.assertTrue(NumberUtil.isNumber("011"));
        Assert.assertTrue(NumberUtil.isNumber("01100"));
        Assert.assertTrue(NumberUtil.isNumber("001100"));
        Assert.assertTrue(NumberUtil.isNumber("00011000"));
    }

    @Test
    public void valueOf() {
        Assert.assertNull(NumberUtil.valueOf(null));
        Assert.assertNull(NumberUtil.valueOf(""));
        Assert.assertNull(NumberUtil.valueOf(" "));
        Assert.assertNull(NumberUtil.valueOf("11+"));
        Assert.assertNull(NumberUtil.valueOf("1k"));
        Assert.assertNull(NumberUtil.valueOf("1w"));

        Assert.assertTrue(NumberUtil.valueOf("11") == 11);
        Assert.assertTrue(NumberUtil.valueOf("011") == 11);
        Assert.assertTrue(NumberUtil.valueOf("01100") == 1100);
        Assert.assertTrue(NumberUtil.valueOf("001100") == 1100);
        Assert.assertTrue(NumberUtil.valueOf("00011000") == 11000);
    }

}
