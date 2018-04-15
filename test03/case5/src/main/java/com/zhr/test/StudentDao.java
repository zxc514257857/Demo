package com.zhr.test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by zhr on 2018/4/14.
 * Located:zmkj
 * Des:
 */

public class StudentDao {

    private StudentDataBaseOpenHelper mHelper;

    public StudentDao(Context mContext){
        mHelper = new StudentDataBaseOpenHelper(mContext);
    }

    /**
     * 在数据库中增加一条学生记录 表中规定了字段需有姓名，性别和手机号
     * @param name
     * @param sex
     * @param phone
     */
    public void add(String name, String sex, String phone){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("insert into student (name,sex,phone) values (?,?,?)" , new Object[]{name,sex,phone});
        db.close();
    }

    /**
     * 数据库表中以姓名作为增删改查的依据
     * @param name
     */
    public void delete(String name){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("delete from student where name=?" , new Object[]{name});
        db.close();
    }

    /**
     * 数据库中以姓名作为修改的依据，修改性别和手机号
     * @param name
     * @param newSex
     * @param newPhone
     */
    public void update(String name, String newSex, String newPhone){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("update student set sex=?,phone=? where name=?" , new Object[]{newSex , newPhone , name});
        db.close();
    }

    /**
     * 数据库中根据姓名查找一条学生记录信息中的手机号码信息 返回为null代表学生不存在
     * @param name
     */
    public String find(String name){
        String phone = "";
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select phone from student where name=?", new String[]{name});
        boolean resule = cursor.moveToNext();
        if(resule){
            phone = cursor.getColumnName(0);
        }
        cursor.close();
        db.close();
        return phone;
    }
}
