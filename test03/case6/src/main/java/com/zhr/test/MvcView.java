package com.zhr.test;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by zhr on 2018/4/14.
 * Located:zmkj
 * Des:
 */

public interface MvcView {

    void removeView(LinearLayout ll);

    void addView(LinearLayout ll , TextView tv);

    void showTextView(TextView tv , String content);

    void showToast(Context mContext , String content);
}
