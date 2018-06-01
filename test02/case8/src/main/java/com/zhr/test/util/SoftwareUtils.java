package com.zhr.test.util;

import android.content.Context;
import android.content.pm.PackageInfo;

public class SoftwareUtils {

    /**
     * 获取app的版本
     * @param context
     * @return
     */
    public static int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
