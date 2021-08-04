package com.zhr.test

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.storage.StorageManager
import com.blankj.utilcode.util.CacheDoubleUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ObjectUtils
import com.blankj.utilcode.util.ToastUtils
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.lang.reflect.Method
import java.util.*


class UsbBroadcastReceiver : BroadcastReceiver() {

    private lateinit var mStorageManager: StorageManager
    private val mLists: ArrayList<String> = ArrayList()

    override fun onReceive(context: Context?, intent: Intent?) {
        mStorageManager = context!!.getSystemService(Activity.STORAGE_SERVICE) as StorageManager
        val action = intent?.action
        val uri = intent?.data
        val dataString = intent?.dataString
        // 获取插入u盘的路径 每个广播响应的时候表示的内容不一样
        val mountPath = uri?.path
        if (Intent.ACTION_MEDIA_MOUNTED.equals(action)) {
            LogUtils.e("u盘插入")
            if (ObjectUtils.isNotEmpty(mountPath)) {
                LogUtils.e("mountPath1:::$mountPath")
                ToastUtils.showShort("mountPath1:::$mountPath")
                // 第一次必须要在应用启动的情况下，我们讲mountPath保存住，后面才能判断u盘是否正式挂载
                CacheDoubleUtils.getInstance().put("mountPath", mountPath)
                // 判断u盘是否挂载
                val mounted = isMounted(mountPath)
                LogUtils.e("mounted1:::$mounted")
                ToastUtils.showShort("mounted1:::$mounted")
            }
            // 打印出来的是u盘的名字和u盘的路径 这里的路径和从Intent中获取到的路径是一样的
            getName()
            val file = File(mountPath!!)
            if (file.exists() && file.isDirectory) {
                // 在U盘根目录下找文件s
                val files = file.list()
                LogUtils.e("files:::${files?.toString()}")
                files?.let {
                    for (file in it) {
                        if (isMovieSuffix(file)){
                            mLists.add(file)
                        }
                    }
                }
                if(mLists.size > 0){
                    LogUtils.e("mLists:::$mLists")
                    context.startActivity(Intent(context, VBarChartActivity::class.java))
                }
            }
        } else if (Intent.ACTION_MEDIA_UNMOUNTED.equals(action)) {
            LogUtils.e("u盘拔出")
            val mountPath = CacheDoubleUtils.getInstance().getString("mountPath")
            LogUtils.e("mountPath2:::$mountPath")
            ToastUtils.showShort("mountPath2:::$mountPath")
            val mounted = isMounted(mountPath)
            LogUtils.e("mounted2:::$mounted")
            ToastUtils.showShort("mounted2:::$mounted")
            context.startActivity(Intent(context, PieChartActivity::class.java))
        } else if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            // let主要用于判空处理 表示 如果context不为null
            intent.setClass(context, MainActivity::class.java)
            context.startActivity(intent)
            LogUtils.e("开机自启动")
            val mountPath: String? = CacheDoubleUtils.getInstance().getString("mountPath")
            LogUtils.e("mountPath3:::$mountPath")
            ToastUtils.showShort("mountPath3:::$mountPath")
            val mounted = isMounted(mountPath)
            LogUtils.e("mounted3:::$mounted")
            ToastUtils.showShort("mounted3:::$mounted")
            // 打印出来的是u盘的名字和u盘的路径 这里的路径和从Intent中获取到的路径是一样的
            getName()
        } else if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
            LogUtils.e("应用程序被卸载")
            LogUtils.e("包名:::$dataString")
        }
    }

    // 根据u盘挂载的路径去判断 是否真实挂载
    private fun isMounted(mountPath: String?): Boolean {
        var blnRet = false
        var strLine: String? = null
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(FileReader("/proc/mounts"))
            while (reader.readLine().also { strLine = it } != null) {
                if (strLine?.contains(mountPath.toString())!!) {
                    blnRet = true
                    break
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                reader = null
            }
        }
        return blnRet
    }

    // 打印出来的是u盘的名字和u盘的路径 这里的路径和从Intent中获取到的路径是一样的
    private fun getName() {
        var volumeInfoClazz: Class<*>? = null
        var getDescriptionComparator: Method? = null
        var getBestVolumeDescription: Method? = null
        var getVolumes: Method? = null
        var isMountedReadable: Method? = null
        var getType: Method? = null
        var getPath: Method? = null
        var volumes: List<*>? = null
        try {
            volumeInfoClazz = Class.forName("android.os.storage.VolumeInfo")
            getDescriptionComparator = volumeInfoClazz.getMethod("getDescriptionComparator")
            getBestVolumeDescription =
                StorageManager::class.java.getMethod("getBestVolumeDescription", volumeInfoClazz)
            getVolumes = StorageManager::class.java.getMethod("getVolumes")
            isMountedReadable = volumeInfoClazz.getMethod("isMountedReadable")
            getType = volumeInfoClazz.getMethod("getType")
            getPath = volumeInfoClazz.getMethod("getPath")
            volumes = getVolumes.invoke(mStorageManager) as List<*>?
            for (vol in volumes!!) {
                if (vol != null && isMountedReadable.invoke(vol) as Boolean && getType.invoke(vol) as Int == 0) {
                    val path2: File = getPath.invoke(vol) as File
                    val p2: String = path2.getPath()
                    LogUtils.e("-----------path1-----------------${
                        getBestVolumeDescription.invoke(mStorageManager,
                            vol)
                    }") //打印U盘卷标名称
                    LogUtils.e("-----------path2 @@@@@-----------------$p2") //打印U盘路径
                }
            }
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }
    }

    private fun isMovieSuffix(fileName: String): Boolean {
        //判断是否是视频文件
        val name = fileName.lowercase(Locale.getDefault())
        val suffixs = arrayListOf(".rmvb", ".mp4", ".avi", "png", "jpg", "jpeg")
        for (string in suffixs) {
            if (name.endsWith(string)) {
                return true
            }
        }
        return false
    }
}