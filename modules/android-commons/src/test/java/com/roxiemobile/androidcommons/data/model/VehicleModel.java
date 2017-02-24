package com.roxiemobile.androidcommons.data.model;

import com.google.gson.annotations.SerializedName;

import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNotBlank;
import static com.roxiemobile.androidcommons.util.ArrayUtils.toArray;

public class VehicleModel extends SerializableObject
{
    @SerializedName("model")
    public String mModel;

    @SerializedName("color")
    public String mColor;

    @Override
    protected void validate() {
        super.validate();

        // Validate instance variables
        expectAllNotBlank(toArray(mModel, mColor));
    }
}
