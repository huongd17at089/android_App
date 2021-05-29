package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;

import java.io.Serializable;

public class Quiz implements Serializable {
    private int id;
    private Topic topic;
    private String name;
    private String difficulty;
    private int img;
    public static final String EASY = "easy";
    public static final String MEDIUM = "medium";
    public static final String HARD = "hard";
    public static final int MAX_NUM_QUESTION = 10;

    public Quiz() {
    }

    public Quiz(int id, Topic topic, String name, String difficulty, int img) {
        this.id = id;
        this.topic = topic;
        this.name = name;
        this.difficulty = difficulty;
        this.img = img;
    }

    public Quiz(Topic topic, String name, String difficulty) {
        this.topic = topic;
        this.name = name;
        this.difficulty = difficulty;
        this.img = R.drawable.ic_baseline_quiz_24;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}

