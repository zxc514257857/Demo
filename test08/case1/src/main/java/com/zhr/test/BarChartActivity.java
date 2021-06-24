package com.zhr.test;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BarChartActivity extends AppCompatActivity {

    @BindView(R.id.bar_chart)
    BarChart mBarChart;

    private Context mContext = BarChartActivity.this;
    private List<BarEntry> mBarEntry;
    private BarDataSet mBarDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        initBar1Entry();
        initBar1DataSet();
        initBar1Manager();
    }

    private void initBar1Entry() {
        mBarEntry = new ArrayList<>();
        mBarEntry.add(new BarEntry(0f, 396));
        mBarEntry.add(new BarEntry(1f, 1089));
        mBarEntry.add(new BarEntry(2f, 963));
        mBarEntry.add(new BarEntry(3f, 756));
        mBarEntry.add(new BarEntry(4f, 287));
    }

    private void initBar1DataSet() {
        mBarDataSet = new BarDataSet(mBarEntry , "有违章");
        mBarDataSet.setValueTextColor(Color.RED);
        mBarDataSet.setColor(Color.GREEN);
        mBarDataSet.setValueTextSize(14f);
    }

    private void initBar1Manager() {
        BarChartManager barChartManager = new BarChartManager(mBarChart , mBarDataSet , mContext);
        barChartManager.initBar1Chart();
    }
}
