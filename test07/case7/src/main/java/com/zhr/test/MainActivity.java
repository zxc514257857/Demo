package com.zhr.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSessionConfig;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

/**
 * 案例七：腾讯VasSonic
 * 这个只是优化加载速度等 页面缓存和DOM缓存都是靠H5来完成 其实使用WebView也是可以的
 * https://www.cnblogs.com/baiqiantao/p/7390276.html 参考了WebChromeClient部分
 * https://blog.csdn.net/niuba123456/article/details/81177567 参考了WebViewClient部分
 *
 * 使用要求Android 4.4及以上设备 即minSdkVersion要在19及以上
 *
 * 内核：
 * Trident(IE 内核)
 * Gecko(Firefox 内核)
 * Presto(Opera 前内核)
 * Webkit(Safari内核、Chrome内核原型)
 * Chormium/Blink(Chrome 内核)
 *
 * 移动端：
 * android 4.4之前使用了WebKit版本内核，4.4以后使用的是Chromium版本内核
 * ios 使用Webkit版本内核
 * WP 使用Trident版本内核
 *
 * 浏览器内核：
 * IE浏览器 Trident
 * Chrome浏览器 以前是Webkit 现在是Blink即Chromium
 * Firefox浏览器 Gecko
 * Safari浏览器 Webkit
 * Opera浏览器 以前是Presto 后来是Webkit 现在是Blink
 * 360、猎豹浏览器 Trident(兼容模式) + Blink(极速模式)双内核
 * 搜狗、遨游、QQ浏览器 Trident(兼容模式) + Webkit(极速模式)双内核
 * 百度、世界之窗浏览器 Trident
 * 2345浏览器 以前是Trident 现在是Trident(兼容模式) + Blink(极速模式)双内核
 *
 * 腾讯X5内核是腾讯基于Webkit深度优化的浏览器渲染引擎，内核就是渲染引擎
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mBtn01;
    private Button mBtn02;
    private Button mBtn03;
    private Button mBtn04;
    private Button mBtn05;

    private static final String mUrl = AppConstance.LOCAL_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        mBtn01 = findViewById(R.id.btn01);
        mBtn02 = findViewById(R.id.btn02);
        mBtn03 = findViewById(R.id.btn03);
        mBtn04 = findViewById(R.id.btn04);
        mBtn05 = findViewById(R.id.btn05);

        mBtn01.setOnClickListener(this);
        mBtn02.setOnClickListener(this);
        mBtn03.setOnClickListener(this);
        mBtn04.setOnClickListener(this);
        mBtn05.setOnClickListener(this);
    }

    private void initData(){
        AndPermission.with(this)
            .runtime()
            .permission(Permission.WRITE_EXTERNAL_STORAGE)
            .onGranted(new Action<List<String>>() {
                @Override
                public void onAction(List<String> data) {
                    Log.i(TAG , "onGranted: " + data);
                }
            }).onDenied(new Action<List<String>>() {
                @Override
                public void onAction(List<String> data) {
                    Log.i(TAG , "onDenied: " + data);
                }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            // 未使用sonic加载webview
            case R.id.btn01:
                startBrowserActivity(mUrl , AppConstance.MODE_DEFAULT , AppConstance.REQUEST_CODE);
                break;

            // 使用sonic加载webview
            case R.id.btn02:
                startBrowserActivity(mUrl , AppConstance.MODE_SONIC , AppConstance.REQUEST_CODE);
                break;

            // sonic预加载
            case R.id.btn03:
                SonicSessionConfig.Builder sessionConfigBuilder = new SonicSessionConfig.Builder();
                sessionConfigBuilder.setSupportLocalServer(true);
                // preload session
                boolean proload = SonicEngine.getInstance().preCreateSession(mUrl, sessionConfigBuilder.build());
                Toast.makeText(this, proload ? "Preload start up success!" : "Preload start up fail!" , Toast.LENGTH_SHORT).show();
                break;

            // 使用离线缓存加载sonic
            case R.id.btn04:
                startBrowserActivity(mUrl , AppConstance.MODE_SONIC_WITH_OFFLINE_CACHE , AppConstance.REQUEST_CODE);
                break;

            // 清除sonic缓存
            case R.id.btn05:
                SonicEngine.getInstance().cleanCache();
                Toast.makeText(this, "Clean cache success!", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    /**
     * 跳转到H5页面
     * @param mode
     * @param requestCode
     */
    private void startBrowserActivity(String url , int mode , int requestCode) {
        Intent intent = new Intent(this , BrowserActivity.class);
        // 传递加载链接
        intent.putExtra(AppConstance.PARAM_URL , url);
        // 区分加载模式 在另一个activity中进行判断
        intent.putExtra(AppConstance.PARAM_MODE , mode);
        startActivityForResult(intent , requestCode);
    }
}
