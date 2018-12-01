package com.zhr.test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhr.test.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SmsRecyclerViewAdapter extends RecyclerView.Adapter<SmsRecyclerViewAdapter.MyViewHolder> {

    private static final String TAG = SmsRecyclerViewAdapter.class.getSimpleName();
    private Context mContext;
    private List<String> mSmsNums;

    public SmsRecyclerViewAdapter(Context context, List<String> smsNums) {
        this.mContext = context;
        this.mSmsNums = smsNums;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.sms_list , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView tvSms = holder.mTvSms;
        if(mSmsNums != null){
            tvSms.setText(mSmsNums.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mSmsNums == null ? 0 : mSmsNums.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_sms)
        TextView mTvSms;

        MyViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
            mTvSms = itemView.findViewById(R.id.tv_sms);

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
        mOnItemClickListener = onItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public void removeItem(int position){
        mSmsNums.remove(position);
        notifyItemRemoved(position);
    }
}
