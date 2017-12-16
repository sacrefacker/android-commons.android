package com.roxiemobile.androidcommons.diagnostics;

import com.roxiemobile.androidcommons.data.model.Validatable;

import java.util.Collection;
import java.util.Map;

/**
 * A set of methods useful for validating objects states. Only failed checks are throws errors.
 * These methods can be used directly: <code>Guard.isTrue(...)</code>.
 */
public final class Guard
{
// MARK: - Construction

    private Guard() {
        // Do nothing
    }

// MARK: - Methods

    public static void isTrue(final boolean condition, final String message) {
        rethrowOnFailure(message, () -> Check.isTrue(condition));
    }

    public static void isTrue(final boolean condition) {
        isTrue(condition, null);
    }

    public static void isFalse(final boolean condition, final String message) {
        rethrowOnFailure(message, () -> Check.isFalse(condition));
    }

    public static void isFalse(final boolean condition) {
        isFalse(condition, null);
    }

// MARK: -

    public static void equal(final Object expected, final Object actual, final String message) {
        rethrowOnFailure(message, () -> Check.equal(expected, actual));
    }

    public static void equal(final Object expected, final Object actual) {
        equal(expected, actual, null);
    }

    public static void notEqual(final Object unexpected, final Object actual, final String message) {
        rethrowOnFailure(message, () -> Check.notEqual(unexpected, actual));
    }

    public static void notEqual(final Object unexpected, final Object actual) {
        notEqual(unexpected, actual, null);
    }

// MARK: -

    public static void same(final Object expected, final Object actual, final String message) {
        rethrowOnFailure(message, () -> Check.same(expected, actual));
    }

    public static void same(final Object expected, final Object actual) {
        same(expected, actual, null);
    }

    public static void notSame(final Object unexpected, final Object actual, final String message) {
        rethrowOnFailure(message, () -> Check.notSame(unexpected, actual));
    }

    public static void notSame(final Object unexpected, final Object actual) {
        notSame(unexpected, actual, null);
    }

// MARK: -

    public static void isNull(final Object object, final String message) {
        rethrowOnFailure(message, () -> Check.isNull(object));
    }

    public static void isNull(final Object object) {
        isNull(object, null);
    }

    public static <T> void allNull(final T[] objects, final String message) {
        rethrowOnFailure(message, () -> Check.allNull(objects));
    }

    public static <T> void allNull(final T[] objects) {
        allNull(objects, null);
    }

    public static <T> void allNull(final Collection<T> collection, final String message) {
        rethrowOnFailure(message, () -> Check.allNull(collection));
    }

    public static <T> void allNull(final Collection<T> collection) {
        allNull(collection, null);
    }

    public static void notNull(final Object object, final String message) {
        rethrowOnFailure(message, () -> Check.notNull(object));
    }

    public static void notNull(final Object object) {
        notNull(object, null);
    }

    public static <T> void allNotNull(final T[] objects, final String message) {
        rethrowOnFailure(message, () -> Check.allNotNull(objects));
    }

    public static <T> void allNotNull(final T[] objects) {
        allNotNull(objects, null);
    }

    public static <T> void allNotNull(final Collection<T> collection, final String message) {
        rethrowOnFailure(message, () -> Check.allNotNull(collection));
    }

    public static <T> void allNotNull(final Collection<T> collection) {
        allNotNull(collection, null);
    }

// MARK: -

    public static void empty(final CharSequence value, final String message) {
        rethrowOnFailure(message, () -> Check.empty(value));
    }

    public static void empty(final CharSequence value) {
        empty(value, null);
    }

    public static void allEmpty(final CharSequence[] values, final String message) {
        rethrowOnFailure(message, () -> Check.allEmpty(values));
    }

    public static void allEmpty(final CharSequence[] values) {
        allEmpty(values, null);
    }

    public static void notEmpty(final CharSequence value, final String message) {
        rethrowOnFailure(message, () -> Check.notEmpty(value));
    }

    public static void notEmpty(final CharSequence value) {
        notEmpty(value, null);
    }

    public static void allNotEmpty(final CharSequence[] values, final String message) {
        rethrowOnFailure(message, () -> Check.allNotEmpty(values));
    }

    public static void allNotEmpty(final CharSequence[] values) {
        allNotEmpty(values, null);
    }

// MARK: -

    /**
     * TODO
     */
    public static <T> void empty(final T[] array, final String message) {
        rethrowOnFailure(message, () -> Check.empty(array));
    }

    /**
     * TODO
     */
    public static <T> void empty(final T[] array) {
        empty(array, null);
    }

    /**
     * TODO
     */
    public static <T> void empty(final Collection<T> collection, final String message) {
        rethrowOnFailure(message, () -> Check.empty(collection));
    }

    /**
     * TODO
     */
    public static <T> void empty(final Collection<T> collection) {
        empty(collection, null);
    }

    /**
     * TODO
     */
    public static <K, V> void empty(final Map<K, V> map, final String message) {
        rethrowOnFailure(message, () -> Check.empty(map));
    }

    /**
     * TODO
     */
    public static <K, V> void empty(final Map<K, V> map) {
        empty(map, null);
    }

    /**
     * TODO
     */
    public static <T> void notEmpty(final T[] array, final String message) {
        rethrowOnFailure(message, () -> Check.notEmpty(array));
    }

    /**
     * TODO
     */
    public static <T> void notEmpty(final T[] array) {
        notEmpty(array, null);
    }

    /**
     * TODO
     */
    public static <T> void notEmpty(final Collection<T> collection, final String message) {
        rethrowOnFailure(message, () -> Check.notEmpty(collection));
    }

    /**
     * TODO
     */
    public static <T> void notEmpty(final Collection<T> collection) {
        notEmpty(collection, null);
    }

    /**
     * TODO
     */
    public static <K, V> void notEmpty(final Map<K, V> map, final String message) {
        rethrowOnFailure(message, () -> Check.notEmpty(map));
    }

    /**
     * TODO
     */
    public static <K, V> void notEmpty(final Map<K, V> map) {
        notEmpty(map, null);
    }

// MARK: -

    public static void blank(final CharSequence value, final String message) {
        rethrowOnFailure(message, () -> Check.blank(value));
    }

    public static void blank(final CharSequence value) {
        blank(value, null);
    }

    public static void allBlank(final CharSequence[] values, final String message) {
        rethrowOnFailure(message, () -> Check.allBlank(values));
    }

    public static void allBlank(final CharSequence[] values) {
        allBlank(values, null);
    }

    public static void notBlank(final CharSequence value, final String message) {
        rethrowOnFailure(message, () -> Check.notBlank(value));
    }

    public static void notBlank(final CharSequence value) {
        notBlank(value, null);
    }

    public static void allNotBlank(final CharSequence[] values, final String message) {
        rethrowOnFailure(message, () -> Check.allNotBlank(values));
    }

    public static void allNotBlank(final CharSequence[] values) {
        allNotBlank(values, null);
    }

// MARK: -

    public static void valid(final Validatable object, final String message) {
        rethrowOnFailure(message, () -> Check.valid(object));
    }

    public static void valid(final Validatable object) {
        valid(object, null);
    }

    public static void allValid(final Validatable[] objects, final String message) {
        rethrowOnFailure(message, () -> Check.allValid(objects));
    }

    public static void allValid(final Validatable[] objects) {
        allValid(objects, null);
    }

    public static void notValid(final Validatable object, final String message) {
        rethrowOnFailure(message, () -> Check.notValid(object));
    }

    public static void notValid(final Validatable object) {
        notValid(object, null);
    }

    public static void allNotValid(final Validatable[] objects, final String message) {
        rethrowOnFailure(message, () -> Check.allNotValid(objects));
    }

    public static void allNotValid(final Validatable[] objects) {
        allNotValid(objects, null);
    }

// MARK: -

    public static void nullOrValid(final Validatable object, final String message) {
        rethrowOnFailure(message, () -> Check.nullOrValid(object));
    }

    public static void nullOrValid(final Validatable object) {
        nullOrValid(object, null);
    }

    public static void allNullOrValid(final Validatable[] objects, final String message) {
        rethrowOnFailure(message, () -> Check.allNullOrValid(objects));
    }

    public static void allNullOrValid(final Validatable[] objects) {
        allNullOrValid(objects, null);
    }

    public static void nullOrNotValid(final Validatable object, final String message) {
        rethrowOnFailure(message, () -> Check.nullOrNotValid(object));
    }

    public static void nullOrNotValid(final Validatable object) {
        nullOrNotValid(object, null);
    }

    public static void allNullOrNotValid(final Validatable[] objects, final String message) {
        rethrowOnFailure(message, () -> Check.allNullOrNotValid(objects));
    }

    public static void allNullOrNotValid(final Validatable[] objects) {
        allNullOrNotValid(objects, null);
    }

// MARK: - Private Methods

    private static void rethrowOnFailure(final String message, final Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }

        try {
            task.run();
        }
        catch (CheckException e) {
            throwError(message, e);
        }
    }

    private static void throwError(final String message, final Throwable cause) {
        if (message == null) {
            throw new GuardError(cause);
        }
        throw new GuardError(message, cause);
    }
}
