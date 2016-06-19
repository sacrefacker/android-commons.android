package com.roxiemobile.androidcommons.ui.routing.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.roxiemobile.androidcommons.data.CommonKeys.Prefix;
import com.roxiemobile.androidcommons.data.Resources.ResId;
import com.roxiemobile.androidcommons.util.ClassUtils;

import static com.roxiemobile.androidcommons.util.AssertUtils.assertNotNull;

@SuppressWarnings({"unchecked", "unused"})
public abstract class IntentBuilder<BuilderType extends IntentBuilder>
{
// MARK: - Methods

    public @NonNull BuilderType activity(Class<? extends Activity> clazz) {
        activity(clazz, null);
        return (BuilderType) this;
    }

    public @NonNull BuilderType activity(Class<? extends Activity> clazz, Bundle args) {
        mActivityClass = clazz;
        mActivityArgs = args;
        return (BuilderType) this;
    }

    public Class<?> activityClass() {
        return mActivityClass;
    }

    public Bundle activityArgs() {
        return mActivityArgs;
    }

    public @NonNull BuilderType fragment(Class<? extends Fragment> clazz) {
        fragment(clazz, null);
        return (BuilderType) this;
    }

    public @NonNull BuilderType fragment(Class<? extends Fragment> clazz, Bundle args) {
        mFragmentClass = clazz;
        mFragmentArgs = args;
        return (BuilderType) this;
    }

    public Class<?> fragmentClass() {
        return mFragmentClass;
    }

    public Bundle fragmentArgs() {
        return mFragmentArgs;
    }

    protected abstract @IdRes int activityContainerId();

// --

    public @NonNull Intent build(@NonNull Context context) {
        assertNotNull(context, "context == null");
        assertNotNull(mActivityClass, "activityClass == null");
        assertNotNull(mFragmentClass, "fragmentClass == null");
        return newIntent(context);
    }

// MARK: - Private Methods

    private @NonNull Intent newIntent(@NonNull Context context) {
        assertNotNull(context, "context == null");

        Class<?> activityClass = ClassUtils.getGeneratedClass(mActivityClass);
        assertNotNull(activityClass, "Class generated from " + mActivityClass.getCanonicalName() + " was not found.");

        Class<?> fragmentClass = ClassUtils.getGeneratedClass(mFragmentClass);
        assertNotNull(fragmentClass, "Class generated from " + mFragmentClass.getCanonicalName() + " was not found.");

        // Build Intent
        Intent intent = new Intent(context, activityClass);

        // Put extras
        intent.putExtra(EXTRA_ACTIVITY, newBundle(activityClass, mActivityArgs, activityContainerId()));
        intent.putExtra(EXTRA_FRAGMENT, newBundle(fragmentClass, mFragmentArgs, ResId.NO_ID));

        // Done
        return intent;
    }

    private @NonNull Bundle newBundle(@NonNull Class<?> clazz, Bundle args, @IdRes int layoutId) {
        Bundle bundle = new Bundle();

        // Put class name and arguments
        bundle.putSerializable(EXTRA_CLASS_NAME, clazz.getCanonicalName());
        bundle.putBundle(EXTRA_ARGUMENTS, args);

        // Put container layout Id
        if (layoutId != ResId.NO_ID) {
            bundle.putInt(EXTRA_CONTAINER_ID, layoutId);
        }

        // Done
        return bundle;
    }

// MARK: - Constants

    public static final String EXTRA_ACTIVITY = Prefix.EXTRA + "ACTIVITY";
    public static final String EXTRA_FRAGMENT = Prefix.EXTRA + "FRAGMENT";

    public static final String EXTRA_CLASS_NAME = Prefix.EXTRA + "CLASS_NAME";
    public static final String EXTRA_ARGUMENTS = Prefix.EXTRA + "ARGUMENTS";

    public static final String EXTRA_CONTAINER_ID = Prefix.EXTRA + "CONTAINER_ID";

// MARK: - Variables

    private Class<?> mActivityClass;
    private Bundle mActivityArgs;

    private Class<?> mFragmentClass;
    private Bundle mFragmentArgs;

}
