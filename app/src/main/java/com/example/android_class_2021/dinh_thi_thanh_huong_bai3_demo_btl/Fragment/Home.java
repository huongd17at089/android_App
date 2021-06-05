package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainAdapter.DailyStarAdapter;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.DailyStar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView rv;
    private DailyStarAdapter adapter;


    public Home() {
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
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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
        View v = inflater.inflate(R.layout.fragment_hone, container, false);
        // Inflate the layout for this fragment
        rv = v.findViewById(R.id.rv_star);
        adapter = new DailyStarAdapter(fakeData());
        rv.setAdapter(adapter);
        LinearLayoutManager lm = new LinearLayoutManager(v.getContext());
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(lm);
        return  v;
    }

    private ArrayList<DailyStar> fakeData() {
        ArrayList<DailyStar> ls = new ArrayList<>();
        ls.add(new DailyStar(true, "T2"));
        ls.add(new DailyStar(true, "T3"));
        ls.add(new DailyStar(true, "T4"));
        ls.add(new DailyStar(true, "T5"));
        ls.add(new DailyStar(false, "T6"));
        ls.add(new DailyStar(false, "T7"));
        ls.add(new DailyStar(false, "cn"));
        return ls;
    }
}