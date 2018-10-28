package com.zhr.test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 案例一：ListView的简单使用
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;
    private MvcView mMv;

    @Bind(R.id.lv)
    ListView mLv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mMv = new MvcViewImpl();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mMv.showToast(mContext , "第" + i + "个条目被点击了！");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.base_adapter:
                BaseAdapter baseAdapter = new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return 1000;
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return 0;
                    }

                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        return mMv.showTextView(mContext, "我是文本第：" + i);
                    }
                };

                mLv.setAdapter(baseAdapter);
                mLv.setOnItemClickListener(this);
                break;

            case R.id.array_adapter:
                // 上下文 布局 数据源
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext ,
                        android.R.layout.simple_list_item_1 , new String[]{"1" , "2" , "3" , "4" ,
                        "1" , "2" , "3" , "4" , "1" , "2" , "3" , "4" ,"1" , "2" , "3" , "4" ,
                        "1" , "2" , "3" , "4" , "1" , "2" , "3" , "4" ,"1" , "2" , "3" , "4" ,
                        "1" , "2" , "3" , "4" , "1" , "2" , "3" , "4" ,"1" , "2" , "3" , "4" ,
                        "1" , "2" , "3" , "4" , "1" , "2" , "3" , "4" ,"1" , "2" , "3" , "4" ,
                        "1" , "2" , "3" , "4" , "1" , "2" , "3" , "4"});
                mLv.setAdapter(arrayAdapter);
                mLv.setOnItemClickListener(this);

                break;

            case R.id.simple_adapter:
                List<Map<String , Object>> mapList = new ArrayList<>();
                for(int i = 0 ; i <= 20 ; i++){
                    Map<String , Object> map = new HashMap<>();
                    map.put("imageView" , R.drawable.qq);
                    map.put("title" , "测试帐号" + i);
                    map.put("textView" , "18:00");
                    map.put("content" , "王健林:先定一个小目标，比如挣它一个亿");
                    mapList.add(map);
                }
                // 上下文 数据集 布局 键值对中的键对应的字符串数组 键值对中的键对应的资源id
                SimpleAdapter simpleAdapter = new SimpleAdapter(mContext, mapList , R.layout.simple_adapter_item ,
                       new String[]{"imageView" , "title" , "textView" , "content"} , new int[]{R.id.imageView ,
                R.id.title , R.id.textView , R.id.content});
                mLv.setAdapter(simpleAdapter);
                mLv.setOnItemClickListener(this);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
