package com.zhr.test

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

/**
 * 案例九：setTheme切换系统主题功能、recreate()的特性了解
 * 测试切换系统主题功能 以及调用recreate方法会走onSavedInstanceState方法和onRestoreInstanceState方法
 * 测试无障碍服务功能 AccessibilityService
 * 测试 TableLayout 和Gridlayout 进行复杂表格的制作  使用起来感觉GridLayout比Tablelayout更灵活 好用 todo 再来一种方法实现
 */
class MainActivity : AppCompatActivity() {

    private var isClicked: Boolean = true
    private var currentTheme: Int = 0
    private var TAG: String = "MainActivity"
    private var apkPath: String? = null

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
        tv1.setOnClickListener {
            if (isClicked) {
                currentTheme = R.style.Theme2
                isClicked = false
            } else {
                currentTheme = R.style.Theme1
                isClicked = true
            }
            Log.i(TAG, "onCreate2: $currentTheme")
            recreate()
            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        }
        tv2.setOnClickListener {
            getApkPath()
            install()
        }
    }

    private fun getApkPath() {
        val zhumeiTestFile =
            File(Environment.getExternalStorageDirectory().absolutePath + File.separator + "zhumei_test")
        if (!zhumeiTestFile.exists()) {
            zhumeiTestFile.mkdir()
        } else {
            Log.e(TAG, "the zhumei folder already exists!")
        }
        apkPath = zhumeiTestFile.absolutePath + "/zm.apk"
        Log.i(TAG, "getApkPath: $apkPath")
    }

    private fun install() {
        val intent = Intent()
        // android 7.0以上 7.0 ~ 8
        val sdkInt = Build.VERSION.SDK_INT
        if (sdkInt >= Build.VERSION_CODES.N && sdkInt < Build.VERSION_CODES.P) {
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            val contentUri =
                FileProvider.getUriForFile(this, "com.zhr.test.fileprovider", File(apkPath))
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive")


            // 查询所有符合 intent 跳转目标应用类型的应用，注意此方法必须放置在 setDataAndType 方法之后

            // 查询所有符合 intent 跳转目标应用类型的应用，注意此方法必须放置在 setDataAndType 方法之后
//            MyApplication myApplication = MyApplication.getMyApplication();
            val resolveLists =
                packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
            // 然后全部授权
            for (resolveInfo in resolveLists) {
                val packageName = resolveInfo.activityInfo.packageName
                grantUriPermission(packageName,
                    contentUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            }
            intent.action = Intent.ACTION_VIEW
            startActivity(intent)
        } else {
            val uri = Uri.fromFile(File(apkPath))
            val localIntent = Intent(Intent.ACTION_VIEW)
            localIntent.setDataAndType(uri, "application/vnd.android.package-archive")
            startActivity(localIntent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onCreate3: $currentTheme")
        outState.putInt("currentTheme", currentTheme)
        outState.putBoolean("isClicked", isClicked)
    }
}