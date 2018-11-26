package test.zhr.com;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.dingmouren.layoutmanagergroup.viewpager.OnViewPagerListener;
import com.dingmouren.layoutmanagergroup.viewpager.ViewPagerLayoutManager;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * VideoView  播放异常的情况没有处理
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> list = new ArrayList<>();

        list.add("http://p8wwq0yqf.bkt.clouddn.com/2018-06-05/download.mp4");
        list.add("http://p8wwq0yqf.bkt.clouddn.com/2018-06-05/download1.mp4");
        list.add("http://p8wwq0yqf.bkt.clouddn.com/2018-06-05/22222.mp4");
        list.add("http://p8wwq0yqf.bkt.clouddn.com/2018-06-17/bunch_planting02.mp4");

        final ViewPagerLayoutManager viewPagerLayoutManager = new ViewPagerLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRv = findViewById(R.id.rv);
        mRv.setLayoutManager(viewPagerLayoutManager);
//        mRv.setHasFixedSize(true);
        mRv.setAdapter(new MyAdapter(this , list));
//        mRv.scrollToPosition(list.size() * 10);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                mRv.smoothScrollToPosition(viewPagerLayoutManager.findFirstVisibleItemPosition() + 1);
            }
        }, 20 *1000 , 20 * 1000, TimeUnit.MILLISECONDS);

//        PagerSnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(mRv);

        viewPagerLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {

            @Override
            public void onInitComplete() {
                Log.e(TAG,"onInitComplete");
                playVideo(0);
            }

            @Override
            public void onPageRelease(boolean isNext,int position) {
                Log.e(TAG,"释放位置:"+position +" 下一页:"+isNext);
                int index = 0;
                if (isNext){
                    index = 0;
                }else {
                    index = 1;
                }
//                releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position,boolean isBottom) {
                Log.e(TAG,"选中位置:"+position+"  是否是滑动到底部:"+isBottom);
                playVideo(0);
            }
        });
    }

    private void playVideo(int position) {
        View itemView = mRv.getChildAt(0);
        final VideoView videoView = itemView.findViewById(R.id.vv);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        videoView.start();
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                mediaPlayer[0] = mp;
                mp.setLooping(true);
                return false;
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        });
    }

    private void releaseVideo(int index){
        View itemView = mRv.getChildAt(index);
        final VideoView videoView = itemView.findViewById(R.id.vv);
        videoView.stopPlayback();
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        private Context mContext;
        private ArrayList<String> lists;
        public MyAdapter(Context mContext , ArrayList<String> lists){
            this.lists = lists;
            this.mContext = mContext;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
//            holder.img_thumb.setImageResource(imgs[position%2]);
            holder.videoView.setVideoPath(lists.get(position % lists.size()));
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            FullScreenVideoView videoView;
            public ViewHolder(View itemView) {
                super(itemView);
                videoView = itemView.findViewById(R.id.vv);
            }
        }
    }
}
