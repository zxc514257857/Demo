package com.zhr.test

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

class MyApplication : Application() {

    /**
     * 第二步：初始化Arouter第三方库
     */
    override fun onCreate() {
        super.onCreate()
        if(AppConstants.DEBUG){
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}