package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Word implements Serializable {
    private String word ;
    private List<Phonetic> phonetics;
    private List<Meaning> meanings;

    public Word(JSONObject jsonObject) {
        word = jsonObject.optString("word");
        JSONArray phoneticArr = jsonObject.optJSONArray("phonetics");
        JSONArray meaningArr = jsonObject.optJSONArray("meanings");
        phonetics = new ArrayList<>();
        int numOfphonetic = phoneticArr.length();
        for( int i = 0; i < numOfphonetic; i++){
            phonetics.add(new Phonetic(phoneticArr.optJSONObject(i)));
        }

        meanings = new ArrayList<>();
        int numOfMeaning = meaningArr.length();
        for( int i = 0; i < numOfMeaning; i++){
            meanings.add(new Meaning(meaningArr.optJSONObject(i)));
        }

    }

    public Word(String word, List<Phonetic> phonetics, List<Meaning> meanings) {
        this.word = word;
        this.phonetics = phonetics;
        this.meanings = meanings;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Phonetic> getPhonetics() {
        return phonetics;
    }

    public void setPhonetics(List<Phonetic> phonetics) {
        this.phonetics = phonetics;
    }

    public List<Meaning> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<Meaning> meanings) {
        this.meanings = meanings;
    }

    public String getSynonym() {
        String s = "";
        for(Meaning x : meanings){
            s += x.getSynonym()+ "\n";
        }
        return s;
    }
}
