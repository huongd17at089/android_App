package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Word;

public class WordDetail extends AppCompatActivity {
    private TextView txtWord, txtPhonetic, txtPartOfSpeech, txtMeaning, txtExample, txtSynonym;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);
        txtWord = findViewById(R.id.txt_word);
        txtPhonetic = findViewById(R.id.txt_phonetic);
        txtPartOfSpeech = findViewById(R.id.txt_partOfSpeech);
        txtMeaning = findViewById(R.id.txt_meaning);
        txtExample = findViewById(R.id.txt_example);
        txtSynonym = findViewById(R.id.txt_synonyms);
        Intent intent = getIntent();
        Word w = (Word) intent.getSerializableExtra("word");
        txtWord.setText(w.getWord());
        txtPhonetic.setText(w.getPhonetics().get(0).getText());
        txtPartOfSpeech.setText(w.getMeanings().get(0).getPartOfSpeech());
        txtMeaning.setText(w.getMeanings().get(0).toString());
        txtExample.setText(w.getMeanings().get(0).getExample());
        txtSynonym.setText(w.getSynonym());

    }
}