package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Meaning implements Serializable {
    private String partOfSpeech;
    private List<Definition> definitions;

    public Meaning(JSONObject jsonObject) {
        partOfSpeech = jsonObject.optString("partOfSpeech");
        JSONArray definitionArr = jsonObject.optJSONArray("definitions");
        definitions = new ArrayList<>();
        int numOfDefinition = definitionArr.length();
        for( int i = 0; i < numOfDefinition; i++){
            definitions.add(new Definition(definitionArr.optJSONObject(i)));
        }
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }

    @Override
    public String toString() {
        String s = "";
        for(Definition x : definitions){
            s += "\t" + x.getDefinition() + "\n";
        }
        return s;
    }

    public String getExample() {
        String s = "";
        for(Definition x : definitions){
            s += "\t" + x.getExample()+ "\n";
        }
        return s;

    }

    public String getSynonym() {
        String s = partOfSpeech.toUpperCase() + ": \n";
        for(Definition x : definitions){
            s += x.getSynonym().toLowerCase() + "\n";
        }
        return s;
    }


}
