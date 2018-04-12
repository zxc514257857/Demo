package com.zhr.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 自定义数据库帮助类
 */
public class MyDataBaseOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = MyDataBaseOpenHelper.class.getSimpleName();

    /**
     * 构造方法
     * @param context
     * @param name 数据库名称
     * @param factory 游标工厂
     * @param version 数据库版本号
     */
    public MyDataBaseOpenHelper(Context context) {
        super(context, "zhr.db", null, 1);
    }

    /**
     * 数据库第一次被创建的时候调用 只执行一次 一般用于初始化数据库的表结构
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "数据库onCreate");
        sqLiteDatabase.execSQL("create table person (_id integer primary key autoincrement," +
                "name varchar(20) , phone varch(30))");
    }

    /**
     * 当数据库需要被更新的时候调用的方法
     * 注：数据库只能升级不能降级
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i(TAG, "数据库onUpgrade");
    }
}
