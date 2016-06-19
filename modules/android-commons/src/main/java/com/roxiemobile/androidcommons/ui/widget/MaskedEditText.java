package com.roxiemobile.androidcommons.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

import com.roxiemobile.androidcommons.R;
import com.roxiemobile.androidcommons.util.LogUtils;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Deprecated
public class MaskedEditText extends EditText
{
// MARK: - Construction

    public MaskedEditText(Context context) {
        this(context, null);
    }

    public MaskedEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public MaskedEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // Init instance
        processAttributes(context, attrs);
    }

    protected void processAttributes(Context context, AttributeSet attributeSet)
    {
        if (isInEditMode())
            return;

        TypedArray attrs = context.obtainStyledAttributes(attributeSet, R.styleable.ACM_MaskedEditText);

        // Init instance
        String inputMask = attrs.getString(R.styleable.ACM_MaskedEditText_inputMask);
        setInputMask(inputMask);

        String regexMask = attrs.getString(R.styleable.ACM_MaskedEditText_regexMask);
        setRegexMask(regexMask);

        // Release resources
        attrs.recycle();
    }

// MARK: - Properties

    public TextMask getMask() {
        return mTextMask;
    }

    public String getInputMask() {
        return (mTextMask != null ? mTextMask.getInputMask() : null);
    }

    public void setInputMask(String inputMask)
    {
        mTextMask = TextMask.build(inputMask);
        if (mTextMask != null)
        {
            setInputType(mTextMask.getInputType());
            setText(mTextMask.getValidText(""));
            setFilters(mTextMask.getInputFilters());
        }
        else
        {
            mTextMask = null;
            setText(null);
        }

//        // FIXME: Delete!
//        Spannable text = this.getText();
//        Selection.setSelection(text, text.toString().length());
//
//        // ...
//        View layout = (View) this.getParent();
//        if (layout != null)
//        {
//            layout  = (View) layout.getParent();
//            if (layout != null) {
//                mMaskedTextView = (EditText) layout.findViewById(R.id.param_mask);
//            }
//        }
//
//        if (mMaskedTextView != null)
//        {
//            Drawable drawable = mMaskedTextView.getBackground();
//            if (drawable != null)
//            {
//                // Make background transparent
//                drawable.setAlpha(0);
//            }
//
//            mMaskedTextView.setText(mMask.asString());
//        }
    }

    public String getRegexMask() {
        return (mPattern == null) ? null : mPattern.pattern();
    }

    public void setRegexMask(String regexMask) {
        Pattern pattern = null;

        try {
            // Compile regex expression
            if (regexMask != null) {
                pattern = Pattern.compile(regexMask);
            }
        }
        catch (PatternSyntaxException e) {
            LogUtils.w(TAG, e);
        }

        // Store new pattern
        mPattern = pattern;
    }

// MARK: - Methods

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // Disable ActionBar's SelectionActionMode
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setCustomSelectionActionModeCallback(new DisableSelectionCallback());
        }

        // Set text watcher to filter inputs
        addTextChangedListener(new TextWatcher() {
            private boolean mAllowFormatTextField = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Do nothing
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (mAllowFormatTextField) {
                    return;
                }

                mAllowFormatTextField = true;
                {
                    if (mTextMask != null)
                    {
                        // NOTE: Using setText() causes some keyboards to swap layout

                        String newText = mTextMask.getValidText(s.toString());
                        if (getInputType() == InputType.TYPE_CLASS_NUMBER)
                        {
                            // This inputType means there's a DigitKeyListener attached to this view. If we use
                            // solution from another branch, it will cause that listener to filter all non-digit
                            // characters from formatted text.

                            setText(newText);
                        }
                        else {
                            // Android editText keyboard changed after setText()
                            // @link http://stackoverflow.com/a/10119112

                            getText().clear();
                            append(newText);
                        }
                    }

//                    // FIXME: Delete!
//                    if (mMaskedTextView != null && mTextMask != null)
//                    {
//                        String  text = getText().toString();
//                        text += mTextMask.toCharSequence().toString().substring(text.length());
//                        mMaskedTextView.setText(text);
//                    }
                }
                mAllowFormatTextField = false;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        boolean result = true;

        // Remove last character if needed
        if (mTextMask == null || TextUtils.isEmpty(mTextMask.getInputMask())) {
            result = super.onKeyDown(keyCode, event);
        }
        else if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
            deleteBackward();
        }
        else {
            result = super.onKeyDown(keyCode, event);
        }

        // Done
        return result;
    }

    @Override
    public InputConnection onCreateInputConnection(@NonNull EditorInfo outAttrs)
    {
        // Android EditText delete(backspace) key event (>= Jelly Bean)
        // @see http://stackoverflow.com/a/16551670

        return new CustomInputConnection(super.onCreateInputConnection(outAttrs), true);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd)
    {
        Spannable text = getText();
        if (text != null)
        {
            int length = text.length();
            if (selStart != length && selEnd != length)
            {
                // Place cursor to the end
                Selection.setSelection(text, length, length);
                return;
            }
        }

        // Parent processing
        super.onSelectionChanged(selStart, selEnd);
    }

    public String getMaskedText() {
        return (mTextMask != null) ? mTextMask.getMaskedText(getText().toString()) : null;
    }

    @SuppressWarnings("ConstantConditions")
    public boolean isTextValid() {
        boolean result = true;

        // validate Input mask
        String inputValue = getText().toString();

        if (mTextMask != null && inputValue != null) {
            result &= mTextMask.validate(inputValue);
        }

        // validate RegEx mask
        String regexValue = getMaskedText();

        if (mPattern != null && regexValue != null) {
            result &= mPattern.matcher(regexValue).matches();
        }

        // done
        return result;
    }

// MARK: - Private Methods

    private void deleteBackward()
    {
        String text = getText().toString();
        CharSequence markers = mTextMask.toCharSequenceWithMarkers().subSequence(0, text.length());

        for (int idx = markers.length() -1; idx > -1; --idx)
        {
            if (markers.charAt(idx) != TextMask.MARKER)
                continue;

            setText(text.substring(0, idx));

            Spannable span = getText();
            Selection.setSelection(span, span.toString().length());

            break;
        }
    }

// MARK: - Inner Types

    private class CustomInputConnection extends InputConnectionWrapper
    {
        public CustomInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        @Override
        public boolean sendKeyEvent(KeyEvent event) {
            boolean result = true;

            // Remove last character if needed
            if (mTextMask == null || TextUtils.isEmpty(mTextMask.getInputMask())) {
                result = super.sendKeyEvent(event);
            }
            else if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                deleteBackward();
            }
            else {
                result = super.sendKeyEvent(event);
            }

            // Done
            return result;
        }

        @Override
        public boolean deleteSurroundingText(int beforeLength, int afterLength)
        {
            // Magic: in latest Android, deleteSurroundingText(1, 0) will be called for backspace
            if (beforeLength == 1 && afterLength == 0)
            {
                // Backspace
                return sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL)) &&
                       sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP,   KeyEvent.KEYCODE_DEL));
            }

            return super.deleteSurroundingText(beforeLength, afterLength);
        }

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private class DisableSelectionCallback implements ActionMode.Callback
    {
        public DisableSelectionCallback() {
            // Do nothing
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            // Do nothing
        }

    }

// MARK: - Constants

    private static final String TAG = MaskedEditText.class.getSimpleName();

// MARK: - Variables

    private TextMask mTextMask;
    private Pattern mPattern;

    // FIXME: delete!
    // private EditText mMaskedTextView;

}
