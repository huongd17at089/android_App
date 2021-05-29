package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.Fragment.Adapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView nav;
    private ViewPager vp;
    private Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nav = findViewById(R.id.nav);
        vp = findViewById(R.id.viewPager);

        adapter = new Adapter(getSupportFragmentManager(), Adapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vp.setAdapter(adapter);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home: vp.setCurrentItem(0); break;
                    case  R.id.lession: vp.setCurrentItem(1); break;
                    case R.id.dict: vp.setCurrentItem(2); break;
                    case R.id.profice: vp.setCurrentItem(3); break;
                }
                return false;
            }
        });

    }
}