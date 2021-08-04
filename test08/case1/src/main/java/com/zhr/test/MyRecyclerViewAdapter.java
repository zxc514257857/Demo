package com.zhr.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private final List<String> mList;

    public MyRecyclerViewAdapter(List<String> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyRecyclerViewAdapter.MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.mTv.setText(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv);
        }
    }
}