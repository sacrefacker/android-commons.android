package com.roxiemobile.androidcommons.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.annimon.stream.Stream;
import com.roxiemobile.androidcommons.data.Constants.Charsets;
import com.roxiemobile.androidcommons.diagnostics.Guard;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public final class StringUtils
{
// MARK: - Construction

    private StringUtils() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * Checks if a CharSequence is empty ("") or {@code null}.
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
    public static boolean isEmpty(final @Nullable CharSequence value) {
        return (value == null) || (value.length() < 1);
    }

    /**
     * Checks if all of the CharSequences are empty ("") or {@code null}.
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
    public static boolean isAllEmpty(final @Nullable CharSequence... values) {
        return ArrayUtils.isEmpty(values) || Stream.of(values).allMatch(StringUtils::isEmpty);
    }

    /**
     * Checks if a CharSequence is not empty ("") and not {@code null}.
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
    public static boolean isNotEmpty(final @Nullable CharSequence value) {
        return !isEmpty(value);
    }

    /**
     * Checks if none of the CharSequences are not empty ("") and not {@code null}.
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
    public static boolean isAllNotEmpty(final @Nullable CharSequence... values) {
        return ArrayUtils.isNotEmpty(values) && Stream.of(values).allMatch(StringUtils::isNotEmpty);
    }

// MARK: -

    /**
     * Checks if a CharSequence is empty (""), {@code null} or whitespace only.
     *
     * Whitespace is defined by {@link Character#isWhitespace(char)}.
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
    public static boolean isBlank(final @Nullable CharSequence value) {
        //noinspection ConstantConditions
        return (value == null) || (strip(value.toString()).length() < 1);
    }

    /**
     * Checks if all of the CharSequences are empty (""), {@code null} or whitespace only.
     *
     * Whitespace is defined by {@link Character#isWhitespace(char)}.
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
    public static boolean isAllBlank(final @Nullable CharSequence... values) {
        return ArrayUtils.isEmpty(values) || Stream.of(values).allMatch(StringUtils::isBlank);
    }

    /**
     * Checks if a CharSequence is not empty (""), not {@code null} and not whitespace only.
     *
     * Whitespace is defined by {@link Character#isWhitespace(char)}.
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
    public static boolean isNotBlank(final @Nullable CharSequence value) {
        return !isBlank(value);
    }

    /**
     * Checks if none of the CharSequences are empty (""), {@code null} or whitespace only.
     *
     * Whitespace is defined by {@link Character#isWhitespace(char)}.
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
    public static boolean isAllNotBlank(final @Nullable CharSequence... values) {
        return ArrayUtils.isNotEmpty(values) && Stream.of(values).allMatch(StringUtils::isNotBlank);
    }

// MARK: -

    /**
     * Strips any of a set of characters from the start and end of a String.
     * This is similar to {@link String#trim()} but allows the characters
     * to be stripped to be controlled.
     *
     * A {@code null} input String returns {@code null}. An empty string ("") input
     * returns the empty string.
     *
     * If the stripChars String is {@code null}, whitespace is stripped as defined
     * by {@link Character#isWhitespace(char)}.
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
     * @param value      The String to remove characters from, may be null
     * @param stripChars The characters to remove, null treated as whitespace
     * @return the stripped String, {@code null} if null String input
     */
    public static @Nullable String strip(final @Nullable String value, final @Nullable String stripChars) {
        return isEmpty(value) ? value : stripEnd(stripStart(value, stripChars), stripChars);
    }

    public static @Nullable String strip(final @Nullable String value) {
        return strip(value, null);
    }

    /**
     * Strips any of a set of characters from the start of a String.
     *
     * A {@code null} input String returns {@code null}. An empty string ("") input
     * returns the empty string.
     *
     * If the stripChars String is {@code null}, whitespace is stripped as defined
     * by {@link Character#isWhitespace(char)}.
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
     * @param value      The String to remove characters from, may be null
     * @param stripChars The characters to remove, null treated as whitespace
     * @return the stripped String, {@code null} if null String input
     */
    public static @Nullable String stripStart(final @Nullable String value, final @Nullable String stripChars) {
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
        return (start == 0) ? value : value.substring(start);
    }

    public static @Nullable String stripStart(final @Nullable String value) {
        return stripStart(value, null);
    }

    /**
     * Strips any of a set of characters from the end of a String.
     *
     * A {@code null} input String returns {@code null}. An empty string ("") input
     * returns the empty string.
     *
     * If the stripChars String is {@code null}, whitespace is stripped as defined
     * by {@link Character#isWhitespace(char)}.
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
     * @param value      The String to remove characters from, may be null
     * @param stripChars The set of characters to remove, null treated as whitespace
     * @return the stripped String, {@code null} if null String input
     */
    public static @Nullable String stripEnd(final @Nullable String value, final @Nullable String stripChars) {
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
        return value.substring(0, end);
    }

    public static @Nullable String stripEnd(final @Nullable String value) {
        return stripEnd(value, null);
    }

// MARK: -

    /**
     * Returns the given string if it is nonempty; {@code null} otherwise.
     */
    public static @Nullable String emptyToNull(final @Nullable String value) {
        return isEmpty(value) ? null : value;
    }

    /**
     * Returns the given string if it is non {@code null}; the empty string otherwise.
     */
    public static @NonNull String nullToEmpty(final @Nullable String value) {
        return (value == null) ? "" : value;
    }

// MARK: -

    /**
     * TODO
     */
    public static @NonNull String streamToString(final @NonNull InputStream input) throws IOException {
        return streamToString(input, Charsets.UTF_8);
    }

    /**
     * TODO
     */
    public static @NonNull String streamToString(
            final @NonNull InputStream input, final @NonNull Charset charset) throws IOException {

        Guard.notNull(input, "input is null");
        Guard.notNull(charset, "charset is null");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = input.read(buffer)) != EOF) {
            baos.write(buffer, 0, bytesRead);
        }

        // Done
        return new String(baos.toByteArray(), charset);
    }

// MARK: - Constants

    // Represents a failed index search.
    public static final int INDEX_NOT_FOUND = -1;

    // Represents a end of stream.
    public static final int EOF = -1;
}
