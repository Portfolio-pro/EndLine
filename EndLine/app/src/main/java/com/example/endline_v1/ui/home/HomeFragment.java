package com.example.endline_v1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.endline_v1.MainAdapter;
import com.example.endline_v1.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    RecyclerView recyclerview_main;
    List<String> titles;
    List<Integer> images;
    MainAdapter mainAdapter;

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

        recyclerview_main=(RecyclerView)root.findViewById(R.id.recyclerview_main);

        titles = new ArrayList<>();
        images = new ArrayList<>();

        titles.add("Beauty");
        titles.add("Food");
        titles.add("Health");
        titles.add("Medical");

        images.add(R.drawable.beauty);
        images.add(R.drawable.food);
        images.add(R.drawable.health);
        images.add(R.drawable.madical);

        mainAdapter = new MainAdapter(getActivity(),titles,images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        recyclerview_main.setLayoutManager(gridLayoutManager);
        recyclerview_main.setAdapter(mainAdapter);

        return root;
    }
}