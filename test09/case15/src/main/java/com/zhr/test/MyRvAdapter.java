package com.zhr.test;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.ViewHolder> {

    @NonNull
    @Override
    public MyRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.img_item, parent, false);
        return new ViewHolder(inflate);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull final MyRvAdapter.ViewHolder holder, int position) {
        // Glide的使用参考 ： https://www.jianshu.com/p/9db8f314b286
        // 路过图床 https://imgchr.com/  加载的图片存放地址
        RequestOptions options = new RequestOptions();
        // 加载占位图
        options.placeholder(R.mipmap.ic_launcher);
        // 加载错误图
        options.error(R.mipmap.ic_launcher);
        // 禁用内存缓存功能(默认为false 不禁用)
        options.skipMemoryCache(false);
        // 指定硬盘缓存策略 默认是AUTOMATIC（自动）  Data 缓存原始图片  Resourse 缓存转换过的图片
        options.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        // 手动指定图片尺寸   Target.SIZE_ORIGINAL (原图大小)
        options.override(300 , 300);


        Glide.with(holder.itemView.getContext())
                // asBitmap 意思是只允许加载静态图片 ， 如果是gif图 ， 只会加载gif图的第一帧
//                .asBitmap()
                // 使用asDrawable() 比asBitmap() 能节省部分内存
                .asDrawable()
//                .asFile()
//                .asGif()
                // 加载大图 报错：Failed to load resource
                .load("https://s1.ax1x.com/2020/07/09/UmELwR.jpg")
//                .load("https://s1.ax1x.com/2020/07/09/UmELwR.md.jpg")
                // submit 必须在子线程中执行，意思是下载图片（不传参是下载原图片，传参是下载指定尺寸的图片） 可以用于压缩图片
//                .submit()
                .apply(options)
                .into(holder.mIv);
        // GlideApp 的使用
//        GlideApp.with(holder.itemView.getContext()).load("https://s1.ax1x.com/2020/07/09/UmELwR.jpg")
//                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.mIv);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Glide清除磁盘缓存（必须在子线程中执行）
                Glide.get(holder.itemView.getContext()).clearDiskCache();
            }
        }).start();
        // Glide清除内存缓存（必须在主线程中执行）
        Glide.get(holder.itemView.getContext()).clearMemory();
    }

    /**
     * 当View被回收的时候调用此方法
     * @param holder
     */
    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
        ImageView imageView = holder.mIv;
        if(null != imageView){
            // 当view被回收的时候 清除对应的图片加载请求
            Glide.with(imageView.getContext()).clear(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.iv);
        }
    }
}