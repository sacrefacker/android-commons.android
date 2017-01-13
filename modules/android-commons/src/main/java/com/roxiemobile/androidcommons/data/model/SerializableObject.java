package com.roxiemobile.androidcommons.data.model;

import com.roxiemobile.androidcommons.util.Validatable;
import com.roxiemobile.androidcommons.util.LogUtils;

import java.io.Serializable;

public abstract class SerializableObject
        implements Serializable, Validatable
{
// MARK: - Methods

    /**
     * TODO
     */
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
            LogUtils.w(className, String.format("‘%s’ is invalid.", className), ex);
        }

        // Done
        return result;
    }

    /**
     * Checks attribute values or a combination of attribute values for correctness (cross validation).
     */
    protected void validate() {
        // Do nothing
    }

}
