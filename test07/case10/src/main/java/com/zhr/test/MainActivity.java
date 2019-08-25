package com.zhr.test;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 案例十：android中的时间类、debug调试、字节等单位的转换、和C语言 socket对接传递结构体数据
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // android中的时间获取方式1：
        // 需要借助其他类 去格式化毫秒值
        // 1970年到现在的毫秒值 系统时间调整后 值会修改
        Log.i(TAG, "currentTimeMillis: " + System.currentTimeMillis());

        // 系统启动到现在的毫秒值，包含系统深度休眠时间 值不可以修改
        Log.i(TAG, "elapsedRealtime: " + SystemClock.elapsedRealtime());

        // 系统启动到现在的纳秒值，包含系统深度休眠时间 值不可以修改
        // sdk版本大于17时才会被调用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Log.i(TAG, "elapsedRealtimeNanos: " + SystemClock.elapsedRealtimeNanos());
        }

        // android中的时间获取方式2：
        // 可以方便的单独获取到 年月日 时分秒 毫秒
        Calendar calendar = Calendar.getInstance();
        // 2019年4月29日 星期一
        Log.i(TAG, "year: " + String.valueOf(calendar.get(Calendar.YEAR)));
        Log.i(TAG, "month: " + String.valueOf(calendar.get(Calendar.MONTH) + 1));
        Log.i(TAG, "day: " + String.valueOf(calendar.get(Calendar.DATE)));
        // 1月中的第几天  第29天
        Log.i(TAG, "dayOfMonth: " + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        // 1周里面的第几天  第2天 从周日开始算
        Log.i(TAG, "dayOfWeek: " + String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)));
        // 这一天位于月中的第几周  第5周
        Log.i(TAG, "dayOfWeekInMonth: " + String.valueOf(calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH)));
        // 1年中的第几天  第119天
        Log.i(TAG, "dayOfYear: " + String.valueOf(calendar.get(Calendar.DAY_OF_YEAR)));

        // 12小时制的小时
        Log.i(TAG, "hour: " + String.valueOf(calendar.get(Calendar.HOUR)));
        // 24小时制的小时
        Log.i(TAG, "hourOfDay: " + String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)));
        // 判断是早上还是下午 0表示早上 1表示下午
        Log.i(TAG, "ampm: " + String.valueOf(calendar.get(Calendar.AM_PM)));
        // 分
        Log.i(TAG, "minute: " + String.valueOf(calendar.get(Calendar.MINUTE)));
        // 秒
        Log.i(TAG, "second: " + String.valueOf(calendar.get(Calendar.SECOND)));
        // 毫秒
        Log.i(TAG, "milliSecond: " + String.valueOf(calendar.get(Calendar.MILLISECOND)));

        // android中时间的获取方式3：
        // 格式固定，换格式麻烦  可以格式化long型时间
        // 外国人的年月日表达方式   29 Apr 2019 02:51:18 GMT
        Log.i(TAG, "GMTTime: " + new Date().toGMTString());
        // 中国人的年月日表达方式   2019年4月29日 10:51:18
        Log.i(TAG, "LocaleTime: " + new Date().toLocaleString());
        // 和SimpleDateFormat实现 日期的格式化
        @SuppressLint("SimpleDateFormat")
        // yyyyMMddhhmmss  hh是12小时制   yyyyMMddHHmmss  HH是24小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Log.i(TAG, "time: " + sdf.format(new Date(System.currentTimeMillis())));

        // android中的bebug 调试
        // 断点分为有效断点和无效断点  有效断点是红色  无效断点是灰色
        // 面板左上角
        // step over 快捷键F8 从断点处一行行往下面执行
        // step into 快捷键F7 进入本行程序内部执行，但不会进入类库方法中
        // force step into  进入本行程序内部执行，可以进入类库方法中
        // step out 从跳入的方法中跳出
        // run to cursor 快速向前跳入到光标定位的那行代码

        // 面板右上角
        // overhead 标记的断点位置 以及执行的毫秒值

        // 面板左侧
        // resume program 从一个断点快速跳到另一个断点处
        // stop  停止当前进程
        // view breakpoints 查看当前断点 添加Java异常断点 遇到此异常情况下，程序停止
        // settings   show values inline 在当前行后显示执行的值

        // 千字节、字节、比特的转换（KByte、Byte、bit）
        // 4KB = 4 * 1024B = 4 * 1024 * 8b = 32768b
        // 带宽和网速  2Mb/s和 KB/s
        // 一般2Mb/s 的网速 下载速度为2Kb/s * 1024 / 8 = 256 KB/s

        // android通讯常见的两种方式 一种是基于HTTP的通讯协议方式   一种是基于TCP/ UDP 协议的Socket方式
        // android 对接C语言的结构体数据(注意：C语言是低位在前 Java是高位在前)
        // struct str{
        //     int width[4];
        //     int height[4];
        //     int timeDay[4];
        //     int timeSecond[4];
        //     int type[4];
        //     byte[] sendBuf[256 * 256 * 3];
        // }
        // 首先定义一个byte[] sendBuf = new byte[20 + 256* 256* 3];
        // 将width 塞进sendBuf中 从第0个字节到第3个字节 (将int转为byte[])
        // for(int i = 0 ; i < 4 ; i++){
        //     sendBuf[i] = (byte) (width >> (24 - i * 8));
        // }
        // 将height 塞进sendBuf中 从第4个字节到第7个字节 (将int转为byte[])
        // for(int i = 4 ; i < 8 ; i++){
        //      sendBuf[i] = (byte) (width >> (24 - (i - 4) * 8));
        // }
        // 将timeDay 塞进sendBuf中 从第8个字节到第11个字节 (将int转为byte[])
        // for(int i = 8 ; i < 12 ; i++){
        //      sendBuf[i] = (byte) (timeDay >> (24 - (i - 8) * 8));
        // }
        // 将timeSecond 塞进sendBuf中 从第12个字节到第15个字节 (将int转为byte[])
        // for(int i = 12 ; i < 16 ; i++){
        //      sendBuf[i] = (byte) (timeSecond >> (24 - (i - 8) * 8));
        // }
        // 将type 塞进sendBuf中 从第16个字节到第19个字节 (将int转为byte[])
        // for(int i = 16 ; i < 20 ; i++){
        //      sendBuf[i] = (byte) (type >> (24 - (i - 8) * 8));
        // }
        // 将ARGB_8888转成 RGB_888 从第20个字符到20 + 256* 256* 3 - 1 个字符(将int 转为byte[])
        // int iIndex = 20;
        // for (int row = 0; row < height; row++) {
        //	    for (int col = 0; col < width; col++) {
        //	    	int pixel = scaledBitmap.getPixel(row, col);
        //	    	sendBuf[iIndex++] = (byte) (pixel & 0xff);
        //	    	sendBuf[iIndex++] = (byte) ((pixel >> 8) & 0xff);
        //	    	sendBuf[iIndex++] = (byte) ((pixel >> 16) & 0xff);
        //	    }
        //}
        // 如果是要发送一个byte[] 的话 就使用输出流 outPutStream.write(byte[])
        // 如果是要接收一个byte[] 的话 就使用输入流 inputStream.read(byte[])
        // 字节流和字符流的使用区别 字节流可以用于任何类型对象 字符流只能用于处理字符和字符串对象
    }
}
