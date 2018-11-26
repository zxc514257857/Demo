package test.zhr.com;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by zhr on 2018/7/3.
 * Located:zmkj
 * Des:
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

    private Context context;
    private ArrayList<String> list;
    private int[] videos = {R.raw.video_1,R.raw.video_2};

    public MyAdapter(Context context , ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        // 只是提供数据
        JZVideoPlayerStandard videoView = holder.mVideoView;
        videoView.setUp(list.get(position % list.size()) , JZVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN);
//        videoView.setVideoURI(Uri.parse("android.resource://" + context.getPackageName() + "/"+ videos[position % 2]));
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private JZVideoPlayerStandard mVideoView;

        public MyHolder(View itemView) {
            super(itemView);
            mVideoView = itemView.findViewById(R.id.vv);
        }
    }
}
