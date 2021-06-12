package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Question;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Quiz;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.QuizHelper;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Topic;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Random;

public class TopicAdapterRV extends RecyclerView.Adapter<TopicAdapterRV.TopicViewHolder> {

    private Context context;
    private List<Topic> ls;
    private QuizHelper db;

    public interface Listener {
        void onFinish();
    }
    private Listener mListener;
    public void setListener(Listener listener) {
        mListener = listener;
    }

    public TopicAdapterRV(Context context) {
        this.context = context;
    }

    public TopicAdapterRV(Context context, List<Topic> ls) {
        this.context = context;
        this.ls = ls;
    }

    public TopicAdapterRV() {
        db = new QuizHelper(context);
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
    public TopicAdapterRV.TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_card_view, parent, false);

        return new TopicAdapterRV.TopicViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        Topic topic = ls.get(position);
        if (topic == null)
            return;
        holder.txt_topic.setText(topic.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // cap nhat db
//                db = new QuizHelper(context);
                updateTopicData(topic);
                //capnhat danh dach hien thi topic trong db
                if (mListener != null) {
                    mListener.onFinish();
                }
                //cap nhat adapter hien thi topic new
                removeItem(position);

            }
        });
    }

    private void updateTopicData(Topic topic) {
        db = new QuizHelper(context);
        db.addTopic(topic);
        updateQuestionData(topic, Quiz.EASY);
        updateQuestionData(topic, Quiz.MEDIUM);
        updateQuestionData(topic, Quiz.HARD);
    }

    private void updateQuestionData(Topic topic , String diff) {
        db = new QuizHelper(context);
        int numQues = new Random(2).nextInt(10)*Quiz.MAX_NUM_QUESTION;
        RequestQueue rq = Volley.newRequestQueue(context);
        JsonObjectRequest jar = new JsonObjectRequest(
                Request.Method.GET,
                "https://opentdb.com/api.php?amount="+numQues+"&category="+ topic.getId()+"&difficulty="+ diff +"&type=multiple",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray arr = response.optJSONArray("results");
                        int l = arr.length();
                        Quiz q = null;
                        for( int i = 0; i < l; i++){
                            if( i%Quiz.MAX_NUM_QUESTION == 0){
                                q = new Quiz(topic, " Quiz " + (i/Quiz.MAX_NUM_QUESTION + 1) , diff);
                                q.setId((int) db.addQuiz(q));
                            }
                            Question qu = new Question(q, arr.optJSONObject(i));
                            int id = (int) db.addQuestion(qu);
                            qu.setId(id);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        rq.add(jar);
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


    public Topic getItem(int position) {
        return ls.get(position);
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {
        TextView txt_topic;

        public TopicViewHolder(@NonNull View v) {
            super(v);
            txt_topic = v.findViewById(R.id.txt_topic);
        }
    }


}
