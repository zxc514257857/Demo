package com.zhr.test;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PieChartActivity extends AppCompatActivity {

    @BindView(R.id.pie_chart)
    PieChart mPieChart;
    private List<PieEntry> mPieEntry;
    private List<Integer> mPieColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        initPieEntry();
        initPieColor();
        initPieManager();
    }

    private void initPieEntry() {
        mPieEntry = new ArrayList<>();
        // 设置饼图数字和文字
        mPieEntry.add(new PieEntry(3f , "大白菜"));
        mPieEntry.add(new PieEntry(7f , "娃娃菜"));
    }

    private void initPieColor() {
        mPieColor = new ArrayList<>();
        // 设置饼图颜色
        mPieColor.add(getResources().getColor(R.color.colorAccent));
        mPieColor.add(getResources().getColor(R.color.colorPrimary));
    }

    private void initPieManager() {
        PieChartManager pieChartManager = new PieChartManager(mPieChart);
        pieChartManager.showPieChart(mPieEntry , mPieColor);

        // 饼图手势滑动监听
        mPieChart.setOnChartGestureListener(new OnChartGestureListener() {
            @Override
            public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
            }

            @Override
            public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
            }

            @Override
            public void onChartLongPressed(MotionEvent me) {
            }

            @Override
            public void onChartDoubleTapped(MotionEvent me) {
            }

            @Override
            public void onChartSingleTapped(MotionEvent me) {
            }

            @Override
            public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
            }

            @Override
            public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
            }

            @Override
            public void onChartTranslate(MotionEvent me, float dX, float dY) {
            }
        });

        // 饼图条目点击监听
        mPieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                LogUtils.e("onValueSelected:");
                mPieEntry.add(new PieEntry(7f , "小青菜"));
                initPieManager();
            }

            @Override
            public void onNothingSelected() {
                LogUtils.e("onNothingSelected");
                mPieEntry.clear();
            }
        });
    }
}














