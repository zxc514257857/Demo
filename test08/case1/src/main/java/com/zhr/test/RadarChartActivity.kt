package com.zhr.test

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import kotlinx.android.synthetic.main.activity_radar.*

class RadarChartActivity : AppCompatActivity() {

    private var mRadarEntry: ArrayList<RadarEntry>? = null
    private var mRadarDataSet: RadarDataSet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radar)
        initData()
    }

    private fun initData() {
        initRadarEntry()
        initRadarDataSet()
        iniRadarChartManager()
    }

    private fun initRadarEntry() {
        // 设置雷达图的坐标点数据
        mRadarEntry = arrayListOf()
        mRadarEntry?.add(RadarEntry(20f))
        mRadarEntry?.add(RadarEntry(10f))
        mRadarEntry?.add(RadarEntry(30f))
        mRadarEntry?.add(RadarEntry(30f))
        mRadarEntry?.add(RadarEntry(30f))
    }

    private fun initRadarDataSet() {
        // 设置图例及雷达图的属性数据
        mRadarDataSet = RadarDataSet(mRadarEntry, "我是图例")
        mRadarDataSet?.valueTextColor = Color.YELLOW
        mRadarDataSet?.color = Color.GREEN
        mRadarDataSet?.valueTextSize = 14f
        // 设置是否显示雷达图的值 默认为true 显示
        mRadarDataSet?.setDrawValues(false)
        // 设置雷达图区域是否填充  默认为false  不填充
        mRadarDataSet?.setDrawFilled(true)
        // 设置雷达图区域填充的颜色
        mRadarDataSet?.fillColor = Color.BLUE
        // 设置雷达图区域填充的透明度
        mRadarDataSet?.fillAlpha = 40

        // 设置点击雷达图之后 是否高亮圈出对应的坐标 默认为true
        mRadarDataSet?.isDrawHighlightCircleEnabled = true
        // 设置点击雷达图之后 显示出的高亮圆圈外围的颜色
        mRadarDataSet?.highlightCircleFillColor = Color.RED
        // 设置点击雷达图之后 显示出的高亮圆圈外围的透明度
        mRadarDataSet?.highlightCircleStrokeAlpha = 40
        // 设置点击雷达图之后 显示出的高亮圆圈外围的半径
        mRadarDataSet?.highlightCircleInnerRadius = 10f
        // 设置点击雷达图之后 显示出的高亮圆圈外围内圆的半径
        mRadarDataSet?.highlightCircleOuterRadius = 5f
    }

    private fun iniRadarChartManager() {
        val radarChartManager = RadarChartManager(radarChart, mRadarDataSet)
        radarChartManager.initRadarChart()
    }
}