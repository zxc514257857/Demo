package com.zhr.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class MyPagerAdapter extends PagerAdapter {

    private List<String> mData;

    public MyPagerAdapter(List<String> data){
        this.mData = data;
    }

    /**
     * 返回当前有效视图个数
     * @return
     */
    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    /**
     * 用来判断 instantiateItem(ViewGroup, int)函数所返回来的Key与一个页面视图是否是代表的同一个视图
     * 一般为 view == object
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (View)object;
    }

    /**
     * 加载当前视图 ， 默认会预加载当前页面的 前一页和后一页
     * @param container
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        这两者的区别是什么样子的？  https://blog.csdn.net/chenliguan/article/details/82314122

        // 它有三种设置方法：LayoutInflater.from(context).inflate(R.layout.xxx , null) 等同于 View.inflate(container.getContext() , R.layout.xxx , null)
        // 表示只解析布局文件的子View
        // LayoutInflater.from(context).inflate(R.layout.xxx , container , true) 表示使用布局文件的父布局
        // LayoutInflater.from(context).inflate(R.layout.xxx , container , false) 表示父布局的宽高显示正常，但不使用父布局
//        LayoutInflater.from(container.getContext()).inflate()

        // 其实View.inflate 内部也调用了LayoutInflater.inflate
        // 这里的root为null 表示 布局文件最外层设置任何值都没有意义，仅仅是解析布局文件的子View
        // 如果这里的root为container ， 即等同于 LayoutInflater.inflate（R.layout.xxx , container , true） 表示 使用了布局文件的父布局
        // 在已经有父布局的情况下再使用这个父布局会报错误
//        View.inflate(container.getContext() , R.layout.xxx , null)
        return super.instantiateItem(container, position);
    }

    /**
     * 销毁当前视图
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((View) object);
        super.destroyItem(container, position, object);
    }
}
