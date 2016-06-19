package com.roxiemobile.androidcommons.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

// How do disable paging by swiping with finger in ViewPager but still be able to swipe programmatically?
// @link http://stackoverflow.com/a/13437997

public class CustomViewPager extends ViewPager
{
// MARK: - Construction

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Init instance variables
        mIsPagingEnabled = true;
    }

// MARK: - Properties

    public void setPagingEnabled(boolean enabled) {
        mIsPagingEnabled = enabled;
    }

    public boolean isPagingEnabled() {
        return mIsPagingEnabled;
    }

// MARK: - Methods

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return (mIsPagingEnabled && super.onTouchEvent(event));
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return (mIsPagingEnabled && super.onInterceptTouchEvent(event));
    }

// MARK: - Variables

    private boolean mIsPagingEnabled;

}
