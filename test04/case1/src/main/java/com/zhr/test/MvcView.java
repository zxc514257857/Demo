package com.zhr.test;

import android.content.Context;
import android.widget.TextView;

public interface MvcView {

    TextView showTextView(Context context , String content);

    void showToast(Context context , String content);


}
