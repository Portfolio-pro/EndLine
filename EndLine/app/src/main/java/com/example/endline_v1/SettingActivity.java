package com.example.endline_v1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    // 홈화면의 종 아이콘을 누르면 보여질 알림물품화면
    ExpandableListView exlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        exlist=(ExpandableListView)findViewById(R.id.exList_endline);


    }
}