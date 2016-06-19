package com.roxiemobile.androidcommons.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.roxiemobile.androidcommons.R;

public class BoundedLinearLayout extends LinearLayout
{
// MARK: - Construction

    public BoundedLinearLayout(Context context) {
        this(context, null);
    }

    public BoundedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Init UI
        onInitInterface(attrs);
    }

    @SuppressLint("NewApi")
    public BoundedLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // Init UI
        onInitInterface(attrs);
    }

// MARK: - Methods

    protected void onInitInterface(AttributeSet attrs) {
        TypedArray types = getContext().obtainStyledAttributes(attrs, R.styleable.ACM_BoundedLinearLayout);

        // Init instance variables
        mMaxWidth  = types.getDimensionPixelSize(
                R.styleable.ACM_BoundedLinearLayout_layout_maxWidth,  0);
        mMaxHeight = types.getDimensionPixelSize(
                R.styleable.ACM_BoundedLinearLayout_layout_maxHeight, 0);

        // Release resources
        types.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // Adjust width as necessary
        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (mMaxWidth > 0 && measuredWidth > mMaxWidth) {
            int measureMode = MeasureSpec.getMode(widthMeasureSpec);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxWidth, measureMode);
        }

        // Adjust height as necessary
        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (mMaxHeight > 0 && measuredHeight > mMaxHeight) {
            int measureMode = MeasureSpec.getMode(heightMeasureSpec);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxHeight, measureMode);
        }

        // Parent processing
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

// MARK: - Variables

    private int mMaxWidth;

    private int mMaxHeight;

}
