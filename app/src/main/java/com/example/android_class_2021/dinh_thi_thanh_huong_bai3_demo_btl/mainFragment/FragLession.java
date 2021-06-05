package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainFragment;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.Fragment.StartQuestionActivity;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainAdapter.QuizAdapterRV;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Quiz;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.QuizHelper;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Topic;

import java.util.ArrayList;


public class FragLession extends Fragment {
    private Button btn3, btn1, btn2;
    private RecyclerView rv;
    private QuizAdapterRV adapter;
    private Topic topic ;
    private QuizHelper db;
    private ColorStateList txtSelected, txtUnselected;
    private ColorStateList bbgSelected, bgUnselected;

    public void setTopic(Topic topic) {
        this.topic = topic;
        if(topic == null){
            adapter.setLs(db.getAllQuizs(Quiz.EASY));
            btn1.setTextColor(txtSelected);
            btn2.setTextColor(txtUnselected);
            btn3.setTextColor(txtUnselected);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                btn1.setBackgroundTintList(bbgSelected);
                btn2.setBackgroundTintList(bgUnselected);
                btn3.setBackgroundTintList(bgUnselected);
            }

        }

    }

    public FragLession() {
        // Required empty public constructor
    }

    public static FragLession newInstance(String param1, String param2) {
        FragLession fragment = new FragLession();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_lession, container, false);
        btn1 = v.findViewById(R.id.button);
        btn2 = v.findViewById(R.id.button2);
        btn3 = v.findViewById(R.id.button3);
        txtSelected = btn1.getTextColors();
        txtUnselected = btn2.getTextColors();
        bgUnselected = btn2.getBackgroundTintList();
        bbgSelected = btn1.getBackgroundTintList();

        rv = v.findViewById(R.id.rvl);
//        set adpter
        adapter = new QuizAdapterRV();
        adapter.setListener(new QuizAdapterRV.Listener() {
            @Override
            public void onCickItem(Quiz quiz) {
                Intent intent = new Intent(v.getContext(), StartQuestionActivity.class);
                intent.putExtra("quiz", quiz);
                startActivity(intent);
            }
        });
//        adapter.setLs(initData());
        db = new QuizHelper(getContext());
        if(topic == null)
            adapter.setLs(db.getAllQuizs(Quiz.EASY));
        else
            adapter.setLs(db.getQuizsByTopic(topic,Quiz.EASY));
//        adapter.notifyDataSetChanged();
        rv.setAdapter(adapter);
//        rv.setLayoutManager(new LinearLayoutManager(v.getContext()));
//        rv.setLayoutManager(new GridLayoutManager(v.getContext(), 2));
        rv.setLayoutManager(new LinearLayoutManager(v.getContext()));
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topic == null)
                    adapter.setLs(db.getAllQuizs(Quiz.EASY));
                else
                    adapter.setLs(db.getQuizsByTopic(topic,Quiz.EASY));
                resetBtnView(btn1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topic == null)
                    adapter.setLs(db.getAllQuizs(Quiz.MEDIUM));
                else
                    adapter.setLs(db.getQuizsByTopic(topic,Quiz.MEDIUM));
                resetBtnView(btn2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topic == null)
                    adapter.setLs(db.getAllQuizs(Quiz.HARD));
                else
                    adapter.setLs(db.getQuizsByTopic(topic,Quiz.HARD));
                resetBtnView(btn3);
            }
        });
        return v;
    }


    private void resetBtnView(Button btn) {
        btn1.setTextColor(txtUnselected);
        btn2.setTextColor(txtUnselected);
        btn3.setTextColor(txtUnselected);
        btn.setTextColor(txtSelected);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            btn1.setBackgroundTintList(bgUnselected);
            btn2.setBackgroundTintList(bgUnselected);
            btn3.setBackgroundTintList(bgUnselected);
            btn.setBackgroundTintList(bbgSelected);
        }
    }



    public void displayReceivedData(Topic topic)
    {
        this.topic = topic;
        adapter.setLs(db.getQuizsByTopic(topic,Quiz.EASY));

    }
}