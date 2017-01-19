package com.roxiemobile.androidcommons.data.model;

import com.google.gson.annotations.SerializedName;

import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotBlank;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectValid;

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
        expectNotBlank(mWatcher);
        expectValid(mVehicles);
    }
}
