package com.roxiemobile.androidcommons.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;

import static com.roxiemobile.androidcommons.util.AssertUtils.assertNotNull;
import static com.roxiemobile.androidcommons.util.AssertUtils.assertTrue;

public final class FragmentUtils
{
// MARK: - Construction

    private FragmentUtils() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * Create a new instance of a Fragment with the given class object.  This is
     * the same as calling its empty constructor.
     *
     * @param clazz The class of the fragment to instantiate.
     * @param args  Bundle of arguments to supply to the fragment, which it can retrieve
     *              with {@link Fragment#getArguments()}. May be null.
     * @return Returns a new fragment instance.
     */
    public static Fragment instantiate(@NonNull Class<?> clazz, Bundle args) {
        assertNotNull(clazz, "clazz == null");
        assertTrue(Fragment.class.isAssignableFrom(clazz), "clazz is not assignable from Fragment");

        Fragment fragment = null;
        try {
            // Create fragment with arguments
            fragment = (Fragment) clazz.newInstance();
            if (args != null) {
                args.setClassLoader(fragment.getClass().getClassLoader());
                fragment.setArguments(args);
            }
        }
        catch (Exception ex) {
            LogUtils.e(TAG, ex);

            // Terminate application with runtime exception
            if (LogUtils.isLoggable(Log.DEBUG)) {
                throw new Fragment.InstantiationException("Unable to instantiate fragment "
                        + clazz.getCanonicalName() + ": make sure class name exists, is public, "
                        + "and has an empty constructor that is public", ex);
            }
        }

        // Done
        return fragment;
    }

    public static Fragment instantiate(@NonNull Class<?> clazz) {
        return instantiate(clazz, null);
    }

    /**
     * TODO
     */
    public static Bundle getArguments(@NonNull Fragment fragment, Bundle defaultValue) {
        assertNotNull(fragment, "fragment == null");

        Bundle extras = fragment.getArguments();
        return (extras != null) ? extras : defaultValue;
    }

    /**
     * Call {@link android.app.Activity#startActivity(Intent)} on the fragment's
     * containing Activity.
     */
    public static boolean startActivity(@NonNull Fragment fragment, @NonNull Intent intent) {
        assertNotNull(fragment, "fragment == null");
        assertNotNull(intent, "intent == null");

        boolean result = false;
        try {
            // Launch a new activity
            fragment.startActivity(intent);
            result = true;
        }
        catch (Exception ex) {
            LogUtils.e(TAG, ex);

            // Terminate application with runtime exception
            if (LogUtils.isLoggable(Log.DEBUG)) {
                throw new RuntimeException(ex);
            }
        }

        // Done
        return result;
    }

    /**
     * TODO
     */
    public static boolean startActivityForResult(@NonNull Fragment fragment, @NonNull Intent intent, int requestCode) {
        assertNotNull(fragment, "fragment == null");
        assertNotNull(intent, "intent == null");

        boolean result = false;
        try {
            // Launch a new activity
            fragment.startActivityForResult(intent, requestCode);
            result = true;
        }
        catch (Exception ex) {
            LogUtils.e(TAG, ex);

            // Terminate application with runtime exception
            if (LogUtils.isLoggable(Log.DEBUG)) {
                throw new RuntimeException(ex);
            }
        }

        // Done
        return result;
    }

    /**
     * TODO: Move this method to another class
     */
    public static String newTag(Object object) {
        return TextUtils.join(":", new String[]{
                "urn:tag",
                Integer.toHexString(System.identityHashCode(object != null ? object.getClass() : null)),
                Integer.toHexString(System.identityHashCode(object)),
                Long.toHexString(System.nanoTime())
        });
    }

// MARK: - Constants

    private static final String TAG = FragmentUtils.class.getSimpleName();

}
