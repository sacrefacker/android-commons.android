package com.roxiemobile.androidcommons.diagnostics;

import com.roxiemobile.androidcommons.data.model.Validatable;

import java.util.Collection;
import java.util.Map;

import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllBlank;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNotBlank;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNotEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNotNull;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNull;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNullOrNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNullOrValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectBlank;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectEqual;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectFalse;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotBlank;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotEqual;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotNull;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotSame;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNull;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNullOrNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNullOrValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectSame;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectTrue;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectValid;

/**
 * A set of requirement methods useful for validating objects states. Only failed requirements are recorded.
 * These methods can be used directly: <code>Require.requireTrue(...)</code>, however,
 * they read better if they are referenced through static import:
 * <p>
 * <pre>
 * import static Require.*;
 *    ...
 *    requireEqual(...);
 * </pre>
 */
public final class Require
{
// MARK: - Construction

    private Require() {
        // Do nothing
    }

// MARK: - Methods

    public static void requireTrue(final boolean condition, final String message) {
        rethrowOnFailure(message, () -> expectTrue(condition));
    }

    public static void requireTrue(final boolean condition) {
        requireTrue(condition, null);
    }

    public static void requireFalse(final boolean condition, final String message) {
        rethrowOnFailure(message, () -> expectFalse(condition));
    }

    public static void requireFalse(final boolean condition) {
        requireFalse(condition, null);
    }

// MARK: --

    public static void requireEqual(final Object expected, final Object actual, final String message) {
        rethrowOnFailure(message, () -> expectEqual(expected, actual));
    }

    public static void requireEqual(final Object expected, final Object actual) {
        requireEqual(expected, actual, null);
    }

    public static void requireNotEqual(final Object unexpected, final Object actual, final String message) {
        rethrowOnFailure(message, () -> expectNotEqual(unexpected, actual));
    }

    public static void requireNotEqual(final Object unexpected, final Object actual) {
        requireNotEqual(unexpected, actual, null);
    }

// MARK: --

    public static void requireSame(final Object expected, final Object actual, final String message) {
        rethrowOnFailure(message, () -> expectSame(expected, actual));
    }

    public static void requireSame(final Object expected, final Object actual) {
        requireSame(expected, actual, null);
    }

    public static void requireNotSame(final Object unexpected, final Object actual, final String message) {
        rethrowOnFailure(message, () -> expectNotSame(unexpected, actual));
    }

    public static void requireNotSame(final Object unexpected, final Object actual) {
        requireNotSame(unexpected, actual, null);
    }

// MARK: --

    public static void requireNull(final Object object, final String message) {
        rethrowOnFailure(message, () -> expectNull(object));
    }

    public static void requireNull(final Object object) {
        requireNull(object, null);
    }

    public static <T> void requireAllNull(final T[] objects, final String message) {
        rethrowOnFailure(message, () -> expectAllNull(objects));
    }

    public static <T> void requireAllNull(final T[] objects) {
        requireAllNull(objects, null);
    }

    public static <T> void requireAllNull(final Collection<T> collection, final String message) {
        rethrowOnFailure(message, () -> expectAllNull(collection));
    }

    public static <T> void requireAllNull(final Collection<T> collection) {
        requireAllNull(collection, null);
    }

    public static void requireNotNull(final Object object, final String message) {
        rethrowOnFailure(message, () -> expectNotNull(object));
    }

    public static void requireNotNull(final Object object) {
        requireNotNull(object, null);
    }

    public static <T> void requireAllNotNull(final T[] objects, final String message) {
        rethrowOnFailure(message, () -> expectAllNotNull(objects));
    }

    public static <T> void requireAllNotNull(final T[] objects) {
        requireAllNotNull(objects, null);
    }

    public static <T> void requireAllNotNull(final Collection<T> collection, final String message) {
        rethrowOnFailure(message, () -> expectAllNotNull(collection));
    }

    public static <T> void requireAllNotNull(final Collection<T> collection) {
        requireAllNotNull(collection, null);
    }

// MARK: --

    public static void requireEmpty(final CharSequence value, final String message) {
        rethrowOnFailure(message, () -> expectEmpty(value));
    }

    public static void requireEmpty(final CharSequence value) {
        requireEmpty(value, null);
    }

    public static void requireAllEmpty(final CharSequence[] values, final String message) {
        rethrowOnFailure(message, () -> expectAllEmpty(values));
    }

    public static void requireAllEmpty(final CharSequence[] values) {
        requireAllEmpty(values, null);
    }

    public static void requireNotEmpty(final CharSequence value, final String message) {
        rethrowOnFailure(message, () -> expectNotEmpty(value));
    }

    public static void requireNotEmpty(final CharSequence value) {
        requireNotEmpty(value, null);
    }

    public static void requireAllNotEmpty(final CharSequence[] values, final String message) {
        rethrowOnFailure(message, () -> expectAllNotEmpty(values));
    }

    public static void requireAllNotEmpty(final CharSequence[] values) {
        requireAllNotEmpty(values, null);
    }

// MARK: --

    /**
     * TODO
     */
    public static <T> void requireEmpty(final T[] array, final String message) {
        rethrowOnFailure(message, () -> expectEmpty(array));
    }

    /**
     * TODO
     */
    public static <T> void requireEmpty(final T[] array) {
        requireEmpty(array, null);
    }

    /**
     * TODO
     */
    public static <T> void requireEmpty(final Collection<T> collection, final String message) {
        rethrowOnFailure(message, () -> expectEmpty(collection));
    }

    /**
     * TODO
     */
    public static <T> void requireEmpty(final Collection<T> collection) {
        requireEmpty(collection, null);
    }

    /**
     * TODO
     */
    public static <K, V> void requireEmpty(final Map<K, V> map, final String message) {
        rethrowOnFailure(message, () -> expectEmpty(map));
    }

    /**
     * TODO
     */
    public static <K, V> void requireEmpty(final Map<K, V> map) {
        requireEmpty(map, null);
    }

    /**
     * TODO
     */
    public static <T> void requireNotEmpty(final T[] array, final String message) {
        rethrowOnFailure(message, () -> expectNotEmpty(array));
    }

    /**
     * TODO
     */
    public static <T> void requireNotEmpty(final T[] array) {
        requireNotEmpty(array, null);
    }

    /**
     * TODO
     */
    public static <T> void requireNotEmpty(final Collection<T> collection, final String message) {
        rethrowOnFailure(message, () -> expectNotEmpty(collection));
    }

    /**
     * TODO
     */
    public static <T> void requireNotEmpty(final Collection<T> collection) {
        requireNotEmpty(collection, null);
    }

    /**
     * TODO
     */
    public static <K, V> void requireNotEmpty(final Map<K, V> map, final String message) {
        rethrowOnFailure(message, () -> expectNotEmpty(map));
    }

    /**
     * TODO
     */
    public static <K, V> void requireNotEmpty(final Map<K, V> map) {
        requireNotEmpty(map, null);
    }

// MARK: --

    public static void requireBlank(final CharSequence value, final String message) {
        rethrowOnFailure(message, () -> expectBlank(value));
    }

    public static void requireBlank(final CharSequence value) {
        requireBlank(value, null);
    }

    public static void requireAllBlank(final CharSequence[] values, final String message) {
        rethrowOnFailure(message, () -> expectAllBlank(values));
    }

    public static void requireAllBlank(final CharSequence[] values) {
        requireAllBlank(values, null);
    }

    public static void requireNotBlank(final CharSequence value, final String message) {
        rethrowOnFailure(message, () -> expectNotBlank(value));
    }

    public static void requireNotBlank(final CharSequence value) {
        requireNotBlank(value, null);
    }

    public static void requireAllNotBlank(final CharSequence[] values, final String message) {
        rethrowOnFailure(message, () -> expectAllNotBlank(values));
    }

    public static void requireAllNotBlank(final CharSequence[] values) {
        requireAllNotBlank(values, null);
    }

// MARK: --

    public static void requireValid(final Validatable object, final String message) {
        rethrowOnFailure(message, () -> expectValid(object));
    }

    public static void requireValid(final Validatable object) {
        requireValid(object, null);
    }

    public static void requireAllValid(final Validatable[] objects, final String message) {
        rethrowOnFailure(message, () -> expectAllValid(objects));
    }

    public static void requireAllValid(final Validatable[] objects) {
        requireAllValid(objects, null);
    }

    public static void requireNotValid(final Validatable object, final String message) {
        rethrowOnFailure(message, () -> expectNotValid(object));
    }

    public static void requireNotValid(final Validatable object) {
        requireNotValid(object, null);
    }

    public static void requireAllNotValid(final Validatable[] objects, final String message) {
        rethrowOnFailure(message, () -> expectAllNotValid(objects));
    }

    public static void requireAllNotValid(final Validatable[] objects) {
        requireAllNotValid(objects, null);
    }

// MARK: --

    public static void requireNullOrValid(final Validatable object, final String message) {
        rethrowOnFailure(message, () -> expectNullOrValid(object));
    }

    public static void requireNullOrValid(final Validatable object) {
        requireNullOrValid(object, null);
    }

    public static void requireAllNullOrValid(final Validatable[] objects, final String message) {
        rethrowOnFailure(message, () -> expectAllNullOrValid(objects));
    }

    public static void requireAllNullOrValid(final Validatable[] objects) {
        requireAllNullOrValid(objects, null);
    }

    public static void requireNullOrNotValid(final Validatable object, final String message) {
        rethrowOnFailure(message, () -> expectNullOrNotValid(object));
    }

    public static void requireNullOrNotValid(final Validatable object) {
        requireNullOrNotValid(object, null);
    }

    public static void requireAllNullOrNotValid(final Validatable[] objects, final String message) {
        rethrowOnFailure(message, () -> expectAllNullOrNotValid(objects));
    }

    public static void requireAllNullOrNotValid(final Validatable[] objects) {
        requireAllNullOrNotValid(objects, null);
    }

// MARK: - Private Methods

    private static void rethrowOnFailure(final String message, final Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }

        try {
            task.run();
        }
        catch (ExpectationException e) {
            throwError(message, e);
        }
    }

    private static void throwError(final String message, final Throwable cause) {
        if (message == null) {
            throw new RequirementError(cause);
        }
        throw new RequirementError(message, cause);
    }
}
