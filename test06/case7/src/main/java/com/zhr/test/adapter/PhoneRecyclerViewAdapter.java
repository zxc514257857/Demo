package com.zhr.test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhr.test.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PhoneRecyclerViewAdapter extends RecyclerView.Adapter<PhoneRecyclerViewAdapter.MyViewHolder> {

    private static final String TAG = PhoneRecyclerViewAdapter.class.getSimpleName();
    private Context mContext;
    private List<String> mPhoneNums;

    public PhoneRecyclerViewAdapter(Context context, List<String> phoneNums) {
        this.mContext = context;
        this.mPhoneNums = phoneNums;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.phone_list , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        TextView tvPhone = holder.mTvPhone;
        if(mPhoneNums != null){
            tvPhone.setText(mPhoneNums.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mPhoneNums == null ? 0 : mPhoneNums.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.ll_phone)
        LinearLayout mLlPhone;
        @Bind(R.id.tv_phone)
        TextView mTvPhone;

        MyViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);

            if(mOnItemClickListener != null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(itemView , getAdapterPosition());
                    }
                });
            }

            if(mOnItemLongClickListener != null){
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mOnItemLongClickListener.onItemLongClick(itemView , getAdapterPosition());
                        return true;
                    }
                });
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public void removeItem(int position){
        mPhoneNums.remove(position);
        notifyItemRemoved(position);
    }
}
