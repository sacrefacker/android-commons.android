package com.roxiemobile.androidcommons.util;

/**
 * A set of expectation methods useful for validating objects states. Only failed expectations are recorded.
 * These methods can be used directly: <code>Expect.expectTrue(...)</code>, however,
 * they read better if they are referenced through static import:
 * <p>
 * <pre>
 * import static Expect.*;
 *    ...
 *    expectEquals(...);
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
     * Expects that a condition is true. If it isn't it throws an {@link ExpectationError} with the given message.
     *
     * @param message   The identifying message for the {@link ExpectationError} (<code>null</code> okay)
     * @param condition Condition to be checked
     */
    public static void expectTrue(boolean condition, String message) {
        if (!condition) {
            fail(message);
        }
    }

    /**
     * Expects that a condition is true. If it isn't it throws an {@link ExpectationError} without a message.
     *
     * @param condition Condition to be checked
     */
    public static void expectTrue(boolean condition) {
        expectTrue(condition, null);
    }

    /**
     * Expects that a condition is false. If it isn't it throws an {@link ExpectationError} with the given message.
     *
     * @param message   The identifying message for the {@link ExpectationError} (<code>null</code> okay)
     * @param condition Condition to be checked
     */
    public static void expectFalse(boolean condition, String message) {
        expectTrue(!condition, message);
    }

    /**
     * Expects that a condition is false. If it isn't it throws an {@link ExpectationError} without a message.
     *
     * @param condition Condition to be checked
     */
    public static void expectFalse(boolean condition) {
        expectFalse(condition, null);
    }

    /**
     * Fails a test with the given message.
     *
     * @param message The identifying message for the {@link ExpectationError} (<code>null</code> okay)
     * @see ExpectationError
     */
    public static void fail(String message) {
        if (message == null) {
            throw new ExpectationError();
        }
        throw new ExpectationError(message);
    }

    /**
     * Fails a test with no message.
     */
    public static void fail() {
        fail(null);
    }

    /**
     * Expects that an object isn't null. If it is an {@link ExpectationError} is thrown with the given message.
     *
     * @param message The identifying message for the {@link ExpectationError} (<code>null</code> okay)
     * @param object  Object to check or <code>null</code>
     */
    public static void expectNotNull(Object object, String message) {
        expectTrue(object != null, message);
    }

    /**
     * Expects that an object isn't null. If it is an {@link ExpectationError} is thrown.
     *
     * @param object Object to check or <code>null</code>
     */
    public static void expectNotNull(Object object) {
        expectNotNull(object, null);
    }

    /**
     * Expects that an object is null. If it is not, an {@link ExpectationError} is thrown with the given message.
     *
     * @param message The identifying message for the {@link ExpectationError} (<code>null</code> okay)
     * @param object  Object to check or <code>null</code>
     */
    public static void expectNull(Object object, String message) {
        if (object == null) {
            return;
        }
        failNotNull(message, object);
    }

    /**
     * Expects that an object is null. If it isn't an {@link ExpectationError} is thrown.
     *
     * @param object Object to check or <code>null</code>
     */
    public static void expectNull(Object object) {
        expectNull(object, null);
    }

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
     * {@link ExpectationError} is thrown. If it throws the wrong type of exception, an {@code
     * ExpectationError} is thrown describing the mismatch; the exception that was actually thrown can
     * be obtained by calling {@link ExpectationError#getCause}.
     *
     * @param expectedThrowable The expected type of the exception
     * @param runnable          A function that is expected to throw an exception when executed
     * @return The exception thrown by {@code runnable}
     */
    public static <T extends Throwable> T expectThrows(Class<T> expectedThrowable, ThrowingRunnable runnable)
    {
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
                throw new ExpectationError(mismatchMessage, actualThrown);
            }
        }

        String message = String.format("Expected %s to be thrown, but nothing was thrown", expectedThrowable.getSimpleName());
        throw new ExpectationError(message);
    }

    /**
     * TODO
     */
    public static void expectNotEmpty(CharSequence str, String message) {
        expectTrue(str != null && str.length() > 0, message);
    }

    public static void expectNotEmpty(CharSequence str) {
        expectNotEmpty(str, null);
    }

    /**
     * TODO
     */
    public static void expectEmpty(CharSequence str, String message) {
        expectTrue(str == null || str.length() < 1, message);
    }

    public static void expectEmpty(CharSequence str) {
        expectEmpty(str, null);
    }

// MARK: - Private Methods

    private static void failNotNull(String message, Object actual) {
        String formatted = "";

        if (message != null) {
            formatted = message + " ";
        }
        fail(formatted + "expected null, but was:<" + actual + ">");
    }

    private static String format(String message, Object expected, Object actual) {
        String formatted = "";

        if (message != null && !"".equals(message)) {
            formatted = message + " ";
        }

        String expectedString = String.valueOf(expected);
        String actualString = String.valueOf(actual);

        if (expectedString.equals(actualString)) {
            return formatted + "expected: " + formatClassAndValue(expected, expectedString) + " but was: "
                    + formatClassAndValue(actual, actualString);
        }
        else {
            return formatted + "expected:<" + expectedString + "> but was:<"
                    + actualString + ">";
        }
    }

    private static String formatClassAndValue(Object value, String valueString) {
        String className = (value == null) ? "null" : value.getClass().getName();
        return className + "<" + valueString + ">";
    }

}
