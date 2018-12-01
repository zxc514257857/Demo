package com.zhr.test.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zhr.test.DividerItemDecoration;
import com.zhr.test.R;
import com.zhr.test.adapter.PhoneRecyclerViewAdapter;
import com.zhr.test.helper.DefaultItemTouchHelper;
import com.zhr.test.helper.DefaultItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhoneActivity extends AppCompatActivity {

    private static final String TAG = PhoneActivity.class.getSimpleName();
    private Context mContext = PhoneActivity.this;
    private List<String> phoneNums = new ArrayList<>();
    private static final int RESULT_CODE_PHONE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        phoneNums.add("13512340000");
        phoneNums.add("13512340001");
        phoneNums.add("13512340002");
        phoneNums.add("13512340003");
        phoneNums.add("13512340004");
        phoneNums.add("13512340005");
        phoneNums.add("13512340006");

        RecyclerView rvPhone = findViewById(R.id.rv_phone);
        rvPhone.setLayoutManager(new LinearLayoutManager(mContext , LinearLayoutManager.VERTICAL , false));
        rvPhone.addItemDecoration(new DividerItemDecoration(mContext , LinearLayoutManager.VERTICAL));
        final PhoneRecyclerViewAdapter phoneRecyclerViewAdapter = new PhoneRecyclerViewAdapter(mContext, phoneNums);
        rvPhone.setAdapter(phoneRecyclerViewAdapter);
        phoneRecyclerViewAdapter.setOnItemClickListener(new PhoneRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i(TAG, "onItemClick: " + position);

                Intent intent = new Intent();
                intent.putExtra("phoneData" , phoneNums.get(position));
                setResult(RESULT_CODE_PHONE , intent);
                finish();
            }
        });

        phoneRecyclerViewAdapter.setOnItemLongClickListener(new PhoneRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                Log.i(TAG, "onItemLongClick: " + position);
//                // 长按删除Item
//                phoneRecyclerViewAdapter.removeItem(position);
            }
        });

        DefaultItemTouchHelperCallback.OnItemTouchListener onItemTouchListener = new DefaultItemTouchHelperCallback.OnItemTouchListener() {
            @Override
            public void onSwiped(int adapterPosition) {
                // 滑动删除 在数据集中删除
                phoneNums.remove(adapterPosition);
                phoneRecyclerViewAdapter.notifyItemRemoved(adapterPosition);
            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {
                // 长按拖拽 在数据集中交换
                Collections.swap(phoneNums , srcPosition , targetPosition);
                phoneRecyclerViewAdapter.notifyItemMoved(srcPosition , targetPosition);
                return false;
            }
        };

        DefaultItemTouchHelper defaultItemTouchHelper = new DefaultItemTouchHelper(onItemTouchListener);
        defaultItemTouchHelper.attachToRecyclerView(rvPhone);
        defaultItemTouchHelper.setDragEnable(true);
        defaultItemTouchHelper.setSwipeEnable(true);
    }
}
