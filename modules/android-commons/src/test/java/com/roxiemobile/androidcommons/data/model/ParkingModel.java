package com.roxiemobile.androidcommons.data.model;

import com.google.gson.annotations.SerializedName;

import static com.roxiemobile.androidcommons.util.ThrowIf.throwIfNullOrNotValid;
import static com.roxiemobile.androidcommons.util.ThrowIf.throwIfNullOrWhiteSpace;

public class ParkingModel extends SerializableObject
{
    @SerializedName("watcher")
    public String mWatcher;

    @SerializedName("vehicles")
    public VehicleModel[] mVehicles;

    @Override
    protected void validate() {
        super.validate();

        // Validate instance variables
        throwIfNullOrWhiteSpace(mWatcher);
        throwIfNullOrNotValid(mVehicles);
    }
}
