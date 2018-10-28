package com.zhr.test;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadUtil {

    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private static Executor sExecutor = Executors.newSingleThreadExecutor();

    public static void runOnThread(Runnable runnable){
        sExecutor.execute(runnable);
    }
    public static void runOnUIThread(Runnable runnable){
        sHandler.post(runnable);
    }
}