package com.zhr.test.ui.main.controler;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhr.test.R;
import com.zhr.test.ui.main.model.MvcModel;
import com.zhr.test.ui.main.model.MvcModelImpl;
import com.zhr.test.ui.sec.controler.SecondActivity;

/**
 * 案例五：文字资源和图片资源的国际化
 *
 * 设置过语言之后，就通过系统api，将同一目录下面的不同国家资源进行替换，换成设置国家的资源
 * 默认的格式即为中国格式 不需要专门设置 除非是需要区分中文简体繁体时
 * 问题：中文切到外文再切到中文时切不回来了 不清楚是什么原因
 * AndroidManifest中的<uses-permission android:name="android.permission.CHANGE_CONFIGURATION"/>
 * 以及android:configChanges="locale" 加不加都没有什么影响
 */
public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;
    private Class clazz = SecondActivity.class;
    private MvcModel mMvcModel;
    private String mDefaultLanguage;
    private String mSelectedLanguage = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMvcModel = new MvcModelImpl(mContext);
        mDefaultLanguage = mMvcModel.getDefaultLanguage();
    }

    public void click01(View view){
        mMvcModel.nextActivity(mContext , clazz);
    }

    public void click02(View view){
        mSelectedLanguage = "en";
        mMvcModel.switchLanguage(mDefaultLanguage , mSelectedLanguage);
        mMvcModel.nextActivity(mContext , clazz);
    }

    public void click03(View view){
        mSelectedLanguage = "ja";
        mMvcModel.switchLanguage(mDefaultLanguage , mSelectedLanguage);
        mMvcModel.nextActivity(mContext , clazz);
    }

    public void click04(View view){
        mSelectedLanguage = "zh";
        mMvcModel.switchLanguage(mDefaultLanguage , mSelectedLanguage);
        mMvcModel.nextActivity(mContext , clazz);
    }
}
