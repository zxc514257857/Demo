package com.zhr.test.ui.main.model;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

public class MvcModelImpl implements MvcModel{

    private static final String TAG = MvcModelImpl.class.getSimpleName();
    private Context mContext;

    public MvcModelImpl(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 获取默认语言
     * @return
     */
    @Override
    public String getDefaultLanguage() {
        // 获得资源Resources对象
        Resources resources = mContext.getApplicationContext().getResources();
        //得到返回当前对象的语言"en""zh"等
        return resources.getConfiguration().locale.getLanguage();
    }

    /**
     * 更换语言
     */
    @Override
    public void switchLanguage(String mDefaultLanguage , String mSelectedLanguage) {
        if(mDefaultLanguage != null || mSelectedLanguage != null){
            // 如果选择的语言不为程序的默认语言 需要进行语言切换
            if(!mSelectedLanguage.equals(mDefaultLanguage)){
                // 得到本地资源配置对象
                Configuration localConfiguration = mContext.getResources().getConfiguration();
                // 根据选择的语言 生成一个语言环境Locale
                Locale defaultLocale = new Locale(mSelectedLanguage);
                // 将选择的语言更换为默认语言，进行更换前的准备，返回这个语言环境Locale
                Locale.setDefault(defaultLocale);
                Log.i(TAG, "switchLanguage: " + mSelectedLanguage);
                // 资源配置对象 设置语言环境
                localConfiguration.locale = defaultLocale;

                // 得到自定义资源的对象
                Resources resources = mContext.getResources();
                // 得到位置矩阵
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();

                // 进行资源(包括语言及图片资源)的更新
                resources.updateConfiguration(localConfiguration , displayMetrics);
            }
        }
    }

    /**
     * activity跳转
     */
    @Override
    public void nextActivity(Context context , Class clazz) {
        mContext.startActivity(new Intent(context, clazz));
    }
}
