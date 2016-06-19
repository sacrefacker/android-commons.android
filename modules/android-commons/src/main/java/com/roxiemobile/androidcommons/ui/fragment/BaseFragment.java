package com.roxiemobile.androidcommons.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;

import com.roxiemobile.androidcommons.data.CommonKeys.Prefix;
import com.roxiemobile.androidcommons.ui.activity.base.BaseFragmentActivity;
import com.roxiemobile.androidcommons.util.BundleUtils;
import com.roxiemobile.androidcommons.util.FragmentUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import static com.roxiemobile.androidcommons.util.AssertUtils.assertNotNull;

@EFragment
public abstract class BaseFragment extends Fragment
{
// MARK: - Properties

    public final BaseFragmentActivity getBaseActivity() {
        FragmentActivity activity = getActivity();
        return (activity instanceof BaseFragmentActivity) ? (BaseFragmentActivity) activity : null;
    }

    public final Intent getIntent() {
        FragmentActivity activity = getActivity();
        return (activity != null) ? activity.getIntent() : null;
    }

    public final Bundle getExtras() {
        return FragmentUtils.getArguments(this, new Bundle());
    }

    public final String getUniqueTag() {
        return mUniqueTag;
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
            mUniqueTag = FragmentUtils.newTag(this);
        }

        // Init instance
        onInit(getIntent(), getExtras(), savedInstanceState);
    }

    @SuppressWarnings("UnusedParameters")
    protected void onInit(Intent intent, Bundle extras, Bundle savedInstanceState) {
        // Do nothing
    }

    @AfterViews
    protected void onViewChanged()
    {
        // Update UI
        if (!hasInstanceState()) {
            onInitInterface(getIntent(), getExtras(), null);
        }
    }

    protected void onInitInterface(Intent intent, Bundle extras, Bundle savedInstanceState)
    {
        // Handling ActionBar title with the fragment back stack?
        // @link http://stackoverflow.com/a/24674527

        if (this instanceof ActivityTitleProvider) {
            BaseFragmentActivity activity = getBaseActivity();

            if (activity != null) {
                ActionBar actionBar = getBaseActivity().getSupportActionBar();

                if (actionBar != null) {
                    actionBar.setTitle(((ActivityTitleProvider) this).getActivityTitle());
                }
            }
        }
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
        state.putString(PrefKeys.UNIQUE_TAG, mUniqueTag);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

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
        if (state != null) {
            mUniqueTag = BundleUtils.get(state, PrefKeys.UNIQUE_TAG);
        }
    }

    public View findViewById(int id) {
        View view = getView();
        return (view != null) ? view.findViewById(id) : null;
    }

    @SuppressWarnings("unused")
    protected void setOnClickOptionsItem(final MenuItem menuItem)
    {
        if (menuItem == null) {
            return;
        }

        final View actionView = MenuItemCompat.getActionView(menuItem);
        if (actionView != null) {

            final Fragment fragment = this;
            actionView.setOnClickListener(view -> fragment.onOptionsItemSelected(menuItem));
        }
    }

// MARK: - Inner Types

    public interface ActivityTitleProvider {
        String getActivityTitle();
    }

// MARK: - Constants

    private interface PrefKeys {
        String UNIQUE_TAG = Prefix.PREFS + "UNIQUE_TAG";
    }

// MARK: - Variables

    private Bundle mInstanceState;

    private String mUniqueTag;

}
