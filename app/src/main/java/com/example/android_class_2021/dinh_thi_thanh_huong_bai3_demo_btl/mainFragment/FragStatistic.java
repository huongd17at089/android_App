package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;


public class FragStatistic extends Fragment {



    public FragStatistic() {
        // Required empty public constructor
    }



    public static FragStatistic newInstance(String param1, String param2) {
        FragStatistic fragment = new FragStatistic();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_statistic, container, false);
    }
}