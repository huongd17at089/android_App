package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;

public class DailyStar {
    private boolean active;
    private String day;

    public DailyStar() {
    }

    public DailyStar(String day) {
        this.day = day;
    }

    public DailyStar(boolean active, String day) {
        this.active = active;
        this.day = day;
    }

    public int getImg() {
        if (active)
            return R.drawable.ic_baseline_star_24;
        return R.drawable.ic_baseline_star_outline_24;

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
