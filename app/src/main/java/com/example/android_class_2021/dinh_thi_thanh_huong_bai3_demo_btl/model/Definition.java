package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Definition implements Serializable {
    private String definition;
    private String example;
    private List<String> synonyms;

    public Definition(JSONObject jsonObject) {
        definition = jsonObject.optString("definition");
        example = jsonObject.optString("example");
        synonyms = new ArrayList<>();
        JSONArray synonymArr = jsonObject.optJSONArray("synonyms");
        if( synonymArr != null ){
            int numOfSynonym = synonymArr.length();
            for( int i = 0; i < numOfSynonym; i++){
                synonyms.add(synonymArr.optString(i));
            }
        }


    }

    public Definition(String definition, String example, List<String> synonyms) {
        this.definition = definition;
        this.example = example;
        this.synonyms = synonyms;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public String getSynonym() {
        String s = "";
        for(String x : synonyms){
            s +=  x + ", ";
        }
        return s;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }


}
