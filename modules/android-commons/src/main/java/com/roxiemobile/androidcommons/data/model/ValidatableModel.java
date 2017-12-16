package com.roxiemobile.androidcommons.data.model;

import com.roxiemobile.androidcommons.logging.Logger;

import java.io.Serializable;

public abstract class ValidatableModel
        implements Serializable, Validatable, PostValidatable
{
// MARK: - Methods

    /**
     * Tests the current state of the object.
     */
    @Override
    public boolean isValid() {
        boolean result = true;

        try {
            // Check object's state
            validate();
        }
        catch (Exception ex) {
            String className = getClass().getSimpleName();
            result = false;

            // Log validation error
            Logger.w(className, String.format("%s is invalid", className), ex);
        }

        // Done
        return result;
    }

    /**
     * Checks if object should be validated after construction.
     */
    @Override
    public boolean isShouldPostValidate() {
        return true;
    }

    /**
     * Checks the current state of the object for correctness.
     */
    @Override
    public void validate() {
        // Do nothing
    }
}
