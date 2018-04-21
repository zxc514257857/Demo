package com.zhr.test.ui.main.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.TextView;

public interface MvcView {

    TextView showTextView(Context context , String content);

    void showToast(Context context , String content);

    void showAlertDialog1(Context mContext , String title , String msg , String negativeMsg ,
                          String positiveMsg);

    void showAlertDialog2(Context mContext , String title , String[] items,
                          String negativeMsg , String positiveMsg);

    void showAlertDialog3(Context mContext , String title , String[] items, boolean[] checkedItems,
                          String negativeMsg , String positiveMsg);

    void showProgressDialog1(Context mContext , String title , String msg);

    void dismissProgressDialog1();

    ProgressDialog showProgressDialog2(Context mContext , String title , String msg);

    void dismissProgressDialog2();
}
