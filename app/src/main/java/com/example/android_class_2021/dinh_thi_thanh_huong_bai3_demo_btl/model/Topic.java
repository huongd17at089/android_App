package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;

import org.json.JSONObject;

import java.io.Serializable;


public class Topic implements Serializable {
    private int id;
    private String name;
    private int img;

    public Topic() {
    }

    public Topic(int id, String name,  int img) {
        this.id = id;
        this.name = name;
        this.img = img;

    }

    public Topic(String name,  int img) {
        this.name = name;
        this.img = img;
    }

    public Topic(JSONObject ob) {
        id = Integer.parseInt(ob.optString("id"));
        name = ob.optString("name");
        img = R.drawable.ic_baseline_topic_24;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return id == topic.id;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img=" + img +
                '}';
    }
}
