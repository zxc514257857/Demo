package com.zhr.test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhr on 2018/4/14.
 * Located:zmkj
 * Des:学生信息数据库的Dao(Data access object)
 */

public class StudentDao {

    public StudentDataBaseOpenHelper mHelper;

    public StudentDao(Context mContext){
        mHelper = new StudentDataBaseOpenHelper(mContext);
    }

    public void add(String name , String sex , String phone){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("insert into student(name,sex,phone) values (?,?,?)", new Object[]{name,sex,phone});
        db.close();
    }

    public void delete(String name){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("delete from student where name=?" , new Object[]{name});
        db.close();
    }

    public void update(String name , String newSex , String newPhone){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("update student set sex=?,phone=? where name=?", new Object[]{name , newSex , newPhone});
        db.close();
    }

    /**
     * 数据库中根据姓名查找一条学生记录信息中的手机号码信息 返回为null代表学生不存在
     * @param name
     */
    public String find(String name){
        String phone = "";
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select phone from student where name=?", new String[]{name});
        boolean result = cursor.moveToNext();
        if(result){
            phone = cursor.getString(0);
        }
        db.close();
        cursor.close();
        return phone;
    }

    /**
     * 查找所有的学生信息
     * @return
     */
    public List<StudentBean> findAll(){
        List<StudentBean> students = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select name,sex,phone from student", null);
        boolean result = cursor.moveToNext();
        while(result){
            String name = cursor.getString(0);
            String sex = cursor.getString(1);
            String phone = cursor.getString(2);
            StudentBean studentBean = new StudentBean();
            studentBean.setName(name);
            studentBean.setSex(sex);
            studentBean.setPhone(phone);
            students.add(studentBean);
        }
        cursor.close();
        db.close();
        return students;
    }
}
