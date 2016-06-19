package com.roxiemobile.androidcommons.ui.activity.base;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStub;

import com.roxiemobile.androidcommons.R;
import com.roxiemobile.androidcommons.data.CommonKeys.Prefix;
import com.roxiemobile.androidcommons.data.Resources.ResId;
import com.roxiemobile.androidcommons.ui.routing.base.IntentBuilder;
import com.roxiemobile.androidcommons.util.ActivityUtils;
import com.roxiemobile.androidcommons.util.BundleUtils;
import com.roxiemobile.androidcommons.util.IntentUtils;
import com.roxiemobile.androidcommons.util.StringUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;

import static com.roxiemobile.androidcommons.util.AssertUtils.assertNotNull;

@EActivity
public abstract class BaseFragmentActivity extends AppCompatActivity
{
// MARK: - Properties

    public final @NonNull Bundle getExtras() {
        return mMostRecentExtras;
    }

    public final boolean hasInstanceState() {
        return mInstanceState != null;
    }

// MARK: - Methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        onBeforeCreate(savedInstanceState);

        // Parent processing
        super.onCreate(savedInstanceState);
    }

    protected void onBeforeCreate(Bundle savedInstanceState) {
        loadInstanceState(savedInstanceState);

        // Init instance variables
        if (!hasInstanceState()) {
            mMostRecentExtras = IntentUtils.getExtras(getIntent(), new Bundle());
        }

        // Init instance
        onInit(getIntent(), getExtras(), savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        loadInstanceState(null);

        // Init instance variables
        if (!hasInstanceState()) {
            mMostRecentExtras = IntentUtils.getExtras(intent, new Bundle());
        }

        // Init instance
        onInit(intent, getExtras(), mInstanceState);

        // Parent processing
        super.onNewIntent(intent);

        // Update UI
        onInitInterface(intent, getExtras(), mInstanceState);
    }

    @SuppressWarnings("UnusedParameters")
    protected void onInit(Intent intent, Bundle extras, Bundle savedInstanceState) {
        // Do nothing
    }

    @AfterViews
    protected void onViewChanged()
    {
        // Lock orientation of the current activity
        onLockOrientation();

        // Init ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.acm__layout_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        // Init Content's layout
        View view = findViewById(R.id.acm__layout_content);
        if (view == null) {
            Bundle bundle = BundleUtils.get(getExtras(), IntentBuilder.EXTRA_ACTIVITY);

            // Get layout's Id
            int layoutId = BundleUtils.getInt(bundle, IntentBuilder.EXTRA_CONTAINER_ID, ResId.NO_ID);
            if (layoutId != ResId.NO_ID) {

                // Replace placeholder with passed layout
                ViewStub viewStub = (ViewStub) findViewById(R.id.acm__layout_container_stub);
                if (viewStub != null) {

                    viewStub.setLayoutResource(layoutId);
                    viewStub.inflate();
                }
            }
        }

        // Update UI
        if (!hasInstanceState()) {
            onInitInterface(getIntent(), getExtras(), null);
        }
    }

    protected void onLockOrientation() {
        ActivityUtils.lockActivityOrientation(this);
    }

    protected void onInitInterface(Intent intent, Bundle extras, Bundle savedInstanceState)
    {
        // Android Fragments recreated on orientation change
        // @see http://stackoverflow.com/a/10636182

        if (savedInstanceState == null) {
            Bundle bundle = BundleUtils.get(extras, IntentBuilder.EXTRA_FRAGMENT);

            // Get Fragment's class name
            String fname  = BundleUtils.get(bundle, IntentBuilder.EXTRA_CLASS_NAME);
            if (!StringUtils.isEmpty(fname)) {

                // Instantiate fragment
                Fragment fragment = Fragment.instantiate(this, fname, bundle.getBundle(IntentBuilder.EXTRA_ARGUMENTS));
                if (fragment != null) {

                    // Replace content layout
                    replaceContentLayout(fragment);
                }
            }
        }
    }

    // FIXME: protected
    public final void replaceContentLayout(@NonNull Fragment fragment) {
        assertNotNull(fragment, "fragment == null");

        // Replace fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.acm__layout_content, fragment).commit();
        mContentFragmentId = fragment.getId();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save instance state
        saveInstanceState(outState);

        // Release resources
        mInstanceState = null;
    }

    protected void saveInstanceState(@NonNull Bundle state) {
        assertNotNull(state, "state == null");

        // Save instance state
        state.putInt(PrefKeys.CONTENT_FRAGMENT_ID, mContentFragmentId);
        state.putBundle(PrefKeys.MOST_RECENT_EXTRAS, mMostRecentExtras);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Restore instance state
        if (!hasInstanceState()) {
            loadInstanceState(savedInstanceState);
        }

        // Update UI
        if (hasInstanceState()) {
            onInitInterface(getIntent(), getExtras(), savedInstanceState);
        }
    }

    protected void loadInstanceState(Bundle state) {
        mInstanceState = state;

        // Restore instance state
        mContentFragmentId = BundleUtils.getInt(state, PrefKeys.CONTENT_FRAGMENT_ID);
        mMostRecentExtras = BundleUtils.get(state, PrefKeys.MOST_RECENT_EXTRAS, new Bundle());
    }

// MARK: - Actions

    @TargetApi(VERSION_CODES.HONEYCOMB)
    @OptionsItem(android.R.id.home)
    public boolean onClickHome()
    {
        final Fragment fragment = getSupportFragmentManager().findFragmentById(mContentFragmentId);
        boolean result = false;

        if (fragment instanceof FragmentHomePressed) {
            result = ((FragmentHomePressed) fragment).onHomePressed();
        }

        if (!result) {
            result = onHomePressed();
        }

        // Done
        return result;
    }

    public boolean onHomePressed() {
        return false;
    }

    @Override
    public void onBackPressed()
    {
        final Fragment fragment = getSupportFragmentManager().findFragmentById(mContentFragmentId);
        boolean result = false;

        if (fragment instanceof FragmentBackPressed) {
            result = ((FragmentBackPressed) fragment).onBackPressed();
        }

        if (!result) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        // Android 2.0, Release 1 (Key events executed on key-up)
        // @link http://developer.android.com/about/versions/android-2.0.html

        // Bug fix for Samsung Galaxy Ace GT-S5830 with Android 2.3
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            event.startTracking();
            return true;
        }
        else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, @NonNull KeyEvent event)
    {
        // Android 2.0, Release 1 (Key events executed on key-up)
        // @link http://developer.android.com/about/versions/android-2.0.html

        // Bug fix for Samsung Galaxy Ace GT-S5830 with Android 2.3
        if (keyCode == KeyEvent.KEYCODE_BACK && event.isTracking() && ! event.isCanceled())
        {
            onBackPressed();
            return true;
        }
        else {
            return super.onKeyUp(keyCode, event);
        }
    }

// MARK: - Inner Types

    public interface FragmentHomePressed {
        boolean onHomePressed();
    }

    public interface FragmentBackPressed {
        boolean onBackPressed();
    }

// MARK: - Constants

    private interface PrefKeys {
        String CONTENT_FRAGMENT_ID = Prefix.PREFS + "CONTENT_FRAGMENT_ID";
        String MOST_RECENT_EXTRAS  = Prefix.PREFS + "MOST_RECENT_EXTRAS";
    }

// MARK: - Variables

    private Bundle mInstanceState;

    private int mContentFragmentId;

    private Bundle mMostRecentExtras;

}
