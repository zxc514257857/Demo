package com.zhr.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * activity_constraintlayout布局 写完之后 ConstraintLayout节点报错
 * The layout "activity_constraintlayout" in layout has no declaration in the base layout folder;
 * this can lead to crashes when the resource is queried in a configuration that does not match this qualifier
 * AndroidStudio重新退出再进入之后问题消除
 *
 * 垂直位置上没有进行约束 报错
 * This view is not constrained vertically: at runtime it will jump to the top unless you add a vertical constraint
 * xml文件中进行忽略即可，是故意不进行约束的
 *
 * 学习在xml文件中进行拖拽操作使用
 * constraintLayout v1.1.3版本是 1 版本的最后一个版本，之后就是2.0.0版本 2.0.0版本增加了motionlayout功能
 */
class ConstraintlayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_constraintlayout)
//        setContentView(R.layout.activity_constraint_demo)
        setContentView(R.layout.aa)
    }
}