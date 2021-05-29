package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Question implements Serializable {
    private int id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private Quiz quiz;


    public Question(int id, Quiz quiz, String question, String option1, String option2,
                    String option3, String option4, String answer) {
        this.id = id;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.quiz = quiz;
    }

    public Question( Quiz quiz ,String question, String option1, String option2,
                    String option3, String option4, String answer) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.quiz = quiz;
    }

    public Question() {
    }

    public Question(Quiz q, JSONObject jo) {
        this.quiz = q;
        this.question = jo.optString("question");
        this.answer = jo.optString("correct_answer");
        JSONArray arr = jo.optJSONArray("incorrect_answers");
        ArrayList<String> temp = new ArrayList<>();
        for( int i = 0 ; i < arr.length(); i++){
            temp.add(arr.optString(i));
        }
        String ok = temp.toString();
        temp.add(this.answer);
        Collections.shuffle(temp);
        ok = temp.toString();
        this.option1 = temp.get(0);
        this.option2 = temp.get(1);
        this.option3 = temp.get(2);
        this.option4 = temp.get(3);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", answer='" + answer + '\'' +
                ", quiz=" + quiz +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
