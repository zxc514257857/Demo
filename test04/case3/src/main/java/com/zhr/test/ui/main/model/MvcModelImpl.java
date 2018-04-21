package com.zhr.test.ui.main.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import com.zhr.test.api.ApiConstants;
import com.zhr.test.bean.StoriesBean;
import com.zhr.test.ui.main.view.MvcView;
import com.zhr.test.utils.ThreadUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;
import java.util.Random;

import okhttp3.Call;

public class MvcModelImpl implements MvcModel{

    private static final String TAG = MvcModelImpl.class.getSimpleName();
    private Context mContext;
    private MvcView mMvcView;
    private Handler mHandler;

    public MvcModelImpl(Context mContext , MvcView mMvcView , Handler mHandler) {
        this.mContext = mContext;
        this.mMvcView = mMvcView;
        this.mHandler = mHandler;
    }

    @Override
    public void getData() {
        OkHttpUtils
                .get()
                .url(ApiConstants.Url.ZHIHU_HOST)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mMvcView.showToast(mContext , "获取数据失败！" + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Message msg = Message.obtain();
                        msg.what = ApiConstants.EventCode.GET_DATA_SUCCESS;
                        msg.obj = response;
                        mHandler.sendMessage(msg);
                        Log.i(TAG, "获取数据成功！");
                    }
                });
    }

    @Override
    public int getRandomInt(List<StoriesBean> mStories) {
        if(mStories != null){
            Random random = new Random();
            return random.nextInt(mStories.size());
        }else{
            return 0;
        }
    }

    @Override
    public String[] getStringArr(List<StoriesBean> mStories) {
        String[] arr = new String[mStories.size()];
        if(mStories != null){
            for(int i = 0 ; i < mStories.size() ; i++){
                arr[i] = mStories.get(i).getTitle();
            }
            return arr;
        }else{
            return null;
        }
    }

    public boolean[] getBooleanArr(List<StoriesBean> mStories){
        boolean[] arr = new boolean[mStories.size()];
        if(mStories != null){
            for(int i = 0 ; i < mStories.size() ; i++){
                arr[i] = false;
            }
            return arr;
        }else{
            return null;
        }
    }

    public void simulateSleep1(final long ms , final MvcView mMv){
        ThreadUtils.runInThread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(ms);
                mMv.dismissProgressDialog1();
            }
        });
    }

    public void simulateSleep2(final long ms , final MvcView mMv , final ProgressDialog mPd2){
        ThreadUtils.runInThread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i <= 100 ; i++){
                    SystemClock.sleep(ms);
                    mPd2.setProgress(i);
                }
                mMv.dismissProgressDialog2();
            }
        });
    }
}
