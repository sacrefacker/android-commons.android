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
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(char[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(short[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(float[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(double[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(boolean[] array) {
        return array == null || array.length == 0;
    }

    /**
     * Returns the given array if it is nonempty; {@code null} otherwise.
     */
    public static <T> T[] emptyToNull(T[] array) {
        return isEmpty(array) ? null : array;
    }

    public static byte[] emptyToNull(byte[] array) {
        return isEmpty(array) ? null : array;
    }

    public static char[] emptyToNull(char[] array) {
        return isEmpty(array) ? null : array;
    }

    public static short[] emptyToNull(short[] array) {
        return isEmpty(array) ? null : array;
    }

    public static int[] emptyToNull(int[] array) {
        return isEmpty(array) ? null : array;
    }

    public static long[] emptyToNull(long[] array) {
        return isEmpty(array) ? null : array;
    }

    public static float[] emptyToNull(float[] array) {
        return isEmpty(array) ? null : array;
    }

    public static double[] emptyToNull(double[] array) {
        return isEmpty(array) ? null : array;
    }

    public static boolean[] emptyToNull(boolean[] array) {
        return isEmpty(array) ? null : array;
    }

}
