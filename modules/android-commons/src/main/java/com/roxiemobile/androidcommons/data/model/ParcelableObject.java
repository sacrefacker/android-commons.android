package com.roxiemobile.androidcommons.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.roxiemobile.androidcommons.util.ParcelUtils;

public abstract class ParcelableObject
        implements Parcelable
{
// MARK: - Methods

    @Override
    public int describeContents() {
        return 0;
    }

    // How to read/write a boolean when implementing the Parcelable interface?
    // @link http://stackoverflow.com/a/7089687

    protected void writeBooleanToParcel(Parcel parcel, boolean value) {
        ParcelUtils.writeBooleanToParcel(parcel, value);
    }

    protected boolean readBooleanFromParcel(Parcel parcel) {
        return ParcelUtils.readBooleanFromParcel(parcel);
    }
}
