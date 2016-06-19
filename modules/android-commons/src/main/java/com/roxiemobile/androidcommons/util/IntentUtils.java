package com.roxiemobile.androidcommons.util;

import android.content.Intent;
import android.os.Bundle;

public final class IntentUtils
{
// MARK: - Construction

    private IntentUtils() {
        // Do nothing
    }

// MARK: - Methods

    public static Bundle getExtras(Intent intent, Bundle defaultValue) {
        Bundle result = defaultValue;

        if (intent != null) {
            Bundle extras = intent.getExtras();

            if (extras != null) {
                result = extras;
            }
        }

        return result;
    }

    public static Object getExtra(Intent intent, String key, Object defaultValue) {
        Object result = defaultValue;

        if (intent != null && intent.hasExtra(key)) {
            result = intent.getExtras().get(key);
        }

        return result;
    }

    public static Object getExtra(Intent intent, String key) {
        return getExtra(intent, key, null);
    }

}
