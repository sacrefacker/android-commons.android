package com.roxiemobile.androidcommons.util;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.roxiemobile.androidcommons.logging.Logger;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.roxiemobile.androidcommons.diagnostics.Require.requireNotNull;

public final class BundleUtils
{
// MARK: - Construction

    private BundleUtils() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * Checks if given Bundle is {@code null} or has zero elements.
     */
    public static boolean isEmpty(Bundle bundle) {
        return (bundle == null) || bundle.isEmpty();
    }

    /**
     * Checks if given Bundle is non {@code null} and has elements.
     */
    public static boolean isNotEmpty(Bundle bundle) {
        return !isEmpty(bundle);
    }

    /**
     * Returns {@code true} if the given key is contained in the mapping of this Bundle.
     */
    public static boolean containsKey(Bundle bundle, String key) {
        return (bundle != null) && bundle.containsKey(key);
    }

// MARK: - Methods

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
     * Inserts a Object array value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Value may be {@code null}.
     */
    public static <T extends Serializable> void putSerializableList(@NonNull Bundle bundle, String key, T[] value) {
        requireNotNull(bundle, "bundle is null");

        if (value != null) {
            putSerializableArrayList(bundle, key, Arrays.asList(value));
        }
        else {
            bundle.putSerializable(key, null);
        }
    }

    /**
     * Returns the Object array value associated with the given key,
     * or {@code null} if no mapping of the desired type exists for the given key
     * or a {@code null} value is explicitly associated with the key.
     */
    public static <T extends Serializable> T[] getSerializableList(Bundle bundle, String key, T[] defaultValue) {
        T[] result = defaultValue;

        if (containsKey(bundle, key)) {
            Serializable value = bundle.getSerializable(key);

            try {
                if (value instanceof ArrayList) {
                    //noinspection unchecked
                    ArrayList<T> array = (ArrayList<T>) value;
                    if (array.size() > 0) {
                        //noinspection unchecked
                        result = array.toArray((T[]) Array.newInstance(array.get(0).getClass(), 0));
                    }
                }
                else {
                    //noinspection unchecked
                    result = (T[]) value;
                }
            }
            catch (ClassCastException e) {
                Logger.w(TAG, e);
            }
        }
        return result;
    }

    public static <T extends Serializable> T[] getSerializableList(Bundle bundle, String key) {
        return getSerializableList(bundle, key, null);
    }

    /**
     * Inserts an List<T> value into the mapping of this Bundle, replacing
     * any existing value for the given key.  Key may be null.
     */
    public static <T extends Serializable> void putSerializableArrayList(@NonNull Bundle bundle, String key, List<T> value) {
        requireNotNull(bundle, "bundle is null");

        if (value != null) {
            bundle.putSerializable(key, new ArrayList<>(value));
        }
        else {
            bundle.putSerializable(key, null);
        }
    }

    /**
     * Returns the unmodifiable List<T> value associated with the given key,
     * or {@code null} if no mapping of the desired type exists for the given key
     * or a {@code null} value is explicitly associated with the key.
     */
    public static <T> List<T> getSerializableArrayList(Bundle bundle, String key, List<T> defaultValue) {
        List<T> result = defaultValue;

        try {
            //noinspection unchecked
            result = CollectionUtils.safeUnmodifiableList((List<T>) bundle.getSerializable(key));
        }
        catch (ClassCastException e) {
            Logger.w(TAG, e);
        }
        return result;
    }

    public static <T> List<T> getSerializableArrayList(Bundle bundle, String key) {
        return getSerializableArrayList(bundle, key, null);
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
                Logger.w(TAG, ex);
            }
        }

        return object;
    }

// MARK: - Constants

    private static final String TAG = BundleUtils.class.getSimpleName();
}
