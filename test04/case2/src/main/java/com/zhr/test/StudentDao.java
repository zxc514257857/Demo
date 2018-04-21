package com.zhr.test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private final StudentDatabaseOpenHelper mHelper;

    public StudentDao(Context mContext){
        mHelper = new StudentDatabaseOpenHelper(mContext);
    }

    /**
     * 在表中添加学生的姓名 性别
     * @param name
     * @param sex
     */
    public void add(String name , String sex){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("insert into student (name,sex) values (?,?)", new Object[]{name ,sex});
        db.close();
    }

    /**
     * 根据姓名删除表中学生
     * @param name
     */
    public void delete(String name){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("delete from student where name=?" , new Object[]{name});
        db.close();
    }

    /**
     * 根据学生姓名修改其性别
     * @param name
     * @param newSex
     */
    public void update(String name , String newSex){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("update student set sex=? where name=?" , new Object[]{name , newSex});
        db.close();
    }

    /**
     * 根据学生姓名查找其性别
     * @param name
     */
    public String find(String name){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select sex from student where name=?", new String[]{name});
        String mSex = null;
        boolean result = cursor.moveToNext();
        if(result){
            mSex = cursor.getString(0);
        }
        db.close();
        cursor.close();
        return mSex;
    }

    /**
     *
     * @return
     */
    public List<Student> findAll(){
        List<Student> stuList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select name,sex from student", null);
        boolean result = cursor.moveToNext();
        while(result){
            String name = cursor.getString(0);
            String sex = cursor.getString(1);
            Student stu = new Student();
            stu.setName(name);
            stu.setSex(sex);
            stuList.add(stu);
        }
        db.close();
        cursor.close();
        return stuList;
    }
}
