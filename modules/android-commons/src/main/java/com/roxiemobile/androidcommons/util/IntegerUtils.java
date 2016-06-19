package com.roxiemobile.androidcommons.util;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class IntegerUtils
{
// MARK: - Construction

    private IntegerUtils() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * Returns an array containing each value of {@code collection}, converted to
     * a {@code int} value in the manner of {@link Number#intValue}.
     *
     * <p>Elements are copied from the argument collection as if by {@code
     * collection.toArray()}.  Calling this method is as thread-safe as calling
     * that method.
     *
     * @param collection a collection of {@code Number} instances
     * @return an array containing the same values as {@code collection}, in the
     *     same order, converted to primitives
     * @throws NullPointerException if {@code collection} or any of its elements
     *     is null
     * @since 1.0 (parameter was {@code Collection<Integer>} before 12.0)
     */
    public static int[] toArray(Collection<? extends Number> collection)
    {
        Object[] boxedArray = collection.toArray();
        int count = boxedArray.length;

        int[] outArray = new int[count];
        for (int idx = 0; idx < count; idx++) {
            outArray[idx] = ((Number) boxedArray[idx]).intValue();
        }

        return outArray;
    }

    /**
     * Returns a fixed-size list backed by the specified array. The list supports
     * {@link List#set(int, Object)}, but any attempt to set a value to {@code null}
     * will result in a {@link NullPointerException}.
     *
     * <p>The returned list maintains the values, but not the identities, of
     * {@code Integer} objects written to or read from it.  For example, whether
     * {@code list.get(0) == list.get(0)} is true for the returned list is
     * unspecified.
     *
     * @param backingArray the array to back the list
     * @return a list view of the array
     */
    public static List<Integer> asList(final int... backingArray)
    {
        if (backingArray.length == 0) {
            return Collections.emptyList();
        }

        return new AbstractList<Integer>() {
            public Integer get(int i) { return backingArray[i]; }
            public int size() { return backingArray.length; }
        };
    }

}
