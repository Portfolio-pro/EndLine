package com.example.getdata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button btn_getData;
    EditText et_text;
    Map<String, Object> data = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_getData = (Button) findViewById(R.id.btn_getData);
        et_text = (EditText) findViewById(R.id.et_text);

        et_text.setFilters(new InputFilter[] { editFilter });


        btn_getData.setOnClickListener(new View.OnClickListener() { // 바코드번호를 넘겨서 다음 페이지에 번호에 맞는 데이터 삽입
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(), GetData.class));

                String Num = "123456789";

                Intent intent = new Intent(getApplicationContext(),GetData.class);
                intent.putExtra("BarcodeNum", Num);
                startActivity(intent);

            }

        });

    }

    protected InputFilter editFilter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Pattern pattern = Pattern.compile("^[0-9]*$");        // 영문대문자
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