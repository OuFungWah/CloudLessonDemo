package com.crazywah.cloudlessondemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.crazywah.cloudlessondemo.R;
import com.crazywah.cloudlessondemo.widget.CustomScrollView;

public class AFragment extends Fragment {

    private int scrollDistance;

    private CustomScrollView scrollView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        scrollView = view.findViewById(R.id.scroll_view);
        return view;
    }

    public boolean isTop(){
        boolean flag = true;

        return flag;
    }

}
