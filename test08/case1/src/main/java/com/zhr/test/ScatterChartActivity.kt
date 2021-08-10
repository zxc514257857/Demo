package com.zhr.test

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.ScatterDataSet
import kotlinx.android.synthetic.main.activity_scatter.*

class ScatterChartActivity : AppCompatActivity() {

    private var mScatterEntry: ArrayList<Entry>? = null
    private var mScatterDataSet: ScatterDataSet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scatter)
        initData()
    }

    private fun initData() {
        initScatterEntry()
        initScatterDataSet()
        initScatterChartManager()
    }

    private fun initScatterEntry() {
        mScatterEntry = arrayListOf()
        // 第一列为横坐标，第二列为纵坐标
        mScatterEntry?.add(Entry(120f, 150f))
        mScatterEntry?.add(Entry(140f, 140f))
        mScatterEntry?.add(Entry(160f, 140f))
        mScatterEntry?.add(Entry(180f, 145f))
        mScatterEntry?.add(Entry(250f, 150f))
    }

    private fun initScatterDataSet() {
        mScatterDataSet = ScatterDataSet(mScatterEntry, "我是图例")
        mScatterDataSet?.valueTextColor = Color.YELLOW
        mScatterDataSet?.color = Color.GREEN
        mScatterDataSet?.valueTextSize = 14f
    }

    private fun initScatterChartManager() {
        val scatterChartManager = ScatterChartManager(scatterChart, mScatterDataSet)
        scatterChartManager.initScatterChart()
    }
}