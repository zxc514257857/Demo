package com.zhr.test;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LineChartActivity extends AppCompatActivity {

    @BindView(R.id.line_chart)
    LineChart mLineChart;

    private List<Entry> mLineEntry;
    private LineDataSet mLineDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        initLineEntry();
        initLineDataSet();
        initLineChartManager();
    }

    private void initLineEntry() {
        // 设置折线的坐标点数据
        mLineEntry = new ArrayList<>();
        // 这里的x轴是纵坐标，y轴是横坐标
        mLineEntry.add(new Entry(100, 120));
        mLineEntry.add(new Entry(120, 150));
        mLineEntry.add(new Entry(130, 120));
        mLineEntry.add(new Entry(140, 130));
        mLineEntry.add(new Entry(150, 110));
    }

    private void initLineDataSet() {
        // 设置图例及折线的属性数据
        mLineDataSet = new LineDataSet(mLineEntry, "我是图例");
        mLineDataSet.setValueTextColor(Color.YELLOW);
        mLineDataSet.setColor(Color.GREEN);
        mLineDataSet.setValueTextSize(14f);
    }

    private void initLineChartManager() {
        LineChartManager lineChartManager = new LineChartManager(mLineChart, mLineDataSet);
        lineChartManager.initLineChart();
    }
}
