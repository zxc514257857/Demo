package test.zhr.com;

import android.app.Activity;
import android.app.Application;

import java.util.Set;

public class AppApplication extends Application{

    private static final String TAG = AppApplication.class.getSimpleName();
    private static AppApplication instance;
    private Set<Activity> allActivities;
    private int appCount = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    /**
     * Application的单例写法(之前的单例写法是有问题的)
     * @return
     */
    public static AppApplication getInstance(){
        return instance;
    }
}
