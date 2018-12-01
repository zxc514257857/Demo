package com.zhr.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 案例三：隐式意图练习
 * 显式意图使用的较多 隐式意图较少 重点练习一下
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 开启显式意图
     * @param view
     */
    public void jump1(View view){
        Intent intent = new Intent();
//        intent.setClassName( "com.zhr.test" , "com.zhr.test.Jiemian01");
        intent.setClass(this , Jiemian01.class);
        startActivity(intent);
    }

    /**
     * 开启隐式意图
     * @param view
     */
    public void jump2(View view){
        Intent intent = new Intent();
        intent.setAction("test");
        intent.addCategory("android.intent.category.DEFAUL");
        // android打开各类文件以及文件类型
//        {".3gp",    "video/3gpp"}
//        {".apk",    "application/vnd.android.package-archive"}
//        {".asf",    "video/x-ms-asf"}
//        {".avi",    "video/x-msvideo"}
//        {".bin",    "application/octet-stream"}
//        {".bmp",    "image/bmp"}
//        {".c",  "text/plain"}
//        {".class",  "application/octet-stream"}
//        {".conf",   "text/plain"}
//        {".cpp",    "text/plain"}
//        {".doc",    "application/msword"}
//        {".docx",   "application/vnd.openxmlformats-officedocument.wordprocessingml.document"}
//        {".xls",    "application/vnd.ms-excel"}
//        {".xlsx",   "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"}
//        {".exe",    "application/octet-stream"}
//        {".gif",    "image/gif"}
//        {".gtar",   "application/x-gtar"}
//        {".gz", "application/x-gzip"}
//        {".h",  "text/plain"}
//        {".htm",    "text/html"}
//        {".html",   "text/html"}
//        {".jar",    "application/java-archive"}
//        {".java",   "text/plain"}
//        {".jpeg",   "image/jpeg"}
//        {".jpg",    "image/jpeg"}
//        {".js", "application/x-javascript"}
//        {".log",    "text/plain"}
//        {".m3u",    "audio/x-mpegurl"}
//        {".m4a",    "audio/mp4a-latm"}
//        {".m4b",    "audio/mp4a-latm"}
//        {".m4p",    "audio/mp4a-latm"}
//        {".m4u",    "video/vnd.mpegurl"}
//        {".m4v",    "video/x-m4v"}
//        {".mov",    "video/quicktime"}
//        {".mp2",    "audio/x-mpeg"}
//        {".mp3",    "audio/x-mpeg"}
//        {".mp4",    "video/mp4"}
//        {".mpc",    "application/vnd.mpohun.certificate"}
//        {".mpe",    "video/mpeg"}
//        {".mpeg",   "video/mpeg"}
//        {".mpg",    "video/mpeg"}
//        {".mpg4",   "video/mp4"}
//        {".mpga",   "audio/mpeg"}
//        {".msg",    "application/vnd.ms-outlook"}
//        {".ogg",    "audio/ogg"}
//        {".pdf",    "application/pdf"}
//        {".png",    "image/png"}
//        {".pps",    "application/vnd.ms-powerpoint"}
//        {".ppt",    "application/vnd.ms-powerpoint"}
//        {".pptx",   "application/vnd.openxmlformats-officedocument.presentationml.presentation"}
//        {".prop",   "text/plain"}
//        {".rc", "text/plain"}
//        {".rmvb",   "audio/x-pn-realaudio"}
//        {".rtf",    "application/rtf"}
//        {".sh", "text/plain"}
//        {".tar",    "application/x-tar"}
//        {".tgz",    "application/x-compressed"}
//        {".txt",    "text/plain"}
//        {".wav",    "audio/x-wav"}
//        {".wma",    "audio/x-ms-wma"}
//        {".wmv",    "audio/x-ms-wmv"}
//        {".wps",    "application/vnd.ms-works"}
//        {".xml",    "text/plain"}
//        {".z",  "application/x-compress"}
//        {".zip",    "application/x-zip-compressed"}
        intent.setDataAndType(Uri.parse("data") , "text/plain");
        startActivity(intent);
    }
}
