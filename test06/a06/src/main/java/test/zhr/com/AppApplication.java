package test.zhr.com;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;

import java.io.File;
import java.util.Set;

public class AppApplication extends Application{

    private static final String TAG = AppApplication.class.getSimpleName();
    private static AppApplication instance;
    private Set<Activity> allActivities;
    private int appCount = 0;
    private HttpProxyCacheServer proxy;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static HttpProxyCacheServer getProxy(Context context) {
        AppApplication app = (AppApplication) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer.Builder(this)
                .cacheDirectory(new File(getExternalCacheDir(), "video-cache"))
                .maxCacheSize(AppConstants.VIDEO_MAX_DISK_CACHE_SIZE)
                .maxCacheFilesCount(AppConstants.VIDEO_MAX_DISK_CACHE_FILES_COUNT)
                .build();
    }

    /**
     * Application的单例写法(之前的单例写法是有问题的)
     * @return
     */
    public static AppApplication getInstance(){
        return instance;
    }
}
