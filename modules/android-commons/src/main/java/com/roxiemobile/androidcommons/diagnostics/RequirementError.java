package com.roxiemobile.androidcommons.diagnostics;

/**
 * Thrown to indicate that an requirement has failed.
 *
 * <p>The seven one-argument public constructors provided by this
 * class ensure that the requirement error returned by the invocation:
 * <pre>
 *     new RequirementError(<i>expression</i>)
 * </pre>
 * has as its detail message the <i>string conversion</i> of
 * <i>expression</i> (as defined in section 15.18.1.1 of
 * <cite>The Java&trade; Language Specification</cite>),
 * regardless of the type of <i>expression</i>.
 */
public class RequirementError extends Error {
    private static final long serialVersionUID = 5067049024027853849L;

    /**
     * Constructs an RequirementError with no detail message.
     */
    public RequirementError() {
        // Do nothing
    }

    /**
     * This internal constructor does no processing on its string argument,
     * even if it is a null reference.  The public constructors will
     * never call this constructor with a null argument.
     */
    private RequirementError(String detailMessage) {
        super(detailMessage);
    }

    /**
     * Constructs an RequirementError with its detail message derived
     * from the specified object, which is converted to a string as
     * defined in section 15.18.1.1 of
     * <cite>The Java&trade; Language Specification</cite>.
     * <p>
     * If the specified object is an instance of {@code Throwable}, it
     * becomes the <i>cause</i> of the newly constructed requirement error.
     *
     * @param detailMessage Value to be used in constructing detail message
     * @see Throwable#getCause()
     */
    public RequirementError(Object detailMessage) {
        this(String.valueOf(detailMessage));

        if (detailMessage instanceof Throwable) {
            initCause((Throwable) detailMessage);
        }
    }

    /**
     * Constructs an RequirementError with its detail message derived
     * from the specified <code>boolean</code>, which is converted to
     * a string as defined in section 15.18.1.1 of
     * <cite>The Java&trade; Language Specification</cite>.
     *
     * @param detailMessage Value to be used in constructing detail message
     */
    public RequirementError(boolean detailMessage) {
        this(String.valueOf(detailMessage));
    }

    /**
     * Constructs an RequirementError with its detail message derived
     * from the specified <code>char</code>, which is converted to a
     * string as defined in section 15.18.1.1 of
     * <cite>The Java&trade; Language Specification</cite>.
     *
     * @param detailMessage Value to be used in constructing detail message
     */
    public RequirementError(char detailMessage) {
        this(String.valueOf(detailMessage));
    }

    /**
     * Constructs an RequirementError with its detail message derived
     * from the specified <code>int</code>, which is converted to a
     * string as defined in section 15.18.1.1 of
     * <cite>The Java&trade; Language Specification</cite>.
     *
     * @param detailMessage Value to be used in constructing detail message
     */
    public RequirementError(int detailMessage) {
        this(String.valueOf(detailMessage));
    }

    /**
     * Constructs an RequirementError with its detail message derived
     * from the specified <code>long</code>, which is converted to a
     * string as defined in section 15.18.1.1 of
     * <cite>The Java&trade; Language Specification</cite>.
     *
     * @param detailMessage Value to be used in constructing detail message
     */
    public RequirementError(long detailMessage) {
        this(String.valueOf(detailMessage));
    }

    /**
     * Constructs an RequirementError with its detail message derived
     * from the specified <code>float</code>, which is converted to a
     * string as defined in section 15.18.1.1 of
     * <cite>The Java&trade; Language Specification</cite>.
     *
     * @param detailMessage Value to be used in constructing detail message
     */
    public RequirementError(float detailMessage) {
        this(String.valueOf(detailMessage));
    }

    /**
     * Constructs an RequirementError with its detail message derived
     * from the specified <code>double</code>, which is converted to a
     * string as defined in section 15.18.1.1 of
     * <cite>The Java&trade; Language Specification</cite>.
     *
     * @param detailMessage Value to be used in constructing detail message
     */
    public RequirementError(double detailMessage) {
        this(String.valueOf(detailMessage));
    }

    /**
     * Constructs a new {@code RequirementError} with the specified
     * detail message and cause.
     *
     * <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this error's detail message.
     *
     * @param message The detail message, may be {@code null}
     * @param cause   The cause, may be {@code null}
     */
    public RequirementError(String message, Throwable cause) {
        super(message, cause);
    }
}
