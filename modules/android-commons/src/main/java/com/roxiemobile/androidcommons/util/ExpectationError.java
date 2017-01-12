package com.roxiemobile.androidcommons.util;

/**
 * Thrown to indicate that an expectation has failed.
 *
 * <p>The seven one-argument public constructors provided by this
 * class ensure that the expectation error returned by the invocation:
 * <pre>
 *     new ExpectationError(<i>expression</i>)
 * </pre>
 * has as its detail message the <i>string conversion</i> of
 * <i>expression</i> (as defined in section 15.18.1.1 of
 * <cite>The Java&trade; Language Specification</cite>),
 * regardless of the type of <i>expression</i>.
 */
public class ExpectationError extends Error {
    private static final long serialVersionUID = 8404902409506727853L;

    /**
     * Constructs an ExpectationError with no detail message.
     */
    public ExpectationError() {
        // Do nothing
    }

    /**
     * This internal constructor does no processing on its string argument,
     * even if it is a null reference.  The public constructors will
     * never call this constructor with a null argument.
     */
    private ExpectationError(String detailMessage) {
        super(detailMessage);
    }

    /**
     * Constructs an ExpectationError with its detail message derived
     * from the specified object, which is converted to a string as
     * defined in section 15.18.1.1 of
     * <cite>The Java&trade; Language Specification</cite>.
     * <p>
     * If the specified object is an instance of {@code Throwable}, it
     * becomes the <i>cause</i> of the newly constructed expectation error.
     *
     * @param detailMessage value to be used in constructing detail message
     * @see   Throwable#getCause()
     */
    public ExpectationError(Object detailMessage) {
        this(String.valueOf(detailMessage));

        if (detailMessage instanceof Throwable) {
            initCause((Throwable) detailMessage);
        }
    }

    /**
     * Constructs an ExpectationError with its detail message derived
     * from the specified <code>boolean</code>, which is converted to
     * a string as defined in section 15.18.1.1 of
     * <cite>The Java&trade; Language Specification</cite>.
     *
     * @param detailMessage value to be used in constructing detail message
     */
    public ExpectationError(boolean detailMessage) {
        this(String.valueOf(detailMessage));
    }

    /**
     * Constructs an ExpectationError with its detail message derived
     * from the specified <code>char</code>, which is converted to a
     * string as defined in section 15.18.1.1 of
     * <cite>The Java&trade; Language Specification</cite>.
     *
     * @param detailMessage value to be used in constructing detail message
     */
    public ExpectationError(char detailMessage) {
        this(String.valueOf(detailMessage));
    }

    /**
     * Constructs an ExpectationError with its detail message derived
     * from the specified <code>int</code>, which is converted to a
     * string as defined in section 15.18.1.1 of
     * <cite>The Java&trade; Language Specification</cite>.
     *
     * @param detailMessage value to be used in constructing detail message
     */
    public ExpectationError(int detailMessage) {
        this(String.valueOf(detailMessage));
    }

    /**
     * Constructs an ExpectationError with its detail message derived
     * from the specified <code>long</code>, which is converted to a
     * string as defined in section 15.18.1.1 of
     * <cite>The Java&trade; Language Specification</cite>.
     *
     * @param detailMessage value to be used in constructing detail message
     */
    public ExpectationError(long detailMessage) {
        this(String.valueOf(detailMessage));
    }

    /**
     * Constructs an ExpectationError with its detail message derived
     * from the specified <code>float</code>, which is converted to a
     * string as defined in section 15.18.1.1 of
     * <cite>The Java&trade; Language Specification</cite>.
     *
     * @param detailMessage value to be used in constructing detail message
     */
    public ExpectationError(float detailMessage) {
        this(String.valueOf(detailMessage));
    }

    /**
     * Constructs an ExpectationError with its detail message derived
     * from the specified <code>double</code>, which is converted to a
     * string as defined in section 15.18.1.1 of
     * <cite>The Java&trade; Language Specification</cite>.
     *
     * @param detailMessage value to be used in constructing detail message
     */
    public ExpectationError(double detailMessage) {
        this(String.valueOf(detailMessage));
    }

    /**
     * Constructs a new {@code ExpectationError} with the specified
     * detail message and cause.
     *
     * <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this error's detail message.
     *
     * @param  message the detail message, may be {@code null}
     * @param  cause the cause, may be {@code null}
     */
    public ExpectationError(String message, Throwable cause) {
        super(message, cause);
    }
}
