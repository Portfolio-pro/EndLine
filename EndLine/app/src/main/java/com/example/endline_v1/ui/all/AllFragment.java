package com.example.endline_v1.ui.all;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.endline_v1.MainActivity;
import com.example.endline_v1.MyAdapter;
import com.example.endline_v1.Products;
import com.example.endline_v1.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AllFragment extends Fragment {

    private AllViewModel AllViewModel;

    private TextView tv_home; // Home TextView
    private CalendarView cv_home; // Home CalanderView
    private ListView lv;
    FirebaseFirestore db = FirebaseFirestore.getInstance();;

    RecyclerView rec_all;
    MyAdapter myAdapter;
    ArrayList<Products> productsArrayList;
    ProgressDialog progressDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        AllViewModel = new ViewModelProvider(this).get(AllViewModel.class);
        View root = inflater.inflate(R.layout.fragment_all, container, false);
        /*final TextView textView = root.findViewById(R.id.text_all);
        AllViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("불러오는 중...");
        progressDialog.show();

        rec_all = root.findViewById(R.id.recview);
        rec_all.setHasFixedSize(true);
        rec_all.setLayoutManager(new LinearLayoutManager(getActivity()));

        productsArrayList = new ArrayList<Products>();
        myAdapter = new MyAdapter(getActivity(),productsArrayList);

        rec_all.setAdapter(myAdapter);
        EventChangeListener();

        return root;
    }

    // All 카테고리가 잘 불러져오긴하는데 uid를 추가로 조건에 넣어야함.
    private void EventChangeListener() {
        db.collection("mainData").orderBy("addDay", Query.Direction.ASCENDING) // 정렬은 field 부분을 바꾸면 됨
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }
                       for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){
                                productsArrayList.add(dc.getDocument().toObject(Products.class));
                            }
                            myAdapter.notifyDataSetChanged();

                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }

                    }
                });
    }
}