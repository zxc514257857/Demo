package test.zhr.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * VideoView
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
        list.add("http://txycdn.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4?ssig=4dfa668da47ff0548ac34f849f13f972&time_stamp=1530523064805");

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRv = findViewById(R.id.rv);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.setHasFixedSize(true);
        mRv.setAdapter(new MyRecyclerViewAdapter(this , list));
        mRv.scrollToPosition(list.size() * 10);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRv);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                mRv.smoothScrollToPosition(linearLayoutManager.findFirstVisibleItemPosition() + 1);
            }
        }, 20 *1000 , 20 * 1000, TimeUnit.MILLISECONDS);
    }
}
