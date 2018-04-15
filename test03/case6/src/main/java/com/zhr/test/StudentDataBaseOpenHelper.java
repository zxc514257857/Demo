package com.zhr.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by zhr on 2018/4/14.
 * Located:zmkj
 * Des:
 */

public class StudentDataBaseOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = StudentDataBaseOpenHelper.class.getSimpleName();

    public StudentDataBaseOpenHelper(Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "数据库onCreate");
        sqLiteDatabase.execSQL("create table student (_id integer primary key autoincrement, name varchar(20), sex varchar(6), phone varchar(30))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i(TAG, "数据库onUpgrade");
    }
}
