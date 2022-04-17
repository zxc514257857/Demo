package com.zhr.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.motion5.*

/**
 * MotionLayout的使用：
 * https://www.cnblogs.com/moosphon/p/11565826.html
 * chrome://bookmarks/?id=5435
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 同一个MotionLayout 不同的ConstraintSet的切换 切换的是布局id
//        setContentView(R.layout.activity_main8)
        // 不同的MotionLayout 同一个Scene的切换 切换的是layout
//        setContentView(R.layout.motion1)
        // 同一个MotionLayout 不同的ConstraintSet的  联动布局  切换 切换的是布局id
//        setContentView(R.layout.motion3)
        // 不同的MotionLayout 同一个Scene的切换  稍微复杂的天女散花效果 使用了xml文件中的自定义属性 修改了背景颜色和文字颜色
//        setContentView(R.layout.motion5)
        // ImageFilterView 图片的背景色渐变  最多设置两条自定义属性 多了就会报错
//        setContentView(R.layout.motion6)
        // 位置和属性关键帧学习
        setContentView(R.layout.motion7)
    }
}
