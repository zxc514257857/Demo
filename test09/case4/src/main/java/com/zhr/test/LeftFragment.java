package com.zhr.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LeftFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 更改父布局的宽度和高度 是不起作用的 ， 设置true 才能起作用 ， 需要在内层布局里面做文章
        View inflate = inflater.inflate(R.layout.fragment_left, null);
        return inflate;
    }
}
