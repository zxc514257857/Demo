package com.zhr.test.ui.main.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class MvcViewImpl implements MvcView {

    private Toast mToast;
    private static final String TAG = MvcViewImpl.class.getSimpleName();
    private int mCheckedItem;
    private ProgressDialog mPd1;
    private ProgressDialog mPd2;

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

    @Override
    public void showAlertDialog1(final Context mContext , final String title , final String msg , String negativeMsg ,
                                 String positiveMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(msg);
        builder.setPositiveButton(positiveMsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String content = "资讯内容：" + msg + "条目id：" + title + "，被选中！";
                showToast(mContext ,  content);
                Log.i(TAG, content);
            }
        });
        showDialog(builder , title , negativeMsg);
    }

    @Override
    public void showAlertDialog2(final Context mContext , final String title , final String[] items,
                                 String negativeMsg , String positiveMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        mCheckedItem = 0;
        // 替代了setMessage(msg)的内容
        builder.setSingleChoiceItems(items, mCheckedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mCheckedItem = i;
                showToast(mContext ,  "资讯内容：" + items[i]);
            }
        });
        builder.setPositiveButton(positiveMsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String content = "资讯内容：" + items[mCheckedItem] + ",条目id：" + title + "，被选中！";
                showToast(mContext , content );
                Log.i(TAG, content);
            }
        });
        showDialog(builder , title , negativeMsg);
    }

    @Override
    public void showAlertDialog3(final Context mContext , String title , final String[] items,
                                 final boolean[] checkedItems, String negativeMsg , String positiveMsg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                if(b){
                    showToast(mContext ,  "资讯内容：" + items[i] + ",被选中！");
                }else{
                    showToast(mContext ,  "资讯内容：" + items[i] + ",未被选中！");
                }
            }
        });
        builder.setPositiveButton(positiveMsg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                StringBuilder sb = new StringBuilder();
                for(int i = 0 ; i < items.length ; i++){
                    if(checkedItems[i]){
                        sb.append(items[i] + "；");
                    }
                }
                String content = "资讯内容：" + sb + "被选中！";
                showToast(mContext ,  content);
                Log.i(TAG, content);
            }
        });
        showDialog(builder , title , negativeMsg);
    }

    @Override
    public void showProgressDialog1(Context mContext , String title , String msg) {
        mPd1 = new ProgressDialog(mContext);
        mPd1.setTitle(title);
        mPd1.setMessage(msg);
        mPd1.setCancelable(false);
        mPd1.show();
    }

    public void dismissProgressDialog1(){
        mPd1.dismiss();
    }

    @Override
    public ProgressDialog showProgressDialog2(Context mContext , String title , String msg) {
        mPd2 = new ProgressDialog(mContext);
        mPd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mPd2.setMax(100);
        mPd2.setTitle(title);
        mPd2.setMessage(msg);
        mPd2.show();
        return mPd2;
    }

    public void dismissProgressDialog2(){
        mPd2.dismiss();
    }

    public void showDialog(AlertDialog.Builder builder , String title, String negativeMsg){
        builder.setTitle(title);
        builder.setNegativeButton(negativeMsg , null);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
