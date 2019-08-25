package com.zhr.test;

import android.os.Environment;

import java.io.File;

public class AppConstance {

    public static final int MODE_ERROR = -1;
    public static final int MODE_DEFAULT = 1;
    public static final int MODE_SONIC = 2;
    public static final int MODE_SONIC_WITH_OFFLINE_CACHE = 3;
    public static final int REQUEST_CODE = 11;

    public static final String PARAM_URL = "PARAM_URL";
    public static final String PARAM_MODE = "PARAM_MODE";

    public static final String TEST_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "test/";
    public static final String WEBVIEW_CACHE_PATH = TEST_PATH + File.separator + "webview/";

    public static final String THREAD_NAME = "Thread";

    /**
     * webview - sonic 文件夹下面生成的文件 只有在预加载的时候才会缓存
     */
    public static final String PACKAGE_NAME = "com.zhr.test";
    public static final String USER_AGENT = "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 6 Build/LYZ28E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Mobile Safari/537.36";

    /**
     * 本地静态路径
     */
    public static final String LOCAL_URL = "file:///android_asset/index2.html";

    /**
     * WebView的最大缓存大小
     */
    public static final long WEBVIEW_CACHE_MAX_SIZE = 8 * 1024 * 1024;
}
