package com.zhr.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.alibaba.fastjson.JSON;
import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.zhr.test.bean.bank.ResultBean;
import com.zhr.test.callback.MyStringCallback;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * 案例十九：OkHttpUtils的使用
 * 设置拦截器 超时时间
 * 设置Cookie
 * 设置Https证书
 * 设置同步异步 GET请求
 * 设置同步异步 POST请求
 * POST 文件
 * POST JSON字符串
 * 自定义Callback
 * 表单上传文件 显示上传下载进度
 * 表单下载文件 显示上传下载进度
 * 取消请求
 * 根据TAG取消请求
 * Http六种请求方法
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;
    private Button mBtn5;
    private Button mBtn6;
    private Button mBtn7;
    private Button mBtn8;
    private Button mBtn9;
    private Button mBtn10;
    private Button mBtn11;
    private Button mBtn12;
    private Button mBtn13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mBtn1 = findViewById(R.id.btn1);
        mBtn2 = findViewById(R.id.btn2);
        mBtn3 = findViewById(R.id.btn3);
        mBtn4 = findViewById(R.id.btn4);
        mBtn5 = findViewById(R.id.btn5);
        mBtn6 = findViewById(R.id.btn6);
        mBtn7 = findViewById(R.id.btn7);
        mBtn8 = findViewById(R.id.btn8);
        mBtn9 = findViewById(R.id.btn9);
        mBtn10 = findViewById(R.id.btn10);
        mBtn11 = findViewById(R.id.btn11);
        mBtn12 = findViewById(R.id.btn12);
        mBtn13 = findViewById(R.id.btn13);
    }

    private void initData() {
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mBtn4.setOnClickListener(this);
        mBtn5.setOnClickListener(this);
        mBtn6.setOnClickListener(this);
        mBtn7.setOnClickListener(this);
        mBtn8.setOnClickListener(this);
        mBtn9.setOnClickListener(this);
        mBtn10.setOnClickListener(this);
        mBtn11.setOnClickListener(this);
        mBtn12.setOnClickListener(this);
        mBtn13.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1:
                // OkHttpUtils的默认设置
                // 网络请求状态码 200---> OK   301/302---> 永久重定向/临时重定向   403 forbidden---> 没有访问权限
                // 404 not found---> 没有资源   500---> 服务器错误   503---> 服务器停机或维护   504---> 网关超时
                OkHttpClient client1 = new OkHttpClient.Builder()
                        // 设置拦截器
                        .addInterceptor(new LoggerInterceptor(""))
                        .addNetworkInterceptor(new LoggerInterceptor(""))
                        // 设置超时时间
                        .connectTimeout(1 , TimeUnit.MILLISECONDS)
                        .readTimeout(1 , TimeUnit.MILLISECONDS)
                        .writeTimeout(1 , TimeUnit.MILLISECONDS)
                        .build();
                OkHttpUtils.initClient(client1);
                break;

            case R.id.btn2:
                // OkHttpUtils的Cookie设置 持久化存储cookie
                CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(mContext));
                // 内存存储cookie
//                CookieJarImpl cookieJar = new CookieJarImpl(new MemoryCookieStore());
                OkHttpClient client2 = new OkHttpClient.Builder()
                        .cookieJar(cookieJar)
                        .build();
                OkHttpUtils.initClient(client2);
                break;

            case R.id.btn3:
                // 设置可访问所有的Https网站
                HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null , null , null);
                // 设置具体的Https证书
//                HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(证书的inputStream , null , null);
                // 设置Https证书的双向认证
//                HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(证书的inputStream , 本地证书的inputStream , 本地证书的密码);
                OkHttpClient client3 = new OkHttpClient.Builder()
                        .sslSocketFactory(sslParams.sSLSocketFactory , sslParams.trustManager)
                        .build();
                OkHttpUtils.initClient(client3);
                break;

            case R.id.btn4:
                // OkHttpUtils进行GET请求 异步请求
                // 返回字符串值
                com.zhy.http.okhttp.callback.Callback callback  = new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {}
                    @Override
                    public void onResponse(String response, int id) {}
                };
                OkHttpUtils.get()
                        .url("")
                        .addParams("" ,"")
                        .build()
                        .execute(callback);
                break;

            case R.id.btn5:
                // OkHttpUtils进行POST请求 同步请求
                // 返回Bitmap值 加载图片
                OkHttpUtils.post()
                        .url("")
                        .addParams("", "")
                        .build()
                        .execute(new BitmapCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {}
                            @Override
                            public void onResponse(Bitmap response, int id) {}
                        });
                break;

            case R.id.btn6:
                // OkHttpUtils POST FILE execute方法不传入callback视为同步请求
                // 返回字符串值
                try {
                    OkHttpUtils.postFile()
                            .url("")
                            .file(new File(""))
                            .build()
                            .execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btn7:
                // OkHttpUtils POST Json字符串
                // 返回字符串值
                OkHttpUtils.postString()
                        .url("")
                        .content(JSON.toJSONString(new ResultBean()))
                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {}
                            @Override
                            public void onResponse(String response, int id) {}
                        });
                break;

            case R.id.btn8:
                // OkHttpUtils 表单形式上传文件
                // 对应后台表单种的<input type = "file" name = "mFile"/> name属性
                // 后台如果需要
                Map<String , String> params = new HashMap<>();
                params.put("device_id" , "1024");
                params.put("config" , "0");
                Map<String , String> headers = new HashMap<>();
                headers.put("key1" , "1");
                headers.put("key2" , "2");
                OkHttpUtils.post()
                        .addFile("mFile" , "01.png" , new File(""))
                        .addFile("mFile" , "02.txt" , new File(""))
                        .url("")
                        .params(params)
                        .headers(headers)
                        .build()
                        // 自定义Callback
                        .execute(new MyStringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {}
                            /**
                             * 通过parseNetworkResponse更改返回值
                             * @param response
                             * @param id
                             */
                            @Override
                            public void onResponse(ResultBean response, int id) {}
                        });
                break;

            case R.id.btn9:
                // OkHttpUtils 下载文件
                OkHttpUtils.get()
                        .url("")
                        .build()
                        // 文件下载必须要用 FileCallBack 里面传入文件夹路径和文件名称
                        .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "03.apk") {
                            /**
                             * 可以重写 inProgress方法得到上传下载进度
                             * @param progress
                             * @param total
                             * @param id
                             */
                            @Override
                            public void inProgress(float progress, long total, int id) {
                                new ProgressBar(mContext).setProgress((int)(100 * progress));
                            }
                            @Override
                            public void onError(Call call, Exception e, int id) {}
                            @Override
                            public void onResponse(File response, int id) {}
                        });
                break;

            case R.id.btn10:
                // OkHttpUtils 取消单个请求
                OkHttpUtils.get()
                        .url("")
                        .build()
                        .cancel();
                break;

            case R.id.btn11:
                // OkHttpUtils 根据TAG取消请求
                OkHttpUtils.get()
                        .url("")
                        .tag(this)
                        .build();
                // 取消请求
                OkHttpUtils.getInstance().cancelTag(this);
                break;

            case R.id.btn12:
                // OkHttpUtils Http六种请求方法 get post put delete head options
                OkHttpUtils.get()
                        .url("")
                        .build();

                OkHttpUtils.post()
                        .url("")
                        .build();

                OkHttpUtils.put()
                        .url("")
                        .build();

                OkHttpUtils.delete()
                        .url("")
                        .build();

                OkHttpUtils.head()
                        .url("")
                        .build();
                break;

            case R.id.btn13:
                // 使用FileDownloader下载文件
//                FileDownloader.setup(mContext);
//                Log.i(TAG, "开始下载");
//                FileDownloader.getImpl().create("http://n.zhumei.net/data/upload/market_26/market_26/20181108/2.0.2.apk")
//                        .setPath(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +  "test.apk")
//                        .setListener(new FileDownloadListener() {
//                            @Override
//                            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {}
//
//                            @Override
//                            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//                                Log.i(TAG, "progress---soFarBytes：" + soFarBytes + ",totalBytes：" + totalBytes);
//                            }
//
//                            @Override
//                            protected void completed(BaseDownloadTask task) {}
//
//                            @Override
//                            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {}
//
//                            @Override
//                            protected void error(BaseDownloadTask task, Throwable e) {}
//
//                            @Override
//                            protected void warn(BaseDownloadTask task) {}
//                        }).start();


                // 使用okDownload下载文件
                DownloadTask task = new DownloadTask.Builder("http://n.zhumei.net/data/upload/market_26/market_26/20181108/2.0.2.apk"
                        , Environment.getExternalStorageDirectory().getAbsolutePath() , "test.apk")
                        .build();
                task.enqueue(new DownloadListener() {
                    @Override
                    public void taskStart(@NonNull DownloadTask task) {}

                    @Override
                    public void connectTrialStart(@NonNull DownloadTask task, @NonNull Map<String, List<String>> requestHeaderFields) {}

                    @Override
                    public void connectTrialEnd(@NonNull DownloadTask task, int responseCode, @NonNull Map<String, List<String>> responseHeaderFields) {}

                    @Override
                    public void downloadFromBeginning(@NonNull DownloadTask task, @NonNull BreakpointInfo info, @NonNull ResumeFailedCause cause) {}

                    @Override
                    public void downloadFromBreakpoint(@NonNull DownloadTask task, @NonNull BreakpointInfo info) {}

                    @Override
                    public void connectStart(@NonNull DownloadTask task, int blockIndex, @NonNull Map<String, List<String>> requestHeaderFields) {}

                    @Override
                    public void connectEnd(@NonNull DownloadTask task, int blockIndex, int responseCode, @NonNull Map<String, List<String>> responseHeaderFields) {}

                    @Override
                    public void fetchStart(@NonNull DownloadTask task, int blockIndex, long contentLength) {}

                    @Override
                    public void fetchProgress(@NonNull DownloadTask task, int blockIndex, long increaseBytes) {
                        Log.i(TAG, "fetchProgress---increaseBytes:" + increaseBytes);
                    }

                    @Override
                    public void fetchEnd(@NonNull DownloadTask task, int blockIndex, long contentLength) {}

                    @Override
                    public void taskEnd(@NonNull DownloadTask task, @NonNull EndCause cause, @Nullable Exception realCause) {}
                });
                break;

            default:
                break;
        }
    }
}
