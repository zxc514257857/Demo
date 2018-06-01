package com.zhr.test;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.zhr.test.string.cache.ThreeLevelCache;
import com.zhr.test.video.OneLevelCache;

/**
 * 案例八：android缓存之图片缓存 目前已经有成熟的框架如Glide picasso等 现在是在研究三级缓存思想
 * 三级缓存是指：内存缓存(LruCache)、硬盘缓存(DiskLruCache)以及网络缓存(HttpURLConnection)；通过Http请求网络上的内容，请求成功之后在内存及硬盘缓存一份；
 * 需要取的时候先从内存中取，没有取到的话从硬盘中取，如果都没有取到的话就从网络上取
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext = MainActivity.this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ToolBar和FloatingActionButton的逻辑
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).show();
            }
        });

        // 展示图片
        showImg();
        // 展示文字
        showStr();
        // 展示视频
        showVideo();
    }

    /**
     * 展示图片
     */
    private void showImg() {
        // 图片的三级缓存
        String mImgUrl = "https://b-ssl.duitang.com/uploads/item/201605/29/20160529034112_NPV8K.thumb.700_0.jpeg";
        ImageView mIv = findViewById(R.id.iv);
        com.zhr.test.image.ThreeLevelCache mImageCache = com.zhr.test.image.ThreeLevelCache.getInstance(mContext, AppConstants.DISK_CACHE_IMAGE);
        if (mImageCache != null) {
            mImageCache.showImageView(mImgUrl, mIv);
        }else{
            Log.i(TAG, "can not show imageView , because imageCache is null");
        }
    }

    /**
     * 展示文字
     */
    private void showStr() {
        // 文字的三级缓存
        TextView mTv = findViewById(R.id.tv);
        ThreeLevelCache mStringCache = ThreeLevelCache.getInstance(mContext);
        if(mStringCache != null){
            mStringCache.showTextView(mContext , "DISK_CACHE_STR", mTv);
        }else{
            Log.i(TAG, "can not show textView , because stringCache is null");
        }
    }

    /**
     * 展示视频
     */
    private void showVideo() {
        // 视频缓存
        String mVideoUrl = "http://p8wwq0yqf.bkt.clouddn.com/2018-05-29/bunch_planting001.mp4";
        VideoView mVv = findViewById(R.id.vv);
        OneLevelCache mVideoCache = OneLevelCache.getInstance();
        if(mVideoCache != null){
            mVideoCache.showVideoView(mContext , mVideoUrl , mVv);
        }else{
            Log.i(TAG, "can not show videoView , because videoCache is null");
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        // 展示图片
        showImg();
        // 展示文字
        showStr();
        // 展示视频
        showVideo();
    }

    /**
     * 创建右上角OptionsMenu菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * 当右上角OptionsMenu菜单中item被选中时
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings1:
                Log.i(TAG, "action_settings1");
                break;

            case R.id.action_settings2:
                Log.i(TAG, "action_settings2");
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
