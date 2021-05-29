package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.mainAdapter.WordAdapter;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.DictHelper;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.DictItem;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.QuizHelper;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Word;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.lucasr.twowayview.TwoWayView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dict#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dict extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TwoWayView listItem;
    private ArrayList<String> listData;
    private View v;
    private SearchView txt;
    private RecyclerView rv;
    private WordAdapter adapter;
    private static final String URL = "https://api.dictionaryapi.dev/api/v2/entries/en_US/";
    private ArrayList<Word> ls;




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Dict() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Dict.
     */
    // TODO: Rename and change types and number of parameters
    public static Dict newInstance(String param1, String param2) {
        Dict fragment = new Dict();
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
        v = inflater.inflate(R.layout.fragment_dict, container, false);
        listItem = (TwoWayView)v.findViewById(R.id.list_item);
        listData = new ArrayList<>();
        final ListViewAdapter mAdapter = new ListViewAdapter(v.getContext(), createFakeDataForList());
        listItem.setAdapter(mAdapter);
        txt = v.findViewById(R.id.txt_searchWord);
        rv = v.findViewById(R.id.word_ls);
        adapter = new WordAdapter();
        DictHelper db = new DictHelper(v.getContext());
        ls = createWordList(db.getAllWord());
        adapter.setLs(ls);
        adapter.setListener(new WordAdapter.Listener() {
            @Override
            public void onCickItem(Word word) {
                Intent intent = new Intent(v.getContext(), WordDetail.class);
                intent.putExtra("word", word);
                startActivity(intent);
            }
        });
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(v.getContext()));


        txt.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String[] split = query.split("\\s+");
                if(split.length > 1){
                    translateParagraph(query.toLowerCase());
                }else{
                    translateSingleWord(query.toLowerCase());
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String[] split = newText.split("\\s+");
                adapter.setLs(createWordList(new QuizHelper(getContext()).getAllWordByKey(split[0])));
                return true;
            }
        });


        return v;
    }

    private ArrayList<Word> createWordList(ArrayList<DictItem> items)  {
        ArrayList<Word> ls= new ArrayList();
        for(DictItem item : items){
            try {
                JSONObject j = new JSONObject(item.getContent());
                ls.add(new Word(j));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ls;
    }

    private ArrayList<String> createFakeDataForList() {
        ArrayList<String> ls = new ArrayList();
        ls.add("hello");
        ls.add("hi");
        return ls;
    }

    private void translateParagraph(String word){
        Intent intent = new Intent(v.getContext(), Translate.class);
        intent.putExtra("word", word);
        startActivity(intent);
    }


    private void translateSingleWord(String word){
        RequestQueue rq = Volley.newRequestQueue(v.getContext());
        JsonArrayRequest or = new JsonArrayRequest(
                Request.Method.GET,
                URL + word,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Word w = new Word(response.getJSONObject(0));
                            new DictHelper(getContext()).addWord(new DictItem(word, response.getJSONObject(0).toString()));
                            ls.add(w);
                            adapter.setLs(ls);
                            Intent intent = new Intent(v.getContext(), WordDetail.class);
                            intent.putExtra("word", w);
                            startActivity(intent);
                        } catch (JSONException e) {
                            //error activity
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        rq.add(or);
    }
}