package com.zhr.test;

import android.content.Context;
import android.widget.TextView;

public interface MvcModel {

    void saveStuInfo(Context mContext , String name , String number , String sex);

    void readStuInfo(Context mContext , String name , TextView tv);
}
