package com.zhr.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StudentDatabaseOpenHelper extends SQLiteOpenHelper {

    private String TAG = StudentDatabaseOpenHelper.class.getCanonicalName();

    public StudentDatabaseOpenHelper(Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG, "数据库onCreate！");
        sqLiteDatabase.execSQL("create table student (_id integer primary key autoincrement," +
                "name varchar(20) , sex varchar(6))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i(TAG, "数据库onUpgrade！");
    }
}
