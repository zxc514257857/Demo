package com.zhr.test;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 案例十二：Socket Server端和Client端 代码编写
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText mEt1;
    private EditText mEt2;
    private TextView mTv;
    private Button mBt;
    /**
     * 0代表server端 ，1代表client端
     * server端华为手机不行 有防火墙等防护设置 NoRouteToHostException: No route to host
     */
    private static final int FLAG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        mEt1 = findViewById(R.id.et1);
        mEt2 = findViewById(R.id.et2);
        mBt = findViewById(R.id.bt);
        mTv = findViewById(R.id.tv);
    }

    private void initData() {
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEt1.setEnabled(false);
                mEt2.setEnabled(false);
                final String ip = mEt1.getText().toString().trim();
                final String port = mEt2.getText().toString().trim();
                if(!TextUtils.isEmpty(ip) && !TextUtils.isEmpty(port)){
                    if(FLAG == 0) {
                        ThreadUtils.runInThread(new Runnable() {
                            @Override
                            public void run() {
                                ServerSocket serverSocket = null;
                                try {
                                    serverSocket = new ServerSocket();
                                    InetSocketAddress isa = new InetSocketAddress(ip, Integer.parseInt(port));
                                    // 绑定本机的ip地址(可能会有多个ip)和端口号
                                    serverSocket.bind(isa);
                                    Log.e(TAG, "打开服务器成功!");
                                    int count = 0;
                                    while (true) {
                                        count++;
                                        Socket socket = serverSocket.accept();
                                        Log.e(TAG, "客户端:" + serverSocket.getInetAddress().getHostAddress() + "已连接到服务器!");
                                        // 字节输入流
                                        InputStream is = socket.getInputStream();
                                        // 字节字符输入转换流
                                        InputStreamReader isr = new InputStreamReader(is);
                                        // 字符输入流 这就将字节流转换为字符流
                                        BufferedReader br = new BufferedReader(isr);
                                        final String data = br.readLine() + "+" + count;
                                        ThreadUtils.runUnThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                mTv.setText(data);
                                            }
                                        });
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    try {
                                        if (serverSocket != null) {
                                            serverSocket.close();
                                        }
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            }
                        });
                    }else if(FLAG == 1){
                        ThreadUtils.runInThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    while(true){
                                        // 创建一个客户端socket
                                        Socket clientSocket = new Socket();
                                        // 连接服务端的IP地址和未被占用的端口号
                                        clientSocket.connect(new InetSocketAddress(ip , Integer.parseInt(port)));
                                        // 设置客户端socket数据读取的超时时间
                                        clientSocket.setSoTimeout(5 * 1000);
                                        // 设置客户端socket关闭，close()方法起作用时延迟30秒关闭，30秒内尽量将未发送的数据包发送出去
                                        clientSocket.setSoLinger(true , 30);
                                        // 设置客户端socket输出流的发送缓冲区大小，默认是4 * 1024 Byte
                                        clientSocket.setSendBufferSize(4 * 1024);
                                        // 设置客户端socket输入流的接收缓冲区大小，默认是4 * 1024 Byte
                                        clientSocket.setReceiveBufferSize(4 * 1024);
                                        // 设置客户端socket发送数据不使用Nagle算法，直接将数据发送出去
                                        clientSocket.setTcpNoDelay(true);
                                        // 设置客户端socket在服务端长时间无响应时，自动关闭客户端socket
                                        clientSocket.setKeepAlive(true);
                                        // 判断客户端socket是否连接
                                        if(clientSocket.isConnected()){
                                            Log.e(TAG, "客户端:" + clientSocket.getInetAddress().getHostAddress() + "已连接到服务器!");
                                        }else {
                                            Log.e(TAG, "客户端未连接到服务器!");
                                        }
                                        // 从clientSocket中获取字节输出流
                                        OutputStream os = clientSocket.getOutputStream();
                                        // 字节字符输出转换流
                                        OutputStreamWriter osw = new OutputStreamWriter(os);
                                        // 字符输出流 这就将字节流转换为字符流
                                        BufferedWriter bw = new BufferedWriter(osw);
                                        bw.write(ip , 0 , ip.length());
                                        bw.flush();
                                        bw.close();
                                        osw.close();
                                        os.close();
                                        SystemClock.sleep(1000);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy!!!");
    }
}
