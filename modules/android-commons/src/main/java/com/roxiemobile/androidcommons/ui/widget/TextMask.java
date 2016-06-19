package com.roxiemobile.androidcommons.ui.widget;

import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;

import com.roxiemobile.androidcommons.util.LogUtils;

import java.util.ArrayList;

public class TextMask
{
// MARK: - Construction

    public static TextMask build(String inputMask)
    {
        if (TextUtils.isEmpty(inputMask))
            return null;

        TextMask object = new TextMask();
        object.compile(inputMask);

        // Done
        return object;
    }

    private TextMask() {
        // Do nothing
    }

// MARK: - Properties

    public String getInputMask() {
        return mInputMask;
    }

// MARK: - Methods

    public int getInputType() {
        return (isNumbersOnly() ? InputType.TYPE_CLASS_NUMBER : InputType.TYPE_CLASS_TEXT);
    }

    public InputFilter[] getInputFilters() {
        return new InputFilter[] { new InputFilter.LengthFilter(mRules.size()) };
    }

    public boolean validateInput(String text)
    {
        if (TextUtils.isEmpty(text))
            return false;

        boolean result = (mRules.size() >= text.length());

        // Validate incoming string
        for (int idx = 0, count = text.length(); result && (idx < count); ++idx) {
            result = mRules.get(idx).isAcceptCharacter(text.charAt(idx));
        }

        // Done
        return result;
    }

    public boolean validate(String text)
    {
        if (TextUtils.isEmpty(text)) {
            return false;
        }

        boolean result = (mRules.size() >= text.length());

        // Validate incoming string
        for (int idx = 0, count = text.length(); result && (idx < count); ++idx) {
            result = mRules.get(idx).isAcceptCharacter(text.charAt(idx));
        }

        // Validate mandatory rules
        for (int idx = text.length(), count = mRules.size(); result && (idx < count); ++idx) {
            result = mRules.get(idx).isOptional();
        }

        // Done
        return result;
    }

    public String getMaskedText(String text)
    {
        if (!validateInput(text)) {
            return null;
        }

        StringBuilder builder = new StringBuilder();

        // Build output string
        for (int idx = 0, count = text.length(); idx < count; ++idx)
        {
            InputRule rule = mRules.get(idx);
            char ch = text.charAt(idx);

            if (rule instanceof InputRuleConst)
                continue;

            builder.append(ch);
        }

        // Done
        return builder.toString();
    }

    public String getValidText(String text)
    {
        if (TextUtils.isEmpty(text)) {
            text = "";
        }

        StringBuilder builder = new StringBuilder();
        CharSequence markers = toCharSequenceWithMarkers();

        // Build output string
        for (int idx = 0, countM = markers.length(), countS = text.length(); (idx < countM); ++idx)
        {
            InputRule rule = mRules.get(idx);

            if (rule instanceof InputRuleConst || rule instanceof InputRuleReadonly) {
                builder.append(rule.toCharacter());
            }
            else
            if (idx >= countS) {
                break;
            }
            else {
                char ch2 = text.charAt(idx);

                if (mRules.get(idx).isAcceptCharacter(ch2)) {
                    builder.append(ch2);
                }
                else {
                    break;
                }
            }
        }

        // Done
        return builder.toString();
    }

    public String formatString(String text)
    {
        if (TextUtils.isEmpty(text)) {
            text = "";
        }

        StringBuilder builder = new StringBuilder();
        CharSequence markers = toCharSequenceWithMarkers();

        // Build output string
        for (int idxM = 0, idxS = 0, countM = markers.length(), countS = text.length(); (idxM < countM) && (idxS < countS); ++idxM)
        {
            char ch1 = markers.charAt(idxM);
            if (ch1 == MARKER)
            {
                char ch2 = text.charAt(idxS);

                if (mRules.get(idxM).isAcceptCharacter(ch2))
                {
                    builder.append(ch2);
                    ++idxS;
                }
                else {
                    break;
                }
            }
            else {
                builder.append(ch1);
            }
        }

         // Done
         return builder.toString();
    }

    public CharSequence toCharSequenceWithMarkers()
    {
        StringBuilder builder = new StringBuilder();

        for (InputRule rule : mRules) {
            builder.append(rule.isMarker() ? MARKER : rule.toCharacter());
        }

        // Done
        return builder;
    }

    public CharSequence toCharSequence()
    {
        StringBuilder builder = new StringBuilder();

        for (InputRule rule : mRules) {
            builder.append(rule.toCharacter());
        }

        // Done
        return builder;
    }

// MARK: - Private Mathods

    private void compile(String inputMask)
    {
        ArrayList<InputRule> rules = new ArrayList<>();

        boolean inSquareBrackets = false;
        boolean inCurlyBrackets  = false;

        for (int idx = 0, count = inputMask.length(); idx < count; ++idx)
        {
            char ch = inputMask.charAt(idx);
            switch (ch)
            {
                case '[' : {
                    if ( ! inSquareBrackets) {
                        inSquareBrackets = true;
                    }
                    else {
                        LogUtils.w(TAG, "ALARM! “[[” in mask");
                    }
                    break;
                }
                case ']' : {
                    if (inSquareBrackets) {
                        inSquareBrackets = false;
                    }
                    else {
                        LogUtils.w(TAG, "ALARM! “]]” in mask");
                    }
                    break;
                }
                case '{' : {
                    if ( ! inCurlyBrackets) {
                        inCurlyBrackets = true;
                    }
                    else {
                        LogUtils.w(TAG, "ALARM! “{{” in mask");
                    }
                    break;
                }
                case '}' : {
                    if (inCurlyBrackets) {
                        inCurlyBrackets = false;
                    }
                    else {
                        LogUtils.w(TAG, "ALARM! “}}” in mask");
                    }
                    break;
                }

                default : {
                    rules.add(buildRule(ch, inSquareBrackets || inCurlyBrackets, inCurlyBrackets));
                    break;
                }
            }
        }

        // Init instance variables
        mInputMask = inputMask;
        mRules = rules;
    }

    @SuppressWarnings("UnusedAssignment")
    private InputRule buildRule(char ch, boolean isMarker, boolean isReadonly)
    {
        InputRule inputRule = null;

        if (isMarker && isReadonly) {
            inputRule = new InputRuleReadonly(ch);
        }
        else if (isMarker)
        {
            switch (ch)
            {
                case '0': {
                    inputRule = new InputRuleDigit();
                    break;
                }
                case '9': {
                    inputRule = new InputRuleDigitOpt();
                    break;
                }
                case 'A': {
                    inputRule = new InputRuleAlpha();
                    break;
                }
                case 'a': {
                    inputRule = new InputRuleAlphaOpt();
                    break;
                }
                case 'Z': {
                    inputRule = new InputRuleAlphaDigit();
                    break;
                }
                case 'z': {
                    inputRule = new InputRuleAlphaDigitOpt();
                    break;
                }
                case '_': {
                    inputRule = new InputRuleAny();
                    break;
                }
                case '-': {
                    inputRule = new InputRuleAnyOpt();
                    break;
                }

                default: {
                    // Any other chars in the mask actually can't be there, but we treat them as fixed chars
                    inputRule = new InputRuleReadonly(ch);
                    break;
                }
            }
        }
        else {
            // All fixed chars
            inputRule = new InputRuleConst(ch);
        }

        // Done
        return inputRule;
    }

    private boolean isNumbersOnly() {
        boolean result = true;

        // Scan mask for numbers
        for (int idx = 0, count = mRules.size(); result && (idx < count); ++idx) {
            InputRule rule = mRules.get(idx);

            if (rule instanceof InputRuleReadonly) {
                continue;
            }

            result = (rule instanceof InputRuleDigit);
        }

        // Done
        return result;
    }

// MARK: - Inner Types

    private abstract class InputRule
    {
        public boolean isMarker() {
            return true;
        }

        public boolean isOptional() {
            return false;
        }

        public abstract boolean isAcceptCharacter(char ch);

        public abstract char toCharacter();
    }

    private class InputRuleAlpha extends InputRule
    {
        public InputRuleAlpha() {
            // Do nothing
        }

        @Override
        public boolean isAcceptCharacter(char ch) {
            return Character.isLetter(ch);
        }

        @Override
        public char toCharacter() {
            return '*';
        }
    }

    private class InputRuleAlphaOpt extends InputRuleAlpha
    {
        public InputRuleAlphaOpt() {
            // Do nothing
        }

        @Override
        public boolean isOptional() {
            return true;
        }
    }

    private class InputRuleDigit extends InputRule
    {
        public InputRuleDigit() {
            // Do nothing
        }

        @Override
        public boolean isAcceptCharacter(char ch) {
            return Character.isDigit(ch);
        }

        @Override
        public char toCharacter() {
            return '#';
        }
    }

    private class InputRuleDigitOpt extends InputRuleDigit
    {
        public InputRuleDigitOpt() {
            // Do nothing
        }

        @Override
        public boolean isOptional() {
            return true;
        }
    }

    private class InputRuleAlphaDigit extends InputRuleAlpha
    {
        public InputRuleAlphaDigit() {
            // Do nothing
        }

        @Override
        public boolean isAcceptCharacter(char ch) {
            return Character.isLetter(ch) || Character.isDigit(ch);
        }

        @Override
        public char toCharacter() {
            return '@';
        }
    }

    private class InputRuleAlphaDigitOpt extends InputRuleAlphaDigit
    {
        public InputRuleAlphaDigitOpt() {
            // Do nothing
        }

        @Override
        public boolean isOptional() {
            return true;
        }
    }

    private class InputRuleAny extends InputRule
    {
        public InputRuleAny() {
            // Do nothing
        }

        @Override
        public boolean isAcceptCharacter(char ch) {
            return true;
        }

        @Override
        public char toCharacter() {
            return '?';
        }
    }

    private class InputRuleAnyOpt extends InputRuleAny
    {
        public InputRuleAnyOpt() {
            // Do nothing
        }

        @Override
        public boolean isOptional() {
            return true;
        }
    }

    private class InputRuleReadonly extends InputRule
    {
        public InputRuleReadonly(char ch) {
            mChar = ch;
        }

        @Override
        public boolean isAcceptCharacter(char ch) {
            return mChar == ch;
        }

        @Override
        public char toCharacter() {
            return mChar;
        }

        private char mChar;
    }

    private class InputRuleConst extends InputRuleReadonly
    {
        public InputRuleConst(char ch) {
            super(ch);
        }

        public boolean isMarker() {
            return false;
        }
    }

// MARK: - Constants

    private static final String TAG = TextMask.class.getSimpleName();

    // Defaults
    public static final char MARKER = 0x01;

// MARK: - Variables

    private String mInputMask;

    private ArrayList<InputRule> mRules;

}
