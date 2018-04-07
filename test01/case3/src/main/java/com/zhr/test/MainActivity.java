package com.zhr.test;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 案例三：点击事件的五种写法
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;
    private Button mBt1;
    private Button mBt2;
    private Button mBt3;
    private Button mBt4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }

    public void initView(){
        mBt1 = (Button) findViewById(R.id.bt1);
        mBt2 = (Button) findViewById(R.id.bt2);
        mBt3 = (Button) findViewById(R.id.bt3);
        mBt4 = (Button) findViewById(R.id.bt4);
    }

    public void initData(){
        // 点击事件写法一：匿名内部类
        mBt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SnackBarUtils.ShortSnackbar(view, "点击了按钮01" , Color.WHITE , Color.RED).show();
            }
        });

        // 点击事件写法二：让Activity实现OnClickListener接口
        mBt2.setOnClickListener(this);

        // 点击事件写法三：内部类
        mBt3.setOnClickListener(listener);

        //点击事件写法四：自己建一个类实现OnClickListener接口
        mBt4.setOnClickListener(new MyClick());
    }

    @Override
    public void onClick(View view) {
        SnackBarUtils.ShortSnackbar(view, "点击了按钮02" , Color.WHITE , Color.RED).show();
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SnackBarUtils.ShortSnackbar(view, "点击了按钮03" , Color.WHITE , Color.RED).show();
        }
    };

    private class MyClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            SnackBarUtils.ShortSnackbar(view, "点击了按钮04" , Color.WHITE , Color.RED).show();
        }
    }

    /**
     * 点击事件写法五：xml中的onClick
     */
    public void click(View view){
        SnackBarUtils.ShortSnackbar(view, "点击了按钮05" , Color.WHITE , Color.RED).show();
    }
}
