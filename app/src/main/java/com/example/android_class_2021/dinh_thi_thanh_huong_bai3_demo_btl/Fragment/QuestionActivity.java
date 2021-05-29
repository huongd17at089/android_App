package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Question;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class QuestionActivity extends AppCompatActivity {


    private TextView txtScore, txtTime, txtQuestion, txtOp1, txtOp2, txtOp3, txtOp4;
    private ArrayList<Question> questionList;
    private int questionCounter;
//    private int questionCountTotal;
    private Question currentQuestion;
    private int score;
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private ColorStateList textTrue;
    private ColorStateList textDefault;
    private ColorStateList textFalse;
    private ColorStateList timeDefault;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        txtScore = findViewById(R.id.txt_quiz_score);
        txtTime = findViewById(R.id.txt_time);
        txtQuestion = findViewById(R.id.txt_question);
        txtOp1 = findViewById(R.id.txt_a);
        txtOp2 = findViewById(R.id.txt_b);
        txtOp3 = findViewById(R.id.txt_c);
        txtOp4 = findViewById(R.id.txt_d);
        textTrue = txtOp2.getTextColors();
        textFalse = txtOp3.getTextColors();
        textDefault = txtOp1.getTextColors();
        timeDefault = txtTime.getTextColors();
        Intent intent = getIntent();
        questionList = (ArrayList<Question>) intent.getSerializableExtra("questions");
        Collections.shuffle(questionList);
        questionCounter = 0;
//        questionCountTotal = questionList.size();
        score = 0;
        txtOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSolution(txtOp1);
            }
        });
        txtOp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSolution(txtOp2);
            }
        });
        txtOp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSolution(txtOp3);
            }
        });
        txtOp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSolution(txtOp4);
            }
        });
        showQuestion();

    }

    private void showQuestion() {
        txtOp1.setTextColor(textDefault);
        txtOp2.setTextColor(textDefault);
        txtOp3.setTextColor(textDefault);
        txtOp4.setTextColor(textDefault);
        if (questionCounter < Quiz.MAX_NUM_QUESTION) {
            currentQuestion = questionList.get(questionCounter);
            txtQuestion.setText(Html.fromHtml(currentQuestion.getQuestion()).toString());
            txtOp1.setText(currentQuestion.getOption1());
            txtOp2.setText(currentQuestion.getOption2());
            txtOp3.setText(currentQuestion.getOption3());
            txtOp4.setText(currentQuestion.getOption4());
            questionCounter++;
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        } else {
            finishQuiz();
        }
    }

    private void updateCountDownText(){
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        txtTime.setText(seconds + "s");
        if (timeLeftInMillis < 10000) {
            txtTime.setTextColor(Color.RED);
        } else {
            txtTime.setTextColor(timeDefault);
        }
    }

    private void showSolution(TextView txt){
        if( txt != null){
            countDownTimer.cancel();
            if( currentQuestion.getAnswer().equals(txt.getText().toString()))
                score++;
            else
                txt.setTextColor(textFalse);
            txtScore.setText(score+"/"+Quiz.MAX_NUM_QUESTION);
        }else{
            txtOp1.setTextColor(textFalse);
            txtOp2.setTextColor(textFalse);
            txtOp3.setTextColor(textFalse);
            txtOp4.setTextColor(textFalse);
        }

        if(currentQuestion.getAnswer().equals(txtOp1.getText().toString())) txtOp1.setTextColor(textTrue);
        if(currentQuestion.getAnswer().equals(txtOp2.getText().toString())) txtOp2.setTextColor(textTrue);
        if(currentQuestion.getAnswer().equals(txtOp3.getText().toString())) txtOp3.setTextColor(textTrue);
        if(currentQuestion.getAnswer().equals(txtOp4.getText().toString())) txtOp4.setTextColor(textTrue);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showQuestion();
            }
        }, 1000);
    }


    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                showSolution(null);
            }
        }.start();
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("newScore", score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Ấn quay lại 2 lần", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}