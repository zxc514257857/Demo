package com.zhr.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 案例八：GreenDao数据库的基本使用
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScaleDaoUtil scaleDaoUtil = new ScaleDaoUtil(this);
        List<Scale> scales = new ArrayList<>();
        Scale scale1 = new Scale(1.02, 3, 1.02 * 3, "1234567", "dm001", 1L, "dm001", "微信", "元/公斤");
        Scale scale2 = new Scale(5.02, 2, 5.02 * 2, "5464846", "dm002", 2L, "dm002", "微信", "元/公斤");
        Scale scale3 = new Scale(8.02, 6, 8.02 * 6, "4585485", "dm003", 3L, "dm003", "微信", "元/公斤");
        scales.add(scale1);
        scales.add(scale2);
        scales.add(scale3);
        scaleDaoUtil.insertDataList(scales);
    }
}
