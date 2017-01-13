package com.roxiemobile.androidcommons.util;

import android.support.annotation.NonNull;

import static com.roxiemobile.androidcommons.util.Expect.expectEqual;
import static com.roxiemobile.androidcommons.util.Expect.expectFalse;
import static com.roxiemobile.androidcommons.util.Expect.expectNotEmpty;
import static com.roxiemobile.androidcommons.util.Expect.expectNotEqual;
import static com.roxiemobile.androidcommons.util.Expect.expectNotNull;
import static com.roxiemobile.androidcommons.util.Expect.expectNotWhiteSpace;
import static com.roxiemobile.androidcommons.util.Expect.expectNull;
import static com.roxiemobile.androidcommons.util.Expect.expectNullOrValid;
import static com.roxiemobile.androidcommons.util.Expect.expectTrue;
import static com.roxiemobile.androidcommons.util.Expect.expectValid;

public final class ThrowIf
{
// MARK: - Construction

    private ThrowIf() {
        // Do nothing
    }

// MARK: - Methods

    public static void throwIfTrue(boolean condition, String message) {
        rethrowOnFailure(message, () -> expectTrue(condition));
    }

    public static void throwIfTrue(boolean condition) {
        throwIfTrue(condition, null);
    }

    public static void throwIfFalse(boolean condition, String message) {
        rethrowOnFailure(message, () -> expectFalse(condition));
    }

    public static void throwIfFalse(boolean condition) {
        throwIfFalse(condition, null);
    }

// MARK: - Methods

    public static void throwIfEqual(Object expected, Object actual, String message) {
        rethrowOnFailure(message, () -> expectEqual(expected, actual));
    }

    public static void throwIfEqual(Object expected, Object actual) {
        throwIfEqual(expected, actual, null);
    }

    public static void throwIfNotEqual(Object unexpected, Object actual, String message) {
        rethrowOnFailure(message, () -> expectNotEqual(unexpected, actual));
    }

    public static void throwIfNotEqual(Object unexpected, Object actual) {
        throwIfNotEqual(unexpected, actual, null);
    }

// MARK: - Methods

    public static void throwIfNull(Object object, String message) {
        rethrowOnFailure(message, () -> expectNull(object));
    }

    public static void throwIfNull(Object object) {
        throwIfNull(object, null);
    }

    public static void throwIfNotNull(Object object, String message) {
        rethrowOnFailure(message, () -> expectNotNull(object));
    }

    public static void throwIfNotNull(Object object) {
        throwIfNotNull(object, null);
    }

// MARK: - Methods

    public static void throwIfNullOrEmpty(CharSequence str, String message) {
        rethrowOnSuccess(message, () -> expectNotEmpty(str));
    }

    public static void throwIfNullOrEmpty(CharSequence str) {
        throwIfNullOrEmpty(str, null);
    }

    public static void throwIfNullOrEmpty(@NonNull CharSequence[] values, String message) {
        rethrowOnSuccess(message, () -> expectNotEmpty(values));
    }

    public static void throwIfNullOrEmpty(@NonNull CharSequence[] values) {
        throwIfNullOrEmpty(values, null);
    }

// MARK: - Methods

    public static void throwIfNullOrWhiteSpace(CharSequence str, String message) {
        rethrowOnSuccess(message, () -> expectNotWhiteSpace(str));
    }

    public static void throwIfNullOrWhiteSpace(CharSequence str) {
        throwIfNullOrWhiteSpace(str, null);
    }

    public static void throwIfNullOrWhiteSpace(@NonNull CharSequence[] values, String message) {
        rethrowOnSuccess(message, () -> expectNotWhiteSpace(values));
    }

    public static void throwIfNullOrWhiteSpace(@NonNull CharSequence[] values) {
        throwIfNullOrWhiteSpace(values, null);
    }

// MARK: - Methods

    public static void throwIfNotValid(Validatable obj, String message) {
        rethrowOnSuccess(message, () -> expectNullOrValid(obj));
    }

    public static void throwIfNotValid(Validatable obj) {
        throwIfNotValid(obj, null);
    }

    public static void throwIfNotValid(@NonNull Validatable[] objects, String message) {
        rethrowOnSuccess(message, () -> expectNullOrValid(objects));
    }

    public static void throwIfNotValid(@NonNull Validatable[] objects) {
        throwIfNotValid(objects, null);
    }

// MARK: - Methods

    public static void throwIfNullOrNotValid(Validatable obj, String message) {
        rethrowOnSuccess(message, () -> expectValid(obj));
    }

    public static void throwIfNullOrNotValid(Validatable obj) {
        throwIfNullOrNotValid(obj, null);
    }

    public static void throwIfNullOrNotValid(@NonNull Validatable[] objects, String message) {
        rethrowOnSuccess(message, () -> expectValid(objects));
    }

    public static void throwIfNullOrNotValid(@NonNull Validatable[] objects) {
        throwIfNullOrNotValid(objects, null);
    }

// MARK: - Private Methods

    private static void rethrowOnSuccess(String message, Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }

        Throwable cause = null;
        try {
            task.run();
        }
        catch (ExpectationError e) {
            cause = e;
        }

        if (cause != null) {
            throwException(message, cause);
        }
    }

    private static void rethrowOnFailure(String message, Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }

        Throwable cause = null;
        try {
            task.run();
        }
        catch (ExpectationError e) {
            cause = e;
        }

        if (cause == null) {
            throwException(message, null);
        }
    }

    private static void throwException(String message, Throwable cause) {
        if (message == null) {
            throw new ValidationException(cause);
        }
        throw new ValidationException(message, cause);
    }

}
