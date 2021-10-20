package com.example.endline_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BarCodeNum extends AppCompatActivity {

    TextView et_barcodeNum;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcodenum);

        et_barcodeNum = (TextView)findViewById(R.id.et_barcodeNum);
        btn_add = (Button)findViewById(R.id.btn_add);

        //번호를 입력하고 등록버튼을 누르면 번호 값을 인텐트로 넘겨주면서 데이터 찾기
        //인텐트로 넘겨주는거니까 ScanBarCode 그대로 가져다 쓰면 될듯
        //대신 카메라 키는 거 윗줄에 조건문 넣어서 작동하지 않도록 조절해야함.

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
            }
        });

    }
}