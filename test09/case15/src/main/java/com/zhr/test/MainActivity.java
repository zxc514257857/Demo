package com.zhr.test;

import android.os.Bundle;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

/**
 * Android 高效加载大图处理
 * https://github.com/Curzibn/Luban
 * https://github.com/nanchen2251/CompressHelper
 * https://muyangmin.github.io/glide-docs-cn/
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRv = findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL, false));
        mRv.setAdapter(new MyRvAdapter());

        // 判断当前线程是在子线程还是在主线程
        if(Looper.myLooper() == Looper.getMainLooper()){
            // 在主线程
        }else {
            // 在子线程
        }

        // 判断Rv是滑动状态还是滑动停止状态
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int scrollState) {
                super.onScrollStateChanged(recyclerView, scrollState);
                switch (scrollState){
                    // 暂停
                    case RecyclerView.SCROLL_STATE_IDLE:
                        Glide.with(recyclerView.getContext()).resumeRequests();
                        break;
                    // 滑动
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        Glide.with(recyclerView.getContext()).pauseRequests();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
