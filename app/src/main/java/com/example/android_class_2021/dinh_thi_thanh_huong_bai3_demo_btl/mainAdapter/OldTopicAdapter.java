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
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.QuizHelper;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Topic;

import java.util.List;

public class OldTopicAdapter extends RecyclerView.Adapter<OldTopicAdapter.TopicViewHolder> {

    private Context context;
    private List<Topic> ls;

    private Listener mListener;


    public void setListener(Listener listener) {
        mListener = listener;
    }


    public interface Listener {
        void onFinish(Topic topic);
    }

    public OldTopicAdapter(Context context) {
        this.context = context;
    }

    public OldTopicAdapter(Context context, List<Topic> ls) {
        this.context = context;
        this.ls = ls;
    }

    public OldTopicAdapter() {
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Topic> getLs() {
        return ls;
    }

    public void setLs(List<Topic> ls) {
        this.ls = ls;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OldTopicAdapter.TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_card_view2, parent, false);

        return new OldTopicAdapter.TopicViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OldTopicAdapter.TopicViewHolder holder, int position) {
        Topic topic = ls.get(position);
        if (topic == null)
            return;
        holder.txt_title.setText(topic.getName());
        holder.txt_check.setText("chưa hoàn thành");
        holder.img.setImageResource(topic.getImg());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onFinish(topic);
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


    public Topic getItem(int position) {
        return ls.get(position);
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {
        TextView txt_title, txt_check;
        ImageView img;


        public TopicViewHolder(@NonNull View v) {
            super(v);
            txt_title = v.findViewById(R.id.title);
            txt_check = v.findViewById(R.id.check);
            img =  v.findViewById(R.id.img);

        }
    }

}