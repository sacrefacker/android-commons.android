package com.roxiemobile.androidcommons.util;

import android.support.annotation.NonNull;

import com.roxiemobile.androidcommons.data.model.Validatable;

import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotNull;

public final class ValidatableUtils
{
// MARK: - Construction

    private ValidatableUtils() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * TODO
     */
    public static boolean isValid(Validatable obj) {
        return obj != null && obj.isValid();
    }

    /**
     * TODO
     */
    public static boolean isValid(@NonNull Validatable... objects) {
        expectNotNull(objects, "objects is null");
        boolean result = true;

        if (ArrayUtils.isNullOrEmpty(objects)) {
            return true;
        }

        for (int idx = 0, count = objects.length; result && (idx < count); ++idx) {
            result = isValid(objects[idx]);
        }
        return result;
    }

    /**
     * TODO
     */
    public static boolean isNotValid(Validatable obj) {
        return obj != null && !obj.isValid();
    }

    /**
     * TODO
     */
    public static boolean isNotValid(@NonNull Validatable... objects) {
        expectNotNull(objects, "objects is null");
        boolean result = true;

        if (ArrayUtils.isNullOrEmpty(objects)) {
            return false;
        }

        for (int idx = 0, count = objects.length; result && (idx < count); ++idx) {
            result = isNotValid(objects[idx]);
        }
        return result;
    }

    /**
     * TODO
     */
    public static boolean isNullOrValid(Validatable obj) {
        return obj == null || obj.isValid();
    }

    /**
     * TODO
     */
    public static boolean isNullOrValid(@NonNull Validatable... objects) {
        expectNotNull(objects, "objects is null");
        boolean result = true;

        if (ArrayUtils.isNullOrEmpty(objects)) {
            return true;
        }

        for (int idx = 0, count = objects.length; result && (idx < count); ++idx) {
            result = isNullOrValid(objects[idx]);
        }
        return result;
    }

    /**
     * TODO
     */
    public static boolean isNullOrNotValid(Validatable obj) {
        return obj == null || !obj.isValid();
    }

    /**
     * TODO
     */
    public static boolean isNullOrNotValid(@NonNull Validatable... objects) {
        expectNotNull(objects, "objects is null");
        boolean result = true;

        if (ArrayUtils.isNullOrEmpty(objects)) {
            return false;
        }

        for (int idx = 0, count = objects.length; result && (idx < count); ++idx) {
            result = isNullOrNotValid(objects[idx]);
        }
        return result;
    }

}
