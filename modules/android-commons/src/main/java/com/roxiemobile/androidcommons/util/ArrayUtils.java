package com.roxiemobile.androidcommons.util;

import android.support.annotation.NonNull;

import java.lang.reflect.Array;

import static com.roxiemobile.androidcommons.diagnostics.Require.requireNotNull;

public final class ArrayUtils
{
// MARK: - Construction

    private ArrayUtils() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * Checks if given array is {@code null} or has zero elements.
     *
     * @param array The array to test
     * @return {@code true} if the array is empty or {@code null}
     */
    public static <T> boolean isEmpty(T[] array) {
        return (array == null) || (array.length < 1);
    }

    public static boolean isEmpty(byte[] array) {
        return (array == null) || (array.length < 1);
    }

    public static boolean isEmpty(char[] array) {
        return (array == null) || (array.length < 1);
    }

    public static boolean isEmpty(short[] array) {
        return (array == null) || (array.length < 1);
    }

    public static boolean isEmpty(int[] array) {
        return (array == null) || (array.length < 1);
    }

    public static boolean isEmpty(long[] array) {
        return (array == null) || (array.length < 1);
    }

    public static boolean isEmpty(float[] array) {
        return (array == null) || (array.length < 1);
    }

    public static boolean isEmpty(double[] array) {
        return (array == null) || (array.length < 1);
    }

    public static boolean isEmpty(boolean[] array) {
        return (array == null) || (array.length < 1);
    }

// MARK: -

    /**
     * Checks if given array is non {@code null} and has elements.
     *
     * @param array The array to test
     * @return {@code true} if the array is not empty and not {@code null}
     */
    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(byte[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(char[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(short[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(int[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(long[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(float[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(double[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(boolean[] array) {
        return !isEmpty(array);
    }

// MARK: -

    /**
     * Returns the given array if it is non-empty; {@code null} otherwise.
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

// MARK: -

    /**
     * Returns the given array if it is non {@code null}; the empty array otherwise.
     */
    public static <T> T[] nullToEmpty(T[] array, @NonNull Class<T[]> classOfT) {
        requireNotNull(classOfT, "classOfT is null");
        return (array != null) ? array : classOfT.cast(Array.newInstance(classOfT.getComponentType(), 0));
    }

    public static byte[] nullToEmpty(byte[] array) {
        return (array != null) ? array : EMPTY_BYTE_ARRAY;
    }

    public static char[] nullToEmpty(char[] array) {
        return (array != null) ? array : EMPTY_CHAR_ARRAY;
    }

    public static short[] nullToEmpty(short[] array) {
        return (array != null) ? array : EMPTY_SHORT_ARRAY;
    }

    public static int[] nullToEmpty(int[] array) {
        return (array != null) ? array : EMPTY_INT_ARRAY;
    }

    public static long[] nullToEmpty(long[] array) {
        return (array != null) ? array : EMPTY_LONG_ARRAY;
    }

    public static float[] nullToEmpty(float[] array) {
        return (array != null) ? array : EMPTY_FLOAT_ARRAY;
    }

    public static double[] nullToEmpty(double[] array) {
        return (array != null) ? array : EMPTY_DOUBLE_ARRAY;
    }

    public static boolean[] nullToEmpty(boolean[] array) {
        return (array != null) ? array : EMPTY_BOOLEAN_ARRAY;
    }

// MARK: -

    /**
     * Create a type-safe generic array.
     *
     * This method makes only sense to provide arguments of the same type so that the compiler can deduce
     * the type of the array itself. While it is possible to select the type explicitly like in
     * <code>Number[] array = ArrayUtils.&lt;Number&gt;toArray(new Integer(42), new Double(Math.PI))</code>,
     * there is no real advantage when compared to
     * <code>new Number[] {new Integer(42), new Double(Math.PI)}</code>.
     *
     * @param <T>   The array's element type
     * @param items The varargs array items, null allowed
     * @return The array, not null unless a null array is passed in
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(final T... items) {
        return items;
    }

// MARK: - Constants

    /**
     * An empty immutable {@code long} array.
     */
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    /**
     * An empty immutable {@code int} array.
     */
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    /**
     * An empty immutable {@code short} array.
     */
    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
    /**
     * An empty immutable {@code byte} array.
     */
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    /**
     * An empty immutable {@code double} array.
     */
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    /**
     * An empty immutable {@code float} array.
     */
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    /**
     * An empty immutable {@code boolean} array.
     */
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    /**
     * An empty immutable {@code char} array.
     */
    public static final char[] EMPTY_CHAR_ARRAY = new char[0];
}
