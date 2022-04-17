package com.zhr.test;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import com.blankj.utilcode.util.LogUtils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

public class BarChartManager implements OnChartValueSelectedListener {

    private static final String TAG = "BarChartManager";
    private BarChart mBarChart;
    private IBarDataSet mDataSet;

    public BarChartManager(BarChart barChart , IBarDataSet dataSet) {
        this.mBarChart = barChart;
        this.mDataSet = dataSet;
    }

    public void initBarChart() {
        /***********************通过偏移量控制大小**********************/
        mBarChart.setExtraOffsets(0, 0, 0, 10f);
        /***********************通过偏移量控制大小***********************/

        /***********************背景颜色**********************/
        // 设置控件背景颜色
        mBarChart.setBackgroundColor(Color.WHITE);
        // 设置关闭条目阴影
        mBarChart.setDrawBarShadow(false);
        // 设置文字显示在条目上或条目中
        mBarChart.setDrawValueAboveBar(true);
        // 设置最大可见绘制的chartcount的数量 60个，barData.setDrawValues 设置为true时生效
        mBarChart.setMaxVisibleValueCount(60);
        // 设置显示网格线背景颜色
        mBarChart.setDrawGridBackground(false);
        // 设置网格线背景颜色
        mBarChart.setGridBackgroundColor(Color.RED);
        // 设置条目被选中以及未被选中的监听
        mBarChart.setOnChartValueSelectedListener(this);
        /***********************背景颜色***********************/

        /***********************缩放控制**********************/
        // 关闭缩放
        mBarChart.setScaleEnabled(false);
        // 设置缩放可在x轴y轴同时进行
        mBarChart.setPinchZoom(true);
        /***********************缩放控制**********************/

        /***********************默认数据***********************/
        mBarChart.setNoDataText("暂无数据哦");
        mBarChart.setNoDataTextColor(Color.BLACK);
        mBarChart.setNoDataTextTypeface(Typeface.DEFAULT_BOLD);
        /***********************默认数据***********************/

        /***********************大标题描述***********************/
        // 设置是否显示大标题描述
        mBarChart.getDescription().setEnabled(true);
        Description description = mBarChart.getDescription();
        String descriptionText = "青菜对比图";
        description.setText(descriptionText);
        description.setTextSize(25f);
        description.setTextAlign(Paint.Align.CENTER);
        description.setTextColor(Color.BLACK);
        // 设置大标题的偏移
        description.setXOffset(115f);
        description.setYOffset(10f);

//        // 设置大标题居中 可以参考 https://blog.csdn.net/Honiler/article/details/80073883  不晓得为什么不显示
//        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(outMetrics);
//        Paint paint = new Paint();
//        paint.setTextSize(18f);
//        paint.setTypeface(Typeface.DEFAULT_BOLD);
//        float x = outMetrics.widthPixels / 2;
//        float y =  Utils.calcTextHeight(paint, descriptionText) + Utils.convertDpToPixel(24);
//        description.setPosition(x, y);
        mBarChart.setDescription(description);
//        mBar1Chart.setHighlighter();
        /***********************大标题描述***********************/

        /***********************条目及标签***********************/

        // 获取x轴
        XAxis xAxis = mBarChart.getXAxis();
        // 设置x轴位于底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 设置x轴文字显示位置 是否居中显示，一般不居中显示，直接显示在柱状图下面
        xAxis.setCenterAxisLabels(false);
        // 设置垂直网格线是否显示
        xAxis.setDrawGridLines(true);
        // 设置垂直网格线为虚线（线长度为10，空格长度为10，虚线出发点（从第一根虚线的哪里出发））
        xAxis.enableGridDashedLine(10 , 10 , 0);
        // 设置x轴文字旋转角度
        xAxis.setLabelRotationAngle(0f);
        // 设置x轴文字大小
        xAxis.setTextSize(10f);
        // 设置x轴文字刻度数量
        xAxis.setLabelCount(5);
        // 设置是否显示x轴坐标轴（一般不需要显示。如不显示，setAxisLineWidth 和 setAxisLineColor 功能无效）
        xAxis.setDrawAxisLine(false);
        // 设置x轴 坐标轴的高度
        xAxis.setAxisLineWidth(10f);
        // 设置x轴 坐标轴的高度颜色
        xAxis.setAxisLineColor(Color.RED);
        // 设置x轴粒度（文字间隔）是否宣誓
        xAxis.setGranularityEnabled(true);
        // 设置最小间隔，防止当放大时，出现重复标签
        xAxis.setGranularity(0.1f);
        // 设置x轴自定义文字（将数字转为字符串）
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "￥" + value;
            }
        });

        // 获取y轴左侧
        YAxis yLAxis = mBarChart.getAxisLeft();
        // 设置y轴左侧文字位置  位于表格内部或者其他
        yLAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        // 设置y轴左侧水平网格线是否显示
        yLAxis.setDrawGridLines(true);
        // 设置是否显示y轴左侧 的坐标轴（如不显示，setAxisLineWidth 和 setAxisLineColor 功能无效）
        yLAxis.setDrawAxisLine(false);
        // 设置y轴左侧文字刻度数量
        yLAxis.setLabelCount(5);
        // 设置y轴左侧文字字体
        yLAxis.setTypeface(Typeface.DEFAULT_BOLD);
        // 设置y轴开始最小值
        yLAxis.setAxisMinimum(200f);
        // 设置y轴开始最大值
        yLAxis.setAxisMaximum(1100f);
        // 设置y轴顶部间距
        yLAxis.setSpaceMin(1f);
        // 设置y轴底部间距
        yLAxis.setSpaceMax(50f);

        // 获取y轴右侧
        YAxis yRAxis = mBarChart.getAxisRight();
        // 设置y轴右侧文字位置位于表格内部
        yRAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        // 设置y轴右侧是否生效
        yRAxis.setEnabled(true);
        // 设置y轴右侧水平网格线是否显示
        yRAxis.setDrawGridLines(true);
        // 设置y轴右侧网格线颜色
        yRAxis.setGridColor(Color.BLUE);
        // 设置y轴右侧文字是否显示
        yRAxis.setDrawLabels(true);
        // 设置y轴右侧文字大小
        yRAxis.setTextSize(10f);
        // 设置y轴右侧文字颜色
        yRAxis.setTextColor(Color.RED);
        // 设置y轴右侧文字刻度数量 以及是否精确设置
        // 奇怪的是 为什么左侧和右侧同样的刻度，高度是不一样的呢
        yRAxis.setLabelCount(5 , true);
        // 设置是否显示y轴右侧（如不显示，setAxisLineWidth 和 setAxisLineColor 功能无效）
        yRAxis.setDrawAxisLine(false);
        // 设置y轴右侧坐标轴厚度
        yRAxis.setAxisLineWidth(5f);
        // 设置y轴右侧坐标轴厚度颜色
        yRAxis.setAxisLineColor(Color.RED);

        // 设置y轴右侧显示零线
        yRAxis.setDrawZeroLine(true);
        // 设置y轴右侧零线厚度
        yRAxis.setZeroLineWidth(5f);
        // 设置y轴右侧零线颜色
        yRAxis.setZeroLineColor(Color.GREEN);
        // 设置y轴右侧数字反转  之前是 200-1000 设置之后就是1000-200了
        yRAxis.setInverted(false);
        /***********************条目标签***********************/

        /***********************图例***********************/
        Legend legend = mBarChart.getLegend();
        // 设置是否启用图例
        legend.setEnabled(true);
        // 设置是否画在图表里
        legend.setDrawInside(false);
        // 设置图例显示的位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        // 设置图例显示的方向
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        // 设置图例文字大小
        legend.setTextSize(12f);
        // 设置图例文字颜色
        legend.setTextColor(Color.BLACK);
        // 设置图例水平间距
        legend.setXEntrySpace(0f);
        // 设置图例垂直间距
        legend.setXEntrySpace(20f);
        // 设置图例图形样式
        legend.setForm(Legend.LegendForm.CIRCLE);
        // 设置图例图形大小
        legend.setFormSize(15);
        // 设置图例图形和文字的间距
        legend.setFormToTextSpace(10f);
        /***********************图例***********************/

        BarData barData = new BarData(mDataSet);
        /***********************条目***********************/
        // 设置条目的宽度
        barData.setBarWidth(0.4f);
        // 设置条目是否显示数值
        barData.setDrawValues(true);

        /**
         * 修改条目点击颜色：
         * 再到 BarChartRenderer.java 类
         * mHighlightPaint.setColor(Color.rgb(0, 0, 0)); 颜色修改
         * 找到 drawHighlighted 方法
         * mHighlightPaint.setColor(set.getHighLightColor()); 颜色修改
         * 然后注释掉这句话: mHighlightPaint.setAlpha(set.getHighLightAlpha());
         */

        /***********************条目***********************/
        mBarChart.setData(barData);
        // 更新数据
        barData.notifyDataChanged();
        mBarChart.notifyDataSetChanged();
        mBarChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        LogUtils.e("onValueSelected");
    }

    @Override
    public void onNothingSelected() {
        LogUtils.e("onNothingSelected");
    }
}
