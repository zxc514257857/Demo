package com.zhr.test

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.ScatterData
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet

class ScatterChartManager {

    private val TAG: String = "ScatterChartManager"
    private var mScatterChart: ScatterChart? = null
    private var mScatterDataSet: IScatterDataSet? = null

    constructor(scatterChart: ScatterChart?, scatterDataSet: IScatterDataSet?) {
        this.mScatterChart = scatterChart
        this.mScatterDataSet = scatterDataSet
    }

    fun initScatterChart() {
        /***********************通过偏移量控制大小**********************/
        mScatterChart?.setExtraOffsets(0f, 0f, 0f, 10f)
        /***********************通过偏移量控制大小***********************/

        /***********************背景颜色**********************/
        // 设置控件背景颜色
        mScatterChart?.setBackgroundColor(Color.WHITE)
        // 设置最大可见绘制的chartcount的数量 60个，barData.setDrawValues 设置为true时生效
        mScatterChart?.setMaxVisibleValueCount(60)
        // 设置显示网格线背景颜色
        mScatterChart?.setDrawGridBackground(false)
        // 设置网格线背景颜色
        mScatterChart?.setGridBackgroundColor(Color.RED)
        /***********************背景颜色***********************/

        /***********************缩放控制**********************/
        // 设置是否可触摸 默认为true
        mScatterChart?.setTouchEnabled(true)
        // 设置是否可拖拽 默认为true
        mScatterChart?.isDragEnabled = true
        // 设置是否可以缩放x轴和y轴，默认为true 可以缩放
        mScatterChart?.setScaleEnabled(false)
        // 设置是否可以缩放x轴 默认为true
        mScatterChart?.isScaleXEnabled = true
        // 设置是否可以缩放y轴 默认为true
        mScatterChart?.isScaleYEnabled = true
        // 设置x轴和y轴能否同时缩放 默认为false
        mScatterChart?.setPinchZoom(true)
        // 设置是否能通过双击屏幕放大图表 默认为true
        mScatterChart?.isDoubleTapToZoomEnabled = true
        // 设置能否拖拽高亮线(数据点和坐标的提示线) 默认为true
        mScatterChart?.isHighlightPerDragEnabled = true
        // 设置拖拽滚动时，手放开是否会持续滚动 默认为true有持续效果 false会立即停止
        mScatterChart?.isDragDecelerationEnabled = true
        // 设置持续滚动的速度快慢 0代表立即停止 1代表持续滚动速度快
        mScatterChart?.dragDecelerationFrictionCoef = 0.5f
        /***********************缩放控制**********************/

        /***********************默认数据***********************/
        mScatterChart?.setNoDataText("暂无数据哦")
        mScatterChart?.setNoDataTextColor(Color.BLACK)
        mScatterChart?.setNoDataTextTypeface(Typeface.DEFAULT_BOLD)
        /***********************默认数据***********************/

        /***********************大标题描述***********************/
        // 设置是否显示大标题描述 默认显示
        mScatterChart?.description?.isEnabled = true
        val description = mScatterChart?.description
        val descriptionText = "青菜对比图"
        description?.text = descriptionText
        description?.textSize = 25f
        description?.textAlign = Paint.Align.RIGHT
        description?.textColor = Color.BLACK
        mScatterChart?.description = description
        /***********************大标题描述***********************/

        /***********************x轴y轴属性***********************/
        // 获取x轴
        val xAxis: XAxis? = mScatterChart?.xAxis
        // 设置x轴是否启用 默认启用 如禁用 后面的代码不生效
        xAxis?.isEnabled = true
        // 设置x轴文字位于图表的顶部还是底部 里侧还是外侧 默认位于顶部
        xAxis?.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        // 设置x轴文字是否显示 默认为true 显示
        xAxis?.setDrawLabels(true)
        // 设置x轴文字是否居中显示 默认为false 居左显示
        xAxis?.setCenterAxisLabels(false)
        // 设置垂直网格线是否显示 默认为true
        xAxis?.setDrawGridLines(true)
        // 设置垂直网格线为虚线（线长度为10，空格长度为10，虚线出发点（从第一根虚线的哪里出发））
        xAxis?.enableGridDashedLine(10f, 10f, 0f)
        // 设置x轴文字旋转角度
        xAxis?.labelRotationAngle = 30f
        // 设置x轴文字大小
        xAxis?.textSize = 10f
        // 设置x轴文字颜色
        xAxis?.textColor = Color.RED
        // 设置x轴刻度数量（这个是大致值，不是精确值）
        xAxis?.labelCount = 10
        // 设置是否显示对x轴坐标轴的设置（一般不需要显示。如不显示，setAxisLineWidth 和 setAxisLineColor 功能无效）
        xAxis?.setDrawAxisLine(false)
        // 设置x轴 坐标轴的高度
        xAxis?.axisLineWidth = 10f
        // 设置x轴 坐标轴的高度颜色
        xAxis?.axisLineColor = Color.RED
        // 设置x轴粒度（文字间隔） 为true时下方的设置生效，为false不生效
        xAxis?.isGranularityEnabled = false
        // 设置x轴值的最小间隔，防止当放大时，出现重复标签
        xAxis?.granularity = 2f
        // 图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis?.setAvoidFirstLastClipping(true)
        // 设置x轴文字格式化（将数字转为字符串）
        xAxis?.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "￥$value"
            }
        }
        // 设置x轴文字字体
        xAxis?.typeface = Typeface.DEFAULT_BOLD
        // 设置x轴开始值
        xAxis?.axisMinimum = 100f
        // 设置x轴结束值
        xAxis?.axisMaximum = 300f
        // 设置x轴顶部间距（设置之后未发现效果）
        xAxis?.spaceMin = 30f
        // 设置x轴底部间距（设置之后未发现效果）
        xAxis?.spaceMax = 100f
        // 设置x轴网格线颜色
        xAxis?.gridColor = Color.BLUE

        // 获取左侧y轴
        val yLAxis: YAxis? = mScatterChart?.axisLeft
        // 设置左侧y轴是否显示 默认为true
        yLAxis?.isEnabled = true
        // 设置左侧y轴文字位于图表的里侧还是外侧 默认位于外侧
        yLAxis?.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        // 设置左侧y轴文字是否显示 默认为true 显示
        yLAxis?.setDrawLabels(true)
        // 设置左侧y轴文字是否居中显示 默认为false 居下显示（改成true无效果）
        yLAxis?.setCenterAxisLabels(false)
        // 设置水平网格线是否显示 默认为true （改成false无效果，需要左右y轴都修改才生效）
        yLAxis?.setDrawGridLines(true)
        // 设置水平网格线为虚线（线长度为10，空格长度为10，虚线出发点（从第一根虚线的哪里出发））
        yLAxis?.enableGridDashedLine(10f, 10f, 0f)
        // 设置左侧y轴文字大小
        yLAxis?.textSize = 10f
        // 设置左侧y轴文字颜色
        yLAxis?.textColor = Color.BLUE
        // 设置左侧y轴刻度数量（这个是大致值，不是精确值）
        yLAxis?.labelCount = 8
        // 设置是否显示对x轴坐标轴的设置（一般不需要显示。如不显示，setAxisLineWidth 和 setAxisLineColor 功能无效）
        yLAxis?.setDrawAxisLine(false)
        // 设置左侧y轴 坐标轴的高度
        yLAxis?.axisLineWidth = 10f
        // 设置左侧y轴 坐标轴的高度颜色
        yLAxis?.axisLineColor = Color.RED
        // 设置左侧y轴粒度（文字间隔） 为true时下方的设置生效，为false不生效
        yLAxis?.isGranularityEnabled = false
        // 设置左侧y轴值的最小间隔，防止当放大时，出现重复标签
        yLAxis?.granularity = 2f
        // 设置y轴右侧显示零线（零线的设置没有生效，不知道什么原因）
        yLAxis?.setDrawZeroLine(true)
        // 设置y轴右侧零线厚度
        yLAxis?.zeroLineWidth = 100f
        // 设置y轴右侧零线颜色
        yLAxis?.zeroLineColor = Color.GREEN
        // 设置y轴右侧数字反转  之前是 200-1000 设置之后就是1000-200了
        yLAxis?.isInverted = false

        // 获取右侧y轴
        val yRAxis: YAxis? = mScatterChart?.axisRight
        // 设置右侧y轴是否显示 默认为true
        yRAxis?.isEnabled = false
        // 设置水平网格线是否显示 默认为true
        yRAxis?.setDrawGridLines(false)
        /***********************x轴y轴属性***********************/

        /***********************图例***********************/
        val legend: Legend? = mScatterChart?.legend
        // 设置是否启用图例
        legend?.isEnabled = true
        // 设置是否画在图表里
        legend?.setDrawInside(false)
        // 设置图例显示的位置
        legend?.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend?.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        // 设置图例显示的方向
        legend?.orientation = Legend.LegendOrientation.HORIZONTAL
        // 设置图例文字大小
        legend?.textSize = 12f
        // 设置图例文字颜色
        legend?.textColor = Color.BLACK
        // 设置图例水平间距
        legend?.xEntrySpace = 0f
        // 设置图例垂直间距
        legend?.xEntrySpace = 20f
        // 设置图例图形样式
        legend?.form = Legend.LegendForm.CIRCLE
        // 设置图例图形大小
        legend?.formSize = 15f
        // 设置图例图形和文字的间距
        legend?.formToTextSpace = 10f
        /***********************图例***********************/

        val scatterData = ScatterData(mScatterDataSet)
        /***********************条目***********************/
        // 设置条目是否显示数值
        scatterData.setDrawValues(true)

        /**
         * 修改条目点击颜色：
         * 再到 BarChartRenderer.java 类
         * mHighlightPaint.setColor(Color.rgb(0, 0, 0)); 颜色修改
         * 找到 drawHighlighted 方法
         * mHighlightPaint.setColor(set.getHighLightColor()); 颜色修改
         * 然后注释掉这句话: mHighlightPaint.setAlpha(set.getHighLightAlpha());
         */

        /***********************条目***********************/

        /***********************设置并更新数据***********************/
        mScatterChart?.data = scatterData
        scatterData.notifyDataChanged()
        mScatterChart?.notifyDataSetChanged()
        mScatterChart?.invalidate()
        /***********************设置并更新数据***********************/
    }
}