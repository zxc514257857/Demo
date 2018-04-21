package com.zhr.test;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class MvcViewImpl implements MvcView {

    private Toast mToast;

    @Override
    public TextView showTextView(Context context, String content) {
        TextView tv = new TextView(context);
        if(tv != null && content != null){
            tv.setText(content);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP , 26);
            tv.setGravity(Gravity.CENTER);
        }
        return tv;
    }

    @Override
    public void showToast(Context context, String content) {
        if(mToast == null){
            mToast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        }else {
            mToast.setText(content);
        }
        mToast.show();
    }
}
