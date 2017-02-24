package com.roxiemobile.androidcommons.diagnostics;

import com.roxiemobile.androidcommons.data.model.Validatable;
import com.roxiemobile.androidcommons.util.ArrayUtils;
import com.roxiemobile.androidcommons.util.CollectionUtils;
import com.roxiemobile.androidcommons.util.StringUtils;
import com.roxiemobile.androidcommons.util.ValidatableUtils;

import java.util.Collection;
import java.util.Map;

/**
 * A set of expectation methods useful for validating objects states. Only failed expectations are recorded.
 * These methods can be used directly: <code>Expect.expectTrue(...)</code>, however,
 * they read better if they are referenced through static import:
 * <p>
 * <pre>
 * import static Expect.*;
 *    ...
 *    expectEqual(...);
 * </pre>
 */
public final class Expect
{
// MARK: - Construction

    private Expect() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * Expects that a condition is true. If it isn't it throws an {@link ExpectationException} with the given message.
     *
     * @param condition Condition to be checked
     * @param message   The identifying message for the {@link ExpectationException} ({@code null} okay)
     */
    public static void expectTrue(final boolean condition, final String message) {
        if (!condition) {
            throwException(message);
        }
    }

    /**
     * Expects that a condition is true. If it isn't it throws an {@link ExpectationException} without a message.
     *
     * @param condition Condition to be checked
     */
    public static void expectTrue(final boolean condition) {
        expectTrue(condition, null);
    }

    /**
     * Expects that a condition is false. If it isn't it throws an {@link ExpectationException} with the given message.
     *
     * @param condition Condition to be checked
     * @param message   The identifying message for the {@link ExpectationException} (<code>null</code> okay)
     */
    public static void expectFalse(final boolean condition, final String message) {
        expectTrue(!condition, message);
    }

    /**
     * Expects that a condition is false. If it isn't it throws an {@link ExpectationException} without a message.
     *
     * @param condition Condition to be checked
     */
    public static void expectFalse(final boolean condition) {
        expectFalse(condition, null);
    }

// MARK: --

    /**
     * Expects that two objects are equal. If they are not, an {@link ExpectationException} is thrown with
     * the given message. If <code>expected</code> and <code>actual</code> are <code>null</code>,
     * they are considered equal.
     *
     * @param expected Expected value
     * @param actual   Actual value
     * @param message  The identifying message for the {@link ExpectationException} (<code>null</code> okay)
     */
    static public void expectEqual(final Object expected, final Object actual, final String message) {
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
     * Expects that two objects are equal. If they are not, an {@link ExpectationException} without
     * a message is thrown. If <code>expected</code> and <code>actual</code> are <code>null</code>,
     * they are considered equal.
     *
     * @param expected Expected value
     * @param actual   The value to check against <code>expected</code>
     */
    public static void expectEqual(final Object expected, final Object actual) {
        expectEqual(expected, actual, null);
    }

    /**
     * Expects that two objects are <b>not</b> equals. If they are, an {@link ExpectationException}
     * is thrown with the given message. If <code>unexpected</code> and <code>actual</code>
     * are <code>null</code>, they are considered equal.
     *
     * @param unexpected Unexpected value to check
     * @param actual     The value to check against <code>unexpected</code>
     * @param message    The identifying message for the {@link ExpectationException} (<code>null</code> okay)
     */
    public static void expectNotEqual(final Object unexpected, final Object actual, final String message) {
        if (safeEqual(unexpected, actual)) {
            failEqual(message, actual);
        }
    }

    /**
     * Expects that two objects are <b>not</b> equals. If they are, an {@link ExpectationException} without
     * a message is thrown. If <code>unexpected</code> and <code>actual</code> are <code>null</code>,
     * they are considered equal.
     *
     * @param unexpected Unexpected value to check
     * @param actual     The value to check against <code>unexpected</code>
     */
    public static void expectNotEqual(final Object unexpected, final Object actual) {
        expectNotEqual(unexpected, actual, null);
    }

// MARK: --

    /**
     * Expects that two objects refer to the same object. If they are not,
     * an {@link ExpectationException} is thrown with the given message.
     *
     * @param expected The expected object
     * @param actual   The object to compare to <code>expected</code>
     * @param message  The identifying message for the {@link ExpectationException} (<code>null</code> okay)
     */
    public static void expectSame(final Object expected, final Object actual, final String message) {
        if (expected == actual) {
            return;
        }
        failNotSame(message, expected, actual);
    }

    /**
     * Expects that two objects refer to the same object. If they are not the same,
     * an {@link ExpectationException} without a message is thrown.
     *
     * @param expected The expected object
     * @param actual   The object to compare to <code>expected</code>
     */
    public static void expectSame(final Object expected, final Object actual) {
        expectSame(expected, actual, null);
    }

    /**
     * Expects that two objects do not refer to the same object. If they do refer to the same object,
     * an {@link ExpectationException} is thrown with the given message.
     *
     * @param unexpected The object you don't expect
     * @param actual     The object to compare to <code>unexpected</code>
     * @param message    The identifying message for the {@link ExpectationException} (<code>null</code> okay)
     */
    public static void expectNotSame(final Object unexpected, final Object actual, final String message) {
        if (unexpected == actual) {
            failSame(message);
        }
    }

    /**
     * Expects that two objects do not refer to the same object. If they do refer
     * to the same object, an {@link ExpectationException} without a message is thrown.
     *
     * @param unexpected The object you don't expect
     * @param actual     The object to compare to <code>unexpected</code>
     */
    public static void expectNotSame(final Object unexpected, final Object actual) {
        expectNotSame(unexpected, actual, null);
    }

// MARK: --

    /**
     * Expects that an object is null. If it is not, an {@link ExpectationException} is thrown with the given message.
     *
     * @param object  Object to check or <code>null</code>
     * @param message The identifying message for the {@link ExpectationException} (<code>null</code> okay)
     */
    public static void expectNull(final Object object, final String message) {
        if (object != null) {
            failNotNull(message, object);
        }
    }

    /**
     * Expects that an object is null. If it isn't an {@link ExpectationException} is thrown.
     *
     * @param object Object to check or <code>null</code>
     */
    public static void expectNull(final Object object) {
        expectNull(object, null);
    }

    /**
     * Expects that an object isn't null. If it is an {@link ExpectationException} is thrown with the given message.
     *
     * @param object  Object to check or <code>null</code>
     * @param message The identifying message for the {@link ExpectationException} (<code>null</code> okay)
     */
    public static void expectNotNull(final Object object, final String message) {
        expectTrue(object != null, message);
    }

    /**
     * Expects that an object isn't null. If it is an {@link ExpectationException} is thrown.
     *
     * @param object Object to check or <code>null</code>
     */
    public static void expectNotNull(final Object object) {
        expectNotNull(object, null);
    }

// MARK: --

    /**
     * TODO
     */
    public static void expectEmpty(final CharSequence value, final String message) {
        expectTrue(StringUtils.isEmpty(value), message);
    }

    /**
     * TODO
     */
    public static void expectEmpty(final CharSequence value) {
        expectEmpty(value, null);
    }

    /**
     * TODO
     */
    public static void expectAllEmpty(final CharSequence[] values, final String message) {
        if (ArrayUtils.isNotEmpty(values)) {
            expectTrue(StringUtils.isAllEmpty(values), message);
        }
    }

    /**
     * TODO
     */
    public static void expectAllEmpty(final CharSequence[] values) {
        expectAllEmpty(values, null);
    }

    /**
     * TODO
     */
    public static void expectNotEmpty(final CharSequence value, final String message) {
        expectTrue(StringUtils.isNotEmpty(value), message);
    }

    /**
     * TODO
     */
    public static void expectNotEmpty(final CharSequence value) {
        expectNotEmpty(value, null);
    }

    /**
     * TODO
     */
    public static void expectAllNotEmpty(final CharSequence[] values, final String message) {
        if (ArrayUtils.isNotEmpty(values)) {
            expectTrue(StringUtils.isAllNotEmpty(values), message);
        }
    }

    /**
     * TODO
     */
    public static void expectAllNotEmpty(final CharSequence[] values) {
        expectAllNotEmpty(values, null);
    }

// MARK: --

    /**
     * TODO
     */
    public static <T> void expectEmpty(final T[] array, final String message) {
        expectTrue(ArrayUtils.isEmpty(array), message);
    }

    /**
     * TODO
     */
    public static <T> void expectEmpty(final T[] array) {
        expectEmpty(array, null);
    }

    /**
     * TODO
     */
    public static <T> void expectEmpty(final Collection<T> collection, final String message) {
        expectTrue(CollectionUtils.isEmpty(collection), message);
    }

    /**
     * TODO
     */
    public static <T> void expectEmpty(final Collection<T> collection) {
        expectEmpty(collection, null);
    }

    /**
     * TODO
     */
    public static <K, V> void expectEmpty(final Map<K, V> map, final String message) {
        expectTrue(CollectionUtils.isEmpty(map), message);
    }

    /**
     * TODO
     */
    public static <K, V> void expectEmpty(final Map<K, V> map) {
        expectEmpty(map, null);
    }

    /**
     * TODO
     */
    public static <T> void expectNotEmpty(final T[] array, final String message) {
        expectTrue(ArrayUtils.isNotEmpty(array), message);
    }

    /**
     * TODO
     */
    public static <T> void expectNotEmpty(final T[] array) {
        expectNotEmpty(array, null);
    }

    /**
     * TODO
     */
    public static <T> void expectNotEmpty(final Collection<T> collection, final String message) {
        expectTrue(CollectionUtils.isNotEmpty(collection), message);
    }

    /**
     * TODO
     */
    public static <T> void expectNotEmpty(final Collection<T> collection) {
        expectNotEmpty(collection, null);
    }

    /**
     * TODO
     */
    public static <K, V> void expectNotEmpty(final Map<K, V> map, final String message) {
        expectTrue(CollectionUtils.isNotEmpty(map), message);
    }

    /**
     * TODO
     */
    public static <K, V> void expectNotEmpty(final Map<K, V> map) {
        expectNotEmpty(map, null);
    }

// MARK: --

    /**
     * TODO
     */
    public static void expectBlank(final CharSequence value, final String message) {
        expectTrue(StringUtils.isBlank(value), message);
    }

    /**
     * TODO
     */
    public static void expectBlank(final CharSequence value) {
        expectBlank(value, null);
    }

    /**
     * TODO
     */
    public static void expectAllBlank(final CharSequence[] values, final String message) {
        if (ArrayUtils.isNotEmpty(values)) {
            expectTrue(StringUtils.isAllBlank(values), message);
        }
    }

    /**
     * TODO
     */
    public static void expectAllBlank(final CharSequence[] values) {
        expectAllBlank(values, null);
    }

    /**
     * TODO
     */
    public static void expectNotBlank(final CharSequence value, final String message) {
        expectTrue(StringUtils.isNotBlank(value), message);
    }

    /**
     * TODO
     */
    public static void expectNotBlank(final CharSequence value) {
        expectNotBlank(value, null);
    }

    /**
     * TODO
     */
    public static void expectAllNotBlank(final CharSequence[] values, final String message) {
        if (ArrayUtils.isNotEmpty(values)) {
            expectTrue(StringUtils.isAllNotBlank(values), message);
        }
    }

    /**
     * TODO
     */
    public static void expectAllNotBlank(final CharSequence[] values) {
        expectAllNotBlank(values, null);
    }

// MARK: --

    /**
     * TODO
     */
    public static void expectValid(final Validatable object, final String message) {
        expectTrue(ValidatableUtils.isValid(object), message);
    }

    /**
     * TODO
     */
    public static void expectValid(final Validatable object) {
        expectValid(object, null);
    }

    /**
     * TODO
     */
    public static void expectAllValid(final Validatable[] objects, final String message) {
        if (ArrayUtils.isNotEmpty(objects)) {
            expectTrue(ValidatableUtils.isAllValid(objects), message);
        }
    }

    /**
     * TODO
     */
    public static void expectAllValid(final Validatable[] objects) {
        expectAllValid(objects, null);
    }

    /**
     * TODO
     */
    public static void expectNotValid(final Validatable object, final String message) {
        expectTrue(ValidatableUtils.isNotValid(object), message);
    }

    /**
     * TODO
     */
    public static void expectNotValid(final Validatable object) {
        expectNotValid(object, null);
    }

    /**
     * TODO
     */
    public static void expectAllNotValid(final Validatable[] objects, final String message) {
        if (ArrayUtils.isNotEmpty(objects)) {
            expectTrue(ValidatableUtils.isAllNotValid(objects), message);
        }
    }

    /**
     * TODO
     */
    public static void expectAllNotValid(final Validatable[] objects) {
        expectAllNotValid(objects, null);
    }

// MARK: --

    /**
     * TODO
     */
    public static void expectNullOrValid(final Validatable object, final String message) {
        expectTrue(ValidatableUtils.isNullOrValid(object), message);
    }

    /**
     * TODO
     */
    public static void expectNullOrValid(final Validatable object) {
        expectNullOrValid(object, null);
    }

    /**
     * TODO
     */
    public static void expectAllNullOrValid(final Validatable[] objects, final String message) {
        if (ArrayUtils.isNotEmpty(objects)) {
            expectTrue(ValidatableUtils.isAllNullOrValid(objects), message);
        }
    }

    /**
     * TODO
     */
    public static void expectAllNullOrValid(final Validatable[] objects) {
        expectAllNullOrValid(objects, null);
    }

    /**
     * TODO
     */
    public static void expectNullOrNotValid(final Validatable object, final String message) {
        expectTrue(ValidatableUtils.isNullOrNotValid(object), message);
    }

    /**
     * TODO
     */
    public static void expectNullOrNotValid(final Validatable object) {
        expectNullOrNotValid(object, null);
    }

    /**
     * TODO
     */
    public static void expectAllNullOrNotValid(final Validatable[] objects, final String message) {
        if (ArrayUtils.isNotEmpty(objects)) {
            expectTrue(ValidatableUtils.isAllNullOrNotValid(objects), message);
        }
    }

    /**
     * TODO
     */
    public static void expectAllNullOrNotValid(final Validatable[] objects) {
        expectAllNullOrNotValid(objects, null);
    }

// MARK: --

    /**
     * This interface facilitates the use of expectThrows from Java 8. It allows method references
     * to void methods (that declare checked exceptions) to be passed directly into expectThrows
     * without wrapping. It is not meant to be implemented directly.
     */
    public interface ThrowingRunnable {
        void run() throws Throwable;
    }

    /**
     * Expects that {@code runnable} throws an exception of type {@code expectedThrowable} when
     * executed. If it does, the exception object is returned. If it does not throw an exception, an
     * {@link ExpectationException} is thrown. If it throws the wrong type of exception, an {@code
     * ExpectationException} is thrown describing the mismatch; the exception that was actually thrown can
     * be obtained by calling {@link ExpectationException#getCause}.
     *
     * @param expectedThrowable The expected type of the exception
     * @param runnable          A function that is expected to throw an exception when executed
     * @return The exception thrown by {@code runnable}
     */
    public static <T extends Throwable> T expectThrows(final Class<T> expectedThrowable, final ThrowingRunnable runnable) {
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
                throw new ExpectationException(mismatchMessage, actualThrown);
            }
        }

        String message = String.format("Expected %s to be thrown, but nothing was thrown", expectedThrowable.getSimpleName());
        throw new ExpectationException(message);
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
            throw new ExpectationException();
        }
        throw new ExpectationException(message);
    }
}
