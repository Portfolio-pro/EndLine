package com.example.endline_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class InfoActivity extends AppCompatActivity {

    // 프레그먼트에 있는 리스트뷰 항목을 누르면 보여질 물품정보화면

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }
}