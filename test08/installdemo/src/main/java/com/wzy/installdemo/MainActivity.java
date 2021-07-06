package com.wzy.installdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "[TAG][MainActivity]";
    //    private static final String PACKAGE_NAME = "com.qiyi.video";
    private static final String PACKAGE_NAME = "com.zhumei.commercialscreen";
    //    private String apkPath = "/mnt/sdcard/test.apk";
    private String apkPath = "";

    public static boolean flag = false;//控制只能自己的app才能执行智能安装
    private TextView tvTest;
    private MyInstallReceiver receiver;
    public String SET_ZHUMEI_COMMERCIAL_SCREEN_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + SHP.PRODUCT_SUMMARY;
    private InstallBroadCast installBroadCast;

    /**
     * SHP相关
     */
    public static class SHP {

        public static final String PRODUCT_NAME = "SHP";
        private static final String PRODUCT_SUMMARY = "zhumei_" + PRODUCT_NAME.toLowerCase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTest = (TextView) findViewById(R.id.tv_test);
        findViewById(R.id.btn_install).setOnClickListener(this);
        findViewById(R.id.btn_uninstall).setOnClickListener(this);
        findViewById(R.id.btn_set).setOnClickListener(this);
        findViewById(R.id.btn_smart_install).setOnClickListener(this);
        //注册apk安装监听
        receiver = new MyInstallReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PACKAGE_ADDED");
        filter.addAction("android.intent.action.PACKAGE_REMOVED");
        filter.addDataScheme("package");
        this.registerReceiver(receiver, filter);
        EventBus.getDefault().register(this);


         installBroadCast = new InstallBroadCast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("install");
        registerReceiver(installBroadCast,intentFilter);

        getApkPath();
    }

    @Subscribe()
    public void onEvent(Object event){

    }


    private void getApkPath() {
        File zhumeiShpFile = new File(SET_ZHUMEI_COMMERCIAL_SCREEN_PATH);
        if (!zhumeiShpFile.exists()) {
            zhumeiShpFile.mkdir();
        } else {
            Log.e(TAG, "the zhumei folder already exists!");
        }
        apkPath = zhumeiShpFile.getAbsolutePath() + "/zm.apk";
//        apkPath = zhumeiShpFile.getAbsolutePath();

        Log.e(TAG, apkPath);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //静默安装
            case R.id.btn_install:
                installSlient(apkPath);
                break;
            //静默卸载
            case R.id.btn_uninstall:
                uninstallSlient();
                break;
            //设置无障碍服务
            case R.id.btn_set:
                //跳转到开启无障碍服务的界面
                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
                break;
            //智能安装
            case R.id.btn_smart_install:
                //控制只能自己的app才能智能安装
                flag = true;
                smartInstall();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    //静默安装
    private void installSlient(String apkPath) {
//        String cmd = "pm install -r /mnt/sdcard/test.apk";
        String cmd = "pm install -r " + apkPath + "/zm.apk";

        Process process = null;
        DataOutputStream os = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = null;
        StringBuilder errorMsg = null;
        try {
            //静默安装需要root权限
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.write(cmd.getBytes());
            os.writeBytes("\n");
            os.writeBytes("exit\n");
            os.flush();
            //执行命令
            process.waitFor();
            //获取返回结果
            successMsg = new StringBuilder();
            errorMsg = new StringBuilder();
            successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String s;
            while ((s = successResult.readLine()) != null) {
                successMsg.append(s);
            }
            while ((s = errorResult.readLine()) != null) {
                errorMsg.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (process != null) {
                    process.destroy();
                }
                if (successResult != null) {
                    successResult.close();
                }
                if (errorResult != null) {
                    errorResult.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //显示结果
        tvTest.setText("成功消息：" + successMsg.toString() + "\n" + "错误消息: " + errorMsg.toString());
    }

    //静默卸载
    private void uninstallSlient() {
        String cmd = "pm uninstall " + PACKAGE_NAME;
        Process process = null;
        DataOutputStream os = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = null;
        StringBuilder errorMsg = null;
        try {
            //卸载也需要root权限
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.write(cmd.getBytes());
            os.writeBytes("\n");
            os.writeBytes("exit\n");
            os.flush();
            //执行命令
            process.waitFor();
            //获取返回结果
            successMsg = new StringBuilder();
            errorMsg = new StringBuilder();
            successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String s;
            while ((s = successResult.readLine()) != null) {
                successMsg.append(s);
            }
            while ((s = errorResult.readLine()) != null) {
                errorMsg.append(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (process != null) {
                    process.destroy();
                }
                if (successResult != null) {
                    successResult.close();
                }
                if (errorResult != null) {
                    errorResult.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //显示结果
        tvTest.setText("成功消息：" + successMsg.toString() + "\n" + "错误消息: " + errorMsg.toString());
    }

    //智能安装
    private void smartInstall() {
        MyAccessibilityService.INVOKE_TYPE = MyAccessibilityService.TYPE_INSTALL_APP;

        getApkPath();
        install();
    }

    //    content://com.wzy.installdemo.fileprovider/external_storage_root/zhumei_shp/zm.apk
    public void install() {

        Intent intent = new Intent();

        // android 7.0以上 7.0 ~ 8
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= Build.VERSION_CODES.N && sdkInt < Build.VERSION_CODES.P) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, "com.wzy.installdemo.fileprovider", new File(apkPath));
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");


            // 查询所有符合 intent 跳转目标应用类型的应用，注意此方法必须放置在 setDataAndType 方法之后

            // 查询所有符合 intent 跳转目标应用类型的应用，注意此方法必须放置在 setDataAndType 方法之后
//            MyApplication myApplication = MyApplication.getMyApplication();
            List<ResolveInfo> resolveLists = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            // 然后全部授权
            for (ResolveInfo resolveInfo : resolveLists) {
                String packageName = resolveInfo.activityInfo.packageName;
                grantUriPermission(packageName, contentUri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            intent.setAction(Intent.ACTION_VIEW);
            startActivity(intent);
        } else {


            Uri uri = Uri.fromFile(new File(apkPath));
            Intent localIntent = new Intent(Intent.ACTION_VIEW);
            localIntent.setDataAndType(uri, "application/vnd.android.package-archive");
            startActivity(localIntent);
        }

    }

    //监听apk安装
    private class MyInstallReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {     // install
                String packageName = intent.getDataString();
                Log.i(TAG, "安装了 :" + packageName);
                //安装完毕，设置flag，从而使得其余的apk不能自动安装
                flag = false;
//                EventBus.getDefault().post(new Event("ok"));

                Intent intent2 = new Intent("install");
                intent2.putExtra("msg", "OK");
                sendBroadcast(intent2);

            }
            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {   // uninstall
                String packageName = intent.getDataString();
                Log.i(TAG, "卸载了 :" + packageName);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }

        EventBus.getDefault().unregister(this);
    }
}