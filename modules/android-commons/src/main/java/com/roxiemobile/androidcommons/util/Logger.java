package com.roxiemobile.androidcommons.util;

import android.annotation.SuppressLint;
import android.util.Log;

@SuppressLint("LogTagMismatch")
public final class Logger
{
// MARK: - Construction

    private Logger() {
        // Do nothing
    }

// MARK: - Properties

    public static void setLogLevel(int level) {
        if (level >= Logger.SUPPRESS && level <= Log.ASSERT) {
            sLogLevel = level;
        }
    }

    public static int getLogLevel() {
        return sLogLevel;
    }

// MARK: - Methods

    public static void v(String tag, String msg) {
        if (isLoggable(Log.VERBOSE) && tag != null && msg != null) {
            Log.v(cropTag(tag), msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isLoggable(Log.DEBUG) && tag != null && msg != null) {
            Log.d(cropTag(tag), msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isLoggable(Log.INFO) && tag != null && msg != null) {
            Log.i(cropTag(tag), msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isLoggable(Log.WARN) && tag != null && msg != null) {
            Log.w(cropTag(tag), msg);
        }
    }

    public static void w(String tag, String msg, Throwable ex) {
        if (isLoggable(Log.WARN) && tag != null && msg != null) {
            Log.w(cropTag(tag), msg, ex);
        }
    }

    public static void w(String tag, Throwable ex) {
        if (isLoggable(Log.WARN) && tag != null) {
            Log.w(cropTag(tag), Log.getStackTraceString(ex));
        }
    }

    public static void e(String tag, String msg) {
        if (isLoggable(Log.ERROR) && tag != null && msg != null) {
            Log.e(cropTag(tag), msg);
        }
    }

    public static void e(String tag, String msg, Throwable ex) {
        if (isLoggable(Log.ERROR) && tag != null && msg != null) {
            Log.e(cropTag(tag), msg, ex);
        }
    }

    public static void e(String tag, Throwable ex) {
        if (isLoggable(Log.ERROR) && tag != null) {
            Log.e(cropTag(tag), Log.getStackTraceString(ex));
        }
    }

    public static boolean isLoggable(int level) {
        return (level >= getLogLevel()) && (getLogLevel() != SUPPRESS);
    }

// MARK: - Private Methods

    /**
     * This is an Android side limitation:
     *   IllegalArgumentException is thrown if the tag.length() > 23
     */
    private static String cropTag(String tag) {
        return (tag.length() > 23) ? tag.substring(0, 23) : tag;
    }

// MARK: - Constants

    /**
     * Priority constant SUPPRESS will turn off all logging.
     */
    public static final int SUPPRESS = -1;

// MARK: - Variables

    private static int sLogLevel = Log.INFO;

}
