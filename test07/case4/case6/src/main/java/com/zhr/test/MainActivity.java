package com.zhr.test;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;

/**
 * 案例五：截屏功能测试
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn = findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        com.zhr.test.ScreentShotUtil.getInstance().takeScreenshot(this ,
                Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "zhumei_shp" + File.separator + "test.png");
    }
}
