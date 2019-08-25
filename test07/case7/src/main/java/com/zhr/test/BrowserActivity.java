package com.zhr.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tencent.sonic.sdk.SonicCacheInterceptor;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicConstants;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSession;
import com.tencent.sonic.sdk.SonicSessionConfig;
import com.tencent.sonic.sdk.SonicSessionConnection;
import com.tencent.sonic.sdk.SonicSessionConnectionInterceptor;
import com.zhr.test.sonic.SonicJavaScriptInterface;
import com.zhr.test.sonic.SonicRuntimeImpl;
import com.zhr.test.sonic.SonicSessionClientImpl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrowserActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = BrowserActivity.class.getSimpleName();
    private SonicSession mSonicSession;
    private WebView mWv;
    private FloatingActionButton mFab;
    private ProgressBar mPb;
    private Context context = BrowserActivity.this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String url = intent.getStringExtra(AppConstance.PARAM_URL);
        int mode = intent.getIntExtra(AppConstance.PARAM_MODE, AppConstance.MODE_ERROR);
        if (mode == AppConstance.MODE_ERROR) {
            // 过滤特殊情况 满足时进入后即退出
            finish();
            return;
        }
        // 开启硬件加速
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        // 设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // step 1: Initialize sonic engine if necessary, or maybe u can do this when application created
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(new SonicRuntimeImpl(getApplication()), new SonicConfig.Builder().build());
        }

        SonicSessionClientImpl sonicSessionClient = null;
        // 如果不为 未使用sonic模式
        if (AppConstance.MODE_DEFAULT != mode) {
            SonicSessionConfig.Builder sessionConfigBuilder = new SonicSessionConfig.Builder();
            sessionConfigBuilder.setSupportLocalServer(true);
            // 如果为离线缓存模式
            if (AppConstance.MODE_SONIC_WITH_OFFLINE_CACHE == mode) {
                sessionConfigBuilder.setCacheInterceptor(new SonicCacheInterceptor(null) {
                    @Override
                    public String getCacheData(SonicSession session) {
                        // 离线缓存模式不需要打开缓存
                        return null;
                    }
                });

                // 设置离线加载的本地文件
                sessionConfigBuilder.setConnectionInterceptor(new SonicSessionConnectionInterceptor() {
                    @Override
                    public SonicSessionConnection getConnection(SonicSession session, Intent intent) {
                        return new OfflinePkgSessionConnection(BrowserActivity.this, session, intent);
                    }
                });
            }

            // step 2: Create SonicSession
            mSonicSession = SonicEngine.getInstance().createSession(url, sessionConfigBuilder.build());
            if (null != mSonicSession) {
                sonicSessionClient = new SonicSessionClientImpl();
                mSonicSession.bindClient(sonicSessionClient);
            } else {
                // this only happen when a same sonic session is already running,
                // u can comment following codes to feedback as a default mode.
                Log.i(TAG, "create sonic session fail!");
            }
        }

        // step 3: BindWebView for sessionClient and bindClient for SonicSession
        setContentView(R.layout.activity_browser);
        initView();
        initData(intent , sonicSessionClient , url);
    }

    private void initView() {
        mWv = findViewById(R.id.wv);
        mFab = findViewById(R.id.fab);
        mPb = findViewById(R.id.pb);
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void initData(Intent intent , SonicSessionClientImpl sonicSessionClient , final String url) {
        mFab.setOnClickListener(this);
        // 创建测试文件夹
        makeDir(AppConstance.TEST_PATH);

        /**
         * setWebViewClient 主要帮助WebView处理各种通知、请求事件
         */
        mWv.setWebViewClient(new WebViewClient(){

            /**
             * 当内核加载完当前页面时会通知我们的应用程序
             * @param view
             * @param url
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(mSonicSession != null){
                    mSonicSession.getSessionClient().pageFinish(url);
                }
            }

            /**
             * 通知应用程序内核即将加载url指定的资源，应用程序可以返回本地的资源提供给内核，若本地处理返回数据，内核不从网络上获取数据
             * @param view
             * @param request
             * @return
             */
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return shouldInterceptRequest(view, request.getUrl().toString());
            }

            /**
             * 通知应用程序内核即将加载url指定的资源，应用程序可以返回本地的资源提供给内核，若本地处理返回数据，内核不从网络上获取数据
             * @param view
             * @param url
             * @return
             */
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if(mSonicSession != null){
                    return (WebResourceResponse) mSonicSession.getSessionClient().requestResource(url);
                }
                return null;
            }

            /**
             * 访问地址错误回调
             * @param wv
             * @param request
             * @param error
             */
            @Override
            public void onReceivedError(WebView wv, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(wv, request, error);
                // 有缓存就不需要加载静态页面了
//                wv.loadUrl(AppConstance.LOCAL_URL);
            }

            /**
             * 当加载的网页需要重定向的时候就会回调这个函数告知我们应用程序是否需要接管控制网页加载，如果应用程序接管
             * 并且return true意味着主程序接管网页加载，如果返回false让webview自己处理
             * @param view
             * @param request
             * @return
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }
        });

        /**
         * setWebChromeClient 主要是帮助WebView处理JavaScript的对话框，网站图标，网站title，加载进度等
         * 不设置 弹框无法生效
         */
        mWv.setWebChromeClient(new WebChromeClient(){

            /**
             * 获取网页加载进度
             * @param view
             * @param newProgress
             */
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mPb.setProgress(newProgress);
                if(100 == newProgress){
                    mPb.setVisibility(View.GONE);
                }
            }

            /**
             * 获取网页icon
             * @param view
             * @param icon
             */
            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }

            /**
             * 获取网页标题
             * @param view
             * @param title
             */
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.i(TAG, "onReceivedTitle: " + title);
            }

            /**
             * 是否拦截控制台消息 返回true拦截
             * @param consoleMessage
             * @return
             */
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                // 自定义console控制台输出
//                Log.i(TAG , "【onConsoleMessage】" + "\nmessage=" + consoleMessage.message()
//                        + "\nlineNumber=" + consoleMessage.lineNumber()
//                        + "\nmessageLevel=" + consoleMessage.messageLevel() + "\nsourceId=" + consoleMessage.sourceId());
//                return super.onConsoleMessage(consoleMessage);
                // 拦截console输出
                return true;
            }

            /**
             * 是否拦截展示alert弹框 返回true拦截
             * @param view
             * @param url
             * @param message
             * @param result
             * @return
             */
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                // onJsConfirm---> url:file:///,message:alert弹框
                Log.i(TAG, "onJsAlert---> " + "url:" + url + ",message:" + message);
                if(!TextUtils.isEmpty(message)){
                    // 可在这里弹出android样式弹框 以Toast代替
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    // 关闭弹框 不加会页面无法点击
                    result.cancel();
                    // 拦截alert
                    return true;
                }else{
                    // 对alert不作处理
                    return super.onJsAlert(view, url, message, result);
                }
            }

            /**
             * 是否拦截展示confirm弹框 返回true拦截
             * @param view
             * @param url
             * @param message
             * @param result
             * @return
             */
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                // onJsConfirm---> url:file:///,message:confirm弹框
                Log.i(TAG, "onJsConfirm---> " + "url:" + url + ",message:" + message);
                if(!TextUtils.isEmpty(message)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("标题")
                        .setMessage(message)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.cancel();
                            }
                        });

                    // 取消AlertDialog 显示时，其他键值的作用
                    builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                        @Override
                        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                            Log.i(TAG, "onJsConfirm---> " + "keyCode:" + keyCode + ",event:" + event);
                            return true;
                        }
                    });
                    builder.setCancelable(false);
                    builder.show();
                    // 拦截confirm
                    return true;
                }else {
                    // 对confirm不作处理
                    return super.onJsConfirm(view, url, message, result);
                }
            }

            /**
             * 是否拦截prompt弹框 返回true拦截
             * @param view
             * @param url
             * @param message
             * @param defaultValue
             * @param result
             * @return
             */
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                // onJsPrompt---> url:file:///,message:prompt弹框,defaultValue:包青天
                // defaultValue 是在JS代码里面写的
                Log.i(TAG, "onJsPrompt---> " + "url:" + url + ",message:" + message + ",defaultValue:" + defaultValue);
                if(!TextUtils.isEmpty(message)){
                    // 可在这里弹出android样式弹框 以Toast代替
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                    // 关闭弹框 不加会页面无法点击
                    result.cancel();
                    // 拦截prompt
                    return true;
                }else {
                    // 对prompt不作处理
                    return super.onJsPrompt(view, url, message, defaultValue, result);
                }
            }

            /**
             * 判断浏览器页面刷新或关闭的方法 返回true拦截
             * @param view
             * @param url
             * @param message
             * @param result
             * @return
             */
            @Override
            public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
                Log.i(TAG, "onJsBeforeUnload---> " + "url:" + url + ",message:" + message);
                return super.onJsBeforeUnload(view, url, message, result);
//                return true;
            }
        });

        // step 4: bind javascript
        WebSettings settings = mWv.getSettings();
        // 如果访问的页面中有JS,需要设置页面支持JS
        settings.setJavaScriptEnabled(true);
        // 防止漏洞 移除searchBoxJavaBridge_、accessibility、accessibilityTraversal这三个隐藏系统接口
        mWv.removeJavascriptInterface("searchBoxJavaBridge_");
        mWv.removeJavascriptInterface("accessibility");
        mWv.removeJavascriptInterface("accessibilityTraversal");
        mWv.addJavascriptInterface(new SonicJavaScriptInterface(sonicSessionClient , intent) , AppConstance.PACKAGE_NAME);
        initWebSettings(settings);

        // step 5: webview is ready now, just tell session client to bind
        if(sonicSessionClient != null){
            sonicSessionClient.bindWebView(mWv);
            sonicSessionClient.clientReady();
        }else {
            mWv.loadUrl(url);
        }
    }

    /**
     * 创建测试文件夹
     * @return
     */
    public File makeDir(String path) {
        File file = new File(path.trim());
        if(!file.exists()){
            file.mkdir();
        }
        return file;
    }

    private void initWebSettings(WebSettings settings) {
        // 是否允许在WebView中访问Url
        settings.setAllowContentAccess(true);
        // 数据库存储API是否可用
        settings.setDatabaseEnabled(true);
        // DOM存储API是否可用
        settings.setDomStorageEnabled(true);
        // 应用缓存API是否可用
        settings.setAppCacheEnabled(true);
        // 设置WebView是否保存密码 API18以上版本已废弃。未来版本将不支持保存WebView中的密码
        settings.setSavePassword(true);
        // WebView是否保存表单数据
        settings.setSaveFormData(true);
        // WebView是否支持HTML的"viewport" 标签或者使用wide viewport。设置值为true时，布局的宽度总是与WebView控件上的设备无关像素(device-dependent pixels)宽度一致。
        // 当值为true且页面包含viewport标记，将使用标签指定的宽度。如果页面不包含标签或者标签没有提供宽度，那就使用wide viewport
        settings.setUseWideViewPort(true);
        // 是否允许WebView度超出以概览的方式载入页面,缩小内容以适应屏幕宽度
        settings.setLoadWithOverviewMode(true);

        // 设置渲染效果优先级 高
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 五种缓存模式
        // LOAD_CACHE_ONLY  不使用网络，只使用缓存
        // LOAD_DEFAULT  根据cache-control 决定是否从网络获取数据
        // LOAD_CACHE_NORMAL  API 17中已经废弃, 从API 11开始作用同 LOAD_DEFAULT模式
        // LOAD_NO_CACHE  不使用缓存，只使用本地
        // LOAD_CACHE_ELSE_NETWORK  本地有缓存则使用，没有则使用网络
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 创建webview缓存文件夹
        makeDir(AppConstance.WEBVIEW_CACHE_PATH);
        // 设置数据库缓存路径
        settings.setDatabasePath(AppConstance.WEBVIEW_CACHE_PATH);
        // 设置应用缓存路径
        settings.setAppCachePath(AppConstance.WEBVIEW_CACHE_PATH);
        // 设置应用缓存的最大缓存大小  8M
        settings.setAppCacheMaxSize(AppConstance.WEBVIEW_CACHE_MAX_SIZE);
        // 设置隐藏缩放控件
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        // 设置禁止缩放
        settings.setSupportZoom(false);
        // 设置支持多窗口 设置为true的话 主程序需要实现 onCreateWindow
        settings.setSupportMultipleWindows(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                if (mSonicSession != null) {
                    mSonicSession.refresh();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if(null != mSonicSession){
            mSonicSession.destroy();
            mSonicSession = null;
        }
        super.onDestroy();
    }

    /**
     * 设置离线加载的本地文件
     */
    private static class OfflinePkgSessionConnection extends SonicSessionConnection {

        private final WeakReference<Context> context;
        private OfflinePkgSessionConnection(Context context, SonicSession session, Intent intent) {
            super(session, intent);
            this.context = new WeakReference<>(context);
        }

        @Override
        protected int internalConnect() {
            Context ctx = context.get();
            if (null != ctx) {
                try {
                    InputStream offlineHtmlInputStream = ctx.getAssets().open("index1.html");
                    responseStream = new BufferedInputStream(offlineHtmlInputStream);
                    return SonicConstants.ERROR_CODE_SUCCESS;
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
            return SonicConstants.ERROR_CODE_UNKNOWN;
        }

        @Override
        protected BufferedInputStream internalGetResponseStream() {
            return responseStream;
        }

        @Override
        protected String internalGetCustomHeadFieldEtag() {
            return SonicSessionConnection.CUSTOM_HEAD_FILED_ETAG;
        }

        @Override
        public void disconnect() {
            if (null != responseStream) {
                try {
                    responseStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public int getResponseCode() {
            return 200;
        }

        @Override
        public Map<String, List<String>> getResponseHeaderFields() {
            return new HashMap<>(0);
        }

        @Override
        public String getResponseHeaderField(String key) {
            return "";
        }
    }
}
