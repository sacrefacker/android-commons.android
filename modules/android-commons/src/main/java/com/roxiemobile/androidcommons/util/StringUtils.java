package com.roxiemobile.androidcommons.util;

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

    public static String streamToString(InputStream is) throws IOException {
        return streamToString(is, "UTF-8");
    }

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

    /**
     * Returns true if the string is null or 0-length.
     *
     * @return {@code true} if str is {@code null} or zero length.
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * TODO
     *
     * @return {@code true} if all arguments are non-null and non-empty, {@code false} otherwise
     */
    public static boolean isNotEmpty(CharSequence... values) {
        boolean result = true;

        for (int idx = 0, count = values.length; result && (idx < count); ++idx) {
            result = !isEmpty(values[idx]);
        }
        return result;
    }

    /**
     * Returns the given string if it is nonempty; {@code null} otherwise.
     */
    public static String emptyToNull(String str) {
        return isEmpty(str) ? null : str;
    }

    /**
     * Returns the given string if it is non-null; the empty string otherwise.
     */
    public static String nullToEmpty(String str) {
        return (str == null) ? "" : str;
    }

}
