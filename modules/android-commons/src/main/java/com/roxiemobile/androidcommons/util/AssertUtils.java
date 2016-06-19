package com.roxiemobile.androidcommons.util;

/**
 * A set of assertion methods useful for validating objects states. Only failed assertions are recorded.
 * These methods can be used directly: <code>AssertUtils.assertTrue(...)</code>, however,
 * they read better if they are referenced through static import:
 * <p>
 * <pre>
 * import static AssertUtils.*;
 *    ...
 *    assertEquals(...);
 * </pre>
 */
public final class AssertUtils
{
// MARK: - Construction

    private AssertUtils() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * Asserts that a condition is true. If it isn't it throws an {@link AssertionError} with the given message.
     *
     * @param message   The identifying message for the {@link AssertionError} (<code>null</code> okay)
     * @param condition Condition to be checked
     */
    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            fail(message);
        }
    }

    /**
     * Asserts that a condition is true. If it isn't it throws an {@link AssertionError} without a message.
     *
     * @param condition Condition to be checked
     */
    public static void assertTrue(boolean condition) {
        assertTrue(condition, null);
    }

    /**
     * Asserts that a condition is false. If it isn't it throws an {@link AssertionError} with the given message.
     *
     * @param message   The identifying message for the {@link AssertionError} (<code>null</code> okay)
     * @param condition Condition to be checked
     */
    public static void assertFalse(boolean condition, String message) {
        assertTrue(!condition, message);
    }

    /**
     * Asserts that a condition is false. If it isn't it throws an {@link AssertionError} without a message.
     *
     * @param condition Condition to be checked
     */
    public static void assertFalse(boolean condition) {
        assertFalse(condition, null);
    }

    /**
     * Fails a test with the given message.
     *
     * @param message The identifying message for the {@link AssertionError} (<code>null</code> okay)
     * @see AssertionError
     */
    public static void fail(String message) {
        if (message == null) {
            throw new AssertionError();
        }
        throw new AssertionError(message);
    }

    /**
     * Fails a test with no message.
     */
    public static void fail() {
        fail(null);
    }

    /**
     * Asserts that an object isn't null. If it is an {@link AssertionError} is thrown with the given message.
     *
     * @param message The identifying message for the {@link AssertionError} (<code>null</code> okay)
     * @param object  Object to check or <code>null</code>
     */
    public static void assertNotNull(Object object, String message) {
        assertTrue(object != null, message);
    }

    /**
     * Asserts that an object isn't null. If it is an {@link AssertionError} is thrown.
     *
     * @param object Object to check or <code>null</code>
     */
    public static void assertNotNull(Object object) {
        assertNotNull(object, null);
    }

    /**
     * Asserts that an object is null. If it is not, an {@link AssertionError} is thrown with the given message.
     *
     * @param message The identifying message for the {@link AssertionError} (<code>null</code> okay)
     * @param object  Object to check or <code>null</code>
     */
    public static void assertNull(Object object, String message) {
        if (object == null) {
            return;
        }
        failNotNull(message, object);
    }

    /**
     * Asserts that an object is null. If it isn't an {@link AssertionError} is thrown.
     *
     * @param object Object to check or <code>null</code>
     */
    public static void assertNull(Object object) {
        assertNull(object, null);
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
     * Asserts that {@code runnable} throws an exception of type {@code expectedThrowable} when
     * executed. If it does not throw an exception, an {@link AssertionError} is thrown. If it
     * throws the wrong type of exception, an {@code AssertionError} is thrown describing the
     * mismatch; the exception that was actually thrown can be obtained by calling {@link
     * AssertionError#getCause}.
     *
     * @param expectedThrowable The expected type of the exception
     * @param runnable          A function that is expected to throw an exception when executed
     */
    public static void assertThrows(Class<? extends Throwable> expectedThrowable, ThrowingRunnable runnable) {
        //noinspection ThrowableResultOfMethodCallIgnored
        expectThrows(expectedThrowable, runnable);
    }

    /**
     * Asserts that {@code runnable} throws an exception of type {@code expectedThrowable} when
     * executed. If it does, the exception object is returned. If it does not throw an exception, an
     * {@link AssertionError} is thrown. If it throws the wrong type of exception, an {@code
     * AssertionError} is thrown describing the mismatch; the exception that was actually thrown can
     * be obtained by calling {@link AssertionError#getCause}.
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
                String mismatchMessage = format("unexpected exception type thrown;",
                        expectedThrowable.getSimpleName(), actualThrown.getClass().getSimpleName());

                // The AssertionError(String, Throwable) ctor is only available on JDK7.
                AssertionError assertionError = new AssertionError(mismatchMessage);
                assertionError.initCause(actualThrown);
                throw assertionError;
            }
        }

        String message = String.format("expected %s to be thrown, but nothing was thrown", expectedThrowable.getSimpleName());
        throw new AssertionError(message);
    }

    /**
     * TODO
     */
    public static void assertNotEmpty(CharSequence str, String message) {
        assertTrue(str != null && str.length() > 0, message);
    }

    public static void assertNotEmpty(CharSequence str) {
        assertNotEmpty(str, null);
    }

    /**
     * TODO
     */
    public static void assertEmpty(CharSequence str, String message) {
        assertTrue(str == null || str.length() < 1, message);
    }

    public static void assertEmpty(CharSequence str) {
        assertEmpty(str, null);
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
