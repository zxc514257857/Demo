package com.zhr.test;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 案例一：android代码实现多线程下载
 * 使用了一下 线程一经开启无法关闭掉 一直开启下载是没问题的
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText mEt;
    private ProgressBar mPb1;
    private ProgressBar mPb2;
    private ProgressBar mPb3;
    /**
     * 开启几个线程从服务器下载数据
     */
    public static int threadCount = 3;
    public static int runningThreadCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEt = findViewById(R.id.et);
        mPb1 = findViewById(R.id.pb1);
        mPb2 = findViewById(R.id.pb2);
        mPb3 = findViewById(R.id.pb3);
    }

    /**
     * 点击按钮下载
     * @param view
     */
    public void download(View view){
        final String path = mEt.getText().toString().trim();
        if(TextUtils.isEmpty(path) || !path.startsWith("http://")){
            Toast.makeText(this , "对不起，路径不合法！" , Toast.LENGTH_LONG).show();
            return;
        }
        // 在子线程中访问网络
        new Thread(){
            @Override
            public void run(){
                try {
                    // 1、获取服务器上目标文件的大小
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("GET");
                    int responseCode = conn.getResponseCode();
                    if(responseCode == 200){
                        int contentLength = conn.getContentLength();
                        Log.i(TAG, "服务器的文件长度为：" + contentLength);
                        // 2、在本地创建一个跟原始文件同样大小的文件 模式可读可写
                        RandomAccessFile raf = new RandomAccessFile(Environment.getExternalStorageDirectory().getAbsolutePath()
                            + File.separator + getFileName(path) , "rw");
                        raf.setLength(contentLength);
                        raf.close();
                        // 3、计算每个线程下载的起始位置和结束位置
                        int blockSize = contentLength / threadCount;
                        runningThreadCount = threadCount;
                        for(int threadId = 0; threadId < threadCount; threadId++){
                            int startIndex = threadId * blockSize;
                            int endIndex = (threadId + 1) * blockSize - 1;
                            if(threadId == (threadCount -1)){
                                endIndex = contentLength - 1;
                            }
                            // 4、开启多个子线程开始下载
                            new DownloadThread(threadId , startIndex , endIndex , path).start();
                        }
                    }
                } catch (Exception e) {
                  e.printStackTrace();
                }
            }
        }.start();
    }

    private class DownloadThread extends Thread {

        /**
         * 线程id
         */
        private int mThreadId;

        /**
         * 线程下载的理论开始位置
         */
        private int mStartIndex;

        /**
         * 线程下载的结束位置
         */
        private int mEndIndex;

        /**
         * 当前线程下载到文件的哪个位置了
         */
        private int mCurrentPosition;

        private String mPath;

        public DownloadThread(int threadId, int startIndex, int endIndex, String path) {
            this.mThreadId = threadId;
            this.mStartIndex = startIndex;
            this.mEndIndex = endIndex;
            this.mPath = path;
            Log.i(TAG, threadId + "号线程下载的范围为：" + startIndex + "--" + endIndex);
            mCurrentPosition = startIndex;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(mPath);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                // 检查当前线程是否已经下载过一部分的数据
                File info = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator + mThreadId + ".position");
                RandomAccessFile raf = new RandomAccessFile(Environment.getExternalStorageDirectory().getAbsolutePath()
                        + File.separator + getFileName(mPath), "rw");
                if (info.exists() && info.length() > 0) {
                    FileInputStream fis = new FileInputStream(info);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    mCurrentPosition = Integer.valueOf(br.readLine());
                    conn.setRequestProperty("Range", "bytes=" + mCurrentPosition + "-" + mEndIndex);
                    Log.i(TAG, "原来有下载进度，从上一次终止的位置继续下载，bytes=" + mCurrentPosition + "--" + mEndIndex);
                    fis.close();
                    // 每个线程写文件的开始位置都是不一样的
                    raf.seek(mCurrentPosition);
                } else {
                    // 告诉服务器，只想下载资源的一部分
                    conn.setRequestProperty("Range", "bytes=" + mCurrentPosition + "-" + mEndIndex);
                    Log.i(TAG, "原来没有下载进度，新的下载，bytes=" + mCurrentPosition + "--" + mEndIndex);
                    raf.seek(mStartIndex);
                    InputStream is = conn.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    while ((len = is.read(buffer)) != -1) {
                        raf.write(buffer, 0, len);
                        // 5,记录下载进度
                        mCurrentPosition += len;
                        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                                + File.separator + mThreadId + ".position");
                        RandomAccessFile fos = new RandomAccessFile(file, "rwd");
                        Log.i(TAG, "线程：" + mThreadId + "写到了" + mCurrentPosition);
                        fos.write(String.valueOf(mCurrentPosition).getBytes());
                        fos.close();
                        int max = mEndIndex - mStartIndex;
                        int progress = mCurrentPosition - mStartIndex;
                        if (mThreadId == 0) {
                            mPb1.setMax(max);
                            mPb1.setProgress(progress);
                        } else if (mThreadId == 1) {
                            mPb2.setMax(max);
                            mPb2.setProgress(progress);
                        } else if (mThreadId == 2) {
                            mPb3.setMax(max);
                            mPb3.setProgress(progress);
                        }
                    }
                    raf.close();
                    is.close();
                    Log.i(TAG, "线程：" + mThreadId + "下载完毕了...");
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                            + File.separator + mThreadId + ".position");
                    file.renameTo(new File(Environment.getExternalStorageDirectory()
                            + File.separator + mThreadId + ".position.finish"));
                    synchronized (DownloadThread.class) {
                        runningThreadCount--;
                        // 6,删除临时文件
                        if(runningThreadCount <= 0){
                            for(int i = 0 ; i < threadCount; i++){
                                File f = new File(Environment.getExternalStorageDirectory() + File.separator + mThreadId + ".position.finish");
                                f.delete();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取文件名称（方法不严谨）
     * @param path
     */
    public String getFileName(String path) {
        int start = path.lastIndexOf("/") + 1;
        return path.substring(start);
    }
}
