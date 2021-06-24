package com.zhr.test;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import java.util.List;

/**
 * 饼状图显示圆形圆环的的管理类
 */
public class PieChartManager {

    private static final String TAG = "PieChartManager";
    private PieChart mPieChart;

    public PieChartManager(PieChart pieChart) {
        this.mPieChart = pieChart;
        initPieChart();
    }

    /**
     * 初始化
     */
    private void initPieChart() {
        /***********************通过偏移量控制大小**********************/
        mPieChart.setExtraOffsets(0, 0, 0, 10);
        /***********************通过偏移量控制大小***********************/

        /***********************背景颜色**********************/
        mPieChart.setBackgroundColor(Color.TRANSPARENT);
        /***********************背景颜色***********************/

        /***********************默认数据***********************/
        mPieChart.setNoDataText("暂无数据哦");
        mPieChart.setNoDataTextColor(Color.BLACK);
        mPieChart.setNoDataTextTypeface(Typeface.DEFAULT_BOLD);
        /***********************默认数据***********************/

        /***********************百分值***********************/
        // 设置是否使用百分比展示
        mPieChart.setUsePercentValues(true);
        // 设置饼图的最大角度 [90 , 360) 默认360
        mPieChart.setMaxAngle(360);
        /***********************百分值***********************/

        /***********************大标题描述***********************/
        // 设置是否显示大标题描述
        mPieChart.getDescription().setEnabled(false);
        Description description = mPieChart.getDescription();
        String descriptionText = "青菜对比图";
        description.setText(descriptionText);
        description.setTextSize(25f);
        description.setTextAlign(Paint.Align.CENTER);
        description.setTextColor(Color.BLACK);
        mPieChart.setDescription(description);
        /***********************大标题描述***********************/

        /***********************条目标签***********************/
        // 设置饼图是否只显示数字或者显示数字和文字(true 显示数字和文字 / false 只显示数字)
        mPieChart.setDrawEntryLabels(true);
        // 设置饼图文字颜色
        mPieChart.setEntryLabelColor(Color.BLACK);
        // 设置饼图文字大小
        mPieChart.setEntryLabelTextSize(15f);
        // 设置点击饼图是否放大（为否时 设置放大大小也无效）
        mPieChart.setHighlightPerTapEnabled(true);
        // 设置饼图文字字体样式
        mPieChart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD);
        // 判断饼图是否只显示数字或者显示数字和文字(true 显示数字和文字 / false 只显示数字)
        Log.i(TAG , "isDrawEntryLabelsEnabled: " + mPieChart.isDrawEntryLabelsEnabled());
        /***********************条目标签***********************/

        /***********************转动动画***********************/
        // 设置转动阻力摩擦系数[0,1] （值越小，转动流畅度越低）
        mPieChart.setDragDecelerationFrictionCoef(0.95f);
        // 设置旋转开始角度
        mPieChart.setRotationAngle(0f);
        // 设置是否可以手动旋转
        mPieChart.setRotationEnabled(false);
        // 设置旋转转动动画
//        mPieChart.animateY(5000, Easing.EaseInOutQuad);
        /***********************转动动画***********************/

        /***********************图例***********************/
        Legend legend = mPieChart.getLegend();
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

        /***********************中间文本***********************/
        // 设置是否显示中间文本
        mPieChart.setDrawCenterText(false);
        // 设置中间文本内容
        mPieChart.setCenterText("中间文本");
        // 设置中间文本颜色
        mPieChart.setCenterTextColor(Color.BLACK);
        // 设置中间文本大小
        mPieChart.setCenterTextSize(15f);
        // 设置中间文本偏移
        mPieChart.setCenterTextOffset(5 , 5);
        // 设置中间文本字体样式
        mPieChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD);
        // 设置中间文本半径与中心圆半径的百分比，默认100%
        mPieChart.setCenterTextRadiusPercent(10f);
        // 获取中间文本内容
        Log.i(TAG, "getCenterText: " + mPieChart.getCenterText());
        /***********************中间文本***********************/
    }

    public void showPieChart(List<PieEntry> pieEntry, List<Integer> pieColor) {
        /***********************中心圈***********************/
        // 设置是否显示中心圈（true 圆环 / false 圆饼）
        mPieChart.setDrawHoleEnabled(false);
        // 设置中心圈颜色
        mPieChart.setHoleColor(Color.TRANSPARENT);
        // 设置透明圆弧里面是否显示中心圆（true 显示 / false 不显示）
        mPieChart.setDrawSlicesUnderHole(false);
        // 设置中心圆半径（为55时中心弧和饼图半径重合）
        mPieChart.setHoleRadius(50f);
        // 设置中心圆半径占饼图半径百分比，默认为55%
        mPieChart.setTransparentCircleRadius(55f);
        // 设置透明圆弧颜色，默认为白色
        mPieChart.setTransparentCircleColor(Color.WHITE);
        // 设置中心圆透明度, 默认为105
        mPieChart.setTransparentCircleAlpha(105);
        // 获取中心圆半径占饼图半径百分比，默认为55%
        Log.i(TAG, "getTransparentCircleRadius: " + mPieChart.getTransparentCircleRadius());
        /***********************中心圆***********************/

        /***********************数字数据集***********************/
        PieDataSet dataSet = new PieDataSet(pieEntry, "菜品名称");
        // 设置填充每个区域的颜色
        dataSet.setColors(pieColor);
        // 设置数字大小
        dataSet.setValueTextSize(15f);
        // 设置数字颜色
        dataSet.setValueTextColor(Color.BLACK);
        // 设置文字的位置是在圆内还是圆外（在圆外才有指示线，在圆内没有指示线）
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        // 设置数字位置是在圆内还是圆外（在圆外才有指示线，在圆内没有指示线）
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        // 设置数字指示线的颜色
        dataSet.setValueLineColor(Color.BLACK);
        // 设置数字字体样式
        dataSet.setValueTypeface(Typeface.DEFAULT_BOLD);
        // 设置数字指示线前半段和后半段的比例（100则为不显示前半段，只显示后半段）
        dataSet.setValueLinePart1OffsetPercentage(85f);
        // 设置数字指示线的前半段长度
        dataSet.setValueLinePart1Length(0.5f);
        // 设置数字指示线的后半段长度
        dataSet.setValueLinePart2Length(0.5f);
        // 设置饼块之间的间隔
        dataSet.setSliceSpace(5f);
        // 设置饼状被选中时变大的距离（可以设置为负值）
        dataSet.setSelectionShift(5f);
        // 格式化百分比数据
        dataSet.setValueFormatter(new PercentFormatter(mPieChart));
        /***********************数字数据集***********************/
        PieData pieData = new PieData(dataSet);
        mPieChart.setData(pieData);
        mPieChart.invalidate();
    }
}
