package com.example.endline_v1.ui.all;

import android.os.Bundle;
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

import com.example.endline_v1.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AllFragment extends Fragment {

    private AllViewModel AllViewModel;

    private TextView tv_home; // Home TextView
    private CalendarView cv_home; // Home CalanderView
    private ListView lv;
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        AllViewModel = new ViewModelProvider(this).get(AllViewModel.class);
        View root = inflater.inflate(R.layout.fragment_all, container, false);
        final TextView textView = root.findViewById(R.id.text_all);
        AllViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}