package com.zhr.test.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zhr.test.DividerItemDecoration;
import com.zhr.test.R;
import com.zhr.test.adapter.SmsRecyclerViewAdapter;
import com.zhr.test.helper.DefaultItemTouchHelper;
import com.zhr.test.helper.DefaultItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmsActivity extends AppCompatActivity {

    private static final String TAG = SmsActivity.class.getSimpleName();
    private Context mContext = SmsActivity.this;
    private List<String> smsMessages = new ArrayList<>();
    private static final int RESULT_CODE_SMS = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        smsMessages.add("春节的欢聚已分开，鞭炮声声已渐远。各自奔往工作地，踌躇满志加紧干。同事见面问新年，祝福的话语说不完。亲人的嘱托在耳畔，勤奋工作莫迟延。愿你年后心情好，事业更上一层楼！");
        smsMessages.add("欢快的歌声尽情飘，温暖的春风暖心潮。万千的喜气多热闹，吉祥的日子要来到。发条短信问个好，财源广进吉星照。万事顺利开怀笑，羊年幸福乐逍遥。");
        smsMessages.add("心情预报：今夜到明早想你，预计下午转为很想你，受此情绪影响，傍晚将转为暴想，此类天气将持续到见你为止。");
        smsMessages.add("世界如此忙碌，用心的人就会幸福;想你的脸，心里就温暖;想你的嘴，笑容跟着灿烂！随着新年的到来，关心你的人要跟你说声：新年快乐！");
        smsMessages.add("羊年到，身体很重要，应酬拒不掉，少喝酒多吃菜，表示心意就算了;祝愿老板身体好，越过越开心，财富滚滚来，生活越来越美好。");
        smsMessages.add("新年到了，衷心祝福你。祝你年年圆满如意，月月事事顺心，日日喜悦无忧，时时高兴欢喜，刻刻充满朝气！");
        smsMessages.add("勇攀高峰不怕险，开辟新径铸辉煌。勤奋创业走新道，学做银羊敢闯关。羊年要走阳关道，排除万难创业路。艰苦奋斗能胜天，幸福生活如意添。愿你羊年心想事业成!");

        RecyclerView rvSms = findViewById(R.id.rv_sms);
        rvSms.setLayoutManager(new LinearLayoutManager(mContext , LinearLayoutManager.VERTICAL , false));
        rvSms.addItemDecoration(new DividerItemDecoration(mContext , LinearLayoutManager.VERTICAL));
        final SmsRecyclerViewAdapter smsRecyclerViewAdapter = new SmsRecyclerViewAdapter(mContext, smsMessages);
        rvSms.setAdapter(smsRecyclerViewAdapter);
        rvSms.setItemAnimator(new DefaultItemAnimator());
        smsRecyclerViewAdapter.setOnItemClickListener(new SmsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i(TAG, "onItemClick: " + position);

                Intent intent = new Intent();
                intent.putExtra("smsData" , smsMessages.get(position));
                setResult(RESULT_CODE_SMS , intent);
                finish();
            }
        });

        smsRecyclerViewAdapter.setOnItemLongClickListener(new SmsRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                Log.i(TAG, "onItemLongClick: " + position);
                // 长按删除Item
//                smsRecyclerViewAdapter.removeItem(position);
            }
        });

        DefaultItemTouchHelperCallback.OnItemTouchListener onItemTouchListener = new DefaultItemTouchHelperCallback.OnItemTouchListener() {
            @Override
            public void onSwiped(int adapterPosition) {
                // 滑动删除后 从数据集中删除 并刷新Item
                if(smsMessages != null){
                    smsMessages.remove(adapterPosition);
                    smsRecyclerViewAdapter.notifyItemRemoved(adapterPosition);
                }
            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {
                if(smsMessages != null){
                    // 交换数据集中的位置
                    Collections.swap(smsMessages , srcPosition , targetPosition);
                    smsRecyclerViewAdapter.notifyItemMoved(srcPosition , targetPosition);
                }
                return false;
            }
        };

        DefaultItemTouchHelper defaultItemTouchHelper = new DefaultItemTouchHelper(onItemTouchListener);
        defaultItemTouchHelper.attachToRecyclerView(rvSms);
        // 打开条目拖拽
        defaultItemTouchHelper.setDragEnable(true);
        // 打开条目滑动删除
        defaultItemTouchHelper.setSwipeEnable(true);
    }
}
