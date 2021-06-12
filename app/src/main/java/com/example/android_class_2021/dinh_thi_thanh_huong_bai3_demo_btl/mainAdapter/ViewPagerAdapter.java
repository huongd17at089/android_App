package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainFragment.FragLession;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainFragment.FragSuggest;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private int numPager;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numPager = behavior;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new FragSuggest();
            case 1 :
                return new FragLession();
//
//            case 2:
//                return new FragStatistic();
            default:
                return new FragSuggest();
        }
    }

    @Override
    public int getCount() {
        return numPager;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return "Chủ đề";
            case 1 :
                return "Quiz";
//            case 2:
//                return "Statistic";
            default:
                return "Chủ đề";
        }
    }
}
