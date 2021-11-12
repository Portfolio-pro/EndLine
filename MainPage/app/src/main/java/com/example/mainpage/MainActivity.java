package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerview_main;
    List<String> titles;
    List<Integer> images;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview_main=(RecyclerView)findViewById(R.id.recyclerview_main);

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

        adapter = new MainAdapter(this,titles,images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerview_main.setLayoutManager(gridLayoutManager);
        recyclerview_main.setAdapter(adapter);

    }
}