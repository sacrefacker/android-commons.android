package com.roxiemobile.androidcommons.data.validator;

public class JsonValidationException extends Exception
{
// MARK: - Construction

    public JsonValidationException() {
        super();
    }

    public JsonValidationException(String detailMessage) {
        super(detailMessage);
    }

    public JsonValidationException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public JsonValidationException(Throwable throwable) {
        super(throwable);
    }

// MARK: - Variables

    private static final long serialVersionUID = 8039716172237443408L;
}
