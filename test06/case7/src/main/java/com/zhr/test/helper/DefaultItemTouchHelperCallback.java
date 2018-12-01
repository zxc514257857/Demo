package com.zhr.test.helper;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class DefaultItemTouchHelperCallback extends ItemTouchHelper.Callback {

    public DefaultItemTouchHelperCallback(OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListener = onItemTouchListener;
    }

    /**
     * 是否可拖拽
     */
    private boolean isCanDrag = false;

    /**
     * 是否可滑动
     */
    private boolean isCanSwipe = false;

    /**
     * 当用户拖拽或者滑动Item时候告诉系统拖拽或滑动的方向
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        // 如果layoutManager为 GridLayoutManager
        if(layoutManager instanceof GridLayoutManager){
            // 拖拽
            int dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            // 滑动
            int swipeFlag = 0;
            // 标志位如果为0，表示这个功能被关闭
            return makeMovementFlags(dragFlag , swipeFlag);
            // 如果layoutManager为 LinearLayoutManager
        }else if(layoutManager instanceof LinearLayoutManager){
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int orientation = linearLayoutManager.getOrientation();
            int dragFlag = 0;
            int swipeFlag = 0;
            // 横向的LinearLayoutManager
            if(orientation == LinearLayoutManager.HORIZONTAL){
                // 左右拖拽
                dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                // 上下滑动
                swipeFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                // 竖向的LinearLayoutManager
            }else if(orientation == LinearLayoutManager.VERTICAL){
                // 上下拖拽
                dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                // 左右滑动
                swipeFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            }
            return makeMovementFlags(dragFlag , swipeFlag);
        }
        return 0;
    }

    /**
     * Item拖拽的时候回调
     * @param recyclerView
     * @param srcViewHolder
     * @param targetViewHolder
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder srcViewHolder, RecyclerView.ViewHolder targetViewHolder) {
        if(mOnItemTouchListener != null){
            return mOnItemTouchListener.onMove(srcViewHolder.getAdapterPosition() , targetViewHolder.getAdapterPosition());
        }
        return false;
    }

    /**
     * Item滑动的时候回调
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if(mOnItemTouchListener != null){
            mOnItemTouchListener.onSwiped(viewHolder.getAdapterPosition());
        }
    }

    /**
     * 设置是否可被拖拽
     * @param canDrag
     */
    public void setDragEnable(boolean canDrag) {
        this.isCanDrag = canDrag;
    }

    /**
     * 设置是否可被滑动
     * @param canSwipe
     */
    public void setSwipeEnable(boolean canSwipe) {
        this.isCanSwipe = canSwipe;
    }

    /**
     * Item被长按时是否可以拖拽
     * @return
     */
    public boolean isLongPressDragEnable(){
        return isCanDrag;
    }

    /**
     * Item是否可以滑动
     * @return
     */
    public boolean isSwipeEnable(){
        return isCanSwipe;
    }

    public interface OnItemTouchListener{

        /**
         * Item被滑动删除调用
         * @param adapterPosition
         */
        void onSwiped(int adapterPosition);

        /**
         * Item拖拽位置互换时调用
         * @param srcPosition
         * @param targetPosition
         * @return 开发者处理了操作应该返回true，开发者没有处理就返回false
         */
        boolean onMove(int srcPosition , int targetPosition);
    }

    /**
     * Item触摸监听
     */
    public OnItemTouchListener mOnItemTouchListener;

    public OnItemTouchListener getOnItemTouchListener() {
        return mOnItemTouchListener;
    }

    public void setOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        this.mOnItemTouchListener = onItemTouchListener;
    }
}
