package com.roxiemobile.androidcommons.util;

public class ValidationException extends RuntimeException
{
// MARK: - Construction

    /**
     * Constructs a new runtime exception with {@code null} as its detail message.  The cause is not initialized,
     * and may subsequently be initialized by a call to {@link #initCause}.
     */
    public ValidationException() {
        super();
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message The detail message which is saved for later retrieval by the {@link #getMessage()} method.
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with the specified detail message and cause.  <p>Note that
     * the detail message associated with {@code cause} is <i>not</i> automatically incorporated
     * in this runtime exception's detail message.
     *
     * @param message The detail message which is saved for later retrieval by the {@link #getMessage()} method.
     * @param cause   The cause which is saved for later retrieval by the {@link #getCause()} method). A <tt>null</tt>
     *                value is permitted, and indicates that the cause is nonexistent or unknown.
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new runtime exception with the specified cause and a detail message
     * of <tt>(cause==null ? null : cause.toString())</tt> (which typically contains the class and detail message
     * of <tt>cause</tt>).  This constructor is useful for runtime exceptions that are little more than wrappers
     * for other throwables.
     *
     * @param cause The cause which is saved for later retrieval by the {@link #getCause()} method). A <tt>null</tt>
     *              value is permitted, and indicates that the cause is nonexistent or unknown.
     */
    public ValidationException(Throwable cause) {
        super(cause);
    }

// MARK: - Constants

    private static final long serialVersionUID = -8859635214223632138L;

}
