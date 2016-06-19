package com.roxiemobile.androidcommons.util;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;

import java.util.HashMap;

import static com.roxiemobile.androidcommons.util.AssertUtils.assertNotNull;
import static com.roxiemobile.androidcommons.util.AssertUtils.assertTrue;

public final class ClassUtils
{
// MARK: - Construction

    private ClassUtils() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * Returns a {@code Class} object which represents the Activity's or Fragment's class which
     * was generated with AndroidAnnotations or {@code null} if such class was not found.
     */
    public static Class<?> getGeneratedClass(Class<?> clazz) {
        Class<?> foundClass = null;
        try {
            if (clazz != null) {
                foundClass = loadGeneratedClass(clazz);
            }
        }
        catch (ClassNotFoundException ex) {
            LogUtils.w(TAG, ex);
        }
        return foundClass;
    }

    /**
     * Returns a {@code Class} object which represents the Activity's or Fragment's class which
     * was generated with AndroidAnnotations or passed class if generated class was not found.
     */
    public static Class<?> nullSafeGeneratedClass(@NonNull Class<?> clazz) {
        Class<?> foundClass = getGeneratedClass(clazz);
        return (foundClass == null) ? clazz : foundClass;
    }

// MARK: - Private Methods

    private static Class<?> loadGeneratedClass(@NonNull Class<?> clazz) throws ClassNotFoundException {
        assertNotNull(clazz, "clazz == null");
        assertTrue(Activity.class.isAssignableFrom(clazz) || Fragment.class.isAssignableFrom(clazz),
                "Only classes inherited from Activity or Fragment are allowed.");

        Class<?> foundClass = sClassMap.get(clazz);
        if (foundClass == null) {

            String cname = clazz.getCanonicalName() + "_";
            foundClass = Class.forName(cname, false, clazz.getClassLoader());

            // Check if found class was generated with AndroidAnnotations
            if (!clazz.isAssignableFrom(foundClass) || !HasViews.class.isAssignableFrom(foundClass)
                    || !OnViewChangedListener.class.isAssignableFrom(foundClass)) {
                throw new ClassNotFoundException("Found class was not generated with AndroidAnnotations: " + cname);
            }

            // Put this found class name to cache
            sClassMap.put(clazz, foundClass);
        }

        // Done
        return foundClass;
    }

// MARK: - Constants

    public static final String TAG = ClassUtils.class.getSimpleName();

// MARK: - Variables

    // Cache of previously looked up generated classes
    private static final HashMap<Class<?>, Class<?>> sClassMap = new HashMap<>();

}
