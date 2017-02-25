package com.roxiemobile.androidcommons.data.validator;

// Commonly used regular expression patterns
// @link http://grepcode.com/file/repository.grepcode.com/java/ext/com.google.android/android/5.1.1_r1/android/util/Patterns.java#Patterns.0EMAIL_ADDRESS

public class EmailValidator
{
// MARK: - Construction

    public static class SingletonHolder {
        public static final EmailValidator SHARED_INSTANCE = new EmailValidator();
    }

    public static EmailValidator shared() {
        return EmailValidator.SingletonHolder.SHARED_INSTANCE;
    }

    public EmailValidator() {
        // Do nothing
    }

// MARK: - Methods

    public boolean isValid(CharSequence value) {
        return EMAIL_REGEX_VALIDATOR.isValid(value);
    }

// MARK: - Constants

    /**
     * Regular expression strings for Emails.
     */
    public static final String EMAIL_ADDRESS_REGEX = ""
            + "^"
                + "[a-zA-Z0-9\\.\\_\\%\\-\\+]{1,256}"
                + "\\@"
                + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"
                + "(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})*"
            + "$";

    /**
     * RegexValidator for matching Emails.
     */
    private static final RegexValidator EMAIL_REGEX_VALIDATOR =
            new RegexValidator(EMAIL_ADDRESS_REGEX);
}
