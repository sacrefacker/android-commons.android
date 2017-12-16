package com.roxiemobile.androidcommons.data.model;

import com.google.gson.annotations.SerializedName;
import com.roxiemobile.androidcommons.diagnostics.Check;

public class ParkingModel extends ValidatableModel
{
    @SerializedName("watcher")
    public String mWatcher;

    @SerializedName("vehicles")
    public VehicleModel[] mVehicles;

    @Override
    public void validate() {
        super.validate();

        // Validate instance variables
        Check.notBlank(mWatcher);
        Check.notEmpty(mVehicles);
        Check.allValid(mVehicles);
    }
}
