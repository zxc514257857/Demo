package com.zhr.test;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HBarChartActivity extends AppCompatActivity {

    @BindView(R.id.bar_chart)
    HorizontalBarChart mHorizontalBarChart;

    private Context mContext = HBarChartActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hbar);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mHorizontalBarChart.getDescription().setEnabled(false);
        mHorizontalBarChart.setExtraOffsets(5, 5, 5, 5);
        mHorizontalBarChart.setDrawBarShadow(false);
        mHorizontalBarChart.getLegend().setEnabled(false);
        mHorizontalBarChart.setDragEnabled(false);
        mHorizontalBarChart.setScaleEnabled(false);
        mHorizontalBarChart.setDrawGridBackground(false);
        mHorizontalBarChart.setDrawValueAboveBar(false);
        mHorizontalBarChart.setMaxVisibleValueCount(0);
        mHorizontalBarChart.setPinchZoom(false);
        XAxis xl = mHorizontalBarChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(false);
        xl.setDrawGridLines(false);
        xl.setGranularity(1f);
        xl.setTextColor(Color.parseColor("#FFFFFF"));
        xl.setAxisLineColor(Color.parseColor("#001CE8"));
        // 得到x轴值
        xl.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "其他11111";
            }
        });
        //获取到图形左边的Y轴，并设置为不显示
        mHorizontalBarChart.getAxisLeft().setEnabled(false);
        //获取到图形左边的Y轴
        YAxis leftAxis = mHorizontalBarChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setAxisMinimum(0);
        leftAxis.setTextColor(Color.parseColor("#000000"));
        leftAxis.setAxisLineColor(Color.parseColor("#FF0000"));
        //获取到图形右边的Y轴
        YAxis rightAxis = mHorizontalBarChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawAxisLine(false);
        rightAxis.setAxisMinimum(0);
        rightAxis.setTextColor(Color.parseColor("#000000"));
        rightAxis.setAxisLineColor(Color.parseColor("#001CE8"));
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0f, 396));
        barEntries.add(new BarEntry(1f, 1089));
        barEntries.add(new BarEntry(2f, 963));
        barEntries.add(new BarEntry(3f, 756));
        barEntries.add(new BarEntry(4f, 287));
        BarDataSet set1;
        set1 = new BarDataSet(barEntries, "对比");
        set1.setDrawIcons(false);
        set1.setColors(Color.parseColor("#1DCBFF"));
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        data.setBarWidth(0.4f);
        mHorizontalBarChart.setData(data);
        mHorizontalBarChart.setFitBars(true);
//        mHorizontalBarChart.animateY(3500);
    }
}
