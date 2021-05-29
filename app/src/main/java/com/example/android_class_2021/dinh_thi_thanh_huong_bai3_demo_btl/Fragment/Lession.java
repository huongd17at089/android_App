package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainAdapter.ViewPagerAdapter;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainFragment.FragLession;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainFragment.FragSuggest;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainFragment.Tranform;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Topic;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Lession#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Lession extends Fragment implements FragSuggest.SendTopic{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TabLayout tab;
    private ViewPager vp;
    private ViewPagerAdapter adapter;
    private View v;

    public Lession() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Lession newInstance(String param1, String param2) {
        Lession fragment = new Lession();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_trang_chu, container, false);
        tab = v.findViewById(R.id.main_tab_layout);
        vp = v.findViewById(R.id.main_viewPager);
        adapter = new ViewPagerAdapter(getChildFragmentManager(), 2);
        vp.setAdapter(adapter);
        tab.setSelectedTabIndicatorColor(Color.parseColor("#816AD6"));
        tab.setupWithViewPager(vp);
        vp.setPageTransformer(true, new Tranform());
        // Inflate the layout for this fragment
//        FragmentManager fragmentManager = getParentFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.fragment_container,fragment);
//        fragmentTransaction.commit();
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0){
                    String tag = "android:switcher:"+R.id.main_viewPager+":"+1;
                    FragLession f = (FragLession) getChildFragmentManager().findFragmentByTag(tag);
                    f.setTopic(null);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return v;
    }

    @Override
    public void sendData(Topic topic) {
        String tag = "android:switcher:"+R.id.main_viewPager+":"+1;
        FragLession f = (FragLession) getChildFragmentManager().findFragmentByTag(tag);
        f.displayReceivedData(topic);
        vp.setCurrentItem(1);
    }
}