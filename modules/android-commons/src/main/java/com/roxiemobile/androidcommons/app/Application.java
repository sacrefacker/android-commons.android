package com.roxiemobile.androidcommons.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.roxiemobile.androidcommons.util.LogUtils;

import static com.roxiemobile.androidcommons.util.AssertUtils.assertNotNull;

public class Application extends android.app.Application
{
// MARK: - Methods

    public static @Nullable PackageInfo getPackageInfo(@NonNull Context context) {
        assertNotNull(context, "context == null");

        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        }
        catch (NameNotFoundException e) {
            LogUtils.e(TAG, e);
        }
        return packageInfo;
    }

    public static @Nullable String getVersionName(@NonNull Context context) {
        assertNotNull(context, "context == null");

        PackageInfo packageInfo = getPackageInfo(context);
        return (packageInfo == null) ? null : packageInfo.versionName;
    }

    public static @Nullable Integer getVersionCode(@NonNull Context context) {
        assertNotNull(context, "context == null");

        PackageInfo packageInfo = getPackageInfo(context);
        return (packageInfo == null) ? null : packageInfo.versionCode;
    }

// MARK: - Constants

    private static final String TAG = Application.class.getSimpleName();

}
