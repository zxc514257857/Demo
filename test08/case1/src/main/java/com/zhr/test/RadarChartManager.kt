package com.zhr.test

import android.graphics.Color
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet

class RadarChartManager {

    private val TAG: String = "RadarChartManager"
    private var mRadarChart: RadarChart? = null
    private var mRadarDataSet: IRadarDataSet? = null
    private val colors = intArrayOf(Color.RED, Color.BLACK, Color.GREEN, Color.BLUE, Color.GRAY)

    constructor(radarChart: RadarChart?, radarDataSet: IRadarDataSet?) {
        this.mRadarChart = radarChart
        this.mRadarDataSet = radarDataSet
    }

    fun initRadarChart() {
        // 设置web线的颜色(即就是外面包着的那个颜色)
        mRadarChart?.webColorInner = Color.BLACK
        // 设置中心线颜色(也就是竖着的线条)
        mRadarChart?.webColor = Color.BLACK
        mRadarChart?.webAlpha = 50

        val xAxis = mRadarChart?.xAxis
        // 设置x轴标签字体颜色
        xAxis?.setLabelCount(4, true)
        xAxis?.axisMaximum = 4f
        xAxis?.axisMinimum = 0f
        xAxis?.textSize = 20f
        // 自定义x轴标签
        xAxis?.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                mRadarChart?.xAxis?.textColor = colors[Math.abs(value % 5).toInt()]
                return super.getFormattedValue(value)
            }
        }

        val yAxis = mRadarChart?.yAxis
        // 设置y轴的标签个数
        yAxis?.setLabelCount(5, true);
        // 设置y轴从0f开始
        yAxis?.axisMinimum = 0f;
        // 启用绘制Y轴顶点标签，这个是最新添加的功能
        yAxis?.setDrawTopYLabelEntry(false)
        //设置字体大小
        yAxis?.textSize = 15f
        //设置字体颜色
        yAxis?.textColor = Color.RED

        // 启用线条，如果禁用，则无任何线条
        mRadarChart?.setDrawWeb(true)
        // 禁用图例和图表描述
        mRadarChart?.description?.isEnabled = false
        mRadarChart?.legend?.isEnabled = false

        val data = RadarData(mRadarDataSet)
        mRadarChart?.data = data
        mRadarChart?.invalidate()
    }
}