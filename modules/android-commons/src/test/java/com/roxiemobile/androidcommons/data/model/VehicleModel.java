package com.roxiemobile.androidcommons.data.model;

import com.google.gson.annotations.SerializedName;
import com.roxiemobile.androidcommons.diagnostics.Check;

import static com.roxiemobile.androidcommons.util.ArrayUtils.toArray;

public class VehicleModel extends ValidatableModel
{
    @SerializedName("model")
    public String mModel;

    @SerializedName("color")
    public String mColor;

    @Override
    public void validate() {
        super.validate();

        // Validate instance variables
        Check.allNotBlank(toArray(mModel, mColor));
    }
}
