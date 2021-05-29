package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Adapter extends FragmentStatePagerAdapter {

    private int numPage = 4;
    public Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new Home();
            case 1: return  new Lession();
            case 2 : return  new Dict();
            case 3: return new Profile();
            default: return  new Home();
        }

    }

    @Override
    public int getCount() {
        return numPage;
    }
}