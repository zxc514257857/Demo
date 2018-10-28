package com.zhr.test.ui.main.controler;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.zhr.test.R;

/**
 * 案例六：android中样式和主题的使用
 * 1，样式的使用：对布局中的同类代码进行样式抽取，以精简代码；如果有少量属性修改，可在样式调用后进行覆盖
 * 2，主题的使用：在样式中对主题样式进行设置 然后在androidManifest中进行调用
 * 3，代码中设置窗体透明度，昏暗度
 */
public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 设置窗体透明度
        setWindowsTransDegree();

        // 设置窗体昏暗度
        setWindowsDarkDegree();
    }

    /**
     * 设置窗体透明度
     */
    private void setWindowsTransDegree() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.9f;
        getWindow().setAttributes(lp);
    }

    /**
     * 设置窗体昏暗度
     */
    private void setWindowsDarkDegree() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = 0.4f;
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }
}
