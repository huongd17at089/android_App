package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model;

public class DictItem {
    private int id;
    private String word;
    private String content;

    public DictItem(int id, String word, String content) {
        this.id = id;
        this.word = word;
        this.content = content;
    }

    public DictItem() {
    }

    public DictItem( String word, String content) {
        this.word = word;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
