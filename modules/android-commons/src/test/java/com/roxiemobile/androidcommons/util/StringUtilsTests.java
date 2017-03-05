package com.roxiemobile.androidcommons.util;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public final class StringUtilsTests
{
// MARK: - Tests

    @Test
    @SuppressWarnings("ConstantConditions")
    public void testStrip() {
        assertTrue(StringUtils.strip(null) == null);
        assertTrue(StringUtils.strip("").equals(""));
        assertTrue(StringUtils.strip("   ").equals(""));
        assertTrue(StringUtils.strip("abc").equals("abc"));
        assertTrue(StringUtils.strip("  abc").equals("abc"));
        assertTrue(StringUtils.strip("abc  ").equals("abc"));
        assertTrue(StringUtils.strip(" abc ").equals("abc"));
        assertTrue(StringUtils.strip(" ab c ").equals("ab c"));
        assertTrue(StringUtils.strip("  abcyx", "xyz").equals("  abc"));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void testStripStart() {
        assertTrue(StringUtils.stripStart(null) == null);
        assertTrue(StringUtils.stripStart("").equals(""));
        assertTrue(StringUtils.stripStart("   ").equals(""));
        assertTrue(StringUtils.stripStart("abc", "").equals("abc"));
        assertTrue(StringUtils.stripStart("abc").equals("abc"));
        assertTrue(StringUtils.stripStart("  abc").equals("abc"));
        assertTrue(StringUtils.stripStart("abc  ").equals("abc  "));
        assertTrue(StringUtils.stripStart(" abc ").equals("abc "));
        assertTrue(StringUtils.stripStart("yxabc  ", "xyz").equals("abc  "));
        assertTrue(StringUtils.stripStart("120.00", "12").equals("0.00"));
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void testStripEnd() {
        assertTrue(StringUtils.stripEnd(null) == null);
        assertTrue(StringUtils.stripEnd("").equals(""));
        assertTrue(StringUtils.stripEnd("   ").equals(""));
        assertTrue(StringUtils.stripEnd("abc", "").equals("abc"));
        assertTrue(StringUtils.stripEnd("abc").equals("abc"));
        assertTrue(StringUtils.stripEnd("  abc").equals("  abc"));
        assertTrue(StringUtils.stripEnd("abc  ").equals("abc"));
        assertTrue(StringUtils.stripEnd(" abc ").equals(" abc"));
        assertTrue(StringUtils.stripEnd("  abcyx", "xyz").equals("  abc"));
        assertTrue(StringUtils.stripEnd("120.00", ".0").equals("12"));
    }
}
