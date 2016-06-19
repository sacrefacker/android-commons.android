package com.roxiemobile.androidcommons.util;

import android.os.Parcel;

public final class ParcelUtils
{
// MARK: - Construction

    private ParcelUtils() {
        // Do nothing
    }

// MARK: - Methods

    // How to read/write a boolean when implementing the Parcelable interface?
    // @link http://stackoverflow.com/a/7089687

    public static void writeBooleanToParcel(Parcel parcel, boolean value) {
        parcel.writeInt(value ? 1 : 0);
    }

    public static boolean readBooleanFromParcel(Parcel parcel) {
        return (parcel.readInt() != 0);
    }

}
