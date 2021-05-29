package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainAdapter.OldTopicAdapter;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainAdapter.TopicAdapterRV;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.QuizHelper;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Topic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FragSuggest extends Fragment{

    private RecyclerView rv, rv1;
    private TopicAdapterRV topicAdapter;
    private OldTopicAdapter oldtopicAdapter;
    private QuizHelper db;
    private SendTopic sender;

    public interface SendTopic {
        void sendData(Topic topic);
    }

    public FragSuggest() {
        // Required empty public constructor
//        db = new QuizHelper(getContext());
    }

    public static FragSuggest newInstance(String param1, String param2) {
        FragSuggest fragment = new FragSuggest();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_suggest, container, false);
        rv = v.findViewById(R.id.rv_suggest);

        oldtopicAdapter = new OldTopicAdapter(getContext());
        db = new QuizHelper(getContext());
        oldtopicAdapter.setLs(db.getAllTopic());
        oldtopicAdapter.notifyDataSetChanged();
        oldtopicAdapter.setListener(new OldTopicAdapter.Listener() {
            @Override
            public void onFinish(Topic topic) {
                sender.sendData(topic);
            }
        });
        rv.setAdapter(oldtopicAdapter);
        rv.setLayoutManager(new LinearLayoutManager(v.getContext()));

        rv1 = v.findViewById(R.id.rv_sg);
        topicAdapter = new TopicAdapterRV(getContext());
        rv1.setAdapter(topicAdapter);
//        topicAdapter.setLs(initTopicData());
        RestTask task = new RestTask();
        task.setListener(new RestTask.Listener() {
            @Override
            public void onResult(String result) {
                System.out.println(result);
                JSONObject jo = null;
                try {
                    jo = new JSONObject(result);
                    ArrayList<Topic> a = new ArrayList<>();
                    JSONArray arr = jo.optJSONArray("trivia_categories");
                    int l = arr.length();
                    for (int i = 0; i < l; i++) {
                        a.add(new Topic(arr.optJSONObject(i)));
                    }
                    db = new QuizHelper(getContext());
                    ArrayList<Topic> b = db.getAllTopic();
                    a.removeAll(b);
                    topicAdapter.setLs(a);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        task.execute("https://opentdb.com/api_category.php");
        LinearLayoutManager lm = new LinearLayoutManager(v.getContext());
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        topicAdapter.setListener(new TopicAdapterRV.Listener() {
            @Override
            public void onFinish() {
                oldtopicAdapter.setLs(db.getAllTopic());
            }
        });
        rv1.setLayoutManager(lm);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            sender = (SendTopic) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

//    private void getNewTopic() {
//        RequestQueue rq = Volley.newRequestQueue(getContext());
//        JsonObjectRequest or = new JsonObjectRequest(
//                Request.Method.GET,
//                "https://opentdb.com/api_category.php",
//                null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        ArrayList<Topic> a = new ArrayList<>();
//                        JSONArray arr = response.optJSONArray("trivia_categories");
//                        int l = arr.length();
//                        for (int i = 0; i < l; i++) {
//                            a.add(new Topic(arr.optJSONObject(i)));
//                        }
//                        ArrayList<Topic> b = db.getAllTopic();
//                        a.removeAll(b);
//                        topicAdapter.setLs(a);
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
////                        txt.setText(error.toString());
//                    }
//                }
//        );
//
//        rq.add(or);
//
//    }



    private List<Topic> initTopicData() {
        ArrayList<Topic> ls = new ArrayList<>();
        ls.add(new Topic(1, "1000 từ vựng cơ bản", R.drawable.ic_baseline_topic_24));
        ls.add(new Topic(1, "1000 từ vựng cơ bản", R.drawable.ic_baseline_topic_24));
        return ls;
    }

//    private ArrayList<Quiz> initQuizData() {
//        ArrayList<Quiz> ls = new ArrayList<>();
//        ls.add(new Quiz(1, "title1", 10, 5, R.drawable.jpg));
//        ls.add(new Quiz(1, "title2", 10, 5, R.drawable.jpg));
//        return ls;
//    }

//    private ArrayList<Lession> initData() {
//        ArrayList<Lession> ls = new ArrayList<>();
//        ls.add(new Lession(1, "title1", 10, 5, 1, R.drawable.e));
//        ls.add(new Lession(1, "title2", 10, 5, 1, R.drawable.e));
//        return ls;
//    }
}
