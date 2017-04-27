package com.roxiemobile.androidcommons.data.model;

public interface PostValidatable
{
    /**
     * Checks if object should be validated after construction.
     */
    boolean isShouldPostValidate();

    /**
     * Checks attribute values or a combination of attribute values for correctness (cross validation).
     */
    void validate();
}
