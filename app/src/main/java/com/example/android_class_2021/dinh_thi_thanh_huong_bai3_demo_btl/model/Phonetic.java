package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model;

import org.json.JSONObject;

import java.io.Serializable;

public class Phonetic implements Serializable {
    private String text, audio;

    public Phonetic(JSONObject jsonObject) {
        text = jsonObject.optString("text");
        audio = jsonObject.optString("audio");
    }

    public Phonetic(String text, String audio) {
        this.text = text;
        this.audio = audio;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
