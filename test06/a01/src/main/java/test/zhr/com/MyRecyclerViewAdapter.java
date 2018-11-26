package test.zhr.com;


import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by zhr on 2018/6/27.
 * Located:zmkj
 * Des:
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyHolder> {

    private static final String TAG = "MyRecyclerViewAdapter";
    private Context mContext;
    private ArrayList<String> lists;
    private RequestOptions mOptions;
    private FullScreenVideoView mVideo;
    private ImageView mImage;
    private int mPlayProgress;
    private String mUrl;
    private MediaPlayer mPlayer;

    public MyRecyclerViewAdapter(Context mContext , ArrayList<String> lists) {
        this.lists = lists;
        this.mContext = mContext;
        mOptions = new RequestOptions().skipMemoryCache(true).centerCrop();
        mPlayer = new MediaPlayer();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View image = LayoutInflater.from(mContext).inflate(R.layout.item_image , parent , false);
//        return new MyHolder(image);
        View video = LayoutInflater.from(mContext).inflate(R.layout.item_video , parent , false);
        return new MyHolder(video);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
//        Glide.with(mContext)
//                .load(lists.get(position % lists.size()))
//                .apply(mOptions)
//                .into(holder.mIv);

//        HttpProxyCacheServer proxy = AppApplication.getProxy(mContext);
//        final String proxyUrl = proxy.getProxyUrl(lists.get(position % lists.size()));

        JZVideoPlayerStandard jzVideoPlayerStandard = holder.mJzVideoPlayerStandard;
        jzVideoPlayerStandard.setUp(lists.get(position % lists.size()), JZVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN);
//        Glide.with(mContext).load("http://p8wwq0yqf.bkt.clouddn.com/2018-06-05/default1.png").apply(mOptions).into(jzVideoPlayerStandard.thumbImageView);
        jzVideoPlayerStandard.startVideo();
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private JZVideoPlayerStandard mJzVideoPlayerStandard;

        //        private ImageView mIv;
////        public MyHolder(View itemView) {
////            super(itemView);
////            mIv = itemView.findViewById(R.id.iv);
////        }

        public MyHolder(View itemView) {
            super(itemView);
            mJzVideoPlayerStandard = itemView.findViewById(R.id.vv);
        }
    }
}


