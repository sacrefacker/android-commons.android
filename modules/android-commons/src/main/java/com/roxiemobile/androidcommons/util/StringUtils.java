package com.roxiemobile.androidcommons.util;

import android.support.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotNull;

public final class StringUtils
{
// MARK: - Construction

    private StringUtils() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * Returns true if the string is null or 0-length.
     *
     * @return {@code true} if str is {@code null} or zero length.
     */
    public static boolean isNullOrEmpty(CharSequence str) {
        return str == null || str.length() < 1;
    }

    /**
     * Returns true if the all strings is null or 0-length.
     *
     * @return {@code true} if str is {@code null} or zero length.
     */
    public static boolean isNullOrEmpty(@NonNull CharSequence... values) {
        expectNotNull(values, "values is null");
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
     * Returns true if the string is non-null and non-empty.
     *
     * @return {@code true} if string are non-null and non-empty, {@code false} otherwise
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isNullOrEmpty(str);
    }

    /**
     * Returns true if the all strings is non-null and non-empty.
     *
     * @return {@code true} if all strings are non-null and non-empty, {@code false} otherwise
     */
    public static boolean isNotEmpty(@NonNull CharSequence... values) {
        expectNotNull(values, "values is null");
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
     * TODO
     */
    public static boolean isNullOrWhiteSpace(CharSequence str) {
        return str == null || trimSequence(str).length() < 1;
    }

    /**
     * TODO
     */
    public static boolean isNullOrWhiteSpace(@NonNull CharSequence... values) {
        expectNotNull(values, "values is null");
        boolean result = true;

        if (ArrayUtils.isNullOrEmpty(values)) {
            return true;
        }

        for (int idx = 0, count = values.length; result && (idx < count); ++idx) {
            result = isNullOrWhiteSpace(values[idx]);
        }
        return result;
    }

    /**
     * TODO
     */
    public static boolean isNotWhiteSpace(CharSequence str) {
        return !isNullOrWhiteSpace(str);
    }

    /**
     * TODO
     */
    public static boolean isNotWhiteSpace(@NonNull CharSequence... values) {
        expectNotNull(values, "values is null");
        boolean result = true;

        if (ArrayUtils.isNullOrEmpty(values)) {
            return false;
        }

        for (int idx = 0, count = values.length; result && (idx < count); ++idx) {
            result = isNotWhiteSpace(values[idx]);
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
     * Returns the given string if it is non-null; the empty string otherwise.
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
