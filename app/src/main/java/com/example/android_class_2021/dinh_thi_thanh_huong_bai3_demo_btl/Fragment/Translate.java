package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;
import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.model.Word;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

public class Translate extends AppCompatActivity {
    private TextView  txtEn;
    private EditText txtVi;
    private ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        txtVi = findViewById(R.id.txt_vi);
        txtEn = findViewById(R.id.txt_en);
        btn = findViewById(R.id.btn_tran);
        Intent intent = getIntent();
        String param = intent.getStringExtra("word");
        txtVi.setText(param);
        translate(param);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translate(txtVi.getText().toString());
            }
        });

    }

    private void translate(String param){
        TranslatorOptions options =
                new TranslatorOptions.Builder()
                        .setSourceLanguage(TranslateLanguage.ENGLISH)
                        .setTargetLanguage(TranslateLanguage.VIETNAMESE)
                        .build();
        final Translator englishVietTranslator = Translation.getClient(options);
        DownloadConditions conditions = new DownloadConditions.Builder()
                .requireWifi()
                .build();

        englishVietTranslator.translate(param)
                .addOnSuccessListener(
                        new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                txtEn.setText(o.toString());
                            }

                            public void onSuccess(@NonNull String translatedText) {
                                txtEn.setText(translatedText);
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Error.
                                // ...
                            }
                        });

//        englishVietTranslator.downloadModelIfNeeded(conditions)
//                .addOnSuccessListener(
//                        new OnSuccessListener() {
//                            @Override
//                            public void onSuccess(Object o) {
//
//                            }
//
//                            public void onSuccess(Void v) {
//
//                            }
//                        })
//                .addOnFailureListener(
//                        new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//
//                            }
//                        });
    }
}