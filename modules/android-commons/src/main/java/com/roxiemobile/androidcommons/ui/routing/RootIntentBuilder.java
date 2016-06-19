package com.roxiemobile.androidcommons.ui.routing;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

public class RootIntentBuilder extends LeafIntentBuilder
{
// MARK: - Methods

    @Override
    public @NonNull Intent build(@NonNull Context context) {
        Intent intent = super.build(context);

        // Update Intent
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

}
