package com.zhr.test.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;

public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

    /**
     * 获取硬盘缓存路径
     * @param context
     * @param uniqueName
     * @return
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        if(TextUtils.isEmpty(uniqueName)){
            Log.i(TAG , "can't get disk cache dir because uniqueName is null");
            return null;
        }
        final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||!Environment.isExternalStorageRemovable()
                ? context.getExternalCacheDir().getAbsolutePath()
                : context.getCacheDir().getAbsolutePath();
        Log.i(TAG , "path：" + cachePath + File.separator + uniqueName);
        return new File(cachePath + File.separator + uniqueName);
    }
}
