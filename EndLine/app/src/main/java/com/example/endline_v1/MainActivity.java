package com.example.endline_v1;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, FirebaseAuth.AuthStateListener {

    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth auth; //Auth
    FirebaseUser user;

    private TextView tv_result;     //User ID
    private ImageView iv_profile;   //User Profile Photo

    public static String displayName = "";
    public static String profilePhotoUrl = "";
    public static boolean isLogin = false;

    private long backBtnTime = 0;

    NavigationView navigationView;

    //10.12 추가
    private TextView tv_home; // Home TextView
    private CalendarView cv_home; // Home CalanderView
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ListView lv;
    // 10.12 여기까지

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //10.12추가 (MainActivity에 추가한 코드라서 Profile같은 다른 메뉴에 갔다가 Home 메뉴로 돌아오면 데이터가 다 사라져있다.)
        //Fragment에 코드를 추가해야 함.
        tv_home = findViewById(R.id.text_home); // 날짜 보여주는 곳
        cv_home = findViewById(R.id.calendarView); // 달력
        lv = findViewById(R.id.list_home); // 리스트 뷰
        final ArrayList<String> midList = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,midList);
        lv.setAdapter(adapter);

        // 이 부분은 임의대로 넣은 부분이고 추후 Firestore에서 데이터를 받아와야한다.
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
                //아이템 클릭했을 때 물품 정보화면으로 넘어가는 코드

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

        // 10.12 여기까지

        auth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ScanBarCode.class));
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //R.id.nav_gallery, R.id.nav_slideshow,

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_beauty, R.id.nav_food,
                R.id.nav_health, R.id.nav_medical, R.id.nav_profile, R.id.nav_all)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("TOKEN", token);
        ConnectServerTask connectServerTask = new ConnectServerTask();
        connectServerTask.execute("http://192.168.0.11:3000/register", token);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if(firebaseAuth.getCurrentUser() == null){      //non-Login
            Log.d("AUTH STATE", "Main fail");
            startActivity(new Intent(this, LoadActivity.class));
            finish();
        }else{      //login
            Log.d("AUTH STATE", firebaseAuth.getUid());
            resultLogin(firebaseAuth.getCurrentUser());
        }
    }

    //change login state
    public boolean toggleIsSignIn(){
        isLogin = !isLogin;
        return isLogin;
    }

    //set user profile
    public void resultLogin(FirebaseUser user) {
        tv_result = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_id);
        iv_profile = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.iv_profile);

        tv_result.setText(user.getDisplayName());
        Glide.with(MainActivity.this).load(user.getPhotoUrl()).into(iv_profile);

        toggleIsSignIn();
    }

    //back button safety
    @Override
    public void onBackPressed() {
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;
        if(0<= gapTime && 2000 >= gapTime){
            super.onBackPressed();
        }else{
            backBtnTime = curTime;
            Toast.makeText(getApplicationContext(), "뒤로 가기 버튼을 한 번 더 누르시면 종료됩니다", Toast.LENGTH_SHORT).show();
        }
    }

    //create notification, search menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //notification setting page
        if(item.getItemId() == R.id.action_notification){
            Intent iNotification = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(iNotification);
        }else if(item.getItemId() == R.id.action_search){   //search activity start
            Intent iSearch = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(iSearch);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //in master
    }
}