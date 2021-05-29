package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Quiz;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Word;


import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private Context context;
    private List<Word> ls;

    private Listener mListener;


    public void setListener(Listener listener) {
        mListener = listener;
    }


    public interface Listener {
        void onCickItem(Word word);
    }


    public WordAdapter(Context context) {
        this.context = context;
    }

    public WordAdapter(Context context, List<Word> ls) {
        this.context = context;
        this.ls = ls;
    }

    public WordAdapter() {

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Word> getLs() {
        return ls;
    }

    public void setLs(List<Word> ls) {
        this.ls = ls;
        this.notifyDataSetChanged();
    }

    public void addItem(Word w) {
        ls.add(w);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WordAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_card, parent, false);

        return new WordAdapter.WordViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = ls.get(position);
        if (word == null)
            return;
        holder.txt_word.setText(word.getWord());
        holder.txt_meaning.setText(word.getMeanings().get(0).getDefinitions().get(0).getDefinition());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onCickItem(word);
                }
            }
        });
    }



    public void removeItem(int p){
        ls.remove(p);
        this.notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (ls != null)
            return ls.size();
        return 0;
    }


    public Word getItem(int position) {
        return ls.get(position);
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        TextView txt_word, txt_meaning;

        public WordViewHolder(@NonNull View v) {
            super(v);
            txt_word = v.findViewById(R.id.txt_w);
            txt_meaning = v.findViewById(R.id.txt_mean);
        }
    }



}

