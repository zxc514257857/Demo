package com.zhr.test

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent

/**
 * AccessibilityService 就是无障碍服务即 辅助功能服务
 *
 * AccessibilityService 的本质还是一个服务，所以需要在AndroidManifest文件中注册服务
 * AccessibilituService 只能由用户在设置中手动打开
 * 1、在无障碍 - 服务中显示服务列表 ： 创建一个AccessibilityService类，在androidManifest文件中注册这个Service
 * 2、在开启列表中显示的描述内容 在xml文件中设置的
 * 3、打开开启开关弹出的 检测内容确认通知
 * 4、写AccessibilityService类里面的内容
 */
class TestAccessibilityService : AccessibilityService() {

    private val TAG: String = "TestAccessibilityService"

    // 接收到系统发送 AccessibilityEvent时的回调  核心方法
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.e(TAG, "onAccessibilityEvent: ")
        processAccessibilityEvent(event)
    }

    // 当服务被中断时的回调
    override fun onInterrupt() {
        Log.e(TAG, "onInterrupt: ")
    }

    private fun processAccessibilityEvent(event: AccessibilityEvent?) {
        // 找到安装器的包名
        if("com.android.packageinstaller" == event!!.packageName){
            val installNode = event.source.findAccessibilityNodeInfosByText("安装")
            for(i in installNode.indices){
                Log.e(TAG, "install: ${installNode[i]}")
            }
        }
    }
}