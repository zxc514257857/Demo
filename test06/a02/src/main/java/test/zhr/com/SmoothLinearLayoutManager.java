package test.zhr.com;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

/**
 * Created by zhr on 2018/6/27.
 * Located:zmkj
 * Des:
 */
public class SmoothLinearLayoutManager extends LinearLayoutManager {

    public SmoothLinearLayoutManager(Context context) {
        super(context);
    }

    public SmoothLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller =
                new LinearSmoothScroller(recyclerView.getContext()) {
                    protected float calculateSpeedPerPixelFloat(DisplayMetrics displayMetrics) {
                        //返回0.2
                        return 0.01f;
                    }
                };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }
}