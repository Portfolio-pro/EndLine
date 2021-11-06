package com.example.endline_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class BarCodeNum extends AppCompatActivity {

    TextView et_barcodeNum;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcodenum);

        et_barcodeNum = (TextView)findViewById(R.id.et_barcodeNum);
        btn_add = (Button)findViewById(R.id.btn_add);

        //10.30 추가, 필터 설정
        et_barcodeNum.setFilters(new InputFilter[] { editFilter });

        //번호를 입력하고 등록버튼을 누르면 번호 값을 인텐트로 넘겨주면서 데이터 찾기
        //인텐트로 넘겨주는거니까 ScanBarCode2 그대로 가져다 쓰면 될듯
        //대신 카메라 키는 거 윗줄에 조건문 넣어서 작동하지 않도록 조절해야함.

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText의 값을 넘겨줘야함. 넘겨준 페이지에서는 받은 값을 인증키로 검색해서 데이터를 입력해야함.

                String barcodeNum = et_barcodeNum.getText().toString();
                Intent intent = new Intent(getApplicationContext(),ScanBarCode2.class); // intent로 값을 받아서 fb에서 찾아야되는데 Scanpage3을 만드는게 좋을까?
                intent.putExtra("barcodeNum", barcodeNum);
                startActivity(intent);
            }
        });

    }

    //10.30 추가, 필터 만들기
    protected InputFilter editFilter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Pattern pattern = Pattern.compile("^[0-9]*$");        // 숫자
            // Pattern pattern = Pattern.compile("^[a-zA-Z]+$");        // 영문
            // Pattern pattern = Pattern.compile("^[ㄱ-ㅎ가-힣]+$");        // 한글
            // Pattern pattern = Pattern.compile("^[a-zA-Z0-9ㄱ-ㅎ가-힣]+$");        // 영문,숫자,한글
            if(!pattern.matcher(source).matches()) {
                Toast.makeText(getApplicationContext(), "숫자를 입력해주세요", Toast.LENGTH_SHORT).show();
                return "";
            }
            return null;
        }
    };

}