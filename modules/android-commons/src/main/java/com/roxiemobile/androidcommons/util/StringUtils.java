package com.roxiemobile.androidcommons.util;

import android.support.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.roxiemobile.androidcommons.diagnostics.Require.requireNotNull;

public final class StringUtils
{
// MARK: - Construction

    private StringUtils() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * Checks if a CharSequence is empty ("") or {@code null}.
     */
    public static boolean isNullOrEmpty(CharSequence str) {
        return str == null || str.length() < 1;
    }

    /**
     * Checks if all CharSequences is empty ("") or {@code null}.
     */
    public static boolean isNullOrEmpty(@NonNull CharSequence... values) {
        requireNotNull(values, "values is null");
        boolean result = true;

        if (ArrayUtils.isNullOrEmpty(values)) {
            return true;
        }

        for (int idx = 0, count = values.length; result && (idx < count); ++idx) {
            result = isNullOrEmpty(values[idx]);
        }
        return result;
    }

    /**
     * Checks if a CharSequence is not empty ("") and not {@code null}.
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isNullOrEmpty(str);
    }

    /**
     * Checks if none of the CharSequences are not empty ("") and not {@code null}.
     */
    public static boolean isNotEmpty(@NonNull CharSequence... values) {
        requireNotNull(values, "values is null");
        boolean result = true;

        if (ArrayUtils.isNullOrEmpty(values)) {
            return false;
        }

        for (int idx = 0, count = values.length; result && (idx < count); ++idx) {
            result = isNotEmpty(values[idx]);
        }
        return result;
    }

// MARK: - Methods

    /**
     * Checks if a CharSequence is whitespace, empty ("") or {@code null}.
     */
    public static boolean isNullOrWhitespace(CharSequence str) {
        return str == null || trimSequence(str).length() < 1;
    }

    /**
     * Checks if all CharSequences is whitespace, empty ("") or {@code null}.
     */
    public static boolean isNullOrWhitespace(@NonNull CharSequence... values) {
        requireNotNull(values, "values is null");
        boolean result = true;

        if (ArrayUtils.isNullOrEmpty(values)) {
            return true;
        }

        for (int idx = 0, count = values.length; result && (idx < count); ++idx) {
            result = isNullOrWhitespace(values[idx]);
        }
        return result;
    }

    /**
     * Checks if a CharSequence is not empty (""), not {@code null} and not whitespace only.
     */
    public static boolean isNotBlank(CharSequence str) {
        return !isNullOrWhitespace(str);
    }

    /**
     * Checks if none of the CharSequences are not empty (""), not {@code null} and not whitespace only.
     */
    public static boolean isNotBlank(@NonNull CharSequence... values) {
        requireNotNull(values, "values is null");
        boolean result = true;

        if (ArrayUtils.isNullOrEmpty(values)) {
            return false;
        }

        for (int idx = 0, count = values.length; result && (idx < count); ++idx) {
            result = isNotBlank(values[idx]);
        }
        return result;
    }

// MARK: - Methods

    /**
     * Returns the given string if it is nonempty; {@code null} otherwise.
     */
    public static String emptyToNull(String str) {
        return isNullOrEmpty(str) ? null : str;
    }

    /**
     * Returns the given string if it is non {@code null}; the empty string otherwise.
     */
    public static String nullToEmpty(String str) {
        return (str == null) ? "" : str;
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
        int length = 0;

        while ((length = is.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }

        // Done
        return new String(baos.toByteArray(), encoding);
    }

// MARK: - Private Methods

    /**
     * TODO
     */
    private static CharSequence trimSequence(CharSequence str) {
        int len = str.length();
        int st = 0;

        while ((st < len) && (str.charAt(st) <= ' ')) {
            st++;
        }
        while ((st < len) && (str.charAt(len - 1) <= ' ')) {
            len--;
        }
        return ((st > 0) || (len < str.length())) ? str.subSequence(st, len) : str;
    }
}
