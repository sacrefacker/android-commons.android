package com.roxiemobile.androidcommons.util;

import java.util.Collection;
import java.util.Map;

public final class CollectionUtils
{
// MARK: - Construction

    private CollectionUtils() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * Checks if given collection is null or has zero elements.
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.size() == 0;
    }

}
