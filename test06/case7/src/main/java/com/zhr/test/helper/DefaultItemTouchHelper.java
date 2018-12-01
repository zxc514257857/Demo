package com.zhr.test.helper;

import android.support.v7.widget.helper.YolandaItemTouchHelper;

public class DefaultItemTouchHelper extends YolandaItemTouchHelper {

    private DefaultItemTouchHelperCallback mDefaultItemTouchHelperCallback;

    public DefaultItemTouchHelper(DefaultItemTouchHelperCallback.OnItemTouchListener onItemTouchListener) {
        super(new DefaultItemTouchHelperCallback(onItemTouchListener));
        mDefaultItemTouchHelperCallback = (DefaultItemTouchHelperCallback) getCallback();
    }

    /**
     * 设置是否可以被拖拽
     * @param canDrag 是true，否false
     */
    public void setDragEnable(boolean canDrag) {
        mDefaultItemTouchHelperCallback.setDragEnable(canDrag);
    }

    /**
     * 设置是否可以被滑动
     * @param canSwipe 是true，否false
     */
    public void setSwipeEnable(boolean canSwipe) {
        mDefaultItemTouchHelperCallback.setSwipeEnable(canSwipe);
    }
}
