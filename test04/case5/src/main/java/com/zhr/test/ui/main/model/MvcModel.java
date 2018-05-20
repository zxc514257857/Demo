package com.zhr.test.ui.main.model;

import android.content.Context;

public interface MvcModel {

    String getDefaultLanguage();

    void switchLanguage(String mDefaultLanguage , String mSelectedLanguage);

    void nextActivity(Context context , Class clazz);
}
