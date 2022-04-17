package com.zhr.test;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * AndroidStudio 4.0 升级之后，新建Java class 没有public 关键字， 需要在Settings - editor - File and code templates - Class
 * 将里面的模版改成：
 * <p>
 * #if (${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
 * #if (${IMPORT_BLOCK} != "")${IMPORT_BLOCK}
 * #end
 * #parse("File Header.java")
 * #if (${VISIBILITY} == "public") public #end #if (${VISIBILITY} == "") public #end #if (${ABSTRACT} == "true")abstract #end #if (${FINAL} == "true")final #end class ${NAME} #if (${SUPERCLASS} != "")extends ${SUPERCLASS} #end #if (${INTERFACES} != "") implements ${INTERFACES} #end {}
 */
public class MyRvPagerAdapter extends RecyclerView.Adapter<MyRvPagerAdapter.ViewPagerViewHolder> {

    private static final String TAG = "MyRvPagerAdapter";
    private List<String> mData;

    public MyRvPagerAdapter(List<String> data) {
        this.mData = data;
    }

    /**
     * 当需要创建ViewHolder的时候调用 每一个Rv的Item都有一个ViewHolder ， ViewHolder所有的视图创建之后 是可以复用的
     * https://blog.csdn.net/csdn_aiyang/article/details/80094302
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //  inflate(R.layout.rv_pager_item, null) 如果这样写是没有使用父布局的 ， 会报这个错误 Pages must fill the whole ViewPager2
        // item的父布局一定要注意，这里是需要满屏显示的，如果是RecyclerView 不需要满屏显示，则这里的父布局高度需要酌情设置为wrap_content，否则也是占满全屏
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_pager_item, parent , false);
        Log.e(TAG , "onCreateViewHolder: ");
        return new ViewPagerViewHolder(inflate);
    }

    /**
     * 当ViewHolder创建后，需要向其中填充数据的时候调用
     * onBindViewHolder是每个Item第一次创建时都会调用，后面Item被销毁时，再次移动到此Item会再次调用
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
        holder.mTv.setText(mData.get(position));
        Log.e(TAG , "onBindViewHolder: ");
    }

    /**
     * 返回RecyclerView 中的Item的个数
     * @return
     */
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    /**
     * ViewHolder中的数据是可以复用的
     */
    public class ViewPagerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv)
        AppCompatTextView mTv;

        public ViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}