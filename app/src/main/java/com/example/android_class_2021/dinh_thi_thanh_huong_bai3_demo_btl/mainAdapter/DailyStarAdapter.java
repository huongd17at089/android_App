package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.DailyStar;

import java.util.ArrayList;
import java.util.List;

public class DailyStarAdapter extends RecyclerView.Adapter<DailyStarAdapter.DailyStarViewHolder> {

private Context context;
private List<DailyStar> ls;





    public DailyStarAdapter(Context context) {
        this.context = context;
    }

    public DailyStarAdapter(Context context, List<DailyStar> ls) {
        this.context = context;
        this.ls = ls;
    }

    public DailyStarAdapter(ArrayList<DailyStar> dailyStars) {
        ls = dailyStars;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<DailyStar> getLs() {
        return ls;
    }

    public void setLs(List<DailyStar> ls) {
        this.ls = ls;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DailyStarAdapter.DailyStarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_star_card, parent, false);

        return new DailyStarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyStarViewHolder holder, int position) {
        DailyStar star = ls.get(position);
        if (star == null) {
            return;
        }
        holder.txt.setText(star.getDay());
        holder.img.setImageResource(star.getImg());
    }



    @Override
    public int getItemCount() {
        if (ls != null)
            return ls.size();
        return 0;
    }


    public DailyStar getItem(int position) {
        return ls.get(position);
    }

public static class DailyStarViewHolder extends RecyclerView.ViewHolder {
    TextView txt;
    ImageView img;


    public DailyStarViewHolder(@NonNull View v) {
        super(v);
        txt = v.findViewById(R.id.text_day);
        img = v.findViewById(R.id.img_star);

    }
}

}
