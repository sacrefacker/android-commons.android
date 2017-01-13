package com.roxiemobile.androidcommons.util;

public final class ArrayUtils
{
// MARK: - Construction

    private ArrayUtils() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * Checks if given array is null or has zero elements.
     */
    public static <T> boolean isNullOrEmpty(T[] array) {
        return (array == null) || (array.length < 1);
    }

    public static boolean isNullOrEmpty(byte[] array) {
        return (array == null) || (array.length < 1);
    }

    public static boolean isNullOrEmpty(char[] array) {
        return (array == null) || (array.length < 1);
    }

    public static boolean isNullOrEmpty(short[] array) {
        return (array == null) || (array.length < 1);
    }

    public static boolean isNullOrEmpty(int[] array) {
        return (array == null) || (array.length < 1);
    }

    public static boolean isNullOrEmpty(long[] array) {
        return (array == null) || (array.length < 1);
    }

    public static boolean isNullOrEmpty(float[] array) {
        return (array == null) || (array.length < 1);
    }

    public static boolean isNullOrEmpty(double[] array) {
        return (array == null) || (array.length < 1);
    }

    public static boolean isNullOrEmpty(boolean[] array) {
        return (array == null) || (array.length < 1);
    }

    /**
     * Returns the given array if it is nonempty; {@code null} otherwise.
     */
    public static <T> T[] emptyToNull(T[] array) {
        return isNullOrEmpty(array) ? null : array;
    }

    public static byte[] emptyToNull(byte[] array) {
        return isNullOrEmpty(array) ? null : array;
    }

    public static char[] emptyToNull(char[] array) {
        return isNullOrEmpty(array) ? null : array;
    }

    public static short[] emptyToNull(short[] array) {
        return isNullOrEmpty(array) ? null : array;
    }

    public static int[] emptyToNull(int[] array) {
        return isNullOrEmpty(array) ? null : array;
    }

    public static long[] emptyToNull(long[] array) {
        return isNullOrEmpty(array) ? null : array;
    }

    public static float[] emptyToNull(float[] array) {
        return isNullOrEmpty(array) ? null : array;
    }

    public static double[] emptyToNull(double[] array) {
        return isNullOrEmpty(array) ? null : array;
    }

    public static boolean[] emptyToNull(boolean[] array) {
        return isNullOrEmpty(array) ? null : array;
    }

}
