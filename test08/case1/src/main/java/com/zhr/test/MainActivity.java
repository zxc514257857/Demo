package com.zhr.test;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.blankj.utilcode.util.ActivityUtils;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 案例一：
 * 测试MPChartLib 里面的
 * 圆饼图、垂直柱状图、折线图和雷达图demo
 */
public class MainActivity extends AppCompatActivity {

    private Context mContext = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({
            R.id.btn_pie , R.id.btn_bar , R.id.btn_line , R.id.btn_radar
    })
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_pie:
                ActivityUtils.startActivity(new Intent(mContext , PieChartActivity.class));
                break;

            case R.id.btn_bar:
                ActivityUtils.startActivity(new Intent(mContext , BarChartActivity.class));
                break;

            case R.id.btn_line:
                // TODO: 2021-07-06 折线图demo  安装mpchartDemo  还需要完善
                break;

            case R.id.btn_radar:
                // TODO: 2021-07-06 雷达图demo  还需要完善
                break;

            default:
                break;
        }
    }
}
