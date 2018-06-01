package com.zhr.test;

import android.app.Application;

public class App extends Application {

    private StudentBean mBean;
    private static App instance;

    public StudentBean getBean() {
        return mBean;
    }

    public void setBean(StudentBean bean) {
        mBean = bean;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance(){
        return instance;
    }
}
