package com.zhr.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CenterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 这里设置为可能会报两个错误
        // jumpDrawablesToCurrentState 或者 The specified child already has a parent. You must call removeView() on the child’s parent first
        View inflate = inflater.inflate(R.layout.fragment_center, container, false);
        return inflate;
    }
}
