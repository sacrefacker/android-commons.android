package com.roxiemobile.androidcommons.util;

import android.os.Bundle;

public final class BundleUtils
{
// MARK: - Construction

    private BundleUtils() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * Returns the value associated with the given key, or {@code false} if
     * no mapping of the desired type exists for the given key.
     */
    public static boolean getBoolean(Bundle bundle, String key) {
        return getBoolean(bundle, key, false);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     */
    public static boolean getBoolean(Bundle bundle, String key, boolean defaultValue) {
        return containsKey(bundle, key) ? bundle.getBoolean(key) : defaultValue;
    }

    /**
     * Returns the value associated with the given key, or 0 if
     * no mapping of the desired type exists for the given key.
     */
    public static int getInt(Bundle bundle, String key) {
        return getInt(bundle, key, 0);
    }

    /**
     * Returns the value associated with the given key, or defaultValue if
     * no mapping of the desired type exists for the given key.
     */
    public static int getInt(Bundle bundle, String key, int defaultValue) {
        return containsKey(bundle, key) ? bundle.getInt(key) : defaultValue;
    }

    /**
     * Returns the entry with the given key as an object, or {@code null} if
     * no mapping of the desired type exists for the given key.
     */
    public static <T> T get(Bundle bundle, String key) {
        return get(bundle, key, null);
    }

    /**
     * Returns the entry with the given key as an object, or defaultValue if
     * no mapping of the desired type exists for the given key.
     */
    public static <T> T get(Bundle bundle, String key, T defaultValue) {
        T object = defaultValue;

        if (containsKey(bundle, key)) {
            try {
                //noinspection unchecked
                object = (T) bundle.get(key);
            }
            catch (ClassCastException ex) {
                LogUtils.w(TAG, ex);
            }
        }

        return object;
    }

    /**
     * Returns {@code true} if the Bundle is {@code null} or empty, {@code false} otherwise.
     */
    public static boolean isEmpty(Bundle bundle) {
        return (bundle == null) || bundle.isEmpty();
    }

    /**
     * Returns {@code true} if the given key is contained in the mapping of this Bundle.
     */
    public static boolean containsKey(Bundle bundle, String key) {
        return (bundle != null) && bundle.containsKey(key);
    }

    /**
     * Returns the given Bundle if it is non-empty; {@code null} otherwise.
     */
    public static Bundle emptyToNull(Bundle bundle) {
        return isEmpty(bundle) ? null : bundle;
    }

    /**
     * Returns the given Bundle if it is non-null; the empty Bundle otherwise.
     */
    public static Bundle nullToEmpty(Bundle bundle) {
        return (bundle == null) ? new Bundle() : bundle;
    }

// MARK: - Constants

    private static final String TAG = BundleUtils.class.getSimpleName();

}
