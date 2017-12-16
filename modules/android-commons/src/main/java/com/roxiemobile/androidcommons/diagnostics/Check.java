package com.roxiemobile.androidcommons.diagnostics;

import com.annimon.stream.Stream;
import com.roxiemobile.androidcommons.data.model.Validatable;
import com.roxiemobile.androidcommons.util.ArrayUtils;
import com.roxiemobile.androidcommons.util.CollectionUtils;
import com.roxiemobile.androidcommons.util.StringUtils;
import com.roxiemobile.androidcommons.util.ValidatableUtils;

import java.util.Collection;
import java.util.Map;

/**
 * A set of methods useful for validating objects states. Only failed checks are throws exceptions.
 * These methods can be used directly: <code>Check.isTrue(...)</code>.
 */
public final class Check
{
// MARK: - Construction

    private Check() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * Expects that a condition is true. If it isn't it throws an {@link CheckException} with the given message.
     *
     * @param condition Condition to be checked
     * @param message   The identifying message for the {@link CheckException} ({@code null} okay)
     */
    public static void isTrue(final boolean condition, final String message) {
        if (!condition) {
            throwException(message);
        }
    }

    /**
     * Expects that a condition is true. If it isn't it throws an {@link CheckException} without a message.
     *
     * @param condition Condition to be checked
     */
    public static void isTrue(final boolean condition) {
        isTrue(condition, null);
    }

    /**
     * Expects that a condition is false. If it isn't it throws an {@link CheckException} with the given message.
     *
     * @param condition Condition to be checked
     * @param message   The identifying message for the {@link CheckException} (<code>null</code> okay)
     */
    public static void isFalse(final boolean condition, final String message) {
        isTrue(!condition, message);
    }

    /**
     * Expects that a condition is false. If it isn't it throws an {@link CheckException} without a message.
     *
     * @param condition Condition to be checked
     */
    public static void isFalse(final boolean condition) {
        isFalse(condition, null);
    }

// MARK: -

    /**
     * Expects that two objects are equal. If they are not, an {@link CheckException} is thrown with
     * the given message. If <code>expected</code> and <code>actual</code> are <code>null</code>,
     * they are considered equal.
     *
     * @param expected Expected value
     * @param actual   Actual value
     * @param message  The identifying message for the {@link CheckException} (<code>null</code> okay)
     */
    static public void equal(final Object expected, final Object actual, final String message) {
        if (safeEqual(expected, actual)) {
            // Do nothing
        }
        else if (expected instanceof String && actual instanceof String) {
            String cleanMessage = (message == null) ? "" : message;
            throw new ComparisonFailure(cleanMessage, (String) expected, (String) actual);
        }
        else {
            failNotEqual(expected, actual, message);
        }
    }

    /**
     * Expects that two objects are equal. If they are not, an {@link CheckException} without
     * a message is thrown. If <code>expected</code> and <code>actual</code> are <code>null</code>,
     * they are considered equal.
     *
     * @param expected Expected value
     * @param actual   The value to check against <code>expected</code>
     */
    public static void equal(final Object expected, final Object actual) {
        equal(expected, actual, null);
    }

    /**
     * Expects that two objects are <b>not</b> equals. If they are, an {@link CheckException}
     * is thrown with the given message. If <code>unexpected</code> and <code>actual</code>
     * are <code>null</code>, they are considered equal.
     *
     * @param unexpected Unexpected value to check
     * @param actual     The value to check against <code>unexpected</code>
     * @param message    The identifying message for the {@link CheckException} (<code>null</code> okay)
     */
    public static void notEqual(final Object unexpected, final Object actual, final String message) {
        if (safeEqual(unexpected, actual)) {
            failEqual(message, actual);
        }
    }

    /**
     * Expects that two objects are <b>not</b> equals. If they are, an {@link CheckException} without
     * a message is thrown. If <code>unexpected</code> and <code>actual</code> are <code>null</code>,
     * they are considered equal.
     *
     * @param unexpected Unexpected value to check
     * @param actual     The value to check against <code>unexpected</code>
     */
    public static void notEqual(final Object unexpected, final Object actual) {
        notEqual(unexpected, actual, null);
    }

// MARK: -

    /**
     * Expects that two objects refer to the same object. If they are not,
     * an {@link CheckException} is thrown with the given message.
     *
     * @param expected The expected object
     * @param actual   The object to compare to <code>expected</code>
     * @param message  The identifying message for the {@link CheckException} (<code>null</code> okay)
     */
    public static void same(final Object expected, final Object actual, final String message) {
        if (expected == actual) {
            return;
        }
        failNotSame(message, expected, actual);
    }

    /**
     * Expects that two objects refer to the same object. If they are not the same,
     * an {@link CheckException} without a message is thrown.
     *
     * @param expected The expected object
     * @param actual   The object to compare to <code>expected</code>
     */
    public static void same(final Object expected, final Object actual) {
        same(expected, actual, null);
    }

    /**
     * Expects that two objects do not refer to the same object. If they do refer to the same object,
     * an {@link CheckException} is thrown with the given message.
     *
     * @param unexpected The object you don't expect
     * @param actual     The object to compare to <code>unexpected</code>
     * @param message    The identifying message for the {@link CheckException} (<code>null</code> okay)
     */
    public static void notSame(final Object unexpected, final Object actual, final String message) {
        if (unexpected == actual) {
            failSame(message);
        }
    }

    /**
     * Expects that two objects do not refer to the same object. If they do refer
     * to the same object, an {@link CheckException} without a message is thrown.
     *
     * @param unexpected The object you don't expect
     * @param actual     The object to compare to <code>unexpected</code>
     */
    public static void notSame(final Object unexpected, final Object actual) {
        notSame(unexpected, actual, null);
    }

// MARK: -

    /**
     * Expects that an object is null. If it is not, an {@link CheckException} is thrown with the given message.
     *
     * @param object  Object to check or <code>null</code>
     * @param message The identifying message for the {@link CheckException} (<code>null</code> okay)
     */
    public static void isNull(final Object object, final String message) {
        if (object != null) {
            failNotNull(message, object);
        }
    }

    /**
     * Expects that an object is null. If it isn't an {@link CheckException} is thrown.
     *
     * @param object Object to check or <code>null</code>
     */
    public static void isNull(final Object object) {
        isNull(object, null);
    }

    /**
     * TODO
     */
    public static <T> void allNull(final T[] objects, final String message) {
        if (ArrayUtils.isNotEmpty(objects)) {
            isTrue(Stream.of(objects).allMatch(object -> object == null), message);
        }
    }

    /**
     * TODO
     */
    public static <T> void allNull(final T[] objects) {
        allNull(objects, null);
    }

    /**
     * TODO
     */
    public static <T> void allNull(final Collection<T> collection, final String message) {
        if (CollectionUtils.isNotEmpty(collection)) {
            isTrue(Stream.of(collection).allMatch(object -> object == null), message);
        }
    }

    /**
     * TODO
     */
    public static <T> void allNull(final Collection<T> collection) {
        allNull(collection, null);
    }

    /**
     * Expects that an object isn't null. If it is an {@link CheckException} is thrown with the given message.
     *
     * @param object  Object to check or <code>null</code>
     * @param message The identifying message for the {@link CheckException} (<code>null</code> okay)
     */
    public static void notNull(final Object object, final String message) {
        isTrue(object != null, message);
    }

    /**
     * Expects that an object isn't null. If it is an {@link CheckException} is thrown.
     *
     * @param object Object to check or <code>null</code>
     */
    public static void notNull(final Object object) {
        notNull(object, null);
    }

    /**
     * TODO
     */
    public static <T> void allNotNull(final T[] objects, final String message) {
        if (ArrayUtils.isNotEmpty(objects)) {
            isTrue(Stream.of(objects).allMatch(object -> object != null), message);
        }
    }

    /**
     * TODO
     */
    public static <T> void allNotNull(final T[] objects) {
        allNull(objects, null);
    }

    /**
     * TODO
     */
    public static <T> void allNotNull(final Collection<T> collection, final String message) {
        if (CollectionUtils.isNotEmpty(collection)) {
            isTrue(Stream.of(collection).allMatch(object -> object != null), message);
        }
    }

    /**
     * TODO
     */
    public static <T> void allNotNull(final Collection<T> collection) {
        allNull(collection, null);
    }

// MARK: -

    /**
     * TODO
     */
    public static void empty(final CharSequence value, final String message) {
        isTrue(StringUtils.isEmpty(value), message);
    }

    /**
     * TODO
     */
    public static void empty(final CharSequence value) {
        empty(value, null);
    }

    /**
     * TODO
     */
    public static void allEmpty(final CharSequence[] values, final String message) {
        if (ArrayUtils.isNotEmpty(values)) {
            isTrue(StringUtils.isAllEmpty(values), message);
        }
    }

    /**
     * TODO
     */
    public static void allEmpty(final CharSequence[] values) {
        allEmpty(values, null);
    }

    /**
     * TODO
     */
    public static void notEmpty(final CharSequence value, final String message) {
        isTrue(StringUtils.isNotEmpty(value), message);
    }

    /**
     * TODO
     */
    public static void notEmpty(final CharSequence value) {
        notEmpty(value, null);
    }

    /**
     * TODO
     */
    public static void allNotEmpty(final CharSequence[] values, final String message) {
        if (ArrayUtils.isNotEmpty(values)) {
            isTrue(StringUtils.isAllNotEmpty(values), message);
        }
    }

    /**
     * TODO
     */
    public static void allNotEmpty(final CharSequence[] values) {
        allNotEmpty(values, null);
    }

// MARK: -

    /**
     * TODO
     */
    public static <T> void empty(final T[] array, final String message) {
        isTrue(ArrayUtils.isEmpty(array), message);
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
        isTrue(CollectionUtils.isEmpty(collection), message);
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
        isTrue(CollectionUtils.isEmpty(map), message);
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
        isTrue(ArrayUtils.isNotEmpty(array), message);
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
        isTrue(CollectionUtils.isNotEmpty(collection), message);
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
        isTrue(CollectionUtils.isNotEmpty(map), message);
    }

    /**
     * TODO
     */
    public static <K, V> void notEmpty(final Map<K, V> map) {
        notEmpty(map, null);
    }

// MARK: -

    /**
     * TODO
     */
    public static void blank(final CharSequence value, final String message) {
        isTrue(StringUtils.isBlank(value), message);
    }

    /**
     * TODO
     */
    public static void blank(final CharSequence value) {
        blank(value, null);
    }

    /**
     * TODO
     */
    public static void allBlank(final CharSequence[] values, final String message) {
        if (ArrayUtils.isNotEmpty(values)) {
            isTrue(StringUtils.isAllBlank(values), message);
        }
    }

    /**
     * TODO
     */
    public static void allBlank(final CharSequence[] values) {
        allBlank(values, null);
    }

    /**
     * TODO
     */
    public static void notBlank(final CharSequence value, final String message) {
        isTrue(StringUtils.isNotBlank(value), message);
    }

    /**
     * TODO
     */
    public static void notBlank(final CharSequence value) {
        notBlank(value, null);
    }

    /**
     * TODO
     */
    public static void allNotBlank(final CharSequence[] values, final String message) {
        if (ArrayUtils.isNotEmpty(values)) {
            isTrue(StringUtils.isAllNotBlank(values), message);
        }
    }

    /**
     * TODO
     */
    public static void allNotBlank(final CharSequence[] values) {
        allNotBlank(values, null);
    }

// MARK: -

    /**
     * TODO
     */
    public static void valid(final Validatable object, final String message) {
        isTrue(ValidatableUtils.isValid(object), message);
    }

    /**
     * TODO
     */
    public static void valid(final Validatable object) {
        valid(object, null);
    }

    /**
     * TODO
     */
    public static void allValid(final Validatable[] objects, final String message) {
        if (ArrayUtils.isNotEmpty(objects)) {
            isTrue(ValidatableUtils.isAllValid(objects), message);
        }
    }

    /**
     * TODO
     */
    public static void allValid(final Validatable[] objects) {
        allValid(objects, null);
    }

    /**
     * TODO
     */
    public static void notValid(final Validatable object, final String message) {
        isTrue(ValidatableUtils.isNotValid(object), message);
    }

    /**
     * TODO
     */
    public static void notValid(final Validatable object) {
        notValid(object, null);
    }

    /**
     * TODO
     */
    public static void allNotValid(final Validatable[] objects, final String message) {
        if (ArrayUtils.isNotEmpty(objects)) {
            isTrue(ValidatableUtils.isAllNotValid(objects), message);
        }
    }

    /**
     * TODO
     */
    public static void allNotValid(final Validatable[] objects) {
        allNotValid(objects, null);
    }

// MARK: -

    /**
     * TODO
     */
    public static void nullOrValid(final Validatable object, final String message) {
        isTrue(ValidatableUtils.isNullOrValid(object), message);
    }

    /**
     * TODO
     */
    public static void nullOrValid(final Validatable object) {
        nullOrValid(object, null);
    }

    /**
     * TODO
     */
    public static void allNullOrValid(final Validatable[] objects, final String message) {
        if (ArrayUtils.isNotEmpty(objects)) {
            isTrue(ValidatableUtils.isAllNullOrValid(objects), message);
        }
    }

    /**
     * TODO
     */
    public static void allNullOrValid(final Validatable[] objects) {
        allNullOrValid(objects, null);
    }

    /**
     * TODO
     */
    public static void nullOrNotValid(final Validatable object, final String message) {
        isTrue(ValidatableUtils.isNullOrNotValid(object), message);
    }

    /**
     * TODO
     */
    public static void nullOrNotValid(final Validatable object) {
        nullOrNotValid(object, null);
    }

    /**
     * TODO
     */
    public static void allNullOrNotValid(final Validatable[] objects, final String message) {
        if (ArrayUtils.isNotEmpty(objects)) {
            isTrue(ValidatableUtils.isAllNullOrNotValid(objects), message);
        }
    }

    /**
     * TODO
     */
    public static void allNullOrNotValid(final Validatable[] objects) {
        allNullOrNotValid(objects, null);
    }

// MARK: -

    /**
     * This interface facilitates the use of isThrows from Java 8. It allows method references
     * to void methods (that declare checked exceptions) to be passed directly into isThrows
     * without wrapping. It is not meant to be implemented directly.
     */
    public interface ThrowingRunnable {
        void run() throws Throwable;
    }

    /**
     * Expects that {@code runnable} throws an exception of type {@code expectedThrowable} when
     * executed. If it does, the exception object is returned. If it does not throw an exception, an
     * {@link CheckException} is thrown. If it throws the wrong type of exception, an {@code
     * CheckException} is thrown describing the mismatch; the exception that was actually thrown can
     * be obtained by calling {@link CheckException#getCause}.
     *
     * @param expectedThrowable The expected type of the exception
     * @param runnable          A function that is expected to throw an exception when executed
     * @return The exception thrown by {@code runnable}
     */
    public static <T extends Throwable> T isThrows(final Class<T> expectedThrowable, final ThrowingRunnable runnable) {
        try {
            runnable.run();
        }
        catch (Throwable actualThrown)
        {
            if (expectedThrowable.isInstance(actualThrown)) {
                @SuppressWarnings("unchecked")
                T retVal = (T) actualThrown;
                return retVal;
            }
            else
            {
                String mismatchMessage = format("Unexpected exception type thrown;",
                        expectedThrowable.getSimpleName(), actualThrown.getClass().getSimpleName());
                throw new CheckException(mismatchMessage, actualThrown);
            }
        }

        String message = String.format("Expected %s to be thrown, but nothing was thrown", expectedThrowable.getSimpleName());
        throw new CheckException(message);
    }

// MARK: - Private Methods

    private static boolean safeEqual(final Object expected, final Object actual) {
        return (expected == null && actual == null) || (expected != null && actual != null && expected.equals(actual));
    }

    private static void failNotEqual(final Object expected, final Object actual, final String message) {
        throwException(format(message, expected, actual));
    }

    private static void failEqual(final String message, final Object actual) {
        String formatted = "Values should be different. ";
        if (message != null) {
            formatted = message + ". ";
        }

        formatted += "Actual: " + actual;
        throwException(formatted);
    }

    private static void failSame(final String message) {
        String formatted = "";

        if (message != null) {
            formatted = message + " ";
        }
        throwException(formatted + "expected not same");
    }

    private static void failNotSame(final String message, final Object expected, final Object actual) {
        String formatted = "";

        if (message != null) {
            formatted = message + " ";
        }
        throwException(formatted + "expected same:<" + expected + "> was not:<" + actual + ">");
    }

    private static void failNotNull(final String message, final Object actual) {
        String formatted = "";

        if (message != null) {
            formatted = message + " ";
        }
        throwException(formatted + "expected null, but was:<" + actual + ">");
    }

    static String format(final String message, final Object expected, final Object actual) {
        String formatted = "";

        if (message != null && !message.equals("")) {
            formatted = message + " ";
        }

        String expectedString = String.valueOf(expected);
        String actualString = String.valueOf(actual);

        if (expectedString.equals(actualString)) {
            return formatted + "expected: " + formatClassAndValue(expected, expectedString)
                    + " but was: " + formatClassAndValue(actual, actualString);
        }
        else {
            return formatted + "expected:<" + expectedString + "> but was:<" + actualString + ">";
        }
    }

    private static String formatClassAndValue(final Object value, final String valueString) {
        String className = (value == null) ? "null" : value.getClass().getName();
        return className + "<" + valueString + ">";
    }

    private static void throwException(final String message) {
        if (message == null) {
            throw new CheckException();
        }
        throw new CheckException(message);
    }
}
