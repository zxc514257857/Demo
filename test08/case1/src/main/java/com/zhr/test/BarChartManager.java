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
    private BarChart mBar1Chart;
    private IBarDataSet mDataSet;
    private Context mContext;

    public BarChartManager(BarChart bar1Chart , IBarDataSet dataSet , Context context) {
        this.mBar1Chart = bar1Chart;
        this.mDataSet = dataSet;
        this.mContext = context;
    }

    public void initBar1Chart() {
        /***********************通过偏移量控制大小**********************/
        mBar1Chart.setExtraOffsets(0, 0, 0, 10);
        /***********************通过偏移量控制大小***********************/

        /***********************常规设置**********************/
        // 设置控件背景颜色
        mBar1Chart.setBackgroundColor(Color.WHITE);
        // 设置关闭条目阴影
        mBar1Chart.setDrawBarShadow(false);
        // 设置文字显示在条目上或条目中
        mBar1Chart.setDrawValueAboveBar(true);
        // 设置最大可见绘制的chartcount的数量 60个，barData.setDrawValues 设置为true时生效
        mBar1Chart.setMaxVisibleValueCount(60);
        // 设置显示网格线背景颜色
        mBar1Chart.setDrawGridBackground(false);
        // 设置网格线背景颜色
        mBar1Chart.setGridBackgroundColor(Color.RED);
        // 设置条目被选中以及未被选中的监听
        mBar1Chart.setOnChartValueSelectedListener(this);
        /***********************背景颜色***********************/

        /***********************缩放控制**********************/
        // 关闭缩放
        mBar1Chart.setScaleEnabled(false);
        // 设置缩放可在x轴y轴同时进行
        mBar1Chart.setPinchZoom(true);
        /***********************缩放控制**********************/

        /***********************默认数据***********************/
        mBar1Chart.setNoDataText("暂无数据哦");
        mBar1Chart.setNoDataTextColor(Color.BLACK);
        mBar1Chart.setNoDataTextTypeface(Typeface.DEFAULT_BOLD);
        /***********************默认数据***********************/

        /***********************大标题描述***********************/
        // 设置是否显示大标题描述
        mBar1Chart.getDescription().setEnabled(true);
        Description description = mBar1Chart.getDescription();
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
        mBar1Chart.setDescription(description);
//        mBar1Chart.setHighlighter();
        /***********************大标题描述***********************/

        /***********************条目及标签***********************/

        // 获取x轴
        XAxis xAxis = mBar1Chart.getXAxis();
        // 设置x轴位于底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 设置x轴文字居中
        xAxis.setCenterAxisLabels(true);
        // 设置取消垂直网格线
        xAxis.setDrawGridLines(false);
        // 设置垂直网格线为虚线（线长度为10，空格长度为10，虚线出发点（从第一根虚线的哪里出发））
        xAxis.enableGridDashedLine(10 , 10 , 0);
        // 设置x轴文字旋转角度
        xAxis.setLabelRotationAngle(20f);
        // 设置x轴文字大小
        xAxis.setTextSize(20f);
        // 设置x轴文字刻度数量
        xAxis.setLabelCount(10);
        // 设置是否显示x轴（如不显示，setAxisLineWidth 和 setAxisLineColor 功能无效）
        xAxis.setDrawAxisLine(false);
        // 设置x轴坐标轴厚度
        xAxis.setAxisLineWidth(5f);
        // 设置x轴坐标轴厚度颜色
        xAxis.setAxisLineColor(Color.RED);
        // 设置x轴粒度（文字间隔）
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
        YAxis yLAxis = mBar1Chart.getAxisLeft();
        // 设置y轴左侧文字位置位于表格内部
        yLAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        // 设置取消y轴左侧水平网格线
        yLAxis.setDrawGridLines(false);
        // 设置是否显示y轴左侧（如不显示，setAxisLineWidth 和 setAxisLineColor 功能无效）
        yLAxis.setDrawAxisLine(false);
        // 设置y轴左侧文字刻度数量
        yLAxis.setLabelCount(10);
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
        YAxis yRAxis = mBar1Chart.getAxisRight();
        // 设置y轴右侧文字位置位于表格内部
        yRAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        // 设置y轴右侧无效
        yRAxis.setEnabled(true);
        // 设置取消y轴右侧水平网格线
        yRAxis.setDrawGridLines(false);
        // 设置y轴右侧网格线颜色
        yRAxis.setGridColor(Color.BLUE);
        // 设置取消y轴右侧文字
        yRAxis.setDrawLabels(true);
        // 设置y轴右侧文字大小
        yRAxis.setTextSize(15f);
        // 设置y轴右侧文字颜色
        yRAxis.setTextColor(Color.YELLOW);
        // 设置y轴右侧文字刻度数量 以及是否精确设置
        yRAxis.setLabelCount(10 , true);
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
        // 设置y轴右侧数字反转
        yRAxis.setInverted(true);
        /***********************条目标签***********************/

        /***********************图例***********************/
        Legend legend = mBar1Chart.getLegend();
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
        mBar1Chart.setData(barData);
        // 更新数据
        barData.notifyDataChanged();
        mBar1Chart.notifyDataSetChanged();
        mBar1Chart.invalidate();
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
