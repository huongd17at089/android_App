package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Question;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Quiz;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.QuizHelper;

import java.util.ArrayList;

public class StartQuestionActivity extends AppCompatActivity {
    private TextView txtTopic, txtQuiz, txtScore;
    private Button btnStart;
    private static final int REQUEST_CODE_QUIZ = 1;
    private Quiz quiz;
    private ArrayList<Question> ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_question);
        Intent intent = getIntent();
        quiz = (Quiz) intent.getSerializableExtra("quiz");
        txtTopic = findViewById(R.id.txt_quiz_topic);
        txtScore = findViewById(R.id.txt_score);
        txtQuiz = findViewById(R.id.txt_quiz);
        btnStart = findViewById(R.id.btn_start);

        txtTopic.setText("Chủ đề :" + quiz.getTopic().getName());
        txtScore.setText("0/" + Quiz.MAX_NUM_QUESTION);
        txtQuiz.setText(quiz.getName());
        ls = new QuizHelper(StartQuestionActivity.this).getQuestionsByQuiz(quiz);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartQuestionActivity.this, QuestionActivity.class);
                ArrayList<Question> ls = new QuizHelper(StartQuestionActivity.this).getQuestionsByQuiz(quiz);
                intent.putExtra("questions", ls);
                startActivityForResult(intent, REQUEST_CODE_QUIZ);
                btnStart.setText("Restart");
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra("newScore", 0);
                int currScore = Integer.parseInt(txtScore.getText().toString().split("/")[0]);
                if (score > currScore) {
                    txtScore.setText(score+"/"+Quiz.MAX_NUM_QUESTION);
                }
            }
        }
    }
}