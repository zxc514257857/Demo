package com.zhr.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.CacheDoubleUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zhr.test.ticker.TickerView;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 案例一：
 * 测试MPChartLib 里面的圆饼图、垂直柱状图、折线图和雷达图demo
 * TickerView 和 RecyclerView加载轮播文字
 * Thread线程池 通过single模式可以让多个线程以队列方式执行
 * u盘插入拔出状态监听
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Context mContext = MainActivity.this;
    private TickerView mTv;
    private List<String> mList;
    private RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTv = findViewById(R.id.tickerview);
        mRv = findViewById(R.id.rv);
        mList = new ArrayList<>();
        mList.add("张宏睿");
        mList.add("王再阳");
        mList.add("李帅辉");
        mList.add("李建朋");
        random();
        mTv.setOnClickListener(v -> random());
        initRv();

        try {
            // 实现多线程依次执行的方法
            // 方法一：join方法
            Thread a = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    Log.i(TAG, "onCreate1: " + i);
                }
            });
            Thread b = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    Log.i(TAG, "onCreate2: " + i);
                }
            });
            Thread c = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    Log.i(TAG, "onCreate3: " + i);
                }
            });
//            a.start();
//            a.join();
//            b.start();
//            b.join();
//            c.start();
//            c.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // 方法二：Single 单一线程线程池
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Runnable a = () -> {
                for (int i = 0; i < 10; i++) {
                    Log.i(TAG, "onCreate1: " + i);
                }
            };
            Runnable b = () -> {
                for (int i = 0; i < 10; i++) {
                    Log.i(TAG, "onCreate2: " + i);
                }
            };
            Runnable c = () -> {
                for (int i = 0; i < 10; i++) {
                    Log.i(TAG, "onCreate3: " + i);
                }
            };
//            executorService.submit(a);
//            executorService.submit(b);
//            executorService.submit(c);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 方法三：用ThreadUtils 实现单一线程池
        ThreadUtils.SimpleTask<Object> a = new ThreadUtils.SimpleTask<Object>() {
            @Override
            public Object doInBackground() throws Throwable {
                for (int i = 0; i < 10; i++) {
                    Log.i(TAG, "onCreate1: " + i);
                }
                return null;
            }

            @Override
            public void onSuccess(Object o) {
            }
        };
        ThreadUtils.SimpleTask<Object> b = new ThreadUtils.SimpleTask<Object>() {
            @Override
            public Object doInBackground() throws Throwable {
                for (int i = 0; i < 10; i++) {
                    Log.i(TAG, "onCreate2: " + i);
                }
                return null;
            }

            @Override
            public void onSuccess(Object o) {
            }
        };
        ThreadUtils.SimpleTask<Object> c = new ThreadUtils.SimpleTask<Object>() {
            @Override
            public Object doInBackground() throws Throwable {
                for (int i = 0; i < 10; i++) {
                    Log.i(TAG, "onCreate3: " + i);
                }
                return null;
            }

            @Override
            public void onSuccess(Object o) {
            }
        };
        // single cached  一般就使用single或者cached就好了
        // io cpu fix custom
//        ThreadUtils.executeBySingle(a);
//        ThreadUtils.executeBySingle(b);
//        ThreadUtils.executeBySingle(c);

        // 方法四：用ThreadUtils 的另一种写法
        ExecutorService singlePool = ThreadUtils.getSinglePool();
        singlePool.execute(() -> {
            for (int i = 0; i < 10; i++) {
                Log.i(TAG, "onCreate1: " + i);
            }
        });
        singlePool.execute(() -> {
            for (int i = 0; i < 10; i++) {
                Log.i(TAG, "onCreate2: " + i);
            }
        });
        singlePool.execute(() -> {
            for (int i = 0; i < 10; i++) {
                Log.i(TAG, "onCreate3: " + i);
            }
        });

        // 先插入u盘 再启动应用时的判断
        // 读取u盘里面的内容失败 原来是没有添加读写权限
        String mountPath = CacheDoubleUtils.getInstance().getString("mountPath", "");
        ToastUtils.showShort("mountPath111:" + mountPath);
        LogUtils.e("mountPath111:" + mountPath);
        boolean mounted = isMounted(mountPath);
        ToastUtils.showShort("mounted111:" + mounted);
        LogUtils.e("mounted111:" + mounted);
        // 判断完u盘是否插入再从u盘中取出对应的图片视频文件  直接拷贝广播中的内容即可
        File file = new File(mountPath);
        if (file.exists() && file.isDirectory()) {
            // 在U盘根目录下找文件
            String[] list = file.list();
            if (list != null && list.length > 0) {
                for (String aaa : list) {
                    LogUtils.e("u盘里面的名称:" + aaa);
                }
            }
        }

        // 方法一：在子线程更新UI操作  runOnUiThread
        new Thread(new Runnable() {
            @Override
            public void run() {
                // ToastUtils 里面已经做了 线程切换操作 可以无惧子线程
                // ToastUtils.showShort();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(MainActivity.this, "子线程更新UI", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();

        // 方法二：在子线程更新UI操作 Handler发送消息  handler.post()
        Handler mHandler = new Handler(Looper.getMainLooper());
        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(MainActivity.this, "子线程更新UI", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();

        // 方法三：子线程更新UI --- Handler发送消息  handler.sendMessage()
        int MESSAGE_WHAT = 1000;
        Handler handler = new Handler(){
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(@NonNull @NotNull Message msg) {
                super.handleMessage(msg);
                if(msg.what == MESSAGE_WHAT){
//                    Toast.makeText(MainActivity.this, "子线程更新UI", Toast.LENGTH_LONG).show();
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(MESSAGE_WHAT);
            }
        }).start();

        // 方法四： 通过EventBus进行线程切换
        new Thread(new Runnable() {
            @Override
            public void run() {
//                EventBus线程切换
            }
        }).start();

        // 方法五： 通过AsyncTask 进行线程切换
        AsyncTask asyncTask = new AsyncTask() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Object doInBackground(Object[] objects) {
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                Toast.makeText(MainActivity.this, "子线程更新UI", Toast.LENGTH_LONG).show();
            }
        };
        asyncTask.execute();
    }

    private void random() {
        int random = new Random().nextInt(mList.size());
        LogUtils.e("random:" + mList.get(random));
        mTv.setText(mList.get(random) + "");
    }

    private void initRv() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        mRv.setLayoutManager(layoutManager);
        List<String> list = new ArrayList<>();
        list.add("11111111111111111");
        list.add("2222");
        list.add("3333333333");
        list.add("44444444444444444444444444444444444444444444444444");
        mRv.setAdapter(new MyRecyclerViewAdapter(list));
    }

    @OnClick({
            R.id.btn_pie, R.id.btn_vbar, R.id.btn_hbar, R.id.btn_line, R.id.btn_radar, R.id.btn_scatter
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pie:
                // 圆饼图
                ActivityUtils.startActivity(new Intent(mContext, PieChartActivity.class));
                break;

            case R.id.btn_vbar:
                // 垂直柱状图
                ActivityUtils.startActivity(new Intent(mContext, VBarChartActivity.class));
                break;

            case R.id.btn_hbar:
                // 水平柱状图
                ActivityUtils.startActivity(new Intent(mContext, HBarChartActivity.class));
                break;

            case R.id.btn_line:
                // 折线图
                ActivityUtils.startActivity(new Intent(mContext, LineChartActivity.class));
                break;

            case R.id.btn_radar:
                // 雷达图
                ActivityUtils.startActivity(new Intent(mContext, RadarChartActivity.class));
                break;

            case R.id.btn_scatter:
                // 散列图  用法和折线图的用法类似
                ActivityUtils.startActivity(new Intent(mContext, ScatterChartActivity.class));
                break;

            default:
                break;
        }
    }

    /**
     * 判断是否有U盘插入,当U盘开机之前插入使用该方法.
     *
     * @param path
     * @return
     */
    public static boolean isMounted(String path) {
        boolean blnRet = false;
        String strLine = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/mounts"));
            while ((strLine = reader.readLine()) != null) {
                if (strLine.contains(path)) {
                    blnRet = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                reader = null;
            }
        }
        return blnRet;
    }
}
