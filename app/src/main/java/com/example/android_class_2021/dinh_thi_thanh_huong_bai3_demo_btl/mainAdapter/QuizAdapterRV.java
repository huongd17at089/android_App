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
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Quiz;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Topic;

import java.util.List;

public class QuizAdapterRV  extends RecyclerView.Adapter<QuizAdapterRV.QuizViewHolder> {

    private Context context;
    private List<Quiz> ls;

    private Listener mListener;


    public void setListener(Listener listener) {
        mListener = listener;
    }


    public interface Listener {
        void onCickItem(Quiz quiz);
    }

    public QuizAdapterRV(Context context) {
        this.context = context;
    }

    public QuizAdapterRV(Context context, List<Quiz> ls) {
        this.context = context;
        this.ls = ls;
    }

    public QuizAdapterRV() {
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Quiz> getLs() {
        return ls;
    }

    public void setLs(List<Quiz> ls) {
        this.ls = ls;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuizAdapterRV.QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lession_card_view, parent, false);

        return new QuizViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizAdapterRV.QuizViewHolder holder, int position) {
        Quiz q = ls.get(position);
        if (q == null)
            return;
        holder.txtTitle.setText(q.getName());
        holder.txtDiff.setText(q.getDifficulty());
        holder.img.setImageResource(q.getImg());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onCickItem(q);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        if (ls != null)
            return ls.size();
        return 0;
    }


    public Quiz getItem(int position) {
        return ls.get(position);
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDiff;
        ImageView img;

        public QuizViewHolder(@NonNull View v) {
            super(v);
            txtTitle = v.findViewById(R.id.lession_card_title);
            txtDiff = v.findViewById(R.id.lession_card_diff);
            img = v.findViewById(R.id.lession_card_img);
        }
    }

}
