package test.zhr.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.dingmouren.layoutmanagergroup.viewpager.OnViewPagerListener;
import com.dingmouren.layoutmanagergroup.viewpager.ViewPagerLayoutManager;

import java.util.ArrayList;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * VideoView
 */
public class MainActivity extends AppCompatActivity implements OnViewPagerListener {

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
        MyAdapter adapter = new MyAdapter(this , list);
        mRv.setAdapter(adapter);
        viewPagerLayoutManager.setOnViewPagerListener(this);

//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                mRv.smoothScrollToPosition(viewPagerLayoutManager.findFirstVisibleItemPosition() + 1);
//            }
//        }, 10 *1000 , 10 * 1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void onInitComplete() {
        // 初始化成功
        Log.e(TAG, "onInitComplete: ");
        playVideo();
    }

    @Override
    public void onPageRelease(boolean b, int i) {
        // 释放
        Log.e(TAG, "onPageRelease: " + b +" 下一页:" + i);
        int index = 0;
        if (b){
            index = 0;
        }else {
            index = 1;
        }
        releaseVideo(index);
    }

    @Override
    public void onPageSelected(int i, boolean b) {
        // 选中
        Log.e(TAG, "onPageSelected: " + i);
        playVideo();
    }

    private void playVideo() {
        View itemView = mRv.getChildAt(0);
        JZVideoPlayerStandard jzVideoPlayerStandard = itemView.findViewById(R.id.vv);
        jzVideoPlayerStandard.startVideo();
//        VideoView videoView = itemView.findViewById(R.id.vv);
//        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
//        videoView.start();
//        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
//            @Override
//            public boolean onInfo(MediaPlayer mp, int what, int extra) {
//                mediaPlayer[0] = mp;
//                mp.setLooping(true);
//                return false;
//            }
//        });
//        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//            @Override
//            public boolean onError(MediaPlayer mp, int what, int extra) {
//                return true;
//            }
//        });
    }

    private void releaseVideo(int index) {
        View itemView = mRv.getChildAt(index);
        JZVideoPlayerStandard jzVideoPlayerStandard = itemView.findViewById(R.id.vv);
        jzVideoPlayerStandard.release();
//        VideoView videoView = itemView.findViewById(R.id.vv);
//        videoView.stopPlayback();
    }
}