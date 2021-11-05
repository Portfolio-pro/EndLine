package com.example.endline_v1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.endline_v1.Products;
import com.example.endline_v1.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private TextView tv_home; // Home TextView
    private CalendarView cv_home; // Home CalanderView
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ListView lv;
    ArrayList<String> homeList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        tv_home = root.findViewById(R.id.text_home); // 날짜 보여주는 곳
        cv_home = root.findViewById(R.id.calendarView); // 달력
        lv = root.findViewById(R.id.list_home); // 리스트 뷰


        //11.05, Firestore에서 productName 필드의 값만 쭉 받아와야하는데 잘 모르겠음.
        homeList = new ArrayList<String>();

        db.collection("mainData").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException error) {
                homeList.clear();
                for(DocumentSnapshot snapshot : documentSnapshots){
                    //homeList.add(snapshot.getString("productName"));
                }
                ArrayAdapter<String>adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_selectable_list_item,homeList);
                adapter.notifyDataSetChanged();
                lv.setAdapter(adapter);
            }
        });
        //11.05, 여기까지 수정해야함


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

        return root;
    }
}