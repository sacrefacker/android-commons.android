package com.roxiemobile.androidcommons.util;

import com.annimon.stream.Stream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class StringUtils
{
// MARK: - Construction

    private StringUtils() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * <p>Checks if a CharSequence is empty ("") or {@code null}.</p>
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param value The CharSequence to check, may be null
     * @return {@code true} if the CharSequence is empty or null
     */
    public static boolean isEmpty(final CharSequence value) {
        return (value == null) || (value.length() < 1);
    }

    /**
     * <p>Checks if all of the CharSequences are empty ("") or {@code null}.</p>
     *
     * <pre>
     * StringUtils.isAllEmpty(null)             = true
     * StringUtils.isAllEmpty(null, "")         = true
     * StringUtils.isAllEmpty(new String[] {})  = true
     * StringUtils.isAllEmpty(null, "foo")      = false
     * StringUtils.isAllEmpty("", "bar")        = false
     * StringUtils.isAllEmpty("bob", "")        = false
     * StringUtils.isAllEmpty("  bob  ", null)  = false
     * StringUtils.isAllEmpty(" ", "bar")       = false
     * StringUtils.isAllEmpty("foo", "bar")     = false
     * </pre>
     *
     * @param values The CharSequences to check, may be null or empty
     * @return {@code true} if all of the CharSequences are empty or null
     */
    public static boolean isAllEmpty(final CharSequence... values) {
        return ArrayUtils.isEmpty(values) || Stream.of(values).allMatch(StringUtils::isEmpty);
    }

    /**
     * <p>Checks if a CharSequence is not empty ("") and not {@code null}.</p>
     *
     * <pre>
     * StringUtils.isNotEmpty(null)      = false
     * StringUtils.isNotEmpty("")        = false
     * StringUtils.isNotEmpty(" ")       = true
     * StringUtils.isNotEmpty("bob")     = true
     * StringUtils.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param value The CharSequence to check, may be null
     * @return {@code true} if the CharSequence is not empty and not null
     */
    public static boolean isNotEmpty(final CharSequence value) {
        return !isEmpty(value);
    }

    /**
     * <p>Checks if none of the CharSequences are not empty ("") and not {@code null}.</p>
     *
     * <pre>
     * StringUtils.isAllNotEmpty(null)             = false
     * StringUtils.isAllNotEmpty(null, "foo")      = false
     * StringUtils.isAllNotEmpty("", "bar")        = false
     * StringUtils.isAllNotEmpty("bob", "")        = false
     * StringUtils.isAllNotEmpty("  bob  ", null)  = false
     * StringUtils.isAllNotEmpty(new String[] {})  = false
     * StringUtils.isAllNotEmpty(" ", "bar")       = true
     * StringUtils.isAllNotEmpty("foo", "bar")     = true
     * </pre>
     *
     * @param values The CharSequences to check, may be null or empty
     * @return {@code true} if none of the CharSequences are empty or null
     */
    public static boolean isAllNotEmpty(final CharSequence... values) {
        return ArrayUtils.isNotEmpty(values) && Stream.of(values).allMatch(StringUtils::isNotEmpty);
    }

// MARK: - Methods

    /**
     * <p>Checks if a CharSequence is empty (""), {@code null} or whitespace only.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param value The CharSequence to check, may be null
     * @return {@code true} if the CharSequence is null, empty or whitespace only
     */
    public static boolean isBlank(final CharSequence value) {
        return (value == null) || (strip(value).length() < 1);
    }

    /**
     * <p>Checks if all of the CharSequences are empty (""), {@code null} or whitespace only.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.isAllBlank(null)             = true
     * StringUtils.isAllBlank(null, "foo")      = false
     * StringUtils.isAllBlank(null, null)       = true
     * StringUtils.isAllBlank("", "bar")        = false
     * StringUtils.isAllBlank("bob", "")        = false
     * StringUtils.isAllBlank("  bob  ", null)  = false
     * StringUtils.isAllBlank(" ", "bar")       = false
     * StringUtils.isAllBlank("foo", "bar")     = false
     * StringUtils.isAllBlank(new String[] {})  = true
     * </pre>
     *
     * @param values The CharSequences to check, may be null or empty
     * @return {@code true} if all of the CharSequences are empty or null or whitespace only
     */
    public static boolean isAllBlank(final CharSequence... values) {
        return ArrayUtils.isEmpty(values) || Stream.of(values).allMatch(StringUtils::isBlank);
    }

    /**
     * <p>Checks if a CharSequence is not empty (""), not {@code null} and not whitespace only.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param value The CharSequence to check, may be null
     * @return {@code true} if the CharSequence is not empty and not null and not whitespace only
     */
    public static boolean isNotBlank(final CharSequence value) {
        return !isBlank(value);
    }

    /**
     * <p>Checks if none of the CharSequences are empty (""), {@code null} or whitespace only.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.isAllNotBlank(null)             = false
     * StringUtils.isAllNotBlank(null, "foo")      = false
     * StringUtils.isAllNotBlank(null, null)       = false
     * StringUtils.isAllNotBlank("", "bar")        = false
     * StringUtils.isAllNotBlank("bob", "")        = false
     * StringUtils.isAllNotBlank("  bob  ", null)  = false
     * StringUtils.isAllNotBlank(" ", "bar")       = false
     * StringUtils.isAllNotBlank(new String[] {})  = false
     * StringUtils.isAllNotBlank("foo", "bar")     = true
     * </pre>
     *
     * @param values The CharSequences to check, may be null or empty
     * @return {@code true} if none of the CharSequences are empty or null or whitespace only
     */
    public static boolean isAllNotBlank(final CharSequence... values) {
        return ArrayUtils.isNotEmpty(values) && Stream.of(values).allMatch(StringUtils::isNotBlank);
    }

// MARK: - Methods

    /**
     * <p>Strips whitespace from the start and end of a CharSequence.</p>
     *
     * <p>This is similar to {@link String#trim()} but removes whitespace.
     * Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <p>A {@code null} input CharSequence returns {@code null}.</p>
     *
     * <pre>
     * StringUtils.strip(null)     = null
     * StringUtils.strip("")       = ""
     * StringUtils.strip("   ")    = ""
     * StringUtils.strip("abc")    = "abc"
     * StringUtils.strip("  abc")  = "abc"
     * StringUtils.strip("abc  ")  = "abc"
     * StringUtils.strip(" abc ")  = "abc"
     * StringUtils.strip(" ab c ") = "ab c"
     * </pre>
     *
     * @param value The CharSequence to remove whitespace from, may be null
     * @return the stripped CharSequence, {@code null} if null CharSequence input
     */
    public static CharSequence strip(final CharSequence value) {
        return strip(value, null);
    }

    public static String strip(final String value) {
        return strip((CharSequence) value).toString();
    }

    /**
     * <p>Strips any of a set of characters from the start and end of a CharSequence.
     * This is similar to {@link String#trim()} but allows the characters
     * to be stripped to be controlled.</p>
     *
     * <p>A {@code null} input CharSequence returns {@code null}.
     * An empty string ("") input returns the empty string.</p>
     *
     * <p>If the stripChars String is {@code null}, whitespace is
     * stripped as defined by {@link Character#isWhitespace(char)}.
     * Alternatively use {@link #strip(CharSequence)}.</p>
     *
     * <pre>
     * StringUtils.strip(null, *)          = null
     * StringUtils.strip("", *)            = ""
     * StringUtils.strip("abc", null)      = "abc"
     * StringUtils.strip("  abc", null)    = "abc"
     * StringUtils.strip("abc  ", null)    = "abc"
     * StringUtils.strip(" abc ", null)    = "abc"
     * StringUtils.strip("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param value      The CharSequence to remove characters from, may be null
     * @param stripChars The characters to remove, null treated as whitespace
     * @return the stripped CharSequence, {@code null} if null CharSequence input
     */
    public static CharSequence strip(final CharSequence value, final String stripChars) {
        if (isEmpty(value)) {
            return value;
        }
        return stripEnd(stripStart(value, stripChars), stripChars);
    }

    public static String strip(final String value, final String stripChars) {
        return strip((CharSequence) value, stripChars).toString();
    }

    /**
     * <p>Strips any of a set of characters from the start of a CharSequence.</p>
     *
     * <p>A {@code null} input CharSequence returns {@code null}.
     * An empty string ("") input returns the empty string.</p>
     *
     * <p>If the stripChars String is {@code null}, whitespace is
     * stripped as defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.stripStart(null, *)          = null
     * StringUtils.stripStart("", *)            = ""
     * StringUtils.stripStart("abc", "")        = "abc"
     * StringUtils.stripStart("abc", null)      = "abc"
     * StringUtils.stripStart("  abc", null)    = "abc"
     * StringUtils.stripStart("abc  ", null)    = "abc  "
     * StringUtils.stripStart(" abc ", null)    = "abc "
     * StringUtils.stripStart("yxabc  ", "xyz") = "abc  "
     * </pre>
     *
     * @param value      The CharSequence to remove characters from, may be null
     * @param stripChars The characters to remove, null treated as whitespace
     * @return the stripped CharSequence, {@code null} if null CharSequence input
     */
    public static CharSequence stripStart(final CharSequence value, final String stripChars) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return value;
        }
        int start = 0;
        if (stripChars == null) {
            while (start != strLen && Character.isWhitespace(value.charAt(start))) {
                start++;
            }
        }
        else if (stripChars.isEmpty()) {
            return value;
        }
        else {
            while (start != strLen && stripChars.indexOf(value.charAt(start)) != INDEX_NOT_FOUND) {
                start++;
            }
        }
        return value.subSequence(start, value.length() - 1);
    }

    public static String stripStart(final String value, final String stripChars) {
        return stripStart((CharSequence) value, stripChars).toString();
    }

    /**
     * <p>Strips any of a set of characters from the end of a CharSequence.</p>
     *
     * <p>A {@code null} input CharSequence returns {@code null}.
     * An empty string ("") input returns the empty string.</p>
     *
     * <p>If the stripChars CharSequence is {@code null}, whitespace is
     * stripped as defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.stripEnd(null, *)          = null
     * StringUtils.stripEnd("", *)            = ""
     * StringUtils.stripEnd("abc", "")        = "abc"
     * StringUtils.stripEnd("abc", null)      = "abc"
     * StringUtils.stripEnd("  abc", null)    = "  abc"
     * StringUtils.stripEnd("abc  ", null)    = "abc"
     * StringUtils.stripEnd(" abc ", null)    = " abc"
     * StringUtils.stripEnd("  abcyx", "xyz") = "  abc"
     * StringUtils.stripEnd("120.00", ".0")   = "12"
     * </pre>
     *
     * @param value      The CharSequence to remove characters from, may be null
     * @param stripChars The set of characters to remove, null treated as whitespace
     * @return the stripped CharSequence, {@code null} if null CharSequence input
     */
    public static CharSequence stripEnd(final CharSequence value, final String stripChars) {
        int end;
        if (value == null || (end = value.length()) == 0) {
            return value;
        }

        if (stripChars == null) {
            while (end != 0 && Character.isWhitespace(value.charAt(end - 1))) {
                end--;
            }
        }
        else if (stripChars.isEmpty()) {
            return value;
        }
        else {
            while (end != 0 && stripChars.indexOf(value.charAt(end - 1)) != INDEX_NOT_FOUND) {
                end--;
            }
        }
        return value.subSequence(0, end);
    }

    public static String stripEnd(final String value, final String stripChars) {
        return stripEnd((CharSequence) value, stripChars).toString();
    }

// MARK: - Methods

    /**
     * Returns the given string if it is nonempty; {@code null} otherwise.
     */
    public static String emptyToNull(String value) {
        return isEmpty(value) ? null : value;
    }

    /**
     * Returns the given string if it is non {@code null}; the empty string otherwise.
     */
    public static String nullToEmpty(String value) {
        return (value == null) ? "" : value;
    }

// MARK: - Methods

    /**
     * TODO
     */
    public static String streamToString(InputStream is) throws IOException {
        return streamToString(is, "UTF-8");
    }

    /**
     * TODO
     */
    public static String streamToString(InputStream is, String encoding) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int length;

        while ((length = is.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }

        // Done
        return new String(baos.toByteArray(), encoding);
    }

// MARK: - Constants

    /**
     * Represents a failed index search.
     */
    public static final int INDEX_NOT_FOUND = -1;
}
