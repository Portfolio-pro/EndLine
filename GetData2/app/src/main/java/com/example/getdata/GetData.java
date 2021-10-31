package com.example.getdata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class GetData extends AppCompatActivity {

    TextView et_barcode, et_productName, et_category, et_price, et_brand, et_buyDay, et_endline;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);

        et_barcode = (TextView) findViewById(R.id.et_barcode);
        et_productName = (TextView) findViewById(R.id.et_productName);
        et_category = (TextView) findViewById(R.id.et_category);
        et_price = (TextView) findViewById(R.id.et_price);
        et_brand = (TextView) findViewById(R.id.et_brand);
        et_buyDay = (TextView) findViewById(R.id.et_buyDay);
        et_endline = (TextView) findViewById(R.id.et_endline);
        iv = (ImageView) findViewById(R.id.iv);

        Intent intent = getIntent();
        String barcode = intent.getStringExtra("BarcodeNum"); // EditText에 입력한 번호를 인텐트로 넘겨받음

        //et_barcode.setText(barcode);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // 로그인한 유저의 정보 가져오기
        String uid = user != null? user.getUid() : null; // 로그인한 유저의 고유 uid 가져오기

        db.collection("user").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    //조건문으로 문서의 uid와 바코드번호 항목을 확인하여 그에 해당하는 정보들을 불러와야함.
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }
}