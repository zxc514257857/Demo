package com.zhr.test;

import android.content.Context;
import android.widget.TextView;

public interface MvcView {

    void showToast(Context context , String content);

    void showTextView(TextView textView , String string);
}
