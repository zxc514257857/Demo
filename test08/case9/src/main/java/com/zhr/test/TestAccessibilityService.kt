package com.zhr.test

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.accessibility.AccessibilityEvent

/**
 * AccessibilityService 就是无障碍服务即 辅助功能服务
 */
class TestAccessibilityService : AccessibilityService() {

    private val TAG: String = "TestAccessibilityService"

    // 接收到系统发送 AccessibilityEvent时的回调
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.e(TAG, "onAccessibilityEvent: ")
    }

    // 当服务被中断时的回调
    override fun onInterrupt() {
        Log.e(TAG, "onInterrupt: ")
    }

    // AccessibilityService 的本质还是一个服务，所以需要在AndroidManifest文件中注册服务
}