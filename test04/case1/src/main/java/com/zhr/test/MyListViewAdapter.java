package com.zhr.test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyListViewAdapter extends BaseAdapter{

    private Context context;
    private MvcView mMv;

    public MyListViewAdapter(MvcView mMv , Context mContext) {
        this.mMv = mMv;
        this.context = mContext;
    }

    @Override
    public int getCount() {
        return 1000;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = mMv.showTextView(context, "我是文本第：" + i);
        return textView;
    }
}
