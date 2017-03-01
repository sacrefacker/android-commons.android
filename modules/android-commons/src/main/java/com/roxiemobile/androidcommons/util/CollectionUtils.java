package com.roxiemobile.androidcommons.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class CollectionUtils
{
// MARK: - Construction

    private CollectionUtils() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * Checks if given collection is {@code null} or has zero elements.
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        return (collection == null) || (collection.size() < 1);
    }

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return (map == null) || (map.size() < 1);
    }

    /**
     * Checks if given collection is non-null and has elements.
     */
    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }

// MARK: -

    /**
     * Returns the given collection if it is non-empty; {@code null} otherwise.
     */
    public static <T> Collection<T> emptyToNull(Collection<T> collection) {
        return isEmpty(collection) ? null : collection;
    }

    public static <T> List<T> emptyToNull(List<T> list) {
        return isEmpty(list) ? null : list;
    }

    public static <K, V> Map<K, V> emptyToNull(Map<K, V> map) {
        return isEmpty(map) ? null : map;
    }

    public static <T> Set<T> emptyToNull(Set<T> set) {
        return isEmpty(set) ? null : set;
    }

    /**
     * Returns the given collection if it is non {@code null}; the empty collection otherwise.
     */
    public static <T> Collection<T> nullToEmpty(Collection<T> collection) {
        return (collection != null) ? collection : Collections.emptyList();
    }

    public static <T> List<T> nullToEmpty(List<T> list) {
        return (list != null) ? list : Collections.emptyList();
    }

    public static <K, V> Map<K, V> nullToEmpty(Map<K, V> map) {
        return (map != null) ? map : Collections.emptyMap();
    }

    public static <T> Set<T> nullToEmpty(Set<T> set) {
        return (set != null) ? set : Collections.emptySet();
    }

// MARK: -

    /**
     * Returns an unmodifiable view of the specified collection.
     */
    public static <T> Collection<T> safeUnmodifiableCollection(Collection<? extends T> collection) {
        return Collections.unmodifiableCollection(collection != null ? collection : Collections.emptyList());
    }

    /**
     * Returns an unmodifiable view of the specified set.
     */
    public static <T> Set<T> safeUnmodifiableSet(Set<? extends T> set) {
        return Collections.unmodifiableSet(set != null ? set : Collections.emptySet());
    }

    /**
     * Returns an unmodifiable view of the specified list.
     */
    public static <T> List<T> safeUnmodifiableList(List<? extends T> list) {
        return Collections.unmodifiableList(list != null ? list : Collections.emptyList());
    }

    /**
     * Returns an unmodifiable view of the specified map.
     */
    public static <K, V> Map<K, V> safeUnmodifiableMap(Map<? extends K, ? extends V> map) {
        return Collections.unmodifiableMap(map != null ? map : Collections.emptyMap());
    }

// MARK: -

    /**
     * Returns a ArrayList containing the elements of the specified array.
     */
    public static <T> ArrayList<T> toArrayList(T[] array) {
        ArrayList<T> arrayList = new ArrayList<>();

        if (ArrayUtils.isNotEmpty(array)) {
            Collections.addAll(arrayList, array);
        }
        return arrayList;
    }

    /**
     * Returns a ArrayList containing the elements of the specified list.
     */
    public static <T> ArrayList<T> toArrayList(Collection<T> collection) {
        ArrayList<T> arrayList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(collection)) {
            arrayList.addAll(collection);
        }
        return arrayList;
    }
}
