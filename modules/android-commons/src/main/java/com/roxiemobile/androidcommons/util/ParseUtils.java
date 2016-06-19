package com.roxiemobile.androidcommons.util;

public final class ParseUtils
{
// MARK: - Construction

    private ParseUtils() {
        // Do nothing
    }

// MARK: - Methods

    public static int parseInt(String value, int defaultValue) {
        int result = defaultValue;

        try {
            result = Integer.parseInt(value);
        }
        catch (NumberFormatException ex) {
            // Do nothing
        }

        return result;
    }

    public static boolean tryParseInt(String value) {
        try {
            //noinspection ResultOfMethodCallIgnored
            Integer.parseInt(value);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }

    public static long parseLong(String value, long defaultValue) {
        long result = defaultValue;

        try {
            result = Long.parseLong(value);
        }
        catch (NumberFormatException ex) {
            // Do nothing
        }

        return result;
    }

    public static boolean tryParseLong(String value) {
        try {
            //noinspection ResultOfMethodCallIgnored
            Long.parseLong(value);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }

    public static float parseFloat(String value, float defaultValue) {
        float result = defaultValue;

        try {
            result = Float.parseFloat(value);
        }
        catch (NumberFormatException ex) {
            // Do nothing
        }

        return result;
    }

    public static boolean tryParseFloat(String value) {
        try {
            //noinspection ResultOfMethodCallIgnored
            Float.parseFloat(value);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }

    public static double parseDouble(String value, double defaultValue) {
        double result = defaultValue;

        try {
            result = Double.parseDouble(value);
        }
        catch (NumberFormatException ex) {
            // Do nothing
        }

        return result;
    }

    public static boolean tryParseDouble(String value) {
        try {
            //noinspection ResultOfMethodCallIgnored
            Double.parseDouble(value);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }

}
