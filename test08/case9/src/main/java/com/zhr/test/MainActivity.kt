package com.zhr.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 测试切换系统主题功能 以及调用recreate方法会走onSavedInstanceState方法和onRestoreInstanceState方法
 * 测试无障碍服务功能 AccessibilityService
 * 测试 TableLayout 和Gridlayout 进行复杂表格的制作  使用起来感觉GridLayout比Tablelayout更灵活 好用
 */
class MainActivity : AppCompatActivity() {

    private var isClicked: Boolean = true
    private var currentTheme: Int = 0
    private var TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (null != savedInstanceState) {
            currentTheme = savedInstanceState.getInt("currentTheme")
            isClicked = savedInstanceState.getBoolean("isClicked")
        }
        if (currentTheme == 0) {
            setTheme(R.style.Theme1)
        } else {
            setTheme(currentTheme)
        }
        Log.i(TAG, "onCreate1: $currentTheme")
        setContentView(R.layout.activity_main)

        // 如果要findViewById 需要在module的gradle中添加  id 'kotlin-android-extensions'
        tv.setOnClickListener {
            if (isClicked) {
                currentTheme = R.style.Theme2
                isClicked = false
            } else {
                currentTheme = R.style.Theme1
                isClicked = true
            }
            Log.i(TAG, "onCreate2: $currentTheme")
            recreate()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onCreate3: $currentTheme")
        outState.putInt("currentTheme", currentTheme)
        outState.putBoolean("isClicked", isClicked)
    }
}