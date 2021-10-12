package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView tv_home; // Home TextView
    private CalendarView cv_home; // Home CalanderView
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //새로 추가
    private ListView lv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_home = findViewById(R.id.text_home); // 날짜 보여주는 곳
        cv_home = findViewById(R.id.calendarView); // 달력

        //새로 추가
        lv = findViewById(R.id.list_home);
        final ArrayList<String> midList = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,midList);
        lv.setAdapter(adapter);

        midList.add("01. 서울우유");
        midList.add("02. 가나초콜릿");
        midList.add("03. 사각햇반");
        midList.add("04. 유부초밥");
        midList.add("05. 옥수수식빵");
        midList.add("06. 아침햇살");
        midList.add("07. 종합비타민");
        midList.add("08. 홍삼스틱");
        midList.add("09. 이소티논");
        midList.add("10. 강력소화제");
        midList.add("11. 오메가3");
        midList.add("12. 비비고 왕교자");
        midList.add("13. 그리밀 단백질");

        adapter.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //아이템 클릭했을 때 물품 정보화면으로 넘어갈 수 있도록 하는 코드

            }
        });




        DateFormat formatter = new SimpleDateFormat("[ 유통기한 만료일 : yyyy년 MM월 d일 ]"); // 날짜 형식
        Date date = new Date(cv_home.getDate()); // 달력에서 날짜 가져오기
        tv_home.setText(formatter.format(date));

        cv_home.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String day;
                day = "[ 유통기한 만료일 : "+ year + "년 " + (month+1) + "월 " + dayOfMonth +"일 ]";
                tv_home.setText(day);
            }
        });


/*
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("second", "Mary");
        user.put("last", "Lovelace");
        user.put("born", 1816);

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Firestore", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firestore", "Error adding document", e);
                    }
                });

 */

    }
}