package test.zhr.com;


import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

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

    public MyRecyclerViewAdapter(Context mContext , ArrayList<String> lists) {
        this.lists = lists;
        this.mContext = mContext;
        mOptions = new RequestOptions().skipMemoryCache(true).centerCrop();

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View video = LayoutInflater.from(mContext).inflate(R.layout.item_video , parent , false);
        return new MyHolder(video);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        VideoView videoView = holder.mVideoView;
        final ProgressBar pb = holder.mPb;
        videoView.setVideoPath(lists.get(position % lists.size()));
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                pb.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private VideoView mVideoView;
        private ProgressBar mPb;

        public MyHolder(View itemView) {
            super(itemView);
            mVideoView = itemView.findViewById(R.id.vv);
            mPb = itemView.findViewById(R.id.pb);
        }
    }
}


