package com.zhr.test;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyListViewAdapter extends BaseAdapter{

    private Context context;
    private MvcView mMv;
    private List<Student> students;

    public MyListViewAdapter(MvcView mMv , Context mContext , List<Student> students) {
        this.mMv = mMv;
        this.context = mContext;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setGravity(Gravity.CENTER_VERTICAL);

        // 显示性别
        ImageView iv = new ImageView(context);
        String sex = students.get(i).getSex();
        if("male".equals(sex)){
            iv.setBackgroundColor(Color.YELLOW);
        }else if("female".equals(sex)){
            iv.setBackgroundColor(Color.GREEN);
        }

        // 显示姓名
        TextView tv = new TextView(context);
        String name = students.get(i).getName();
        tv.setText(name);

        ll.addView(iv , 50 , 50);
        ll.addView(tv);
        return ll;
    }
}
