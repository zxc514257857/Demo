package com.zhr.test.ui.main.controler;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.zhr.test.R;
import com.zhr.test.api.ApiConstants;
import com.zhr.test.bean.DataBean;
import com.zhr.test.bean.StoriesBean;
import com.zhr.test.ui.main.model.MvcModel;
import com.zhr.test.ui.main.model.MvcModelImpl;
import com.zhr.test.ui.main.view.MvcView;
import com.zhr.test.ui.main.view.MvcViewImpl;

import java.util.List;

/**
 * 案例三：常见的对话框显示
 * 确定取消对话框 单选对话框 多选对话框 加载中对话框 进度条对话框等
 */
public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;
    private MvcView mMv;
    private MvcModel mModel;
    private List<StoriesBean> mStories;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case ApiConstants.EventCode.GET_DATA_SUCCESS:
                    String json = (String) msg.obj;
                    DataBean dataBean = JSON.parseObject(json, DataBean.class);
                    mStories = dataBean.getStories();
                    break;

                default:
                    break;
            }

            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMv = new MvcViewImpl();
        mModel = new MvcModelImpl(mContext , mMv , mHandler);
        mModel.getData();
    }

    public void click01(View view){
        if(mStories != null){
            int randomInt = mModel.getRandomInt(mStories);
            int title = mStories.get(randomInt).getId();
            String msg = mStories.get(randomInt).getTitle();
            mMv.showAlertDialog1(mContext , title + "" , msg , "取消" , "确定");
        }
    }

    public void click02(View view){
        if(mStories != null) {
            int randomInt = mModel.getRandomInt(mStories);
            int id = mStories.get(randomInt).getId();
            String[] stringArr = mModel.getStringArr(mStories);
            mMv.showAlertDialog2(mContext, id + "", stringArr, "取消", "确定");
        }
    }

    public void click03(View view){
        int randomInt = mModel.getRandomInt(mStories);
        String title = mStories.get(randomInt).getTitle();
        String[] stringArr = mModel.getStringArr(mStories);
        boolean[] booleanArr = mModel.getBooleanArr(mStories);
        mMv.showAlertDialog3(mContext , title , stringArr , booleanArr ,"取消" , "确定");
    }

    public void click04(View view){
        mMv.showProgressDialog1(mContext , "注意！" , "软件下载中，请稍后！");
        mModel.simulateSleep1(4000 , mMv);
    }

    public void click05(View view){
        ProgressDialog pd2 = mMv.showProgressDialog2(mContext, "注意！", "软件下载中，请稍后！");
        mModel.simulateSleep2(40 , mMv , pd2);
    }
}
