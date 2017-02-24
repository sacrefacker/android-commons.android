package com.roxiemobile.androidcommons.util;

import com.annimon.stream.Stream;
import com.roxiemobile.androidcommons.data.model.Validatable;

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
    public static boolean isValid(Validatable object) {
        return (object != null) && object.isValid();
    }

    /**
     * TODO
     */
    public static boolean isAllValid(Validatable... objects) {
        return ArrayUtils.isNotEmpty(objects) && Stream.of(objects).allMatch(ValidatableUtils::isValid);
    }

    /**
     * TODO
     */
    public static boolean isNotValid(Validatable object) {
        return (object != null) && !object.isValid();
    }

    /**
     * TODO
     */
    public static boolean isAllNotValid(Validatable... objects) {
        return ArrayUtils.isNotEmpty(objects) && Stream.of(objects).allMatch(ValidatableUtils::isNotValid);
    }

// MARK: - Methods

    /**
     * TODO
     */
    public static boolean isNullOrValid(Validatable object) {
        return (object == null) || object.isValid();
    }

    /**
     * TODO
     */
    public static boolean isAllNullOrValid(Validatable... objects) {
        return ArrayUtils.isNotEmpty(objects) && Stream.of(objects).allMatch(ValidatableUtils::isNullOrValid);
    }

    /**
     * TODO
     */
    public static boolean isNullOrNotValid(Validatable object) {
        return (object == null) || !object.isValid();
    }

    /**
     * TODO
     */
    public static boolean isAllNullOrNotValid(Validatable... objects) {
        return ArrayUtils.isNotEmpty(objects) && Stream.of(objects).allMatch(ValidatableUtils::isNullOrNotValid);
    }
}
