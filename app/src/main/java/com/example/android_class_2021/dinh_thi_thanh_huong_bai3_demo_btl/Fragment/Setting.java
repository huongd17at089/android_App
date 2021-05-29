package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.Fragment;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;

public class Setting extends AppCompatActivity {
    ImageButton btnEdit;
    TextView txtTarget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        btnEdit = findViewById(R.id.btn_edit_target);
        txtTarget = findViewById(R.id.txt_target);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_edit_target, null);
                LinearLayout a = popupView.findViewById(R.id.target1);
                LinearLayout b = popupView.findViewById(R.id.target2);
                LinearLayout c = popupView.findViewById(R.id.target3);
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(btnEdit, Gravity.CENTER, 0, 0);

//                txt.setText(b.getText().toString());
//                popupWindow.dismiss();
                a.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtTarget.setText(((TextView)a.findViewById(R.id.a)).getText().toString());
                        popupWindow.dismiss();
                    }
                });
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtTarget.setText(((TextView)b.findViewById(R.id.b)).getText().toString());
                        popupWindow.dismiss();
                    }
                });
                c.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txtTarget.setText(((TextView)c.findViewById(R.id.c)).getText().toString());
                        popupWindow.dismiss();
                    }
                });
            }
        });

    }
}