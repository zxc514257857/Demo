package test.zhr.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dueeeke.videoplayer.player.IjkVideoView;

import java.util.ArrayList;

/**
 * VideoView
 */
public class MainActivity extends AppCompatActivity{

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

        mRv = findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        MyAdapter adapter = new MyAdapter(this , list);
        mRv.setAdapter(adapter);

//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                mRv.smoothScrollToPosition(viewPagerLayoutManager.findFirstVisibleItemPosition() + 1);
//            }
//        }, 10 *1000 , 10 * 1000, TimeUnit.MILLISECONDS);

        mRv.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                IjkVideoView ijkVideoView = view.findViewById(R.id.vv);

                if (ijkVideoView != null && !ijkVideoView.isFullScreen()) {
                    ijkVideoView.stopPlayback();
                }
            }
        });


    }
}