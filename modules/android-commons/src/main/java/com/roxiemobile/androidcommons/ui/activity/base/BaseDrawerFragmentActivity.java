package com.roxiemobile.androidcommons.ui.activity.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.support.v4.widget.DrawerLayout.SimpleDrawerListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.roxiemobile.androidcommons.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EActivity
public abstract class BaseDrawerFragmentActivity extends BaseFragmentActivity
{
// MARK: - Methods

    @Override
    protected void onInitInterface(Intent intent, Bundle extras, Bundle savedInstanceState) {
        super.onInitInterface(intent, extras, savedInstanceState);

        // Init ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        // Init Drawer layout
        if (mDrawerLayout != null) {
            mDrawerLayout.addDrawerListener(mSimpleDrawerListener);

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                mDrawerLayout.setDrawerShadow(R.drawable.acm__ic_vertical_shadow, GravityCompat.START);
            }
        }

        // ActionBarDrawerToggle provides convenient helpers for tying together the prescribed
        // interactions between a top-level sliding drawer and the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.acm__layout_toolbar);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.acm__drawer_open, R.string.acm__drawer_close);
        mDrawerToggle.syncState();

        // TODO: AppCompat v7 Toolbar onOptionsItemSelected not called
        // @link http://stackoverflow.com/a/28177091
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Update Drawer
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Update Drawer
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = false;

        if ((item != null) && (item.getItemId() == android.R.id.home) && mDrawerToggle.isDrawerIndicatorEnabled()) {
            result = true;

            // Toggle Drawer layout
            toggleDrawerMenu();
        }

        return (result || super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed()
    {
        if (getDrawerLayout().isDrawerVisible(GravityCompat.START)) {
            closeDrawerMenu();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Release resources
        if (mDrawerLayout != null) {
            mDrawerLayout.removeDrawerListener(mSimpleDrawerListener);
        }
    }

// --

    protected ActionBarDrawerToggle getDrawerToggle() {
        return mDrawerToggle;
    }

    protected DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

    public void addDrawerListener(DrawerListener listener) {
        synchronized (mLock) {
            if (!mDrawerListeners.contains(listener)) {
                mDrawerListeners.add(listener);
            }
        }
    }

    public void removeDrawerListener(DrawerListener listener) {
        synchronized (mLock) {
            mDrawerListeners.remove(listener);
        }
    }

    public void openDrawerMenu() {
        if (mDrawerLayout != null) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    public void closeDrawerMenu() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void toggleDrawerMenu()
    {
        if (getDrawerLayout().isDrawerVisible(GravityCompat.START)) {
            closeDrawerMenu();
        }
        else {
            openDrawerMenu();
        }
    }

// MARK: - Inner Types

    // ActionBarDrawerToggle
    // @link https://developer.android.com/reference/android/support/v4/app/ActionBarDrawerToggle.html

    private SimpleDrawerListener mSimpleDrawerListener = new SimpleDrawerListener()
    {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            mDrawerToggle.onDrawerSlide(drawerView, slideOffset);

            // Send event to the listeners
            for (DrawerListener listener : getListeners()) {
                listener.onDrawerSlide(drawerView, slideOffset);
            }
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            mDrawerToggle.onDrawerOpened(drawerView);

            // Send event to the listeners
            for (DrawerListener listener : getListeners()) {
                listener.onDrawerOpened(drawerView);
            }
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            mDrawerToggle.onDrawerClosed(drawerView);

            // Send event to the listeners
            for (DrawerListener listener : getListeners()) {
                listener.onDrawerClosed(drawerView);
            }
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            mDrawerToggle.onDrawerStateChanged(newState);

            // Send event to the listeners
            for (DrawerListener listener : getListeners()) {
                listener.onDrawerStateChanged(newState);
            }
        }

        private List<DrawerListener> getListeners() {
            synchronized (mLock) {
                return Collections.unmodifiableList(mDrawerListeners);
            }
        }
    };

// MARK: - Variables

    private ActionBarDrawerToggle mDrawerToggle;

    @ViewById(resName = "acm__layout_drawer")
    protected DrawerLayout mDrawerLayout;

    private List<DrawerListener> mDrawerListeners = new ArrayList<>();
    private final Object mLock = new Object();

}
